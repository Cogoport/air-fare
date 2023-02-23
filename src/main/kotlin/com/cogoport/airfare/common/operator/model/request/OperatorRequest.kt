package com.cogoport.airfare.common.operator.model.request

import io.micronaut.core.annotation.Introspected
import java.util.*

@Introspected
data class OperatorRequest(
    val id: UUID,
    val operatorType: String,
    val status: String? = "active"
)
