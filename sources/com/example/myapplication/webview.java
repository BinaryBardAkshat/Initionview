package com.example.myapplication;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.binarybardakshat.initionview.R;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\bB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0012\u0010\u0005\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0015¨\u0006\t"}, d2 = {"Lcom/example/myapplication/webview;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "onBackPressed", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "MyClient", "app_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* compiled from: webview.kt */
public final class webview extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_webview);
        String stringExtra = getIntent().getStringExtra("url");
        View findViewById = findViewById(R.id.webView);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(R.id.webView)");
        WebView webView = (WebView) findViewById;
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(String.valueOf(stringExtra));
        webView.setDownloadListener(new webview$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-0  reason: not valid java name */
    public static final void m42onCreate$lambda0(webview webview, String str, String str2, String str3, String str4, long j) {
        Intrinsics.checkNotNullParameter(webview, "this$0");
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(str));
        request.setMimeType(str4);
        request.addRequestHeader("cookie", CookieManager.getInstance().getCookie(str));
        request.addRequestHeader("User-Agent", str2);
        request.setDescription("Downloading file...");
        request.setTitle(URLUtil.guessFileName(str, str3, str4));
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(1);
        request.setDestinationInExternalFilesDir(webview, Environment.DIRECTORY_DOWNLOADS, ".png");
        Object systemService = webview.getSystemService("download");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.app.DownloadManager");
        ((DownloadManager) systemService).enqueue(request);
        Toast.makeText(webview.getApplicationContext(), "Downloading File", 1).show();
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/example/myapplication/webview$MyClient;", "Landroid/webkit/WebViewClient;", "()V", "shouldOverrideUrlLoading", "", "view", "Landroid/webkit/WebView;", "Url", "", "app_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* compiled from: webview.kt */
    public static final class MyClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Intrinsics.checkNotNullParameter(webView, "view");
            Intrinsics.checkNotNullParameter(str, "Url");
            webView.loadUrl(str);
            return true;
        }
    }

    public void onBackPressed() {
        View findViewById = findViewById(R.id.webView);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(R.id.webView)");
        WebView webView = (WebView) findViewById;
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
