package androidx.constraintlayout.motion.widget;

import android.view.View;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintSet;
import java.util.Arrays;
import java.util.LinkedHashMap;

class MotionPaths implements Comparable<MotionPaths> {
    static final int CARTESIAN = 0;
    public static final boolean DEBUG = false;
    static final int OFF_HEIGHT = 4;
    static final int OFF_PATH_ROTATE = 5;
    static final int OFF_POSITION = 0;
    static final int OFF_WIDTH = 3;
    static final int OFF_X = 1;
    static final int OFF_Y = 2;
    public static final boolean OLD_WAY = false;
    static final int PERPENDICULAR = 1;
    static final int SCREEN = 2;
    public static final String TAG = "MotionPaths";
    static String[] names = {"position", "x", "y", "width", "height", "pathRotate"};
    LinkedHashMap<String, ConstraintAttribute> attributes = new LinkedHashMap<>();
    float height;
    int mAnimateCircleAngleTo;
    int mAnimateRelativeTo = Key.UNSET;
    int mDrawPath = 0;
    Easing mKeyFrameEasing;
    int mMode = 0;
    int mPathMotionArc = Key.UNSET;
    float mPathRotate = Float.NaN;
    float mProgress = Float.NaN;
    float mRelativeAngle = Float.NaN;
    MotionController mRelativeToController = null;
    double[] mTempDelta = new double[18];
    double[] mTempValue = new double[18];
    float position;
    float time;
    float width;
    float x;
    float y;

    private static final float xRotate(float f, float f2, float f3, float f4, float f5, float f6) {
        return (((f5 - f3) * f2) - ((f6 - f4) * f)) + f3;
    }

    private static final float yRotate(float f, float f2, float f3, float f4, float f5, float f6) {
        return ((f5 - f3) * f) + ((f6 - f4) * f2) + f4;
    }

    public MotionPaths() {
    }

    /* access modifiers changed from: package-private */
    public void initCartesian(KeyPosition keyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        KeyPosition keyPosition2 = keyPosition;
        MotionPaths motionPaths3 = motionPaths;
        MotionPaths motionPaths4 = motionPaths2;
        float f = ((float) keyPosition2.mFramePosition) / 100.0f;
        this.time = f;
        this.mDrawPath = keyPosition2.mDrawPath;
        float f2 = Float.isNaN(keyPosition2.mPercentWidth) ? f : keyPosition2.mPercentWidth;
        float f3 = Float.isNaN(keyPosition2.mPercentHeight) ? f : keyPosition2.mPercentHeight;
        float f4 = motionPaths4.width;
        float f5 = motionPaths3.width;
        float f6 = motionPaths4.height;
        float f7 = motionPaths3.height;
        this.position = this.time;
        float f8 = motionPaths3.x;
        float f9 = motionPaths3.y;
        float f10 = (motionPaths4.x + (f4 / 2.0f)) - ((f5 / 2.0f) + f8);
        float f11 = (motionPaths4.y + (f6 / 2.0f)) - (f9 + (f7 / 2.0f));
        float f12 = (f4 - f5) * f2;
        float f13 = f12 / 2.0f;
        this.x = (float) ((int) ((f8 + (f10 * f)) - f13));
        float f14 = (f6 - f7) * f3;
        float f15 = f14 / 2.0f;
        this.y = (float) ((int) ((f9 + (f11 * f)) - f15));
        this.width = (float) ((int) (f5 + f12));
        this.height = (float) ((int) (f7 + f14));
        KeyPosition keyPosition3 = keyPosition;
        float f16 = Float.isNaN(keyPosition3.mPercentX) ? f : keyPosition3.mPercentX;
        float f17 = 0.0f;
        float f18 = Float.isNaN(keyPosition3.mAltPercentY) ? 0.0f : keyPosition3.mAltPercentY;
        if (!Float.isNaN(keyPosition3.mPercentY)) {
            f = keyPosition3.mPercentY;
        }
        if (!Float.isNaN(keyPosition3.mAltPercentX)) {
            f17 = keyPosition3.mAltPercentX;
        }
        this.mMode = 0;
        MotionPaths motionPaths5 = motionPaths;
        this.x = (float) ((int) (((motionPaths5.x + (f16 * f10)) + (f17 * f11)) - f13));
        this.y = (float) ((int) (((motionPaths5.y + (f10 * f18)) + (f11 * f)) - f15));
        this.mKeyFrameEasing = Easing.getInterpolator(keyPosition3.mTransitionEasing);
        this.mPathMotionArc = keyPosition3.mPathMotionArc;
    }

