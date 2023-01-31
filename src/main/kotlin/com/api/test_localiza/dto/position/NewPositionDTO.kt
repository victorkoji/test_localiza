package com.api.test_localiza.dto.position

import java.time.LocalDateTime

data class NewPositionDTO (
    val placa: String,
    val dataPosicao: LocalDateTime,
    val velocidade: Int,
    val longitude: Double,
    val latitude: Double,
    val ignicao: Boolean
)