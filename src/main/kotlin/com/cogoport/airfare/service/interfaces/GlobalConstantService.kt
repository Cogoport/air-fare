package com.cogoport.airfare.service.interfaces

import com.cogoport.airfare.model.entity.PlatformConfigConstantMappings
import com.cogoport.airfare.model.request.GlobalConstantRequest

interface GlobalConstantService {
    suspend fun getGlobalConstant(request: GlobalConstantRequest): PlatformConfigConstantMappings?
}
