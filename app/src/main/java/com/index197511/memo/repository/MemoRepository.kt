package com.index197511.memo.repository

import androidx.lifecycle.LiveData
import com.index197511.memo.database.Memo
import com.index197511.memo.database.MemoDatabaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MemoRepository @Inject constructor(
    private val dao: MemoDatabaseDao
) {
    var allMemos: LiveData<List<Memo>> = dao.getAllMemo()

    suspend fun insert(memo: Memo) {
        withContext(Dispatchers.IO) {
            dao.insert(memo)
            allMemos = dao.getAllMemo()
        }
    }

    suspend fun delete(memo: Memo) {
        withContext(Dispatchers.IO) {
            dao.delete(memo)
            allMemos = dao.getAllMemo()
        }
    }

    suspend fun update(memo: Memo) {
        withContext(Dispatchers.IO) {
            dao.update(memo)
        }
    }

}