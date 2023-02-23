package com.cogoport.airfare.service.implementation

import com.cogoport.airfare.model.entity.SurchargeRate
import com.cogoport.airfare.model.request.SurchargeRateRequest
import com.cogoport.airfare.repository.SurchargeRateRepository
import com.cogoport.airfare.service.interfaces.SurchargeRateService
import jakarta.inject.Inject

class SurchargeRateServiceImpl : SurchargeRateService {
    @Inject
    lateinit var surchargeRateRepository: SurchargeRateRepository
    override suspend fun listSurchargeRate(request: SurchargeRateRequest): List<SurchargeRate?> {
        return surchargeRateRepository.listOrderById(
            commodity = request.commodity,
            commodityType = request.commodityType,
            serviceProviderId = request.serviceProviderId,
            pageIndex = request.page,
            pageSize = request.pageLimit
        ).toList()
    }
}
