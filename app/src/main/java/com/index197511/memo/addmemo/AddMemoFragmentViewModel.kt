package com.index197511.memo.addmemo

import androidx.lifecycle.ViewModel
import com.index197511.memo.database.Memo
import com.index197511.memo.repository.MemoRepository
import kotlinx.coroutines.runBlocking

class AddMemoFragmentViewModel(private val repository: MemoRepository) :
    ViewModel() {

    fun insertMemoToDatabase(memo: Memo) {
        runBlocking {
            repository.insert(memo)
        }
    }
}
