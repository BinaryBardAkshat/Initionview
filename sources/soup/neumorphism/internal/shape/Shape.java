package soup.neumorphism.internal.shape;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import kotlin.Metadata;
import soup.neumorphism.NeumorphShapeDrawable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH&¨\u0006\u000e"}, d2 = {"Lsoup/neumorphism/internal/shape/Shape;", "", "draw", "", "canvas", "Landroid/graphics/Canvas;", "outlinePath", "Landroid/graphics/Path;", "setDrawableState", "newDrawableState", "Lsoup/neumorphism/NeumorphShapeDrawable$NeumorphShapeDrawableState;", "updateShadowBitmap", "bounds", "Landroid/graphics/Rect;", "neumorphism_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: Shape.kt */
public interface Shape {
    void draw(Canvas canvas, Path path);

    void setDrawableState(NeumorphShapeDrawable.NeumorphShapeDrawableState neumorphShapeDrawableState);

    void updateShadowBitmap(Rect rect);
}
