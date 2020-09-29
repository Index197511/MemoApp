package com.index197511.memo.database

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Memo(
    val id: Int,
    val title: String,
    val content: String
) : Parcelable