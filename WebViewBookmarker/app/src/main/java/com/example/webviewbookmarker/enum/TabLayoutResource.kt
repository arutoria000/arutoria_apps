package com.example.webviewbookmarker.enum

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.webviewbookmarker.R
import com.example.webviewbookmarker.fragment.FavoriteFragment
import com.example.webviewbookmarker.fragment.MemoFragment
import com.example.webviewbookmarker.fragment.NewsFragment
import com.example.webviewbookmarker.fragment.SettingFragment

/**
 * タブレイアウトのリソース管理.
 */
enum class TabLayoutResource {

    /**
     * お気に入り.
     */
    TAB_FAVORITE {
        override fun getFragment(): Fragment {
            // お気に入りフラグメント
            return FavoriteFragment()
        }

        override fun getTabIcon(context: Context): Drawable? {
            // お気に入りアイコン
            return ResourcesCompat.getDrawable(context.resources, R.drawable.hart_white, null)
        }

        override fun getTabTitle(context: Context): String {
            // お気に入りタイトル
            return context.getString(R.string.tab_title_favorite)
        }
    },

    /**
     * お知らせ.
     */
    TAB_NEWS {
        override fun getFragment(): Fragment {
            // お知らせフラグメント
            return NewsFragment()
        }

        override fun getTabIcon(context: Context): Drawable? {
            // お知らせアイコン
            return ResourcesCompat.getDrawable(context.resources, R.drawable.news_white, null)
        }

        override fun getTabTitle(context: Context): String {
            // お知らせタイトル
            return context.getString(R.string.tab_title_news)
        }
    },

    /**
     * メモ.
     */
    TAB_MEMO {
        override fun getFragment(): Fragment {
            // メモフラグメント
            return MemoFragment()
        }

        override fun getTabIcon(context: Context): Drawable? {
            // メモアイコン
            return ResourcesCompat.getDrawable(context.resources, R.drawable.memo_white, null)
        }

        override fun getTabTitle(context: Context): String {
            // メモタイトル
            return context.getString(R.string.tab_title_memo)
        }
    },

    /**
     * 設定.
     */
    TAB_SETTING {
        override fun getFragment(): Fragment {
            return SettingFragment()
        }

        override fun getTabIcon(context: Context): Drawable? {
            // 設定アイコン
            return ResourcesCompat.getDrawable(context.resources, R.drawable.setting_white, null)
        }

        override fun getTabTitle(context: Context): String {
            // 設定タイトル
            return context.getString(R.string.tab_title_setting)
        }
    }

    ;
    /**
     * タブに表示するフラグメントを返却する.
     */
    abstract fun getFragment(): Fragment

    /**
     * タブに表示するアイコンを返却する.
     */
    abstract fun getTabIcon(context: Context): Drawable?

    /**
     * タブに表示するタイトルを返却する.
     */
    abstract fun getTabTitle(context: Context): String

}