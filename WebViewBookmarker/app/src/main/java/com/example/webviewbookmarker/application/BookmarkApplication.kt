package com.example.webviewbookmarker.application

import android.app.Application
import com.example.webviewbookmarker.database.BookmarkDatabase

class BookmarkApplication: Application() {

    companion object {
        lateinit var instance: BookmarkApplication
        lateinit var database: BookmarkDatabase
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = BookmarkDatabase.getInstance(applicationContext)
    }

}