package com.cogoport.airfare.model.entity

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.*
import java.sql.Timestamp
import java.time.LocalDateTime
import java.util.*

@Introspected
@MappedEntity("local_rates")
open class LocalRate(
    @field:Id @GeneratedValue
    val id: UUID,
    @JsonProperty("airline_id")
    val airlineId: UUID,
    @JsonProperty("airport_id")
    val airportId: UUID,
    @JsonProperty("country_id")
    val countryId: UUID,
    @JsonProperty("trade_id")
    val tradeId: UUID,
    @JsonProperty("continent_id")
    val continentId: UUID,
    @JsonProperty("commodity")
    val commodity: String,
    @JsonProperty("commodity_type")
    val commodityType: String,
    @JsonProperty("trade_type")
    val tradeType: String,
    @JsonProperty("service_provider_id")
    val serviceProviderId: UUID,
    @JsonProperty("line_items")
    val lineItems: List<LocalLineItems>?,
    @JsonProperty("is_line_items_error_messages_present")
    val isLineItemsErrorMessagesPresent: Boolean,
    @JsonProperty("is_line_items_info_messages_present")
    val isLineItemsInfoMessagesPresent: Boolean,
    @JsonProperty("line_items_error_messages")
    val lineItemsErrorMessages: String?,
    @JsonProperty("line_items_info_messages")
    val lineItemsInfoMessages: String?,
    @JsonProperty("source")
    val source: String?,
    @JsonProperty("created_at")
    @DateCreated
    var createdAt: Timestamp = Timestamp.valueOf(LocalDateTime.now()),
    @JsonProperty("updated_at")
    @DateUpdated
    var updatedAt: Timestamp = Timestamp.valueOf(LocalDateTime.now())
)
