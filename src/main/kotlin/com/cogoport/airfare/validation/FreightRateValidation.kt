package com.cogoport.airfare.validation

import com.cogoport.airfare.exception.AirfareError
import com.cogoport.airfare.exception.AirfareException
import com.cogoport.airfare.model.entity.FreightRateWeightSlab
import com.cogoport.airfare.model.request.FreightRateRequest
import java.time.LocalDateTime

class FreightRateValidation {
    fun validateWeightSlabInput(weightSlabs: FreightRateWeightSlab): Boolean {
        val CURRENCY_REGEX = Regex("^\\$?[0-9]+(\\.[0-9][0-9])?\$")
        return (weightSlabs.currency.matches(CURRENCY_REGEX)) && (weightSlabs.tariff_price >= 0)
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
        if (request.validityEnd <= request.validityStart)
            throw(AirfareException(AirfareError.ERR_1001,"validity_end can not be lesser than or equal to validity_start'"))

        return true
    }
}
