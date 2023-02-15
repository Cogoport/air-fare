package com.cogoport.airfare.repository
import com.cogoport.airfare.model.entity.PlatformConfigConstants
import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.Query
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.r2dbc.annotation.R2dbcRepository
import io.micronaut.data.repository.kotlin.CoroutineCrudRepository
import java.util.*

@Introspected
@R2dbcRepository(dialect = Dialect.POSTGRES)
interface GlobalConstantRepository : CoroutineCrudRepository<PlatformConfigConstants, UUID> {
    @Query(
        """SELECT * FROM platform_config_constants WHERE key_name = :keyName and service = :service and status = 'active'"""
    )
    fun findGlobalConstant(keyName: String, service: String): PlatformConfigConstants?
}
