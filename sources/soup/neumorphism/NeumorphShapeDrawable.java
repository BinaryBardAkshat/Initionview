package soup.neumorphism;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.core.view.ViewCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import soup.neumorphism.NeumorphShapeAppearanceModel;
import soup.neumorphism.internal.blur.BlurProvider;
import soup.neumorphism.internal.shape.BasinShape;
import soup.neumorphism.internal.shape.FlatShape;
import soup.neumorphism.internal.shape.PressedShape;
import soup.neumorphism.internal.shape.Shape;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000f\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b&\u0018\u0000 q2\u00020\u0001:\u0002qrB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B-\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b\u0012\b\b\u0001\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\nB\u0017\b\u0010\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fB\u000f\b\u0012\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0002\u0010\u0012J\u0018\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020\u0019H\u0002J\u0010\u0010#\u001a\u00020 2\u0006\u0010$\u001a\u00020%H\u0016J\u0010\u0010&\u001a\u00020 2\u0006\u0010$\u001a\u00020%H\u0002J\u0010\u0010'\u001a\u00020 2\u0006\u0010$\u001a\u00020%H\u0002J\b\u0010(\u001a\u00020\u001bH\u0002J\b\u0010)\u001a\u00020*H\u0002J\n\u0010+\u001a\u0004\u0018\u00010,H\u0016J\b\u0010-\u001a\u0004\u0018\u00010.J\u0006\u0010/\u001a\u00020\bJ\b\u00100\u001a\u00020\bH\u0016J\u0010\u00101\u001a\u00020 2\u0006\u00102\u001a\u000203H\u0016J\u0006\u00104\u001a\u00020\u0019J\b\u00105\u001a\u0004\u0018\u000106J\u0006\u00107\u001a\u000208J\u0006\u00109\u001a\u00020\fJ\u0006\u0010:\u001a\u00020\bJ\b\u0010;\u001a\u0004\u0018\u00010.J\u0006\u0010<\u001a\u000208J\u0006\u0010=\u001a\u000208J\u0006\u0010>\u001a\u000208J\b\u0010?\u001a\u00020\u0014H\u0002J\b\u0010@\u001a\u00020\u0014H\u0002J\b\u0010A\u001a\u00020 H\u0016J\b\u0010B\u001a\u00020 H\u0002J\b\u0010C\u001a\u00020\u0014H\u0016J\b\u0010D\u001a\u00020\u0001H\u0016J\u0010\u0010E\u001a\u00020 2\u0006\u0010!\u001a\u00020*H\u0014J\u0010\u0010F\u001a\u00020\u00142\u0006\u0010G\u001a\u00020HH\u0014J\u0010\u0010I\u001a\u00020 2\u0006\u0010J\u001a\u00020\bH\u0016J\u0012\u0010K\u001a\u00020 2\b\u0010L\u001a\u0004\u0018\u00010MH\u0016J\u0010\u0010N\u001a\u00020 2\b\u0010O\u001a\u0004\u0018\u00010.J\u000e\u0010P\u001a\u00020 2\u0006\u0010Q\u001a\u00020\u0014J&\u0010R\u001a\u00020 2\u0006\u0010S\u001a\u00020\b2\u0006\u0010T\u001a\u00020\b2\u0006\u0010U\u001a\u00020\b2\u0006\u0010V\u001a\u00020\bJ\u000e\u0010W\u001a\u00020 2\u0006\u0010X\u001a\u00020\bJ\u000e\u0010Y\u001a\u00020 2\u0006\u0010Z\u001a\u000206J\u0010\u0010[\u001a\u00020 2\b\b\u0001\u0010\\\u001a\u00020\bJ\u0010\u0010]\u001a\u00020 2\b\b\u0001\u0010\\\u001a\u00020\bJ\u000e\u0010^\u001a\u00020 2\u0006\u0010_\u001a\u000208J\u000e\u0010`\u001a\u00020 2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010a\u001a\u00020 2\u0006\u0010b\u001a\u00020\bJ\u0018\u0010c\u001a\u00020 2\u0006\u0010d\u001a\u0002082\b\u0010e\u001a\u0004\u0018\u00010.J\u0018\u0010c\u001a\u00020 2\u0006\u0010d\u001a\u0002082\b\b\u0001\u0010e\u001a\u00020\bJ\u0010\u0010f\u001a\u00020 2\b\u0010e\u001a\u0004\u0018\u00010.J\u000e\u0010g\u001a\u00020 2\u0006\u0010d\u001a\u000208J\u000e\u0010h\u001a\u00020 2\u0006\u0010i\u001a\u000208J\u0018\u0010j\u001a\u00020\u00142\u0006\u0010k\u001a\u00020\u00142\u0006\u0010l\u001a\u00020\u0014H\u0016J\u000e\u0010m\u001a\u00020 2\u0006\u0010n\u001a\u000208J\u0018\u0010o\u001a\u00020\u001d2\u0006\u0010b\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010p\u001a\u00020\u00142\u0006\u0010G\u001a\u00020HH\u0002R\u000e\u0010\u0013\u001a\u00020\u0014X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0014X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000¨\u0006s"}, d2 = {"Lsoup/neumorphism/NeumorphShapeDrawable;", "Landroid/graphics/drawable/Drawable;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "shapeAppearanceModel", "Lsoup/neumorphism/NeumorphShapeAppearanceModel;", "blurProvider", "Lsoup/neumorphism/internal/blur/BlurProvider;", "(Lsoup/neumorphism/NeumorphShapeAppearanceModel;Lsoup/neumorphism/internal/blur/BlurProvider;)V", "drawableState", "Lsoup/neumorphism/NeumorphShapeDrawable$NeumorphShapeDrawableState;", "(Lsoup/neumorphism/NeumorphShapeDrawable$NeumorphShapeDrawableState;)V", "dirty", "", "fillPaint", "Landroid/graphics/Paint;", "isVisibleChanging", "outlinePath", "Landroid/graphics/Path;", "rectF", "Landroid/graphics/RectF;", "shadow", "Lsoup/neumorphism/internal/shape/Shape;", "strokePaint", "calculateOutlinePath", "", "bounds", "path", "draw", "canvas", "Landroid/graphics/Canvas;", "drawFillShape", "drawStrokeShape", "getBoundsAsRectF", "getBoundsInternal", "Landroid/graphics/Rect;", "getConstantState", "Landroid/graphics/drawable/Drawable$ConstantState;", "getFillColor", "Landroid/content/res/ColorStateList;", "getLightSource", "getOpacity", "getOutline", "outline", "Landroid/graphics/Outline;", "getOutlinePath", "getPaintStyle", "Landroid/graphics/Paint$Style;", "getShadowElevation", "", "getShapeAppearanceModel", "getShapeType", "getStrokeColor", "getStrokeWidth", "getTranslationZ", "getZ", "hasFill", "hasStroke", "invalidateSelf", "invalidateSelfIgnoreShape", "isStateful", "mutate", "onBoundsChange", "onStateChange", "state", "", "setAlpha", "alpha", "setColorFilter", "colorFilter", "Landroid/graphics/ColorFilter;", "setFillColor", "fillColor", "setInEditMode", "inEditMode", "setInset", "left", "top", "right", "bottom", "setLightSource", "lightSource", "setPaintStyle", "paintStyle", "setShadowColorDark", "shadowColor", "setShadowColorLight", "setShadowElevation", "shadowElevation", "setShapeAppearanceModel", "setShapeType", "shapeType", "setStroke", "strokeWidth", "strokeColor", "setStrokeColor", "setStrokeWidth", "setTranslationZ", "translationZ", "setVisible", "visible", "restart", "setZ", "z", "shadowOf", "updateColorsForState", "Companion", "NeumorphShapeDrawableState", "neumorphism_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: NeumorphShapeDrawable.kt */
public final class NeumorphShapeDrawable extends Drawable {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public boolean dirty;
    private NeumorphShapeDrawableState drawableState;
    private final Paint fillPaint;
    private boolean isVisibleChanging;
    private final Path outlinePath;
    private final RectF rectF;
    private Shape shadow;
    private final Paint strokePaint;

