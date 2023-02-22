package com.cogoport.airfare.model.entity

import com.cogoport.airfare.constants.FreightConstants
import io.micronaut.core.annotation.Introspected
import io.micronaut.core.annotation.NonNull
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import java.time.ZonedDateTime
import java.util.*

@Introspected
@MappedEntity("air_freight_rates")
data class FreightRate(
    @field:Id @GeneratedValue @NonNull
    val id: UUID?,
    val originAirportId: UUID,
    val destinationAirportId: UUID,
    val originLocalId: UUID?,
    val destinationLocalId: UUID?,
    val surchargeId: UUID?,
    val originCountryId: UUID?,
    val originTradeId: UUID?,
    val originContinentId: UUID?,
    val destinationCountryId: UUID?,
    val destinationTradeId: UUID?,
    val destinationContinentId: UUID?,
    val commodity: String,
    val commodityType: String,
    val commoditySubType: String? = null,
    val airlineId: UUID,
    val operationType: String,
    val priceType: String,
    val minPrice: Double? = 0.0,
    val serviceProviderId: UUID,
    val bulkOperationId: UUID? = null,
    val rateSheetId: UUID? = null,
    val performedById: UUID,
    val procuredById: UUID,
    val sourcedById: UUID,
    val cogoEntityId: UUID? = null,
    var lastRateAvailableDate: ZonedDateTime?,
    var rateNotAvailableEntry: Boolean? = false,
    var length: Int? = FreightConstants.length,
    var breadth: Int? = FreightConstants.breadth,
    var height: Int? = FreightConstants.height,
    var maximumWeight: Int? = FreightConstants.maximumWeight,
    val shipmentType: String?,
    val stackingType: String?,
    var originLocal: LocalRate? = null,
    var destinationLocal: LocalRate? = null,
    var surcharge: Surcharge? = null

)
