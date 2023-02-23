package com.cogoport.airfare.common.location.repository

import com.cogoport.airfare.common.location.model.entity.Organization
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.r2dbc.annotation.R2dbcRepository
import io.micronaut.data.repository.kotlin.CoroutineCrudRepository
import java.util.*

@R2dbcRepository(dialect = Dialect.POSTGRES)
interface OrganizationRepository : CoroutineCrudRepository<Organization, UUID> {

    suspend fun findByIdAndAccountType(id: UUID, accountType: String, status: String): List<Organization?>
}
