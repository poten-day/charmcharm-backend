package com.charm.charm.application

import com.charm.charm.domain.charmer.Charmer
import com.charm.charm.domain.charmer.CharmerRepository
import com.charm.charm.domain.form.QuestionFormAnswerRepository
import com.charm.charm.domain.form.QuestionFormRepository
import com.charm.charm.dto.request.QuestionFormAnswerRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QuestionAnswerService(
    private val questionFormAnswerRepository: QuestionFormAnswerRepository,
    private val charmerRepository: CharmerRepository,
    private val questionFormRepository: QuestionFormRepository,
) {
    @Transactional
    fun saveQuestionFormAnswers(uuid: String, request: List<QuestionFormAnswerRequest>) {
        val charmer = charmerRepository.findByUuid(uuid) ?: throw IllegalArgumentException("존재하지 않는 charmer")
        check(charmer.isFinished().not()) { "답변 가능한 시간이 지났습니다." }
        request.forEach { saveQuestionFormAnswer(it, charmer) }
    }

    private fun saveQuestionFormAnswer(
        it: QuestionFormAnswerRequest,
        charmer: Charmer,
    ) {
        val questionForm = questionFormRepository.findById(it.questionId)
            .orElseThrow { IllegalArgumentException("존재하지 않는 questionForm") }
        val questionFormAnswer = questionForm.toQuestionAnswer(charmer, it.answerIds)
        questionFormAnswerRepository.save(questionFormAnswer)
    }
}
