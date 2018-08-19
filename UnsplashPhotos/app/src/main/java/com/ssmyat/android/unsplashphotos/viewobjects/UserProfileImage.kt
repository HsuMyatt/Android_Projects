package com.ssmyat.android.unsplashphotos.viewobjects

import com.google.gson.annotations.SerializedName

data class UserProfileImage(
        @SerializedName("medium")
        val userPhoto: String
)