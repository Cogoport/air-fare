package com.cogoport.airfare.controller

import com.cogoport.airfare.models.entity.PlatformConfigConstantMappings
import com.cogoport.airfare.models.request.GlobalConstantRequest
import com.cogoport.airfare.models.response.Response
import com.cogoport.airfare.service.interfaces.GlobalConstantService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import jakarta.inject.Inject

@Controller("global-constants")
class ConstantController {
    @Inject
    lateinit var globalConstantService: GlobalConstantService

    @Get("/{?request*}")
    suspend fun getGlobalConstant(request: GlobalConstantRequest): PlatformConfigConstantMappings? {
        return Response<PlatformConfigConstantMappings?>().ok(globalConstantService.getGlobalConstant(request))
    }
}
