package com.index197511.memo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.index197511.memo.databinding.HomeFragmentBinding
import com.index197511.memo.recycler.HomeRecyclerAdapter
import com.index197511.memo.recycler.HomeRecyclerViewHolder


class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("HomeFragment", "called onCreateView")

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

        return binding.root
    }

    fun onItemClick(tappedView: View, position: Int) {

        val content = position.toString()
        val action =  HomeFragmentDirections.actionHomeFragmentToMemoPageFragment(content)
        findNavController().navigate(action)

//        Toast.makeText(context, "tapped ${position}", Toast.LENGTH_SHORT).show()
//        Log.i("HomeFragment", "tapped ${position}")
    }


}
