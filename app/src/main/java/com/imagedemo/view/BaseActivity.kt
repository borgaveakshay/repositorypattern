package com.imagedemo.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.ProgressBar
import com.imagedemo.R

abstract class BaseActivity : AppCompatActivity() {

    private var mProgressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mProgressBar = findViewById(R.id.progressBar)
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