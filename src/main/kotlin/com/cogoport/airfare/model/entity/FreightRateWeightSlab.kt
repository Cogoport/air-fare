package com.cogoport.airfare.model.entity

import io.micronaut.core.annotation.Introspected

@Introspected

data class FreightRateWeightSlab(
    val unit: String,
    val lower_limit: Float,
    val upper_limit: Float,
    val tariff_price: Float,
    val currency: String

)