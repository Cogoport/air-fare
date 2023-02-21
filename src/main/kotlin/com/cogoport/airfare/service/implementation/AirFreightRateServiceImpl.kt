package com.cogoport.airfare.services.interfaces.implementation

import com.cogoport.airfare.model.entity.AirFreightRates
import com.cogoport.airfare.model.entity.AirFreightWeightSlabs
import com.cogoport.airfare.model.request.AirFreightRequest
import com.cogoport.airfare.repository.AirFreightRepository
import com.cogoport.airfare.service.interfaces.AirFreightRateService
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class AirFreightRateServiceImpl : AirFreightRateService {
    @Inject
    lateinit var airFreightRepo: AirFreightRepository

    override suspend fun getAirFreightRate(request: AirFreightRequest): AirFreightRates {
        return airFreightRepo.findById(request.id!!)!!
    }

    override suspend fun createAirFreightRate(request: AirFreightRequest): Any? {
        val weightSlabs = request.weightSlabs
        var errors = StringBuilder()

        for (weightSlab in weightSlabs) {
            if (!validateWeightSlabInput(weightSlab)) {
                errors.append(" Invalid weight slab,")
                return errors
            }
        }
//        more code to be written will remove return 0
        return 0
    }

    private fun validateWeightSlabInput(weightSlabs: AirFreightWeightSlabs): Boolean {
        val CURRENCY_REGEX = Regex("^\\$?[0-9]+(\\.[0-9][0-9])?\$")
        return weightSlabs.currency.matches(CURRENCY_REGEX) && weightSlabs.tariff_price >= 0
    }
}
