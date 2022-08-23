package lk.crud.bikestation.data.remote.mapper

import lk.crud.bikestation.data.remote.entity.GeometryEntity
import lk.crud.bikestation.domain.model.Geometry

class GeometryMapper {
    // convert entity model to model
    fun invoke(it: GeometryEntity): Geometry {
        return Geometry(
            type = it.type,
            coordinates = it.coordinates

        )
    }
}