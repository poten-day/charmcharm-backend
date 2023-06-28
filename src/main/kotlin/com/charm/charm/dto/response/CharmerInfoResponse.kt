package com.charm.charm.dto.response

import com.charm.charm.domain.charmer.Charmer
import java.time.LocalDateTime

data class CharmerInfoResponse(
    var name: String,
    var openTime: LocalDateTime,
    var shareLink: String,
    var finished: Boolean,
) {
    companion object {
        fun from(charmer: Charmer): CharmerInfoResponse {
            return CharmerInfoResponse(
                name = charmer.name,
                openTime = charmer.openTime,
                shareLink = charmer.sharedLink(),
                finished = charmer.isFinished(),
            )
        }
    }
}
