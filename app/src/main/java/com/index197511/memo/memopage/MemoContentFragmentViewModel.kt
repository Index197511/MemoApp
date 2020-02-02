package com.index197511.memo.memopage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.index197511.memo.database.Memo
import com.index197511.memo.repository.MemoRepository
import kotlinx.coroutines.launch

class MemoContentFragmentViewModel(
    application: Application
) : AndroidViewModel(application) {
    private var memoRepository: MemoRepository = MemoRepository.getInstance(application)

    fun updateMemo(memo: Memo) {
        viewModelScope.launch {
            memoRepository.update(memo)
        }
    }

}
