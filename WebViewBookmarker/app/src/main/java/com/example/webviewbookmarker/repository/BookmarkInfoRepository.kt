package com.example.webviewbookmarker.repository

import com.example.webviewbookmarker.application.BookmarkApplication
import com.example.webviewbookmarker.database.room.BookmarkInfo

/**
 * ブックマーク情報リポジトリ.
 */
class BookmarkInfoRepository {

    /**
     * ブックマーク情報を全件取得.
     */
    suspend fun getBookmarkList(): List<BookmarkInfo> {
        return BookmarkApplication.database.bookmarkInfoDao().getAllBookmarkInfo()
    }

    /**
     * ブックマーク情報を1件登録.
     */
    suspend fun insertBookmarkData(bookmarkInfo: BookmarkInfo) {
        BookmarkApplication.database.bookmarkInfoDao().insert(bookmarkInfo)
    }

    /**
     * ブックマーク情報を1件削除.
     */
    suspend fun deleteBookmark(bookmarkInfo: BookmarkInfo) {
        BookmarkApplication.database.bookmarkInfoDao().delete(bookmarkInfo)
    }

}