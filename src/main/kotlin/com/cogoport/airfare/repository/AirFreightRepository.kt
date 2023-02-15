package com.cogoport.airfare.repository

import com.cogoport.airfare.model.entity.AirFreightRates
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.r2dbc.annotation.R2dbcRepository
import io.micronaut.data.repository.kotlin.CoroutineCrudRepository
import java.util.*

@R2dbcRepository(dialect = Dialect.POSTGRES)
interface AirFreightRepository : CoroutineCrudRepository<AirFreightRates, UUID> {

    override suspend fun findById(id: UUID): AirFreightRates?
}
