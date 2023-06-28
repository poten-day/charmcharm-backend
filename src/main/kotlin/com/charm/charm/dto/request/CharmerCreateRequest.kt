package com.charm.charm.dto.request

import com.charm.charm.domain.charmer.Charmer

data class CharmerCreateRequest(
    var name: String,
) {
    fun toEntity() = Charmer(name = name)
}
