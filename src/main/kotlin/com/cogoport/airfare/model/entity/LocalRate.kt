package com.cogoport.airfare.model.entity

import com.cogoport.airfare.models.entity.LocalLineItem
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
    val id: UUID? = null,
    @JsonProperty("airline_id")
    val airlineId: UUID? = null,
    @JsonProperty("airport_id")
    val airportId: UUID? = null,
    @JsonProperty("country_id")
    val countryId: UUID? = null,
    @JsonProperty("trade_id")
    val tradeId: UUID? = null,
    @JsonProperty("continent_id")
    val continentId: UUID? = null,
    @JsonProperty("commodity")
    val commodity: String? = null,
    @JsonProperty("commodity_type")
    val commodityType: String? = null,
    @JsonProperty("trade_type")
    val tradeType: String? = null,
    @JsonProperty("service_provider_id")
    val serviceProviderId: UUID? = null,
    @JsonProperty("line_items")
    var lineItems: List<LocalLineItem>? = null,
    @JsonProperty("is_line_items_error_messages_present")
    val isLineItemsErrorMessagesPresent: Boolean? = null,
    @JsonProperty("is_line_items_info_messages_present")
    val isLineItemsInfoMessagesPresent: Boolean? = null,
    @JsonProperty("line_items_error_messages")
    val lineItemsErrorMessages: String? = null,
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
