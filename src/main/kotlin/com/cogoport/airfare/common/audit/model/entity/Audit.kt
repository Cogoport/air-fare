package com.cogoport.airfare.common.audit.model.entity

import io.micronaut.core.annotation.Introspected
import io.micronaut.core.annotation.NonNull
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import java.util.*

@Introspected
@MappedEntity("air_freight_audits")
data class Audit(
    @field: Id @GeneratedValue @NonNull
    val id: UUID?,
    val rateSheetId: UUID?,
    val bulkOperationId: UUID?,
    val objectId: UUID,
    val objectType: String,
    val actionName: String,
    val data: Any?,
    val procuredById: UUID?,
    val sourcedById: UUID?,
    val validityId: UUID?,
    val performedById: UUID


)
