package com.api.test_localiza.dto.pointInterest

data class PointInterestTimeViewTODO (
    val id: Int?,
    val nomePOI: String,
    val raio: Int,
    val latitude: Double,
    val longitude: Double,
    val veiculos: List<VehicleTimePoiView>
)