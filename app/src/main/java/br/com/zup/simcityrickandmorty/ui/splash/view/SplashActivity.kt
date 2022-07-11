package br.com.zup.simcityrickandmorty.ui.splash.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.zup.simcityrickandmorty.databinding.ActivitySplashBinding
import br.com.zup.simcityrickandmorty.ui.home.view.HomeActivity
import java.util.*

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private val timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        timer.schedule(object:TimerTask(){
            override fun run() {
                jump()
            }
        }, 3000)
    }
    private fun jump(){
        timer.cancel()
        startActivity(Intent(this, HomeActivity::class.java))
        this.finish()
    }
}