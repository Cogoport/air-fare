package com.cogoport.airfare.common.location.model.request

import io.micronaut.core.annotation.Introspected
import java.util.*

@Introspected
data class LocationRequest(
    val id: UUID,
    val type: String,
    val status: String? = "active"
)
