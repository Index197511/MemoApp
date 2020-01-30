package com.index197511.memo.addmemo

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.index197511.memo.R
import com.index197511.memo.database.Memo
import com.index197511.memo.databinding.AddMemoFragmentBinding
import com.index197511.memo.ext.closeKeyboard
import com.index197511.memo.repository.MemoRepository

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
        val memoRepository = MemoRepository.getInstance(application)

        val viewModelFactory =
            AddMemoViewModelFactory(
                application,
                memoRepository
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
        insertMemoToDatabase()
        closeKeyboard()

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

}