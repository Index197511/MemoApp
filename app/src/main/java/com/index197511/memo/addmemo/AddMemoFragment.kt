package com.index197511.memo.addmemo

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.index197511.memo.R
import com.index197511.memo.database.Memo
import com.index197511.memo.databinding.AddMemoFragmentBinding
import com.index197511.memo.ext.closeKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddMemoFragment : Fragment() {

    private val viewModel by viewModels<AddMemoFragmentViewModel>()
    private lateinit var binding: AddMemoFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddMemoFragmentBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.save_button, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        insertMemoToDatabase()
        closeKeyboard()

        return view
            ?.let { view ->
                NavigationUI.onNavDestinationSelected(item, view.findNavController())
            }
            ?: super.onOptionsItemSelected(item)
    }


    private fun insertMemoToDatabase() {
        val newMemo = Memo(
            id = 0,
            title = binding.titleText.text.toString(),
            content = binding.memoContentText.text.toString()
        )
        viewModel.insertMemo(newMemo)
    }
}