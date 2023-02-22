package com.cogoport.airfare.model.request

import io.micronaut.core.annotation.Introspected
import java.time.ZonedDateTime

@Introspected
data class FreightRateValidityRequest(
    val validityStart: ZonedDateTime,
    val validityEnd: ZonedDateTime
)