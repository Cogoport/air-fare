package com.cogoport.airfare.exception

import io.micronaut.http.HttpStatus

enum class AirfareError(
    val code: String,
    val message: String,
    val httpStatus: HttpStatus
) {
    ERR_1001("ERROR_1001", "Something went wrong please contact admin", HttpStatus.SERVICE_UNAVAILABLE);
    fun getMessage(param: String): String {
        if (param.isNotEmpty()) {
            return "$message $param"
        }
        return message
    }

    override fun toString(): String {
        return "$code: $message"
    }
}
