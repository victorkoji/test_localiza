package com.api.test_localiza.mapper.pointInterest

import com.api.test_localiza.dto.pointInterest.PointInterestView
import com.api.test_localiza.mapper.Mapper
import com.api.test_localiza.models.PointInterest
import org.springframework.stereotype.Component

@Component
class PointInterestViewMapper: Mapper<PointInterest, PointInterestView> {
    override fun map(obj: PointInterest): PointInterestView {
        return PointInterestView(
            id = obj.id,
            nomePOI = obj.nomePOI,
            raio= obj.raio,
            latitude= obj.latitude,
            longitude= obj.longitude
        )
    }

}