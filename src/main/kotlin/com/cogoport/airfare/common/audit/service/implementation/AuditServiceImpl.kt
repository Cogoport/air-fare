package com.cogoport.airfare.common.audit.service.implementation

import com.cogoport.airfare.common.audit.model.entity.Audit
import com.cogoport.airfare.common.audit.model.request.AuditRequest
import com.cogoport.airfare.common.audit.repository.AuditRepository
import com.cogoport.airfare.common.audit.service.interfaces.AuditService
import jakarta.inject.Inject

class AuditServiceImpl : AuditService {
    @Inject
    lateinit var auditRepository: AuditRepository
    override suspend fun createAudit(request: AuditRequest) {
        auditRepository.save(
            Audit(
                id = null,
                objectType = request.objectType,
                objectId = request.objectId,
                bulkOperationId = request.bulkOperationId,
                actionName = request.actionName,
                data = request.data,
                validityId = request.validityId,
                procuredById = request.procuredById,
                sourcedById = request.sourcedById,
                performedById = request.performedById,
                rateSheetId = request.rateSheetId
            )
        )
    }
}
