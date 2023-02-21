package com.cogoport.airfare.model.entity

import com.cogoport.airfare.enum.Constants
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
    val commoditySubType: String? = null,
    val airlineId: UUID?,
    val operationType: String?,
    val priceType: String?,
    val minPrice: Double = 0.0,
    val serviceProviderId: UUID?,
    val densityCategory: String? = "general",
    val densityRatio: String? = null,
    val bulkOperationId: UUID? = null,
    val rateSheetId: UUID? = null,
    val performedById: UUID?,
    val procuredById: UUID?,
    val sourcedById: UUID?,
    val length: Int? = Constants.length,
    val breadth: Int? = Constants.breadth,
    val height: Int? = Constants.height,
    val maximum_weight: Int? = Constants.maximum_weight,
    val weightSlabs: List<FreightRateWeightSlab>,
    val shipmentType: String?,
    val stackingType: String?

)
