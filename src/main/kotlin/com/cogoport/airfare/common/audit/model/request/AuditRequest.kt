package com.cogoport.airfare.common.audit.model.request

import io.micronaut.core.annotation.Introspected
import java.util.*

@Introspected
data class AuditRequest(
    val objectType: String,
    val objectId: UUID,
    val actionName: String,
    val bulkOperationId: UUID?,
    val rateSheetId: UUID?,
    val data: Any?,
    var validityId: UUID? = null,
    val procuredById: UUID?,
    val sourcedById: UUID?,
    val performedById: UUID

)
