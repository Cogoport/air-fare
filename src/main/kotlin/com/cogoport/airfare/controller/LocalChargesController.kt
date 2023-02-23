package com.cogoport.airfare.controller

import com.cogoport.airfare.models.entity.LocalCharge
import com.cogoport.airfare.models.request.LocalChargeRequest
import com.cogoport.airfare.models.response.Response
import com.cogoport.airfare.service.interfaces.LocalChargeService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import jakarta.inject.Inject

@Controller("/local-charges")
class LocalChargesController {
    @Inject lateinit var chargeCodeService: LocalChargeService

    @Post("/create")
    suspend fun createLocalChargeCode(
        request: LocalChargeRequest
    ): Any? {
        return Response<Any?>().ok(chargeCodeService.createLocalChargeCode(request))
    }

    @Get("/{?request*}")
    suspend fun getLocalChargeCode(request: LocalChargeRequest): LocalCharge? {
        return Response<LocalCharge?>().ok(chargeCodeService.getLocalChargeCode(request))
    }
}
