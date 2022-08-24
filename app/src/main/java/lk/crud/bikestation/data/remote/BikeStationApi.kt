package lk.crud.bikestation.data.remote

import lk.crud.bikestation.data.remote.entity.BikeStationEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface BikeStationApi {

    @GET("mim/plan/map_service.html")
    suspend fun getBikeStation(@Query("mtype")mType:String,@Query("co")co:String): BikeStationEntity
}