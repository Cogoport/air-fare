package com.cogoport.airfare.service.implementation

import com.cogoport.airfare.models.entity.LocalCharge
import com.cogoport.airfare.models.request.LocalChargeRequest
import com.cogoport.airfare.repository.LocalChargeRepository
import com.cogoport.airfare.service.interfaces.LocalChargeService
import jakarta.inject.Inject
import java.util.*

class LocalChargeServiceImpl : LocalChargeService {
    @Inject
    lateinit var localChargeCodeRepository: LocalChargeRepository
    override suspend fun getLocalCharge(request: String?): LocalCharge {
        return localChargeCodeRepository.findByCode(request!!)
    }

    override suspend fun getLocalChargeByTag(request: Array<String>): List<LocalCharge> {
        return localChargeCodeRepository.findByTags(request!!)
    }

    override suspend fun createLocalCharge(request: LocalChargeRequest): UUID {
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
