package com.dicoding.top10fpsgamesonlinepc

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Games(
    val name: String,
    val description: String,
    val photo: Int,
    val photoBackground: Int,
    val rating: String,
    val developer: String,
    val publisher: String,
    val url: String
) : Parcelable
