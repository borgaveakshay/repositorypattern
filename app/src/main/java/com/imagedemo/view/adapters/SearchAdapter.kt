package com.imagedemo.view.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.imagedemo.R
import com.imagedemo.models.PhotoItem

class SearchAdapter(private val photos: List<PhotoItem>?) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    interface OnSearchItemClickListener {

        fun onItemClicked(photoItem: PhotoItem)
    }

    var onSearchItemClickListener: OnSearchItemClickListener? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.row_search_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return photos?.size ?: 0
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.title.text = photos?.get(position)?.title

        viewHolder.itemView.setOnClickListener { photoItem ->

            photos?.get(position)?.let {
                onSearchItemClickListener?.onItemClicked(it)
            }

        }
    }

    class ViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

        val title: TextView = view.findViewById(R.id.title)

    }
}