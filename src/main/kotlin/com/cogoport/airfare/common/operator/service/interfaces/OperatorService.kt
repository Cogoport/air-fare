package com.cogoport.airfare.common.operator.service.interfaces

import com.cogoport.airfare.common.operator.model.entity.Operator
import com.cogoport.airfare.common.operator.model.request.OperatorRequest

interface OperatorService {

    suspend fun getOperatorById(request: OperatorRequest): List<Operator?>
}
