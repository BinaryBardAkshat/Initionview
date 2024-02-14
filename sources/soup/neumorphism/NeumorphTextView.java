package soup.neumorphism;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.ViewCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import soup.neumorphism.internal.util.NeumorphResources;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\u0018\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0007H\u0002J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J(\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0007H\u0014J\u0018\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0002J0\u0010$\u001a\u0004\u0018\u00010\u000b*\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010%\u001a\u00020\u00072\b\b\u0002\u0010&\u001a\u00020'H\u0002R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lsoup/neumorphism/NeumorphTextView;", "Landroidx/appcompat/widget/AppCompatTextView;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "lastShadowDark", "Landroid/graphics/Bitmap;", "lastShadowLight", "lastTextCache", "shadowColorDark", "shadowColorLight", "shadowElevation", "", "shadowPaint", "Landroid/graphics/Paint;", "buildTextCache", "w", "h", "draw", "", "canvas", "Landroid/graphics/Canvas;", "onSizeChanged", "oldw", "oldh", "staticLayout", "Landroid/text/StaticLayout;", "text", "", "textPaint", "Landroid/text/TextPaint;", "generateBitmapShadowCache", "color", "isInEditMode", "", "neumorphism_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: NeumorphTextView.kt */
public final class NeumorphTextView extends AppCompatTextView {
    private Bitmap lastShadowDark;
    private Bitmap lastShadowLight;
    private Bitmap lastTextCache;
    private final int shadowColorDark;
    private final int shadowColorLight;
    private final float shadowElevation;
    private final Paint shadowPaint;

    public NeumorphTextView(Context context) {
        this(context, (AttributeSet) null, 0, 0, 14, (DefaultConstructorMarker) null);
    }

    public NeumorphTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, (DefaultConstructorMarker) null);
    }

    public NeumorphTextView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0, 8, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ NeumorphTextView(Context context, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? R.attr.neumorphTextViewStyle : i, (i3 & 8) != 0 ? R.style.Widget_Neumorph_TextView : i2);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NeumorphTextView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.shadowPaint = new Paint(3);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.NeumorphTextView, i, i2);
        Intrinsics.checkExpressionValueIsNotNull(obtainStyledAttributes, "context.obtainStyledAttr…tr, defStyleRes\n        )");
        this.shadowElevation = obtainStyledAttributes.getDimension(R.styleable.NeumorphTextView_neumorph_shadowElevation, 0.0f);
        this.shadowColorLight = NeumorphResources.INSTANCE.getColor(context, obtainStyledAttributes, R.styleable.NeumorphTextView_neumorph_shadowColorLight, R.color.design_default_color_shadow_light);
        this.shadowColorDark = NeumorphResources.INSTANCE.getColor(context, obtainStyledAttributes, R.styleable.NeumorphTextView_neumorph_shadowColorDark, R.color.design_default_color_shadow_dark);
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        Bitmap buildTextCache = buildTextCache(i, i2);
        Bitmap bitmap = buildTextCache;
        int i5 = i;
        int i6 = i2;
        this.lastShadowLight = generateBitmapShadowCache(bitmap, i5, i6, this.shadowColorLight, isInEditMode());
        this.lastShadowDark = generateBitmapShadowCache(bitmap, i5, i6, this.shadowColorDark, isInEditMode());
        this.lastTextCache = buildTextCache;
    }

    public void draw(Canvas canvas) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        Bitmap bitmap = this.lastShadowLight;
        if (bitmap != null) {
            float f = this.shadowElevation;
            canvas.drawBitmap(bitmap, -f, -f, this.shadowPaint);
        }
        Bitmap bitmap2 = this.lastShadowDark;
        if (bitmap2 != null) {
            float f2 = this.shadowElevation;
            canvas.drawBitmap(bitmap2, f2, f2, this.shadowPaint);
        }
        super.draw(canvas);
    }

    private final Bitmap buildTextCache(int i, int i2) {
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        TextPaint textPaint = new TextPaint(1);
        textPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
        textPaint.setTextSize(getTextSize());
        textPaint.setTypeface(getTypeface());
        CharSequence text = getText();
        Intrinsics.checkExpressionValueIsNotNull(text, "text");
        staticLayout(text, textPaint).draw(new Canvas(createBitmap));
        Intrinsics.checkExpressionValueIsNotNull(createBitmap, "Bitmap.createBitmap(w, h…w(Canvas(this))\n        }");
        return createBitmap;
    }

    private final StaticLayout staticLayout(CharSequence charSequence, TextPaint textPaint) {
        if (Build.VERSION.SDK_INT >= 23) {
            StaticLayout build = StaticLayout.Builder.obtain(charSequence, 0, charSequence.length(), textPaint, Integer.MAX_VALUE).build();
            Intrinsics.checkExpressionValueIsNotNull(build, "StaticLayout.Builder\n   …\n                .build()");
            return build;
        }
        return new StaticLayout(charSequence, textPaint, Integer.MAX_VALUE, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
    }

    static /* synthetic */ Bitmap generateBitmapShadowCache$default(NeumorphTextView neumorphTextView, Bitmap bitmap, int i, int i2, int i3, boolean z, int i4, Object obj) {
        return neumorphTextView.generateBitmapShadowCache(bitmap, i, i2, i3, (i4 & 8) != 0 ? false : z);
    }

    private final Bitmap generateBitmapShadowCache(Bitmap bitmap, int i, int i2, int i3, boolean z) {
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.save();
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        canvas.restore();
        Paint paint = new Paint(3);
        paint.setColor(i3);
        if (!z) {
            paint.setMaskFilter(new BlurMaskFilter(5.0f, BlurMaskFilter.Blur.NORMAL));
        }
        int[] iArr = new int[2];
        Bitmap extractAlpha = createBitmap.extractAlpha(paint, iArr);
        paint.setMaskFilter((MaskFilter) null);
        createBitmap.eraseColor(0);
        canvas.drawBitmap(extractAlpha, (float) iArr[0], (float) iArr[1], paint);
        return createBitmap;
    }
}
