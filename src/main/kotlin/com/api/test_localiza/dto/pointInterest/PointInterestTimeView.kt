package com.api.test_localiza.dto.pointInterest

import com.api.test_localiza.dto.TimeView

data class PointInterestTimeView (
    val id: Int? = null,
    val nomePOI: String,
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
    val tempoNoPOI: TimeView
)