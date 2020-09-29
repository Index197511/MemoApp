package com.index197511.memo.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.index197511.memo.database.Memo
import com.index197511.memo.repository.IMemoRepository
import kotlinx.coroutines.launch

class HomeFragmentViewModel @ViewModelInject constructor(
    private val repository: IMemoRepository
) : ViewModel() {
    val memos: LiveData<List<Memo>> = repository.loadAllMemo().asLiveData()

    fun delete(memo: Memo) {
        viewModelScope.launch {
            repository.delete(memo)
        }
    }
}
