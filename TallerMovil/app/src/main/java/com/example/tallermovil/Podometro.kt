package com.example.tallermovil

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Podometro : AppCompatActivity(), SensorEventListener {

    private var sensorManager: SensorManager? = null
    private var running = false
    private var totalSteps = 0f
    private var previousTotalSteps = 0f
    private lateinit var tvStepsTaken: TextView
    private lateinit var btnStartPod: Button
    private lateinit var btnStopPod: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_podometro)

        // Inicializar vistas
        tvStepsTaken = findViewById(R.id.tv_stepsTaken)
        btnStartPod = findViewById<Button>(R.id.btn_startpod)
        btnStopPod = findViewById<Button>(R.id.btn_stoppod)

        loadData()
        resetSteps()

        // Configuración del SensorManager
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        // Solicitar permiso si es necesario (Android 10 o superior)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACTIVITY_RECOGNITION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.ACTIVITY_RECOGNITION),
                    101
                )
            }
        }

        // Configurar acciones de los botones
        btnStartPod.setOnClickListener {
            startPodometer()
        }

        btnStopPod.setOnClickListener {
            stopPodometer()
        }
    }

    private fun startPodometer() {
        if (!running) {
            running = true
            val stepSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
            if (stepSensor == null) {
                Toast.makeText(this, "No sensor detected on this device", Toast.LENGTH_SHORT).show()
            } else {
                sensorManager?.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI)
                Toast.makeText(this, "Podómetro iniciado", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "El podómetro ya está funcionando", Toast.LENGTH_SHORT).show()
        }
    }

    private fun stopPodometer() {
        if (running) {
            running = false
            sensorManager?.unregisterListener(this)
            Toast.makeText(this, "Podómetro detenido", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "El podómetro ya está detenido", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.values != null && event.values.isNotEmpty() && running) {
            totalSteps = event.values[0]
            val currentSteps = totalSteps.toInt() - previousTotalSteps.toInt()

            // Actualizar la interfaz de usuario
            tvStepsTaken.text = currentSteps.toString()
        }
    }

    fun resetSteps() {
        tvStepsTaken.setOnClickListener {
            Toast.makeText(this, "Long tap to reset steps", Toast.LENGTH_SHORT).show()
        }

        tvStepsTaken.setOnLongClickListener {
            previousTotalSteps = totalSteps
            tvStepsTaken.text = "0"
            saveData()
            true
        }
    }

    private fun saveData() {
        val sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putFloat("key1", previousTotalSteps)
        editor.apply()
    }

    private fun loadData() {
        val sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        previousTotalSteps = sharedPreferences.getFloat("key1", 0f)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Este método no es necesario para el podómetro
    }
}