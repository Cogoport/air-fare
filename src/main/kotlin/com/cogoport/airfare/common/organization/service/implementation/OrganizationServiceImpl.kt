package com.cogoport.airfare.common.location.service.implementation

import com.cogoport.airfare.common.location.model.entity.Organization
import com.cogoport.airfare.common.location.model.request.OrganizationRequest
import com.cogoport.airfare.common.location.repository.OrganizationRepository
import com.cogoport.airfare.common.location.service.interfaces.OrganizationService
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class OrganizationServiceImpl : OrganizationService {
    @Inject

    lateinit var organizationRepository: OrganizationRepository

    override suspend fun getOrganizationById(request: OrganizationRequest): List<Organization?> {
        return organizationRepository.findByIdAndAccountType(request.id, request.accountType, "active")
    }
}
