package com.cogoport.airfare.repository

import com.cogoport.airfare.model.entity.WarehouseRate
import io.micronaut.core.annotation.Introspected
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.r2dbc.annotation.R2dbcRepository
import io.micronaut.data.repository.kotlin.CoroutineCrudRepository
import java.util.*

@Introspected
@R2dbcRepository(dialect = Dialect.POSTGRES)
interface WarehouseRateRepository : CoroutineCrudRepository<WarehouseRate, UUID> {
    suspend fun findWarehouseRate(airportId: UUID?, tradeType: String?, commodity: String?, serviceProviderId: UUID?): WarehouseRate?
}
