package com.api.test_localiza.dto.pointInterest

import com.api.test_localiza.dto.TimeView
import java.time.LocalDateTime

data class VehicleTimePoiView (
    val placa: String,
    val dataUltimaPosicao: LocalDateTime,
    val tempoNoPOI: TimeView
)