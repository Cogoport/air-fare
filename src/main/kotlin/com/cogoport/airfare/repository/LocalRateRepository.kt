package com.cogoport.airfare.repository

import com.cogoport.airfare.models.entity.LocalRate
import io.micronaut.core.annotation.Introspected
import io.micronaut.data.model.Pageable
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.r2dbc.annotation.R2dbcRepository
import io.micronaut.data.repository.kotlin.CoroutineCrudRepository
import java.util.*

@Introspected
@R2dbcRepository(dialect = Dialect.POSTGRES)
interface LocalRateRepository : CoroutineCrudRepository<LocalRate, UUID> {
    override suspend fun findById(id: UUID): LocalRate
    suspend fun findLocalRate(airlineId: UUID, airportId: UUID, commodity: String, commodityType: String, tradeType: String, serviceProviderId: UUID): LocalRate?
    suspend fun listOrderById(pageable: Pageable): List<LocalRate>
}
