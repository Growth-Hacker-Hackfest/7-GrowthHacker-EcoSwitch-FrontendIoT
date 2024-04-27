package com.example.ecoswitchiot.presentation

import android.graphics.Bitmap
import android.net.Uri
import androidx.camera.core.ImageProxy
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ecoswitchiot.components.CameraLayout
import com.example.ecoswitchiot.components.PersonScannerLayout
import com.example.ecoswitchiot.data.http
import com.example.ecoswitchiot.model.response.image_classification.PersonDetectResponse
import com.example.ecoswitchiot.util.LightSensor
import io.ktor.client.call.body
import io.ktor.client.request.forms.formData
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.util.InternalAPI
import io.ktor.utils.io.streams.asInput
import okhttp3.Headers
import java.io.ByteArrayOutputStream

@OptIn(ExperimentalMaterial3Api::class, InternalAPI::class)
@Composable
fun CameraSensorScreen() {
    val id = remember {
        mutableStateOf("99988877766")
    }
    val context = LocalContext.current
    val lightSensor = LightSensor(context)
    val isListening = remember { mutableStateOf(false) }
    val value = remember { mutableStateOf<ImageProxy?>(null) }
    val hasil = remember { mutableStateOf("-") }
//    val uploadState = suspend {
//        val bitmap = value.value?.toBitmap()
//        val byteArrayOutputStream = ByteArrayOutputStream()
//        bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
//        val byteArray = byteArrayOutputStream.toByteArray()
//
//        http.post("http://199.180.131.186/predict") {
//            formData {
//                append("avatar", "image.jpg")
//                appendInput(
//                    key = "image"
//            }
//            contentType(ContentType.Image.JPEG)
//        }
//    }
    val res = remember {
        mutableStateOf<HttpResponse?>(null)
    }

//    LaunchedEffect(key1 = value.value) {
//        if(res.value?.status?.isSuccess() == true){
//            res.value?.let {
//                val body = it.body<PersonDetectResponse>()
//
//                if(body.result){
//                    hasil.value = "MANUSIA"
//                    //ALSO CALL API
//                } else {
//                    hasil.value = "BUKAN MANUSIA"
//                }
//            }
//        } else {
//            res.value = uploadState()
//        }
//    }

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
            }

            PersonScannerLayout(
                modifier = Modifier.padding(64.dp),
                onCancelClick = {},
                onSelesaiClick = {},
                callback = {

                }
            )
        }
    }
}