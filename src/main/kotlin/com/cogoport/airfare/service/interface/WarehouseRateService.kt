package com.cogoport.airfare.service.`interface`
import com.cogoport.airfare.model.request.FreightWarehouseRequest

interface WarehouseRateService {

    suspend fun getAirFreightWarehouseRate(request: FreightWarehouseRequest): Any?
}
