package com.index197511.memo.recycler

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.index197511.memo.R
import com.index197511.memo.database.Memo

class HomeRecyclerAdapter(
    private val idAndTitleList: List<Memo>,
    private val onItemClick: (view: View, position: Int) -> Unit
) : RecyclerView.Adapter<HomeRecyclerViewHolder>() {

    private lateinit var recyclerView: RecyclerView

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        this.recyclerView = recyclerView
    }

    override fun onBindViewHolder(holder: HomeRecyclerViewHolder, position: Int) {
        holder.apply {
            itemTextView.text = idAndTitleList[position].memoTitle
            itemImageView.setImageResource(R.mipmap.ic_launcher)
        }
    }

    override fun getItemCount(): Int = idAndTitleList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecyclerViewHolder {
        Log.i("Adapter", "onCreateViewHolder")

        val myView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
            .apply {
                setOnClickListener { view ->
                    onItemClick(
                        view,
                        recyclerView.getChildAdapterPosition(view)
                    )
                }
            }

        return HomeRecyclerViewHolder(myView)
    }

}