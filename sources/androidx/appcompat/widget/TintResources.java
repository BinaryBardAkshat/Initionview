package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

class TintResources extends ResourcesWrapper {
    private final WeakReference<Context> mContextRef;

    public TintResources(Context context, Resources resources) {
        super(resources);
        this.mContextRef = new WeakReference<>(context);
    }

    public Drawable getDrawable(int i) throws Resources.NotFoundException {
        Drawable drawableCanonical = getDrawableCanonical(i);
        Context context = (Context) this.mContextRef.get();
        if (!(drawableCanonical == null || context == null)) {
            ResourceManagerInternal.get().tintDrawableUsingColorFilter(context, i, drawableCanonical);
        }
        return drawableCanonical;
    }
}
