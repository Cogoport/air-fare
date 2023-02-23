package com.cogoport.airfare.common.audit.service.interfaces

import com.cogoport.airfare.common.audit.model.request.AuditRequest

interface AuditService {
    suspend fun createAudit(request: AuditRequest)
}