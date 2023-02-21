package com.cogoport.airfare.service.interfaces
import com.cogoport.airfare.model.request.FreightWarehouseRequest

interface WarehouseRateService {

    suspend fun getAirFreightWarehouseRate(request: FreightWarehouseRequest): Any?
}
