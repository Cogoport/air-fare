package com.cogoport.airfare.services.interfaces.implementation

import com.cogoport.airfare.models.entity.AirFreightRates
import com.cogoport.airfare.models.request.AirFreightRequest
import com.cogoport.airfare.repository.AirFreightRepo
import com.cogoport.airfare.services.interfaces.AirFreightRateService
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class AirFreightRateServiceImplementation : AirFreightRateService {
    @Inject
    lateinit var airFreightRepo: AirFreightRepo

    override suspend fun getAirFreightRate(request: AirFreightRequest): AirFreightRates {
        return airFreightRepo.findById(request.id)!!
    }
}
