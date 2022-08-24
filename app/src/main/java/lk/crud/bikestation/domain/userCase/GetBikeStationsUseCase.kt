package lk.crud.bikestation.domain.userCase

import kotlinx.coroutines.flow.flow
import lk.crud.bikestation.common.Resource
import lk.crud.bikestation.data.remote.entity.BikeStationEntity
import lk.crud.bikestation.domain.model.BikeStation
import lk.crud.bikestation.domain.repository.BikeRepository
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.Flow
import javax.inject.Inject

class GetBikeStationsUseCase @Inject constructor(
    private val repository: BikeRepository
) {
    operator fun invoke() = flow {
        try {
            emit(Resource.Loading())
            val bikeStation = repository.getBikeStation()
            emit(Resource.Success(bikeStation))
        }catch (ex: HttpException){
            emit(Resource.Error(ex.localizedMessage?:"An unexpected error occured!"))
        }catch (ex:IOException){

            emit(Resource.Error(ex.localizedMessage?:"Couldn't reach server. Check your internet connect"))
        }
    }
}