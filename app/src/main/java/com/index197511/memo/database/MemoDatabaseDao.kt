package com.index197511.memo.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MemoDatabaseDao {
    @Insert
    fun insert(memo: Memo)

    @Update
    fun update(memo: Memo)

    @Query("SELECt * from memo_table WHERE memoId = :key")
    fun get(key: Int): Memo?

    @Query("SELECT * FROM memo_table ORDER BY memoId DESC")
    fun getAllMemo(): LiveData<List<Memo>>

    @Query("DELETE FROM memo_table WHERE memoId = :key")
    fun delete(key: Int)

}