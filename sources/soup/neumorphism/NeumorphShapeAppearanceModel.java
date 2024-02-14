package soup.neumorphism;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0014\n\u0002\b\u0007\u0018\u0000 \u00182\u00020\u0001:\u0002\u0017\u0018B\u000f\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0007\b\u0016¢\u0006\u0002\u0010\u0005J\u0006\u0010\u000e\u001a\u00020\u0007J\u0006\u0010\u000f\u001a\u00020\u0007J\u0006\u0010\u0010\u001a\u00020\nJ\u0015\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\u0014J\u0006\u0010\u0015\u001a\u00020\u0007J\u0006\u0010\u0016\u001a\u00020\u0007R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\nX\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u000b\u0010\u0005R\u000e\u0010\f\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lsoup/neumorphism/NeumorphShapeAppearanceModel;", "", "builder", "Lsoup/neumorphism/NeumorphShapeAppearanceModel$Builder;", "(Lsoup/neumorphism/NeumorphShapeAppearanceModel$Builder;)V", "()V", "bottomLeftCornerSize", "", "bottomRightCornerSize", "cornerFamily", "", "cornerFamily$annotations", "topLeftCornerSize", "topRightCornerSize", "getBottomLeftCornerSize", "getBottomRightCornerSize", "getCornerFamily", "getCornerRadii", "", "maximum", "getCornerRadii$neumorphism_release", "getTopLeftCornerSize", "getTopRightCornerSize", "Builder", "Companion", "neumorphism_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: NeumorphShapeAppearanceModel.kt */
public final class NeumorphShapeAppearanceModel {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final float bottomLeftCornerSize;
    private final float bottomRightCornerSize;
    private final int cornerFamily;
    private final float topLeftCornerSize;
    private final float topRightCornerSize;

    private static /* synthetic */ void cornerFamily$annotations() {
    }

    public /* synthetic */ NeumorphShapeAppearanceModel(Builder builder, DefaultConstructorMarker defaultConstructorMarker) {
        this(builder);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u001b\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\rJ\u0018\u0010\u001b\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\r2\b\b\u0001\u0010\u001c\u001a\u00020\u0004J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\u000b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0004J\u000e\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u0004J\u000e\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u0004J\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0004R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR \u0010\f\u001a\u00020\rX\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u000e\u0010\u0002\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0006\"\u0004\b\u0015\u0010\bR\u001a\u0010\u0016\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0006\"\u0004\b\u0018\u0010\b¨\u0006\u001f"}, d2 = {"Lsoup/neumorphism/NeumorphShapeAppearanceModel$Builder;", "", "()V", "bottomLeftCornerSize", "", "getBottomLeftCornerSize", "()F", "setBottomLeftCornerSize", "(F)V", "bottomRightCornerSize", "getBottomRightCornerSize", "setBottomRightCornerSize", "cornerFamily", "", "cornerFamily$annotations", "getCornerFamily", "()I", "setCornerFamily", "(I)V", "topLeftCornerSize", "getTopLeftCornerSize", "setTopLeftCornerSize", "topRightCornerSize", "getTopRightCornerSize", "setTopRightCornerSize", "build", "Lsoup/neumorphism/NeumorphShapeAppearanceModel;", "setAllCorners", "cornerSize", "setCornerRadius", "cornerRadius", "neumorphism_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: NeumorphShapeAppearanceModel.kt */
    public static final class Builder {
        private float bottomLeftCornerSize;
        private float bottomRightCornerSize;
        private int cornerFamily;
        private float topLeftCornerSize;
        private float topRightCornerSize;

        public static /* synthetic */ void cornerFamily$annotations() {
        }

        public final int getCornerFamily() {
            return this.cornerFamily;
        }

        public final void setCornerFamily(int i) {
            this.cornerFamily = i;
        }

        public final float getTopLeftCornerSize() {
            return this.topLeftCornerSize;
        }

        /* renamed from: setTopLeftCornerSize  reason: collision with other method in class */
        public final void m1440setTopLeftCornerSize(float f) {
            this.topLeftCornerSize = f;
        }

        public final float getTopRightCornerSize() {
            return this.topRightCornerSize;
        }

        /* renamed from: setTopRightCornerSize  reason: collision with other method in class */
        public final void m1441setTopRightCornerSize(float f) {
            this.topRightCornerSize = f;
        }

        public final float getBottomLeftCornerSize() {
            return this.bottomLeftCornerSize;
        }

        /* renamed from: setBottomLeftCornerSize  reason: collision with other method in class */
        public final void m1438setBottomLeftCornerSize(float f) {
            this.bottomLeftCornerSize = f;
        }

        public final float getBottomRightCornerSize() {
            return this.bottomRightCornerSize;
        }

        /* renamed from: setBottomRightCornerSize  reason: collision with other method in class */
        public final void m1439setBottomRightCornerSize(float f) {
            this.bottomRightCornerSize = f;
        }

        public final Builder setAllCorners(int i, float f) {
            return setAllCorners(i).setCornerRadius(f);
        }

        public final Builder setAllCorners(int i) {
            Builder builder = this;
            builder.cornerFamily = i;
            return builder;
        }

        public final Builder setCornerRadius(float f) {
            return setTopLeftCornerSize(f).setTopRightCornerSize(f).setBottomLeftCornerSize(f).setBottomRightCornerSize(f);
        }

        public final Builder setTopLeftCornerSize(float f) {
            Builder builder = this;
            builder.topLeftCornerSize = f;
            return builder;
        }

        public final Builder setTopRightCornerSize(float f) {
            Builder builder = this;
            builder.topRightCornerSize = f;
            return builder;
        }

        public final Builder setBottomLeftCornerSize(float f) {
            Builder builder = this;
            builder.bottomLeftCornerSize = f;
            return builder;
        }

        public final Builder setBottomRightCornerSize(float f) {
            Builder builder = this;
            builder.bottomRightCornerSize = f;
            return builder;
        }

        public final NeumorphShapeAppearanceModel build() {
            return new NeumorphShapeAppearanceModel(this, (DefaultConstructorMarker) null);
        }
    }

    private NeumorphShapeAppearanceModel(Builder builder) {
        this.cornerFamily = builder.getCornerFamily();
        this.topLeftCornerSize = builder.getTopLeftCornerSize();
        this.topRightCornerSize = builder.getTopRightCornerSize();
        this.bottomLeftCornerSize = builder.getBottomLeftCornerSize();
        this.bottomRightCornerSize = builder.getBottomRightCornerSize();
    }

    public NeumorphShapeAppearanceModel() {
        this.cornerFamily = 0;
        this.topLeftCornerSize = 0.0f;
        this.topRightCornerSize = 0.0f;
        this.bottomLeftCornerSize = 0.0f;
        this.bottomRightCornerSize = 0.0f;
    }

    public final int getCornerFamily() {
        return this.cornerFamily;
    }

    public final float getTopLeftCornerSize() {
        return this.topLeftCornerSize;
    }

    public final float getTopRightCornerSize() {
        return this.topRightCornerSize;
    }

    public final float getBottomLeftCornerSize() {
        return this.bottomLeftCornerSize;
    }

    public final float getBottomRightCornerSize() {
        return this.bottomRightCornerSize;
    }

    public final float[] getCornerRadii$neumorphism_release(float f) {
        float min = Math.min(f, getTopLeftCornerSize());
        float min2 = Math.min(f, getTopRightCornerSize());
        float min3 = Math.min(f, getBottomLeftCornerSize());
        float min4 = Math.min(f, getBottomRightCornerSize());
        return new float[]{min, min, min2, min2, min3, min3, min4, min4};
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J6\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0001\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\rJ\"\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u000e\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rH\u0002J\u001c\u0010\u000f\u001a\u00020\r*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\rH\u0002¨\u0006\u0013"}, d2 = {"Lsoup/neumorphism/NeumorphShapeAppearanceModel$Companion;", "", "()V", "builder", "Lsoup/neumorphism/NeumorphShapeAppearanceModel$Builder;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "defStyleRes", "defaultCornerSize", "", "shapeAppearanceResId", "getCornerSize", "Landroid/content/res/TypedArray;", "index", "defaultValue", "neumorphism_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: NeumorphShapeAppearanceModel.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Builder builder() {
            return new Builder();
        }

        public static /* synthetic */ Builder builder$default(Companion companion, Context context, AttributeSet attributeSet, int i, int i2, float f, int i3, Object obj) {
            return companion.builder(context, attributeSet, i, i2, (i3 & 16) != 0 ? 0.0f : f);
        }

        public final Builder builder(Context context, AttributeSet attributeSet, int i, int i2, float f) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.NeumorphShape, i, i2);
            Intrinsics.checkExpressionValueIsNotNull(obtainStyledAttributes, "context.obtainStyledAttr…defStyleRes\n            )");
            int resourceId = obtainStyledAttributes.getResourceId(R.styleable.NeumorphShape_neumorph_shapeAppearance, 0);
            obtainStyledAttributes.recycle();
            return builder(context, resourceId, f);
        }

