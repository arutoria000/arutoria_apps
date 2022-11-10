package com.example.webviewbookmarker.activity

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.webviewbookmarker.R
import com.example.webviewbookmarker.adapter.WebContentsAdapter
import com.example.webviewbookmarker.database.room.BookmarkInfo
import com.example.webviewbookmarker.fragment.CustomTabsWebViewFragment
import com.example.webviewbookmarker.viewmodel.BookmarkViewModel

/**
 * トップ画面.
 */
class TopActivity: AppCompatActivity(), CustomTabsWebViewFragment.OnEditStateChangeListener, Observer<List<BookmarkInfo>> {

    /**
     * MotionLayout本体.
     */
    private lateinit var mMotionLayout: MotionLayout

    /**
     * ブックマークデータのRecyclerView.
     */
    private lateinit var mRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        // view設定
        setContentView(R.layout.activity_top)
        // Fragment設定
        val fragmentTransient: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransient.add(R.id.custom_tabs_fragment_container,
            CustomTabsWebViewFragment(this), "CustomTabsWebViewFragment")
        fragmentTransient.commit()
        // MotionLayout設定
        mMotionLayout = findViewById(R.id.motion_layout_top_activity)
        // ブックマーク情報ViewModle設定
        val viewModel: BookmarkViewModel by viewModels()
        viewModel.getLiveData().observe(this, this)
        // RecyclerViewの設定
        mRecyclerView = findViewById(R.id.recyclerview_top_web_contents)
        mRecyclerView.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(this, 4, LinearLayoutManager.HORIZONTAL, false)
        mRecyclerView.layoutManager = gridLayoutManager
        // 初回データ設定
        firstSettingData(viewModel)
    }

    /**
     * 初回だけデフォルトのデータを設定する.
     */
    private fun firstSettingData(viewModel: BookmarkViewModel) {
        // SharedPreferencesを取得
        val sharedPreferences = getPreferences(MODE_PRIVATE)
        if (!sharedPreferences.getBoolean(getString(R.string.shared_preferences_key_first_session_data_insert), false)) {
            // 初回データ設定が終わっていない場合に実行
            viewModel.insertBookmarkData(getString(R.string.search_google_url),
                getString(R.string.search_default_icon_path), getString(R.string.search_default_text))
            sharedPreferences.edit()
            .putBoolean(getString(R.string.shared_preferences_key_first_session_data_insert), true)
            .apply()
        } else {
            viewModel.getBookmarkList()
        }
    }

    override fun onViewFocus() {
        // EditTextにフォーカスした場合の動作
        mMotionLayout.transitionToEnd()
        supportActionBar?.hide()
        actionBar?.hide()
    }

    override fun onViewHideFocus() {
        // EditTextかフォーカスが外された場合の動作
        mMotionLayout.transitionToStart()
        supportActionBar?.show()
        actionBar?.show()
    }

    override fun onChanged(bookmarkinfo: List<BookmarkInfo>) {
        // ブックマークデータの更新通知
        mRecyclerView.adapter = WebContentsAdapter(bookmarkinfo, this)
        mRecyclerView.adapter?.notifyDataSetChanged()
    }
}