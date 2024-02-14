package soup.neumorphism.internal.shape;

import android.graphics.Bitmap;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import soup.neumorphism.internal.blur.BlurProvider;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"blurred", "Landroid/graphics/Bitmap;", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: PressedShape.kt */
final class PressedShape$generateShadowBitmap$1 extends Lambda implements Function1<Bitmap, Bitmap> {
    final /* synthetic */ PressedShape this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PressedShape$generateShadowBitmap$1(PressedShape pressedShape) {
        super(1);
        this.this$0 = pressedShape;
    }

    public final Bitmap invoke(Bitmap bitmap) {
        Intrinsics.checkParameterIsNotNull(bitmap, "$this$blurred");
        if (this.this$0.drawableState.getInEditMode()) {
            return bitmap;
        }
        return BlurProvider.blur$default(this.this$0.drawableState.getBlurProvider(), bitmap, 0, 0, 6, (Object) null);
    }
}
