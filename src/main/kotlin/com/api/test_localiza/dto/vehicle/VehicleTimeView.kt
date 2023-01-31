package com.api.test_localiza.dto.vehicle

import com.api.test_localiza.dto.pointInterest.PointInterestTimeView
import java.time.LocalDateTime

data class VehicleTimeView (
    val placa: String,
    val dataUltimaPosicao: LocalDateTime,
    val pontoInteresse: List<PointInterestTimeView>
)