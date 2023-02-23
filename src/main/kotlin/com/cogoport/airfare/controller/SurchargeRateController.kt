package com.cogoport.airfare.controller

import com.cogoport.airfare.model.entity.SurchargeRate
import com.cogoport.airfare.model.request.SurchargeRateRequest
import com.cogoport.airfare.service.interfaces.SurchargeRateService
import io.micronaut.http.annotation.Get
import jakarta.inject.Inject

class SurchargeRateController {
    @Inject
    lateinit var surchargeRateService: SurchargeRateService

    @Get("/list{?request*}")
    suspend fun listSurchargeRate(
        request: SurchargeRateRequest
    ): List<SurchargeRate?> {
        return surchargeRateService.listSurchargeRate(request)
    }
}
