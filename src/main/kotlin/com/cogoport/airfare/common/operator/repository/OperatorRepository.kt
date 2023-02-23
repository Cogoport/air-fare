package com.cogoport.airfare.common.location.repository

import com.cogoport.airfare.common.operator.model.entity.Operator
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.r2dbc.annotation.R2dbcRepository
import io.micronaut.data.repository.kotlin.CoroutineCrudRepository
import java.util.*

@R2dbcRepository(dialect = Dialect.POSTGRES)
interface OperatorRepository : CoroutineCrudRepository<Operator, UUID> {

    suspend fun findByIdAndOperatorType(id: UUID, operatorType: String, status: String): List<Operator?>
}
