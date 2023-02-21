package com.cogoport.airfare.exception

class AirfareException(val error: AirfareError, val context: String) : RuntimeException()
