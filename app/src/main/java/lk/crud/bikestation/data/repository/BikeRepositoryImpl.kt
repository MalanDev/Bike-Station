package lk.crud.bikestation.data.repository

import lk.crud.bikestation.data.remote.BikeStationApi
import lk.crud.bikestation.data.remote.mapper.BikeStationMapper
import lk.crud.bikestation.domain.model.BikeStation
import lk.crud.bikestation.domain.model.Feature
import lk.crud.bikestation.domain.repository.BikeRepository
import javax.inject.Inject

class BikeRepositoryImpl @Inject constructor(private val api: BikeStationApi,private val bikeStationMapper: BikeStationMapper) :BikeRepository{
    //    override suspend fun getBikeStation(): BikeStation {
//        return api.getBikeStation().let { bikeStationMapper.invoke(it) }
//    }
    override suspend fun getBikeStation(): BikeStation {
        TODO("Not yet implemented")
    }


}