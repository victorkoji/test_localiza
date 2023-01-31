package com.api.test_localiza.mapper.position

import com.api.test_localiza.dto.position.NewPositionDTO
import com.api.test_localiza.mapper.Mapper
import com.api.test_localiza.models.Position
import org.springframework.stereotype.Component

@Component
class NewPositionDTOMapper: Mapper<NewPositionDTO, Position> {

    override fun map(obj: NewPositionDTO): Position {
        return Position(
            placa = obj.placa,
            dataPosicao= obj.dataPosicao,
            velocidade= obj.velocidade,
            longitude = obj.longitude,
            latitude = obj.latitude,
            ignicao = obj.ignicao
        )
    }
}
