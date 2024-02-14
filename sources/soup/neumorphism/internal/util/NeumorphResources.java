package soup.neumorphism.internal.util;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.core.content.ContextCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J*\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\t\u001a\u00020\u00042\b\b\u0001\u0010\n\u001a\u00020\u0004¨\u0006\u000b"}, d2 = {"Lsoup/neumorphism/internal/util/NeumorphResources;", "", "()V", "getColor", "", "context", "Landroid/content/Context;", "attributes", "Landroid/content/res/TypedArray;", "index", "defaultColor", "neumorphism_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: NeumorphResources.kt */
public final class NeumorphResources {
    public static final NeumorphResources INSTANCE = new NeumorphResources();

    private NeumorphResources() {
    }

    public final int getColor(Context context, TypedArray typedArray, int i, int i2) {
        int resourceId;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(typedArray, "attributes");
        try {
            if (!typedArray.hasValue(i) || (resourceId = typedArray.getResourceId(i, 0)) == 0) {
                return typedArray.getColor(i, ContextCompat.getColor(context, i2));
            }
            return ContextCompat.getColor(context, resourceId);
        } catch (Exception unused) {
            return ContextCompat.getColor(context, i2);
        }
    }
}
