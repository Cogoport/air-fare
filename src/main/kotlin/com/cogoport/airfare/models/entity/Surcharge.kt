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
    val id: UUID? = null,
    @JsonProperty("code")
    val code: String? = null,
    @JsonProperty("name")
    val name: String? = null,
    @JsonProperty("units")
    val units: Array<String>? = null,
    @JsonProperty("condition")
    val condition: String? = null,
    @JsonProperty("tags")
    val tags: Array<String>? = null,
    @JsonProperty("sacCode")
    val sacCode: String? = null
)
