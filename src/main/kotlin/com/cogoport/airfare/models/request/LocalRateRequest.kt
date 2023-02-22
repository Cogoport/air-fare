package com.cogoport.airfare.models.request
import com.cogoport.airfare.models.entity.LocalLineItems
import com.fasterxml.jackson.annotation.JsonAutoDetect
import io.micronaut.core.annotation.Introspected
import java.util.*

@Introspected
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
data class LocalRateRequest(
    var id: UUID,
    var airlineId: UUID,
    var airportId: UUID,
    var commodity: String,
    var commodityType: String,
    var tradeType: String,
    var serviceProviderId: UUID,
    var performedById: UUID?,
    var procuredById: UUID?,
    var bulkOperationId: UUID? = null,
    var rateSheetId: UUID? = null,
    var source: String? = "manual",
    var lineItems: List<LocalLineItems>?
)
