package com.index197511.memo.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.index197511.memo.database.Memo
import com.index197511.memo.database.MemoDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MemoRepository(context: Context) {
    private val database = MemoDatabase.getInstance(context).memoDatabaseDao
    var allMemos: LiveData<List<Memo>> = database.getAllMemo()

    companion object {
        private var instance: MemoRepository? = null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: MemoRepository(
                context.applicationContext
            ).also {
                instance = it
            }
        }
    }

    suspend fun insert(memo: Memo) {
        withContext(Dispatchers.IO) {
            database.insert(memo)
            allMemos = database.getAllMemo()
        }
    }

    suspend fun delete(memo: Memo) {
        withContext(Dispatchers.IO) {
            database.delete(memo)
            allMemos = database.getAllMemo()
        }
    }

    suspend fun update(memo: Memo) {
        withContext(Dispatchers.IO) {
            database.update(memo)
        }
    }

}