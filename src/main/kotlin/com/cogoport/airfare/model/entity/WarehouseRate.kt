package com.cogoport.airfare.model.entity

import io.micronaut.core.annotation.Introspected
import io.micronaut.core.annotation.NonNull
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import java.util.*

@Introspected
@MappedEntity("air_freight_warehouse_rates")
open class WarehouseRate(
    @field:Id @GeneratedValue @NonNull
    val airportId: UUID?,
    val tradeType: String?,
    val commodity: String?,
    val serviceProviderId: UUID?

)
