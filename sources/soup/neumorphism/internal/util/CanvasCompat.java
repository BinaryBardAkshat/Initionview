package soup.neumorphism.internal.util;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Region;
import android.os.Build;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lsoup/neumorphism/internal/util/CanvasCompat;", "", "()V", "clipOutPath", "", "canvas", "Landroid/graphics/Canvas;", "path", "Landroid/graphics/Path;", "neumorphism_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: CanvasCompat.kt */
public final class CanvasCompat {
    public static final CanvasCompat INSTANCE = new CanvasCompat();

    private CanvasCompat() {
    }

    public final boolean clipOutPath(Canvas canvas, Path path) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        Intrinsics.checkParameterIsNotNull(path, "path");
        if (Build.VERSION.SDK_INT >= 26) {
            return canvas.clipOutPath(path);
        }
        return canvas.clipPath(path, Region.Op.DIFFERENCE);
    }
}
