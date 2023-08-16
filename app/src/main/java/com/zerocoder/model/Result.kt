package com.zerocoder.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( tableName = "quote")
data class Result(
    @PrimaryKey(true)
    val quoteId:Int,
    val _id: String,
    val author: String,
    val authorSlug: String,
    val content: String,
    val dateAdded: String,
    val dateModified: String,
    val length: Int,
)