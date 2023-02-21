package com.cogoport.airfare.model.request

import com.cogoport.airfare.enum.Constants
import com.cogoport.airfare.model.entity.AirFreightWeightSlabs
import io.micronaut.core.annotation.Introspected
import java.sql.Timestamp
import java.util.*

@Introspected
data class AirFreightRequest(
    var id: UUID?,
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
    val weightSlabs: List<AirFreightWeightSlabs>,
    val validityStart: Timestamp?,
    val validityEnd: Timestamp?,
    val cogoEntityId: UUID? = null

)
