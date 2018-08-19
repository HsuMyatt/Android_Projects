package com.ssmyat.android.unsplashphotos.pagingwithnetwork

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.ssmyat.android.unsplashphotos.network.UnsplashApi
import com.ssmyat.android.unsplashphotos.viewobjects.Photo


class PhotoDataSourceFactory(private val api: UnsplashApi) : DataSource.Factory<Int, Photo>() {

    val sourceLiveData = MutableLiveData<PhotoDataSource>()

    override fun create(): DataSource<Int, Photo> {
        val dataSource = PhotoDataSource(api)
        sourceLiveData.postValue(dataSource)
        return dataSource
    }
}