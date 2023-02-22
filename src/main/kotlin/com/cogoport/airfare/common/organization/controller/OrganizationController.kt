package com.cogoport.airfare.common.location.controller

import com.cogoport.airfare.common.location.model.entity.Organization
import com.cogoport.airfare.common.location.model.request.OrganizationRequest
import com.cogoport.airfare.common.location.service.interfaces.OrganizationService
import com.cogoport.airfare.model.response.Response
import io.micronaut.http.annotation.Get
import jakarta.inject.Inject

class OrganizationController {
    @Inject
    lateinit var organizationService: OrganizationService

    @Get("/{?request*}")
    suspend fun getOrganization(request: OrganizationRequest): List<Organization?> {
        return Response<List<Organization?>>().ok(organizationService.getOrganizationById(request))
    }
}
