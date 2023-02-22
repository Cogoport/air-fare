package com.cogoport.airfare.models.entity

import io.micronaut.core.annotation.Introspected

@Introspected
data class LocalLineItems(
    val code: String?,
    var unit: String?,
    var minPrice: Float?,
    var price: Float?,
    var currency: String?,
    var remarks: Array<String>? = null
)
