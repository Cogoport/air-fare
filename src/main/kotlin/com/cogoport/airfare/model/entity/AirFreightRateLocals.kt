package com.cogoport.airfare.model.entity

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.core.annotation.Introspected
import io.micronaut.core.annotation.NonNull
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import java.util.*

@Introspected
@MappedEntity("air_freight_rate_locals")
open class AirFreightRateLocals(
    @field:Id @GeneratedValue @NonNull
    val id: UUID,
    @JsonProperty("airline_id")
    val airlineId: UUID?
)
