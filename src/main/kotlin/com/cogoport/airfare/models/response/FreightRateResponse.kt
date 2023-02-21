package com.cogoport.airfare.models.response

import com.cogoport.airfare.models.entity.FreightRate
import io.micronaut.core.annotation.Introspected

@Introspected
data class FreightRateResponse(
        val list: List<FreightRate?>? = null,
        val totalRecords: Int,
        val totalPage: Int,
        val page: Int
)
