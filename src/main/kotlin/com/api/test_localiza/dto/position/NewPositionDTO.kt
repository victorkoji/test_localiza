package com.api.test_localiza.dto.position

import java.time.LocalDateTime
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class NewPositionDTO (
    @field:NotEmpty
    val placa: String,
    @field:NotNull
    val dataPosicao: LocalDateTime,
    @field:Min(value = 0)
    val velocidade: Int,
    val longitude: Double,
    val latitude: Double,
    val ignicao: Boolean = false
)