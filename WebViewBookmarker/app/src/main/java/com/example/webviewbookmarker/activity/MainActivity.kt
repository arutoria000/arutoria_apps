package com.example.webviewbookmarker.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * メインActivity.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // トップ画面を起動
        val intent = Intent(this, TopActivity::class.java)
        startActivity(intent)
    }
}