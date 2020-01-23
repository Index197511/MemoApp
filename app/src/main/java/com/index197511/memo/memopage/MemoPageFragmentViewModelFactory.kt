package com.index197511.memo.memopage

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.index197511.memo.database.MemoDatabaseDao

class MemoPageFragmentViewModelFactory(
    private val dataSource: MemoDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MemoPageFragmentViewModel::class.java)) {
            return MemoPageFragmentViewModel(
                dataSource,
                application
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}