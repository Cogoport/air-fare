package com.cogoport.airfare.models.entity

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import java.util.*

@Introspected
@MappedEntity("surcharges")
data class Surcharge(
    @field:Id @GeneratedValue
    val id: UUID?,
    @JsonProperty("code")
    val code: String,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("units")
    val units: Array<String>,
    @JsonProperty("condition")
    val condition: Boolean,
    @JsonProperty("tags")
    val tags: Array<String>,
    @JsonProperty("sacCode")
    val sacCode: String
)
