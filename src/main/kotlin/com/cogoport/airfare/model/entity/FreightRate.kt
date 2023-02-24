package com.cogoport.airfare.model.entity

import com.cogoport.airfare.constants.FreightConstants
import com.cogoport.airfare.models.entity.Surcharge
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
    val id: UUID? = null,
    val originAirportId: UUID? = null,
    val destinationAirportId: UUID? = null,
    var originLocalId: UUID? = null,
    var destinationLocalId: UUID? = null,
    val surchargeId: UUID? = null,
    val originCountryId: UUID? = null,
    val originTradeId: UUID? = null,
    val originContinentId: UUID? = null,
    val destinationCountryId: UUID? = null,
    val destinationTradeId: UUID? = null,
    val destinationContinentId: UUID? = null,
    val commodity: String? = null,
    val commodityType: String? = null,
    val commoditySubType: String? = null,
    val airlineId: UUID? = null,
    val operationType: String? = null,
    val priceType: String? = null,
    val minPrice: Double? = 0.0,
    val serviceProviderId: UUID? = null,
    val bulkOperationId: UUID? = null,
    val rateSheetId: UUID? = null,
    val performedById: UUID? = null,
    val procuredById: UUID? = null,
    val sourcedById: UUID? = null,
    val cogoEntityId: UUID? = null,
    var lastRateAvailableDate: ZonedDateTime? = null,
    var rateNotAvailableEntry: Boolean? = false,
    var length: Int? = FreightConstants.length,
    var breadth: Int? = FreightConstants.breadth,
    var height: Int? = FreightConstants.height,
    var maximumWeight: Int? = FreightConstants.maximumWeight,
    val shipmentType: String? = null,
    val stackingType: String? = null,
    var originLocal: LocalRate? = null,
    var destinationLocal: LocalRate? = null,
    var surcharge: Surcharge? = null

)
