package com.cogoport.airfare.validation

import com.cogoport.airfare.model.entity.LocalRate
import com.cogoport.airfare.models.entity.LocalLineItem
import com.cogoport.airfare.service.interfaces.LocalChargeService
import jakarta.inject.Inject
import javax.script.ScriptEngineManager

class LocalLineItemValidation {
    private val scriptEngineManager = ScriptEngineManager()
    private val scriptEngine = scriptEngineManager.getEngineByName("kotlin")

    @Inject
    lateinit var localChargeService: LocalChargeService
    suspend fun validateLineItems(lineItems: List<LocalLineItem>, localRate: LocalRate): MutableList<String> {
        val errorMessage: MutableList<String> = mutableListOf()
        val commodityType = localRate.commodityType
        val commodity = localRate.commodity

        val mandatoryCodeConfig = localChargeService.getLocalChargeByTag(arrayOf("mandatory"))
        for (lineItem in lineItems) {
            val codeConfig = localChargeService.getLocalCharge(lineItem.code)
            if (codeConfig == null) {
                errorMessage.add("${lineItem.code} code is invalid")
            }

            if (codeConfig != null && !codeConfig.tradeTypes?.contains(localRate.tradeType)!!) {
                errorMessage.add("${lineItem.code} can only be added for ${codeConfig.tradeTypes}.")
            }

            if (codeConfig != null && !codeConfig.units?.contains(lineItem.unit)!!) {
                errorMessage.add("${lineItem.code} can only be added for units ${codeConfig.units}.")
            }

            if (codeConfig != null && codeConfig.condition != "true" && scriptEngine.eval((codeConfig.condition)) == false) {
                errorMessage.add("${lineItem.code} code is invalid")
            }
        }

        if (mandatoryCodeConfig != null) {
            for (mandatoryCode in mandatoryCodeConfig) {
                for (lineItem in lineItems) {
                    if (lineItem.code != mandatoryCode.code) {
                        errorMessage.add("${mandatoryCode.code} code is mandatory")
                    }
                }
                return errorMessage
            }
        }

        return errorMessage
    }
}
