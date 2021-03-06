package com.index197511.memo.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.afollestad.materialdialogs.MaterialDialog
import com.index197511.memo.R
import com.index197511.memo.database.Memo
import com.index197511.memo.databinding.HomeFragmentBinding
import com.index197511.memo.home.listitem.ListItem
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint

interface ListItemClickHandler {
    fun onClick()
    fun onLongClick()
}

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding
    private val viewModel by viewModels<HomeFragmentViewModel>()
    private val adapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.memoRecyclerView.adapter = adapter
        viewModel.memos.observe(viewLifecycleOwner, Observer {
            updateMemoList(it)
        })
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

    private fun updateMemoList(memoList: List<Memo>) {
        adapter.update(mutableListOf<Group>().apply {
            memoList.forEach { memo ->
                add(ListItem(memo, generateClickHandler(memo)))
            }
        })
    }

    private fun generateClickHandler(memo: Memo): ListItemClickHandler {
        return object : ListItemClickHandler {
            override fun onClick() {
                val action = HomeFragmentDirections.actionHomeFragmentToMemoPageFragment(memo)
                findNavController().navigate(action)
            }

            override fun onLongClick() {
                showConfirmationOfDeletion(memo)
            }
        }
    }

    private fun showConfirmationOfDeletion(memo: Memo) {
        MaterialDialog(this.requireContext()).show {
            title(text = "Delete Dialog?")
            message(text = "Delete ${memo.title}?")
            positiveButton(text = "DELETE") {
                viewModel.delete(memo)
            }
            negativeButton()
        }
    }
}
