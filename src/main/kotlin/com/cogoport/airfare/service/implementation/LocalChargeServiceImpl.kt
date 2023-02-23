package com.cogoport.airfare.service.implementation

import com.cogoport.airfare.models.entity.LocalCharge
import com.cogoport.airfare.models.request.LocalChargeRequest
import com.cogoport.airfare.repository.LocalChargeRepository
import jakarta.inject.Inject
import java.util.*

class LocalChargeServiceImpl {
    @Inject lateinit var localChargeCodeRepository: LocalChargeRepository
    suspend fun getLocalChargeCode(request: LocalChargeRequest): LocalCharge {
        return localChargeCodeRepository.findByCode(request.code!!)!!
    }
    suspend fun createLocalChargeCode(request: LocalChargeRequest): UUID {
        val localChargeCode = localChargeCodeRepository.save(
            LocalCharge(
                id = UUID.randomUUID(),
                code = request.code,
                name = request.name,
                units = request.units,
                tradeTypes = request.tradeTypes,
                condition = request.condition,
                tags = request.tags,
                sacCode = request.sacCode
            )
        )
        return localChargeCode.id!!
    }
}
