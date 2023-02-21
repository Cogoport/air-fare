package com.cogoport.airfare.models.response

import com.cogoport.airfare.models.entity.LocalRate

data class LocalRateResponse(
    var list: List<LocalRate?>
)