    public MotionPaths(int i, int i2, KeyPosition keyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        if (motionPaths.mAnimateRelativeTo != Key.UNSET) {
            initPolar(i, i2, keyPosition, motionPaths, motionPaths2);
            return;
        }
        int i3 = keyPosition.mPositionType;
        if (i3 == 1) {
            initPath(keyPosition, motionPaths, motionPaths2);
        } else if (i3 != 2) {
            initCartesian(keyPosition, motionPaths, motionPaths2);
        } else {
            initScreen(i, i2, keyPosition, motionPaths, motionPaths2);
        }
    }

    /* access modifiers changed from: package-private */
    public void initPolar(int i, int i2, KeyPosition keyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        float f;
        float f2;
        float f3 = ((float) keyPosition.mFramePosition) / 100.0f;
        this.time = f3;
        this.mDrawPath = keyPosition.mDrawPath;
        this.mMode = keyPosition.mPositionType;
        float f4 = Float.isNaN(keyPosition.mPercentWidth) ? f3 : keyPosition.mPercentWidth;
        float f5 = Float.isNaN(keyPosition.mPercentHeight) ? f3 : keyPosition.mPercentHeight;
        float f6 = motionPaths2.width;
        float f7 = motionPaths.width;
        float f8 = motionPaths2.height;
        float f9 = motionPaths.height;
        this.position = this.time;
        this.width = (float) ((int) (f7 + ((f6 - f7) * f4)));
        this.height = (float) ((int) (f9 + ((f8 - f9) * f5)));
        int i3 = keyPosition.mPositionType;
        if (i3 == 1) {
            float f10 = Float.isNaN(keyPosition.mPercentX) ? f3 : keyPosition.mPercentX;
            float f11 = motionPaths2.x;
            float f12 = motionPaths.x;
            this.x = (f10 * (f11 - f12)) + f12;
            if (!Float.isNaN(keyPosition.mPercentY)) {
                f3 = keyPosition.mPercentY;
            }
            float f13 = motionPaths2.y;
            float f14 = motionPaths.y;
            this.y = (f3 * (f13 - f14)) + f14;
        } else if (i3 != 2) {
            float f15 = Float.isNaN(keyPosition.mPercentX) ? f3 : keyPosition.mPercentX;
            float f16 = motionPaths2.x;
            float f17 = motionPaths.x;
            this.x = (f15 * (f16 - f17)) + f17;
            if (!Float.isNaN(keyPosition.mPercentY)) {
                f3 = keyPosition.mPercentY;
            }
            float f18 = motionPaths2.y;
            float f19 = motionPaths.y;
            this.y = (f3 * (f18 - f19)) + f19;
        } else {
            if (Float.isNaN(keyPosition.mPercentX)) {
                float f20 = motionPaths2.x;
                float f21 = motionPaths.x;
                f = ((f20 - f21) * f3) + f21;
            } else {
                f = Math.min(f5, f4) * keyPosition.mPercentX;
            }
            this.x = f;
            if (Float.isNaN(keyPosition.mPercentY)) {
                float f22 = motionPaths2.y;
                float f23 = motionPaths.y;
                f2 = (f3 * (f22 - f23)) + f23;
            } else {
                f2 = keyPosition.mPercentY;
            }
            this.y = f2;
        }
        this.mAnimateRelativeTo = motionPaths.mAnimateRelativeTo;
        this.mKeyFrameEasing = Easing.getInterpolator(keyPosition.mTransitionEasing);
        this.mPathMotionArc = keyPosition.mPathMotionArc;
    }

