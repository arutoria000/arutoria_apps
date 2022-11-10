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
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.example.webviewbookmarker.R
import com.example.webviewbookmarker.view.BaseCustomWebViewClient

/**
 * カスタムWebView本体.
 */
class CustomTabsWebViewFragment(onEditStateChangeListener: OnEditStateChangeListener): Fragment(), BaseCustomWebViewClient.OnPageLoadStateChangeListener {

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
     * ルートView.
     */
    private lateinit var mRootView: View

    /**
     * EditText本体.
     */
    private lateinit var mEditText: EditText

    /**
     * WebView本体.
     */
    private lateinit var mWebView: WebView

    /**
     * OnBackPressedCallback.
     */
    private lateinit var mCallbacks: OnBackPressedCallback

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // バックキーの操作をフラグメントでキャッチする
        mCallbacks = requireActivity().onBackPressedDispatcher.addCallback(this) {
            closeSerachBar()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // root取得
        mRootView = inflater.inflate(R.layout.fragment_custom_tabs_webview, container, false)
        initializeEditText()
        initializeWebView()
        return mRootView
    }

    /**
     * WebViewの初期化処理.
     */
    private fun initializeWebView() {
        mWebView = mRootView.findViewById(R.id.webview_layout)
        mWebView.settings.javaScriptEnabled = true
        mWebView.settings.loadWithOverviewMode = true
        mWebView.settings.cacheMode = WebSettings.LOAD_NO_CACHE
        mWebView.settings.setSupportMultipleWindows(false)
        mWebView.settings.javaScriptCanOpenWindowsAutomatically = false
        mWebView.isVerticalScrollBarEnabled = false
        mWebView.isHorizontalScrollBarEnabled = false
        mWebView.webViewClient = BaseCustomWebViewClient(this)
    }

    /**
     * EditTextの初期化処理.
     */
    private fun initializeEditText() {
        // EditText取得
        mEditText = mRootView.findViewById(R.id.edit_text_search_bar)
        mEditText.visibility = View.VISIBLE
        mEditText.text.clear()
        mEditText.setOnFocusChangeListener { edit, hasFocus ->
            // フォーカスの状態を保持
            val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            // フォーカスの状態に応じてアニメーションを実行
            if (hasFocus) {
                // フォーカスされた場合
                mOnEditStateChangeListener.onViewFocus()
                mCallbacks.isEnabled = true
                inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS)
            } else {
                // フォーカスを外した場合
                inputMethodManager.hideSoftInputFromWindow(edit.windowToken, 0)
                closeSerachBar()
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
        // URLのロード
        mWebView.loadUrl(getString(R.string.search_google_url) + param)
        // バックキー制御のためのキーリスナー設定
        mWebView.setOnKeyListener { _, keyCode, event ->
            (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN).apply {
                if (mWebView.canGoBack()) {
                    // 戻ることができる履歴が存在する
                    mWebView.goBack()
                } else {
                    // 戻ることができる履歴が存在しない
                    closeSerachBar()
                }
            }
        }
    }

    /**
     * 検索バーを閉じる時の処理.
     */
    private fun closeSerachBar() {
        mWebView.setOnKeyListener(null)
        mWebView.visibility = View.GONE
        initializeEditText()
        mEditText.isFocusable = false
        mOnEditStateChangeListener.onViewHideFocus()
        mCallbacks.isEnabled = false
    }

    override fun onPageLoadStart() {
        // プログレスバーの表示
    }

    override fun onPageLoadFinish() {
        // プログレスバーの非表示
    }
}