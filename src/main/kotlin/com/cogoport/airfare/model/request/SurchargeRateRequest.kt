package com.cogoport.airfare.model.request

import com.cogoport.airfare.models.entity.LocalLineItem
import io.micronaut.core.annotation.Introspected
import java.util.*

@Introspected
data class SurchargeRateRequest(
    var id: UUID?,
    var airlineId: UUID?,
    val originAirportId: UUID,
    val destinationAirportId: UUID,
    var commodity: String?,
    var commodityType: String?,
    var operationType: String?,
    var serviceProviderId: UUID?,
    val page: Int? = 1,
    val pageLimit: Int? = 10,
    var source: String? = "manual",
    var performedById: UUID?,
    var sourcedById: UUID?,
    var procuredById: UUID?,
    var bulkOperationId: UUID? = null,
    var rateSheetId: UUID? = null,
    var lineItems: List<LocalLineItem>?
)
