package soup.neumorphism.internal.shape;

import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import soup.neumorphism.NeumorphShapeAppearanceModel;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"setCornerShape", "", "Landroid/graphics/drawable/GradientDrawable;", "shapeAppearanceModel", "Lsoup/neumorphism/NeumorphShapeAppearanceModel;", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: FlatShape.kt */
final class FlatShape$updateShadowBitmap$1 extends Lambda implements Function2<GradientDrawable, NeumorphShapeAppearanceModel, Unit> {
    final /* synthetic */ Rect $bounds;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FlatShape$updateShadowBitmap$1(Rect rect) {
        super(2);
        this.$bounds = rect;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((GradientDrawable) obj, (NeumorphShapeAppearanceModel) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(GradientDrawable gradientDrawable, NeumorphShapeAppearanceModel neumorphShapeAppearanceModel) {
        Intrinsics.checkParameterIsNotNull(gradientDrawable, "$this$setCornerShape");
        Intrinsics.checkParameterIsNotNull(neumorphShapeAppearanceModel, "shapeAppearanceModel");
        int cornerFamily = neumorphShapeAppearanceModel.getCornerFamily();
        if (cornerFamily == 0) {
            gradientDrawable.setShape(0);
            gradientDrawable.setCornerRadii(neumorphShapeAppearanceModel.getCornerRadii$neumorphism_release(Math.min(((float) this.$bounds.width()) / 2.0f, ((float) this.$bounds.height()) / 2.0f)));
        } else if (cornerFamily == 1) {
            gradientDrawable.setShape(1);
        }
    }
}
