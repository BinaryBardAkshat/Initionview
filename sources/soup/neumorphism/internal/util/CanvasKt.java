package soup.neumorphism.internal.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\u001a&\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\b\u001a.\u0010\u0007\u001a\u00020\u0005*\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\b\u001a.\u0010\n\u001a\u00020\u0005*\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\b\u001a:\u0010\u000b\u001a\u00020\u0005*\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\b¨\u0006\u000f"}, d2 = {"onCanvas", "Landroid/graphics/Bitmap;", "block", "Lkotlin/Function1;", "Landroid/graphics/Canvas;", "", "Lkotlin/ExtensionFunctionType;", "withClip", "clipPath", "Landroid/graphics/Path;", "withClipOut", "withTranslation", "x", "", "y", "neumorphism_release"}, k = 2, mv = {1, 1, 16})
/* compiled from: Canvas.kt */
public final class CanvasKt {
    public static final Bitmap onCanvas(Bitmap bitmap, Function1<? super Canvas, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(bitmap, "$this$onCanvas");
        Intrinsics.checkParameterIsNotNull(function1, "block");
        function1.invoke(new Canvas(bitmap));
        return bitmap;
    }

    public static /* synthetic */ void withTranslation$default(Canvas canvas, float f, float f2, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            f = 0.0f;
        }
        if ((i & 2) != 0) {
            f2 = 0.0f;
        }
        Intrinsics.checkParameterIsNotNull(canvas, "$this$withTranslation");
        Intrinsics.checkParameterIsNotNull(function1, "block");
        int save = canvas.save();
        canvas.translate(f, f2);
        try {
            function1.invoke(canvas);
        } finally {
            InlineMarker.finallyStart(1);
            canvas.restoreToCount(save);
            InlineMarker.finallyEnd(1);
        }
    }

    public static final void withTranslation(Canvas canvas, float f, float f2, Function1<? super Canvas, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(canvas, "$this$withTranslation");
        Intrinsics.checkParameterIsNotNull(function1, "block");
        int save = canvas.save();
        canvas.translate(f, f2);
        try {
            function1.invoke(canvas);
        } finally {
            InlineMarker.finallyStart(1);
            canvas.restoreToCount(save);
            InlineMarker.finallyEnd(1);
        }
    }

    public static final void withClip(Canvas canvas, Path path, Function1<? super Canvas, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(canvas, "$this$withClip");
        Intrinsics.checkParameterIsNotNull(path, "clipPath");
        Intrinsics.checkParameterIsNotNull(function1, "block");
        int save = canvas.save();
        canvas.clipPath(path);
        try {
            function1.invoke(canvas);
        } finally {
            InlineMarker.finallyStart(1);
            canvas.restoreToCount(save);
            InlineMarker.finallyEnd(1);
        }
    }

    public static final void withClipOut(Canvas canvas, Path path, Function1<? super Canvas, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(canvas, "$this$withClipOut");
        Intrinsics.checkParameterIsNotNull(path, "clipPath");
        Intrinsics.checkParameterIsNotNull(function1, "block");
        int save = canvas.save();
        CanvasCompat.INSTANCE.clipOutPath(canvas, path);
        try {
            function1.invoke(canvas);
        } finally {
            InlineMarker.finallyStart(1);
            canvas.restoreToCount(save);
            InlineMarker.finallyEnd(1);
        }
    }
}
