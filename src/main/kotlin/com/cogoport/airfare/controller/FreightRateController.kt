package com.cogoport.airfare.controller
import com.cogoport.airfare.models.entity.FreightRate
import com.cogoport.airfare.models.request.FreightRateRequest
import com.cogoport.airfare.models.response.Response
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

    @Inject
    lateinit var WarehouseRateService: WarehouseRateService

    @Get("/{?request*}")
    suspend fun getAiFreightRate(@RequestBean request: FreightRateRequest): FreightRate {
        return Response<FreightRate>().ok(freightRateService.getAirFreightRate(request))
    }

    @Post("/create")
    suspend fun createAirFreightRate(
        request: FreightRateRequest
    ): Any? {
        return Response<Any?>().ok(freightRateService.createAirFreightRate(request))
    }
}
