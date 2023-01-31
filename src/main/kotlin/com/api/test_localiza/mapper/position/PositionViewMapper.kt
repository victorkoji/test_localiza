package com.api.test_localiza.mapper.position

import com.api.test_localiza.dto.position.PositionView
import com.api.test_localiza.mapper.Mapper
import com.api.test_localiza.models.Position
import org.springframework.stereotype.Component

@Component
class PositionViewMapper: Mapper<Position, PositionView> {
    override fun map(obj: Position): PositionView {
        return PositionView(
            id= obj.id,
            placa = obj.placa,
            dataPosicao = obj.dataPosicao,
            velocidade= obj.velocidade,
            longitude= obj.longitude,
            latitude= obj.latitude,
            ignicao= obj.ignicao
        )
    }

}
