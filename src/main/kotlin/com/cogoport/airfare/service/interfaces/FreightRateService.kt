package com.cogoport.airfare.service.interfaces
import com.cogoport.airfare.models.entity.FreightRate
import com.cogoport.airfare.models.request.FreightRateRequest

interface FreightRateService {
    suspend fun getAirFreightRate(request: FreightRateRequest): FreightRate
    suspend fun createAirFreightRate(request: FreightRateRequest): Any?
}
