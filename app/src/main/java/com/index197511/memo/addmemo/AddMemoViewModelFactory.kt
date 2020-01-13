package com.index197511.memo.addmemo

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.index197511.memo.database.MemoDatabaseDao

class AddMemoViewModelFactory(
    private val dataSource: MemoDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddMemoFragmentViewModel::class.java)) {
            return AddMemoFragmentViewModel(
                dataSource,
                application
            ) as T
        }
    }
}