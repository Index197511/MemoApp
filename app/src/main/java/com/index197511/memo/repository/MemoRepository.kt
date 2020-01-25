package com.index197511.memo.repository

import android.app.Application
import com.index197511.memo.database.Memo
import com.index197511.memo.database.MemoDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MemoRepository(application: Application) {
    private val database = MemoDatabase.getInstance(application).memoDatabaseDao

    suspend fun insert(memo: Memo) {
        withContext(Dispatchers.IO) {
            database.insert(memo)
        }
    }

    suspend fun getAllMemoFromDatabase(): List<Memo> {
        return withContext(Dispatchers.IO) {
            database.getAllMemo()
        }
    }

    suspend fun delete(memo: Memo) {
        withContext(Dispatchers.IO) {
            database.delete(memo)
        }
    }

    suspend fun update(memo: Memo) {
        withContext(Dispatchers.IO) {
            database.update(memo)
        }
    }

}