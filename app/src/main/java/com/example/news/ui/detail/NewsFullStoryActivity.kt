package com.example.news.ui.detail

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.news.ui.base.BaseActivity
import com.example.news.util.showToast


class NewsFullStoryActivity : BaseActivity() {

    private lateinit var mWebview: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mWebview = WebView(this)
        mWebview.settings.javaScriptEnabled = true // enable javascript
        mWebview.webViewClient = object : WebViewClient() {
            override fun onReceivedError(
                view: WebView,
                errorCode: Int,
                description: String,
                failingUrl: String
            ) {
                showToast(description)
            }

            @TargetApi(Build.VERSION_CODES.M)
            override fun onReceivedError(
                view: WebView,
                req: WebResourceRequest,
                rerr: WebResourceError
            ) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(
                    view,
                    rerr.errorCode,
                    rerr.description.toString(),
                    req.url.toString()
                )
            }
        }

        intent?.getStringExtra(URL)?.let {
            mWebview.loadUrl(it)
        }

        setContentView(mWebview)
    }

    companion object {
        private const val URL = "URL"

        fun getIntent(context: Context, url: String?): Intent =
            Intent(context, NewsFullStoryActivity::class.java).also {
                it.putExtra(URL, url)
            }

    }

}