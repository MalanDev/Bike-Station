package lk.crud.bikestation.data.remote.entity

data class FeatureEntity(
    val geometry: GeometryEntity,
    val id: String,
    val properties: PropertiesEntity,
    val type: String
)