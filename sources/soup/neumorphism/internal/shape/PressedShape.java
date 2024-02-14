package soup.neumorphism.internal.shape;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import soup.neumorphism.LightSource;
import soup.neumorphism.NeumorphShapeAppearanceModel;
import soup.neumorphism.NeumorphShapeDrawable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u001a\u0010\u0010\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0002J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010\u0019\u001a\u00020\u0017H\u0002J\b\u0010\u001a\u001a\u00020\u0017H\u0002J\u0010\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u0003H\u0016J\u0010\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lsoup/neumorphism/internal/shape/PressedShape;", "Lsoup/neumorphism/internal/shape/Shape;", "drawableState", "Lsoup/neumorphism/NeumorphShapeDrawable$NeumorphShapeDrawableState;", "(Lsoup/neumorphism/NeumorphShapeDrawable$NeumorphShapeDrawableState;)V", "darkShadowDrawable", "Landroid/graphics/drawable/GradientDrawable;", "lightShadowDrawable", "shadowBitmap", "Landroid/graphics/Bitmap;", "draw", "", "canvas", "Landroid/graphics/Canvas;", "outlinePath", "Landroid/graphics/Path;", "generateShadowBitmap", "w", "", "h", "getCornerRadiiForDarkShadow", "", "cornerSize", "", "getCornerRadiiForLightShadow", "getCornerSizeForDarkShadow", "getCornerSizeForLightShadow", "setDrawableState", "newDrawableState", "updateShadowBitmap", "bounds", "Landroid/graphics/Rect;", "neumorphism_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: PressedShape.kt */
public final class PressedShape implements Shape {
    private final GradientDrawable darkShadowDrawable = new GradientDrawable();
    /* access modifiers changed from: private */
    public NeumorphShapeDrawable.NeumorphShapeDrawableState drawableState;
    private final GradientDrawable lightShadowDrawable = new GradientDrawable();
    private Bitmap shadowBitmap;

    public PressedShape(NeumorphShapeDrawable.NeumorphShapeDrawableState neumorphShapeDrawableState) {
        Intrinsics.checkParameterIsNotNull(neumorphShapeDrawableState, "drawableState");
        this.drawableState = neumorphShapeDrawableState;
    }

    public void setDrawableState(NeumorphShapeDrawable.NeumorphShapeDrawableState neumorphShapeDrawableState) {
        Intrinsics.checkParameterIsNotNull(neumorphShapeDrawableState, "newDrawableState");
        this.drawableState = neumorphShapeDrawableState;
    }

    public void updateShadowBitmap(Rect rect) {
        Intrinsics.checkParameterIsNotNull(rect, "bounds");
        int shadowElevation = (int) this.drawableState.getShadowElevation();
        int width = rect.width();
        int height = rect.height();
        int i = width + shadowElevation;
        int i2 = height + shadowElevation;
        GradientDrawable gradientDrawable = this.lightShadowDrawable;
        gradientDrawable.setSize(i, i2);
        gradientDrawable.setStroke(shadowElevation, this.drawableState.getShadowColorLight());
        int cornerFamily = this.drawableState.getShapeAppearanceModel().getCornerFamily();
        if (cornerFamily == 0) {
            float min = Math.min(Math.min(((float) width) / 2.0f, ((float) height) / 2.0f), getCornerSizeForLightShadow());
            gradientDrawable.setShape(0);
            gradientDrawable.setCornerRadii(getCornerRadiiForLightShadow(min));
        } else if (cornerFamily == 1) {
            gradientDrawable.setShape(1);
        }
        GradientDrawable gradientDrawable2 = this.darkShadowDrawable;
        gradientDrawable2.setSize(i, i2);
        gradientDrawable2.setStroke(shadowElevation, this.drawableState.getShadowColorDark());
        int cornerFamily2 = this.drawableState.getShapeAppearanceModel().getCornerFamily();
        if (cornerFamily2 == 0) {
            float min2 = Math.min(Math.min(((float) width) / 2.0f, ((float) height) / 2.0f), getCornerSizeForDarkShadow());
            gradientDrawable2.setShape(0);
            gradientDrawable2.setCornerRadii(getCornerRadiiForDarkShadow(min2));
        } else if (cornerFamily2 == 1) {
            gradientDrawable2.setShape(1);
        }
        this.lightShadowDrawable.setSize(i, i2);
        this.lightShadowDrawable.setBounds(0, 0, i, i2);
        this.darkShadowDrawable.setSize(i, i2);
        this.darkShadowDrawable.setBounds(0, 0, i, i2);
        this.shadowBitmap = generateShadowBitmap(width, height);
    }

