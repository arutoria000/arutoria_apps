package com.example.webviewbookmarker.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.webviewbookmarker.R
import com.example.webviewbookmarker.database.room.BookmarkInfo
import java.util.zip.Inflater
import kotlin.coroutines.coroutineContext

/**
 * Webコンテンツブックマーク一覧表示用Adapter.
 */
class WebContentsAdapter(val bookmarkinfo: List<BookmarkInfo>, val context: Context) : RecyclerView.Adapter<WebContentsAdapter.WebContentsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WebContentsViewHolder {
        return WebContentsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_top_web_contents_card, parent, false))
    }

    override fun getItemCount(): Int {
        return bookmarkinfo.size
    }

    override fun onBindViewHolder(holder: WebContentsViewHolder, position: Int) {
        holder.mBookmarkIconImageView.setImageDrawable(ContextCompat.getDrawable(
            context, context.resources.getIdentifier(bookmarkinfo[position].icon_path, "drawable", context.packageName)))
        holder.mBookmarkTextView.text = bookmarkinfo[position].top_text
    }

    /**
     * Webコンテンツブックマークビューホルダー.
     */
    class WebContentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /**
         * ブックマークアイコン表示イメージビュー.
         */
        val mBookmarkIconImageView: ImageView = itemView.findViewById(R.id.image_view_web_contents_icon)

        /**
         * ブックマークテキスト表示ビュー.
         */
        val mBookmarkTextView: TextView = itemView.findViewById(R.id.text_view_web_contents_title)

    }
}