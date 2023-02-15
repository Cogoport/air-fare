package com.cogoport.airfare.service.implementation

import com.cogoport.airfare.model.entity.PlatformConfigConstantMappings
import com.cogoport.airfare.model.request.GlobalConstantRequest
import com.cogoport.airfare.repository.GlobalConstantRepository
import com.cogoport.airfare.repository.PlatformConfigConstantMappingRepository
import com.cogoport.airfare.service.interfaces.GlobalConstantService
import io.micronaut.caffeine.cache.Caffeine
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.util.concurrent.TimeUnit

@Singleton
class GlobalConstantServiceImplementation : GlobalConstantService {
    @Inject
    lateinit var globalConstantRepository: GlobalConstantRepository

    @Inject
    lateinit var platformConfigConstantMappingRepository: PlatformConfigConstantMappingRepository

    override suspend fun getGlobalConstant(request: GlobalConstantRequest): PlatformConfigConstantMappings? {
        var key = "DC_${request.service}_${request.keyName}"
        val cache = Caffeine.newBuilder()
            .expireAfterWrite(1, TimeUnit.MINUTES)
            .maximumSize(100)
            .build<String, PlatformConfigConstantMappings>()
        val value = cache.get(key){
            val result = globalConstantRepository.findGlobalConstant(request.keyName!!, request.service!!)
            val result2 = platformConfigConstantMappingRepository.findPlatformConfigConstantBy(result?.id)
            result2
        }
        return value
    }
}
