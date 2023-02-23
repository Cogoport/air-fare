package com.cogoport.airfare.validations

import com.cogoport.airfare.models.entity.LocalLineItem
import com.cogoport.airfare.models.entity.LocalRate

class LineItemValidation {
    fun validateLineItems(lineItems: List<LocalLineItem>?, localRate: LocalRate): String {
//        for (lineItem in lineItems) {
//
//            if (!codeConfig.has(lineItem.code)){
//                return "code is invalid"
//            }
//
//
//            if (!codeConfig.has(localRate.tradeType)){
//                return "Can only be added for ${codeConfig.tradeTypes}."
//            }
//
//
//            if (!codeConfig.has(lineItem.unit)){
//                return "Can only be added for Unit ${codeConfig.units}."
//            }
//
//        }
        return "ok"
    }
}
