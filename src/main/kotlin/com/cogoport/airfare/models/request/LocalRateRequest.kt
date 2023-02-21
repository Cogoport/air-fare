package com.cogoport.airfare.models.request
import com.fasterxml.jackson.annotation.JsonAutoDetect
import io.micronaut.core.annotation.Introspected
import io.micronaut.json.tree.JsonArray
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
    var performedById: UUID,
    var procuredById: UUID,
    var bulkOperationId: UUID? = null,
    var rateSheetId: UUID? = null,
    var lineItems: JsonArray,
    var unit: String,
    var minPrice: Float,
    var price: Float,
    var currency: String,
    var remarks: Array<String>? = null,
    var lowerLimit: Float,
    var upperLimit: Float,
    var source: String? = "manual"
)
