package com.api.test_localiza.controllers

import com.api.test_localiza.dto.HealthCheckView
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/health-check")
@Api(value="health-check")
class HealthCheck {
    @GetMapping
    @ApiOperation(value = "Verifica se o sistema está no ar")
    fun healthCheck(): HealthCheckView {
        return HealthCheckView(status="UP")
    }
}