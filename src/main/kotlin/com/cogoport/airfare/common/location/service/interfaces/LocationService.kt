package com.cogoport.airfare.common.location.service.interfaces

import com.cogoport.airfare.common.location.model.entity.Location
import com.cogoport.airfare.common.location.model.request.LocationRequest

interface LocationService {

    suspend fun getLocationById(request: LocationRequest): List<Location?>
}