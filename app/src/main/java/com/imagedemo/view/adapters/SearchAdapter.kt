package com.imagedemo.view.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.imagedemo.R
import com.imagedemo.models.PhotoItem

class SearchAdapter(val onClickListener: (photoItem: PhotoItem, view: View) -> Unit) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    private var photoList = mutableListOf<PhotoItem>()

    fun setData(list: List<PhotoItem>) {
        photoList.clear()
        photoList.addAll(list)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.row_search_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val photoItem = photoList[position]
        viewHolder.title.text = photoItem.title
        viewHolder.itemView.setOnClickListener {onClickListener(photoItem, viewHolder.itemView)}
    }

    class ViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
    }
}