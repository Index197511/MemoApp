package com.index197511.memo.addmemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.index197511.memo.R
import com.index197511.memo.database.Memo
import com.index197511.memo.database.MemoDatabase
import com.index197511.memo.databinding.AddMemoFragmentBinding

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
        addMemoFragmentFragmentViewModel = ViewModelProviders.of(this, viewModelFactory).get(AddMemoFragmentViewModel::class.java)
        addMemoBinding.addMemoFragmentViewModel = addMemoFragmentFragmentViewModel
        return addMemoBinding.root

    }

    fun insertMemoToDatabase() {
        val newMemoTitle: String = addMemoBinding.titleText.text.toString()
        val newMemoContent = addMemoBinding.memoContentText.text.toString()
        val newMemo = Memo(memoTitle = newMemoTitle, memoContent = newMemoContent)
        addMemoFragmentFragmentViewModel.insertMemoToDatabase(newMemo)
    }

}