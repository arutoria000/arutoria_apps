package com.example.webviewbookmarker.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.webviewbookmarker.R

/**
 * トップ画面.
 */
class TopActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // View設定
        setContentView(R.layout.activity_top)
    }
}