    private final float getCornerSizeForLightShadow() {
        NeumorphShapeAppearanceModel shapeAppearanceModel = this.drawableState.getShapeAppearanceModel();
        int lightSource = this.drawableState.getLightSource();
        if (lightSource == 0) {
            return shapeAppearanceModel.getBottomLeftCornerSize();
        }
        if (lightSource == 1) {
            return shapeAppearanceModel.getTopRightCornerSize();
        }
        if (lightSource == 2) {
            return shapeAppearanceModel.getBottomRightCornerSize();
        }
        if (lightSource == 3) {
            return shapeAppearanceModel.getTopLeftCornerSize();
        }
        throw new IllegalStateException("LightSource " + this.drawableState.getLightSource() + " is not supported.");
    }

    private final float[] getCornerRadiiForLightShadow(float f) {
        int lightSource = this.drawableState.getLightSource();
        if (lightSource == 0) {
            return new float[]{0.0f, 0.0f, 0.0f, 0.0f, f, f, 0.0f, 0.0f};
        } else if (lightSource == 1) {
            return new float[]{0.0f, 0.0f, f, f, 0.0f, 0.0f, 0.0f, 0.0f};
        } else if (lightSource == 2) {
            return new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, f, f};
        } else if (lightSource == 3) {
            return new float[]{f, f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
        } else {
            throw new IllegalStateException("LightSource " + this.drawableState.getLightSource() + " is not supported.");
        }
    }

    private final float getCornerSizeForDarkShadow() {
        NeumorphShapeAppearanceModel shapeAppearanceModel = this.drawableState.getShapeAppearanceModel();
        int lightSource = this.drawableState.getLightSource();
        if (lightSource == 0) {
            return shapeAppearanceModel.getTopLeftCornerSize();
        }
        if (lightSource == 1) {
            return shapeAppearanceModel.getBottomLeftCornerSize();
        }
        if (lightSource == 2) {
            return shapeAppearanceModel.getTopRightCornerSize();
        }
        if (lightSource == 3) {
            return shapeAppearanceModel.getBottomRightCornerSize();
        }
        throw new IllegalStateException("LightSource " + this.drawableState.getLightSource() + " is not supported.");
    }

    private final float[] getCornerRadiiForDarkShadow(float f) {
        int lightSource = this.drawableState.getLightSource();
        if (lightSource == 0) {
            return new float[]{f, f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
        } else if (lightSource == 1) {
            return new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, f, f};
        } else if (lightSource == 2) {
            return new float[]{0.0f, 0.0f, f, f, 0.0f, 0.0f, 0.0f, 0.0f};
        } else if (lightSource == 3) {
            return new float[]{0.0f, 0.0f, 0.0f, 0.0f, f, f, 0.0f, 0.0f};
        } else {
            throw new IllegalStateException("LightSource " + this.drawableState.getLightSource() + " is not supported.");
        }
    }

    /* JADX INFO: finally extract failed */
    private final Bitmap generateShadowBitmap(int i, int i2) {
        PressedShape$generateShadowBitmap$1 pressedShape$generateShadowBitmap$1 = new PressedShape$generateShadowBitmap$1(this);
        float shadowElevation = this.drawableState.getShadowElevation();
        int lightSource = this.drawableState.getLightSource();
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        Intrinsics.checkExpressionValueIsNotNull(createBitmap, "Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)");
        Canvas canvas = new Canvas(createBitmap);
        float f = 0.0f;
        float f2 = LightSource.Companion.isLeft(lightSource) ? -shadowElevation : 0.0f;
        float f3 = LightSource.Companion.isTop(lightSource) ? -shadowElevation : 0.0f;
        int save = canvas.save();
        canvas.translate(f2, f3);
        try {
            this.lightShadowDrawable.draw(canvas);
            canvas.restoreToCount(save);
            float f4 = LightSource.Companion.isRight(lightSource) ? -shadowElevation : 0.0f;
            if (LightSource.Companion.isBottom(lightSource)) {
                f = -shadowElevation;
            }
            int save2 = canvas.save();
            canvas.translate(f4, f);
            try {
                this.darkShadowDrawable.draw(canvas);
                canvas.restoreToCount(save2);
                return pressedShape$generateShadowBitmap$1.invoke(createBitmap);
            } catch (Throwable th) {
                canvas.restoreToCount(save2);
                throw th;
            }
        } catch (Throwable th2) {
            canvas.restoreToCount(save);
            throw th2;
        }
    }

    public void draw(Canvas canvas, Path path) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        Intrinsics.checkParameterIsNotNull(path, "outlinePath");
        int save = canvas.save();
        canvas.clipPath(path);
        try {
            Bitmap bitmap = this.shadowBitmap;
            if (bitmap != null) {
                Rect inset = this.drawableState.getInset();
                canvas.drawBitmap(bitmap, (float) inset.left, (float) inset.top, (Paint) null);
            }
        } finally {
            canvas.restoreToCount(save);
        }
    }
}
