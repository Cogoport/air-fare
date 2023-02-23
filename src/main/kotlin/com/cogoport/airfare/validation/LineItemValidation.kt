package com.cogoport.airfare.validation

import com.cogoport.airfare.model.entity.LocalRate
import com.cogoport.airfare.models.entity.LocalLineItem
import com.cogoport.airfare.service.interfaces.LocalChargeService
import jakarta.inject.Inject
import javax.script.ScriptEngineManager

class LineItemValidation {
    private val scriptEngineManager = ScriptEngineManager()
    private val scriptEngine = scriptEngineManager.getEngineByName("kotlin")

    @Inject
    lateinit var localChargeService: LocalChargeService
    suspend fun validateLineItems(lineItems: List<LocalLineItem>, localRate: LocalRate): Array<String> {
        var errorMessage: Array<String> = arrayOf()
        val commodity = localRate.commodity
        val commodityType = localRate.commodityType

        for (lineItem in lineItems) {
            val codeConfig = localChargeService.getLocalCharge(lineItem.code)
            if (codeConfig == null) {
                errorMessage += ("${lineItem.code} code is invalid")
            }

            if (codeConfig != null && !codeConfig.tradeTypes?.contains(localRate.tradeType)!!) {
                errorMessage += ("${lineItem.code} can only be added for ${codeConfig.tradeTypes}.")
            }

            if (codeConfig != null && !codeConfig.units?.contains(lineItem.unit)!!) {
                errorMessage += ("${lineItem.code} can only be added for units ${codeConfig.units}.")
            }

            if (codeConfig != null && codeConfig.condition != "true" && scriptEngine.eval(("$commodity == ${codeConfig.condition}")) == false) {
            }
        }

        return errorMessage
    }
}
