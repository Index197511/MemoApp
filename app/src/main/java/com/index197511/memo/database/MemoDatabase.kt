package com.index197511.memo.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MemoEntity::class], version = 1, exportSchema = false)
abstract class MemoDatabase : RoomDatabase() {
    abstract fun memoDatabaseDao(): MemoDatabaseDao
}