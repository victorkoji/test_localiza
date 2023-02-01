package com.api.test_localiza.services

import com.api.test_localiza.components.NavigationComponent
import com.api.test_localiza.components.TimeComponent
import com.api.test_localiza.dto.pointInterest.*
import com.api.test_localiza.exceptions.AlreadyExistException
import com.api.test_localiza.exceptions.NotFoundException
import com.api.test_localiza.mapper.pointInterest.NewPointInterestDTOMapper
import com.api.test_localiza.mapper.pointInterest.PointInterestViewMapper
import com.api.test_localiza.repository.PointInterestRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody


@Service
class PointInterestService (
    private val repository: PointInterestRepository,
    private val navigation: NavigationComponent,
    private val pointInterestViewMapper: PointInterestViewMapper,
    private val newPointInterestDTOMapper: NewPointInterestDTOMapper,
    private val timeComponent: TimeComponent
) {
    fun findAll(
        pageable: Pageable
    ): Page<PointInterestView>{

        val pointInterestList = repository.findAll(pageable)

        return pointInterestList.map {
            poi -> pointInterestViewMapper.map(poi)
        }
    }

    fun findById(id: Int): PointInterestView {
        val pointInterest = repository.findById(id).orElseThrow {
            NotFoundException()
        }

        return pointInterestViewMapper.map(pointInterest)
    }

    fun insert(newPointInterestDTO: NewPointInterestDTO): PointInterestView {
        if(repository.findByNomePOI(newPointInterestDTO.nomePOI) !== null)
            throw AlreadyExistException()

        val newPointInterest = newPointInterestDTOMapper.map(newPointInterestDTO)
        repository.save(newPointInterest)

        return pointInterestViewMapper.map(newPointInterest)
    }

    fun update(@RequestBody form: UpdatePointInterestDTO): PointInterestView {
        val pointInterest = repository.findById(form.id).orElseThrow{NotFoundException()}

        pointInterest.nomePOI = form.nomePOI
        pointInterest.raio = form.raio
        pointInterest.latitude = form.latitude
        pointInterest.longitude = form.longitude

        return pointInterestViewMapper.map(pointInterest)
    }

    fun delete(id: Int): PointInterestView {
        val pointInterest = repository.findById(id).orElseThrow{NotFoundException()}
        repository.delete(pointInterest)

        return pointInterestViewMapper.map(pointInterest)
    }
}