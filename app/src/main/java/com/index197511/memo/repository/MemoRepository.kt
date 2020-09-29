package com.index197511.memo.repository

import com.index197511.memo.database.Memo
import com.index197511.memo.database.MemoDatabaseDao
import com.index197511.memo.database.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface IMemoRepository {
    suspend fun insert(memo: Memo)
    suspend fun delete(memo: Memo)
    suspend fun update(memo: Memo)
    fun loadAllMemo(): Flow<List<Memo>>
}

class MemoRepository @Inject constructor(
    private val dao: MemoDatabaseDao
) : IMemoRepository {

    override suspend fun insert(memo: Memo) {
        withContext(Dispatchers.IO) {
            dao.insert(memo.toEntity())
        }
    }

    override suspend fun delete(memo: Memo) {
        withContext(Dispatchers.IO) {
            dao.delete(memo.toEntity())
        }
    }

    override suspend fun update(memo: Memo) {
        withContext(Dispatchers.IO) {
            dao.update(memo.toEntity())
        }
    }

    override fun loadAllMemo(): Flow<List<Memo>> {
        return dao.loadAllMemo().map { memos -> memos.map { it.toModel() } }
    }
}