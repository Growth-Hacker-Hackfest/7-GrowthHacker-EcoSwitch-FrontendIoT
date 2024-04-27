package com.example.ecoswitchiot.presentation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ecoswitchiot.data.Repository
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun StartScreen(
    navController: NavController
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(100.dp)
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    navController.navigate("light_sensor")
                }
            ) {
                Text(text = "Light Sensor")
            }
//            Button(
//                modifier = Modifier.fillMaxWidth(),
//                onClick = {
//                    navController.navigate("camera_sensor")
//                }
//            ) {
//                Text(text = "Camera Sensor")
//            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    navController.navigate("receiver")
                }
            ) {
                Text(text = "Receiver")
            }
        }
    }
}