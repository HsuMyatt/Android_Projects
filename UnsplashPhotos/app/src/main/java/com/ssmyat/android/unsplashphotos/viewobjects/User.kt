package com.ssmyat.android.unsplashphotos.viewobjects

import com.google.gson.annotations.SerializedName

data class User(
        @SerializedName("id")
        val id: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("profile_image")
        val profileImage: UserProfileImage
)