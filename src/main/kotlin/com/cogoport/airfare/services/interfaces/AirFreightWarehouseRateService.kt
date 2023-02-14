package com.cogoport.airfare.services.interfaces

import com.cogoport.airfare.models.entity.AirFreightWarehouseRates
import com.cogoport.airfare.models.request.AirFreightWarehouseRequest

interface AirFreightWarehouseRateService {

    suspend fun getAirFreightWarehouseRate(request: AirFreightWarehouseRequest): Any?
}
