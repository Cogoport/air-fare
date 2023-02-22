package com.cogoport.airfare.common.location.service.implementation

import com.cogoport.airfare.common.location.model.entity.Location
import com.cogoport.airfare.common.location.model.request.LocationRequest
import com.cogoport.airfare.common.location.repository.LocationRepository
import com.cogoport.airfare.common.location.service.interfaces.LocationService
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class LocationServiceImpl : LocationService {
    @Inject

    lateinit var locationRepository: LocationRepository

    override suspend fun getLocationById(request: LocationRequest): List<Location?> {
        return locationRepository.findByIdAndType(request.id, request.type, "active")
    }
}
