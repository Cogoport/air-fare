package com.cogoport.airfare.common.location.controller

import com.cogoport.airfare.common.location.model.entity.Location
import com.cogoport.airfare.common.location.model.request.LocationRequest
import com.cogoport.airfare.common.location.service.interfaces.LocationService
import com.cogoport.airfare.model.response.Response
import io.micronaut.http.annotation.Get
import jakarta.inject.Inject

class LocationController {
    @Inject
    lateinit var locationService: LocationService

    @Get("/{?request*}")
    suspend fun getLocation(request: LocationRequest): List<Location?> {
        return Response<List<Location?>>().ok(locationService.getLocationById(request))
    }
}
