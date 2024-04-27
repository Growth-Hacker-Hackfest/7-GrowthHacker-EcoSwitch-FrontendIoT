package com.example.ecoswitchiot.util

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

class LightSensor(
    private val context: Context
) {
    private var sensorManager: SensorManager? = null
    private var sensor: Sensor? = null
    private var listener: SensorEventListener? = null

    private fun create(){
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager?.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    fun listenSensor(
        onChanged:(event: SensorEvent) -> Unit
    ){
        if(sensorManager == null || sensor == null) create()
        listener = object : SensorEventListener{
            override fun onSensorChanged(p0: SensorEvent?) {
                p0?.let(onChanged)
            }

            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
                //TODO Let this empty
            }
        }
        sensorManager?.registerListener(
            listener,
            sensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    fun unlistenSensor(){
        sensorManager?.unregisterListener(listener)
    }
}