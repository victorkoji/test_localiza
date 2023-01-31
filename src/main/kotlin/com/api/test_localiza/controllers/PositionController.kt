package com.api.test_localiza.controllers

import com.api.test_localiza.dto.position.NewPositionDTO
import com.api.test_localiza.dto.position.PositionView
import com.api.test_localiza.dto.position.UpdatePositionDTO
import com.api.test_localiza.dto.vehicle.VehicleTimeView
import com.api.test_localiza.exceptions.AlreadyExistException
import com.api.test_localiza.services.PositionService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("position")
@Api(value="position", description="Operações relacionadas a posição do veículo")
class PositionController(private val service: PositionService) {

    @GetMapping
    @ApiOperation(value = "Busca todas as posições dos veículos")
    fun findAll(pageable: Pageable): Page<PositionView> {
        return service.findAll(pageable)
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Busca uma posição do veículo por ID")
    fun findById(@PathVariable id: Int): PositionView {
        return service.findById(id)
    }

    @PostMapping
    @Transactional
    @ApiOperation(value = "Insere uma nova posição")
    fun insert(
        @RequestBody newPosition: NewPositionDTO,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<PositionView> {
        val position = service.insert(newPosition)
        val uri = uriBuilder.path("/point-interest/${position.id}").build().toUri()

        return ResponseEntity.created(uri).body(position)
    }

    @PutMapping()
    @Transactional
    @ApiOperation(value = "Atualiza uma posição já existente")
    fun update(@RequestBody position: UpdatePositionDTO): ResponseEntity<PositionView> {
        val positionView = service.update(position)
        return ResponseEntity.ok(positionView)
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ApiOperation(value = "Deleta uma posição do veículo")
    fun delete(@PathVariable id: Int): ResponseEntity<PositionView> {
        val positionView = service.delete(id)
        return ResponseEntity.ok(positionView)
    }

    @GetMapping("/time-vehicle")
    @ApiOperation(value = "Busca todos os veículos com a quantidade de tempo por ponto de interesse")
    fun getVehicleTimeInPoi(
        @RequestParam licensePlate: String?,
        @RequestParam startDate: String?,
        @RequestParam endDate: String?,
    ): List<VehicleTimeView>{
        return service.getVehicleTimeInPoi(licensePlate, startDate, endDate)
    }
}