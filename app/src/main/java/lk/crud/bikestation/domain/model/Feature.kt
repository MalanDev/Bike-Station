package lk.crud.bikestation.domain.model

data class Feature (
    val geometry: Geometry,
    val id: String,
    val properties: Properties,
    val type: String
)