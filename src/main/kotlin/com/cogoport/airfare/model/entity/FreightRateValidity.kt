package com.cogoport.airfare.model.entity

import io.micronaut.core.annotation.Introspected
import io.micronaut.core.annotation.NonNull
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import java.time.ZonedDateTime
import java.util.*

@Introspected
@MappedEntity("air_freight_validity")
data class FreightRateValidity(
    @field: Id @GeneratedValue @NonNull
    val id: UUID,
    val rateId: UUID,
    var status: Boolean,
    var validityStart: ZonedDateTime,
    var validityEnd: ZonedDateTime,
    val minPrice: Double,
    val currency: String,
    val likeCount: Int,
    val dislikesCount: Int,
    val weightSlabs: List<FreightRateWeightSlab>,
    var densityCategory: String? = "general",
    val minDensityWeight: Double,
    var maxDensityWeight: Double
)
