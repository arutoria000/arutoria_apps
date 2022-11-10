package com.example.webviewbookmarker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.webviewbookmarker.database.dao.BookmarkInfoDao
import com.example.webviewbookmarker.database.room.BookmarkInfo

/**
 * ブックマークデータベース本体.
 */
@Database(entities = [BookmarkInfo::class], version = 1)
abstract class BookmarkDatabase : RoomDatabase() {

    abstract fun bookmarkInfoDao(): BookmarkInfoDao

    companion object {

        private val instances: BookmarkDatabase?= null

        const val databaseName = "bookmark.db"

        fun getInstance(context: Context): BookmarkDatabase = instances?: synchronized(this) {
            Room.databaseBuilder(context, BookmarkDatabase::class.java, databaseName).build()
        }

    }

}