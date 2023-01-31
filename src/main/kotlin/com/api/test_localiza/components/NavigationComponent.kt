package com.api.test_localiza.components

import org.springframework.stereotype.Component
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

@Component
class NavigationComponent(private val earthRay: Int = 6371) {

    fun calculateDistanceMeters(lat1: Double, long1: Double, lat2: Double, long2: Double): Double{
        val lat1Cos = cos(Math.toRadians(lat1))
        val lat2Cos = cos(Math.toRadians(lat2))
        val longCos = cos(Math.toRadians(long1) - Math.toRadians(long2))
        val lat1Sin = sin(Math.toRadians(lat1))
        val lat2Sin = sin(Math.toRadians(lat2))

        return earthRay *  acos(
            lat1Cos * lat2Cos * longCos + lat1Sin * lat2Sin
        ) * 1000
    }
}