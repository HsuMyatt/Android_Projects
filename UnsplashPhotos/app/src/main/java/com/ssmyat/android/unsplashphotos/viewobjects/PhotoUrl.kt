package com.ssmyat.android.unsplashphotos.viewobjects

import com.google.gson.annotations.SerializedName

data class PhotoUrl(
        @SerializedName("regular")
        val regularPhoto: String
)