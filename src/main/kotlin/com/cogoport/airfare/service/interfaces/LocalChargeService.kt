package com.cogoport.airfare.service.interfaces

import com.cogoport.airfare.models.entity.LocalCharge
import com.cogoport.airfare.models.request.LocalChargeRequest
import java.util.*

interface LocalChargeService {
    suspend fun getLocalCharge(request: String?): LocalCharge?
    suspend fun getLocalChargeByTag(request: Array<String>): List<LocalCharge>?
    suspend fun createLocalCharge(request: LocalChargeRequest): UUID?
}
