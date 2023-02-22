package com.cogoport.airfare.common.location.service.interfaces

import com.cogoport.airfare.common.location.model.entity.Organization
import com.cogoport.airfare.common.location.model.request.OrganizationRequest

interface OrganizationService {

    suspend fun getOrganizationById(request: OrganizationRequest): List<Organization?>
}