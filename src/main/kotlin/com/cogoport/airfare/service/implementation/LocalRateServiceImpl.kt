package com.cogoport.airfare.service.implementation

import com.cogoport.airfare.models.entity.LocalLineItem
import com.cogoport.airfare.models.entity.LocalRate
import com.cogoport.airfare.models.request.LocalRateRequest
import com.cogoport.airfare.repository.LocalRateRepository
import com.cogoport.airfare.service.interfaces.LocalRateService
import com.cogoport.airfare.utils.logger
import com.cogoport.airfare.validations.LineItemValidation
import io.micronaut.data.model.Pageable
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.util.*

@Singleton
class LocalRateServiceImpl : LocalRateService {
    @Inject
    lateinit var airFreightRateLocalRepository: LocalRateRepository
    lateinit var lineItemValidation: LineItemValidation


    val logger = logger()
    override suspend fun createAirFreightRateLocal(request: LocalRateRequest): UUID? {
        var airFreightRateLocal = airFreightRateLocalRepository.findLocalRate(request.airlineId, request.airportId, request.commodity, request.commodityType, request.tradeType, request.serviceProviderId)

        logger.info(airFreightRateLocal.toString())

        if (airFreightRateLocal != null) {
            var oldLineItems = airFreightRateLocal.lineItems!!
            var finalOldItems: MutableList<LocalLineItem> = mutableListOf<LocalLineItem>()
            var newLineItems = request.lineItems!!
            for (newLineItem in newLineItems) {
                var isNewLineItem = true
                for (oldLineItem in oldLineItems) {
                    if (oldLineItem.code == newLineItem.code) {
                        var isNewLineItem = false
                        finalOldItems.add(oldLineItem)
                    }
                }

                if (isNewLineItem == true) {
                    oldLineItems += newLineItem
                }
            }
            airFreightRateLocal.lineItems = oldLineItems
        } else {
            airFreightRateLocal = LocalRate(
                id = UUID.randomUUID(),
                airlineId = request.airlineId,
                airportId = request.airportId,
                tradeType = request.tradeType,
                commodity = request.commodity,
                commodityType = request.commodityType,
                serviceProviderId = request.serviceProviderId,
                lineItems = request.lineItems,
                countryId = null,
                tradeId = null,
                continentId = null,
                isLineItemsErrorMessagesPresent = null,
                isLineItemsInfoMessagesPresent = null,
                lineItemsErrorMessages = null,
                lineItemsInfoMessages = null,
                source = null,
                createdAt = null,
                updatedAt = null
            )
            airFreightRateLocalRepository.save(airFreightRateLocal)
        }

//        updateLineItemsErrorMessages(airFreightRateLocal.lineItems, airFreightRateLocal)
        return request.id
    }

    override suspend fun getAirFreightRateLocal(request: LocalRateRequest): LocalRate {
        val airFreightRateLocal = request.id?.let { airFreightRateLocalRepository.findById(it) }
        if (airFreightRateLocal != null) {
            return airFreightRateLocal!!
        } else return error(message = "no rate")
    }
    override suspend fun listAirFreightRate(page: Int, pageLimit: Int): List<LocalRate> {
        return airFreightRateLocalRepository.listOrderById(Pageable.from(page, pageLimit)).toList()
    }

//    private suspend fun updateLineItemsErrorMessages(lineItems: List<LocalLineItem>?, localRate: LocalRate) {
//        var message = lineItemValidation.validateLineItems(lineItems, localRate)
//        if (message != "ok") {
//            airFreightRateLocalRepository.update(
//                LocalRate(
//
//                )
//            )
//        } else {
//            airFreightRateLocalRepository.update(
//                isLineItemsErrorMessagesPresent = true,
//                lineItemsErrorMessages = message
//            )
//        }
//    }
}
