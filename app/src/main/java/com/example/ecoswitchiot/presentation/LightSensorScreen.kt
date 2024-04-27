package com.example.ecoswitchiot.presentation

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ecoswitchiot.util.LightSensor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LightSensorScreen() {
    val id = remember {
        mutableStateOf("99988877766")
    }
    val context = LocalContext.current
    val lightSensor = LightSensor(context)
    val isListening = remember { mutableStateOf(false) }
    val value = remember { mutableStateOf(.0) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "IoT Sensor Cahaya") })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = id.value,
                    onValueChange = { id.value = it },
                    label = {
                        Text(text = "ID Perangkat")
                    }
                )

                Button(
                    onClick = {
                        lightSensor
                            .listenSensor {
                                isListening.value = true

                                //CHECK THE DATA, IS IT SHOULD CALL API NOR NOT
                                value.value = it.values[0].toDouble()
                            }
                    }
                ) {
                    Text(text = "Sambungkan")
                }
            }
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(
                    modifier = Modifier.padding(horizontal = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (isListening.value) {
                        Text(
                            text = "Hasil Kepekaan Cahaya Dari Sensor Ini Adalah",
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = String.format("%.4f", value.value),
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Center
                        )
                    } else {
                        Text(
                            text = "Tekan Sambungkan Untuk Mulai Monitoring",
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = value.value.toString(),
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}