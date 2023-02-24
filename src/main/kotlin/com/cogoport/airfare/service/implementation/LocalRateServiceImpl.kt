package com.cogoport.airfare.service.implementation

import com.cogoport.airfare.model.entity.FreightRate
import com.cogoport.airfare.model.entity.LocalRate
import com.cogoport.airfare.model.request.FreightRateRequest
import com.cogoport.airfare.model.request.LocalRateRequest
import com.cogoport.airfare.models.entity.LocalLineItem
import com.cogoport.airfare.repository.FreightRateRepository
import com.cogoport.airfare.repository.LocalRateRepository
import com.cogoport.airfare.service.interfaces.FreightRateService
import com.cogoport.airfare.service.interfaces.LocalRateService
import com.cogoport.airfare.utils.logger
import com.cogoport.airfare.validation.LineItemValidation
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.util.*

@Singleton
class LocalRateServiceImpl : LocalRateService {
    @Inject
    lateinit var localRateRepository: LocalRateRepository
    lateinit var freightRateRepository: FreightRateRepository
    lateinit var lineItemValidation: LineItemValidation
    lateinit var freightRateService: FreightRateService

    val logger = logger()
    override suspend fun createLocalRate(request: LocalRateRequest): UUID? {
        var localRate = localRateRepository.findLocalRate(request.airlineId, request.airportId, request.commodity, request.commodityType, request.tradeType, request.serviceProviderId)
        val newRecord = localRate?.let { false } ?: true
        if (!newRecord) {
            logger.info(localRate.toString())
            var oldLineItems = localRate?.lineItems!!.toMutableList()
            var newLineItems = request.lineItems!!
            newLineItems.forEach { newLineItem ->
                var isNewLineItem = true
                for (index in oldLineItems.indices) {
                    if (oldLineItems[index].code == newLineItem.code) {
                        isNewLineItem = false
                        oldLineItems[index] = newLineItem
                    }
                }
                if (isNewLineItem == true) {
                    oldLineItems.add(newLineItem)
                }
                localRate.lineItems = oldLineItems
            }
        } else {
            val localRate = LocalRate(
                id = UUID.randomUUID(),
                airlineId = request.airlineId,
                airportId = request.airportId,
                tradeType = request.tradeType,
                commodity = request.commodity,
                commodityType = request.commodityType,
                serviceProviderId = request.serviceProviderId,
                lineItems = request.lineItems

            )
            localRateRepository.save(localRate)
            updateAirFreightObjects(localRate)
        }
        if (localRate != null) {
            updateLineItemsErrorMessages(localRate.lineItems, localRate)
        }

        return request.id
    }

    override suspend fun getLocalRate(request: LocalRateRequest): LocalRate {
        return request.id?.let { localRateRepository.findById(it) } ?: error(message = "no rate")
    }

    override suspend fun listLocalRate(request: LocalRateRequest): List<LocalRate?> {
        return localRateRepository.listOrderById(
            airlineId = request.airlineId,
            airportId = request.airportId,
            commodity = request.commodity,
            commodityType = request.commodityType,
            tradeType = request.tradeType,
            serviceProviderId = request.serviceProviderId,
            pageIndex = request.page,
            pageSize = request.pageLimit
        ).toList()
    }

    private suspend fun updateLineItemsErrorMessages(lineItems: List<LocalLineItem>?, localRate: LocalRate) {
        var message = lineItemValidation.validateLineItems(lineItems!!, localRate)
        if (message.isNotEmpty()) {
            localRateRepository.update(
                LocalRate(
                    isLineItemsErrorMessagesPresent = true,
                    lineItemsErrorMessages = message
                )
            )
        } else {
            localRateRepository.update(
                LocalRate(
                    isLineItemsErrorMessagesPresent = false,
                    lineItemsErrorMessages = message
                )
            )
        }
    }

    private suspend fun updateAirFreightObjects(localRateObject: LocalRate) {
        val freightObjects = freightRateRepository.listForLocalIdUpdate(airlineId = localRateObject.airlineId!!, commodity = localRateObject.commodity!!, commodityType = localRateObject.commodityType!!, serviceProviderId = localRateObject.serviceProviderId!!, originAirportId = localRateObject.airportId!!, destinationAirportId = localRateObject.airportId!!, originLocalId = null)
        if (localRateObject.tradeType == "export") {
            freightObjects!!.forEach {
                freightRateRepository.update(
                        FreightRate(
                            originLocalId = localRateObject.id
                        )
                )

            }
        } else if (localRateObject.tradeType == "import") {
            freightObjects!!.forEach {
                freightRateRepository.update(
                        FreightRate(
                                destinationLocalId = localRateObject.id
                        )
                )

            }
        }


    }


}
