package com.jgvg.tpvjgvg.views.splashscreen

import android.R
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

import com.jgvg.tpvjgvg.views.main.MainActivity


class pantalla_principal : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.jgvg.tpvjgvg.R.layout.activity_pantalla_principal)
        val Tiempo = 5000
        Handler().postDelayed(Runnable {
            startActivity(Intent(this@pantalla_principal, MainActivity::class.java))
            finish()
        }, Tiempo.toLong())
    }
}