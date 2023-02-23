package com.cogoport.airfare.service.implementation

import com.cogoport.airfare.common.audit.model.request.AuditRequest
import com.cogoport.airfare.common.audit.service.interfaces.AuditService
import com.cogoport.airfare.constants.FreightConstants
import com.cogoport.airfare.exception.AirfareError
import com.cogoport.airfare.exception.AirfareException
import com.cogoport.airfare.model.entity.FreightRate
import com.cogoport.airfare.model.entity.FreightRateValidity
import com.cogoport.airfare.model.request.FreightRateRequest
import com.cogoport.airfare.model.request.LocalRateRequest
import com.cogoport.airfare.repository.FreightRateRepository
import com.cogoport.airfare.repository.FreightRateValidityRepository
import com.cogoport.airfare.service.interfaces.FreightRateService
import com.cogoport.airfare.service.interfaces.LocalRateService
import com.cogoport.airfare.validation.FreightRateValidation
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.time.Instant
import java.time.LocalTime
import java.time.ZoneId
import java.util.*

@Singleton
class FreightRateServiceImpl : FreightRateService {
    @Inject
    lateinit var airFreightRepo: FreightRateRepository

    @Inject
    lateinit var freightRateValidation: FreightRateValidation

    @Inject
    lateinit var freightRateValidityRepository: FreightRateValidityRepository

    @Inject
    lateinit var localRateService: LocalRateService

    @Inject
    lateinit var auditService: AuditService

    override suspend fun getAirFreightRate(request: FreightRateRequest): FreightRate {
        return airFreightRepo.findById(request.id!!)!!
    }

    override suspend fun createAirFreightRate(request: FreightRateRequest): UUID {
        val utcZoneId = ZoneId.of("UTC")
        val beginningOfDay = Instant.now().atZone(utcZoneId).toLocalDate().atStartOfDay(utcZoneId)

        if (request.validityEnd >= beginningOfDay) {
            throw(AirfareException(AirfareError.ERR_1001, "Validity End is greater than beginning of the day"))
        }

        if (!freightRateValidation.validateWeightSlabInput(request.weightSlabs)) {
            throw(AirfareException(AirfareError.ERR_1001, "Weight slabs is invalid"))
        }
        if (request.commodity == "general") {
            request.commoditySubType = "all"
        }
        if (request.densityCategory == "general") {
            request.densityRatio = "1:1"
        }
        if (!freightRateValidation.validateDensityRatio(request)) {
            throw (AirfareException(AirfareError.ERR_1001, "should be in the form of 1:x"))
        }
        request.weightSlabs = request.weightSlabs.sortedBy { it.lowerLimit }
        var objectFreight = airFreightRepo.listFreightRate(request.originAirportId, request.destinationAirportId, request.commodity, request.commodityType, request.commoditySubType, request.airlineId, request.operationType, request.serviceProviderId, request.shipmentType, request.stackingType, request.priceType, request.cogoEntityId)
        var newRecord = false
        if (objectFreight == null) {
            objectFreight = listOf(
                airFreightRepo.save(
                    FreightRate(
                        id = null,
                        originAirportId = request.originAirportId,
                        destinationAirportId = request.destinationAirportId,
                        commodity = request.commodity,
                        commodityType = request.commodityType,
                        commoditySubType = request.commoditySubType,
                        airlineId = request.airlineId,
                        operationType = request.operationType,
                        priceType = request.priceType,
                        minPrice = request.minPrice,
                        serviceProviderId = request.serviceProviderId,
                        bulkOperationId = request.bulkOperationId,
                        rateSheetId = request.rateSheetId,
                        performedById = request.performedById,
                        procuredById = request.procuredById,
                        sourcedById = request.sourcedById,
                        length = request.length,
                        breadth = request.breadth,
                        height = request.height,
                        maximumWeight = request.maximumWeight,
                        shipmentType = request.shipmentType,
                        stackingType = request.stackingType,
                        cogoEntityId = request.cogoEntityId,
                        lastRateAvailableDate = null,
                        originLocalId = null,
                        destinationLocalId = null,
                        surchargeId = null,
                        originCountryId = null,
                        originTradeId = null,
                        originContinentId = null,
                        destinationCountryId = null,
                        destinationTradeId = null,
                        destinationContinentId = null
                    )
                )
            ) as List<FreightRate>

            newRecord = true
        }

        if (freightRateValidation.validateValidityObject(request)) {
            if (request.rateSheetId != null) {
                val asiaCalcuttaZone = ZoneId.of("Asia/Calcutta")
                val utcZone = ZoneId.of("UTC")

                request.validityStart = request.validityStart.toLocalDateTime().atZone(asiaCalcuttaZone).with(LocalTime.MIN).withZoneSameInstant(utcZone)
                request.validityEnd = request.validityEnd.toLocalDateTime().atZone(asiaCalcuttaZone).with(LocalTime.MAX).withZoneSameInstant(utcZone)
            }
            val validityId = objectFreight.map { it }.first().let { setValidities(request, it, true) }
            objectFreight.map { it }.first()?.let { setLastRateAvailableDate(it) }
            objectFreight.map { it }.first()?.let { setObjectParameter(it, request) }

            if (freightRateValidation.validateMainObject(objectFreight.first())) {
                if (newRecord) {
                    updateForeignReferences(objectFreight.first())
                }
//                val auditParams = getAuditParams(object_freight.first(), validityId)
                auditService.createAudit(
                    AuditRequest(
                        objectType = "AirFreightRate",
                        objectId = objectFreight.first().id!!,
                        bulkOperationId = null,
                        actionName = "create",
                        data = request,
                        validityId = validityId,
                        procuredById = request.procuredById,
                        sourcedById = request.sourcedById,
                        performedById = request.performedById,
                        rateSheetId = null
                    )
                )
                // TODO: 1.create_trade_requirement_rate_mapping is not used as of now so not calling it in AIR
                // TODO: 2. CreateSaasAirScheduleAirportPair not calling as of now else have to move saasAirSchedule here
                // TODO: 3. UpdateOrganization in Sidekiq delay
            }
        }

        return objectFreight.first().id!!
    }

