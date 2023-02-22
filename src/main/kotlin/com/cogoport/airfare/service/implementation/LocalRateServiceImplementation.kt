package com.cogoport.airfare.service.implementation

import com.cogoport.airfare.model.entity.LocalRate
import com.cogoport.airfare.model.request.LocalRateRequest
import com.cogoport.airfare.repository.LocalRateRepository
import com.cogoport.airfare.service.interfaces.LocalRateService
import com.cogoport.airfare.utils.logger
import io.micronaut.data.model.Pageable
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.util.*

@Singleton
class LocalRateServiceImplementation : LocalRateService {
    @Inject
    lateinit var airFreightRateLocalRepository: LocalRateRepository
    val logger = logger()
    override suspend fun createAirFreightRateLocal(request: LocalRateRequest): UUID? {
        val airFreightRateLocal = airFreightRateLocalRepository.findLocalRate(request.airlineId, request.airportId, request.commodity, request.commodityType, request.tradeType, request.serviceProviderId)!!
        logger.info(airFreightRateLocal.toString())

//        if (airFreightRateLocal != null) {
//            val oldLineItems = airFreightRateLocal.lineItems
//            List<request.lineItems?>()?.forEach{ lineItem ->
//                val isNewLineItem = true
//                oldLineItems.forEachIndexed {index, element ->
//                    if (element.code == lineItem.code)
//
//                }
//
//
//
//            }
        return request.id
    }

    //        val data = airFreightRateLocalRepository.save(
//            AirFreightRateLocals(
//                id = null,
//                    airlineId = request.airlineId,
//                    airportId = request.airportId,
//                    tradeType = request.tradeType,
//                    commodity = request.commodity,
//                    commodityType = request.commodityType,
//                    serviceProviderId = request.serviceProviderId,
//
//
//
//
//                    )
//        )
//        return data.id!!
//    }
    override suspend fun getAirFreightRateLocal(request: LocalRateRequest): LocalRate? {
        val airFreightRateLocal = request.id?.let { airFreightRateLocalRepository.findById(it) }
        if (airFreightRateLocal != null) {
            return airFreightRateLocal!!
        } else return error(message = "no rate")
    }
    override suspend fun listAirFreightRate(page: Int, pageLimit: Int): List<LocalRate> {
        return airFreightRateLocalRepository.listOrderById(Pageable.from(page, pageLimit)).toList()
    }
}
