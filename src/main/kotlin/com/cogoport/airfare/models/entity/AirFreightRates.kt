package com.cogoport.airfare.models.entity

import io.micronaut.core.annotation.Introspected
import io.micronaut.core.annotation.NonNull
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import java.util.*

@Introspected
@MappedEntity("air_freight_rates")
class AirFreightRates(
    @field:Id @GeneratedValue @NonNull val id: UUID?,
    val originAirportId: UUID?,
    val destinationAirportId: UUID?,
    val commodity: String?,
    val airlineId: UUID?,
    val serviceProviderId: UUID?,
    val shipmentType: String?,
    val stackingType: String?
)
