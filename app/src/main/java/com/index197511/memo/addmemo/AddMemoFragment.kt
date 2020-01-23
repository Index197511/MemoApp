package com.index197511.memo.addmemo

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.index197511.memo.R
import com.index197511.memo.database.Memo
import com.index197511.memo.database.MemoDatabase
import com.index197511.memo.databinding.AddMemoFragmentBinding
import android.view.inputmethod.InputMethodManager

class AddMemoFragment : Fragment() {

    private lateinit var addMemoFragmentFragmentViewModel: AddMemoFragmentViewModel
    private lateinit var addMemoBinding: AddMemoFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        addMemoBinding =
            DataBindingUtil.inflate(inflater, R.layout.add_memo_fragment, container, false)
        val application = requireNotNull(this.activity).application

        val viewModelFactory =
            AddMemoViewModelFactory(
                MemoDatabase.getInstance(application).memoDatabaseDao,
                application
            )
        addMemoFragmentFragmentViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(AddMemoFragmentViewModel::class.java)
        addMemoBinding.addMemoFragmentViewModel = addMemoFragmentFragmentViewModel

        setHasOptionsMenu(true)

        return addMemoBinding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.save_button, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        this.insertMemoToDatabase()
        val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view!!.windowToken, 0)
        return NavigationUI.onNavDestinationSelected(
            item,
            view!!.findNavController()
        )
                || super.onOptionsItemSelected(item)
    }


    private fun insertMemoToDatabase() {
        val newMemoTitle: String = addMemoBinding.titleText.text.toString()
        val newMemoContent = addMemoBinding.memoContentText.text.toString()
        val newMemo = Memo(memoTitle = newMemoTitle, memoContent = newMemoContent)
        addMemoFragmentFragmentViewModel.insertMemoToDatabase(newMemo)
    }

}