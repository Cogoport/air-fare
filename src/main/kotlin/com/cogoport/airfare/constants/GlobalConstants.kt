package com.cogoport.airfare.constants

object GlobalConstants {
    val tradeType = listOf("export", "import", "domestic")
    val commodity = listOf("general", "special_consideration")
    const val generalCommodityType = "all"
    val specialConsiderationCommodityType = listOf("dangerous", "temp_controlled", "other_special")
    val commodityType = generalCommodityType + specialConsiderationCommodityType
}
