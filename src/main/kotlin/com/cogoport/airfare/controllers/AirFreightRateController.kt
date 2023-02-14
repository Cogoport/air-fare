package com.cogoport.airfare.controllers
import com.cogoport.airfare.enums.Response
import com.cogoport.airfare.models.entity.AirFreightRates
import com.cogoport.airfare.models.entity.AirFreightWarehouseRates
import com.cogoport.airfare.models.request.AirFreightRequest
import com.cogoport.airfare.models.request.AirFreightWarehouseRequest
import com.cogoport.airfare.services.interfaces.AirFreightRateService
import com.cogoport.airfare.services.interfaces.AirFreightWarehouseRateService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.RequestBean
import jakarta.inject.Inject

@Controller("/air-freight-rate")
class AirFreightRateController {

    @Inject
    lateinit var airFreightRateService: AirFreightRateService

    @Inject
    lateinit var airFreightWarehouseRateService: AirFreightWarehouseRateService

    @Get("/{?request*}")
    suspend fun getAiFreightRate(@RequestBean request: AirFreightRequest): AirFreightRates {
        return Response<AirFreightRates>().ok(airFreightRateService.getAirFreightRate(request))
    }
}