    private suspend fun setValidities(request: FreightRateRequest, objectFreight: FreightRate, deleted: Boolean): UUID? {
        var minDensityWeight = FreightConstants.MinDensityWeight
        var maxDensityWeight = FreightConstants.MaxCargoLimit
        if (request.densityCategory == "low_density") {
            if (request.densityRatio != null) {
                minDensityWeight = request.densityRatio!!.replace(" ", "").split(":")?.last().toInt().toDouble()
            }
            maxDensityWeight = minDensityWeight
        } else if (request.densityCategory == "high_density" && request.densityRatio != null) {
            minDensityWeight = request.densityRatio!!.replace(" ", "").split(":")?.last().toInt().toDouble()
        }
        if (objectFreight != null) {
            var validities = freightRateValidityRepository.findByRateId(objectFreight.id!!)
            for (validity in validities) {
                if (validity.densityCategory == null) {
                    validity.densityCategory = "general"
                }
                if (deleted && validity.rateId == request.id) {
                    request.minPrice = validity.minPrice
                    continue
                }
                if (validity.status == false) {
                    continue
                }
                if (validity.densityCategory == "high_density" && !deleted && validity.densityCategory == request.densityCategory) {
                    if ((validity.minDensityWeight < minDensityWeight) && (validity.maxDensityWeight > minDensityWeight)) {
                        validity.maxDensityWeight = minDensityWeight
                    }
                    if (validity.minDensityWeight > minDensityWeight && maxDensityWeight > validity.minDensityWeight) {
                        maxDensityWeight = validity.minDensityWeight
                    }
                }
                if (validity.densityCategory == request.densityCategory && maxDensityWeight == validity.maxDensityWeight && minDensityWeight == validity.minDensityWeight) {
                    if (validity.validityStart > request.validityEnd) {
                        continue
                    }
                    if (validity.validityEnd < request.validityStart) {
                        continue
                    }
                    if (validity.validityStart >= request.validityStart && validity.validityEnd < request.validityEnd) {
                        validity.status = false
                        continue
                    }
                    if (validity.validityStart < request.validityStart && validity.validityEnd <= request.validityEnd) {
                        validity.validityEnd = request.validityStart.minusMinutes(1)
                        continue
                    }
                    if (validity.validityStart >= request.validityStart && validity.validityEnd > request.validityEnd) {
                        validity.validityStart = request.validityEnd.plusMinutes(1)
                        continue
                    }
                    if (validity.validityStart < request.validityStart && validity.validityEnd > request.validityEnd) {
                        freightRateValidityRepository.save(
                            FreightRateValidity(
                                id = UUID.randomUUID(),
                                rateId = validity.rateId,
                                status = true,
                                validityStart = validity.validityStart,
                                validityEnd = request.validityStart.minusMinutes(1),
                                minPrice = validity.minPrice,
                                currency = validity.currency,
                                likeCount = validity.likeCount,
                                dislikesCount = validity.dislikesCount,
                                weightSlabs = validity.weightSlabs,
                                densityCategory = validity.densityCategory,
                                minDensityWeight = validity.minDensityWeight,
                                maxDensityWeight = validity.maxDensityWeight
                            )
                        )
                        freightRateValidityRepository.save(
                            FreightRateValidity(
                                id = UUID.randomUUID(),
                                rateId = validity.rateId,
                                status = true,
                                validityStart = request.validityEnd.plusMinutes(1),
                                validityEnd = validity.validityEnd,
                                minPrice = validity.minPrice,
                                currency = validity.currency,
                                likeCount = validity.likeCount,
                                dislikesCount = validity.dislikesCount,
                                weightSlabs = validity.weightSlabs,
                                densityCategory = validity.densityCategory,
                                minDensityWeight = validity.minDensityWeight,
                                maxDensityWeight = validity.maxDensityWeight
                            )
                        )
                        continue
                    }
                }
            }

            if (!deleted) {
                val newValidityObject = freightRateValidityRepository.save(
                    FreightRateValidity(
                        id = UUID.randomUUID(),
                        rateId = objectFreight.id,
                        status = true,
                        validityStart = request.validityStart,
                        validityEnd = request.validityEnd,
                        minPrice = request.minPrice!!,
                        currency = request.currency,
                        likeCount = 0,
                        dislikesCount = 0,
                        weightSlabs = request.weightSlabs,
                        densityCategory = request.densityCategory,
                        minDensityWeight = minDensityWeight,
                        maxDensityWeight = maxDensityWeight
                    )
                )
                if (!deleted) {
                    return newValidityObject.id
                }
            }
        }
        return null
    }
    private suspend fun setLastRateAvailableDate(objectFreight: FreightRate) {
        var validities = freightRateValidityRepository.findByRateIdAndStatus(objectFreight.id!!, true)
        validities = validities.sortedBy { it.validityEnd }

        objectFreight.lastRateAvailableDate = validities.last().validityEnd
    }

