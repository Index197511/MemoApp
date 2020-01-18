package com.index197511.memo.home

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.index197511.memo.R
import com.index197511.memo.database.MemoDatabase
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

        //database
        val application = requireNotNull(this.activity).application
        val dataSource = MemoDatabase.getInstance(application).memoDatabaseDao

        val viewModelFactory =
            HomeFragmentViewModelFactory(
                dataSource,
                application
            )

        homeFragmentViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(HomeFragmentViewModel::class.java)
        binding.homeFragmentViewModel = homeFragmentViewModel
        binding.lifecycleOwner = this


        //recyclerView
        val recyclerView = binding.memoRecyclerView

        val adapter =
            HomeRecyclerAdapter(
                homeFragmentViewModel.allMemoList,
                object : HomeRecyclerViewHolder.ItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        this@HomeFragment.onItemClick(view, position)
                    }
                })

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val swipeToDismissTouchHelper = getSwipeToDismissTouchHelper(adapter)
        swipeToDismissTouchHelper.attachToRecyclerView(recyclerView)
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            view!!.findNavController()
        )
                || super.onOptionsItemSelected(item)
    }

    fun onItemClick(tappedView: View, position: Int) {
        homeFragmentViewModel.onItemClick(tappedView, position)
    }

    override fun onStart() {
        super.onStart()
        homeFragmentViewModel.updateMemoList()
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
                adapter.notifyItemRemoved(viewHolder.adapterPosition)
                adapter.notifyDataSetChanged()
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
