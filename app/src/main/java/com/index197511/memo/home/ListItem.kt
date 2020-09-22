package com.index197511.memo.home

import android.view.View
import com.index197511.memo.R
import com.index197511.memo.database.Memo
import com.index197511.memo.databinding.ListItemBinding
import com.xwray.groupie.viewbinding.BindableItem

class ListItem(
    private val listener: ListItemListener,
    private val memo: Memo
) : BindableItem<ListItemBinding>() {
    override fun getLayout(): Int = R.layout.list_item

    override fun bind(viewBinding: ListItemBinding, position: Int) {
        viewBinding.itemTextView.text = memo.memoTitle

        viewBinding.apply {
            root.setOnClickListener {
                listener.onClick()
            }
        }
    }

    override fun initializeViewBinding(view: View): ListItemBinding = ListItemBinding.bind(view)
}