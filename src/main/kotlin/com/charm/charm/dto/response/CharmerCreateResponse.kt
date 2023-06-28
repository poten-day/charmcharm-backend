package com.charm.charm.dto.response

import com.charm.charm.domain.charmer.Charmer
import java.time.LocalDateTime

data class CharmerCreateResponse(
    var name: String,
    var openTime: LocalDateTime,
    var shareLink: String,
) {
    companion object {
        fun from(charmer: Charmer) = CharmerCreateResponse(
            name = charmer.name,
            openTime = charmer.openTime,
            shareLink = charmer.sharedLink(),
        )
    }
}
