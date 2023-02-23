package com.cogoport.airfare.service.interfaces

import com.cogoport.airfare.model.entity.LocalRate
import com.cogoport.airfare.model.request.LocalRateRequest
import java.util.*

interface LocalRateService {
    suspend fun createAirFreightRateLocal(request: LocalRateRequest): UUID?
    suspend fun getAirFreightRateLocal(request: LocalRateRequest): LocalRate?
    suspend fun listAirFreightRate(page: Int, pageLimit: Int, request: LocalRateRequest): List<LocalRate?>
}
