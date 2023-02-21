package com.cogoport.airfare.services.interfaces.implementation

import com.cogoport.airfare.model.entity.FreightRate
import com.cogoport.airfare.model.entity.FreightRateWeightSlab
import com.cogoport.airfare.model.request.FreightRateRequest
import com.cogoport.airfare.repository.FreightRateRepository
import com.cogoport.airfare.service.`interface`.FreightRateService
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class FreightRateServiceImpl : FreightRateService {
    @Inject
    lateinit var airFreightRepo: FreightRateRepository

    override suspend fun getAirFreightRate(request: FreightRateRequest): FreightRate {
        return airFreightRepo.findById(request.id!!)!!
    }

    override suspend fun createAirFreightRate(request: FreightRateRequest): Any? {
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

    private fun validateWeightSlabInput(weightSlabs: FreightRateWeightSlab): Boolean {
        val CURRENCY_REGEX = Regex("^\\$?[0-9]+(\\.[0-9][0-9])?\$")
        return weightSlabs.currency.matches(CURRENCY_REGEX) && weightSlabs.tariff_price >= 0
    }
}
