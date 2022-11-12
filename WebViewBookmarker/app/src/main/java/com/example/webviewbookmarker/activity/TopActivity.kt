package com.example.webviewbookmarker.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.webviewbookmarker.databinding.ActivityTopBinding

/**
 * トップ画面.
 */
class TopActivity: AppCompatActivity() {

    /**
     * Viewバインディング.
     */
    private lateinit var mBinding: ActivityTopBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // View設定
        mBinding = ActivityTopBinding.inflate(layoutInflater)
        val view = mBinding.root
        setContentView(view)
    }
}