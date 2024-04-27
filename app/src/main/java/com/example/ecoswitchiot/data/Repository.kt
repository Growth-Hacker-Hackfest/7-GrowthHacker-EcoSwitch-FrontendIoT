package com.example.ecoswitchiot.data

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.ecoswitchiot.model.response.BaseResponse
import com.example.ecoswitchiot.model.response.perangkat.SinglePerangkatResponse
import com.example.ecoswitchiot.util.getResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.serialization.gson.gson

const val BASE_URL = "http://199.180.130.189"

val http = HttpClient(Android) {
    install(ContentNegotiation) {
        gson()
    }
}

val encPref = { context: Context ->
    EncryptedSharedPreferences.create(
        "enc_iot",
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
}

object Repository{
    fun getAllDeviceIot() =
        getResponse<BaseResponse<List<SinglePerangkatResponse>>>() {
            http.get("$BASE_URL/device-iot")
        }
}
