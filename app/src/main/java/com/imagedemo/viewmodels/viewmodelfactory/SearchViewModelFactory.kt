package com.imagedemo.viewmodels.viewmodelfactory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.imagedemo.repository.FlikrRepo
import com.imagedemo.viewmodels.BaseViewModel
import com.imagedemo.viewmodels.DetailViewModel
import com.imagedemo.viewmodels.SearchViewModel

@Suppress("UNCHECKED_CAST")
class SearchViewModelFactory(private val flikrRepo: FlikrRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        when (modelClass.canonicalName) {

            SearchViewModel::class.java.canonicalName -> return SearchViewModel(flikrRepo) as T
            DetailViewModel::class.java.canonicalName -> return DetailViewModel(flikrRepo) as T

            else -> return BaseViewModel() as T
        }

    }
}