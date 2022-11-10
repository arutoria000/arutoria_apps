package com.example.webviewbookmarker.database.dao

import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.webviewbookmarker.database.room.BookmarkInfo

/**
 * ブックマークデータアクセスオブジェクト.
 */
@Dao
interface BookmarkInfoDao {

    /**
     * 全件取得.
     */
    @Query("SELECT * FROM BookmarkInfo")
    suspend fun getAllBookmarkInfo() : List<BookmarkInfo>

    /**
     * 1件追加.
     */
    @Insert
    suspend fun insert(bookmarkInfo: BookmarkInfo)

    /**
     * 1件更新.
     */
    @Update
    suspend fun update(bookmarkInfo: BookmarkInfo)

    /**
     * 1件削除.
     */
    @Delete
    suspend fun delete(bookmarkInfo: BookmarkInfo)

}