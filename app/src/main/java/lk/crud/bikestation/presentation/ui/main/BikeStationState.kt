package lk.crud.bikestation.presentation.ui.main

import lk.crud.bikestation.domain.model.BikeStation
import lk.crud.bikestation.domain.model.Crs
import lk.crud.bikestation.domain.model.CrsProperties

data class BikeStationState(
    val isLoading: Boolean = false,
    val bikeStation:BikeStation= BikeStation(crs = Crs(properties = CrsProperties(code = ""), type = ""), features = emptyList(), type = ""),
    val error: String = ""
)