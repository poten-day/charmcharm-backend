package com.charm.charm.dto.response

import com.charm.charm.domain.charmer.Charmer
import com.charm.charm.domain.form.QuestionForm
import com.charm.charm.domain.form.QuestionOption

data class QuestionFormResponse(
    var id: Long,
    var questionTitle: String,
    var answers: List<QuestionOptionResponse>,
    var isSingleChoice: Boolean,
    var minAnswerCount: Int,
) {
    companion object {
        fun of(questionForm: QuestionForm, charmer: Charmer) = QuestionFormResponse(
            id = questionForm.id,
            questionTitle = questionForm.questionTitle(charmer.name),
            answers = questionForm.questionOptions.map { QuestionOptionResponse.from(it) },
            isSingleChoice = questionForm.isSingleChoice,
            minAnswerCount = questionForm.minAnswerCount,
        )
    }
}

data class QuestionOptionResponse(
    var id: Long,
    var description: String,
) {
    companion object {
        fun from(questionOption: QuestionOption) = QuestionOptionResponse(
            id = questionOption.id,
            description = questionOption.description,
        )
    }
}
