package com.cogoport.airfare.model.request
import io.micronaut.core.annotation.Introspected
import java.util.UUID

@Introspected
data class AirFreightRateLocalRequest(
    var id: UUID
)
