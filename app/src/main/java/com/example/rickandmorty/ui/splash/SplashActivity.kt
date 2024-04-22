package com.example.rickandmorty.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmorty.databinding.ActivitySplashBinding
import com.example.rickandmorty.ui.characterlist.CharacterListActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    companion object {
        private const val animationDuration: Long = 750
        private const val launchViewAfterTime: Long = 1500
        private const val flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        startAnimation()
    }

    private fun setupView() {}

    private fun startAnimation() {
        Handler(Looper.getMainLooper()).postDelayed({
            showMainView()
        }, launchViewAfterTime)
    }

    private fun showMainView() {
        val intent = Intent(this@SplashActivity, CharacterListActivity::class.java)
        intent.flags = flags
        startActivity(intent)
    }
}