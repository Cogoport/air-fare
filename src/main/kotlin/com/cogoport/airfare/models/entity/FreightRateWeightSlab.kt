package com.cogoport.airfare.models.entity

import io.micronaut.core.annotation.Introspected

@Introspected

data class FreightRateWeightSlab(
    val unit: String,
    val lowerLimit: Float,
    val upperLimit: Float,
    val tariffPrice: Float,
    val currency: String
)