package br.com.poc_webview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.net.http.SslError
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.*
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

@SuppressLint("SetJavaScriptEnabled")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView.webViewClient = MyWebViewClient(this@MainActivity)
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.settings.builtInZoomControls = false
        webView.settings.displayZoomControls = false
        webView.settings.defaultTextEncodingName = "utf-8"

        webView.loadUrl("https://google.com")
    }

    private class MyWebViewClient(context: Context) : WebViewClient() {

        private val activity = context as MainActivity

        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            Toast.makeText(activity, "Carregar a url '$url'", Toast.LENGTH_LONG).show()
            return false
        }

        override fun onPageStarted(
                view: WebView,
                url: String,
                favicon: Bitmap?
        ) {
            Toast.makeText(activity, "Começou a carregar a url '${url}'", Toast.LENGTH_LONG).show()
        }

        override fun onPageFinished(
                view: WebView,
                url: String
        ) {
            Toast.makeText(activity, "Terminou de carregar a url '${url}'", Toast.LENGTH_LONG).show()
        }

        override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
        ) {
            Toast.makeText(activity, "Ocorreu um erro ao carregar a url '${request?.url}': '{$error?.description}'", Toast.LENGTH_LONG).show()
        }

        override fun onReceivedHttpError(
                view: WebView?,
                request: WebResourceRequest?,
                errorResponse: WebResourceResponse?
        ) {
            Toast.makeText(activity, "Ocorreu um erro ao carregar a url '{$request?.url}': '${errorResponse?.reasonPhrase}'", Toast.LENGTH_LONG).show()
        }

        override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler?,
                error: SslError?
        ) {
            handler?.proceed()
        }
    }
}
