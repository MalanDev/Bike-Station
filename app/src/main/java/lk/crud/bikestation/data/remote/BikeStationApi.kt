package lk.crud.bikestation.data.remote

import lk.crud.bikestation.data.remote.entity.BikeStationEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface BikeStationApi {

    @GET("/v2/everything")
    suspend fun getBikeStation(): BikeStationEntity
}