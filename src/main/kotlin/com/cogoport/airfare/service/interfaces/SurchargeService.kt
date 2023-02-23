package com.cogoport.airfare.service.interfaces

import com.cogoport.airfare.models.entity.Surcharge
import com.cogoport.airfare.models.request.SurchargeRequest
import java.util.*

interface SurchargeService {
    fun getSurcharge(request: SurchargeRequest): Surcharge
    fun createSurcharge(request: SurchargeRequest): UUID?
}
