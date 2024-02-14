package androidx.emoji2.text;

import android.text.Editable;
import android.text.SpanWatcher;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import androidx.core.util.Preconditions;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public final class SpannableBuilder extends SpannableStringBuilder {
    private final Class<?> mWatcherClass;
    private final List<WatcherWrapper> mWatchers = new ArrayList();

    SpannableBuilder(Class<?> cls) {
        Preconditions.checkNotNull(cls, "watcherClass cannot be null");
        this.mWatcherClass = cls;
    }

    SpannableBuilder(Class<?> cls, CharSequence charSequence) {
        super(charSequence);
        Preconditions.checkNotNull(cls, "watcherClass cannot be null");
        this.mWatcherClass = cls;
    }

    SpannableBuilder(Class<?> cls, CharSequence charSequence, int i, int i2) {
        super(charSequence, i, i2);
        Preconditions.checkNotNull(cls, "watcherClass cannot be null");
        this.mWatcherClass = cls;
    }

    public static SpannableBuilder create(Class<?> cls, CharSequence charSequence) {
        return new SpannableBuilder(cls, charSequence);
    }

    private boolean isWatcher(Object obj) {
        return obj != null && isWatcher(obj.getClass());
    }

    private boolean isWatcher(Class<?> cls) {
        return this.mWatcherClass == cls;
    }

    public CharSequence subSequence(int i, int i2) {
        return new SpannableBuilder(this.mWatcherClass, this, i, i2);
    }

    public void setSpan(Object obj, int i, int i2, int i3) {
        if (isWatcher(obj)) {
            WatcherWrapper watcherWrapper = new WatcherWrapper(obj);
            this.mWatchers.add(watcherWrapper);
            obj = watcherWrapper;
        }
        super.setSpan(obj, i, i2, i3);
    }

    public <T> T[] getSpans(int i, int i2, Class<T> cls) {
        if (!isWatcher((Class<?>) cls)) {
            return super.getSpans(i, i2, cls);
        }
        WatcherWrapper[] watcherWrapperArr = (WatcherWrapper[]) super.getSpans(i, i2, WatcherWrapper.class);
        T[] tArr = (Object[]) Array.newInstance(cls, watcherWrapperArr.length);
        for (int i3 = 0; i3 < watcherWrapperArr.length; i3++) {
            tArr[i3] = watcherWrapperArr[i3].mObject;
        }
        return tArr;
    }

    public void removeSpan(Object obj) {
        WatcherWrapper watcherWrapper;
        if (isWatcher(obj)) {
            watcherWrapper = getWatcherFor(obj);
            if (watcherWrapper != null) {
                obj = watcherWrapper;
            }
        } else {
            watcherWrapper = null;
        }
        super.removeSpan(obj);
        if (watcherWrapper != null) {
            this.mWatchers.remove(watcherWrapper);
        }
    }

    public int getSpanStart(Object obj) {
        WatcherWrapper watcherFor;
        if (isWatcher(obj) && (watcherFor = getWatcherFor(obj)) != null) {
            obj = watcherFor;
        }
        return super.getSpanStart(obj);
    }

    public int getSpanEnd(Object obj) {
        WatcherWrapper watcherFor;
        if (isWatcher(obj) && (watcherFor = getWatcherFor(obj)) != null) {
            obj = watcherFor;
        }
        return super.getSpanEnd(obj);
    }

    public int getSpanFlags(Object obj) {
        WatcherWrapper watcherFor;
        if (isWatcher(obj) && (watcherFor = getWatcherFor(obj)) != null) {
            obj = watcherFor;
        }
        return super.getSpanFlags(obj);
    }

    public int nextSpanTransition(int i, int i2, Class<WatcherWrapper> cls) {
        if (cls == null || isWatcher((Class<?>) cls)) {
            cls = WatcherWrapper.class;
        }
        return super.nextSpanTransition(i, i2, cls);
    }

    private WatcherWrapper getWatcherFor(Object obj) {
        for (int i = 0; i < this.mWatchers.size(); i++) {
            WatcherWrapper watcherWrapper = this.mWatchers.get(i);
            if (watcherWrapper.mObject == obj) {
                return watcherWrapper;
            }
        }
        return null;
    }

    public void beginBatchEdit() {
        blockWatchers();
    }

    public void endBatchEdit() {
        unblockwatchers();
        fireWatchers();
    }

    private void blockWatchers() {
        for (int i = 0; i < this.mWatchers.size(); i++) {
            this.mWatchers.get(i).blockCalls();
        }
    }

    private void unblockwatchers() {
        for (int i = 0; i < this.mWatchers.size(); i++) {
            this.mWatchers.get(i).unblockCalls();
        }
    }

    private void fireWatchers() {
        for (int i = 0; i < this.mWatchers.size(); i++) {
            this.mWatchers.get(i).onTextChanged(this, 0, length(), length());
        }
    }

    public SpannableStringBuilder replace(int i, int i2, CharSequence charSequence) {
        blockWatchers();
        super.replace(i, i2, charSequence);
        unblockwatchers();
        return this;
    }

    public SpannableStringBuilder replace(int i, int i2, CharSequence charSequence, int i3, int i4) {
        blockWatchers();
        super.replace(i, i2, charSequence, i3, i4);
        unblockwatchers();
        return this;
    }

    public SpannableStringBuilder insert(int i, CharSequence charSequence) {
        super.insert(i, charSequence);
        return this;
    }

    public SpannableStringBuilder insert(int i, CharSequence charSequence, int i2, int i3) {
        super.insert(i, charSequence, i2, i3);
        return this;
    }

    public SpannableStringBuilder delete(int i, int i2) {
        super.delete(i, i2);
        return this;
    }

    public SpannableStringBuilder append(CharSequence charSequence) {
        super.append(charSequence);
        return this;
    }

    public SpannableStringBuilder append(char c) {
        super.append(c);
        return this;
    }

    public SpannableStringBuilder append(CharSequence charSequence, int i, int i2) {
        super.append(charSequence, i, i2);
        return this;
    }

    public SpannableStringBuilder append(CharSequence charSequence, Object obj, int i) {
        super.append(charSequence, obj, i);
        return this;
    }

    private static class WatcherWrapper implements TextWatcher, SpanWatcher {
        private final AtomicInteger mBlockCalls = new AtomicInteger(0);
        final Object mObject;

        WatcherWrapper(Object obj) {
            this.mObject = obj;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            ((TextWatcher) this.mObject).beforeTextChanged(charSequence, i, i2, i3);
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            ((TextWatcher) this.mObject).onTextChanged(charSequence, i, i2, i3);
        }

        public void afterTextChanged(Editable editable) {
            ((TextWatcher) this.mObject).afterTextChanged(editable);
        }

        public void onSpanAdded(Spannable spannable, Object obj, int i, int i2) {
            if (this.mBlockCalls.get() <= 0 || !isEmojiSpan(obj)) {
                ((SpanWatcher) this.mObject).onSpanAdded(spannable, obj, i, i2);
            }
        }

        public void onSpanRemoved(Spannable spannable, Object obj, int i, int i2) {
            if (this.mBlockCalls.get() <= 0 || !isEmojiSpan(obj)) {
                ((SpanWatcher) this.mObject).onSpanRemoved(spannable, obj, i, i2);
            }
        }

        public void onSpanChanged(Spannable spannable, Object obj, int i, int i2, int i3, int i4) {
            if (this.mBlockCalls.get() <= 0 || !isEmojiSpan(obj)) {
                ((SpanWatcher) this.mObject).onSpanChanged(spannable, obj, i, i2, i3, i4);
            }
        }

        /* access modifiers changed from: package-private */
        public final void blockCalls() {
            this.mBlockCalls.incrementAndGet();
        }

        /* access modifiers changed from: package-private */
        public final void unblockCalls() {
            this.mBlockCalls.decrementAndGet();
        }

        private boolean isEmojiSpan(Object obj) {
            return obj instanceof EmojiSpan;
        }
    }
}
