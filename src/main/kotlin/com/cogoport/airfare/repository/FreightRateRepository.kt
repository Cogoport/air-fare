package com.cogoport.airfare.repository

import com.cogoport.airfare.model.entity.FreightRate
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.r2dbc.annotation.R2dbcRepository
import io.micronaut.data.repository.kotlin.CoroutineCrudRepository
import java.util.*

@R2dbcRepository(dialect = Dialect.POSTGRES)
interface FreightRateRepository : CoroutineCrudRepository<FreightRate, UUID> {

    override suspend fun findById(id: UUID): FreightRate?
    suspend fun findFreightRate(originAirportId: UUID, destinationAirportId: UUID, commodity: String, commodityType: String, commoditySubType: String?, airlineId: UUID, operationType: String, serviceProviderId: UUID, shipmentType: String, stackingType: String, priceType: String, cogoEntityId: UUID?): FreightRate?
}
