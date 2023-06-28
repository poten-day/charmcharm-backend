package com.charm.charm.domain.form

import com.charm.charm.domain.charmer.Charmer
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class QuestionForm(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1,

    @Column(name = "question_title")
    var questionTitle: String,

    @Column(name = "is_single_choice")
    var isSingleChoice: Boolean,

    @Column(name = "min_answer_count")
    var minAnswerCount: Int = 1,

    @Convert(converter = AnswerConverter::class)
    @Column(name = "question_options")
    var questionOptions: MutableList<QuestionOption> = mutableListOf(),
) {
    fun toQuestionAnswer(charmer: Charmer, ids: List<Long>): QuestionFormAnswer {
        require(questionOptions.map { it.id }.containsAll(ids)) { "존재하지 않는 QuestionOption 요청" }
        validateChoiceCount(ids)
        return QuestionFormAnswer(
            questionForm = this,
            charmer = charmer,
            questionOptions = questionOptions.filter { ids.contains(it.id) }.toMutableList(),
        )
    }

    fun questionTitle(name: String): String {
        return questionTitle.replace("[name_space]", name)
    }

    private fun validateChoiceCount(ids: List<Long>) {
        if (isSingleChoice) {
            require(ids.size == 1) { "단일 선택만 가능합니다." }
            return
        }
        require(ids.size >= minAnswerCount) { "최소 요구 선택 개수 이상 선택하여야합니다." }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as QuestionForm

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
