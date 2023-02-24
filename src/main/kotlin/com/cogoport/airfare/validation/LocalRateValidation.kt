package com.cogoport.airfare.validation

import com.cogoport.airfare.constants.FreightConstants
import com.cogoport.airfare.constants.GlobalConstants
import com.cogoport.airfare.exception.AirfareError
import com.cogoport.airfare.exception.AirfareException
import com.cogoport.airfare.model.request.LocalRateRequest

class LocalRateValidation {

    fun validateLocalRequest(request: LocalRateRequest): Boolean {
        if (!GlobalConstants.tradeTypes.contains(request.tradeType)) {
            throw (AirfareException(AirfareError.ERR_1001, "Trade type is invalid"))
            return false
        }

        if (FreightConstants.commodity.contains(request.commodity)) {
            throw (AirfareException(AirfareError.ERR_1001, "Commodity is invalid"))
            return false
        }

        if (request.commodityType?.let { FreightConstants.commodityType.contains(it) } == true) {
            throw (AirfareException(AirfareError.ERR_1001, "Commodity type is invalid"))
            return false
        }

        if (request?.lineItems!!.distinct()?.size != request?.lineItems!!.size) {
            throw (AirfareException(AirfareError.ERR_1001, "Line items are duplicate"))
            return false
        }
        return true
    }
}
