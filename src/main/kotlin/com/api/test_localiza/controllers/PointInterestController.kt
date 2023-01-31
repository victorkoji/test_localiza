package com.api.test_localiza.controllers

import com.api.test_localiza.dto.pointInterest.NewPointInterestDTO
import com.api.test_localiza.dto.pointInterest.PointInterestView
import com.api.test_localiza.dto.pointInterest.UpdatePointInterestDTO
import com.api.test_localiza.exceptions.AlreadyExistException
import com.api.test_localiza.services.PointInterestService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/point-interest")
@Api(value="point-interest", description="Operações relacionadas ao ponto de interesse")
class PointInterestController(
    private val service: PointInterestService,
) {

    @GetMapping
    @ApiOperation(value = "Busca todos pontos de interesse")
    fun findAll(
        @PageableDefault(sort = ["id"], direction = Sort.Direction.ASC, size=30) pageable: Pageable
    ): Page<PointInterestView>{
        return service.findAll(pageable)
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Busca um ponto de interesse por ID")
    fun findById(@PathVariable id: Int): PointInterestView {
        return service.findById(id)
    }

    @PostMapping
    @Transactional
    @ApiOperation(value = "Insere um novo ponto de interesse")
    fun insert(
        @RequestBody pointInterest: NewPointInterestDTO,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<PointInterestView> {
        val pointInterestView = service.insert(pointInterest)
        val uri = uriBuilder.path("/point-interest/${pointInterestView.id}").build().toUri()

        return ResponseEntity.created(uri).body(pointInterestView)
    }

    @PutMapping()
    @Transactional
    @ApiOperation(value = "Atualiza um ponto de interesse já existente")
    fun update(@RequestBody pointInterest: UpdatePointInterestDTO): ResponseEntity<PointInterestView> {
        val pointInterestView = service.update(pointInterest)
        return ResponseEntity.ok(pointInterestView)
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ApiOperation(value = "Deleta um ponto de interesse")
    fun delete(@PathVariable id: Int): ResponseEntity<PointInterestView> {
        val pointInterestView = service.delete(id)
        return ResponseEntity.ok(pointInterestView)
    }
}