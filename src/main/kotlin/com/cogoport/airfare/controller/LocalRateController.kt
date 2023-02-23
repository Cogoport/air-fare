package com.cogoport.airfare.controller

import com.cogoport.airfare.model.entity.LocalRate
import com.cogoport.airfare.model.request.LocalRateRequest
import com.cogoport.airfare.model.response.Response
import com.cogoport.airfare.service.interfaces.LocalRateService
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import jakarta.inject.Inject
import java.util.*

@Controller("/local-rates")
class LocalRateController {
    @Inject
    lateinit var airFreightRateLocalService: LocalRateService

    @Post("/create")
    suspend fun createAirFreightRateLocal(
        request: LocalRateRequest
    ): Response<UUID?> {
        return Response<UUID?>()
            .ok(HttpStatus.CREATED.name, airFreightRateLocalService.createAirFreightRateLocal(request))
    }

    @Get("/{?request*}")
    suspend fun getAirFreightRateLocal(request: LocalRateRequest): Response<LocalRate?> {
        return Response<LocalRate?>().ok(HttpStatus.OK.name, airFreightRateLocalService.getAirFreightRateLocal(request))
    }

    @Get("/list{?request*}")
    suspend fun listAirFreightLocal(
        request: LocalRateRequest
    ): List<LocalRate?> {
        return airFreightRateLocalService.listAirFreightRate(request)
    }
}
