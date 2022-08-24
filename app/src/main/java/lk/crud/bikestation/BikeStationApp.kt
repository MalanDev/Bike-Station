package lk.crud.bikestation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BikeStationApp: Application(){

    companion object{

        //keep relative center location in colombo as default
        var currentLat: Double = 6.958598
        var currentLng: Double = 79.864789
    }
}