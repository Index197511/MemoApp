package com.index197511.memo.addmemo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.index197511.memo.database.Memo
import com.index197511.memo.database.MemoDatabaseDao
import com.index197511.memo.repository.MemoRepository
import kotlinx.coroutines.*

class AddMemoFragmentViewModel(application: Application, private val memoRepository: MemoRepository) :
    AndroidViewModel(application) {

    fun insertMemoToDatabase(memo: Memo) {
        runBlocking {
            memoRepository.insert(memo)
        }
    }

}
