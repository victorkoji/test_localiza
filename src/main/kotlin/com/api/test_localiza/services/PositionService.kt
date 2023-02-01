package com.api.test_localiza.services

import com.api.test_localiza.components.NavigationComponent
import com.api.test_localiza.components.TimeComponent
import com.api.test_localiza.dto.position.*
import com.api.test_localiza.dto.pointInterest.PointInterestTimeView
import com.api.test_localiza.dto.vehicle.VehicleTimeView
import com.api.test_localiza.exceptions.NotFoundException
import com.api.test_localiza.mapper.position.NewPositionDTOMapper
import com.api.test_localiza.mapper.position.PositionViewMapper
import com.api.test_localiza.repository.PointInterestRepository
import com.api.test_localiza.repository.PositionRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class PositionService(
    private val repository: PositionRepository,
    private val pointInterestRepository: PointInterestRepository,
    private val positionViewMapper: PositionViewMapper,
    private val newPositionDTOMapper: NewPositionDTOMapper,
    private val navigation: NavigationComponent,
    private val timeComponent: TimeComponent
) {
    fun findAll(pageable: Pageable): Page<PositionView>{
        val positionList = repository.findAll(pageable)

        return positionList.map {
                poi -> positionViewMapper.map(poi)
        }
    }

    fun findById(id: Int): PositionView {
        val position = repository.findById(id).orElseThrow {
            NotFoundException()
        }

        return positionViewMapper.map(position)
    }

    fun insert(newPosition: NewPositionDTO): PositionView {
        val position = newPositionDTOMapper.map(newPosition)
        repository.save(position)

        return positionViewMapper.map(position)
    }

    fun update(form: UpdatePositionDTO): PositionView {
        val position = repository.findById(form.id).orElseThrow{NotFoundException()}

        position.dataPosicao = form.dataPosicao
        position.placa = form.placa
        position.latitude = form.latitude
        position.longitude = form.longitude
        position.velocidade = form.velocidade
        position.ignicao = form.ignicao

        return positionViewMapper.map(position)
    }

    fun delete(id: Int): PositionView {
        val position = repository.findById(id).orElseThrow{NotFoundException()}
        repository.delete(position)

        return positionViewMapper.map(position)
    }

    fun getVehicleTimeInPoi(
        licensePlate: String?,
        startDate: String?,
        endDate: String?,
    ) : List<VehicleTimeView>{
        val pointInterestList = pointInterestRepository.findAll(
            Sort.by( Sort.Order.asc("id") )
        )
        val sort: Sort = Sort.by(
            Sort.Order.asc("placa"),
            Sort.Order.asc("dataPosicao")
        )
        val positionList = if(licensePlate !== null && startDate !== null && endDate !== null){
            repository.findAllByDataAndPlaca(
                licensePlate,
                startDate,
                endDate,
            )
        } else if(licensePlate !== null){
            repository.findAllByPlaca(
                licensePlate,
                sort
            )
        } else if(startDate !== null && endDate != null) {
            repository.findAllByData(
                startDate,
                endDate,
            )
        } else {
            repository.findAll(sort)
        }

        val vehicleTimeMap = hashMapOf<String, VehicleTimeView>()
        val pointInterestTimeList = hashMapOf<String, PointInterestTimeView>()

        positionList.forEach {
            p -> run {
                val pointInterestListFiltered = pointInterestList.stream().filter{
                    poi -> navigation.calculateDistanceMeters(
                        poi.longitude,
                        poi.latitude,
                        p.latitude,
                        p.longitude
                    ) <= poi.raio
                }

                pointInterestListFiltered.forEach {
                    poi -> run {
                        val vehicleTimeFounded = vehicleTimeMap[p.placa]
                        val pointInterestTimeFounded = pointInterestTimeList["${p.placa} ${poi.nomePOI}"]
                        var totalTimePOI = timeComponent.secondsToTime(0)

                        if(pointInterestTimeFounded !== null && vehicleTimeFounded !== null){
                            val secondsDiff =
                                Duration.between(vehicleTimeFounded.dataUltimaPosicao, p.dataPosicao).getSeconds()

                            val totalSeconds = timeComponent.timeToSeconds(
                                pointInterestTimeFounded.tempoNoPOI.hours,
                                pointInterestTimeFounded.tempoNoPOI.minutes,
                                pointInterestTimeFounded.tempoNoPOI.seconds
                            ) + secondsDiff

                            totalTimePOI = timeComponent.secondsToTime(totalSeconds)
                        }

                        pointInterestTimeList["${p.placa} ${poi.nomePOI}"] = PointInterestTimeView(
                            id = poi.id,
                            nomePOI = poi.nomePOI,
                            latitude = poi.latitude,
                            longitude = poi.longitude,
                            tempoNoPOI = totalTimePOI,
                        )
                    }
                }

                vehicleTimeMap[p.placa] = VehicleTimeView(
                    placa = p.placa,
                    dataUltimaPosicao = p.dataPosicao,
                    pontoInteresse= ArrayList(
                        pointInterestTimeList.filter {
                                poi -> poi.key.contains(p.placa)
                        }.values
                    )
                )
            }
        }

        return ArrayList(vehicleTimeMap.values)
    }
}