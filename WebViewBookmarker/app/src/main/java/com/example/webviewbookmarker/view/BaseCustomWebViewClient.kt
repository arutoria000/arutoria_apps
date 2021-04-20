package com.example.webviewbookmarker.view

import android.graphics.Bitmap
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import org.jetbrains.annotations.NotNull

class BaseCustomWebViewClient(@NotNull onPageLoadStateChangeListener: OnPageLoadStateChangeListener): WebViewClient() {

    /**
     * WebViewのページロードステータス変更検知リスナー.
     */
    interface OnPageLoadStateChangeListener {
        /**
         * ページロード開始.
         */
        fun onPageLoadStart()

        /**
         * ページロード終了.
         */
        fun onPageLoadFinish()
    }

    /**
     * ページロードステータス変更検知リスナー.
     */
    private val mOnPageLoadStateChangeListener: OnPageLoadStateChangeListener = onPageLoadStateChangeListener

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        // 開始通知
        mOnPageLoadStateChangeListener.onPageLoadStart()
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        // 終了通知
        mOnPageLoadStateChangeListener.onPageLoadFinish()
    }

}