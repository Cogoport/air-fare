package com.cogoport.airfare.controller

import com.cogoport.airfare.model.entity.AirFreightRateLocals
import com.cogoport.airfare.model.request.AirFreightRateLocalRequest
import com.cogoport.airfare.service.interfaces.AirFreightRateLocalService
import com.cogoport.airfare.model.response.Response
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import jakarta.inject.Inject

@Controller("/air-freight-rate-local")
class AirFreightRateLocalController {
    @Inject
    lateinit var airFreightRateLocalService: AirFreightRateLocalService

    @Get("/{?request*}")
    suspend fun getAirFreightRateLocal(
        request: AirFreightRateLocalRequest
    ): Response<AirFreightRateLocals> {
        return Response<AirFreightRateLocals>()
            .ok(HttpStatus.OK.name, airFreightRateLocalService.getAirFreightRateLocal(request))
    }
}
