package com.cogoport.airfare.service.interfaces
import com.cogoport.airfare.model.entity.FreightRate
import com.cogoport.airfare.model.request.FreightRateRequest
import java.util.*

interface FreightRateService {
    suspend fun getAirFreightRate(request: FreightRateRequest): FreightRate
    suspend fun createAirFreightRate(request: FreightRateRequest): UUID
    suspend fun listFreightRate(request: FreightRateRequest): List<FreightRate>?
}
