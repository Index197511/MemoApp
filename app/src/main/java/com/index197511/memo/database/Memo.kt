package com.index197511.memo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "memo_table")
data class Memo(
    @PrimaryKey(autoGenerate = true)
    var memoId: Long = 0L,

    @ColumnInfo(name = "memo_title")
    var memoTitle: String = "",

    @ColumnInfo(name = "memo_content")
    var memoContent: String = ""
)