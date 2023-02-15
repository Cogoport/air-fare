package com.cogoport.airfare.service.interfaces
import com.cogoport.airfare.model.entity.AirFreightRates
import com.cogoport.airfare.model.request.AirFreightRequest

interface AirFreightRateService {
    suspend fun getAirFreightRate(request: AirFreightRequest): AirFreightRates
}
