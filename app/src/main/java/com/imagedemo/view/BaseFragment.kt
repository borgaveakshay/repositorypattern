package com.imagedemo.view

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {

    private var mProgressBar: ProgressBar? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    abstract fun initUI()

    fun showOrHideProgressbar() {

        mProgressBar?.let {

            when (it.visibility) {
                View.VISIBLE -> it.visibility = View.INVISIBLE
                else -> it.visibility = View.VISIBLE
            }
        }
    }
}