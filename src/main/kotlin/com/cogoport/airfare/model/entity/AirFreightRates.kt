package com.cogoport.airfare.model.entity

import io.micronaut.core.annotation.Introspected
import io.micronaut.core.annotation.NonNull
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import java.util.*

@Introspected
@MappedEntity("air_freight_rates")
open class AirFreightRates(
    @field:Id @GeneratedValue @NonNull
    val id: UUID?,
    val originAirportId: UUID?,
    val destinationAirportId: UUID?,
    val commodity: String?,
    val commodityType: String?,
    val commoditySubType: String?,
    val airlineId: UUID?,
    val operationType: String?,
    val currency: Currency?,
    val priceType: String?,
    val minPrice: Double = 0.0,
    val serviceProviderId: UUID?,
    val densityCategory: String? = "general",
    val densityRatio: String? = null,
    val bulkOperationId: UUID?,
    val rateSheetId: UUID?,
    val performedById: UUID?,
    val procuredById: UUID?,
    val sourcedById: UUID?,
    val length: Int? = 300,
    val shipmentType: String?,
    val stackingType: String?
)
