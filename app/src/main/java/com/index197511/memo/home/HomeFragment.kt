package com.index197511.memo.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
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

        Log.i("HomeFragment", "called onCreateView")

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

        val hoges: List<String> =
            homeFragmentViewModel.allMemoTitle.value?.reversed() ?: return null
        val recyclerView = binding.memoRecyclerView

        val adapter = HomeRecyclerAdapter(hoges, object : HomeRecyclerViewHolder.ItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                this@HomeFragment.onItemClick(view, position)
            }
        })

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item!!,
            view!!.findNavController()
        )
                || super.onOptionsItemSelected(item)
    }

    fun onItemClick(tappedView: View, position: Int) {
        homeFragmentViewModel.onItemClick(tappedView, position)
    }


}