    private fun setObjectParameter(objectFreight: FreightRate, request: FreightRateRequest) {
        objectFreight.maximumWeight = request.maximumWeight
        objectFreight.length = request.length
        objectFreight.breadth = request.breadth
        objectFreight.height = request.height
        objectFreight.rateNotAvailableEntry = false
    }

    private suspend fun updateForeignReferences(objectFreight: FreightRate) {
        updateLocalReferences(objectFreight)
        if (objectFreight.priceType == "net_net") {
            updateSurchargeReference(objectFreight)
        }
    }

    private suspend fun updateLocalReferences(objectFreight: FreightRate) {
        val originLocal = localRateService.listLocalRate(LocalRateRequest(id = null, airportId = objectFreight.originAirportId, airlineId = objectFreight.airlineId, commodity = objectFreight.commodity, commodityType = objectFreight.commodityType, serviceProviderId = objectFreight.serviceProviderId, tradeType = "export"))
        val destinationLocal = localRateService.listLocalRate(LocalRateRequest(id = null, airportId = objectFreight.destinationAirportId, airlineId = objectFreight.airlineId, commodity = objectFreight.commodity, commodityType = objectFreight.commodityType, serviceProviderId = objectFreight.serviceProviderId, tradeType = "import"))

        if (originLocal != null) {
            objectFreight.originLocalId = originLocal.first()?.id
        }
        if (destinationLocal != null) {
            objectFreight.destinationLocalId = destinationLocal.first()?.id
        }
    }

    private fun updateSurchargeReference(objectFreight: FreightRate) {
        // dependency on chetna
    }

    private fun getAuditParams(objectFreight: FreightRate, validityId: UUID?): ArrayList<Any?> {
        return arrayListOf(objectFreight.originAirportId, objectFreight.destinationAirportId, objectFreight.commodity, objectFreight.commodityType, objectFreight.commoditySubType, objectFreight.shipmentType, objectFreight.stackingType, objectFreight.airlineId, objectFreight.operationType, objectFreight.serviceProviderId, validityId)
    }
}
