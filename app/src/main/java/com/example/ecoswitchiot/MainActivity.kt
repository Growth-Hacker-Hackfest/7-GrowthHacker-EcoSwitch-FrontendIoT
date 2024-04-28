package com.example.ecoswitchiot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecoswitchiot.presentation.CameraSensorScreen
import com.example.ecoswitchiot.presentation.LightSensorScreen
import com.example.ecoswitchiot.presentation.ReceiverScreen
import com.example.ecoswitchiot.presentation.StartScreen
import com.example.ecoswitchiot.ui.theme.EcoSwitchIoTTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            EcoSwitchIoTTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "start") {
                        composable("start") {
                            StartScreen(navController = navController)
                        }

                        composable("light_sensor") {
                            LightSensorScreen()
                        }

                        composable("camera_sensor"){
                            CameraSensorScreen()
                        }

                        composable("receiver") {
                            ReceiverScreen()
                        }
                    }
                }
            }
        }
    }
}