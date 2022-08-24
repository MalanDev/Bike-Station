package lk.crud.bikestation.presentation.ui.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import lk.crud.bikestation.BikeStationApp
import lk.crud.bikestation.R
import lk.crud.bikestation.common.Constants
import lk.crud.bikestation.common.Helpers
import lk.crud.bikestation.databinding.ActivityBikeDetailBinding
import lk.crud.bikestation.domain.model.Feature
import kotlin.math.roundToInt


@AndroidEntryPoint
class BikeDetailActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityBikeDetailBinding
    private lateinit var feature: Feature

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBikeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val featureJson = intent.getStringExtra(Constants.PARAM_FEATURE)
        featureJson.let {
            val gson = Gson()
            feature =
                gson.fromJson(it, Feature::class.java)
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        configUI()
    }


    private fun configUI() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = feature.properties.label

        binding.includeItemBike.txtTitle.text = feature.properties.label
        binding.includeItemBike.txtAvailableBikes.text = feature.properties.bikes
        binding.includeItemBike.txtAvailablePlaces.text = feature.properties.bike_racks


        val dist = Helpers.distance(
            BikeStationApp.currentLat,
            BikeStationApp.currentLng,
            feature.geometry.coordinates[0],
            feature.geometry.coordinates[1]
        ).roundToInt()
        binding.includeItemBike.txtDistance.text = getString(R.string.bike_station, dist)


    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val location = LatLng(feature.geometry.coordinates[0], feature.geometry.coordinates[1])
        val marker = MarkerOptions().position(location).title(feature.properties.label)
            .icon(Helpers.bitmapFromVector(this,R.drawable.ic_bike))
        mMap.addMarker(marker)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
        mMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                location, 14.0f
            )
        )
        mMap.uiSettings.isZoomControlsEnabled = true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

}