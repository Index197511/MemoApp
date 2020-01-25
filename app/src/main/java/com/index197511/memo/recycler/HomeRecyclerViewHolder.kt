package com.index197511.memo.recycler

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.index197511.memo.R

class HomeRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val itemTextView: TextView = view.findViewById(R.id.itemTextView)
    val itemImageView: ImageView = view.findViewById(R.id.itemImageView)

}