package com.api.test_localiza.dto.pointInterest

data class PointInterestView (
    val id: Int? = null,
    val nomePOI: String,
    val raio: Int,
    val latitude: Double,
    val longitude: Double
)