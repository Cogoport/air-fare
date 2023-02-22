package com.cogoport.airfare.validations

import com.cogoport.airfare.constants.GlobalConstants
import com.cogoport.airfare.exception.AirfareError
import com.cogoport.airfare.exception.AirfareException
import com.cogoport.airfare.model.entity.LocalRate
import com.cogoport.airfare.model.request.LocalRateRequest

class LocalRateValidation {
    fun validateDuplicateLineItems(localRate: LocalRate): Boolean {
        if (localRate?.lineItems?.distinct()?.size != localRate?.lineItems?.distinct()?.size) {
            throw (AirfareException(AirfareError.ERR_1001, "Line items are duplicate"))
        }
        return true
    }

    fun validateTradeType(request: LocalRateRequest): Boolean {
        if (GlobalConstants.tradeType.contains(request.tradeType)) {
            return true
        } else {
            throw (AirfareException(AirfareError.ERR_1001, "Trade type is invalid"))
        }
    }

    fun validateCommodity(request: LocalRateRequest): Boolean {
        if (GlobalConstants.commodity.contains(request.commodity)) {
            return true
        } else {
            throw (AirfareException(AirfareError.ERR_1001, "Commodity is invalid"))
        }
    }

    fun validateCommodityType(request: LocalRateRequest): Boolean {
        if (GlobalConstants.commodityType.contains(request.commodityType)) {
            return true
        } else {
            throw (AirfareException(AirfareError.ERR_1001, "Commodity type is invalid"))
        }
    }
}
