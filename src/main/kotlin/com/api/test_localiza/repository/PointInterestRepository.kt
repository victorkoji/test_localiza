package com.api.test_localiza.repository

import com.api.test_localiza.models.PointInterest
import org.springframework.data.jpa.repository.JpaRepository


interface PointInterestRepository: JpaRepository<PointInterest, Int> {
    fun findAllByNomePOI(namePOI: String): List<PointInterest>

    fun findByNomePOI(namePOI: String): PointInterest?
}