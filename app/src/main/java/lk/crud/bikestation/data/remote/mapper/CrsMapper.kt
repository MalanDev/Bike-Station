package lk.crud.bikestation.data.remote.mapper

import lk.crud.bikestation.data.remote.entity.CrsEntity
import lk.crud.bikestation.domain.model.Crs

class CrsMapper {

    // convert entity model to model
    fun invoke(it: CrsEntity): Crs {
        val crsPropertyMapper = CrsPropertyMapper()
        return Crs(
            type = it.type,
            properties = crsPropertyMapper.invoke(it.properties)

        )
    }
}