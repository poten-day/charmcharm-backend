package com.charm.charm.dto.response

import com.charm.charm.domain.form.QuestionOption

data class CharmerResultResponse(
    val name: String,
    val hex: String,
    val mainQuestions: List<QuestionFormMainResultResponse>,
    val subQuestions: List<QuestionFormSubResultResponse>,
)

data class QuestionFormMainResultResponse(
    val questionId: Long,
    val questionTitle: String,
    val answerResults: List<QuestionFormAnswerMainResultResponse>,
)

data class QuestionFormAnswerMainResultResponse(
    val answerId: Long,
    val answerName: String,
) {
    companion object {
        fun from(questionOption: QuestionOption) = QuestionFormAnswerMainResultResponse(
            answerId = questionOption.id,
            answerName = questionOption.description,
        )
    }
}

data class QuestionFormSubResultResponse(
    val questionId: Long,
    val questionTitle: String,
    val answerResults: List<QuestionFormAnswerSubResultResponse>,
)

data class QuestionFormAnswerSubResultResponse(
    val answerId: Long,
    val answerName: String,
    val answerCount: Int,
) {
    companion object {
        fun of(questionOption: QuestionOption, count: Int) = QuestionFormAnswerSubResultResponse(
            answerId = questionOption.id,
            answerName = questionOption.description,
            answerCount = count,
        )
    }
}
