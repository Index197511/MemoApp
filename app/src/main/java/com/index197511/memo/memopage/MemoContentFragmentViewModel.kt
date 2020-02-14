package com.index197511.memo.memopage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.index197511.memo.database.Memo
import com.index197511.memo.repository.MemoRepository
import kotlinx.coroutines.launch

class MemoContentFragmentViewModel(private val repository: MemoRepository) :
    ViewModel() {

    fun updateMemo(memo: Memo) {
        viewModelScope.launch {
            repository.update(memo)
        }
    }
}
