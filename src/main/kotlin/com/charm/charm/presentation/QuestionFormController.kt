package com.charm.charm.presentation

import com.charm.charm.application.QuestionFormService
import com.charm.charm.dto.response.QuestionFormResponses
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api(tags = ["Question Form"])
@RestController
@RequestMapping("/api")
class QuestionFormController(
    private val questionFormService: QuestionFormService,
) {
    @ApiOperation("질문지 정보 조회")
    @GetMapping("/questions/{uuid}")
    fun findAllQuestionForms(@PathVariable uuid: String): QuestionFormResponses =
        questionFormService.findAllQuestionForms(uuid)
}