        private final Builder builder(Context context, int i, float f) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i, R.styleable.NeumorphShapeAppearance);
            Intrinsics.checkExpressionValueIsNotNull(obtainStyledAttributes, "context.obtainStyledAttr…eAppearance\n            )");
            try {
                int i2 = obtainStyledAttributes.getInt(R.styleable.NeumorphShapeAppearance_neumorph_cornerFamily, 0);
                float cornerSize = getCornerSize(obtainStyledAttributes, R.styleable.NeumorphShapeAppearance_neumorph_cornerSize, f);
                float cornerSize2 = getCornerSize(obtainStyledAttributes, R.styleable.NeumorphShapeAppearance_neumorph_cornerSizeTopLeft, cornerSize);
                float cornerSize3 = getCornerSize(obtainStyledAttributes, R.styleable.NeumorphShapeAppearance_neumorph_cornerSizeTopRight, cornerSize);
                float cornerSize4 = getCornerSize(obtainStyledAttributes, R.styleable.NeumorphShapeAppearance_neumorph_cornerSizeBottomLeft, cornerSize);
                return new Builder().setAllCorners(i2).setTopLeftCornerSize(cornerSize2).setTopRightCornerSize(cornerSize3).setBottomRightCornerSize(cornerSize4).setBottomLeftCornerSize(getCornerSize(obtainStyledAttributes, R.styleable.NeumorphShapeAppearance_neumorph_cornerSizeBottomRight, cornerSize));
            } finally {
                obtainStyledAttributes.recycle();
            }
        }

        private final float getCornerSize(TypedArray typedArray, int i, float f) {
            TypedValue peekValue = typedArray.peekValue(i);
            if (peekValue == null || peekValue.type != 5) {
                return f;
            }
            int i2 = peekValue.data;
            Resources resources = typedArray.getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
            return (float) TypedValue.complexToDimensionPixelSize(i2, resources.getDisplayMetrics());
        }
    }
}
