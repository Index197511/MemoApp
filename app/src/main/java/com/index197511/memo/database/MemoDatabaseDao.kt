package com.index197511.memo.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MemoDatabaseDao {

    @Insert
    suspend fun insert(memo: MemoEntity)

    @Update
    suspend fun update(memo: MemoEntity)

    @Delete
    suspend fun delete(memo: MemoEntity)

    @Query("SELECT * FROM memo_table ORDER BY id DESC")
    fun loadAllMemo(): Flow<List<MemoEntity>>
}