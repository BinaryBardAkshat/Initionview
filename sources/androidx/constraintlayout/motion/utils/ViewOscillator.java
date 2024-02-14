package androidx.constraintlayout.motion.utils;

import android.os.Build;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.KeyCycleOscillator;
import androidx.constraintlayout.motion.widget.Key;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintAttribute;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class ViewOscillator extends KeyCycleOscillator {
    private static final String TAG = "ViewOscillator";

    public abstract void setProperty(View view, float f);

    public static ViewOscillator makeSpline(String str) {
        if (str.startsWith("CUSTOM")) {
            return new CustomSet();
        }
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1249320806:
                if (str.equals("rotationX")) {
                    c = 0;
                    break;
                }
                break;
            case -1249320805:
                if (str.equals("rotationY")) {
                    c = 1;
                    break;
                }
                break;
            case -1225497657:
                if (str.equals("translationX")) {
                    c = 2;
                    break;
                }
                break;
            case -1225497656:
                if (str.equals("translationY")) {
                    c = 3;
                    break;
                }
                break;
            case -1225497655:
                if (str.equals("translationZ")) {
                    c = 4;
                    break;
                }
                break;
            case -1001078227:
                if (str.equals("progress")) {
                    c = 5;
                    break;
                }
                break;
            case -908189618:
                if (str.equals("scaleX")) {
                    c = 6;
                    break;
                }
                break;
            case -908189617:
                if (str.equals("scaleY")) {
                    c = 7;
                    break;
                }
                break;
            case -797520672:
                if (str.equals(Key.WAVE_VARIES_BY)) {
                    c = 8;
                    break;
                }
                break;
            case -40300674:
                if (str.equals(Key.ROTATION)) {
                    c = 9;
                    break;
                }
                break;
            case -4379043:
                if (str.equals("elevation")) {
                    c = 10;
                    break;
                }
                break;
            case 37232917:
                if (str.equals("transitionPathRotate")) {
                    c = 11;
                    break;
                }
                break;
            case 92909918:
                if (str.equals("alpha")) {
                    c = 12;
                    break;
                }
                break;
            case 156108012:
                if (str.equals("waveOffset")) {
                    c = 13;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return new RotationXset();
            case 1:
                return new RotationYset();
            case 2:
                return new TranslationXset();
            case 3:
                return new TranslationYset();
            case 4:
                return new TranslationZset();
            case 5:
                return new ProgressSet();
            case 6:
                return new ScaleXset();
            case 7:
                return new ScaleYset();
            case 8:
                return new AlphaSet();
            case 9:
                return new RotationSet();
            case 10:
                return new ElevationSet();
            case 11:
                return new PathRotateSet();
            case 12:
                return new AlphaSet();
            case 13:
                return new AlphaSet();
            default:
                return null;
        }
    }

    static class ElevationSet extends ViewOscillator {
        ElevationSet() {
        }

        public void setProperty(View view, float f) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setElevation(get(f));
            }
        }
    }

    static class AlphaSet extends ViewOscillator {
        AlphaSet() {
        }

        public void setProperty(View view, float f) {
            view.setAlpha(get(f));
        }
    }

    static class RotationSet extends ViewOscillator {
        RotationSet() {
        }

        public void setProperty(View view, float f) {
            view.setRotation(get(f));
        }
    }

    static class RotationXset extends ViewOscillator {
        RotationXset() {
        }

        public void setProperty(View view, float f) {
            view.setRotationX(get(f));
        }
    }

    static class RotationYset extends ViewOscillator {
        RotationYset() {
        }

        public void setProperty(View view, float f) {
            view.setRotationY(get(f));
        }
    }

    public static class PathRotateSet extends ViewOscillator {
        public void setProperty(View view, float f) {
        }

        public void setPathRotate(View view, float f, double d, double d2) {
            view.setRotation(get(f) + ((float) Math.toDegrees(Math.atan2(d2, d))));
        }
    }

    static class ScaleXset extends ViewOscillator {
        ScaleXset() {
        }

        public void setProperty(View view, float f) {
            view.setScaleX(get(f));
        }
    }

    static class ScaleYset extends ViewOscillator {
        ScaleYset() {
        }

        public void setProperty(View view, float f) {
            view.setScaleY(get(f));
        }
    }

    static class TranslationXset extends ViewOscillator {
        TranslationXset() {
        }

        public void setProperty(View view, float f) {
            view.setTranslationX(get(f));
        }
    }

    static class TranslationYset extends ViewOscillator {
        TranslationYset() {
        }

        public void setProperty(View view, float f) {
            view.setTranslationY(get(f));
        }
    }

    static class TranslationZset extends ViewOscillator {
        TranslationZset() {
        }

        public void setProperty(View view, float f) {
            if (Build.VERSION.SDK_INT >= 21) {
                view.setTranslationZ(get(f));
            }
        }
    }

    static class CustomSet extends ViewOscillator {
        protected ConstraintAttribute mCustom;
        float[] value = new float[1];

        CustomSet() {
        }

        /* access modifiers changed from: protected */
        public void setCustom(Object obj) {
            this.mCustom = (ConstraintAttribute) obj;
        }

        public void setProperty(View view, float f) {
            this.value[0] = get(f);
            CustomSupport.setInterpolatedValue(this.mCustom, view, this.value);
        }
    }

    static class ProgressSet extends ViewOscillator {
        boolean mNoMethod = false;

        ProgressSet() {
        }

        public void setProperty(View view, float f) {
            if (view instanceof MotionLayout) {
                ((MotionLayout) view).setProgress(get(f));
            } else if (!this.mNoMethod) {
                Method method = null;
                try {
                    method = view.getClass().getMethod("setProgress", new Class[]{Float.TYPE});
                } catch (NoSuchMethodException unused) {
                    this.mNoMethod = true;
                }
                if (method != null) {
                    try {
                        method.invoke(view, new Object[]{Float.valueOf(get(f))});
                    } catch (IllegalAccessException e) {
                        Log.e(ViewOscillator.TAG, "unable to setProgress", e);
                    } catch (InvocationTargetException e2) {
                        Log.e(ViewOscillator.TAG, "unable to setProgress", e2);
                    }
                }
            }
        }
    }
}
