package com.cogoport.airfare.controller

import com.cogoport.airfare.models.entity.LocalRate
import com.cogoport.airfare.models.request.LocalRateRequest
import com.cogoport.airfare.models.response.Response
import com.cogoport.airfare.service.interfaces.LocalRateService
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.QueryValue
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
    suspend fun getAirFreightRateLocal(
        request: LocalRateRequest
    ): Response<LocalRate> {
        return Response<LocalRate>().ok(HttpStatus.OK.name, airFreightRateLocalService.getAirFreightRateLocal(request))
    }

    @Get("/list")
    suspend fun listAirFreightLocal(
        @QueryValue("page") page: Int,
        @QueryValue("pageLimit") pageLimit: Int
    ): List<LocalRate> {
        return airFreightRateLocalService.listAirFreightRate(page, pageLimit)
    }
}
