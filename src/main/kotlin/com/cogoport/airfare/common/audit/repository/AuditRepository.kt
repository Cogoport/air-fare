package com.cogoport.airfare.common.audit.repository

import com.cogoport.airfare.common.audit.model.entity.Audit
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.r2dbc.annotation.R2dbcRepository
import io.micronaut.data.repository.kotlin.CoroutineCrudRepository
import java.util.*

@R2dbcRepository(dialect = Dialect.POSTGRES)
interface AuditRepository : CoroutineCrudRepository<Audit, UUID>
