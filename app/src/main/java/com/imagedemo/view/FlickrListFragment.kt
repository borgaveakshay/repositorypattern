package com.imagedemo.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.imagedemo.BuildConfig.API_KEY

import com.imagedemo.R
import com.imagedemo.view.adapters.SearchAdapter
import com.imagedemo.viewmodels.SearchViewModel
import com.imagedemo.viewmodels.viewmodelfactory.SearchViewModelFactory
import org.koin.android.ext.android.inject
import androidx.navigation.Navigation
import com.imagedemo.models.PhotoItem
import kotlinx.android.synthetic.main.fragment_flickr_list.*


class FlickrListFragment : BaseFragment() {

    private lateinit var mSearchViewModel: SearchViewModel
    private val mSearchViewModelFactory: SearchViewModelFactory by inject()
    private lateinit var mSearchAdapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return  inflater.inflate(R.layout.fragment_flickr_list, container, false)
    }

    override fun initUI() {
        mSearchViewModel = ViewModelProviders.of(this, mSearchViewModelFactory).get(SearchViewModel::class.java)

        val layoutManager = LinearLayoutManager(
            activity,
            RecyclerView.VERTICAL,
            false
        )
        photoSearchList.layoutManager = layoutManager
        photoSearchList.addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                activity,
                layoutManager.orientation
            )
        )
        mSearchAdapter = SearchAdapter { photoItem: PhotoItem, view: View ->

           val action = FlickrListFragmentDirections.actionFlickrListFragmentToFlickrDetailFragment2(photoItem.id!!)
            Navigation.findNavController(view).navigate(action)
        }
        photoSearchList.adapter = mSearchAdapter
        submitBtn.setOnClickListener {
            makeSearchQuery()
        }
    }

    private fun makeSearchQuery() {
        searchTag.text?.let {
            showOrHideProgressbar()
            mSearchViewModel.search(API_KEY, it.toString())
                ?.observe(this, Observer { response ->

                    if (mSearchViewModel.mError) {
                        showOrHideProgressbar()
                        Toast.makeText(activity, getString(R.string.error_string), Toast.LENGTH_LONG).show()
                    } else {
                        showOrHideProgressbar()
                        response?.let { photoItem ->

                            photoItem.photos?.let { photos ->
                                photos.photo?.let { list ->
                                    mSearchAdapter.setData(list)
                                }
                            }

                        }
                    }
                })
        }
    }
}
