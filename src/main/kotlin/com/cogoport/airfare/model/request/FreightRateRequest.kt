package com.cogoport.airfare.model.request

import com.cogoport.airfare.constants.FreightConstants
import com.cogoport.airfare.model.entity.FreightRateWeightSlab
import io.micronaut.core.annotation.Introspected
import java.time.ZonedDateTime
import java.util.*

@Introspected
data class FreightRateRequest(
    var id: UUID? = null,
    var originAirportId: UUID? = null,
    var destinationAirportId: UUID? = null,
    var commodity: String? = null,
    var commodityType: String? = null,
    var commoditySubType: String? = null,
    var airlineId: UUID? = null,
    var operationType: String? = null,
    var priceType: String? = null,
    var minPrice: Double = 0.0,
    var shipmentType: String? = null,
    var stackingType: String? = null,
    var serviceProviderId: UUID? = null,
    var densityCategory: String? = "general",
    var densityRatio: String? = null,
    var bulkOperationId: UUID? = null,
    var rateSheetId: UUID? = null,
    var performedById: UUID? = null,
    var procuredById: UUID? = null,
    var sourcedById: UUID? = null,
    var currency: String? = null,
    var maximumWeight: Int? = FreightConstants.maximumWeight,
    var length: Int? = FreightConstants.length,
    var breadth: Int? = FreightConstants.breadth,
    var height: Int? = FreightConstants.height,
    var weightSlabs: List<FreightRateWeightSlab>? = null,
    var validityStart: ZonedDateTime? = null,
    var validityEnd: ZonedDateTime? = null,
    var cogoEntityId: UUID? = null
)
