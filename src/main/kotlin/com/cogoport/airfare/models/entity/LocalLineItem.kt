package com.cogoport.airfare.models.entity

import io.micronaut.core.annotation.Introspected

@Introspected
data class LocalLineItem(
    val code: String?,
    val unit: String?,
    val minPrice: Float?,
    val price: Float?,
    val currency: String?,
    val remarks: Array<String>? = null
)
