package lk.crud.bikestation.data.remote.mapper

import lk.crud.bikestation.data.remote.entity.FeatureEntity
import lk.crud.bikestation.domain.model.Feature
import javax.inject.Inject

class FeatureMapper @Inject constructor(
    private val geometryMapper: GeometryMapper,
    private val propertyMapper: PropertiesMapper
) {

    // convert entity model to model
    fun invoke(it: FeatureEntity): Feature {

        return Feature(
            geometry = geometryMapper.invoke(it.geometry),
            id = it.id,
            properties = propertyMapper.invoke(it.properties),
            type = it.type
        )
    }
}