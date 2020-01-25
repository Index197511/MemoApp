package com.index197511.memo.memopage

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.index197511.memo.database.MemoDatabaseDao
import com.index197511.memo.repository.MemoRepository

class MemoContentFragmentViewModelFactory(
    private val application: Application,
    private val memoRepository: MemoRepository
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MemoContentFragmentViewModel::class.java)) {
            return MemoContentFragmentViewModel(
                application,
                memoRepository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}