package com.cogoport.airfare.validation
import com.cogoport.airfare.common.location.model.request.LocationRequest
import com.cogoport.airfare.common.location.model.request.OrganizationRequest
import com.cogoport.airfare.common.location.service.interfaces.LocationService
import com.cogoport.airfare.common.location.service.interfaces.OrganizationService
import com.cogoport.airfare.common.operator.model.request.OperatorRequest
import com.cogoport.airfare.common.operator.service.interfaces.OperatorService
import com.cogoport.airfare.constants.FreightConstants
import com.cogoport.airfare.exception.AirfareError
import com.cogoport.airfare.exception.AirfareException
import com.cogoport.airfare.model.entity.FreightRate
import com.cogoport.airfare.model.entity.FreightRateWeightSlab
import com.cogoport.airfare.model.request.FreightRateRequest
import com.cogoport.airfare.repository.FreightRateValidityRepository
import jakarta.inject.Inject
import java.time.LocalDateTime

class FreightRateValidation {
    @Inject
    lateinit var freightRateValidityRepository: FreightRateValidityRepository

    @Inject
    lateinit var locationService: LocationService

    @Inject
    lateinit var organizationService: OrganizationService

    @Inject
    lateinit var operatorService: OperatorService

    val CURRENCY_REGEX = Regex("^\\$?[0-9]+(\\.[0-9][0-9])?\$")

    fun validateWeightSlabInput(weightSlabs: List<FreightRateWeightSlab>): Boolean {
        for (weightSlab in weightSlabs) {
            if (!(weightSlab.currency.matches(CURRENCY_REGEX) &&(weightSlab.tariffPrice >= 0))) {
                return false
            }
        }
        return true
    }
    fun validateDensityRatio(request: FreightRateRequest): Boolean {
        return request.densityRatio?.replace(" ", "")?.split(":")?.first()?.toInt() == 1
    }

    fun validateValidityObject(request: FreightRateRequest): Boolean {
        if (request.validityStart == null) {
            throw (AirfareException(AirfareError.ERR_1001, "validity start is invalid"))
        }
        if (request.validityEnd == null) {
            throw (AirfareException(AirfareError.ERR_1001, "validity end is invalid"))
        }
        if (request.validityEnd.toLocalDateTime() > LocalDateTime.now().plusDays(120)) {
            throw(AirfareException(AirfareError.ERR_1001, "validity_end can not be greater than 120 days from current date"))
        }

        if (request.validityStart.toLocalDateTime() < LocalDateTime.now().minusDays(15)) {
            throw(AirfareException(AirfareError.ERR_1001, "validity_start can not be less than 15 days from current date"))
        }
        if (request.validityEnd <= request.validityStart) {
            throw(AirfareException(AirfareError.ERR_1001, "validity_end can not be lesser than or equal to validity_start'"))
        }

        return true
    }

