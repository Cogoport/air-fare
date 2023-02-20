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
        validateInput(request)
        return airFreightRepo.findById(request.id)!!
    }

    private fun validateInput(request: AirFreightRequest) {
        val tariffPrice: Float = request.tariffPrice
        val currency: String = request.currency
        val currencyCodeRegex = Regex("^[A-Z]{3}$")

        if (tariffPrice < 0) {
            throw IllegalArgumentException("Tariff price cannot be negative.")
        }

        if (!currencyCodeRegex.matches(currency)) {
            throw IllegalArgumentException("Currency code is invalid.")
        }
    }
}
