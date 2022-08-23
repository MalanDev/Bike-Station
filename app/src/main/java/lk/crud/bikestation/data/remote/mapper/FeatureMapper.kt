package lk.crud.bikestation.data.remote.mapper

import lk.crud.bikestation.data.remote.entity.FeatureEntity
import lk.crud.bikestation.domain.model.Feature

class FeatureMapper {

    // convert entity model to model
    fun invoke(it: FeatureEntity): Feature {
        val geometryMapper = GeometryMapper()
        val propertyMapper = PropertiesMapper()
        return Feature(
            geometry = geometryMapper.invoke(it.geometry),
            id = it.id,
            properties = propertyMapper.invoke(it.properties),
            type = it.type
        )
    }
}