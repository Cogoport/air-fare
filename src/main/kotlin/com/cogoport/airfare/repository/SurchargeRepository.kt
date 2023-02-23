package com.cogoport.airfare.repository

import com.cogoport.airfare.models.entity.Surcharge
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.r2dbc.annotation.R2dbcRepository
import io.micronaut.data.repository.kotlin.CoroutineCrudRepository
import java.util.*

@R2dbcRepository(dialect = Dialect.POSTGRES)
interface SurchargeRepository : CoroutineCrudRepository<Surcharge, UUID> {
    suspend fun findByCode(code: String): Surcharge
}
