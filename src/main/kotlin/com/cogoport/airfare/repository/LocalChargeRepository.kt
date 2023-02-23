package com.cogoport.airfare.repository

import com.cogoport.airfare.models.entity.LocalCharge
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.r2dbc.annotation.R2dbcRepository
import io.micronaut.data.repository.kotlin.CoroutineCrudRepository
import java.util.*

@R2dbcRepository(dialect = Dialect.POSTGRES)
interface LocalChargeRepository : CoroutineCrudRepository<LocalCharge, UUID> {
    suspend fun findByCode(code: String): LocalCharge
}
