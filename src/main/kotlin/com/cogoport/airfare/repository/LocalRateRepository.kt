package com.cogoport.airfare.repository

import com.cogoport.airfare.model.entity.LocalRate
import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.Query
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.r2dbc.annotation.R2dbcRepository
import io.micronaut.data.repository.kotlin.CoroutineCrudRepository
import java.util.*

@Introspected
@R2dbcRepository(dialect = Dialect.POSTGRES)
interface LocalRateRepository : CoroutineCrudRepository<LocalRate, UUID> {
    override suspend fun findById(id: UUID): LocalRate
    suspend fun findLocalRate(airlineId: UUID?, airportId: UUID?, commodity: String?, commodityType: String?, tradeType: String?, serviceProviderId: UUID?): LocalRate?

    @Query(
        """
       SELECT * FROM air_freight_rates 
       where airline_id = :airlineId and airport_id = :airportId and commodity = :commodity 
       and commodity_type = :commodityType and trade_type = :tradeType
       and service_provider_id = :serviceProviderId
        OFFSET GREATEST(0, ((:pageIndex - 1) * :pageSize)) LIMIT :pageSize

   """
    )
    suspend fun listOrderById(
        airlineId: UUID?,
        airportId: UUID?,
        commodity: String?,
        commodityType: String?,
        tradeType: String?,
        serviceProviderId: UUID?,
        pageIndex: Int?,
        pageSize: Int?
    ): List<LocalRate?>
}
