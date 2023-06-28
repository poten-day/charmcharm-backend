package com.charm.charm.application

import com.charm.charm.domain.charmer.Charmer
import com.charm.charm.domain.charmer.CharmerRepository
import com.charm.charm.domain.color.Color
import com.charm.charm.domain.form.QuestionForm
import com.charm.charm.domain.form.QuestionOption
import com.charm.charm.dto.request.CharmerCreateRequest
import com.charm.charm.dto.response.CharmerCreateResponse
import com.charm.charm.dto.response.CharmerInfoResponse
import com.charm.charm.dto.response.CharmerResultResponse
import com.charm.charm.dto.response.QuestionFormAnswerMainResultResponse
import com.charm.charm.dto.response.QuestionFormAnswerSubResultResponse
import com.charm.charm.dto.response.QuestionFormMainResultResponse
import com.charm.charm.dto.response.QuestionFormSubResultResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CharmerService(
    private val charmerRepository: CharmerRepository,
) {
    @Transactional
    fun signUpCharmer(request: CharmerCreateRequest): CharmerCreateResponse {
        val charmer = charmerRepository.save(request.toEntity())
        return CharmerCreateResponse.from(charmer)
    }

    @Transactional(readOnly = true)
    fun charmerInfo(uuid: String): CharmerInfoResponse {
        val charmer = charmerRepository.findByUuid(uuid) ?: throw IllegalArgumentException("존재하지 않는 charmer")
        return CharmerInfoResponse.from(charmer)
    }

    @Transactional(readOnly = true)
    fun charmerResults(uuid: String): CharmerResultResponse {
        val charmer = charmerRepository.findByUuid(uuid) ?: throw IllegalArgumentException("존재하지 않는 charmer")
        check(charmer.isFinished()) { "아직 결과를 볼 수 없습니다." }
        check(charmer.haveNoAnswer().not()) { "답변이 없습니다." }

        return charmer.questionFormAnswers.groupBy { it.questionForm }
            .map { (questionForm, answers) ->
                questionForm to answers.flatMap { it.questionOptions }.groupingBy { it }.eachCount()
            }
            .let { result ->
                val mainQuestions = result.map { (questionForm, values) -> to(questionForm, charmer, values) }
                val hex = mainQuestions.flatMap { it.answerResults }
                    .map { it.answerName }
                    .find { Color.contains(it) }?.let { Color.from(it).hex } ?: throw RuntimeException("존재하지 않는 색상")

                val subQuestions = result.map { (questionForm, values) -> to2(questionForm, charmer, values) }
                return CharmerResultResponse(
                    name = charmer.name,
                    hex = hex,
                    mainQuestions = mainQuestions,
                    subQuestions = subQuestions,
                )
            }
    }

    private fun to(
        questionForm: QuestionForm,
        charmer: Charmer,
        values: Map<QuestionOption, Int>,
    ): QuestionFormMainResultResponse =
        QuestionFormMainResultResponse(
            questionId = questionForm.id,
            questionTitle = questionForm.questionTitle(charmer.name),
            answerResults = values.toList()
                .sortedByDescending { (_, value) -> value }
                .take(questionForm.minAnswerCount)
                .map { (option, _) -> QuestionFormAnswerMainResultResponse.from(option) },
        )

    private fun to2(
        questionForm: QuestionForm,
        charmer: Charmer,
        values: Map<QuestionOption, Int>,
    ): QuestionFormSubResultResponse =
        QuestionFormSubResultResponse(
            questionId = questionForm.id,
            questionTitle = questionForm.questionTitle(charmer.name),
            answerResults = values.toList()
                .sortedByDescending { (_, value) -> value }
                .map { (option, count) -> QuestionFormAnswerSubResultResponse.of(option, count) },
        )
}
