package com.cogoport.airfare.enums

class Response<T> {
    var message: String? = null

    var data: T? = null

    // TO Return Response Object in data with message
    fun ok(msg: String, data: T): Response<T> {
        this.message = msg
        this.data = data
        return this
    }

    // TO Return Object only
    fun ok(data: T): T {
        return data
    }
}
