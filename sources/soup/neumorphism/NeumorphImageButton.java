package soup.neumorphism;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import androidx.appcompat.widget.AppCompatImageButton;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import soup.neumorphism.internal.util.NeumorphResources;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0019\u0018\u0000 <2\u00020\u0001:\u0001<B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013J\u0006\u0010\u0014\u001a\u00020\u0007J\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u0019\u001a\u00020\u0007J\b\u0010\u001a\u001a\u0004\u0018\u00010\u0013J\u0006\u0010\u001b\u001a\u00020\u0016J(\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u00072\u0006\u0010!\u001a\u00020\u0007H\u0002J\u0012\u0010\"\u001a\u00020\u001d2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\u0010\u0010%\u001a\u00020\u001d2\b\u0010&\u001a\u0004\u0018\u00010\u0013J\u0010\u0010%\u001a\u00020\u001d2\u0006\u0010'\u001a\u00020\u0007H\u0016J\u0012\u0010(\u001a\u00020\u001d2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\u0012\u0010)\u001a\u00020\u001d2\b\u0010#\u001a\u0004\u0018\u00010$H\u0002J&\u0010*\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u00072\u0006\u0010!\u001a\u00020\u0007J\u000e\u0010+\u001a\u00020\u001d2\u0006\u0010,\u001a\u00020\u0007J\u0010\u0010-\u001a\u00020\u001d2\b\b\u0001\u0010.\u001a\u00020\u0007J\u0010\u0010/\u001a\u00020\u001d2\b\b\u0001\u0010.\u001a\u00020\u0007J\u000e\u00100\u001a\u00020\u001d2\u0006\u00101\u001a\u00020\u0016J\u000e\u00102\u001a\u00020\u001d2\u0006\u00103\u001a\u00020\u0018J\u000e\u00104\u001a\u00020\u001d2\u0006\u00105\u001a\u00020\u0007J\u0010\u00106\u001a\u00020\u001d2\b\u00107\u001a\u0004\u0018\u00010\u0013J\u000e\u00108\u001a\u00020\u001d2\u0006\u00109\u001a\u00020\u0016J\u0010\u0010:\u001a\u00020\u001d2\u0006\u0010;\u001a\u00020\u0016H\u0016R\u000e\u0010\n\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"Lsoup/neumorphism/NeumorphImageButton;", "Landroidx/appcompat/widget/AppCompatImageButton;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "insetBottom", "insetEnd", "insetStart", "insetTop", "isInitialized", "", "shapeDrawable", "Lsoup/neumorphism/NeumorphShapeDrawable;", "getBackgroundColor", "Landroid/content/res/ColorStateList;", "getLightSource", "getShadowElevation", "", "getShapeAppearanceModel", "Lsoup/neumorphism/NeumorphShapeAppearanceModel;", "getShapeType", "getStrokeColor", "getStrokeWidth", "internalSetInset", "", "left", "top", "right", "bottom", "setBackground", "drawable", "Landroid/graphics/drawable/Drawable;", "setBackgroundColor", "backgroundColor", "color", "setBackgroundDrawable", "setBackgroundInternal", "setInset", "setLightSource", "lightSource", "setShadowColorDark", "shadowColor", "setShadowColorLight", "setShadowElevation", "shadowElevation", "setShapeAppearanceModel", "shapeAppearanceModel", "setShapeType", "shapeType", "setStrokeColor", "strokeColor", "setStrokeWidth", "strokeWidth", "setTranslationZ", "translationZ", "Companion", "neumorphism_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: NeumorphImageButton.kt */
public final class NeumorphImageButton extends AppCompatImageButton {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String LOG_TAG = "NeumorphImageView";
    private int insetBottom;
    private int insetEnd;
    private int insetStart;
    private int insetTop;
    private boolean isInitialized;
    private final NeumorphShapeDrawable shapeDrawable;

    public NeumorphImageButton(Context context) {
        this(context, (AttributeSet) null, 0, 0, 14, (DefaultConstructorMarker) null);
    }

    public NeumorphImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12, (DefaultConstructorMarker) null);
    }

    public NeumorphImageButton(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0, 8, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ NeumorphImageButton(Context context, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? R.attr.neumorphImageButtonStyle : i, (i3 & 8) != 0 ? R.style.Widget_Neumorph_ImageButton : i2);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NeumorphImageButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
        Context context2 = context;
        AttributeSet attributeSet2 = attributeSet;
        int i3 = i;
        int i4 = i2;
        Intrinsics.checkParameterIsNotNull(context2, "context");
        TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet2, R.styleable.NeumorphImageButton, i3, i4);
        Intrinsics.checkExpressionValueIsNotNull(obtainStyledAttributes, "context.obtainStyledAttr…tr, defStyleRes\n        )");
        ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(R.styleable.NeumorphImageButton_neumorph_backgroundColor);
        ColorStateList colorStateList2 = obtainStyledAttributes.getColorStateList(R.styleable.NeumorphImageButton_neumorph_strokeColor);
        float dimension = obtainStyledAttributes.getDimension(R.styleable.NeumorphImageButton_neumorph_strokeWidth, 0.0f);
        int i5 = obtainStyledAttributes.getInt(R.styleable.NeumorphImageButton_neumorph_lightSource, 0);
        int i6 = obtainStyledAttributes.getInt(R.styleable.NeumorphImageButton_neumorph_shapeType, 0);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.NeumorphImageButton_neumorph_inset, 0);
        int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.NeumorphImageButton_neumorph_insetStart, -1);
        int dimensionPixelSize3 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.NeumorphImageButton_neumorph_insetEnd, -1);
        int dimensionPixelSize4 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.NeumorphImageButton_neumorph_insetTop, -1);
        int i7 = dimensionPixelSize;
        int dimensionPixelSize5 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.NeumorphImageButton_neumorph_insetBottom, -1);
        float dimension2 = obtainStyledAttributes.getDimension(R.styleable.NeumorphImageButton_neumorph_shadowElevation, 0.0f);
        int i8 = dimensionPixelSize3;
        int i9 = dimensionPixelSize4;
        int color = NeumorphResources.INSTANCE.getColor(context2, obtainStyledAttributes, R.styleable.NeumorphImageButton_neumorph_shadowColorLight, R.color.design_default_color_shadow_light);
        int i10 = dimensionPixelSize2;
        int color2 = NeumorphResources.INSTANCE.getColor(context2, obtainStyledAttributes, R.styleable.NeumorphImageButton_neumorph_shadowColorDark, R.color.design_default_color_shadow_dark);
        obtainStyledAttributes.recycle();
        NeumorphShapeDrawable neumorphShapeDrawable = new NeumorphShapeDrawable(context2, attributeSet2, i3, i4);
        neumorphShapeDrawable.setInEditMode(isInEditMode());
        neumorphShapeDrawable.setLightSource(i5);
        neumorphShapeDrawable.setShapeType(i6);
        neumorphShapeDrawable.setShadowElevation(dimension2);
        neumorphShapeDrawable.setShadowColorLight(color);
        neumorphShapeDrawable.setShadowColorDark(color2);
        neumorphShapeDrawable.setFillColor(colorStateList);
        neumorphShapeDrawable.setStroke(dimension, colorStateList2);
        neumorphShapeDrawable.setTranslationZ(getTranslationZ());
        this.shapeDrawable = neumorphShapeDrawable;
        internalSetInset(i10 >= 0 ? i10 : i7, i9 >= 0 ? i9 : i7, i8 >= 0 ? i8 : i7, dimensionPixelSize5 >= 0 ? dimensionPixelSize5 : i7);
        setBackgroundInternal(neumorphShapeDrawable);
        this.isInitialized = true;
    }

    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    public void setBackgroundDrawable(Drawable drawable) {
        Log.i(LOG_TAG, "Setting a custom background is not supported.");
    }

    private final void setBackgroundInternal(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    public final void setShapeAppearanceModel(NeumorphShapeAppearanceModel neumorphShapeAppearanceModel) {
        Intrinsics.checkParameterIsNotNull(neumorphShapeAppearanceModel, "shapeAppearanceModel");
        this.shapeDrawable.setShapeAppearanceModel(neumorphShapeAppearanceModel);
    }

    public final NeumorphShapeAppearanceModel getShapeAppearanceModel() {
        return this.shapeDrawable.getShapeAppearanceModel();
    }

    public final void setBackgroundColor(ColorStateList colorStateList) {
        this.shapeDrawable.setFillColor(colorStateList);
    }

    public void setBackgroundColor(int i) {
        this.shapeDrawable.setFillColor(ColorStateList.valueOf(i));
    }

    public final ColorStateList getBackgroundColor() {
        return this.shapeDrawable.getFillColor();
    }

    public final void setStrokeColor(ColorStateList colorStateList) {
        this.shapeDrawable.setStrokeColor(colorStateList);
    }

    public final ColorStateList getStrokeColor() {
        return this.shapeDrawable.getStrokeColor();
    }

    public final void setStrokeWidth(float f) {
        this.shapeDrawable.setStrokeWidth(f);
    }

    public final float getStrokeWidth() {
        return this.shapeDrawable.getStrokeWidth();
    }

    public final void setLightSource(int i) {
        this.shapeDrawable.setLightSource(i);
    }

    public final int getLightSource() {
        return this.shapeDrawable.getLightSource();
    }

    public final void setShapeType(int i) {
        this.shapeDrawable.setShapeType(i);
    }

    public final int getShapeType() {
        return this.shapeDrawable.getShapeType();
    }

    public final void setInset(int i, int i2, int i3, int i4) {
        internalSetInset(i, i2, i3, i4);
    }

    private final void internalSetInset(int i, int i2, int i3, int i4) {
        boolean z;
        boolean z2 = true;
        if (this.insetStart != i) {
            this.insetStart = i;
            z = true;
        } else {
            z = false;
        }
        if (this.insetTop != i2) {
            this.insetTop = i2;
            z = true;
        }
        if (this.insetEnd != i3) {
            this.insetEnd = i3;
            z = true;
        }
        if (this.insetBottom != i4) {
            this.insetBottom = i4;
        } else {
            z2 = z;
        }
        if (z2) {
            this.shapeDrawable.setInset(i, i2, i3, i4);
            requestLayout();
            invalidateOutline();
        }
    }

    public final void setShadowElevation(float f) {
        this.shapeDrawable.setShadowElevation(f);
    }

    public final float getShadowElevation() {
        return this.shapeDrawable.getShadowElevation();
    }

    public final void setShadowColorLight(int i) {
        this.shapeDrawable.setShadowColorLight(i);
    }

    public final void setShadowColorDark(int i) {
        this.shapeDrawable.setShadowColorDark(i);
    }

    public void setTranslationZ(float f) {
        super.setTranslationZ(f);
        if (this.isInitialized) {
            this.shapeDrawable.setTranslationZ(f);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lsoup/neumorphism/NeumorphImageButton$Companion;", "", "()V", "LOG_TAG", "", "neumorphism_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: NeumorphImageButton.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
