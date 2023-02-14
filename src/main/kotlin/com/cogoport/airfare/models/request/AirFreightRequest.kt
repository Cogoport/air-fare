package com.cogoport.airfare.models.request

import io.micronaut.core.annotation.Introspected
import java.util.UUID

@Introspected
data class AirFreightRequest(
    var id: UUID
)
