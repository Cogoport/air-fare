package com.cogoport.airfare.models.request

import com.fasterxml.jackson.annotation.JsonAutoDetect
import io.micronaut.core.annotation.Introspected
import java.util.*

@Introspected
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
data class LocalChargeRequest(
    var id: UUID?,
    val code: String?,
    val name: String?,
    val units: Array<String>?,
    val tradeTypes: Array<String>?,
    val condition: String?,
    val tags: Array<String>?,
    val sacCode: String?,
    val page: Int? = 1,
    val pageLimit: Int? = 10

)
