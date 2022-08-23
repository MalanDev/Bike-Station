package lk.crud.bikestation.data.remote.mapper
import lk.crud.bikestation.data.remote.entity.CrsPropertiesEntity
import lk.crud.bikestation.domain.model.CrsProperties

class CrsPropertyMapper {

    // convert entity model to model
    fun invoke(it: CrsPropertiesEntity): CrsProperties {
        return CrsProperties(
            code = it.code
        )
    }

}