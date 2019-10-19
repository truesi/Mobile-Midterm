package com.example.android.swagger.network

import com.squareup.moshi.Json

data class Image(

        @Json(name = "#text") val image: String,
        val size: String
)