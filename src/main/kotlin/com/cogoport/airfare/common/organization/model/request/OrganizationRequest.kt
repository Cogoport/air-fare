package com.cogoport.airfare.common.location.model.request

import io.micronaut.core.annotation.Introspected
import java.util.*

@Introspected
data class OrganizationRequest(
    val id: UUID,
    val accountType: String,
    val status: String? = "active"
)
