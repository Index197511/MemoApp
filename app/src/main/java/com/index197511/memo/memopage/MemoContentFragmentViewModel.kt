package com.index197511.memo.memopage

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.index197511.memo.database.Memo
import com.index197511.memo.repository.IMemoRepository
import kotlinx.coroutines.launch

class MemoContentFragmentViewModel @ViewModelInject constructor(
    private val repository: IMemoRepository
) : ViewModel() {
    fun update(memo: Memo) {
        viewModelScope.launch {
            repository.update(memo)
        }
    }
}
