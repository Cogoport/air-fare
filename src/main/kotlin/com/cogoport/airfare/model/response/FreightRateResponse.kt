package com.cogoport.airfare.model.response

import com.cogoport.airfare.model.entity.FreightRate
import io.micronaut.core.annotation.Introspected

@Introspected
data class FreightRateResponse(
        val list: List<FreightRate?>? = null,
        val totalRecords: Int,
        val totalPage: Int,
        val page: Int
)
