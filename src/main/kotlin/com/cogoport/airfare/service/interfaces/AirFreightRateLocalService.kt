package com.cogoport.airfare.service.interfaces

import com.cogoport.airfare.model.entity.AirFreightRateLocals
import com.cogoport.airfare.model.request.AirFreightRateLocalRequest

interface AirFreightRateLocalService {
    suspend fun getAirFreightRateLocal(request: AirFreightRateLocalRequest): AirFreightRateLocals
}