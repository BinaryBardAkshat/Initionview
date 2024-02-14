package soup.neumorphism.internal.shape;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import soup.neumorphism.NeumorphShapeDrawable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u0003H\u0016J\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lsoup/neumorphism/internal/shape/BasinShape;", "Lsoup/neumorphism/internal/shape/Shape;", "drawableState", "Lsoup/neumorphism/NeumorphShapeDrawable$NeumorphShapeDrawableState;", "(Lsoup/neumorphism/NeumorphShapeDrawable$NeumorphShapeDrawableState;)V", "shadows", "", "draw", "", "canvas", "Landroid/graphics/Canvas;", "outlinePath", "Landroid/graphics/Path;", "setDrawableState", "newDrawableState", "updateShadowBitmap", "bounds", "Landroid/graphics/Rect;", "neumorphism_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: BasinShape.kt */
public final class BasinShape implements Shape {
    private final List<Shape> shadows;

    public BasinShape(NeumorphShapeDrawable.NeumorphShapeDrawableState neumorphShapeDrawableState) {
        Intrinsics.checkParameterIsNotNull(neumorphShapeDrawableState, "drawableState");
        this.shadows = CollectionsKt.listOf(new FlatShape(neumorphShapeDrawableState), new PressedShape(neumorphShapeDrawableState));
    }

    public void setDrawableState(NeumorphShapeDrawable.NeumorphShapeDrawableState neumorphShapeDrawableState) {
        Intrinsics.checkParameterIsNotNull(neumorphShapeDrawableState, "newDrawableState");
        for (Shape drawableState : this.shadows) {
            drawableState.setDrawableState(neumorphShapeDrawableState);
        }
    }

    public void draw(Canvas canvas, Path path) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        Intrinsics.checkParameterIsNotNull(path, "outlinePath");
        for (Shape draw : this.shadows) {
            draw.draw(canvas, path);
        }
    }

    public void updateShadowBitmap(Rect rect) {
        Intrinsics.checkParameterIsNotNull(rect, "bounds");
        for (Shape updateShadowBitmap : this.shadows) {
            updateShadowBitmap.updateShadowBitmap(rect);
        }
    }
}
