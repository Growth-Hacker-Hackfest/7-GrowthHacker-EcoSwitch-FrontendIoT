package com.example.ecoswitchiot.components

import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import java.util.concurrent.Executors
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.runtime.mutableStateOf

@Composable
fun PersonScannerLayout(
    modifier: Modifier = Modifier,
    onCancelClick: () -> Unit,
    onSelesaiClick: () -> Unit,
    callback:(ImageProxy) -> Unit
) {
    val context = LocalContext.current
    val containerWidth = LocalConfiguration.current.screenWidthDp
    val containerHeight = LocalConfiguration.current.screenHeightDp
    val qrScannerAreaSize = (containerWidth * 0.7).dp
    val density = LocalDensity.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = remember {
        ProcessCameraProvider.getInstance(context)
    }
    val cameraController = remember {
        LifecycleCameraController(context)
    }
    val isSending = remember {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier
    ) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { ctx ->
                val cameraExecutor = Executors.newSingleThreadExecutor()
                val previewView = PreviewView(ctx).also { it.controller = cameraController }

                cameraProviderFuture.addListener(
                    {
                        val cameraProvider = cameraProviderFuture.get()
                        val preview =
                            Preview.Builder()
                                .build().also {
                                    it.setSurfaceProvider(previewView.surfaceProvider)
                                }
                        val imageAnalyzer = ImageAnalysis
                            .Builder()
                            .setTargetResolution(
                                android.util.Size(
                                    with(density) { containerWidth.dp.toPx().toInt() },
                                    with(density) { containerHeight.dp.toPx().toInt() }
                                )
                            )
                            .build()
                            .also { analyzer ->
                                analyzer.setAnalyzer(
                                    cameraExecutor,
                                    ImageAnalysis.Analyzer {
                                        callback(it)
                                    }
                                )
                            }
                        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                        try {
                            // Unbind use cases before rebinding
                            cameraProvider.unbindAll()

                            // Bind use cases to camera
                            cameraProvider.bindToLifecycle(
                                lifecycleOwner,
                                cameraSelector,
                                preview,
                                imageAnalyzer
                            )

                        } catch (exc: Exception) {
                            Log.e("DEBUG", "Use case binding failed", exc)
                        }
                    },
                    ContextCompat.getMainExecutor(ctx)
                )

                previewView
            }
        )
    }
}