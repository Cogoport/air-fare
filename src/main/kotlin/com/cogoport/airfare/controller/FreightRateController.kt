package com.cogoport.airfare.controller
import com.cogoport.airfare.model.entity.FreightRate
import com.cogoport.airfare.model.request.FreightRateRequest
import com.cogoport.airfare.model.response.Response
import com.cogoport.airfare.service.interfaces.FreightRateService
import com.cogoport.airfare.service.interfaces.WarehouseRateService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.RequestBean
import jakarta.inject.Inject

@Controller("/air-freight-rate")
class FreightRateController {

    @Inject
    lateinit var freightRateService: FreightRateService

    @Get("/{?request*}")
    suspend fun getAirFreightRate(@RequestBean request: FreightRateRequest): FreightRate {
        return Response<FreightRate>().ok(freightRateService.getAirFreightRate(request))
    }

    @Post("/create")
    suspend fun createAirFreightRate(
        request: FreightRateRequest
    ): Any? {
        return Response<Any?>().ok(freightRateService.createAirFreightRate(request))
    }
}
