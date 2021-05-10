package com.example.webviewbookmarker.database.room

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * ブックマークエンティティ.
 */
@Entity
data class BookmarkInfo (

    /**
     * 主キー(int).
     */
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    /**
     * WebURL.
     */
    val web_url: String,

    /**
     * アイコンパス.
     */
    val icon_path: String,

    /**
     * ブックマークのテキスト.
     */
    val top_text: String
)