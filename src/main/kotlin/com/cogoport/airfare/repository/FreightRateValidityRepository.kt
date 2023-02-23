package com.cogoport.airfare.repository

import com.cogoport.airfare.model.entity.FreightRateValidity
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.r2dbc.annotation.R2dbcRepository
import io.micronaut.data.repository.kotlin.CoroutineCrudRepository
import java.util.*

@R2dbcRepository(dialect = Dialect.POSTGRES)
interface FreightRateValidityRepository : CoroutineCrudRepository<FreightRateValidity, UUID>{
    suspend fun findByRateId(rateId: UUID): List<FreightRateValidity>
    suspend fun findByRateIdAndStatus(rateId: UUID, status: Boolean): List<FreightRateValidity>
}
