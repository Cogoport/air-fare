package com.cogoport.airfare.controller

import com.cogoport.airfare.model.response.Response
import com.cogoport.airfare.models.entity.Surcharge
import com.cogoport.airfare.models.request.SurchargeRequest
import com.cogoport.airfare.service.interfaces.SurchargeService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import jakarta.inject.Inject

@Controller("/surcharges")
class SurchargesController {

    @Inject
    lateinit var surchargeService: SurchargeService

    @Post("/create")
    suspend fun createSurcharge(
        request: SurchargeRequest
    ): Any? {
        return Response<Any?>().ok(surchargeService.createSurcharge(request))
    }

    @Get("/{?request*}")
    suspend fun getSurcharge(request: SurchargeRequest): Surcharge? {
        return Response<Surcharge?>().ok(surchargeService.getSurcharge(request))
    }
}
