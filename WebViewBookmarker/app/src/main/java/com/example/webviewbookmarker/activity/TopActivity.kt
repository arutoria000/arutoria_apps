package com.example.webviewbookmarker.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.webviewbookmarker.R

/**
 * トップ画面.
 */
class TopActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // view設定
        setContentView(R.layout.activity_top)
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {

        val view : View = findViewById(R.id.fab_search_button)
        view.setOnClickListener{
            val intent = Intent(this, CustomTabsActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}