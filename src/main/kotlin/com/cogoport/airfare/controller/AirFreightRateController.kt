package com.cogoport.airfare.controller
import com.cogoport.airfare.model.entity.AirFreightRates
import com.cogoport.airfare.model.request.AirFreightRequest
import com.cogoport.airfare.model.response.Response
import com.cogoport.airfare.service.interfaces.AirFreightRateService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.RequestBean
import jakarta.inject.Inject

@Controller("/air-freight-rate")
class AirFreightRateController {

    @Inject
    lateinit var airFreightRateService: AirFreightRateService

    @Get("/{?request*}")
    suspend fun getAirFreightRate(@RequestBean request: AirFreightRequest): AirFreightRates {
        return Response<AirFreightRates>().ok(airFreightRateService.getAirFreightRate(request))
    }
}
