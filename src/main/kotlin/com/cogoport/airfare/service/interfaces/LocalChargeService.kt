package com.cogoport.airfare.service.interfaces

import com.cogoport.airfare.models.entity.LocalCharge
import com.cogoport.airfare.models.request.LocalChargeRequest
import java.util.*

interface LocalChargeService {
    fun getLocalChargeCode(request: LocalChargeRequest): LocalCharge
    fun createLocalChargeCode(request: LocalChargeRequest): UUID?

}