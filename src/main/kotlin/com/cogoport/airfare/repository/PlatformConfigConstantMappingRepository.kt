package com.cogoport.airfare.repository

import com.cogoport.airfare.models.entity.PlatformConfigConstantMappings
import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.Query
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.r2dbc.annotation.R2dbcRepository
import io.micronaut.data.repository.kotlin.CoroutineCrudRepository
import java.util.*

@Introspected
@R2dbcRepository(dialect = Dialect.POSTGRES)
interface PlatformConfigConstantMappingRepository : CoroutineCrudRepository<PlatformConfigConstantMappings, UUID> {
    @Query(""" SELECT * FROM platform_config_constant_mappings where platform_config_constant_id= :platformConfigConstantId""")
    fun findPlatformConfigConstantBy(platformConfigConstantId: UUID?): PlatformConfigConstantMappings?
}
