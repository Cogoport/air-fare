package com.cogoport.airfare.service.implementation

import com.cogoport.airfare.common.audit.model.request.AuditRequest
import com.cogoport.airfare.common.audit.service.interfaces.AuditService
import com.cogoport.airfare.model.entity.LocalRate
import com.cogoport.airfare.model.request.LocalRateRequest
import com.cogoport.airfare.models.entity.LocalLineItem
import com.cogoport.airfare.repository.FreightRateRepository
import com.cogoport.airfare.repository.LocalRateRepository
import com.cogoport.airfare.service.interfaces.LocalRateService
import com.cogoport.airfare.utils.logger
import com.cogoport.airfare.validation.LocalLineItemValidation
import com.cogoport.airfare.validation.LocalRateValidation
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.util.*

@Singleton
class LocalRateServiceImpl : LocalRateService {
    @Inject lateinit var localRateRepository: LocalRateRepository

    @Inject lateinit var freightRateRepository: FreightRateRepository

    @Inject lateinit var lineItemValidation: LocalLineItemValidation

    @Inject lateinit var localRateValidation: LocalRateValidation

    @Inject lateinit var auditService: AuditService

    val logger = logger()
    override suspend fun createLocalRate(request: LocalRateRequest): UUID? {
        localRateValidation.validateLocalRequest(request)
        val localRate = localRateRepository.findLocalRate(request.airlineId, request.airportId, request.commodity, request.commodityType, request.tradeType, request.serviceProviderId)
        if (localRate != null) {
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
            updateLineItemsErrorMessages(localRate.lineItems, localRate)
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
            updateLineItemsErrorMessages(localRate.lineItems, localRate)
            updateAirFreightObjects(localRate)
        }

        createAudit(localRate!!, request)
        return localRate.id
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
            localRate.isLineItemsErrorMessagesPresent = true
            localRate.lineItemsErrorMessages = message
        } else {
            localRate.isLineItemsErrorMessagesPresent = false
            localRate.lineItemsErrorMessages = message
        }
    }

    private suspend fun updateAirFreightObjects(localRateObject: LocalRate) {
        val freightObjects = freightRateRepository.listForLocalId(airlineId = localRateObject.airlineId!!, commodity = localRateObject.commodity!!, commodityType = localRateObject.commodityType!!, serviceProviderId = localRateObject.serviceProviderId!!, originAirportId = localRateObject.airportId!!, destinationAirportId = localRateObject.airportId!!, originLocalId = null)
        if (localRateObject.tradeType == "export") {
            freightObjects!!.forEach { freightObject ->
                freightObject.originLocalId = localRateObject.id
            }
        } else if (localRateObject.tradeType == "import") {
            freightObjects!!.forEach { _ ->
                freightObjects!!.forEach { freightObject ->
                    freightObject.destinationLocalId = localRateObject.id
                }
            }
        }
    }
    private suspend fun createAudit(localRate: LocalRate, request: LocalRateRequest) {
        auditService.createAudit(
            AuditRequest(
                objectType = "AirFreightRate",
                objectId = localRate.id!!,
                bulkOperationId = request.bulkOperationId,
                actionName = "create",
                data = localRate,
                procuredById = request.procuredById,
                sourcedById = request.sourcedById,
                performedById = request.performedById!!,
                rateSheetId = request.rateSheetId!!
            )
        )
    }
}
