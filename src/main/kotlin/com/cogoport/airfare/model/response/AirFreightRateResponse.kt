package com.cogoport.airfare.model.response

import com.cogoport.airfare.model.entity.AirFreightRates
import io.micronaut.core.annotation.Introspected

@Introspected
data class AirFreightRateResponse(
    val list: List<AirFreightRates?>? = null,
    val totalRecords: Int,
    val totalPage: Int,
    val page: Int
)
