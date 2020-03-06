package com.example.news

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.news.util.showToast


class NewsDetail : BaseActivity() {

    private lateinit var mWebview: WebView

    override fun onCreate(savedInstanceState: Bundle?) {

        controllerComponent.inject(this)

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

        intent?.let {
            mWebview.loadUrl(it.getStringExtra(URL))
        }

        setContentView(mWebview)
    }

    companion object {

        const val URL = "url"
    }

}