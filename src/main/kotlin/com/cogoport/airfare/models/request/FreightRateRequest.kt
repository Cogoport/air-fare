package com.cogoport.airfare.models.request

import com.cogoport.airfare.constants.FreightConstants
import com.cogoport.airfare.models.entity.FreightRateWeightSlab
import io.micronaut.core.annotation.Introspected
import java.sql.Timestamp
import java.util.*

@Introspected
data class FreightRateRequest(
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
        val length: Int? = FreightConstants.length,
        val weightSlabs: List<FreightRateWeightSlab>,
        val validityStart: Timestamp?,
        val validityEnd: Timestamp?,
        val cogoEntityId: UUID? = null
)
