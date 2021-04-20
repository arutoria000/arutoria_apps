package com.example.webviewbookmarker.database.dao

import androidx.room.*
import com.example.webviewbookmarker.database.room.BookmarkInfo

/**
 * ブックマークデータアクセスオブジェクト.
 */
@Dao
interface BookmarkInfoDao {

    @Query("SELECT * FROM bookmarkinfo")
    fun getAllBookmarkInfo() : List<BookmarkInfo>

    @Insert
    fun insert(bookmarkInfo: BookmarkInfo)

    @Update
    fun update(bookmarkInfo: BookmarkInfo)

    @Delete
    fun delete(bookmarkInfo: BookmarkInfo)

}