package com.cogoport.airfare.service.implementation

import com.cogoport.airfare.model.entity.AirFreightRateLocals
import com.cogoport.airfare.model.request.AirFreightRateLocalRequest
import com.cogoport.airfare.repository.AirFreightRateLocalRepository
import com.cogoport.airfare.service.interfaces.AirFreightRateLocalService
import io.micronaut.data.model.Pageable
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.util.*

@Singleton
class AirFreightRateLocalServiceImplementation : AirFreightRateLocalService {
    @Inject
    lateinit var airFreightRateLocalRepository: AirFreightRateLocalRepository

    override suspend fun createAirFreightRateLocal(request: AirFreightRateLocalRequest): UUID? {
        val airFreightRateLocal = airFreightRateLocalRepository.findByAirFreightRateLocals(request.airlineId, request.airportId, request.commodity, request.commodityType, request.tradeType, request.serviceProviderId)
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
    override suspend fun getAirFreightRateLocal(request: AirFreightRateLocalRequest): AirFreightRateLocals {
        val airFreightRateLocal = request.id?.let { airFreightRateLocalRepository.findById(it) }
        if (airFreightRateLocal != null) {
            return airFreightRateLocal!!
        } else return error(message = "no rate")
    }
    override suspend fun listAirFreightRate(page: Int, pageLimit: Int): List<AirFreightRateLocals> {
        return airFreightRateLocalRepository.listOrderById(Pageable.from(page, pageLimit)).toList()
    }
}
