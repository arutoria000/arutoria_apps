package com.example.webviewbookmarker.enum

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import com.example.webviewbookmarker.R

/**
 * フローティングアクションボタンのリソース管理.
 */
enum class FloatingActionButtonResource {

    /**
     * お気に入り.
     */
    FAB_FAVORITE {
        override fun getIconDrawable(context: Context): Drawable? {
            return ResourcesCompat.getDrawable(context.resources, R.drawable.search_white, null)
        }

        override fun isShowFloatingActionButton(): Boolean {
            return true
        }
    },

    /**
     * お知らせ.
     */
    FAB_NEWS {
        override fun getIconDrawable(context: Context): Drawable? {
            return ResourcesCompat.getDrawable(context.resources, R.drawable.reply_white, null)
        }

        override fun isShowFloatingActionButton(): Boolean {
            return true
        }
    },

    /**
     * メモ.
     */
    FAB_MEMO {
        override fun getIconDrawable(context: Context): Drawable? {
            return ResourcesCompat.getDrawable(context.resources, R.drawable.pen_white, null)
        }

        override fun isShowFloatingActionButton(): Boolean {
            return true
        }
    },

    /**
     * 設定.
     */
    FAB_SETTING {
        override fun getIconDrawable(context: Context): Drawable? {
            return null
        }

        override fun isShowFloatingActionButton(): Boolean {
            return false
        }
    }
    ;

    /**
     * フローティングアクションボタンに表示する画像を返却する.
     */
    abstract fun getIconDrawable(context: Context): Drawable?

    /**
     * フローティングボタンアクションボタンを表示するかどうか.
     */
    abstract fun isShowFloatingActionButton(): Boolean

}