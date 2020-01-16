package com.index197511.memo.database

import androidx.room.*

@Dao
interface MemoDatabaseDao {
    @Insert
    fun insert(memo: Memo)

    @Update
    fun update(memo: Memo)

    @Query("SELECT * FROM memo_table ORDER BY memoId DESC")
    fun getAllMemo(): List<Memo>

    @Delete
    fun delete(memo: Memo)


}