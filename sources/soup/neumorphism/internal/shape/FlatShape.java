package soup.neumorphism.internal.shape;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import soup.neumorphism.LightSource;
import soup.neumorphism.NeumorphShapeDrawable;
import soup.neumorphism.internal.util.CanvasCompat;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0003H\u0016J\u0010\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u001e\u0010\u0016\u001a\u0004\u0018\u00010\u0006*\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0019H\u0002R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lsoup/neumorphism/internal/shape/FlatShape;", "Lsoup/neumorphism/internal/shape/Shape;", "drawableState", "Lsoup/neumorphism/NeumorphShapeDrawable$NeumorphShapeDrawableState;", "(Lsoup/neumorphism/NeumorphShapeDrawable$NeumorphShapeDrawableState;)V", "darkShadowBitmap", "Landroid/graphics/Bitmap;", "darkShadowDrawable", "Landroid/graphics/drawable/GradientDrawable;", "lightShadowBitmap", "lightShadowDrawable", "draw", "", "canvas", "Landroid/graphics/Canvas;", "outlinePath", "Landroid/graphics/Path;", "setDrawableState", "newDrawableState", "updateShadowBitmap", "bounds", "Landroid/graphics/Rect;", "toBlurredBitmap", "Landroid/graphics/drawable/Drawable;", "w", "", "h", "neumorphism_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: FlatShape.kt */
public final class FlatShape implements Shape {
    private Bitmap darkShadowBitmap;
    private final GradientDrawable darkShadowDrawable = new GradientDrawable();
    /* access modifiers changed from: private */
    public NeumorphShapeDrawable.NeumorphShapeDrawableState drawableState;
    private Bitmap lightShadowBitmap;
    private final GradientDrawable lightShadowDrawable = new GradientDrawable();

    public FlatShape(NeumorphShapeDrawable.NeumorphShapeDrawableState neumorphShapeDrawableState) {
        Intrinsics.checkParameterIsNotNull(neumorphShapeDrawableState, "drawableState");
        this.drawableState = neumorphShapeDrawableState;
    }

    public void setDrawableState(NeumorphShapeDrawable.NeumorphShapeDrawableState neumorphShapeDrawableState) {
        Intrinsics.checkParameterIsNotNull(neumorphShapeDrawableState, "newDrawableState");
        this.drawableState = neumorphShapeDrawableState;
    }

    public void updateShadowBitmap(Rect rect) {
        Intrinsics.checkParameterIsNotNull(rect, "bounds");
        FlatShape$updateShadowBitmap$1 flatShape$updateShadowBitmap$1 = new FlatShape$updateShadowBitmap$1(rect);
        GradientDrawable gradientDrawable = this.lightShadowDrawable;
        gradientDrawable.setColor(this.drawableState.getShadowColorLight());
        flatShape$updateShadowBitmap$1.invoke(gradientDrawable, this.drawableState.getShapeAppearanceModel());
        GradientDrawable gradientDrawable2 = this.darkShadowDrawable;
        gradientDrawable2.setColor(this.drawableState.getShadowColorDark());
        flatShape$updateShadowBitmap$1.invoke(gradientDrawable2, this.drawableState.getShapeAppearanceModel());
        int width = rect.width();
        int height = rect.height();
        this.lightShadowDrawable.setSize(width, height);
        this.lightShadowDrawable.setBounds(0, 0, width, height);
        this.darkShadowDrawable.setSize(width, height);
        this.darkShadowDrawable.setBounds(0, 0, width, height);
        this.lightShadowBitmap = toBlurredBitmap(this.lightShadowDrawable, width, height);
        this.darkShadowBitmap = toBlurredBitmap(this.darkShadowDrawable, width, height);
    }

    /* JADX INFO: finally extract failed */
    private final Bitmap toBlurredBitmap(Drawable drawable, int i, int i2) {
        FlatShape$toBlurredBitmap$1 flatShape$toBlurredBitmap$1 = new FlatShape$toBlurredBitmap$1(this);
        float shadowElevation = this.drawableState.getShadowElevation();
        float f = ((float) 2) * shadowElevation;
        Bitmap createBitmap = Bitmap.createBitmap(MathKt.roundToInt(((float) i) + f), MathKt.roundToInt(((float) i2) + f), Bitmap.Config.ARGB_8888);
        Intrinsics.checkExpressionValueIsNotNull(createBitmap, "Bitmap.createBitmap(widt… Bitmap.Config.ARGB_8888)");
        Canvas canvas = new Canvas(createBitmap);
        int save = canvas.save();
        canvas.translate(shadowElevation, shadowElevation);
        try {
            drawable.draw(canvas);
            canvas.restoreToCount(save);
            return flatShape$toBlurredBitmap$1.invoke(createBitmap);
        } catch (Throwable th) {
            canvas.restoreToCount(save);
            throw th;
        }
    }

    public void draw(Canvas canvas, Path path) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        Intrinsics.checkParameterIsNotNull(path, "outlinePath");
        int save = canvas.save();
        CanvasCompat.INSTANCE.clipOutPath(canvas, path);
        try {
            int lightSource = this.drawableState.getLightSource();
            float shadowElevation = this.drawableState.getShadowElevation();
            float shadowElevation2 = this.drawableState.getShadowElevation() + this.drawableState.getTranslationZ();
            Rect inset = this.drawableState.getInset();
            float f = (float) inset.left;
            float f2 = (float) inset.top;
            Bitmap bitmap = this.lightShadowBitmap;
            if (bitmap != null) {
                canvas.drawBitmap(bitmap, (LightSource.Companion.isLeft(lightSource) ? (-shadowElevation) - shadowElevation2 : (-shadowElevation) + shadowElevation2) + f, (LightSource.Companion.isTop(lightSource) ? (-shadowElevation) - shadowElevation2 : (-shadowElevation) + shadowElevation2) + f2, (Paint) null);
            }
            Bitmap bitmap2 = this.darkShadowBitmap;
            if (bitmap2 != null) {
                canvas.drawBitmap(bitmap2, (LightSource.Companion.isLeft(lightSource) ? (-shadowElevation) + shadowElevation2 : (-shadowElevation) - shadowElevation2) + f, (LightSource.Companion.isTop(lightSource) ? (-shadowElevation) + shadowElevation2 : (-shadowElevation) - shadowElevation2) + f2, (Paint) null);
            }
        } finally {
            canvas.restoreToCount(save);
        }
    }
}
