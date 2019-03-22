package com.imagedemo.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.imagedemo.BuildConfig
import com.imagedemo.R
import com.imagedemo.viewmodels.DetailViewModel
import com.imagedemo.viewmodels.viewmodelfactory.SearchViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_flickr_detail.*
import org.koin.android.ext.android.inject

class FlickrDetailFragment : BaseFragment() {

    private val mSearchViewModelFactory: SearchViewModelFactory by inject()
    private lateinit var mDetailViewModel: DetailViewModel
    private var photoId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_flickr_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photoId = arguments!!.getString("photoId")
        initUI()
    }

    override fun initUI() {
        mDetailViewModel = ViewModelProviders.of(activity!!, mSearchViewModelFactory).get(DetailViewModel::class.java)
        if(photoId != null) {
            getSearchDetails()
        }
    }

    private fun getSearchDetails() {
        photoId?.let {

            showOrHideProgressbar()
            mDetailViewModel.searchDetails(BuildConfig.API_KEY, it)?.observe(this, Observer { searchDetails ->
                if (mDetailViewModel.mError) {
                    showOrHideProgressbar()
                    Toast.makeText(context, getString(R.string.error_string), Toast.LENGTH_LONG).show()
                }
                searchDetails?.let { searchResult ->
                    showOrHideProgressbar()
                    title.text = searchResult.prevphoto?.title
                    searchResult.prevphoto?.thumb?.let { url ->
                        Picasso.get().load(url)
                            .into(thumbnail)
                    }

                }

            })
        }

    }
}
