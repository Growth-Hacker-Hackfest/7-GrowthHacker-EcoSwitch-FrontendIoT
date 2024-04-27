package com.example.ecoswitchiot.model.response

data class BaseResponse<T>(
    val message: String,
    val data: T
)
