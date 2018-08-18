package com.ssmyat.android.unsplashphotos.pagingwithnetwork

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import com.ssmyat.android.unsplashphotos.network.UnsplashApi
import com.ssmyat.android.unsplashphotos.viewobjects.DataLoadState
import com.ssmyat.android.unsplashphotos.viewobjects.Photo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class PhotoDataSource(private val api: UnsplashApi) : PageKeyedDataSource<Int, Photo>() {

    val mDataLoadedLiveData = MutableLiveData<DataLoadState>()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Photo>) {
        mDataLoadedLiveData.postValue(DataLoadState.LOADING)
        val call = api.getPhotos(1)

        try {
            val response = call.execute()
            mDataLoadedLiveData.postValue(DataLoadState.LOADED)
            callback.onResult(response.body()!!, null, 2)
        } catch (e: IOException) {
            mDataLoadedLiveData.postValue(DataLoadState.FAILED)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
        mDataLoadedLiveData.postValue(DataLoadState.LOADING)

        val call = api.getPhotos(params.key)

        call.enqueue(object : Callback<List<Photo>> {
            override fun onFailure(call: Call<List<Photo>>?, t: Throwable?) {
                mDataLoadedLiveData.postValue(DataLoadState.FAILED)
            }

            override fun onResponse(call: Call<List<Photo>>?, response: Response<List<Photo>>?) {
                if (response?.isSuccessful == true) {
                    callback.onResult(response.body()!!, params.key + 1)
                    mDataLoadedLiveData.postValue(DataLoadState.LOADED)
                } else {
                    mDataLoadedLiveData.postValue((DataLoadState.FAILED))
                }

            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}