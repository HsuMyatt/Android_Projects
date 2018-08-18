package com.ssmyat.android.unsplashphotos.viewobjects

import com.google.gson.annotations.SerializedName

data class Photo(
        @SerializedName("id")
        val id: String,
        @SerializedName("width")
        val photoWidth: Int,
        @SerializedName("height")
        val photoHeight: Int,
        @SerializedName("urls")
        val photoUrl: PhotoUrl,
        @SerializedName("user")
        val user: User,
        @SerializedName("likes")
        val likeCount: Int
) {
    val heightRatio: Double
        get() = photoHeight.toDouble() / photoWidth.toDouble()
}