    public void setupRelative(MotionController motionController, MotionPaths motionPaths) {
        double d = (double) (((this.x + (this.width / 2.0f)) - motionPaths.x) - (motionPaths.width / 2.0f));
        double d2 = (double) (((this.y + (this.height / 2.0f)) - motionPaths.y) - (motionPaths.height / 2.0f));
        this.mRelativeToController = motionController;
        this.x = (float) Math.hypot(d2, d);
        if (Float.isNaN(this.mRelativeAngle)) {
            this.y = (float) (Math.atan2(d2, d) + 1.5707963267948966d);
        } else {
            this.y = (float) Math.toRadians((double) this.mRelativeAngle);
        }
    }

    /* access modifiers changed from: package-private */
    public void initScreen(int i, int i2, KeyPosition keyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        KeyPosition keyPosition2 = keyPosition;
        MotionPaths motionPaths3 = motionPaths;
        MotionPaths motionPaths4 = motionPaths2;
        float f = ((float) keyPosition2.mFramePosition) / 100.0f;
        this.time = f;
        this.mDrawPath = keyPosition2.mDrawPath;
        float f2 = Float.isNaN(keyPosition2.mPercentWidth) ? f : keyPosition2.mPercentWidth;
        float f3 = Float.isNaN(keyPosition2.mPercentHeight) ? f : keyPosition2.mPercentHeight;
        float f4 = motionPaths4.width;
        float f5 = motionPaths3.width;
        float f6 = motionPaths4.height;
        float f7 = motionPaths3.height;
        this.position = this.time;
        float f8 = motionPaths3.x;
        float f9 = motionPaths3.y;
        float f10 = motionPaths4.x + (f4 / 2.0f);
        float f11 = motionPaths4.y + (f6 / 2.0f);
        float f12 = (f4 - f5) * f2;
        this.x = (float) ((int) ((f8 + ((f10 - ((f5 / 2.0f) + f8)) * f)) - (f12 / 2.0f)));
        float f13 = (f6 - f7) * f3;
        this.y = (float) ((int) ((f9 + ((f11 - (f9 + (f7 / 2.0f))) * f)) - (f13 / 2.0f)));
        this.width = (float) ((int) (f5 + f12));
        this.height = (float) ((int) (f7 + f13));
        this.mMode = 2;
        KeyPosition keyPosition3 = keyPosition;
        if (!Float.isNaN(keyPosition3.mPercentX)) {
            this.x = (float) ((int) (keyPosition3.mPercentX * ((float) ((int) (((float) i) - this.width)))));
        }
        if (!Float.isNaN(keyPosition3.mPercentY)) {
            this.y = (float) ((int) (keyPosition3.mPercentY * ((float) ((int) (((float) i2) - this.height)))));
        }
        this.mAnimateRelativeTo = this.mAnimateRelativeTo;
        this.mKeyFrameEasing = Easing.getInterpolator(keyPosition3.mTransitionEasing);
        this.mPathMotionArc = keyPosition3.mPathMotionArc;
    }

