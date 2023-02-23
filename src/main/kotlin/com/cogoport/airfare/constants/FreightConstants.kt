package com.cogoport.airfare.constants

object FreightConstants {
    const val length = 300
    const val breadth = 300
    const val height = 300
    const val maximumWeight = 1000
    const val MaxCargoLimit = 10000000.0
    const val MinDensityWeight = 0.01
    val DensityCategories = arrayOf("general", "low_density", "high_density")
    val shipmentType = arrayOf("pallet", "box", "crate", "loose")
    val stackingType = arrayOf("stackable", "non_stackable")
    val commodity = arrayOf("general", "special_consideration")
    private val generalCommodityType = arrayOf("all")
    private val specialConsiderationCommodityType = arrayOf("dangerous", "temp_controlled", "other_special")
    val commodityType = generalCommodityType + specialConsiderationCommodityType
    private val generalCommoditySubType = arrayOf("all")
    private val dangerousCommoditySubType = arrayOf("class_1.1", "class_1.2", "class_1.3", "class_1.4", "class_1.5", "class_1.6", "class_2.1", "class_2.2", "class_2.3", "class_3", "class_4.1", "class_4.3", "class_5.1", "class_5.2", "class_6.1", "class_6.2", "class_7", "class_8", "class_9")
    private val tempControlledCommoditySubType = arrayOf("active_general_pharma", "active_chilled", "active_ambient", "active_frozen", "passive_general_pharma", "passive_chilled", "passive_ambient", "passive_frozen")
    private val otherSpecialCommoditySubType = arrayOf("valuables", "perishable", "fragile", "others")
    val commoditySubType = generalCommoditySubType + dangerousCommoditySubType + tempControlledCommoditySubType + otherSpecialCommoditySubType
    val priceType = arrayOf("net_net", "all_in")
    val operationType = arrayOf("passenger", "freighter", "charter", "prime", "lean")
}

