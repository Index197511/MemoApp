package com.index197511.memo.addmemo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.index197511.memo.database.Memo
import com.index197511.memo.database.MemoDatabaseDao
import kotlinx.coroutines.*

class AddMemoFragmentViewModel(val database: MemoDatabaseDao, application: Application) :
    AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private suspend fun insert(memo: Memo) {
        withContext(Dispatchers.IO) {
            database.insert(memo)
        }

    }

    fun insertMemoToDatabase(memo: Memo) {
        runBlocking {
            insert(memo)
        }
    }

}
