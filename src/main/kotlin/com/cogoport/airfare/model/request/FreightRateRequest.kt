package com.cogoport.airfare.model.request

import com.cogoport.airfare.constants.FreightConstants
import com.cogoport.airfare.model.entity.FreightRateWeightSlab
import io.micronaut.core.annotation.Introspected
import java.time.ZonedDateTime
import java.util.*

@Introspected
data class FreightRateRequest(
    var id: UUID,
    val originAirportId: UUID,
    val destinationAirportId: UUID,
    val commodity: String,
    val commodityType: String,
    var commoditySubType: String? = null,
    val airlineId: UUID,
    val operationType: String,
    val priceType: String,
    var minPrice: Double = 0.0,
    val shipmentType: String,
    val stackingType: String,
    val serviceProviderId: UUID,
    val densityCategory: String? = "general",
    var densityRatio: String? = null,
    val bulkOperationId: UUID? = null,
    val rateSheetId: UUID? = null,
    val performedById: UUID,
    val procuredById: UUID,
    val sourcedById: UUID,
    val currency: String,
    val maximumWeight: Int? = FreightConstants.maximumWeight,
    val length: Int? = FreightConstants.length,
    val breadth: Int? = FreightConstants.breadth,
    val height: Int? = FreightConstants.height,
    var weightSlabs: List<FreightRateWeightSlab>,
    var validityStart: ZonedDateTime,
    var validityEnd: ZonedDateTime,
    val cogoEntityId: UUID? = null
)
