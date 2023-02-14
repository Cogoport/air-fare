package com.cogoport.airfare.services.interfaces.implementation

import com.cogoport.airfare.models.request.AirFreightWarehouseRequest
import com.cogoport.airfare.repository.AirFreightWarehouseRepository
import com.cogoport.airfare.services.interfaces.AirFreightWarehouseRateService
import jakarta.inject.Inject

class AirFreightWarehouseRateServiceImplementation : AirFreightWarehouseRateService {
    @Inject
    lateinit var airFreightWarehouseRepo: AirFreightWarehouseRepository

    override suspend fun getAirFreightWarehouseRate(request: AirFreightWarehouseRequest): Any? {
        return airFreightWarehouseRepo.findAirFreightWarehouseRates(request.airportId, request.tradeType, request.commodity, request.serviceProviderId)
    }
}