    @Throws(AirfareException::class)
    suspend fun validateMainObject(objectFreight: FreightRate): Boolean {
        var validities = freightRateValidityRepository.findByRateId(objectFreight.id!!)
        for (validity in validities) {
            if (validity.minDensityWeight != null && validity.minDensityWeight <= 0) {
                throw(AirfareException(AirfareError.ERR_1001, "Minimum density weight corresponding to validity id: ${validity.id} should be greater than zero"))
            }
            if (validity.maxDensityWeight != null && validity.maxDensityWeight <= 0) {
                throw(AirfareException(AirfareError.ERR_1001, "Maximum density weight corresponding to validity id: ${validity.id} should be greater than zero"))
            }
            if (validity.validityStart == null || validity.validityEnd == null || validity.minPrice == null) {
                throw (AirfareException(AirfareError.ERR_1001, "Validity corresponding to validity id: ${validity.id} is invalid"))
            }
            if (!validity.currency.matches(CURRENCY_REGEX)) {
                throw(AirfareException(AirfareError.ERR_1001, "Validity currency corresponding to id:  ${validity.id} is invalid"))
            }
            if (!validateWeightSlabInput(validity.weightSlabs)) {
                throw (AirfareException(AirfareError.ERR_1001, "Validity WeightSlabs corresponding to id:  ${validity.id} is invalid"))
            }
            if (!FreightConstants.DensityCategories.contains(validity.densityCategory)) {
                throw(AirfareException(AirfareError.ERR_1001, "Validity Density Category corresponding to id:  ${validity.id} is invalid"))
            }
            if (validity.weightSlabs.map { it.upperLimit <= it.lowerLimit }.contains(true)) {
                throw(AirfareException(AirfareError.ERR_1001, "Validity Weightslab corresponding to id:  ${validity.id} is invalid"))
            }
            val lowerLimits = validity.weightSlabs.map { it.lowerLimit }
            val upperLimits = validity.weightSlabs.map { it.upperLimit }
            var iterator = 0
            while (iterator <= upperLimits.count() - 1) {
                if (upperLimits[iterator].toFloat() != lowerLimits[iterator + 1].toFloat()) {
                    throw (AirfareException(AirfareError.ERR_1001, "Validity WeightSlabs corresponding to id:  ${validity.id} is not consistent"))
                }
                iterator += 1
            }
        } // validate validity
        if (!FreightConstants.shipmentType.contains(objectFreight.shipmentType)) {
            throw(AirfareException(AirfareError.ERR_1001, " Shipment Type is invalid"))
        }
        if (!FreightConstants.stackingType.contains(objectFreight.stackingType)) {
            throw(AirfareException(AirfareError.ERR_1001, " Stacking Type is invalid"))
        }

        if (!FreightConstants.commodity.contains(objectFreight.commodity)) {
            throw(AirfareException(AirfareError.ERR_1001, " Commodity is invalid"))
        }

        if (!FreightConstants.commodityType.contains(objectFreight.commodityType)) {
            throw(AirfareException(AirfareError.ERR_1001, " Commodity Type is invalid"))
        }
        if (!FreightConstants.commoditySubType.contains(objectFreight.commoditySubType)) {
            throw(AirfareException(AirfareError.ERR_1001, " Commodity Sub Type is invalid"))
        }
        if (!FreightConstants.priceType.contains(objectFreight.priceType)) {
            throw(AirfareException(AirfareError.ERR_1001, " PriceType is invalid"))
        }
        if (!FreightConstants.operationType.contains(objectFreight.operationType)) {
            throw(AirfareException(AirfareError.ERR_1001, " Operation Type is invalid"))
        }
        if (objectFreight.length!! < 0) {
            throw(AirfareException(AirfareError.ERR_1001, " Length is invalid"))
        }
        if (objectFreight.breadth!! < 0) {
            throw(AirfareException(AirfareError.ERR_1001, " Breadth is invalid"))
        }
        if (objectFreight.height!! < 0) {
            throw(AirfareException(AirfareError.ERR_1001, " Height is invalid"))
        }

        val originLocation = locationService.getLocationById(LocationRequest(objectFreight.originAirportId, "airport"))
        val destinationLocation = locationService.getLocationById(LocationRequest(objectFreight.destinationAirportId, "airport"))

        if (originLocation == null) {
            throw(AirfareException(AirfareError.ERR_1001, " Origin Airport is invalid"))
        }
        if (destinationLocation == null) {
            throw(AirfareException(AirfareError.ERR_1001, " Destination Airport is invalid"))
        }

        if ((originLocation.first() != null) && (destinationLocation.first() != null)) {
            if (originLocation.first()!!.countryCode == destinationLocation.first()!!.countryCode) {
                throw(AirfareException(AirfareError.ERR_1001, " Destination Airport cannot be same as Origin Airport"))
            }
        }

        val serviceProvider = organizationService.getOrganizationById(OrganizationRequest(objectFreight.serviceProviderId, "service_provider"))
        if (serviceProvider == null) {
            throw(AirfareException(AirfareError.ERR_1001, " Service Provider is invalid"))
        }

        val airline = operatorService.getOperatorById(OperatorRequest(objectFreight.airlineId, "airline"))
        if (airline == null) {
            throw(AirfareException(AirfareError.ERR_1001, " Airline is invalid"))
        }

        return true
    }
}
