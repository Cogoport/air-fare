package com.cogoport.airfare.repository

import com.cogoport.airfare.model.entity.SurchargeRate
import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.Query
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.r2dbc.annotation.R2dbcRepository
import io.micronaut.data.repository.kotlin.CoroutineCrudRepository
import java.util.*

@Introspected
@R2dbcRepository(dialect = Dialect.POSTGRES)
interface SurchargeRateRepository : CoroutineCrudRepository<SurchargeRate, UUID> {
    @Query(
        """
       SELECT * FROM surcharge_rates
       where commodity = :commodity 
       and commodity_type = :commodityType
       and service_provider_id = :serviceProviderId
        OFFSET GREATEST(0, ((:pageIndex - 1) * :pageSize)) LIMIT :pageSize

   """
    )
    suspend fun listOrderById(
        commodity: String?,
        commodityType: String?,
        serviceProviderId: UUID?,
        pageIndex: Int?,
        pageSize: Int?
    ): List<SurchargeRate?>
}
