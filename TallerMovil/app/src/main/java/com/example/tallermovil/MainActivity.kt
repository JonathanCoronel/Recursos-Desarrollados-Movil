package com.example.tallermovil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_TallerMovil)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        navegarFomrulario()
        navegarImagen()
        navegarGps()
        navegarCamara()
        navegarPodo()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btn_formulario)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun navegarFomrulario(){
        val btnform = findViewById<Button>(R.id.btn_1)
        btnform.setOnClickListener(){
            val saltarVentana: Intent = Intent(this, Formulario::class.java)
            startActivity(saltarVentana)
        }
    }


    fun navegarImagen(){
        val btnimg= findViewById<Button>(R.id.btn_img)
        btnimg.setOnClickListener(){
            val saltarVentana: Intent = Intent(this, Imagen::class.java)
            startActivity(saltarVentana)
        }
    }

    fun navegarCamara(){
        val btncam= findViewById<Button>(R.id.btn_camara)
        btncam.setOnClickListener(){
            val saltarVentana: Intent = Intent(this, Camara::class.java)
            startActivity(saltarVentana)
        }
    }

    fun navegarGps(){
        val btngps= findViewById<Button>(R.id.btn_gps)
        btngps.setOnClickListener(){
            val saltarVentana: Intent = Intent(this, GPS::class.java)
            startActivity(saltarVentana)
        }
    }

    fun navegarPodo(){
        val btnpod= findViewById<Button>(R.id.btn_podo)
        btnpod.setOnClickListener(){
            val saltarVentana: Intent = Intent(this, Podometro::class.java)
            startActivity(saltarVentana)
        }
    }

}