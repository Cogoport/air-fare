package com.cogoport.airfare.service.interfaces
import com.cogoport.airfare.models.request.FreightWarehouseRequest

interface WarehouseRateService {

    suspend fun getAirFreightWarehouseRate(request: FreightWarehouseRequest): Any?
}
