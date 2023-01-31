package com.api.test_localiza.dto.position

import java.time.LocalDateTime

data class PositionView (
    var id: Int?,
    var placa: String,
    var dataPosicao: LocalDateTime,
    var velocidade: Int,
    var longitude: Double,
    var latitude: Double,
    var ignicao: Boolean
)