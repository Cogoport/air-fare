package com.cogoport.airfare.services.interfaces.implementation

import com.cogoport.airfare.models.request.FreightWarehouseRequest
import com.cogoport.airfare.repository.WarehouseRateRepository
import com.cogoport.airfare.service.interfaces.WarehouseRateService
import jakarta.inject.Inject

class WarehouseRateServiceImpl : WarehouseRateService {
    @Inject
    lateinit var airFreightWarehouseRepo: WarehouseRateRepository

    override suspend fun getAirFreightWarehouseRate(request: FreightWarehouseRequest): Any? {
        var a = airFreightWarehouseRepo.findWarehouseRate(request.airportId, request.tradeType, request.commodity, request.serviceProviderId)
        return a
    }
}
