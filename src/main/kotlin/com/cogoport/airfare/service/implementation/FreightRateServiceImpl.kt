package com.cogoport.airfare.services.interfaces.implementation

import com.cogoport.airfare.exception.AirfareError
import com.cogoport.airfare.exception.AirfareException
import com.cogoport.airfare.model.entity.FreightRate
import com.cogoport.airfare.model.request.FreightRateRequest
import com.cogoport.airfare.repository.FreightRateRepository
import com.cogoport.airfare.repository.FreightRateValidityRepository
import com.cogoport.airfare.service.interfaces.FreightRateService
import com.cogoport.airfare.validation.FreightRateValidation
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class FreightRateServiceImpl : FreightRateService {
    @Inject
    lateinit var airFreightRepo: FreightRateRepository

    @Inject
    lateinit var freightRateValidation: FreightRateValidation

    @Inject
    lateinit var freightRate: FreightRate
    @Inject
    lateinit var freightRateValidityRepository: FreightRateValidityRepository

    override suspend fun getAirFreightRate(request: FreightRateRequest): FreightRate {
        return airFreightRepo.findById(request.id!!)!!
    }

    override suspend fun createAirFreightRate(request: FreightRateRequest): Any? {
        for (weightSlab in request.weightSlabs) {
            if (!freightRateValidation.validateWeightSlabInput(weightSlab)) {
                throw(AirfareException(AirfareError.ERR_1001, "Weight slab in invalid"))
            }
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
        request.weightSlabs = request.weightSlabs.sortedBy { it.lower_limit }
        var object_freight = airFreightRepo.findFreightRate(request.originAirportId, request.destinationAirportId, request.commodity, request.commodityType, request.commoditySubType, request.airlineId, request.operationType, request.serviceProviderId, request.shipmentType, request.stackingType, request.priceType, request.cogoEntityId)

        if (object_freight == null) {
            if (freightRateValidation.validateValidityObject(request)) {
                if(request.rateSheetId != null){
                }
            }
//            airFreightRepo.save(
//                FreightRate(
//                    id = null,
//                    originAirportId = request.originAirportId,
//                    destinationAirportId = request.destinationAirportId,
//                    commodity = request.commodity,
//                    commodityType = request.commodityType,
//                    commoditySubType = request.commoditySubType,
//                    airlineId = request.airlineId,
//                    operationType = request.operationType,
//                    priceType = request.priceType,
//                    minPrice = request.minPrice,
//                    serviceProviderId = request.serviceProviderId,
//                    bulkOperationId = request.bulkOperationId,
//                    rateSheetId = request.rateSheetId,
//                    performedById = request.performedById,
//                    procuredById = request.procuredById,
//                    sourcedById = request.sourcedById,
//                    length = request.length,
//                    breadth = request.breadth,
//                    height = request.height,
//                    maximumWeight = request.maximumWeight,
//                    shipmentType = request.shipmentType,
//                    stackingType = request.stackingType,
//                    weightSlabs = request.weightSlabs,
//                    validityId = null,
//                    cogoEntityId = request.cogoEntityId
//                )
//            )
        }
        return 0
    }

//    private fun validateWeightSlabInput(weightSlabs: FreightRateWeightSlab): Boolean {
//        val CURRENCY_REGEX = Regex("^\\$?[0-9]+(\\.[0-9][0-9])?\$")
//        return weightSlabs.currency.matches(CURRENCY_REGEX) && weightSlabs.tariff_price >= 0
//    }
}
