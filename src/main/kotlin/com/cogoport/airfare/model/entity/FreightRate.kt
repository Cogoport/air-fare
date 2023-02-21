package com.cogoport.airfare.model.entity

import io.micronaut.core.annotation.Introspected
import io.micronaut.core.annotation.NonNull
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import java.util.*

@Introspected
@MappedEntity("air_freight_rates")
open class FreightRate(
    @field:Id @GeneratedValue @NonNull
    val id: UUID?,
    val originAirportId: UUID?,
    val destinationAirportId: UUID?,
    val commodity: String?,
    val commodityType: String?,
    val commoditySubType: String?,
    val airlineId: UUID?,
    val operationType: String?,
    val priceType: String?,
    val minPrice: Double?,
    val serviceProviderId: UUID?,
    val densityCategory: String?,
    val densityRatio: String?,
    val bulkOperationId: UUID?,
    val rateSheetId: UUID?,
    val performedById: UUID?,
    val procuredById: UUID?,
    val sourcedById: UUID?,
    val length: Int?,
    val breadth: Int?,
    val height: Int?,
    val maximumWeight: Int?,
    val weightSlabs: List<FreightRateWeightSlab>,
    val shipmentType: String?,
    val stackingType: String?,
    val validityId: UUID?,
    val cogoEntityId: UUID?
)
