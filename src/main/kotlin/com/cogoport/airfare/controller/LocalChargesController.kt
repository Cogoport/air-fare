package com.cogoport.airfare.controller

import com.cogoport.airfare.model.response.Response
import com.cogoport.airfare.models.entity.LocalCharge
import com.cogoport.airfare.models.request.LocalChargeRequest
import com.cogoport.airfare.service.interfaces.LocalChargeService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import jakarta.inject.Inject

@Controller("/local-charges")
class LocalChargesController {
    @Inject lateinit var chargeCodeService: LocalChargeService

    @Post("/create")
    suspend fun createLocalCharge(
        request: LocalChargeRequest
    ): Any? {
        return Response<Any?>().ok(chargeCodeService.createLocalCharge(request))
    }

    @Get("/{?request*}")
    suspend fun getLocalCharge(request: LocalChargeRequest): LocalCharge? {
        return Response<LocalCharge?>().ok(chargeCodeService.getLocalCharge(request.code))
    }
}
