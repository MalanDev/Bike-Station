package lk.crud.bikestation.presentation.ui.main

import androidx.compose.runtime.State
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import lk.crud.bikestation.common.Resource
import lk.crud.bikestation.domain.userCase.GetBikeStationsUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getBikeStationsUseCase: GetBikeStationsUseCase
):ViewModel() {

    private val _bikeStationState = MutableLiveData(BikeStationState())
    val bikeStationState: LiveData<BikeStationState> = _bikeStationState


    init {
        getBikeStations()
    }

    private fun getBikeStations() {
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