package com.index197511.memo.recycler

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.index197511.memo.R

class HomeRecyclerAdapter(
    private val itemList: List<String>
//    private val itemClickListener: itemClickListener
) : RecyclerView.Adapter<HomeRecyclerViewHolder>() {
    private var myRecyclerView: RecyclerView? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        myRecyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        myRecyclerView = null
    }

    override fun onBindViewHolder(holder: HomeRecyclerViewHolder, position: Int) {
        holder.let {
            it.itemTextView.text = itemList.get(position)
            it.itemImageView.setImageResource(R.mipmap.ic_launcher)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecyclerViewHolder {
        Log.i("Adapter", "onCreateViewHolder")
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

//        mView.setOnClickListener { view ->
//            myRecyclerView?.let {
//                itemClickListener.onItemClick(view, it.getChildAdapterPosition(view))
//            }
//        }

        return HomeRecyclerViewHolder(mView)
    }
}