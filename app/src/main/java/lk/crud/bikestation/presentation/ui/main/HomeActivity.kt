package lk.crud.bikestation.presentation.ui.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import lk.crud.bikestation.BikeStationApp
import lk.crud.bikestation.common.Constants
import lk.crud.bikestation.databinding.ActivityHomeBinding
import lk.crud.bikestation.presentation.ui.detail.BikeDetailActivity
import pub.devrel.easypermissions.AppSettingsDialog


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var viewBinding: ActivityHomeBinding
    private lateinit var bikeStationAdapter: BikeStationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            configPermission()
        }

        configUI()
        configObserver()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun configPermission() {
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    // Only approximate location access granted.
                    getLocation()
                    homeViewModel.getBikeStations()
                }
                else -> {
                    // No location access granted.
                    AppSettingsDialog.Builder(this).build().show()
                }
            }
        }


        // Before you perform the actual permission request, check whether your app
        // already has the permissions, and whether your app needs to show a permission
        // rationale dialog. For more details, see Request permissions.
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    private fun configUI() {


        viewBinding.rvBikes.layoutManager = LinearLayoutManager(this)
        bikeStationAdapter = BikeStationAdapter { feature ->
            val gson = Gson()
            val featureJson = gson.toJson(feature)
            Intent(this, BikeDetailActivity::class.java).also {
                it.putExtra(Constants.PARAM_FEATURE, featureJson)
                startActivity(it)
            }

        }
        viewBinding.rvBikes.adapter = bikeStationAdapter
    }


    @SuppressLint("MissingPermission")
    private fun getLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            BikeStationApp.currentLat = location.latitude
            BikeStationApp.currentLng = location.longitude
        }

    }

    private fun configObserver() {
        homeViewModel.bikeStationState.observe(this, Observer {
            handleBikeStation(it)
        })


    }

    private fun handleBikeStation(bikeStationState: BikeStationState) {

        viewBinding.progressBar.visibility =
            if (bikeStationState.isLoading) View.VISIBLE else View.GONE

        bikeStationAdapter.submitList(bikeStationState.bikeStation.features)

    }
}