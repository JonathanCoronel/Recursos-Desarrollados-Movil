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

class Formulario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_formulario)
        regresar()
        layoutform()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btn_formulario)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }


    fun regresar(){
        val btnregresa = findViewById<Button>(R.id.btn_reg)
        btnregresa.setOnClickListener(){
            val volverVentana: Intent = Intent(this, MainActivity::class.java)
            startActivity(volverVentana)

        }
    }


    fun layoutform(){
        val btn_guardar = findViewById<Button>(R.id.btn_guardar)
        val tv_nombre = findViewById<TextView>(R.id.txt_nombre)
        val tv_apellido = findViewById<TextView>(R.id.txt_apellido)

        btn_guardar.setOnClickListener(){
            val mensaje = "Nombres: ${tv_nombre.text} ${tv_apellido.text}, Es estudiante"
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        }
    }
}