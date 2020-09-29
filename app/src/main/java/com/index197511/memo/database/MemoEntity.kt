package com.index197511.memo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memo_table")
data class MemoEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "content")
    var content: String
) {
    fun toModel(): Memo =
        Memo(id, title, content)
}

fun Memo.toEntity(): MemoEntity =
    MemoEntity(id, title, content)