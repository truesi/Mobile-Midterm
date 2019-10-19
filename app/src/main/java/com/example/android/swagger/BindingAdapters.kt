package com.example.android.swagger

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("image")
fun bindImage(imgView: ImageView, imageURL: String?){
    imageURL?.let {
        val imageURI = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
                .load(imageURI)
                .into(imgView)
    }
}
