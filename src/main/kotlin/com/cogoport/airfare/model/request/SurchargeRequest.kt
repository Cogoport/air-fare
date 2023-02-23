package com.cogoport.airfare.models.request

import com.fasterxml.jackson.annotation.JsonAutoDetect
import io.micronaut.core.annotation.Introspected
import java.util.*

@Introspected
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
data class SurchargeRequest(
    var id: UUID?,
    val code: String?,
    val name: String?,
    val units: Array<String>?,
    val condition: String?,
    val tags: Array<String>?,
    val sacCode: String?

)
