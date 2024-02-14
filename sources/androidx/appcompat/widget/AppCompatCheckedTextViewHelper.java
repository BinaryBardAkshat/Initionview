package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.CheckedTextView;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.widget.CheckedTextViewCompat;

class AppCompatCheckedTextViewHelper {
    private ColorStateList mCheckMarkTintList = null;
    private PorterDuff.Mode mCheckMarkTintMode = null;
    private boolean mHasCheckMarkTint = false;
    private boolean mHasCheckMarkTintMode = false;
    private boolean mSkipNextApply;
    private final CheckedTextView mView;

    AppCompatCheckedTextViewHelper(CheckedTextView checkedTextView) {
        this.mView = checkedTextView;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0041 A[SYNTHETIC, Splitter:B:12:0x0041] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0066 A[Catch:{ all -> 0x008e }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0079 A[Catch:{ all -> 0x008e }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void loadFromAttributes(android.util.AttributeSet r11, int r12) {
        /*
            r10 = this;
            android.widget.CheckedTextView r0 = r10.mView
            android.content.Context r0 = r0.getContext()
            int[] r1 = androidx.appcompat.R.styleable.CheckedTextView
            r2 = 0
            androidx.appcompat.widget.TintTypedArray r0 = androidx.appcompat.widget.TintTypedArray.obtainStyledAttributes(r0, r11, r1, r12, r2)
            android.widget.CheckedTextView r3 = r10.mView
            android.content.Context r4 = r3.getContext()
            int[] r5 = androidx.appcompat.R.styleable.CheckedTextView
            android.content.res.TypedArray r7 = r0.getWrappedTypeArray()
            r9 = 0
            r6 = r11
            r8 = r12
            androidx.core.view.ViewCompat.saveAttributeDataForStyleable(r3, r4, r5, r6, r7, r8, r9)
            int r11 = androidx.appcompat.R.styleable.CheckedTextView_checkMarkCompat     // Catch:{ all -> 0x008e }
            boolean r11 = r0.hasValue(r11)     // Catch:{ all -> 0x008e }
            if (r11 == 0) goto L_0x003e
            int r11 = androidx.appcompat.R.styleable.CheckedTextView_checkMarkCompat     // Catch:{ all -> 0x008e }
            int r11 = r0.getResourceId(r11, r2)     // Catch:{ all -> 0x008e }
            if (r11 == 0) goto L_0x003e
            android.widget.CheckedTextView r12 = r10.mView     // Catch:{ NotFoundException -> 0x003e }
            android.content.Context r1 = r12.getContext()     // Catch:{ NotFoundException -> 0x003e }
            android.graphics.drawable.Drawable r11 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r1, r11)     // Catch:{ NotFoundException -> 0x003e }
            r12.setCheckMarkDrawable(r11)     // Catch:{ NotFoundException -> 0x003e }
            r11 = 1
            goto L_0x003f
        L_0x003e:
            r11 = 0
        L_0x003f:
            if (r11 != 0) goto L_0x005e
            int r11 = androidx.appcompat.R.styleable.CheckedTextView_android_checkMark     // Catch:{ all -> 0x008e }
            boolean r11 = r0.hasValue(r11)     // Catch:{ all -> 0x008e }
            if (r11 == 0) goto L_0x005e
            int r11 = androidx.appcompat.R.styleable.CheckedTextView_android_checkMark     // Catch:{ all -> 0x008e }
            int r11 = r0.getResourceId(r11, r2)     // Catch:{ all -> 0x008e }
            if (r11 == 0) goto L_0x005e
            android.widget.CheckedTextView r12 = r10.mView     // Catch:{ all -> 0x008e }
            android.content.Context r1 = r12.getContext()     // Catch:{ all -> 0x008e }
            android.graphics.drawable.Drawable r11 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r1, r11)     // Catch:{ all -> 0x008e }
            r12.setCheckMarkDrawable(r11)     // Catch:{ all -> 0x008e }
        L_0x005e:
            int r11 = androidx.appcompat.R.styleable.CheckedTextView_checkMarkTint     // Catch:{ all -> 0x008e }
            boolean r11 = r0.hasValue(r11)     // Catch:{ all -> 0x008e }
            if (r11 == 0) goto L_0x0071
            android.widget.CheckedTextView r11 = r10.mView     // Catch:{ all -> 0x008e }
            int r12 = androidx.appcompat.R.styleable.CheckedTextView_checkMarkTint     // Catch:{ all -> 0x008e }
            android.content.res.ColorStateList r12 = r0.getColorStateList(r12)     // Catch:{ all -> 0x008e }
            androidx.core.widget.CheckedTextViewCompat.setCheckMarkTintList(r11, r12)     // Catch:{ all -> 0x008e }
        L_0x0071:
            int r11 = androidx.appcompat.R.styleable.CheckedTextView_checkMarkTintMode     // Catch:{ all -> 0x008e }
            boolean r11 = r0.hasValue(r11)     // Catch:{ all -> 0x008e }
            if (r11 == 0) goto L_0x008a
            android.widget.CheckedTextView r11 = r10.mView     // Catch:{ all -> 0x008e }
            int r12 = androidx.appcompat.R.styleable.CheckedTextView_checkMarkTintMode     // Catch:{ all -> 0x008e }
            r1 = -1
            int r12 = r0.getInt(r12, r1)     // Catch:{ all -> 0x008e }
            r1 = 0
            android.graphics.PorterDuff$Mode r12 = androidx.appcompat.widget.DrawableUtils.parseTintMode(r12, r1)     // Catch:{ all -> 0x008e }
            androidx.core.widget.CheckedTextViewCompat.setCheckMarkTintMode(r11, r12)     // Catch:{ all -> 0x008e }
        L_0x008a:
            r0.recycle()
            return
        L_0x008e:
            r11 = move-exception
            r0.recycle()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AppCompatCheckedTextViewHelper.loadFromAttributes(android.util.AttributeSet, int):void");
    }

    /* access modifiers changed from: package-private */
    public void setSupportCheckMarkTintList(ColorStateList colorStateList) {
        this.mCheckMarkTintList = colorStateList;
        this.mHasCheckMarkTint = true;
        applyCheckMarkTint();
    }

    /* access modifiers changed from: package-private */
    public ColorStateList getSupportCheckMarkTintList() {
        return this.mCheckMarkTintList;
    }

    /* access modifiers changed from: package-private */
    public void setSupportCheckMarkTintMode(PorterDuff.Mode mode) {
        this.mCheckMarkTintMode = mode;
        this.mHasCheckMarkTintMode = true;
        applyCheckMarkTint();
    }

    /* access modifiers changed from: package-private */
    public PorterDuff.Mode getSupportCheckMarkTintMode() {
        return this.mCheckMarkTintMode;
    }

    /* access modifiers changed from: package-private */
    public void onSetCheckMarkDrawable() {
        if (this.mSkipNextApply) {
            this.mSkipNextApply = false;
            return;
        }
        this.mSkipNextApply = true;
        applyCheckMarkTint();
    }

    /* access modifiers changed from: package-private */
    public void applyCheckMarkTint() {
        Drawable checkMarkDrawable = CheckedTextViewCompat.getCheckMarkDrawable(this.mView);
        if (checkMarkDrawable == null) {
            return;
        }
        if (this.mHasCheckMarkTint || this.mHasCheckMarkTintMode) {
            Drawable mutate = DrawableCompat.wrap(checkMarkDrawable).mutate();
            if (this.mHasCheckMarkTint) {
                DrawableCompat.setTintList(mutate, this.mCheckMarkTintList);
            }
            if (this.mHasCheckMarkTintMode) {
                DrawableCompat.setTintMode(mutate, this.mCheckMarkTintMode);
            }
            if (mutate.isStateful()) {
                mutate.setState(this.mView.getDrawableState());
            }
            this.mView.setCheckMarkDrawable(mutate);
        }
    }
}
