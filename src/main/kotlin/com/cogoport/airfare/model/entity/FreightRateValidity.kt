package com.cogoport.airfare.model.entity

import io.micronaut.core.annotation.Introspected
import io.micronaut.core.annotation.NonNull
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import java.sql.Timestamp
import java.util.*

@Introspected
@MappedEntity("air_freight_validity")
data class FreightRateValidity(
    @field: Id @GeneratedValue @NonNull
    val id: UUID,
    val status: Boolean,
    val validityStart: Timestamp,
    val validityEnd: Timestamp,
    val minPrice: Float,
    val currency: String,
    val likeCount: Int,
    val dislikesCount: Int,
    val weightSlabs: List<FreightRateWeightSlab>,
    val densityCategory: String? = "general",
    val minDensityWeight: Float?,
    val maxDensityWeight: Float
)
