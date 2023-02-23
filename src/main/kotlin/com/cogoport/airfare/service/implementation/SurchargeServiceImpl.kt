package com.cogoport.airfare.service.implementation

import com.cogoport.airfare.models.entity.Surcharge
import com.cogoport.airfare.models.request.SurchargeRequest
import com.cogoport.airfare.repository.SurchargeRepository
import jakarta.inject.Inject
import java.util.*

class SurchargeServiceImpl {
    @Inject lateinit var surchargeRepository: SurchargeRepository
    suspend fun getSurcharge(request: SurchargeRequest): Surcharge {
        return surchargeRepository.findByCode(request.code!!)!!
    }
    suspend fun createSurcharge(request: SurchargeRequest): UUID {
        val surcharge = surchargeRepository.save(
                Surcharge(
                        id = UUID.randomUUID(),
                        code = request.code,
                        name = request.name,
                        units = request.units,
                        condition = request.condition,
                        tags = request.tags,
                        sacCode = request.sacCode
                )
        )
        return surcharge.id!!
    }
}
