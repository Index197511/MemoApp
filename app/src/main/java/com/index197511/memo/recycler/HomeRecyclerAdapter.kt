package com.index197511.memo.recycler

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.index197511.memo.R
import com.index197511.memo.database.Memo

class HomeRecyclerAdapter(
    private val idAndTitleList: List<Memo>,
    private val itemClickListener: HomeRecyclerViewHolder.ItemClickListener
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
            it.itemTextView.text = idAndTitleList[position].memoTitle
            it.itemImageView.setImageResource(R.mipmap.ic_launcher)
        }
    }

    override fun getItemCount(): Int {
        return idAndTitleList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecyclerViewHolder {
        Log.i("Adapter", "onCreateViewHolder")
        val myView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        myView.setOnClickListener { view ->
            myRecyclerView?.let {
                itemClickListener.onItemClick(view, it.getChildAdapterPosition(view))
            }
        }
        return HomeRecyclerViewHolder(myView)
    }

}