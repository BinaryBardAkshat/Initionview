package soup.neumorphism.internal.blur;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J;\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u001c"}, d2 = {"Lsoup/neumorphism/internal/blur/BlurFactor;", "", "width", "", "height", "radius", "sampling", "color", "(IIIII)V", "getColor", "()I", "getHeight", "getRadius", "getSampling", "getWidth", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "", "Companion", "neumorphism_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: BlurFactor.kt */
public final class BlurFactor {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int DEFAULT_SAMPLING = 1;
    public static final int MAX_RADIUS = 25;
    private final int color;
    private final int height;
    private final int radius;
    private final int sampling;
    private final int width;

    public static /* synthetic */ BlurFactor copy$default(BlurFactor blurFactor, int i, int i2, int i3, int i4, int i5, int i6, Object obj) {
        if ((i6 & 1) != 0) {
            i = blurFactor.width;
        }
        if ((i6 & 2) != 0) {
            i2 = blurFactor.height;
        }
        int i7 = i2;
        if ((i6 & 4) != 0) {
            i3 = blurFactor.radius;
        }
        int i8 = i3;
        if ((i6 & 8) != 0) {
            i4 = blurFactor.sampling;
        }
        int i9 = i4;
        if ((i6 & 16) != 0) {
            i5 = blurFactor.color;
        }
        return blurFactor.copy(i, i7, i8, i9, i5);
    }

    public final int component1() {
        return this.width;
    }

    public final int component2() {
        return this.height;
    }

    public final int component3() {
        return this.radius;
    }

    public final int component4() {
        return this.sampling;
    }

    public final int component5() {
        return this.color;
    }

    public final BlurFactor copy(int i, int i2, int i3, int i4, int i5) {
        return new BlurFactor(i, i2, i3, i4, i5);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BlurFactor)) {
            return false;
        }
        BlurFactor blurFactor = (BlurFactor) obj;
        return this.width == blurFactor.width && this.height == blurFactor.height && this.radius == blurFactor.radius && this.sampling == blurFactor.sampling && this.color == blurFactor.color;
    }

    public int hashCode() {
        return (((((((this.width * 31) + this.height) * 31) + this.radius) * 31) + this.sampling) * 31) + this.color;
    }

    public String toString() {
        return "BlurFactor(width=" + this.width + ", height=" + this.height + ", radius=" + this.radius + ", sampling=" + this.sampling + ", color=" + this.color + ")";
    }

    public BlurFactor(int i, int i2, int i3, int i4, int i5) {
        this.width = i;
        this.height = i2;
        this.radius = i3;
        this.sampling = i4;
        this.color = i5;
    }

    public final int getWidth() {
        return this.width;
    }

    public final int getHeight() {
        return this.height;
    }

    public final int getRadius() {
        return this.radius;
    }

    public final int getSampling() {
        return this.sampling;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ BlurFactor(int i, int i2, int i3, int i4, int i5, int i6, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, (i6 & 4) != 0 ? 25 : i3, (i6 & 8) != 0 ? 1 : i4, (i6 & 16) != 0 ? 0 : i5);
    }

    public final int getColor() {
        return this.color;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lsoup/neumorphism/internal/blur/BlurFactor$Companion;", "", "()V", "DEFAULT_SAMPLING", "", "MAX_RADIUS", "neumorphism_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: BlurFactor.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
