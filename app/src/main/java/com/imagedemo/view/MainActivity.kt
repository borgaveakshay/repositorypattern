package com.imagedemo.view

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.imagedemo.BuildConfig.API_KEY
import com.imagedemo.R
import com.imagedemo.models.PhotoItem
import com.imagedemo.view.adapters.SearchAdapter
import com.imagedemo.viewmodels.SearchViewModel
import com.imagedemo.viewmodels.viewmodelfactory.SearchViewModelFactory
import org.koin.android.ext.android.inject


class MainActivity : BaseActivity(), SearchAdapter.OnSearchItemClickListener {

    private lateinit var mSearchBox: EditText
    private lateinit var mSubmit: Button
    private lateinit var mSearchViewModel: SearchViewModel
    private val mSearchViewModelFactory: SearchViewModelFactory by inject()
    private lateinit var mSearchAdapter: SearchAdapter
    private lateinit var mSearchRecyclerView: androidx.recyclerview.widget.RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)
    }

    override fun initUI() {
        mSearchViewModel = ViewModelProviders.of(this, mSearchViewModelFactory).get(SearchViewModel::class.java)
        mSearchRecyclerView = findViewById(R.id.searchList)

        val layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )
        mSearchRecyclerView.layoutManager = layoutManager
        mSearchRecyclerView.addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                this,
                layoutManager.orientation
            )
        )

        mSearchBox = findViewById(R.id.searchTag)
        mSubmit = findViewById(R.id.submitBtn)
        mSubmit.setOnClickListener { makeSearchQuery() }
    }

    private fun makeSearchQuery() {
        mSearchBox.text?.let {
            showOrHideProgressbar()
            mSearchViewModel.search(API_KEY, it.toString())
                ?.observe(this, Observer { response ->

                    if (mSearchViewModel.mError) {
                        showOrHideProgressbar()
                        Toast.makeText(this, getString(R.string.error_string), Toast.LENGTH_LONG).show()
                    } else {
                        showOrHideProgressbar()
                        response?.let { photoItem ->

                            photoItem.photos?.let { photos ->
                                photos.photo?.let { list ->
                                    mSearchAdapter = SearchAdapter(list)
                                    mSearchAdapter.onSearchItemClickListener = this
                                    mSearchRecyclerView.adapter = mSearchAdapter
                                }
                            }

                        }
                    }
                })
        }
    }

    override fun onItemClicked(photoItem: PhotoItem) {

        val intent = Intent(this, DetailActivity::class.java)
        val bundle = Bundle()
        bundle.putString("photoId", photoItem.id)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}
