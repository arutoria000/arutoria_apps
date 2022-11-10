package com.example.webviewbookmarker.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.activity.addCallback
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import com.example.webviewbookmarker.R

/**
 * カスタムWebView本体.
 */
class CustomTabsWebViewFragment(onEditStateChangeListener: OnEditStateChangeListener) : Fragment() {

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
    private val mOnEditStateChangeListener : OnEditStateChangeListener = onEditStateChangeListener

    /**
     * EditText.
     */
    private lateinit var mEditText :EditText

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // バックキーの操作をフラグメントでキャッチする
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            mEditText.isFocusable = false
            mOnEditStateChangeListener.onViewHideFocus()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view : View = inflater.inflate(R.layout.fragment_custom_tabs_webview, container, false)
        mEditText = view.findViewById(R.id.edit_text_search_bar)
        mEditText.setOnFocusChangeListener { edit, hasFocus ->
            // フォーカスの状態を保持
            val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            // フォーカスの状態に応じてアニメーションを実行
            if (hasFocus) {
                mOnEditStateChangeListener.onViewFocus()
                inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS)
            } else {
                mOnEditStateChangeListener.onViewHideFocus()
                inputMethodManager.hideSoftInputFromWindow(edit.windowToken, 0)
            }
        }
        mEditText.setOnClickListener {
            mEditText.isFocusable = true
            mEditText.isFocusableInTouchMode = true
            mEditText.requestFocus()
        }

        return view
    }

    fun loadUrl() {
        val uri : Uri = Uri.parse("https://google.com")
        val customTabsIntentBuilder : CustomTabsIntent.Builder = CustomTabsIntent.Builder()
        val customTabsIntent : CustomTabsIntent = customTabsIntentBuilder.build()
        customTabsIntent.launchUrl(requireContext(), uri)
    }

}