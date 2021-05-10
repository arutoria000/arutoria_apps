package com.example.webviewbookmarker.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.webviewbookmarker.database.room.BookmarkInfo
import com.example.webviewbookmarker.repository.BookmarkInfoRepository
import kotlinx.coroutines.launch

/**
 * ブックマークデータViewModel.
 */
class BookmarkViewModel() : ViewModel() {

    /**
     * ブックマークデータリポジトリ.
     */
    private val bookmarkInfoRepository: BookmarkInfoRepository = BookmarkInfoRepository()

    /**
     * ブックマークデータ本体.
     */
    private val mBookmarkInfoLiveData: MutableLiveData<List<BookmarkInfo>> by lazy { MutableLiveData() }

    /**
     * ブックマークデータ全件取得.
     */
    fun getBookmarkList() {
        viewModelScope.launch {
            mBookmarkInfoLiveData.postValue(bookmarkInfoRepository.getBookmarkList())
        }
    }

    /**
     * ブックマーク情報を1件追加.
     */
    fun insertBookmarkData(webUrl: String, iconPath: String, bookmarkText: String) {
        viewModelScope.launch {
            bookmarkInfoRepository.insertBookmarkData(BookmarkInfo(0, webUrl, iconPath, bookmarkText))
            mBookmarkInfoLiveData.postValue(bookmarkInfoRepository.getBookmarkList())
        }
    }

    /**
     * ViewModel管理のLiveData返却.
     */
    fun getLiveData(): MutableLiveData<List<BookmarkInfo>> {
        return mBookmarkInfoLiveData
    }
}