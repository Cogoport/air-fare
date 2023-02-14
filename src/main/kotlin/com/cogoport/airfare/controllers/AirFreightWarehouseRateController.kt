package com.cogoport.airfare.controllers

import com.cogoport.airfare.enums.Response
import com.cogoport.airfare.models.request.AirFreightWarehouseRequest
import com.cogoport.airfare.services.interfaces.AirFreightWarehouseRateService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.RequestBean
import jakarta.inject.Inject

@Controller("/air-freight-warehouse-rate")
class AirFreightWarehouseRateController {
    @Inject
    lateinit var airFreightWarehouseRateService: AirFreightWarehouseRateService

    @Get("/{?request*}")
    suspend fun getAirFreightWarehouseRate(@RequestBean request: AirFreightWarehouseRequest) {
        Response<Any?>().ok(airFreightWarehouseRateService.getAirFreightWarehouseRate(request))
    }
}
