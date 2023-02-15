package com.cogoport.airfare.model.entity
import io.micronaut.core.annotation.Introspected
import io.micronaut.core.annotation.NonNull
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import java.util.*

@Introspected
@MappedEntity("platform_config_constant_mappings")
data class PlatformConfigConstantMappings(
    @field:Id @GeneratedValue @NonNull
    val id: UUID?,
    val platformConfigConstantId: UUID?,
    val value: Any?,
    val displayName: Any?,
    @ManyToOne
    @JoinColumn(name = "platform_config_constant_id", insertable = false, updatable = false)
    val platformConfigConstant: PlatformConfigConstants?

)

annotation class JoinColumn(val name: String, val insertable: Boolean, val updatable: Boolean)

annotation class ManyToOne
