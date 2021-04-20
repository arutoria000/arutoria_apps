package com.example.webviewbookmarker.database.room

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * ブックマークエンティティ.
 */
@Entity
data class BookmarkInfo (

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val web_url: String,

    val icon_path: String
)