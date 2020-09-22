package com.index197511.memo.addmemo

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.index197511.memo.database.Memo
import com.index197511.memo.repository.MemoRepository
import kotlinx.coroutines.runBlocking

class AddMemoFragmentViewModel @ViewModelInject constructor(
    private val repository: MemoRepository
) : ViewModel() {

    fun insertMemoToDatabase(memo: Memo) {
        runBlocking {
            repository.insert(memo)
        }
    }
}
