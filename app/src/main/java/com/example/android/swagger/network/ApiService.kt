package com.example.android.swagger.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "http://ws.audioscrobbler.com/2.0/"

private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()


private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()

interface ApiService {

    @GET("?method=chart.gettopartists&api_key=f05eaac1fc44a8881ff049cdd6ac0df5&format=json")
    fun getProperties():
    // Deferred<List<Property>>
            Deferred<List<ArtistPropery>>

    @GET("?method=chart.gettopartists&api_key=f05eaac1fc44a8881ff049cdd6ac0df5&format=json")
    fun getImage():
            Deferred<List<Image>>
}

object ArtistApi {
    val retrofitService : ApiService by lazy { retrofit.create(ApiService::class.java) }
}
//GET("?method=chart.gettopartists&api_key=f05eaac1fc44a8881ff049cdd6ac0df5&format=json")
//@GET("?method=artist.getinfo&artist=Cher&api_key=f05eaac1fc44a8881ff049cdd6ac0df5&format=json")
