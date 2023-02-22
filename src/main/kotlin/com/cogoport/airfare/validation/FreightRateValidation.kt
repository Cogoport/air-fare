package com.cogoport.airfare.validation

import com.cogoport.airfare.enum.Constants
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
    suspend fun validateMainObject(objectFreight: FreightRate) {
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
            if (!Constants.DensityCategories.contains(validity.densityCategory)) {
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
        if (!Constants.shipmentType.contains(objectFreight.shipmentType)) {
            throw(AirfareException(AirfareError.ERR_1001, " Shipment Type is invalid"))
        }
        if (!Constants.stackingType.contains(objectFreight.stackingType)) {
            throw(AirfareException(AirfareError.ERR_1001, " Stacking Type is invalid"))
        }

    }
}
