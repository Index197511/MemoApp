package com.index197511.memo.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "memo_table")
data class Memo(
    @PrimaryKey(autoGenerate = true)
    var memoId: Int = 0,

    @ColumnInfo(name = "memo_title")
    var memoTitle: String = "",

    @ColumnInfo(name = "memo_content")
    var memoContent: String = ""
) : Parcelable