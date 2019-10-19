package com.example.android.swagger.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.swagger.network.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class OverviewViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()
    private val _status = MutableLiveData<String>()
    private val _properties = MutableLiveData<ArtistPropery>()
    private val _image = MutableLiveData<Artists>()

    val image: LiveData<Artists>
        get() = _image

    val response: LiveData<String>
        get() = _response

    val status: LiveData<String>
        get() = _status

    val property: LiveData<ArtistPropery>
        get() = _properties


    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    init {
        getMarsRealEstateProperties()
    }


    private fun getMarsRealEstateProperties() {

//        MarsApi.retrofitService.getProperties().enqueue(object : Callback<ArtistPropery>{
//            override fun onFailure(call: Call<ArtistPropery>, t: Throwable) {
//                _response.value = "Failure " + t.message
//            }
//
//            override fun onResponse(call: Call<ArtistPropery>, response: Response<ArtistPropery>) {
//                _response.value = "Succes ${response.body()?.artists?.artist?.get(0)?.name}"
//            }
//        })


        coroutineScope.launch {
            var getPropertiesDeferred = MarsApi.retrofitService.getProperties()
            try {
                var listResult = getPropertiesDeferred.await()
                _response.value = "Success: ${listResult.artists.artist.get(0).image[0].image}"


//                if (listResult.artists.artist.size > 0){
//                    _properties.value = listResult
//                }



//                _status.value = MarsApiStatus.LOADING
//                // Await the completion of our Retrofit request
//                val listResult = getPropertiesDeferred.await()
//                _status.value = MarsApiStatus.DONE
//                _properties.value = listResult
            }catch (e: Exception) {
                _status.value = "Failure: ${e.message}"

//                _status.value = MarsApiStatus.ERROR
//                _properties.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
