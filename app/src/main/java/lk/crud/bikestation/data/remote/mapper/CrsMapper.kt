package lk.crud.bikestation.data.remote.mapper

import lk.crud.bikestation.data.remote.entity.CrsEntity
import lk.crud.bikestation.domain.model.Crs
import javax.inject.Inject

class CrsMapper @Inject constructor(private val crsPropertyMapper: CrsPropertyMapper) {

    // convert entity model to model
    fun invoke(it: CrsEntity): Crs {

        return Crs(
            type = it.type,
            properties = crsPropertyMapper.invoke(it.properties)

        )
    }
}