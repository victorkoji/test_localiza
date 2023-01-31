package com.api.test_localiza.models

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Position (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    var placa: String,
    var dataPosicao: LocalDateTime,
    var velocidade: Int,
    var longitude: Double,
    var latitude: Double,
    var ignicao: Boolean
)