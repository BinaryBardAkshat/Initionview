package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.binarybardakshat.initionview.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014¨\u0006\u0007"}, d2 = {"Lcom/example/myapplication/aboutme;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* compiled from: aboutme.kt */
public final class aboutme extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_aboutme);
        ((ImageView) findViewById(R.id.git)).setOnClickListener(new aboutme$$ExternalSyntheticLambda0(this));
        ((ImageView) findViewById(R.id.inst)).setOnClickListener(new aboutme$$ExternalSyntheticLambda2(this));
        ((ImageView) findViewById(R.id.what)).setOnClickListener(new aboutme$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-0  reason: not valid java name */
    public static final void m37onCreate$lambda0(aboutme aboutme, View view) {
        Intrinsics.checkNotNullParameter(aboutme, "this$0");
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("https://github.com/geeksahilthakur"));
        aboutme.startActivity(intent);
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-1  reason: not valid java name */
    public static final void m38onCreate$lambda1(aboutme aboutme, View view) {
        Intrinsics.checkNotNullParameter(aboutme, "this$0");
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("https://www.instagram.com/geeksahil/"));
        aboutme.startActivity(intent);
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-2  reason: not valid java name */
    public static final void m39onCreate$lambda2(aboutme aboutme, View view) {
        Intrinsics.checkNotNullParameter(aboutme, "this$0");
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("https://api.whatsapp.com/send/?phone=919015389061&text&app_absent=0"));
        aboutme.startActivity(intent);
    }
}
