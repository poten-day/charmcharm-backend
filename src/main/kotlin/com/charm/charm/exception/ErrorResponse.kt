package com.charm.charm.exception

import java.lang.RuntimeException

data class ErrorResponse(
    var message: String,
) {
    companion object {
        fun from(e: RuntimeException) = ErrorResponse(
            message = e.message ?: "UNKNOWN ERROR",
        )
    }
}
