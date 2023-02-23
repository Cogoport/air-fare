package com.cogoport.airfare.models.entity

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
@MappedEntity("local_charges")
data class LocalCharge(
    @field:Id @GeneratedValue
    val id: UUID? = null,
    @JsonProperty("code")
    val code: String? = null,
    @JsonProperty("name")
    val name: String? = null,
    @JsonProperty("units")
    val units: Array<String>? = null,
    @JsonProperty("tradeTypes")
    val tradeTypes: Array<String>? = null,
    @JsonProperty("condition")
    val condition: String? = null,
    @JsonProperty("tags")
    val tags: Array<String>? = null,
    @JsonProperty("sacCode")
    val sacCode: String? = null,
    @JsonProperty("created_at")
    @DateCreated
    var createdAt: Timestamp? = Timestamp.valueOf(LocalDateTime.now()),
    @JsonProperty("updated_at")
    @DateUpdated
    var updatedAt: Timestamp? = Timestamp.valueOf(LocalDateTime.now())
)
