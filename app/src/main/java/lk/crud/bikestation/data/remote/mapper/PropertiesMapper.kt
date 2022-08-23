package lk.crud.bikestation.data.remote.mapper

import lk.crud.bikestation.data.remote.entity.PropertiesEntity
import lk.crud.bikestation.domain.model.Properties

class PropertiesMapper {
    // convert entity model to model
    fun invoke(it: PropertiesEntity): Properties {
        return Properties(
            bike_racks = it.bike_racks,
            bikes = it.bikes,
            free_racks = it.free_racks,
            label = it.label,
            updated = it.updated

        )
    }
}