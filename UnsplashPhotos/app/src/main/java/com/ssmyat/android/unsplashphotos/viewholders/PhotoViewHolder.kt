package com.ssmyat.android.unsplashphotos.viewholders

import android.content.res.Resources
import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.ssmyat.android.unsplashphotos.utils.GlideApp
import com.ssmyat.android.unsplashphotos.viewobjects.Photo
import kotlinx.android.synthetic.main.photo_item.view.*


class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val photoView = itemView.iv_unsplashPhoto
    private val userPhoto = itemView.iv_user_photo
    private val userName = itemView.tv_user_name
    private val likeCount = itemView.tv_likeCount
    private val progress = itemView.pb_loading

    fun bindTo(photo: Photo) {

        if (progress.visibility == View.GONE) progress.visibility = View.VISIBLE

        setPhotoViewHeight(photo.photoHeight, photo.photoWidth)

        GlideApp.with(itemView)
                .asBitmap()
                .load(photo.photoUrl.regularPhoto)
                .fitCenter()
                .listener(object : RequestListener<Bitmap> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Bitmap>?, isFirstResource: Boolean): Boolean {
                        //Log.d("Image Load:::", "fail")
                        return false
                    }

                    override fun onResourceReady(resource: Bitmap?, model: Any?, target: Target<Bitmap>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        progress.visibility = View.GONE
                        if (progress.visibility == View.GONE) {
                            if (photo.likeCount > 0) {
                                likeCount.visibility = View.VISIBLE
                                likeCount.text = photo.likeCount.toString()
                            }
                            userName.text = photo.user.name
                            GlideApp.with(itemView)
                                    .asBitmap()
                                    .load(photo.user.profileImage.userPhoto)
                                    .fitCenter()
                                    .into(userPhoto)
                        }
                        return false
                    }

                })
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.IMMEDIATE)
                .dontAnimate()
                .into(photoView)

    }

    private fun setPhotoViewHeight(photoHeight: Int, photoWidth: Int) {
        val heightRatio: Double = photoHeight.toDouble() / photoWidth.toDouble()
        val imageWidth = Resources.getSystem().displayMetrics.widthPixels
        val height = (imageWidth * heightRatio).toInt()
        photoView.getLayoutParams().height = height
    }


}