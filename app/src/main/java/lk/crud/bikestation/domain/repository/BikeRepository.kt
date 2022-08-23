package lk.crud.bikestation.domain.repository

import lk.crud.bikestation.domain.model.BikeStation

interface BikeRepository {

    suspend fun getBikeStation():BikeStation
}