package com.cogoport.airfare.services.interfaces.implementation

import com.cogoport.airfare.model.request.FreightWarehouseRequest
import com.cogoport.airfare.repository.WarehouseRateRepository
import com.cogoport.airfare.service.`interface`.WarehouseRateService
import jakarta.inject.Inject

class WarehouseRateServiceImpl : WarehouseRateService {
    @Inject
    lateinit var airFreightWarehouseRepo: WarehouseRateRepository

    override suspend fun getAirFreightWarehouseRate(request: FreightWarehouseRequest): Any? {
        var a = airFreightWarehouseRepo.findAirFreightWarehouseRates(request.airportId, request.tradeType, request.commodity, request.serviceProviderId)
        return a
    }
}
