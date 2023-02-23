package com.cogoport.airfare.model.entity

import com.cogoport.airfare.models.entity.LocalLineItem
import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.DateUpdated
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import java.sql.Timestamp
import java.time.LocalDateTime
import java.util.*

@Introspected
@MappedEntity("surcharge_rates")
data class SurchargeRate(
    @field:Id @GeneratedValue
    val id: UUID? = null,
    @JsonProperty("airline_id")
    val airlineId: UUID? = null,
    @JsonProperty("origin_airport_id")
    val originAirportId: UUID? = null,
    @JsonProperty("destination_airport_id")
    val destinationAirportId: UUID? = null,
    @JsonProperty("destination_country_id")
    val destinationCountryId: UUID? = null,
    @JsonProperty("origin_country_id")
    val originCountryId: UUID? = null,
    @JsonProperty("destination_trade_id")
    val destinationTradeId: UUID? = null,
    @JsonProperty("origin_trade_id")
    val originTradeId: UUID? = null,
    @JsonProperty("origin_continent_id")
    val originContinentId: UUID? = null,
    @JsonProperty("destination_continent_id")
    val destinationContinentId: UUID? = null,
    @JsonProperty("commodity")
    val commodity: String? = null,
    @JsonProperty("commodity_type")
    val commodityType: String? = null,
    @JsonProperty("operation_type")
    val operationType: String? = null,
    @JsonProperty("service_provider_id")
    val serviceProviderId: UUID? = null,
    @JsonProperty("line_items")
    var lineItems: List<LocalLineItem>? = null,
    @JsonProperty("is_line_items_error_messages_present")
    val isLineItemsErrorMessagesPresent: Boolean? = null,
    @JsonProperty("is_line_items_info_messages_present")
    val isLineItemsInfoMessagesPresent: Boolean? = null,
    @JsonProperty("line_items_error_messages")
    val lineItemsErrorMessages: Array<String>? = null,
    @JsonProperty("line_items_info_messages")
    val lineItemsInfoMessages: String? = null,
    @JsonProperty("source")
    val source: String? = null,
    @JsonProperty("created_at")
    @DateCreated
    var createdAt: Timestamp? = Timestamp.valueOf(LocalDateTime.now()),
    @JsonProperty("updated_at")
    @DateUpdated
    var updatedAt: Timestamp? = Timestamp.valueOf(LocalDateTime.now())
)
