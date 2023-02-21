package com.cogoport.airfare.service.interfaces

import com.cogoport.airfare.models.entity.PlatformConfigConstantMappings
import com.cogoport.airfare.models.request.GlobalConstantRequest

interface GlobalConstantService {
    suspend fun getGlobalConstant(request: GlobalConstantRequest): PlatformConfigConstantMappings?
}
