package com.api.test_localiza.dto.position

import java.time.LocalDateTime
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class UpdatePositionDTO (
    var id: Int,
    @field:NotEmpty
    var placa: String,
    @field:NotNull
    var dataPosicao: LocalDateTime,
    @field:Min(value = 0)
    var velocidade: Int,
    var longitude: Double,
    var latitude: Double,
    var ignicao: Boolean = false
)