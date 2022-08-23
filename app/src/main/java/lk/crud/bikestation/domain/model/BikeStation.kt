package lk.crud.bikestation.domain.model

data class BikeStation(
    val crs: Crs,
    val features: List<Feature>,
    val type: String
)