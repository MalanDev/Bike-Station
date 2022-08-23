package lk.crud.bikestation.data.remote.mapper

import lk.crud.bikestation.data.remote.entity.*
import lk.crud.bikestation.domain.model.*
import okhttp3.Interceptor.Companion.invoke

class BikeStationMapper {

    // convert entity model to model
    fun invoke(it: BikeStationEntity): BikeStation {
        val crsMapper = CrsMapper()
        val featureMapper = FeatureMapper()
        return BikeStation(
            crs = crsMapper.invoke(it.crs),
            type = it.type,
            features = it.features.map { featureEntity -> featureMapper.invoke(featureEntity) }

        )
    }

}