    /* access modifiers changed from: package-private */
    public void initPath(KeyPosition keyPosition, MotionPaths motionPaths, MotionPaths motionPaths2) {
        KeyPosition keyPosition2 = keyPosition;
        MotionPaths motionPaths3 = motionPaths;
        MotionPaths motionPaths4 = motionPaths2;
        float f = ((float) keyPosition2.mFramePosition) / 100.0f;
        this.time = f;
        this.mDrawPath = keyPosition2.mDrawPath;
        float f2 = Float.isNaN(keyPosition2.mPercentWidth) ? f : keyPosition2.mPercentWidth;
        float f3 = Float.isNaN(keyPosition2.mPercentHeight) ? f : keyPosition2.mPercentHeight;
        float f4 = motionPaths4.width - motionPaths3.width;
        float f5 = motionPaths4.height - motionPaths3.height;
        this.position = this.time;
        if (!Float.isNaN(keyPosition2.mPercentX)) {
            f = keyPosition2.mPercentX;
        }
        float f6 = motionPaths3.x;
        float f7 = motionPaths3.width;
        float f8 = motionPaths3.y;
        float f9 = motionPaths3.height;
        float f10 = (motionPaths4.x + (motionPaths4.width / 2.0f)) - ((f7 / 2.0f) + f6);
        float f11 = (motionPaths4.y + (motionPaths4.height / 2.0f)) - ((f9 / 2.0f) + f8);
        float f12 = f10 * f;
        float f13 = f4 * f2;
        float f14 = f13 / 2.0f;
        this.x = (float) ((int) ((f6 + f12) - f14));
        float f15 = f * f11;
        float f16 = f5 * f3;
        float f17 = f16 / 2.0f;
        this.y = (float) ((int) ((f8 + f15) - f17));
        this.width = (float) ((int) (f7 + f13));
        this.height = (float) ((int) (f9 + f16));
        KeyPosition keyPosition3 = keyPosition;
        float f18 = Float.isNaN(keyPosition3.mPercentY) ? 0.0f : keyPosition3.mPercentY;
        float f19 = (-f11) * f18;
        float f20 = f10 * f18;
        this.mMode = 1;
        MotionPaths motionPaths5 = motionPaths;
        float f21 = (float) ((int) ((motionPaths5.x + f12) - f14));
        this.x = f21;
        float f22 = (float) ((int) ((motionPaths5.y + f15) - f17));
        this.y = f22;
        this.x = f21 + f19;
        this.y = f22 + f20;
        this.mAnimateRelativeTo = this.mAnimateRelativeTo;
        this.mKeyFrameEasing = Easing.getInterpolator(keyPosition3.mTransitionEasing);
        this.mPathMotionArc = keyPosition3.mPathMotionArc;
    }

