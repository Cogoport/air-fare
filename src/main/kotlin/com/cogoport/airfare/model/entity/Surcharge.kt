package com.cogoport.airfare.model.entity

import io.micronaut.core.annotation.Introspected
import io.micronaut.core.annotation.NonNull
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import java.util.*

@Introspected
data class Surcharge(
    @field: Id @GeneratedValue @NonNull
    val id: UUID
)