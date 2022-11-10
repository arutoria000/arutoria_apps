package com.example.webviewbookmarker.fragment

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.EditText
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.example.webviewbookmarker.R
import com.example.webviewbookmarker.view.BaseCustomWebViewClient

/**
 * カスタムWebView本体.
 */
class CustomTabsWebViewFragment(onEditStateChangeListener: OnEditStateChangeListener): Fragment() {

    /**
     * EditTextの選択状態リスナー.
     */
    interface OnEditStateChangeListener {
        /**
         * EditTextをフォーカスした.
         */
        fun onViewFocus()

        /**
         * EditTextのフォーカスを外した.
         */
        fun onViewHideFocus()
    }

    /**
     * EditTextの選択リスナー.
     */
    private val mOnEditStateChangeListener: OnEditStateChangeListener = onEditStateChangeListener

    /**
     * EditText本体.
     */
    private lateinit var mEditText: EditText

    /**
     * WebView本体.
     */
    private lateinit var mWebView: WebView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // バックキーの操作をフラグメントでキャッチする
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            mEditText.isFocusable = false
            mOnEditStateChangeListener.onViewHideFocus()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // root取得
        val view: View = inflater.inflate(R.layout.fragment_custom_tabs_webview, container, false)
        initializeEditText(view)
        initializeWebView(view)
        return view
    }

    /**
     * WebViewの初期化処理.
     * @param rootView View
     */
    private fun initializeWebView(rootView: View) {
        mWebView = rootView.findViewById<WebView>(R.id.webview_layout)
        mWebView.settings.javaScriptEnabled = true
        mWebView.settings.loadWithOverviewMode = true
        mWebView.settings.cacheMode = WebSettings.LOAD_NO_CACHE
        mWebView.settings.setSupportMultipleWindows(false)
        mWebView.settings.javaScriptCanOpenWindowsAutomatically = false
        mWebView.isVerticalScrollBarEnabled = false
        mWebView.isHorizontalScrollBarEnabled = false
        mWebView.settings.builtInZoomControls = true
        mWebView.webViewClient = BaseCustomWebViewClient()
    }

    /**
     * EditTextの初期化処理.
     * @param rootView View
     */
    private fun initializeEditText(rootView: View) {
        // EditText取得
        mEditText = rootView.findViewById(R.id.edit_text_search_bar)
        mEditText.setOnFocusChangeListener { edit, hasFocus ->
            // フォーカスの状態を保持
            val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            // フォーカスの状態に応じてアニメーションを実行
            if (hasFocus) {
                // フォーカスされた場合
                mOnEditStateChangeListener.onViewFocus()
                inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS)
            } else {
                // フォーカスを外した場合
                mOnEditStateChangeListener.onViewHideFocus()
                inputMethodManager.hideSoftInputFromWindow(edit.windowToken, 0)
                mEditText.text.clear()
            }
        }
        // クリックリスナー設定
        mEditText.setOnClickListener {
            mEditText.isFocusable = true
            mEditText.isFocusableInTouchMode = true
            mEditText.requestFocus()
        }
        // ソフトウェアキーボード操作リスナー
        mEditText.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_ENTER) {
                // Enterキー押下時の動作
                val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS)
                loadUrl(mEditText.text.toString())
            }
            false
        }
    }

    /**
     * WebViewでURLをロードする.
     * @param param 検索パラメータ
     */
    private fun loadUrl(param: String) {
        // EditTextを隠すのでリスナーを解除する
        mEditText.onFocusChangeListener = null
        mEditText.setOnKeyListener(null)
        mEditText.setOnClickListener(null)
        mWebView.visibility = View.VISIBLE
        mWebView.loadUrl(getString(R.string.search_google_url) + param)
    }

}