package com.charm.charm.domain.charmer

import org.springframework.data.jpa.repository.JpaRepository

interface CharmerRepository : JpaRepository<Charmer, Long> {
    fun findByUuid(uuid: String): Charmer?
}
