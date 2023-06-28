package com.charm.charm.application

import com.charm.charm.domain.charmer.CharmerRepository
import com.charm.charm.domain.form.QuestionFormRepository
import com.charm.charm.dto.response.QuestionFormResponse
import com.charm.charm.dto.response.QuestionFormResponses
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalArgumentException

@Service
class QuestionFormService(
    private val questionFormRepository: QuestionFormRepository,
    private val charmerRepository: CharmerRepository,
) {
    @Transactional(readOnly = true)
    fun findAllQuestionForms(uuid: String): QuestionFormResponses {
        val charmer = charmerRepository.findByUuid(uuid) ?: throw IllegalArgumentException("존재하지 않는 charmer")
        val list = questionFormRepository.findAll()
        val questionFormResponses = list
            .map { QuestionFormResponse.of(it, charmer) }

        return QuestionFormResponses(name = charmer.name, questionForms = questionFormResponses)
    }
}
