package com.index197511.memo.addmemo

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.index197511.memo.R
import com.index197511.memo.database.Memo
import com.index197511.memo.database.MemoDatabase
import com.index197511.memo.databinding.AddMemoFragmentBinding

class AddMemoFragment : Fragment() {

    private lateinit var addMemoFragmentViewModel: AddMemoFragmentViewModel
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
        addMemoFragmentViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(AddMemoFragmentViewModel::class.java)

        setHasOptionsMenu(true)

        return addMemoBinding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.save_button, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        this.insertMemoToDatabase()
        this.closeKeyboard()

        return NavigationUI.onNavDestinationSelected(
            item,
            view!!.findNavController()
        )
                || super.onOptionsItemSelected(item)
    }


    private fun insertMemoToDatabase() {
        val newMemo = Memo(
            memoTitle = addMemoBinding.titleText.text.toString(),
            memoContent = addMemoBinding.memoContentText.text.toString()
        )
        addMemoFragmentViewModel.insertMemoToDatabase(newMemo)
    }

    private fun closeKeyboard() {
        val inputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view!!.windowToken, 0)
    }
}