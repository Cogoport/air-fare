package com.cogoport.airfare.services.interfaces.implementation

import com.cogoport.airfare.model.entity.AirFreightRates
import com.cogoport.airfare.model.request.AirFreightRequest
import com.cogoport.airfare.repository.AirFreightRepository
import com.cogoport.airfare.service.interfaces.AirFreightRateService
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class AirFreightRateServiceImplementation : AirFreightRateService {
    @Inject
    lateinit var airFreightRepo: AirFreightRepository

    override suspend fun getAirFreightRate(request: AirFreightRequest): AirFreightRates {
        return airFreightRepo.findById(request.id)!!
    }
}
