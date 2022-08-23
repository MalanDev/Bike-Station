package lk.crud.bikestation.presentation.ui.main

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import lk.crud.bikestation.R
import lk.crud.bikestation.common.Resource
import lk.crud.bikestation.databinding.ActivityHomeBinding

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var viewBinding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)



        onConfigObserver()
    }

    private fun onConfigObserver() {
        homeViewModel.bikeStationState.observe(this, Observer {
           it.let {

               viewBinding.progressBar.visibility =if(it.isLoading) View.VISIBLE else View.GONE



           }
        })


    }
}