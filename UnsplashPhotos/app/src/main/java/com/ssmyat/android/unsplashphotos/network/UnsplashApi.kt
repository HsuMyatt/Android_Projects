package com.ssmyat.android.unsplashphotos.network

import com.ssmyat.android.unsplashphotos.utils.ACCESS_KEY
import com.ssmyat.android.unsplashphotos.viewobjects.Photo
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface UnsplashApi {
    @GET("photos")
    @Headers("Authorization: Client-ID " + ACCESS_KEY)
    fun getPhotos(@Query("page") pageNum: Int, @Query("order_by") order: String = "latest"): Call<List<Photo>>

    companion object {
        private const val BASE_URL = "https://api.unsplash.com/"

        fun create(): UnsplashApi = create(HttpUrl.parse(BASE_URL)!!)

        fun create(httpUrl: HttpUrl): UnsplashApi {

            return Retrofit.Builder()
                    .baseUrl(httpUrl)
                    .client(OkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(UnsplashApi::class.java)
        }
    }
}