package soup.neumorphism.internal.blur;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Build;
import android.renderscript.RSRuntimeException;
import android.util.DisplayMetrics;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J$\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\tJ\u001a\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u001a\u0010\u0011\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\tH\u0002J\"\u0010\u0013\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002R\u001c\u0010\u0005\u001a\u0010\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00030\u00030\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lsoup/neumorphism/internal/blur/BlurProvider;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "contextRef", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "defaultBlurRadius", "", "blur", "Landroid/graphics/Bitmap;", "source", "radius", "sampling", "factor", "Lsoup/neumorphism/internal/blur/BlurFactor;", "rs", "bitmap", "stack", "sentBitmap", "canReuseInBitmap", "", "neumorphism_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: BlurProvider.kt */
public final class BlurProvider {
    private final WeakReference<Context> contextRef;
    private final int defaultBlurRadius;

    public BlurProvider(Context context) {
        float f;
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.contextRef = new WeakReference<>(context);
        if (Build.VERSION.SDK_INT >= 24) {
            f = ((float) DisplayMetrics.DENSITY_DEVICE_STABLE) / ((float) 160);
        } else {
            Resources resources = context.getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources, "context.resources");
            f = resources.getDisplayMetrics().density;
        }
        this.defaultBlurRadius = Math.min(25, MathKt.roundToInt(f * ((float) 10)));
    }

    public static /* synthetic */ Bitmap blur$default(BlurProvider blurProvider, Bitmap bitmap, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = blurProvider.defaultBlurRadius;
        }
        if ((i3 & 4) != 0) {
            i2 = 1;
        }
        return blurProvider.blur(bitmap, i, i2);
    }

    public final Bitmap blur(Bitmap bitmap, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(bitmap, "source");
        return blur(bitmap, new BlurFactor(bitmap.getWidth(), bitmap.getHeight(), i, i2, 0, 16, (DefaultConstructorMarker) null));
    }

    private final Bitmap blur(Bitmap bitmap, BlurFactor blurFactor) {
        Bitmap bitmap2;
        int width = blurFactor.getWidth() / blurFactor.getSampling();
        int height = blurFactor.getHeight() / blurFactor.getSampling();
        if (width == 0 || height == 0) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Intrinsics.checkExpressionValueIsNotNull(createBitmap, "Bitmap.createBitmap(widt… Bitmap.Config.ARGB_8888)");
        Canvas canvas = new Canvas(createBitmap);
        float f = (float) 1;
        canvas.scale(f / ((float) blurFactor.getSampling()), f / ((float) blurFactor.getSampling()));
        Paint paint = new Paint();
        paint.setFlags(3);
        paint.setColorFilter(new PorterDuffColorFilter(blurFactor.getColor(), PorterDuff.Mode.SRC_ATOP));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        try {
            bitmap2 = rs(createBitmap, blurFactor.getRadius());
        } catch (RSRuntimeException unused) {
            bitmap2 = stack(createBitmap, blurFactor.getRadius(), true);
        }
        if (bitmap2 == null) {
            return null;
        }
        if (blurFactor.getSampling() == 1) {
            return bitmap2;
        }
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap2, blurFactor.getWidth(), blurFactor.getHeight(), true);
        bitmap2.recycle();
        return createScaledBitmap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x007a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final android.graphics.Bitmap rs(android.graphics.Bitmap r6, int r7) throws android.renderscript.RSRuntimeException {
        /*
            r5 = this;
            java.lang.ref.WeakReference<android.content.Context> r0 = r5.contextRef
            java.lang.Object r0 = r0.get()
            android.content.Context r0 = (android.content.Context) r0
            r1 = 0
            if (r0 == 0) goto L_0x007e
            java.lang.String r2 = "contextRef.get() ?: return null"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r2)
            r2 = r1
            android.renderscript.RenderScript r2 = (android.renderscript.RenderScript) r2
            r3 = r1
            android.renderscript.Allocation r3 = (android.renderscript.Allocation) r3
            android.renderscript.ScriptIntrinsicBlur r1 = (android.renderscript.ScriptIntrinsicBlur) r1
            android.renderscript.RenderScript r2 = android.renderscript.RenderScript.create(r0)     // Catch:{ all -> 0x0067 }
            java.lang.String r0 = "rs"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r0)     // Catch:{ all -> 0x0067 }
            android.renderscript.RenderScript$RSMessageHandler r0 = new android.renderscript.RenderScript$RSMessageHandler     // Catch:{ all -> 0x0067 }
            r0.<init>()     // Catch:{ all -> 0x0067 }
            r2.setMessageHandler(r0)     // Catch:{ all -> 0x0067 }
            android.renderscript.Allocation$MipmapControl r0 = android.renderscript.Allocation.MipmapControl.MIPMAP_NONE     // Catch:{ all -> 0x0067 }
            r4 = 1
            android.renderscript.Allocation r0 = android.renderscript.Allocation.createFromBitmap(r2, r6, r0, r4)     // Catch:{ all -> 0x0067 }
            java.lang.String r4 = "input"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r4)     // Catch:{ all -> 0x0063 }
            android.renderscript.Type r4 = r0.getType()     // Catch:{ all -> 0x0063 }
            android.renderscript.Allocation r3 = android.renderscript.Allocation.createTyped(r2, r4)     // Catch:{ all -> 0x0063 }
            android.renderscript.Element r4 = android.renderscript.Element.U8_4(r2)     // Catch:{ all -> 0x0063 }
            android.renderscript.ScriptIntrinsicBlur r1 = android.renderscript.ScriptIntrinsicBlur.create(r2, r4)     // Catch:{ all -> 0x0063 }
            r1.setInput(r0)     // Catch:{ all -> 0x0063 }
            float r7 = (float) r7     // Catch:{ all -> 0x0063 }
            r1.setRadius(r7)     // Catch:{ all -> 0x0063 }
            r1.forEach(r3)     // Catch:{ all -> 0x0063 }
            r3.copyTo(r6)     // Catch:{ all -> 0x0063 }
            r2.destroy()
            r0.destroy()
            if (r3 == 0) goto L_0x005d
            r3.destroy()
        L_0x005d:
            if (r1 == 0) goto L_0x0062
            r1.destroy()
        L_0x0062:
            return r6
        L_0x0063:
            r6 = move-exception
            r7 = r3
            r3 = r0
            goto L_0x0069
        L_0x0067:
            r6 = move-exception
            r7 = r3
        L_0x0069:
            if (r2 == 0) goto L_0x006e
            r2.destroy()
        L_0x006e:
            if (r3 == 0) goto L_0x0073
            r3.destroy()
        L_0x0073:
            if (r7 == 0) goto L_0x0078
            r7.destroy()
        L_0x0078:
            if (r1 == 0) goto L_0x007d
            r1.destroy()
        L_0x007d:
            throw r6
        L_0x007e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: soup.neumorphism.internal.blur.BlurProvider.rs(android.graphics.Bitmap, int):android.graphics.Bitmap");
    }

    private final Bitmap stack(Bitmap bitmap, int i, boolean z) {
        Bitmap bitmap2;
        int[] iArr;
        int i2 = i;
        if (i2 < 1) {
            return null;
        }
        if (z) {
            bitmap2 = bitmap;
        } else {
            bitmap2 = bitmap.copy(bitmap.getConfig(), true);
            Intrinsics.checkExpressionValueIsNotNull(bitmap2, "sentBitmap.copy(sentBitmap.config, true)");
        }
        int width = bitmap2.getWidth();
        int height = bitmap2.getHeight();
        int i3 = width * height;
        int[] iArr2 = new int[i3];
        bitmap2.getPixels(iArr2, 0, width, 0, 0, width, height);
        int i4 = width - 1;
        int i5 = height - 1;
        int i6 = i2 + i2 + 1;
        int[] iArr3 = new int[i3];
        int[] iArr4 = new int[i3];
        int[] iArr5 = new int[i3];
        int[] iArr6 = new int[Math.max(width, height)];
        int i7 = (i6 + 1) >> 1;
        int i8 = i7 * i7;
        int i9 = i8 * 256;
        int[] iArr7 = new int[i9];
        for (int i10 = 0; i10 < i9; i10++) {
            iArr7[i10] = i10 / i8;
        }
        int[][] iArr8 = new int[i6][];
        for (int i11 = 0; i11 < i6; i11++) {
            iArr8[i11] = new int[3];
        }
        int[][] iArr9 = iArr8;
        int i12 = i2 + 1;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        while (i13 < height) {
            Bitmap bitmap3 = bitmap2;
            int i16 = height;
            int i17 = 0;
            int i18 = 0;
            int i19 = 0;
            int i20 = 0;
            int i21 = 0;
            int i22 = 0;
            int i23 = 0;
            int i24 = 0;
            int i25 = -i2;
            int i26 = 0;
            while (i25 <= i2) {
                int i27 = i5;
                int[] iArr10 = iArr6;
                int i28 = iArr2[i14 + Math.min(i4, Math.max(i25, 0))];
                int[] iArr11 = iArr9[i25 + i2];
                iArr11[0] = (i28 & 16711680) >> 16;
                iArr11[1] = (i28 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                iArr11[2] = i28 & 255;
                int abs = i12 - Math.abs(i25);
                i26 += iArr11[0] * abs;
                i17 += iArr11[1] * abs;
                i18 += iArr11[2] * abs;
                if (i25 > 0) {
                    i22 += iArr11[0];
                    i23 += iArr11[1];
                    i24 += iArr11[2];
                } else {
                    i19 += iArr11[0];
                    i20 += iArr11[1];
                    i21 += iArr11[2];
                }
                i25++;
                i5 = i27;
                iArr6 = iArr10;
            }
            int i29 = i5;
            int[] iArr12 = iArr6;
            int i30 = i2;
            int i31 = i26;
            int i32 = 0;
            while (i32 < width) {
                iArr3[i14] = iArr7[i31];
                iArr4[i14] = iArr7[i17];
                iArr5[i14] = iArr7[i18];
                int i33 = i31 - i19;
                int i34 = i17 - i20;
                int i35 = i18 - i21;
                int[] iArr13 = iArr9[((i30 - i2) + i6) % i6];
                int i36 = i19 - iArr13[0];
                int i37 = i20 - iArr13[1];
                int i38 = i21 - iArr13[2];
                if (i13 == 0) {
                    iArr = iArr7;
                    iArr12[i32] = Math.min(i32 + i2 + 1, i4);
                } else {
                    iArr = iArr7;
                }
                int i39 = iArr2[i15 + iArr12[i32]];
                iArr13[0] = (i39 & 16711680) >> 16;
                iArr13[1] = (i39 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                iArr13[2] = i39 & 255;
                int i40 = i22 + iArr13[0];
                int i41 = i23 + iArr13[1];
                int i42 = i24 + iArr13[2];
                i31 = i33 + i40;
                i17 = i34 + i41;
                i18 = i35 + i42;
                i30 = (i30 + 1) % i6;
                int[] iArr14 = iArr9[i30 % i6];
                i19 = i36 + iArr14[0];
                i20 = i37 + iArr14[1];
                i21 = i38 + iArr14[2];
                i22 = i40 - iArr14[0];
                i23 = i41 - iArr14[1];
                i24 = i42 - iArr14[2];
                i14++;
                i32++;
                iArr7 = iArr;
            }
            int[] iArr15 = iArr7;
            i15 += width;
            i13++;
            bitmap2 = bitmap3;
            height = i16;
            i5 = i29;
            iArr6 = iArr12;
        }
        Bitmap bitmap4 = bitmap2;
        int i43 = i5;
        int[] iArr16 = iArr6;
        int i44 = height;
        int[] iArr17 = iArr7;
        int i45 = 0;
        while (i45 < width) {
            int i46 = -i2;
            int i47 = i6;
            int[] iArr18 = iArr2;
            int i48 = 0;
            int i49 = 0;
            int i50 = 0;
            int i51 = 0;
            int i52 = 0;
            int i53 = 0;
            int i54 = 0;
            int i55 = i46;
            int i56 = i46 * width;
            int i57 = 0;
            int i58 = 0;
            while (i55 <= i2) {
                int i59 = width;
                int max = Math.max(0, i56) + i45;
                int[] iArr19 = iArr9[i55 + i2];
                iArr19[0] = iArr3[max];
                iArr19[1] = iArr4[max];
                iArr19[2] = iArr5[max];
                int abs2 = i12 - Math.abs(i55);
                i57 += iArr3[max] * abs2;
                i58 += iArr4[max] * abs2;
                i48 += iArr5[max] * abs2;
                if (i55 > 0) {
                    i52 += iArr19[0];
                    i53 += iArr19[1];
                    i54 += iArr19[2];
                } else {
                    i49 += iArr19[0];
                    i50 += iArr19[1];
                    i51 += iArr19[2];
                }
                int i60 = i43;
                if (i55 < i60) {
                    i56 += i59;
                }
                i55++;
                i43 = i60;
                width = i59;
            }
            int i61 = width;
            int i62 = i43;
            int i63 = i2;
            int i64 = i45;
            int i65 = i44;
            int i66 = 0;
            while (i66 < i65) {
                iArr18[i64] = (iArr18[i64] & ViewCompat.MEASURED_STATE_MASK) | (iArr17[i57] << 16) | (iArr17[i58] << 8) | iArr17[i48];
                int i67 = i57 - i49;
                int i68 = i58 - i50;
                int i69 = i48 - i51;
                int[] iArr20 = iArr9[((i63 - i2) + i47) % i47];
                int i70 = i49 - iArr20[0];
                int i71 = i50 - iArr20[1];
                int i72 = i51 - iArr20[2];
                if (i45 == 0) {
                    iArr16[i66] = Math.min(i66 + i12, i62) * i61;
                }
                int i73 = iArr16[i66] + i45;
                iArr20[0] = iArr3[i73];
                iArr20[1] = iArr4[i73];
                iArr20[2] = iArr5[i73];
                int i74 = i52 + iArr20[0];
                int i75 = i53 + iArr20[1];
                int i76 = i54 + iArr20[2];
                i57 = i67 + i74;
                i58 = i68 + i75;
                i48 = i69 + i76;
                i63 = (i63 + 1) % i47;
                int[] iArr21 = iArr9[i63];
                i49 = i70 + iArr21[0];
                i50 = i71 + iArr21[1];
                i51 = i72 + iArr21[2];
                i52 = i74 - iArr21[0];
                i53 = i75 - iArr21[1];
                i54 = i76 - iArr21[2];
                i64 += i61;
                i66++;
                i2 = i;
            }
            i45++;
            i2 = i;
            i43 = i62;
            i44 = i65;
            i6 = i47;
            iArr2 = iArr18;
            width = i61;
        }
        int i77 = width;
        bitmap4.setPixels(iArr2, 0, i77, 0, 0, i77, i44);
        return bitmap4;
    }
}