    private boolean diff(float f, float f2) {
        if (Float.isNaN(f) || Float.isNaN(f2)) {
            if (Float.isNaN(f) != Float.isNaN(f2)) {
                return true;
            }
            return false;
        } else if (Math.abs(f - f2) > 1.0E-6f) {
            return true;
        } else {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void different(MotionPaths motionPaths, boolean[] zArr, String[] strArr, boolean z) {
        boolean diff = diff(this.x, motionPaths.x);
        boolean diff2 = diff(this.y, motionPaths.y);
        zArr[0] = zArr[0] | diff(this.position, motionPaths.position);
        boolean z2 = diff | diff2 | z;
        zArr[1] = zArr[1] | z2;
        zArr[2] = z2 | zArr[2];
        zArr[3] = zArr[3] | diff(this.width, motionPaths.width);
        zArr[4] = diff(this.height, motionPaths.height) | zArr[4];
    }

    /* access modifiers changed from: package-private */
    public void getCenter(double d, int[] iArr, double[] dArr, float[] fArr, int i) {
        int[] iArr2 = iArr;
        float f = this.x;
        float f2 = this.y;
        float f3 = this.width;
        float f4 = this.height;
        for (int i2 = 0; i2 < iArr2.length; i2++) {
            float f5 = (float) dArr[i2];
            int i3 = iArr2[i2];
            if (i3 == 1) {
                f = f5;
            } else if (i3 == 2) {
                f2 = f5;
            } else if (i3 == 3) {
                f3 = f5;
            } else if (i3 == 4) {
                f4 = f5;
            }
        }
        MotionController motionController = this.mRelativeToController;
        if (motionController != null) {
            float[] fArr2 = new float[2];
            motionController.getCenter(d, fArr2, new float[2]);
            float f6 = fArr2[0];
            float f7 = fArr2[1];
            double d2 = (double) f6;
            double d3 = (double) f;
            double d4 = (double) f2;
            f = (float) ((d2 + (Math.sin(d4) * d3)) - ((double) (f3 / 2.0f)));
            f2 = (float) ((((double) f7) - (d3 * Math.cos(d4))) - ((double) (f4 / 2.0f)));
        }
        fArr[i] = f + (f3 / 2.0f) + 0.0f;
        fArr[i + 1] = f2 + (f4 / 2.0f) + 0.0f;
    }

    /* access modifiers changed from: package-private */
    public void getCenter(double d, int[] iArr, double[] dArr, float[] fArr, double[] dArr2, float[] fArr2) {
        float f;
        int[] iArr2 = iArr;
        float f2 = this.x;
        float f3 = this.y;
        float f4 = this.width;
        float f5 = this.height;
        float f6 = 0.0f;
        float f7 = 0.0f;
        float f8 = 0.0f;
        float f9 = 0.0f;
        for (int i = 0; i < iArr2.length; i++) {
            float f10 = (float) dArr[i];
            float f11 = (float) dArr2[i];
            int i2 = iArr2[i];
            if (i2 == 1) {
                f2 = f10;
                f6 = f11;
            } else if (i2 == 2) {
                f3 = f10;
                f8 = f11;
            } else if (i2 == 3) {
                f4 = f10;
                f7 = f11;
            } else if (i2 == 4) {
                f5 = f10;
                f9 = f11;
            }
        }
        float f12 = 2.0f;
        float f13 = (f7 / 2.0f) + f6;
        float f14 = (f9 / 2.0f) + f8;
        MotionController motionController = this.mRelativeToController;
        if (motionController != null) {
            float[] fArr3 = new float[2];
            float[] fArr4 = new float[2];
            motionController.getCenter(d, fArr3, fArr4);
            float f15 = fArr3[0];
            float f16 = fArr3[1];
            float f17 = fArr4[0];
            double d2 = (double) f2;
            float f18 = fArr4[1];
            double d3 = (double) f3;
            f = f4;
            double d4 = (double) f6;
            double d5 = (double) f8;
            float sin = (float) (((double) f17) + (Math.sin(d3) * d4) + (Math.cos(d3) * d5));
            f14 = (float) ((((double) f18) - (d4 * Math.cos(d3))) + (Math.sin(d3) * d5));
            f13 = sin;
            f2 = (float) ((((double) f15) + (Math.sin(d3) * d2)) - ((double) (f4 / 2.0f)));
            f3 = (float) ((((double) f16) - (d2 * Math.cos(d3))) - ((double) (f5 / 2.0f)));
            f12 = 2.0f;
        } else {
            f = f4;
        }
        fArr[0] = f2 + (f / f12) + 0.0f;
        fArr[1] = f3 + (f5 / f12) + 0.0f;
        fArr2[0] = f13;
        fArr2[1] = f14;
    }

    /* access modifiers changed from: package-private */
    public void getCenterVelocity(double d, int[] iArr, double[] dArr, float[] fArr, int i) {
        int[] iArr2 = iArr;
        float f = this.x;
        float f2 = this.y;
        float f3 = this.width;
        float f4 = this.height;
        for (int i2 = 0; i2 < iArr2.length; i2++) {
            float f5 = (float) dArr[i2];
            int i3 = iArr2[i2];
            if (i3 == 1) {
                f = f5;
            } else if (i3 == 2) {
                f2 = f5;
            } else if (i3 == 3) {
                f3 = f5;
            } else if (i3 == 4) {
                f4 = f5;
            }
        }
        MotionController motionController = this.mRelativeToController;
        if (motionController != null) {
            float[] fArr2 = new float[2];
            motionController.getCenter(d, fArr2, new float[2]);
            float f6 = fArr2[0];
            float f7 = fArr2[1];
            double d2 = (double) f6;
            double d3 = (double) f;
            double d4 = (double) f2;
            f = (float) ((d2 + (Math.sin(d4) * d3)) - ((double) (f3 / 2.0f)));
            f2 = (float) ((((double) f7) - (d3 * Math.cos(d4))) - ((double) (f4 / 2.0f)));
        }
        fArr[i] = f + (f3 / 2.0f) + 0.0f;
        fArr[i + 1] = f2 + (f4 / 2.0f) + 0.0f;
    }

    /* access modifiers changed from: package-private */
    public void getBounds(int[] iArr, double[] dArr, float[] fArr, int i) {
        float f = this.width;
        float f2 = this.height;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            float f3 = (float) dArr[i2];
            int i3 = iArr[i2];
            if (i3 == 3) {
                f = f3;
            } else if (i3 == 4) {
                f2 = f3;
            }
        }
        fArr[i] = f;
        fArr[i + 1] = f2;
    }

    /* access modifiers changed from: package-private */
    public void setView(float f, View view, int[] iArr, double[] dArr, double[] dArr2, double[] dArr3, boolean z) {
        float f2;
        boolean z2;
        float f3;
        View view2 = view;
        int[] iArr2 = iArr;
        double[] dArr4 = dArr2;
        float f4 = this.x;
        float f5 = this.y;
        float f6 = this.width;
        float f7 = this.height;
        if (iArr2.length != 0 && this.mTempValue.length <= iArr2[iArr2.length - 1]) {
            int i = iArr2[iArr2.length - 1] + 1;
            this.mTempValue = new double[i];
            this.mTempDelta = new double[i];
        }
        Arrays.fill(this.mTempValue, Double.NaN);
        for (int i2 = 0; i2 < iArr2.length; i2++) {
            this.mTempValue[iArr2[i2]] = dArr[i2];
            this.mTempDelta[iArr2[i2]] = dArr4[i2];
        }
        float f8 = Float.NaN;
        int i3 = 0;
        float f9 = 0.0f;
        float f10 = 0.0f;
        float f11 = 0.0f;
        float f12 = 0.0f;
        while (true) {
            double[] dArr5 = this.mTempValue;
            if (i3 >= dArr5.length) {
                break;
            }
            double d = 0.0d;
            if (!Double.isNaN(dArr5[i3]) || !(dArr3 == null || dArr3[i3] == 0.0d)) {
                if (dArr3 != null) {
                    d = dArr3[i3];
                }
                if (!Double.isNaN(this.mTempValue[i3])) {
                    d = this.mTempValue[i3] + d;
                }
                f3 = f8;
                float f13 = (float) d;
                float f14 = (float) this.mTempDelta[i3];
                if (i3 == 1) {
                    f8 = f3;
                    f9 = f14;
                    f4 = f13;
                } else if (i3 == 2) {
                    f8 = f3;
                    f10 = f14;
                    f5 = f13;
                } else if (i3 == 3) {
                    f8 = f3;
                    f11 = f14;
                    f6 = f13;
                } else if (i3 == 4) {
                    f8 = f3;
                    f12 = f14;
                    f7 = f13;
                } else if (i3 == 5) {
                    f8 = f13;
                }
                i3++;
                double[] dArr6 = dArr2;
            } else {
                f3 = f8;
            }
            f8 = f3;
            i3++;
            double[] dArr62 = dArr2;
        }
        float f15 = f8;
        MotionController motionController = this.mRelativeToController;
        if (motionController != null) {
            float[] fArr = new float[2];
            float[] fArr2 = new float[2];
            motionController.getCenter((double) f, fArr, fArr2);
            float f16 = fArr[0];
            float f17 = fArr[1];
            float f18 = fArr2[0];
            float f19 = fArr2[1];
            double d2 = (double) f4;
            double d3 = (double) f5;
            double sin = ((double) f16) + (Math.sin(d3) * d2);
            f2 = f7;
            float f20 = (float) (sin - ((double) (f6 / 2.0f)));
            float cos = (float) ((((double) f17) - (Math.cos(d3) * d2)) - ((double) (f7 / 2.0f)));
            double d4 = (double) f9;
            double d5 = (double) f10;
            float sin2 = (float) (((double) f18) + (Math.sin(d3) * d4) + (Math.cos(d3) * d2 * d5));
            float f21 = f20;
            float cos2 = (float) ((((double) f19) - (d4 * Math.cos(d3))) + (d2 * Math.sin(d3) * d5));
            double[] dArr7 = dArr2;
            if (dArr7.length >= 2) {
                z2 = false;
                dArr7[0] = (double) sin2;
                dArr7[1] = (double) cos2;
            } else {
                z2 = false;
            }
            if (!Float.isNaN(f15)) {
                view2.setRotation((float) (((double) f15) + Math.toDegrees(Math.atan2((double) cos2, (double) sin2))));
            }
            f4 = f21;
            f5 = cos;
        } else {
            float f22 = f15;
            f2 = f7;
            z2 = false;
            if (!Float.isNaN(f22)) {
                view2.setRotation((float) (((double) 0.0f) + ((double) f22) + Math.toDegrees(Math.atan2((double) (f10 + (f12 / 2.0f)), (double) (f9 + (f11 / 2.0f))))));
            }
        }
        if (view2 instanceof FloatLayout) {
            ((FloatLayout) view2).layout(f4, f5, f6 + f4, f5 + f2);
            return;
        }
        float f23 = f4 + 0.5f;
        int i4 = (int) f23;
        float f24 = f5 + 0.5f;
        int i5 = (int) f24;
        int i6 = (int) (f23 + f6);
        int i7 = (int) (f24 + f2);
        int i8 = i6 - i4;
        int i9 = i7 - i5;
        if (!(i8 == view.getMeasuredWidth() && i9 == view.getMeasuredHeight())) {
            z2 = true;
        }
        if (z2 || z) {
            view2.measure(View.MeasureSpec.makeMeasureSpec(i8, BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(i9, BasicMeasure.EXACTLY));
        }
        view2.layout(i4, i5, i6, i7);
    }

    /* access modifiers changed from: package-private */
    public void getRect(int[] iArr, double[] dArr, float[] fArr, int i) {
        float f = this.x;
        float f2 = this.y;
        float f3 = this.width;
        float f4 = this.height;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            float f5 = (float) dArr[i2];
            int i3 = iArr[i2];
            if (i3 == 1) {
                f = f5;
            } else if (i3 == 2) {
                f2 = f5;
            } else if (i3 == 3) {
                f3 = f5;
            } else if (i3 == 4) {
                f4 = f5;
            }
        }
        MotionController motionController = this.mRelativeToController;
        if (motionController != null) {
            float centerX = motionController.getCenterX();
            double d = (double) f;
            double d2 = (double) f2;
            f2 = (float) ((((double) this.mRelativeToController.getCenterY()) - (d * Math.cos(d2))) - ((double) (f4 / 2.0f)));
            f = (float) ((((double) centerX) + (Math.sin(d2) * d)) - ((double) (f3 / 2.0f)));
        }
        float f6 = f3 + f;
        float f7 = f4 + f2;
        Float.isNaN(Float.NaN);
        Float.isNaN(Float.NaN);
        int i4 = i + 1;
        fArr[i] = f + 0.0f;
        int i5 = i4 + 1;
        fArr[i4] = f2 + 0.0f;
        int i6 = i5 + 1;
        fArr[i5] = f6 + 0.0f;
        int i7 = i6 + 1;
        fArr[i6] = f2 + 0.0f;
        int i8 = i7 + 1;
        fArr[i7] = f6 + 0.0f;
        int i9 = i8 + 1;
        fArr[i8] = f7 + 0.0f;
        fArr[i9] = f + 0.0f;
        fArr[i9 + 1] = f7 + 0.0f;
    }

    /* access modifiers changed from: package-private */
    public void setDpDt(float f, float f2, float[] fArr, int[] iArr, double[] dArr, double[] dArr2) {
        int[] iArr2 = iArr;
        float f3 = 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        for (int i = 0; i < iArr2.length; i++) {
            float f7 = (float) dArr[i];
            double d = dArr2[i];
            int i2 = iArr2[i];
            if (i2 == 1) {
                f3 = f7;
            } else if (i2 == 2) {
                f5 = f7;
            } else if (i2 == 3) {
                f4 = f7;
            } else if (i2 == 4) {
                f6 = f7;
            }
        }
        float f8 = f3 - ((0.0f * f4) / 2.0f);
        float f9 = f5 - ((0.0f * f6) / 2.0f);
        fArr[0] = (f8 * (1.0f - f)) + (((f4 * 1.0f) + f8) * f) + 0.0f;
        fArr[1] = (f9 * (1.0f - f2)) + (((f6 * 1.0f) + f9) * f2) + 0.0f;
    }

    /* access modifiers changed from: package-private */
    public void fillStandard(double[] dArr, int[] iArr) {
        float[] fArr = {this.position, this.x, this.y, this.width, this.height, this.mPathRotate};
        int i = 0;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (iArr[i2] < 6) {
                dArr[i] = (double) fArr[iArr[i2]];
                i++;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean hasCustomData(String str) {
        return this.attributes.containsKey(str);
    }

    /* access modifiers changed from: package-private */
    public int getCustomDataCount(String str) {
        ConstraintAttribute constraintAttribute = this.attributes.get(str);
        if (constraintAttribute == null) {
            return 0;
        }
        return constraintAttribute.numberOfInterpolatedValues();
    }

    /* access modifiers changed from: package-private */
    public int getCustomData(String str, double[] dArr, int i) {
        ConstraintAttribute constraintAttribute = this.attributes.get(str);
        int i2 = 0;
        if (constraintAttribute == null) {
            return 0;
        }
        if (constraintAttribute.numberOfInterpolatedValues() == 1) {
            dArr[i] = (double) constraintAttribute.getValueToInterpolate();
            return 1;
        }
        int numberOfInterpolatedValues = constraintAttribute.numberOfInterpolatedValues();
        float[] fArr = new float[numberOfInterpolatedValues];
        constraintAttribute.getValuesToInterpolate(fArr);
        while (i2 < numberOfInterpolatedValues) {
            dArr[i] = (double) fArr[i2];
            i2++;
            i++;
        }
        return numberOfInterpolatedValues;
    }

    /* access modifiers changed from: package-private */
    public void setBounds(float f, float f2, float f3, float f4) {
        this.x = f;
        this.y = f2;
        this.width = f3;
        this.height = f4;
    }

    public int compareTo(MotionPaths motionPaths) {
        return Float.compare(this.position, motionPaths.position);
    }

    public void applyParameters(ConstraintSet.Constraint constraint) {
        this.mKeyFrameEasing = Easing.getInterpolator(constraint.motion.mTransitionEasing);
        this.mPathMotionArc = constraint.motion.mPathMotionArc;
        this.mAnimateRelativeTo = constraint.motion.mAnimateRelativeTo;
        this.mPathRotate = constraint.motion.mPathRotate;
        this.mDrawPath = constraint.motion.mDrawPath;
        this.mAnimateCircleAngleTo = constraint.motion.mAnimateCircleAngleTo;
        this.mProgress = constraint.propertySet.mProgress;
        this.mRelativeAngle = constraint.layout.circleAngle;
        for (String next : constraint.mCustomConstraints.keySet()) {
            ConstraintAttribute constraintAttribute = constraint.mCustomConstraints.get(next);
            if (constraintAttribute != null && constraintAttribute.isContinuous()) {
                this.attributes.put(next, constraintAttribute);
            }
        }
    }

    public void configureRelativeTo(MotionController motionController) {
        motionController.getPos((double) this.mProgress);
    }
}
