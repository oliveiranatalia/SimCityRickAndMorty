package br.com.zup.simcityrickandmorty.ui.splash.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import br.com.zup.simcityrickandmorty.databinding.ActivitySplashBinding
import br.com.zup.simcityrickandmorty.ui.characterslist.view.CharactersListActivity
import java.util.*

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private val timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.getWindowInsetsController(window.decorView)?.apply {
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            hide(WindowInsetsCompat.Type.systemBars())
        }

        timer.schedule(object:TimerTask(){
            override fun run() {
                jump()
            }
        }, 3000)
    }
    private fun jump(){
        timer.cancel()
        startActivity(Intent(this, CharactersListActivity::class.java))
        this.finish()
    }
}