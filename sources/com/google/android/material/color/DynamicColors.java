package com.google.android.material.color;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import com.google.android.material.R;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DynamicColors {
    private static final Precondition ALWAYS_ALLOW = new Precondition() {
        public boolean shouldApplyDynamicColors(Activity activity, int i) {
            return true;
        }
    };
    private static final DeviceSupportCondition DEFAULT_DEVICE_SUPPORT_CONDITION;
    private static final Map<String, DeviceSupportCondition> DYNAMIC_COLOR_SUPPORTED_BRANDS;
    private static final Map<String, DeviceSupportCondition> DYNAMIC_COLOR_SUPPORTED_MANUFACTURERS;
    private static final int[] DYNAMIC_COLOR_THEME_OVERLAY_ATTRIBUTE = {R.attr.dynamicColorThemeOverlay};
    private static final DeviceSupportCondition SAMSUNG_DEVICE_SUPPORT_CONDITION;
    private static final int USE_DEFAULT_THEME_OVERLAY = 0;

    private interface DeviceSupportCondition {
        boolean isSupported();
    }

    public interface Precondition {
        boolean shouldApplyDynamicColors(Activity activity, int i);
    }

    static {
        AnonymousClass1 r0 = new DeviceSupportCondition() {
            public boolean isSupported() {
                return true;
            }
        };
        DEFAULT_DEVICE_SUPPORT_CONDITION = r0;
        AnonymousClass2 r1 = new DeviceSupportCondition() {
            private Long version;

            public boolean isSupported() {
                if (this.version == null) {
                    try {
                        Method declaredMethod = Build.class.getDeclaredMethod("getLong", new Class[]{String.class});
                        declaredMethod.setAccessible(true);
                        this.version = Long.valueOf(((Long) declaredMethod.invoke((Object) null, new Object[]{"ro.build.version.oneui"})).longValue());
                    } catch (Exception unused) {
                        this.version = -1L;
                    }
                }
                if (this.version.longValue() >= 40100) {
                    return true;
                }
                return false;
            }
        };
        SAMSUNG_DEVICE_SUPPORT_CONDITION = r1;
        HashMap hashMap = new HashMap();
        hashMap.put("oppo", r0);
        hashMap.put("realme", r0);
        hashMap.put("oneplus", r0);
        hashMap.put("vivo", r0);
        hashMap.put("xiaomi", r0);
        hashMap.put("motorola", r0);
        hashMap.put("itel", r0);
        hashMap.put("tecno mobile limited", r0);
        hashMap.put("infinix mobility limited", r0);
        hashMap.put("hmd global", r0);
        hashMap.put("sharp", r0);
        hashMap.put("sony", r0);
        hashMap.put("tcl", r0);
        hashMap.put("lenovo", r0);
        hashMap.put("lge", r0);
        hashMap.put("google", r0);
        hashMap.put("robolectric", r0);
        hashMap.put("samsung", r1);
        DYNAMIC_COLOR_SUPPORTED_MANUFACTURERS = Collections.unmodifiableMap(hashMap);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("asus", r0);
        hashMap2.put("jio", r0);
        DYNAMIC_COLOR_SUPPORTED_BRANDS = Collections.unmodifiableMap(hashMap2);
    }

    private DynamicColors() {
    }

    public static void applyToActivitiesIfAvailable(Application application) {
        applyToActivitiesIfAvailable(application, 0);
    }

    public static void applyToActivitiesIfAvailable(Application application, int i) {
        applyToActivitiesIfAvailable(application, i, ALWAYS_ALLOW);
    }

    public static void applyToActivitiesIfAvailable(Application application, Precondition precondition) {
        applyToActivitiesIfAvailable(application, 0, precondition);
    }

    public static void applyToActivitiesIfAvailable(Application application, int i, Precondition precondition) {
        application.registerActivityLifecycleCallbacks(new DynamicColorsActivityLifecycleCallbacks(i, precondition));
    }

    public static void applyIfAvailable(Activity activity) {
        applyIfAvailable(activity, 0);
    }

    public static void applyIfAvailable(Activity activity, int i) {
        applyIfAvailable(activity, i, ALWAYS_ALLOW);
    }

    public static void applyIfAvailable(Activity activity, Precondition precondition) {
        applyIfAvailable(activity, 0, precondition);
    }

    /* access modifiers changed from: private */
    public static void applyIfAvailable(Activity activity, int i, Precondition precondition) {
        if (isDynamicColorAvailable()) {
            if (i == 0) {
                i = getDefaultThemeOverlay(activity);
            }
            if (i != 0 && precondition.shouldApplyDynamicColors(activity, i)) {
                activity.setTheme(i);
            }
        }
    }

    public static Context wrapContextIfAvailable(Context context) {
        return wrapContextIfAvailable(context, 0);
    }

    public static Context wrapContextIfAvailable(Context context, int i) {
        if (!isDynamicColorAvailable()) {
            return context;
        }
        if (i == 0) {
            i = getDefaultThemeOverlay(context);
        }
        return i == 0 ? context : new ContextThemeWrapper(context, i);
    }

    public static boolean isDynamicColorAvailable() {
        if (Build.VERSION.SDK_INT < 31) {
            return false;
        }
        DeviceSupportCondition deviceSupportCondition = DYNAMIC_COLOR_SUPPORTED_MANUFACTURERS.get(Build.MANUFACTURER.toLowerCase());
        if (deviceSupportCondition == null) {
            deviceSupportCondition = DYNAMIC_COLOR_SUPPORTED_BRANDS.get(Build.BRAND.toLowerCase());
        }
        if (deviceSupportCondition == null || !deviceSupportCondition.isSupported()) {
            return false;
        }
        return true;
    }

    private static int getDefaultThemeOverlay(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(DYNAMIC_COLOR_THEME_OVERLAY_ATTRIBUTE);
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        return resourceId;
    }

    private static class DynamicColorsActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
        private final int dynamicColorThemeOverlay;
        private final Precondition precondition;

        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public void onActivityDestroyed(Activity activity) {
        }

        public void onActivityPaused(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
        }

        DynamicColorsActivityLifecycleCallbacks(int i, Precondition precondition2) {
            this.dynamicColorThemeOverlay = i;
            this.precondition = precondition2;
        }

        public void onActivityPreCreated(Activity activity, Bundle bundle) {
            DynamicColors.applyIfAvailable(activity, this.dynamicColorThemeOverlay, this.precondition);
        }
    }
}
