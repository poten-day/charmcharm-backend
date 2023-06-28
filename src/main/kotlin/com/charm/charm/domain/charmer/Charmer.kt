package com.charm.charm.domain.charmer

import com.charm.charm.domain.form.QuestionFormAnswer
import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Charmer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1,

    @Column(name = "name", length = 15, nullable = false)
    val name: String,

    @Column(name = "uuid", length = 255, nullable = false)
    var uuid: String = UUID.randomUUID().toString(),

    @Column(name = "open_time", nullable = false)
    var openTime: LocalDateTime = LocalDateTime.now().plusHours(OPEN_LIMIT_HOUR),

    @OneToMany(mappedBy = "charmer", fetch = FetchType.LAZY)
    var questionFormAnswers: MutableList<QuestionFormAnswer> = mutableListOf(),
) {
    fun sharedLink() = "https://charmcharm.me/$uuid"

    fun isFinished() = LocalDateTime.now().isAfter(openTime)

    fun haveNoAnswer() = questionFormAnswers.isEmpty()

    companion object {
        private const val OPEN_LIMIT_HOUR: Long = 4
    }
}
