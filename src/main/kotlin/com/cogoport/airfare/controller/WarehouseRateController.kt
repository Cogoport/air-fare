package com.cogoport.airfare.controller

import com.cogoport.airfare.model.request.FreightWarehouseRequest
import com.cogoport.airfare.model.response.Response
import com.cogoport.airfare.service.`interface`.WarehouseRateService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.RequestBean
import jakarta.inject.Inject

@Controller("/air-freight-warehouse-rate")
class WarehouseRateController {
    @Inject
    lateinit var WarehouseRateService: WarehouseRateService

    @Get("/{?request*}")
    suspend fun getAirFreightWarehouseRate(@RequestBean request: FreightWarehouseRequest) {
        Response<Any?>().ok(WarehouseRateService.getAirFreightWarehouseRate(request))
    }
}