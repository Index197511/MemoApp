package com.index197511.memo.addmemo

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.index197511.memo.database.Memo
import com.index197511.memo.repository.IMemoRepository
import kotlinx.coroutines.launch

class AddMemoFragmentViewModel @ViewModelInject constructor(
    private val repository: IMemoRepository
) : ViewModel() {
    fun insertMemo(memo: Memo) {
        viewModelScope.launch {
            repository.insert(memo)
        }
    }
}
