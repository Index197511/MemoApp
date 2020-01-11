package com.index197511.memo.addmemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.index197511.memo.R
import com.index197511.memo.database.Memo
import com.index197511.memo.databinding.AddMemoFragmentBinding

class AddMemoFragment : Fragment() {

    private lateinit var addMemoFragmentViewModel: AddMemoViewModel
    private lateinit var addMemoBinding: AddMemoFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        addMemoBinding =
            DataBindingUtil.inflate(inflater, R.layout.add_memo_fragment, container, false)

        addMemoBinding.addMemoFragmentViewModel = addMemoFragmentViewModel

        return addMemoBinding.root

    }

    fun insertMemoToDatabase() {
        val newMemoTitle: String = addMemoBinding.titleText.text.toString()
        val newMemoContent = addMemoBinding.memoContentText.text.toString()
        val newMemo = Memo(memoTitle = newMemoTitle, memoContent = newMemoContent)
        addMemoFragmentViewModel.insertMemoToDatabase(newMemo)
    }

}