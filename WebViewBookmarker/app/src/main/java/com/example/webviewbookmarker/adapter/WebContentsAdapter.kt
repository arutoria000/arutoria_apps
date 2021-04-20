package com.example.webviewbookmarker.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.webviewbookmarker.R

/**
 * Webコンテンツブックマーク一覧表示用Adapter.
 */
class WebContentsAdapter : RecyclerView.Adapter<WebContentsAdapter.WebContentsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WebContentsViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: WebContentsViewHolder, position: Int) {
        TODO("Not yet implemented")
    }


    /**
     * Webコンテンツブックマークビューホルダー.
     */
    class WebContentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /**
         * ブックマークアイコン表示イメージビュー.
         */
        private val mBookmarkIconImageView: ImageView = itemView.findViewById(R.id.image_view_web_contents_icon)

        /**
         * ブックマークテキスト表示ビュー.
         */
        private val mBookmarkTextView: TextView = itemView.findViewById(R.id.text_view_web_contents_title)

    }
}