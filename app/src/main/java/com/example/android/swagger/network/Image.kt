package com.example.android.swagger.network

import com.squareup.moshi.Json

data class Image(

        @Json(name = "#text") val image: String,
        @Json(name = "size") val sizePic: String

)