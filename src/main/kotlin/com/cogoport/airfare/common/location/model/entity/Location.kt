package com.cogoport.airfare.common.location.model.entity

import io.micronaut.core.annotation.Introspected
import io.micronaut.core.annotation.NonNull
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import java.util.*

@Introspected
@MappedEntity("locations")
data class Location(
    @field:Id @GeneratedValue @NonNull
    val id: UUID,
    val type: String,
    val status: Boolean,
    val countryCode: String
)
