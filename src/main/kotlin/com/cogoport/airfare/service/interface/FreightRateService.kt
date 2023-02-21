package com.cogoport.airfare.service.`interface`
import com.cogoport.airfare.model.entity.FreightRate
import com.cogoport.airfare.model.request.FreightRateRequest

interface FreightRateService {
    suspend fun getAirFreightRate(request: FreightRateRequest): FreightRate
    suspend fun createAirFreightRate(request: FreightRateRequest): Any?
}
