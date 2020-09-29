package com.index197511.memo.home.listitem

import android.view.View
import com.index197511.memo.R
import com.index197511.memo.database.Memo
import com.index197511.memo.databinding.ListItemBinding
import com.index197511.memo.home.ListItemClickHandler
import com.xwray.groupie.viewbinding.BindableItem

class ListItem(
    private val memo: Memo,
    private val clickHandler: ListItemClickHandler
) : BindableItem<ListItemBinding>() {
    override fun getLayout(): Int = R.layout.list_item

    override fun bind(viewBinding: ListItemBinding, position: Int) {
        viewBinding.itemTextView.text = memo.title
        viewBinding.root.setOnClickListener {
            clickHandler.onClick()
        }
        viewBinding.root.setOnLongClickListener {
            clickHandler.onLongClick()
            true
        }
    }

    override fun initializeViewBinding(view: View): ListItemBinding = ListItemBinding.bind(view)
}