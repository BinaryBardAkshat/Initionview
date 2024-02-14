package androidx.appcompat.widget;

import android.content.res.TypedArray;
import android.text.InputFilter;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.appcompat.R;
import androidx.emoji2.viewsintegration.EmojiTextViewHelper;

class AppCompatEmojiTextHelper {
    private final EmojiTextViewHelper mEmojiTextViewHelper;
    private final TextView mView;

    AppCompatEmojiTextHelper(TextView textView) {
        this.mView = textView;
        this.mEmojiTextViewHelper = new EmojiTextViewHelper(textView, false);
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public void loadFromAttributes(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = this.mView.getContext().obtainStyledAttributes(attributeSet, R.styleable.AppCompatTextView, i, 0);
        try {
            boolean z = true;
            if (obtainStyledAttributes.hasValue(R.styleable.AppCompatTextView_emojiCompatEnabled)) {
                z = obtainStyledAttributes.getBoolean(R.styleable.AppCompatTextView_emojiCompatEnabled, true);
            }
            obtainStyledAttributes.recycle();
            setEnabled(z);
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public void setEnabled(boolean z) {
        this.mEmojiTextViewHelper.setEnabled(z);
    }

    public boolean isEnabled() {
        return this.mEmojiTextViewHelper.isEnabled();
    }

    /* access modifiers changed from: package-private */
    public InputFilter[] getFilters(InputFilter[] inputFilterArr) {
        return this.mEmojiTextViewHelper.getFilters(inputFilterArr);
    }

    /* access modifiers changed from: package-private */
    public void setAllCaps(boolean z) {
        this.mEmojiTextViewHelper.setAllCaps(z);
    }

    public TransformationMethod wrapTransformationMethod(TransformationMethod transformationMethod) {
        return this.mEmojiTextViewHelper.wrapTransformationMethod(transformationMethod);
    }
}
