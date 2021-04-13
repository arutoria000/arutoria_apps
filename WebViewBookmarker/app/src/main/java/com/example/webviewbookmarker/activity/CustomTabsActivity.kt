package com.example.webviewbookmarker.activity

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import com.example.webviewbookmarker.R

/**
 * WebView本体Activity.
 */
class CustomTabsActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_tabs_webview)

        val uri : Uri = Uri.parse("https://google.com")
        val customTabsIntentBuilder : CustomTabsIntent.Builder = CustomTabsIntent.Builder()
        val customTabsIntent : CustomTabsIntent = customTabsIntentBuilder.build()
        customTabsIntent.launchUrl(this, uri)
    }
}