package com.index197511.memo.addmemo

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.index197511.memo.database.MemoDatabaseDao
import com.index197511.memo.repository.MemoRepository
import java.lang.IllegalArgumentException

class AddMemoViewModelFactory(
    private val application: Application,
    private val memoRepository: MemoRepository
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddMemoFragmentViewModel::class.java)) {
            return AddMemoFragmentViewModel(
                application,
                memoRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}