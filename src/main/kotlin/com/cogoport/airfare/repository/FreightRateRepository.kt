package com.cogoport.airfare.repository

import com.cogoport.airfare.model.entity.FreightRate
import io.micronaut.data.annotation.Query
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.r2dbc.annotation.R2dbcRepository
import io.micronaut.data.repository.kotlin.CoroutineCrudRepository
import java.util.*

@R2dbcRepository(dialect = Dialect.POSTGRES)
interface FreightRateRepository : CoroutineCrudRepository<FreightRate, UUID> {

    override suspend fun findById(id: UUID): FreightRate?

    @Query(
        """
        SELECT * from air_freight_rates 
        where origin_airport_id = :originAirportId and destination_airport_id = :destinationAirportId and 
        commodity = :commodity and commodity_type = :commodityType and commodity_sub_type =:commoditySubType and
        airline_id = :airlineId and operation_type = :operationType and service_provider_id = :serviceProviderId and
        shipment_type = :shipmentType and stacking_type = :stackingType and price_type = :priceType
    """
    )
    suspend fun listFreightRate(originAirportId: UUID, destinationAirportId: UUID, commodity: String, commodityType: String, commoditySubType: String?, airlineId: UUID, operationType: String, serviceProviderId: UUID, shipmentType: String, stackingType: String, priceType: String, cogoEntityId: UUID?): List<FreightRate>?
}
