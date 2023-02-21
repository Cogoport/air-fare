package com.cogoport.airfare.controller

import com.cogoport.airfare.model.entity.AirFreightRateLocals
import com.cogoport.airfare.model.request.AirFreightRateLocalRequest
import com.cogoport.airfare.model.response.Response
import com.cogoport.airfare.service.interfaces.AirFreightRateLocalService
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.QueryValue
import jakarta.inject.Inject
import java.util.*

@Controller("/air-freight-rate-local")
class AirFreightRateLocalController {
    @Inject
    lateinit var airFreightRateLocalService: AirFreightRateLocalService

    @Post("/create")
    suspend fun createAirFreightRateLocal(
        request: AirFreightRateLocalRequest
    ): Response<UUID?> {
        return Response<UUID?>()
            .ok(HttpStatus.CREATED.name, airFreightRateLocalService.createAirFreightRateLocal(request))
    }

    @Get("/{?request*}")
    suspend fun getAirFreightRateLocal(
        request: AirFreightRateLocalRequest
    ): Response<AirFreightRateLocals> {
        return Response<AirFreightRateLocals>().ok(HttpStatus.OK.name, airFreightRateLocalService.getAirFreightRateLocal(request))
    }

    @Get("/list")
    suspend fun listAirFreightLocal(
        @QueryValue("page") page: Int,
        @QueryValue("pageLimit") pageLimit: Int
    ): List<AirFreightRateLocals> {
        return airFreightRateLocalService.listAirFreightRate(page, pageLimit)
    }
}
