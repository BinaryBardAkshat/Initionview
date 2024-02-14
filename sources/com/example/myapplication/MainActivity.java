package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.binarybardakshat.initionview.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import soup.neumorphism.NeumorphCardView;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014¨\u0006\u0007"}, d2 = {"Lcom/example/myapplication/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* compiled from: MainActivity.kt */
public final class MainActivity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_main);
        ((NeumorphCardView) findViewById(R.id.crd1)).setOnClickListener(new MainActivity$$ExternalSyntheticLambda8(this));
        ((NeumorphCardView) findViewById(R.id.crd2)).setOnClickListener(new MainActivity$$ExternalSyntheticLambda2(this));
        ((NeumorphCardView) findViewById(R.id.crd3)).setOnClickListener(new MainActivity$$ExternalSyntheticLambda7(this));
        ((NeumorphCardView) findViewById(R.id.crd4)).setOnClickListener(new MainActivity$$ExternalSyntheticLambda1(this));
        ((NeumorphCardView) findViewById(R.id.crd5)).setOnClickListener(new MainActivity$$ExternalSyntheticLambda6(this));
        ((NeumorphCardView) findViewById(R.id.crd6)).setOnClickListener(new MainActivity$$ExternalSyntheticLambda4(this));
        ((NeumorphCardView) findViewById(R.id.crd7)).setOnClickListener(new MainActivity$$ExternalSyntheticLambda3(this));
        ((NeumorphCardView) findViewById(R.id.crd8)).setOnClickListener(new MainActivity$$ExternalSyntheticLambda5(this));
        ((NeumorphCardView) findViewById(R.id.neumorphCardView)).setOnClickListener(new MainActivity$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-0  reason: not valid java name */
    public static final void m27onCreate$lambda0(MainActivity mainActivity, View view) {
        Intrinsics.checkNotNullParameter(mainActivity, "this$0");
        Intent intent = new Intent(mainActivity, webview.class);
        intent.putExtra("url", "https://drive.google.com/drive/folders/1I8QqnzZonRewbSnUMyfC9-tRduEJ4gyB?usp=sharing");
        mainActivity.startActivity(intent);
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-1  reason: not valid java name */
    public static final void m28onCreate$lambda1(MainActivity mainActivity, View view) {
        Intrinsics.checkNotNullParameter(mainActivity, "this$0");
        Intent intent = new Intent(mainActivity, webview.class);
        intent.putExtra("url", "https://drive.google.com/drive/folders/1trLM1LSlMaWQAR4I0PhIffMej6OuHuKZ?usp=sharing");
        mainActivity.startActivity(intent);
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-2  reason: not valid java name */
    public static final void m29onCreate$lambda2(MainActivity mainActivity, View view) {
        Intrinsics.checkNotNullParameter(mainActivity, "this$0");
        Intent intent = new Intent(mainActivity, webview.class);
        intent.putExtra("url", "https://drive.google.com/drive/folders/1LIC6FIKzz7iaIMGH5srFjmAcxq7VELgx?usp=sharing");
        mainActivity.startActivity(intent);
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-3  reason: not valid java name */
    public static final void m30onCreate$lambda3(MainActivity mainActivity, View view) {
        Intrinsics.checkNotNullParameter(mainActivity, "this$0");
        Intent intent = new Intent(mainActivity, webview.class);
        intent.putExtra("url", "https://drive.google.com/drive/folders/1ozH4gd8zApey7IiNo3rEA1yCEPx-wvgH?usp=sharing");
        mainActivity.startActivity(intent);
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-4  reason: not valid java name */
    public static final void m31onCreate$lambda4(MainActivity mainActivity, View view) {
        Intrinsics.checkNotNullParameter(mainActivity, "this$0");
        Intent intent = new Intent(mainActivity, webview.class);
        intent.putExtra("url", "https://drive.google.com/drive/folders/13SISxvIsuwIoRjkopkDwvfURfin5fTfz?usp=sharing");
        mainActivity.startActivity(intent);
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-5  reason: not valid java name */
    public static final void m32onCreate$lambda5(MainActivity mainActivity, View view) {
        Intrinsics.checkNotNullParameter(mainActivity, "this$0");
        Intent intent = new Intent(mainActivity, webview.class);
        intent.putExtra("url", "https://drive.google.com/drive/folders/14LcfyMo-QiVn7Zw7Kt2GQsbbSC3MyXzM?usp=sharing");
        mainActivity.startActivity(intent);
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-6  reason: not valid java name */
    public static final void m33onCreate$lambda6(MainActivity mainActivity, View view) {
        Intrinsics.checkNotNullParameter(mainActivity, "this$0");
        Intent intent = new Intent(mainActivity, webview.class);
        intent.putExtra("url", "https://drive.google.com/drive/folders/1HY6bR5SWKixNPyA3bVbvO5AkbcCZX7QU?usp=sharing");
        mainActivity.startActivity(intent);
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-7  reason: not valid java name */
    public static final void m34onCreate$lambda7(MainActivity mainActivity, View view) {
        Intrinsics.checkNotNullParameter(mainActivity, "this$0");
        Intent intent = new Intent(mainActivity, webview.class);
        intent.putExtra("url", "https://drive.google.com/drive/folders/11QLOTY6HM5lXz-uv3Ys9RjkiuAuy9Kc6?usp=sharing");
        mainActivity.startActivity(intent);
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-8  reason: not valid java name */
    public static final void m35onCreate$lambda8(MainActivity mainActivity, View view) {
        Intrinsics.checkNotNullParameter(mainActivity, "this$0");
        mainActivity.startActivity(new Intent(mainActivity, aboutme.class));
    }
}
