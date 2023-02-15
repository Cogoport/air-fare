package com.cogoport.airfare.model.request

import io.micronaut.core.annotation.Introspected
import java.util.*

@Introspected
data class AirFreightWarehouseRequest(
    var airportId: UUID? = null,
    var tradeType: String? = null,
    var commodity: String? = null,
    var serviceProviderId: UUID? = null
)
