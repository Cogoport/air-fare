package com.cogoport.airfare.services.interfaces.implementation

import com.cogoport.airfare.model.request.AirFreightWarehouseRequest
import com.cogoport.airfare.repository.AirFreightWarehouseRepository
import com.cogoport.airfare.service.interfaces.AirFreightWarehouseRateService
import jakarta.inject.Inject

class AirFreightWarehouseRateServiceImplementation : AirFreightWarehouseRateService {
    @Inject
    lateinit var airFreightWarehouseRepo: AirFreightWarehouseRepository

    override suspend fun getAirFreightWarehouseRate(request: AirFreightWarehouseRequest): Any? {
        var a = airFreightWarehouseRepo.findAirFreightWarehouseRates(request.airportId, request.tradeType, request.commodity, request.serviceProviderId)
        return a
    }
}
