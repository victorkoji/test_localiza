package com.api.test_localiza.dto.pointInterest

import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

class UpdatePointInterestDTO (
    val id: Int,

    @field:NotEmpty
    val nomePOI: String,

    @field:NotNull
    @field:Min(value = 0)
    val raio: Int,

    @field:NotNull
    val latitude: Double,

    @field:NotNull
    val longitude: Double
)