    public int getOpacity() {
        return -3;
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public /* synthetic */ NeumorphShapeDrawable(NeumorphShapeDrawableState neumorphShapeDrawableState, DefaultConstructorMarker defaultConstructorMarker) {
        this(neumorphShapeDrawableState);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public NeumorphShapeDrawable(Context context) {
        this(new NeumorphShapeAppearanceModel(), new BlurProvider(context));
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public NeumorphShapeDrawable(Context context, AttributeSet attributeSet, int i, int i2) {
        this(NeumorphShapeAppearanceModel.Companion.builder$default(NeumorphShapeAppearanceModel.Companion, context, attributeSet, i, i2, 0.0f, 16, (Object) null).build(), new BlurProvider(context));
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public NeumorphShapeDrawable(NeumorphShapeAppearanceModel neumorphShapeAppearanceModel, BlurProvider blurProvider) {
        this(new NeumorphShapeDrawableState(neumorphShapeAppearanceModel, blurProvider));
        Intrinsics.checkParameterIsNotNull(neumorphShapeAppearanceModel, "shapeAppearanceModel");
        Intrinsics.checkParameterIsNotNull(blurProvider, "blurProvider");
    }

    private NeumorphShapeDrawable(NeumorphShapeDrawableState neumorphShapeDrawableState) {
        Paint paint = new Paint(1);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(0);
        this.fillPaint = paint;
        Paint paint2 = new Paint(1);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setColor(0);
        this.strokePaint = paint2;
        this.rectF = new RectF();
        this.outlinePath = new Path();
        this.drawableState = neumorphShapeDrawableState;
        this.shadow = shadowOf(neumorphShapeDrawableState.getShapeType(), neumorphShapeDrawableState);
    }

    private final Shape shadowOf(int i, NeumorphShapeDrawableState neumorphShapeDrawableState) {
        if (i == 0) {
            return new FlatShape(neumorphShapeDrawableState);
        }
        if (i == 1) {
            return new PressedShape(neumorphShapeDrawableState);
        }
        if (i == 2) {
            return new BasinShape(neumorphShapeDrawableState);
        }
        throw new IllegalArgumentException("ShapeType(" + i + ") is invalid.");
    }

    public Drawable.ConstantState getConstantState() {
        return this.drawableState;
    }

    public Drawable mutate() {
        NeumorphShapeDrawableState neumorphShapeDrawableState = new NeumorphShapeDrawableState(this.drawableState);
        this.drawableState = neumorphShapeDrawableState;
        Shape shape = this.shadow;
        if (shape != null) {
            shape.setDrawableState(neumorphShapeDrawableState);
        }
        return this;
    }

    public final void setShapeAppearanceModel(NeumorphShapeAppearanceModel neumorphShapeAppearanceModel) {
        Intrinsics.checkParameterIsNotNull(neumorphShapeAppearanceModel, "shapeAppearanceModel");
        this.drawableState.setShapeAppearanceModel(neumorphShapeAppearanceModel);
        invalidateSelf();
    }

    public final NeumorphShapeAppearanceModel getShapeAppearanceModel() {
        return this.drawableState.getShapeAppearanceModel();
    }

    public final void setFillColor(ColorStateList colorStateList) {
        if (!Intrinsics.areEqual((Object) this.drawableState.getFillColor(), (Object) colorStateList)) {
            this.drawableState.setFillColor(colorStateList);
            int[] state = getState();
            Intrinsics.checkExpressionValueIsNotNull(state, "state");
            onStateChange(state);
        }
    }

    public final ColorStateList getFillColor() {
        return this.drawableState.getFillColor();
    }

    public final void setStrokeColor(ColorStateList colorStateList) {
        if (!Intrinsics.areEqual((Object) this.drawableState.getStrokeColor(), (Object) colorStateList)) {
            this.drawableState.setStrokeColor(colorStateList);
            int[] state = getState();
            Intrinsics.checkExpressionValueIsNotNull(state, "state");
            onStateChange(state);
        }
    }

    public final ColorStateList getStrokeColor() {
        return this.drawableState.getStrokeColor();
    }

    public final void setStroke(float f, int i) {
        setStrokeWidth(f);
        setStrokeColor(ColorStateList.valueOf(i));
    }

    public final void setStroke(float f, ColorStateList colorStateList) {
        setStrokeWidth(f);
        setStrokeColor(colorStateList);
    }

    public final float getStrokeWidth() {
        return this.drawableState.getStrokeWidth();
    }

    public final void setStrokeWidth(float f) {
        this.drawableState.setStrokeWidth(f);
        invalidateSelf();
    }

    public void setAlpha(int i) {
        if (this.drawableState.getAlpha() != i) {
            this.drawableState.setAlpha(i);
            invalidateSelfIgnoreShape();
        }
    }

    private final Rect getBoundsInternal() {
        Rect inset = this.drawableState.getInset();
        Rect bounds = super.getBounds();
        Intrinsics.checkExpressionValueIsNotNull(bounds, "super.getBounds()");
        return new Rect(bounds.left + inset.left, bounds.top + inset.top, bounds.right - inset.right, bounds.bottom - inset.bottom);
    }

    private final RectF getBoundsAsRectF() {
        this.rectF.set(getBoundsInternal());
        return this.rectF;
    }

    public final void setInset(int i, int i2, int i3, int i4) {
        this.drawableState.getInset().set(i, i2, i3, i4);
        invalidateSelf();
    }

    public final void setLightSource(int i) {
        if (this.drawableState.getLightSource() != i) {
            this.drawableState.setLightSource(i);
            invalidateSelf();
        }
    }

    public final int getLightSource() {
        return this.drawableState.getLightSource();
    }

    public final void setShapeType(int i) {
        if (this.drawableState.getShapeType() != i) {
            this.drawableState.setShapeType(i);
            this.shadow = shadowOf(i, this.drawableState);
            invalidateSelf();
        }
    }

    public final int getShapeType() {
        return this.drawableState.getShapeType();
    }

    public final void setShadowElevation(float f) {
        if (this.drawableState.getShadowElevation() != f) {
            this.drawableState.setShadowElevation(f);
            invalidateSelf();
        }
    }

    public final float getShadowElevation() {
        return this.drawableState.getShadowElevation();
    }

    public final void setShadowColorLight(int i) {
        if (this.drawableState.getShadowColorLight() != i) {
            this.drawableState.setShadowColorLight(i);
            invalidateSelf();
        }
    }

    public final void setShadowColorDark(int i) {
        if (this.drawableState.getShadowColorDark() != i) {
            this.drawableState.setShadowColorDark(i);
            invalidateSelf();
        }
    }

    public final float getTranslationZ() {
        return this.drawableState.getTranslationZ();
    }

    public final void setTranslationZ(float f) {
        if (this.drawableState.getTranslationZ() != f) {
            this.drawableState.setTranslationZ(f);
            invalidateSelfIgnoreShape();
        }
    }

    public final float getZ() {
        return getShadowElevation() + getTranslationZ();
    }

    public final void setZ(float f) {
        setTranslationZ(f - getShadowElevation());
    }

    public void invalidateSelf() {
        if (!this.isVisibleChanging) {
            this.dirty = true;
        }
        super.invalidateSelf();
    }

    private final void invalidateSelfIgnoreShape() {
        super.invalidateSelf();
    }

    public final Paint.Style getPaintStyle() {
        return this.drawableState.getPaintStyle();
    }

    public final void setPaintStyle(Paint.Style style) {
        Intrinsics.checkParameterIsNotNull(style, "paintStyle");
        this.drawableState.setPaintStyle(style);
        invalidateSelfIgnoreShape();
    }

    private final boolean hasFill() {
        return this.drawableState.getPaintStyle() == Paint.Style.FILL_AND_STROKE || this.drawableState.getPaintStyle() == Paint.Style.FILL;
    }

    private final boolean hasStroke() {
        return (this.drawableState.getPaintStyle() == Paint.Style.FILL_AND_STROKE || this.drawableState.getPaintStyle() == Paint.Style.STROKE) && this.strokePaint.getStrokeWidth() > ((float) 0);
    }

    public boolean setVisible(boolean z, boolean z2) {
        this.isVisibleChanging = true;
        boolean visible = super.setVisible(z, z2);
        this.isVisibleChanging = false;
        return visible;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        Intrinsics.checkParameterIsNotNull(rect, "bounds");
        this.dirty = true;
        super.onBoundsChange(rect);
    }

    public void draw(Canvas canvas) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        int alpha = this.fillPaint.getAlpha();
        Paint paint = this.fillPaint;
        Companion companion = Companion;
        paint.setAlpha(companion.modulateAlpha(alpha, this.drawableState.getAlpha()));
        this.strokePaint.setStrokeWidth(this.drawableState.getStrokeWidth());
        int alpha2 = this.strokePaint.getAlpha();
        this.strokePaint.setAlpha(companion.modulateAlpha(alpha2, this.drawableState.getAlpha()));
        if (this.dirty) {
            calculateOutlinePath(getBoundsAsRectF(), this.outlinePath);
            Shape shape = this.shadow;
            if (shape != null) {
                shape.updateShadowBitmap(getBoundsInternal());
            }
            this.dirty = false;
        }
        if (hasFill()) {
            drawFillShape(canvas);
        }
        Shape shape2 = this.shadow;
        if (shape2 != null) {
            shape2.draw(canvas, this.outlinePath);
        }
        if (hasStroke()) {
            drawStrokeShape(canvas);
        }
        this.fillPaint.setAlpha(alpha);
        this.strokePaint.setAlpha(alpha2);
    }

    private final void drawFillShape(Canvas canvas) {
        canvas.drawPath(this.outlinePath, this.fillPaint);
    }

    private final void drawStrokeShape(Canvas canvas) {
        canvas.drawPath(this.outlinePath, this.strokePaint);
    }

    public final Path getOutlinePath() {
        return this.outlinePath;
    }

    private final void calculateOutlinePath(RectF rectF2, Path path) {
        NeumorphShapeAppearanceModel shapeAppearanceModel = this.drawableState.getShapeAppearanceModel();
        float f = (float) this.drawableState.getInset().left;
        float f2 = (float) this.drawableState.getInset().top;
        float width = f + rectF2.width();
        float height = f2 + rectF2.height();
        path.reset();
        int cornerFamily = shapeAppearanceModel.getCornerFamily();
        if (cornerFamily == 0) {
            path.addRoundRect(f, f2, width, height, shapeAppearanceModel.getCornerRadii$neumorphism_release(Math.min(rectF2.width() / 2.0f, rectF2.height() / 2.0f)), Path.Direction.CW);
        } else if (cornerFamily == 1) {
            path.addOval(f, f2, width, height, Path.Direction.CW);
        }
        path.close();
    }

    public void getOutline(Outline outline) {
        Intrinsics.checkParameterIsNotNull(outline, "outline");
        int cornerFamily = this.drawableState.getShapeAppearanceModel().getCornerFamily();
        if (cornerFamily == 0) {
            outline.setRect(getBoundsInternal());
        } else if (cornerFamily == 1) {
            outline.setOval(getBoundsInternal());
        }
    }

    public boolean isStateful() {
        if (super.isStateful()) {
            return true;
        }
        ColorStateList fillColor = this.drawableState.getFillColor();
        return fillColor != null && fillColor.isStateful();
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        Intrinsics.checkParameterIsNotNull(iArr, "state");
        boolean updateColorsForState = updateColorsForState(iArr);
        if (updateColorsForState) {
            invalidateSelf();
        }
        return updateColorsForState;
    }

    private final boolean updateColorsForState(int[] iArr) {
        int color;
        int colorForState;
        ColorStateList fillColor = this.drawableState.getFillColor();
        boolean z = true;
        boolean z2 = false;
        if (!(fillColor == null || (color = this.fillPaint.getColor()) == (colorForState = fillColor.getColorForState(iArr, color)))) {
            this.fillPaint.setColor(colorForState);
            z2 = true;
        }
        ColorStateList strokeColor = this.drawableState.getStrokeColor();
        if (strokeColor == null) {
            return z2;
        }
        int color2 = this.strokePaint.getColor();
        int colorForState2 = strokeColor.getColorForState(iArr, color2);
        if (color2 != colorForState2) {
            this.strokePaint.setColor(colorForState2);
        } else {
            z = z2;
        }
        return z;
    }

    public final void setInEditMode(boolean z) {
        this.drawableState.setInEditMode(z);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0007\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u000f\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\u0000¢\u0006\u0002\u0010\bJ\b\u0010K\u001a\u00020\nH\u0016J\b\u0010L\u001a\u00020MH\u0016R\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\u001eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R \u0010#\u001a\u00020\nX\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b$\u0010%\u001a\u0004\b&\u0010\f\"\u0004\b'\u0010\u000eR\u001a\u0010(\u001a\u00020)X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010.\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\f\"\u0004\b0\u0010\u000eR\u001a\u00101\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\f\"\u0004\b3\u0010\u000eR\u001a\u00104\u001a\u000205X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R \u0010>\u001a\u00020\nX\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b?\u0010%\u001a\u0004\b@\u0010\f\"\u0004\bA\u0010\u000eR\u001c\u0010B\u001a\u0004\u0018\u00010\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010\u0014\"\u0004\bD\u0010\u0016R\u001a\u0010E\u001a\u000205X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u00107\"\u0004\bG\u00109R\u001a\u0010H\u001a\u000205X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u00107\"\u0004\bJ\u00109¨\u0006N"}, d2 = {"Lsoup/neumorphism/NeumorphShapeDrawable$NeumorphShapeDrawableState;", "Landroid/graphics/drawable/Drawable$ConstantState;", "shapeAppearanceModel", "Lsoup/neumorphism/NeumorphShapeAppearanceModel;", "blurProvider", "Lsoup/neumorphism/internal/blur/BlurProvider;", "(Lsoup/neumorphism/NeumorphShapeAppearanceModel;Lsoup/neumorphism/internal/blur/BlurProvider;)V", "orig", "(Lsoup/neumorphism/NeumorphShapeDrawable$NeumorphShapeDrawableState;)V", "alpha", "", "getAlpha", "()I", "setAlpha", "(I)V", "getBlurProvider", "()Lsoup/neumorphism/internal/blur/BlurProvider;", "fillColor", "Landroid/content/res/ColorStateList;", "getFillColor", "()Landroid/content/res/ColorStateList;", "setFillColor", "(Landroid/content/res/ColorStateList;)V", "inEditMode", "", "getInEditMode", "()Z", "setInEditMode", "(Z)V", "inset", "Landroid/graphics/Rect;", "getInset", "()Landroid/graphics/Rect;", "setInset", "(Landroid/graphics/Rect;)V", "lightSource", "lightSource$annotations", "()V", "getLightSource", "setLightSource", "paintStyle", "Landroid/graphics/Paint$Style;", "getPaintStyle", "()Landroid/graphics/Paint$Style;", "setPaintStyle", "(Landroid/graphics/Paint$Style;)V", "shadowColorDark", "getShadowColorDark", "setShadowColorDark", "shadowColorLight", "getShadowColorLight", "setShadowColorLight", "shadowElevation", "", "getShadowElevation", "()F", "setShadowElevation", "(F)V", "getShapeAppearanceModel", "()Lsoup/neumorphism/NeumorphShapeAppearanceModel;", "setShapeAppearanceModel", "(Lsoup/neumorphism/NeumorphShapeAppearanceModel;)V", "shapeType", "shapeType$annotations", "getShapeType", "setShapeType", "strokeColor", "getStrokeColor", "setStrokeColor", "strokeWidth", "getStrokeWidth", "setStrokeWidth", "translationZ", "getTranslationZ", "setTranslationZ", "getChangingConfigurations", "newDrawable", "Landroid/graphics/drawable/Drawable;", "neumorphism_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: NeumorphShapeDrawable.kt */
    public static final class NeumorphShapeDrawableState extends Drawable.ConstantState {
        private int alpha = 255;
        private final BlurProvider blurProvider;
        private ColorStateList fillColor;
        private boolean inEditMode;
        private Rect inset = new Rect();
        private int lightSource;
        private Paint.Style paintStyle = Paint.Style.FILL_AND_STROKE;
        private int shadowColorDark = ViewCompat.MEASURED_STATE_MASK;
        private int shadowColorLight = -1;
        private float shadowElevation;
        private NeumorphShapeAppearanceModel shapeAppearanceModel;
        private int shapeType;
        private ColorStateList strokeColor;
        private float strokeWidth;
        private float translationZ;

        public static /* synthetic */ void lightSource$annotations() {
        }

        public static /* synthetic */ void shapeType$annotations() {
        }

        public int getChangingConfigurations() {
            return 0;
        }

        public final NeumorphShapeAppearanceModel getShapeAppearanceModel() {
            return this.shapeAppearanceModel;
        }

        public final void setShapeAppearanceModel(NeumorphShapeAppearanceModel neumorphShapeAppearanceModel) {
            Intrinsics.checkParameterIsNotNull(neumorphShapeAppearanceModel, "<set-?>");
            this.shapeAppearanceModel = neumorphShapeAppearanceModel;
        }

        public final BlurProvider getBlurProvider() {
            return this.blurProvider;
        }

        public final boolean getInEditMode() {
            return this.inEditMode;
        }

        public final void setInEditMode(boolean z) {
            this.inEditMode = z;
        }

        public final Rect getInset() {
            return this.inset;
        }

        public final void setInset(Rect rect) {
            Intrinsics.checkParameterIsNotNull(rect, "<set-?>");
            this.inset = rect;
        }

        public final ColorStateList getFillColor() {
            return this.fillColor;
        }

        public final void setFillColor(ColorStateList colorStateList) {
            this.fillColor = colorStateList;
        }

        public final ColorStateList getStrokeColor() {
            return this.strokeColor;
        }

        public final void setStrokeColor(ColorStateList colorStateList) {
            this.strokeColor = colorStateList;
        }

        public final float getStrokeWidth() {
            return this.strokeWidth;
        }

        public final void setStrokeWidth(float f) {
            this.strokeWidth = f;
        }

        public final int getAlpha() {
            return this.alpha;
        }

        public final void setAlpha(int i) {
            this.alpha = i;
        }

        public final int getLightSource() {
            return this.lightSource;
        }

        public final void setLightSource(int i) {
            this.lightSource = i;
        }

        public final int getShapeType() {
            return this.shapeType;
        }

        public final void setShapeType(int i) {
            this.shapeType = i;
        }

        public final float getShadowElevation() {
            return this.shadowElevation;
        }

        public final void setShadowElevation(float f) {
            this.shadowElevation = f;
        }

        public final int getShadowColorLight() {
            return this.shadowColorLight;
        }

        public final void setShadowColorLight(int i) {
            this.shadowColorLight = i;
        }

        public final int getShadowColorDark() {
            return this.shadowColorDark;
        }

        public final void setShadowColorDark(int i) {
            this.shadowColorDark = i;
        }

        public final float getTranslationZ() {
            return this.translationZ;
        }

        public final void setTranslationZ(float f) {
            this.translationZ = f;
        }

        public final Paint.Style getPaintStyle() {
            return this.paintStyle;
        }

        public final void setPaintStyle(Paint.Style style) {
            Intrinsics.checkParameterIsNotNull(style, "<set-?>");
            this.paintStyle = style;
        }

        public NeumorphShapeDrawableState(NeumorphShapeAppearanceModel neumorphShapeAppearanceModel, BlurProvider blurProvider2) {
            Intrinsics.checkParameterIsNotNull(neumorphShapeAppearanceModel, "shapeAppearanceModel");
            Intrinsics.checkParameterIsNotNull(blurProvider2, "blurProvider");
            this.shapeAppearanceModel = neumorphShapeAppearanceModel;
            this.blurProvider = blurProvider2;
        }

        public NeumorphShapeDrawableState(NeumorphShapeDrawableState neumorphShapeDrawableState) {
            Intrinsics.checkParameterIsNotNull(neumorphShapeDrawableState, "orig");
            this.shapeAppearanceModel = neumorphShapeDrawableState.shapeAppearanceModel;
            this.blurProvider = neumorphShapeDrawableState.blurProvider;
            this.inEditMode = neumorphShapeDrawableState.inEditMode;
            this.inset = new Rect(neumorphShapeDrawableState.inset);
            this.fillColor = neumorphShapeDrawableState.fillColor;
            this.strokeColor = neumorphShapeDrawableState.strokeColor;
            this.strokeWidth = neumorphShapeDrawableState.strokeWidth;
            this.alpha = neumorphShapeDrawableState.alpha;
            this.lightSource = neumorphShapeDrawableState.lightSource;
            this.shapeType = neumorphShapeDrawableState.shapeType;
            this.shadowElevation = neumorphShapeDrawableState.shadowElevation;
            this.shadowColorLight = neumorphShapeDrawableState.shadowColorLight;
            this.shadowColorDark = neumorphShapeDrawableState.shadowColorDark;
            this.translationZ = neumorphShapeDrawableState.translationZ;
            this.paintStyle = neumorphShapeDrawableState.paintStyle;
        }

        public Drawable newDrawable() {
            NeumorphShapeDrawable neumorphShapeDrawable = new NeumorphShapeDrawable(this, (DefaultConstructorMarker) null);
            neumorphShapeDrawable.dirty = true;
            return neumorphShapeDrawable;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002¨\u0006\u0007"}, d2 = {"Lsoup/neumorphism/NeumorphShapeDrawable$Companion;", "", "()V", "modulateAlpha", "", "paintAlpha", "alpha", "neumorphism_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: NeumorphShapeDrawable.kt */
    public static final class Companion {
        /* access modifiers changed from: private */
        public final int modulateAlpha(int i, int i2) {
            return (i * (i2 + (i2 >>> 7))) >>> 8;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
