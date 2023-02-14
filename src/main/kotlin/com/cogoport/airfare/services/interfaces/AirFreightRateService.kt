package com.cogoport.airfare.services.interfaces
import com.cogoport.airfare.models.entity.AirFreightRates
import com.cogoport.airfare.models.request.AirFreightRequest

interface AirFreightRateService {
    suspend fun getAirFreightRate(request: AirFreightRequest): AirFreightRates
}
