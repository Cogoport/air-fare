package com.cogoport.airfare.model.request
import io.micronaut.core.annotation.Introspected
import java.util.*

@Introspected
data class LocalRateRequest(
    var id: UUID?,
    var airlineId: UUID?,
    var airportId: UUID?,
    var commodity: String?,
    var commodityType: String?,
    var tradeType: String?,
    var serviceProviderId: UUID?,
    val page: Int? = 1,
    val pageLimit: Int? = 10,
    var source: String? = "manual"
)
