package com.cogoport.airfare.common.location.service.implementation

import com.cogoport.airfare.common.location.repository.OperatorRepository
import com.cogoport.airfare.common.operator.model.entity.Operator
import com.cogoport.airfare.common.operator.model.request.OperatorRequest
import com.cogoport.airfare.common.operator.service.interfaces.OperatorService
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class OperatorServiceImpl : OperatorService {
    @Inject

    lateinit var organizationRepository: OperatorRepository

    override suspend fun getOperatorById(request: OperatorRequest): List<Operator?> {
        return organizationRepository.findByIdAndOperatorType(request.id, request.operatorType, "active")
    }
}
