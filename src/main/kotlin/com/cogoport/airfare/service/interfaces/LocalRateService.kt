package com.cogoport.airfare.service.interfaces

import com.cogoport.airfare.model.entity.LocalRate
import com.cogoport.airfare.model.request.LocalRateRequest
import java.util.*

interface LocalRateService {
    suspend fun createLocalRate(request: LocalRateRequest): UUID?
    suspend fun getLocalRate(request: LocalRateRequest): LocalRate
    suspend fun listLocalRate(request: LocalRateRequest): List<LocalRate?>
}
