package com.api.test_localiza.mapper.pointInterest

import com.api.test_localiza.dto.pointInterest.NewPointInterestDTO
import com.api.test_localiza.mapper.Mapper
import com.api.test_localiza.models.PointInterest
import org.springframework.stereotype.Component

@Component
class NewPointInterestDTOMapper: Mapper<NewPointInterestDTO, PointInterest> {
    override fun map(obj: NewPointInterestDTO): PointInterest {
        return PointInterest(
            nomePOI = obj.nomePOI,
            raio= obj.raio,
            latitude= obj.latitude,
            longitude = obj.longitude
        )
    }

}