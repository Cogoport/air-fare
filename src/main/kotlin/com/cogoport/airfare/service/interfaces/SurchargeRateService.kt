package com.cogoport.airfare.service.interfaces

import com.cogoport.airfare.model.entity.SurchargeRate
import com.cogoport.airfare.model.request.SurchargeRateRequest
import java.util.*

interface SurchargeRateService {
    suspend fun listSurchargeRate(request: SurchargeRateRequest): List<SurchargeRate?>
}