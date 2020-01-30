package com.index197511.memo.memopage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.index197511.memo.database.Memo
import com.index197511.memo.database.MemoDatabaseDao
import com.index197511.memo.repository.MemoRepository
import kotlinx.coroutines.*

class MemoContentFragmentViewModel(
    application: Application,
    private val memoRepository: MemoRepository
) : AndroidViewModel(application) {

    fun updateMemo(memo: Memo) {
        viewModelScope.launch {
            memoRepository.update(memo)
        }
    }

}
