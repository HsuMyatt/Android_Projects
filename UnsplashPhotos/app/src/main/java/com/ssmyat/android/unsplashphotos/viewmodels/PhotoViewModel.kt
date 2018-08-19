package com.ssmyat.android.unsplashphotos.viewmodels

import android.arch.lifecycle.ViewModel
import com.ssmyat.android.unsplashphotos.repositories.PhotoRepositoryImpl

class PhotoViewModel : ViewModel() {

    val photos = PhotoRepositoryImpl.getPhotos()

    val dataLoadState = PhotoRepositoryImpl.getDataLoadState()
}