package com.api.test_localiza.repository

import com.api.test_localiza.models.Position
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface PositionRepository: JpaRepository<Position, Int> {

    fun findAllByPlaca(status: String, sort: Sort?): List<Position>

    @Query(
        value = "SELECT * FROM position p WHERE cast(cast(p.data_posicao as date) as text) >= :startDate AND " +
                "cast(cast(p.data_posicao as date) as text) <= :endDate ",
        nativeQuery = true
    )
    fun findAllByData(@Param("startDate") startDate: String,@Param("endDate") endDate: String): List<Position>
}