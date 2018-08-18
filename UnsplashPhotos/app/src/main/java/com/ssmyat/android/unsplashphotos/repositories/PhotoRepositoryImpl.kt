package com.ssmyat.android.unsplashphotos.repositories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.ssmyat.android.unsplashphotos.network.UnsplashApi
import com.ssmyat.android.unsplashphotos.pagingwithnetwork.PhotoDataSourceFactory
import com.ssmyat.android.unsplashphotos.utils.PAGE_SIZE
import com.ssmyat.android.unsplashphotos.viewobjects.DataLoadState
import com.ssmyat.android.unsplashphotos.viewobjects.Photo
import java.util.concurrent.Executors

object PhotoRepositoryImpl : PhotoRepository {

    private val dataSourceFactory = PhotoDataSourceFactory(UnsplashApi.create())

    private val myExecutor = Executors.newFixedThreadPool(5)

    override fun getPhotos(): LiveData<PagedList<Photo>> {
        val config = PagedList.Config.Builder()
                .setInitialLoadSizeHint(PAGE_SIZE)
                .setPageSize(PAGE_SIZE)
                .setPrefetchDistance(PAGE_SIZE * 3)
                .setEnablePlaceholders(false)
                .build()

        return LivePagedListBuilder(dataSourceFactory, config)
                .setFetchExecutor(myExecutor)
                .setInitialLoadKey(1)
                .build()
    }

    override fun getDataLoadState(): LiveData<DataLoadState> {
        return Transformations.switchMap(dataSourceFactory.sourceLiveData) {
            it.mDataLoadedLiveData
        }
    }
}