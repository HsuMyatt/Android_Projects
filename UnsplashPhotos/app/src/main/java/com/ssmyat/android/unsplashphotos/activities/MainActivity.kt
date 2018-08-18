package com.ssmyat.android.unsplashphotos.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.ssmyat.android.unsplashphotos.R
import com.ssmyat.android.unsplashphotos.adapters.PhotosAdapter
import com.ssmyat.android.unsplashphotos.viewmodels.PhotoViewModel
import com.ssmyat.android.unsplashphotos.viewobjects.DataLoadState

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val adapter = PhotosAdapter()
        with(rv_unsplashPhotos) {
            layoutManager = android.support.v7.widget.StaggeredGridLayoutManager(resources.getInteger(R.integer.grid_span), android.support.v7.widget.StaggeredGridLayoutManager.VERTICAL)
            hasFixedSize()
            this.adapter = adapter
        }

        val viewModel = ViewModelProviders.of(this).get(PhotoViewModel::class.java)
        viewModel.photos.observe(this, Observer {
            adapter.submitList(it)
        })


        viewModel.dataLoadState.observe(this, Observer {
            if (it == DataLoadState.FAILED)
                Snackbar.make(rv_unsplashPhotos, "No Internet Connection", Snackbar.LENGTH_LONG).show()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
