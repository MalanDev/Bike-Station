package lk.crud.bikestation.presentation.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import lk.crud.bikestation.presentation.ui.main.HomeActivity
import lk.crud.bikestation.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startActivity(Intent(this, HomeActivity::class.java))
    }
}