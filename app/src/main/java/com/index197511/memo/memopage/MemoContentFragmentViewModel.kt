package com.index197511.memo.memopage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.index197511.memo.database.Memo
import com.index197511.memo.database.MemoDatabaseDao
import kotlinx.coroutines.*

class MemoContentFragmentViewModel(
    val database: MemoDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun reflectMemoChange(memo: Memo) {
        uiScope.launch {
            update(memo)
        }
    }

    private suspend fun update(memo: Memo) {
        withContext(Dispatchers.IO) {
            database.update(memo)
        }
    }

}
