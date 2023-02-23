package com.cogoport.airfare.service.interfaces

import com.cogoport.airfare.models.entity.LocalRate
import com.cogoport.airfare.models.request.LocalRateRequest
import java.util.*

interface LocalRateService {
    suspend fun createLocalRate(request: LocalRateRequest): UUID?
    suspend fun getLocalRate(request: LocalRateRequest): LocalRate
    suspend fun listLocalRate(page: Int, pageLimit: Int): List<LocalRate>
}
