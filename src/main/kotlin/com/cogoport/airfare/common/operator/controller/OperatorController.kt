package com.cogoport.airfare.common.location.controller

import com.cogoport.airfare.common.operator.model.entity.Operator
import com.cogoport.airfare.common.operator.model.request.OperatorRequest
import com.cogoport.airfare.common.operator.service.interfaces.OperatorService
import com.cogoport.airfare.model.response.Response
import io.micronaut.http.annotation.Get
import jakarta.inject.Inject

class OperatorController {
    @Inject
    lateinit var operatorService: OperatorService

    @Get("/{?request*}")
    suspend fun getOperator(request: OperatorRequest): List<Operator?> {
        return Response<List<Operator?>>().ok(operatorService.getOperatorById(request))
    }
}
