package com.imagedemo.view

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.imagedemo.BuildConfig.API_KEY
import com.imagedemo.R
import com.imagedemo.viewmodels.DetailViewModel
import com.imagedemo.viewmodels.viewmodelfactory.SearchViewModelFactory
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject

class DetailActivity : BaseActivity() {

    private val mSearchViewModelFactory: SearchViewModelFactory by inject()
    private lateinit var mDetailViewModel: DetailViewModel
    private lateinit var mTitle: TextView
    private lateinit var mThumbnail: ImageView
    private var mPhotoId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_detail)
        super.onCreate(savedInstanceState)

    }

    override fun initUI() {

        intent?.let {

            mPhotoId = it.extras?.getString("photoId")
        }
        mDetailViewModel = ViewModelProviders.of(this, mSearchViewModelFactory).get(DetailViewModel::class.java)
        mTitle = findViewById(R.id.title)
        mThumbnail = findViewById(R.id.thumbnail)
        getSearchDetails()

    }

    private fun getSearchDetails() {

        mPhotoId?.let {

            showOrHideProgressbar()
            mDetailViewModel.searchDetails(API_KEY, it)?.observe(this, Observer { searchDetails ->
                if (mDetailViewModel.mError) {
                    showOrHideProgressbar()
                    Toast.makeText(this, getString(R.string.error_string), Toast.LENGTH_LONG).show()
                }
                searchDetails?.let { searchResult ->
                    showOrHideProgressbar()
                    mTitle.text = searchResult.prevphoto?.title

                    searchResult.prevphoto?.thumb?.let { url ->
                        Picasso.get().load(url)
                            .fit()
                            .into(mThumbnail)
                    }

                }

            })
        }
    }
}
