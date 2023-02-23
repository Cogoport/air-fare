package com.cogoport.airfare.model.request

import io.micronaut.core.annotation.Introspected

@Introspected
data class GlobalConstantRequest(
    val keyName: String? = "SUPERADMIN_ROLE_IDS",
    val service: String? = "Global"
)
