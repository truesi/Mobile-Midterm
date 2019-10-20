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
    private val _properties = MutableLiveData<List<ArtistPropery>>()
    private val _images = MutableLiveData<Image>()

    val images: LiveData<Image>
        get() = _images

    val response: LiveData<String>
        get() = _response

    val status: LiveData<String>
        get() = _status

    val property: LiveData<List<ArtistPropery>>
        get() = _properties


    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    init {
        getArtistProperties()
    }


    private fun getArtistProperties() {

        coroutineScope.launch {
            var getPropertiesDeferred = ArtistApi.retrofitService.getProperties()
           // var getImageDeferred = ArtistApi.retrofitService.getImage()

            try {
                var listResult = getPropertiesDeferred.await()

               // var imageResult = getImageDeferred.await()

//                if(imageResult.size > 0){
//                    _images.value = imageResult[0]
//                }


//                if(listResult.size > 0){
//                    _properties.value = listResult
//                }
                if (listResult.size > 0){
                    _status.value = listResult[0].artists.artist[0].image[0].image
                }


            }catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }


    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
// _response.value = listResult.artists.artist.get(0).image[0].image