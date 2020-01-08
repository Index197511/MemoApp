package com.index197511.memo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.index197511.memo.database.Memo
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

        //recyclerView
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)

        val hoges: List<String> = mutableListOf<String>("a", "b", "c", "d")

        val recyclerView = binding.memoRecyclerView ?: return null

        val adapter = HomeRecyclerAdapter(hoges, object : HomeRecyclerViewHolder.ItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                this@HomeFragment.onItemClick(view, position)
            }
        })

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        setHasOptionsMenu(true)

        //database
        val application = requireNotNull(this.activity).application
        val dataSource = MemoDatabase.getInstance(application).memoDatabaseDao

        val viewModelFactory = HomeFragmentViewModelFactory(dataSource, application)
        homeFragmentViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(HomeFragmentViewModel::class.java)
        binding.homeFragment = homeFragmentViewModel


        return binding.root
    }

    fun onItemClick(tappedView: View, position: Int) {
        homeFragmentViewModel.onItemClick(tappedView, position)
    }


}
