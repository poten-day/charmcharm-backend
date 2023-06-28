package com.charm.charm.dto.request

data class QuestionFormAnswerRequest(
    val questionId: Long,
    val answerIds: List<Long>,
)
