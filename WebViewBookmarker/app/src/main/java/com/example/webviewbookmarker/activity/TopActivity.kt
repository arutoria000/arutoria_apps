package com.example.webviewbookmarker.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.FragmentTransaction
import com.example.webviewbookmarker.R
import com.example.webviewbookmarker.fragment.CustomTabsWebViewFragment

/**
 * トップ画面.
 */
class TopActivity : AppCompatActivity(), CustomTabsWebViewFragment.OnEditStateChangeListener {

    private lateinit var mMotionLayout : MotionLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // view設定
        setContentView(R.layout.activity_top)

        val fragmentTransient :FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransient.add(R.id.custom_tabs_fragment_container, CustomTabsWebViewFragment(this), "CustomTabsWebViewFragment")
        fragmentTransient.commit()
        mMotionLayout = findViewById(R.id.motion_layout_top_activity)
    }

    override fun onViewFocus() {
        mMotionLayout.transitionToEnd()
    }

    override fun onViewHideFocus() {
        mMotionLayout.transitionToStart()
    }
}