package com.api.test_localiza.models

import io.swagger.annotations.ApiModelProperty
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Entity
data class PointInterest(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    @ApiModelProperty(notes = "Nome do ponto de interesse", example = "PONTO 1")
    var nomePOI: String,
    @ApiModelProperty(notes = "Raio em metros", example = "1")
    var raio: Int,
    var latitude: Double,
    var longitude: Double
)