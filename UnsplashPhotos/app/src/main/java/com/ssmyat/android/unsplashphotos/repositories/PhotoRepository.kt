package com.ssmyat.android.unsplashphotos.repositories

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import com.ssmyat.android.unsplashphotos.viewobjects.DataLoadState
import com.ssmyat.android.unsplashphotos.viewobjects.Photo

interface PhotoRepository {

    fun getPhotos(): LiveData<PagedList<Photo>>

    fun getDataLoadState(): LiveData<DataLoadState>
}