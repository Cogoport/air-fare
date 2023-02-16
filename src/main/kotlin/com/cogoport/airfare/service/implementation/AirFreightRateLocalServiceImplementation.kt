package com.cogoport.airfare.service.implementation

import com.cogoport.airfare.model.entity.AirFreightRateLocals
import com.cogoport.airfare.model.request.AirFreightRateLocalRequest
import com.cogoport.airfare.repository.AirFreightRateLocalRepository
import com.cogoport.airfare.service.interfaces.AirFreightRateLocalService
import jakarta.inject.Inject

class AirFreightRateLocalServiceImplementation : AirFreightRateLocalService {
    @Inject
    lateinit var airFreightRateLocalRep: AirFreightRateLocalRepository

    override suspend fun getAirFreightRateLocal(request: AirFreightRateLocalRequest): AirFreightRateLocals {
        return airFreightRateLocalRep.findById(request.id)!!
    }
}

