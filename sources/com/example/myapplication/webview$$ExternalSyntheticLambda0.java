package com.example.myapplication;

import android.webkit.DownloadListener;

public final /* synthetic */ class webview$$ExternalSyntheticLambda0 implements DownloadListener {
    public final /* synthetic */ webview f$0;

    public /* synthetic */ webview$$ExternalSyntheticLambda0(webview webview) {
        this.f$0 = webview;
    }

    public final void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        webview.m42onCreate$lambda0(this.f$0, str, str2, str3, str4, j);
    }
}
