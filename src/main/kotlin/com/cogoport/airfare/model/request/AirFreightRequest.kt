package com.cogoport.airfare.model.request

import io.micronaut.core.annotation.Introspected
import java.util.*

@Introspected
data class AirFreightRequest(
    var id: UUID,
    val tariffPrice: Float,
    val currency: String
)

