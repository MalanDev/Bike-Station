package lk.crud.bikestation.data.remote.entity

data class BikeStationEntity(
    val crs: CrsEntity,
    val features: List<FeatureEntity>,
    val type: String
)