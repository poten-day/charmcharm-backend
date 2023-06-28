package com.charm.charm.presentation

import com.charm.charm.application.QuestionAnswerService
import com.charm.charm.dto.request.QuestionFormAnswerRequest
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api(tags = ["Question Form Answer"])
@RestController
@RequestMapping("/api")
class QuestionFormAnswerController(
    private val questionFormAnswerService: QuestionAnswerService,
) {
    @ApiOperation("질문지 답변 저장")
    @PostMapping("/answers/{uuid}")
    fun saveQuestionFormAnswers(@PathVariable uuid: String, @RequestBody request: List<QuestionFormAnswerRequest>) {
        questionFormAnswerService.saveQuestionFormAnswers(uuid, request)
    }
}
