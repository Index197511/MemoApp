package com.index197511.memo.home

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.index197511.memo.R
import com.index197511.memo.databinding.HomeFragmentBinding
import com.index197511.memo.recycler.HomeRecyclerAdapter
import com.index197511.memo.recycler.HomeRecyclerViewHolder


class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding
    private lateinit var homeFragmentViewModel: HomeFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.home_fragment, container, false
        )
        binding.lifecycleOwner = this

        val application = requireNotNull(this.activity).application

        val viewModelFactory = HomeFragmentViewModelFactory(application)

        homeFragmentViewModel =
            ViewModelProvider(this, viewModelFactory).get(HomeFragmentViewModel::class.java)

        //recyclerView
        val homeRecyclerAdapter =
            HomeRecyclerAdapter(this@HomeFragment::onItemClick)

        val recyclerView = binding.memoRecyclerView.apply {
            adapter = homeRecyclerAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        homeFragmentViewModel.allMemoList.observe(this, Observer { memos ->
            memos?.also { homeRecyclerAdapter.setMemos(it) }
        })

        getSwipeToDismissTouchHelper(homeRecyclerAdapter)
            .attachToRecyclerView(recyclerView)
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.add_button, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return view?.let { view ->
            NavigationUI.onNavDestinationSelected(item, view.findNavController())
        } ?: super.onOptionsItemSelected(item)
    }

    private fun onItemClick(tappedView: View, position: Int) {
        homeFragmentViewModel.onItemClick(tappedView, position)
    }

    private fun getSwipeToDismissTouchHelper(adapter: RecyclerView.Adapter<HomeRecyclerViewHolder>) =
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                homeFragmentViewModel.deleteFromDatabase(viewHolder.adapterPosition)

                adapter.apply {
                    notifyItemRemoved(viewHolder.adapterPosition)
                    notifyDataSetChanged()
                }
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
                val itemView = viewHolder.itemView
                val background = ColorDrawable()
                background.color = Color.parseColor("#f44336")
                if (dX < 0)
                    background.setBounds(
                        itemView.right + dX.toInt(),
                        itemView.top,
                        itemView.right,
                        itemView.bottom
                    )
                else
                    background.setBounds(
                        itemView.left,
                        itemView.top,
                        itemView.left + dX.toInt(),
                        itemView.bottom
                    )

                background.draw(c)
            }
        })

}
