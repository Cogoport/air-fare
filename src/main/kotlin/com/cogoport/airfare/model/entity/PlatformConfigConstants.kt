package com.cogoport.airfare.model.entity

import io.micronaut.core.annotation.Introspected
import io.micronaut.core.annotation.NonNull
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import java.util.*

@Introspected
@MappedEntity("platform_config_constants")
data class PlatformConfigConstants(
    @field:Id @GeneratedValue @NonNull
    val id: UUID?,
    val keyName: String?,
    val service: String?,
    val dataType: String?,
    val modelAttribute: String?,
    val accessType: String,
    val status: String?
)


