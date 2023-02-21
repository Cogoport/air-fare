package com.cogoport.airfare.service.interfaces

import com.cogoport.airfare.model.entity.AirFreightRateLocals
import com.cogoport.airfare.model.request.AirFreightRateLocalRequest
import com.cogoport.airfare.model.response.Response
import java.util.*

interface AirFreightRateLocalService {
    suspend fun createAirFreightRateLocal(request: AirFreightRateLocalRequest): UUID?
    suspend fun getAirFreightRateLocal(request: AirFreightRateLocalRequest): AirFreightRateLocals
    suspend fun listAirFreightRate(page: Int, pageLimit: Int): List<AirFreightRateLocals>
}
