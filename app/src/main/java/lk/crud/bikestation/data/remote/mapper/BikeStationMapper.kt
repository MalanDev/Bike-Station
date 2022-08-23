package lk.crud.bikestation.data.remote.mapper

import lk.crud.bikestation.data.remote.entity.*
import lk.crud.bikestation.domain.model.*
import okhttp3.Interceptor.Companion.invoke
import javax.inject.Inject

class BikeStationMapper @Inject constructor(
    private val crsMapper: CrsMapper,
    private val featureMapper: FeatureMapper
) {

    // convert entity model to model
    fun invoke(it: BikeStationEntity): BikeStation {

        return BikeStation(
            crs = crsMapper.invoke(it.crs),
            type = it.type,
            features = it.features.map { featureEntity -> featureMapper.invoke(featureEntity) }

        )
    }

}