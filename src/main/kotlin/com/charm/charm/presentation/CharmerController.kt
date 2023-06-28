package com.charm.charm.presentation

import com.charm.charm.application.CharmerService
import com.charm.charm.dto.request.CharmerCreateRequest
import com.charm.charm.dto.response.CharmerCreateResponse
import com.charm.charm.dto.response.CharmerInfoResponse
import com.charm.charm.dto.response.CharmerResultResponse
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api(tags = ["Charmer"])
@RestController
@RequestMapping("/api")
class CharmerController(
    private val charmerService: CharmerService,
) {
    @ApiOperation("charmer 생성")
    @PostMapping("/charmers")
    fun signUpCharmer(@RequestBody charmerCreateRequest: CharmerCreateRequest): CharmerCreateResponse =
        charmerService.signUpCharmer(charmerCreateRequest)

    @ApiOperation("charmer 현재 정보 조회")
    @GetMapping("/charmers/{uuid}")
    fun charmerInfo(@PathVariable uuid: String): CharmerInfoResponse = charmerService.charmerInfo(uuid)

    @ApiOperation("charmer 결과 조회")
    @GetMapping("/charmers/{uuid}/results")
    fun charmResults(@PathVariable uuid: String): CharmerResultResponse = charmerService.charmerResults(uuid)
}
