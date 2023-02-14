package com.cogoport.airfare.models.response

import com.cogoport.airfare.models.entity.AirFreightRates
import io.micronaut.core.annotation.Introspected

@Introspected
data class AirFreightRateResponse(
    val list: List<AirFreightRates?>? = null,
    val totalRecords: Int,
    val totalPage: Int,
    val page: Int
)
