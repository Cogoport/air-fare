package com.cogoport.airfare.service.interfaces
import com.cogoport.airfare.model.request.AirFreightWarehouseRequest

interface AirFreightWarehouseRateService {

    suspend fun getAirFreightWarehouseRate(request: AirFreightWarehouseRequest): Any?
}
