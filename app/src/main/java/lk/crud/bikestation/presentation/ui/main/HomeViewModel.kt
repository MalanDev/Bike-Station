package lk.crud.bikestation.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import lk.crud.bikestation.common.Resource
import lk.crud.bikestation.domain.userCase.GetBikeStationsUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBikeStationsUseCase: GetBikeStationsUseCase
) : ViewModel() {

    private val _bikeStationState = MutableLiveData(BikeStationState())
    val bikeStationState: LiveData<BikeStationState> = _bikeStationState


     fun getBikeStations() {
        getBikeStationsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    result.data?.let {
                        _bikeStationState.value = BikeStationState(bikeStation = it)
                    }

                }
                is Resource.Loading -> {
                    _bikeStationState.value = BikeStationState(isLoading = true)
                }
                is Resource.Error -> {
                    _bikeStationState.value =
                        BikeStationState(error = result.message ?: "An unexpected error occured")
                }
            }
        }.launchIn(viewModelScope)
    }
}