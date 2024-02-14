package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import androidx.constraintlayout.core.motion.utils.CurveFit;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.motion.utils.KeyCycleOscillator;
import androidx.constraintlayout.core.motion.utils.SplineSet;
import androidx.constraintlayout.core.motion.utils.VelocityMatrix;
import androidx.constraintlayout.motion.utils.ViewOscillator;
import androidx.constraintlayout.motion.utils.ViewSpline;
import androidx.constraintlayout.motion.utils.ViewState;
import androidx.constraintlayout.motion.utils.ViewTimeCycle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class MotionController {
    static final int BOUNCE = 4;
    private static final boolean DEBUG = false;
    public static final int DRAW_PATH_AS_CONFIGURED = 4;
    public static final int DRAW_PATH_BASIC = 1;
    public static final int DRAW_PATH_CARTESIAN = 3;
    public static final int DRAW_PATH_NONE = 0;
    public static final int DRAW_PATH_RECTANGLE = 5;
    public static final int DRAW_PATH_RELATIVE = 2;
    public static final int DRAW_PATH_SCREEN = 6;
    static final int EASE_IN = 1;
    static final int EASE_IN_OUT = 0;
    static final int EASE_OUT = 2;
    private static final boolean FAVOR_FIXED_SIZE_VIEWS = false;
    public static final int HORIZONTAL_PATH_X = 2;
    public static final int HORIZONTAL_PATH_Y = 3;
    private static final int INTERPOLATOR_REFERENCE_ID = -2;
    private static final int INTERPOLATOR_UNDEFINED = -3;
    static final int LINEAR = 3;
    static final int OVERSHOOT = 5;
    public static final int PATH_PERCENT = 0;
    public static final int PATH_PERPENDICULAR = 1;
    public static final int ROTATION_LEFT = 2;
    public static final int ROTATION_RIGHT = 1;
    private static final int SPLINE_STRING = -1;
    private static final String TAG = "MotionController";
    public static final int VERTICAL_PATH_X = 4;
    public static final int VERTICAL_PATH_Y = 5;
    private int MAX_DIMENSION = 4;
    String[] attributeTable;
    private CurveFit mArcSpline;
    private int[] mAttributeInterpolatorCount;
    private String[] mAttributeNames;
    private HashMap<String, ViewSpline> mAttributesMap;
    String mConstraintTag;
    float mCurrentCenterX;
    float mCurrentCenterY;
    private int mCurveFitType = -1;
    private HashMap<String, ViewOscillator> mCycleMap;
    private MotionPaths mEndMotionPath = new MotionPaths();
    private MotionConstrainedPoint mEndPoint = new MotionConstrainedPoint();
    boolean mForceMeasure = false;
    int mId;
    private double[] mInterpolateData;
    private int[] mInterpolateVariables;
    private double[] mInterpolateVelocity;
    private ArrayList<Key> mKeyList = new ArrayList<>();
    private KeyTrigger[] mKeyTriggers;
    private ArrayList<MotionPaths> mMotionPaths = new ArrayList<>();
    float mMotionStagger = Float.NaN;
    private boolean mNoMovement = false;
    private int mPathMotionArc = Key.UNSET;
    private Interpolator mQuantizeMotionInterpolator = null;
    private float mQuantizeMotionPhase = Float.NaN;
    private int mQuantizeMotionSteps = Key.UNSET;
    private CurveFit[] mSpline;
    float mStaggerOffset = 0.0f;
    float mStaggerScale = 1.0f;
    private MotionPaths mStartMotionPath = new MotionPaths();
    private MotionConstrainedPoint mStartPoint = new MotionConstrainedPoint();
    Rect mTempRect = new Rect();
    private HashMap<String, ViewTimeCycle> mTimeCycleAttributesMap;
    private int mTransformPivotTarget = Key.UNSET;
    private View mTransformPivotView = null;
    private float[] mValuesBuff = new float[4];
    private float[] mVelocity = new float[1];
    View mView;

    public int getTransformPivotTarget() {
        return this.mTransformPivotTarget;
    }

    public void setTransformPivotTarget(int i) {
        this.mTransformPivotTarget = i;
        this.mTransformPivotView = null;
    }

    /* access modifiers changed from: package-private */
    public MotionPaths getKeyFrame(int i) {
        return this.mMotionPaths.get(i);
    }

    MotionController(View view) {
        setView(view);
    }

    public float getStartX() {
        return this.mStartMotionPath.x;
    }

    public float getStartY() {
        return this.mStartMotionPath.y;
    }

    public float getFinalX() {
        return this.mEndMotionPath.x;
    }

    public float getFinalY() {
        return this.mEndMotionPath.y;
    }

    public float getStartWidth() {
        return this.mStartMotionPath.width;
    }

    public float getStartHeight() {
        return this.mStartMotionPath.height;
    }

    public float getFinalWidth() {
        return this.mEndMotionPath.width;
    }

    public float getFinalHeight() {
        return this.mEndMotionPath.height;
    }

    public int getAnimateRelativeTo() {
        return this.mStartMotionPath.mAnimateRelativeTo;
    }

    public void setupRelative(MotionController motionController) {
        this.mStartMotionPath.setupRelative(motionController, motionController.mStartMotionPath);
        this.mEndMotionPath.setupRelative(motionController, motionController.mEndMotionPath);
    }

    public float getCenterX() {
        return this.mCurrentCenterX;
    }

    public float getCenterY() {
        return this.mCurrentCenterY;
    }

    public void getCenter(double d, float[] fArr, float[] fArr2) {
        double[] dArr = new double[4];
        double[] dArr2 = new double[4];
        this.mSpline[0].getPos(d, dArr);
        this.mSpline[0].getSlope(d, dArr2);
        Arrays.fill(fArr2, 0.0f);
        this.mStartMotionPath.getCenter(d, this.mInterpolateVariables, dArr, fArr, dArr2, fArr2);
    }

    public void remeasure() {
        this.mForceMeasure = true;
    }

    /* access modifiers changed from: package-private */
    public void buildPath(float[] fArr, int i) {
        int i2 = i;
        float f = 1.0f;
        float f2 = 1.0f / ((float) (i2 - 1));
        HashMap<String, ViewSpline> hashMap = this.mAttributesMap;
        ViewOscillator viewOscillator = null;
        SplineSet splineSet = hashMap == null ? null : hashMap.get("translationX");
        HashMap<String, ViewSpline> hashMap2 = this.mAttributesMap;
        SplineSet splineSet2 = hashMap2 == null ? null : hashMap2.get("translationY");
        HashMap<String, ViewOscillator> hashMap3 = this.mCycleMap;
        ViewOscillator viewOscillator2 = hashMap3 == null ? null : hashMap3.get("translationX");
        HashMap<String, ViewOscillator> hashMap4 = this.mCycleMap;
        if (hashMap4 != null) {
            viewOscillator = hashMap4.get("translationY");
        }
        ViewOscillator viewOscillator3 = viewOscillator;
        int i3 = 0;
        while (i3 < i2) {
            float f3 = ((float) i3) * f2;
            float f4 = this.mStaggerScale;
            if (f4 != f) {
                float f5 = this.mStaggerOffset;
                if (f3 < f5) {
                    f3 = 0.0f;
                }
                if (f3 > f5 && ((double) f3) < 1.0d) {
                    f3 = Math.min((f3 - f5) * f4, f);
                }
            }
            float f6 = f3;
            double d = (double) f6;
            Easing easing = this.mStartMotionPath.mKeyFrameEasing;
            float f7 = Float.NaN;
            Iterator<MotionPaths> it = this.mMotionPaths.iterator();
            float f8 = 0.0f;
            while (it.hasNext()) {
                MotionPaths next = it.next();
                if (next.mKeyFrameEasing != null) {
                    if (next.time < f6) {
                        easing = next.mKeyFrameEasing;
                        f8 = next.time;
                    } else if (Float.isNaN(f7)) {
                        f7 = next.time;
                    }
                }
            }
            if (easing != null) {
                if (Float.isNaN(f7)) {
                    f7 = 1.0f;
                }
                float f9 = f7 - f8;
                d = (double) ((((float) easing.get((double) ((f6 - f8) / f9))) * f9) + f8);
            }
            double d2 = d;
            this.mSpline[0].getPos(d2, this.mInterpolateData);
            CurveFit curveFit = this.mArcSpline;
            if (curveFit != null) {
                double[] dArr = this.mInterpolateData;
                if (dArr.length > 0) {
                    curveFit.getPos(d2, dArr);
                }
            }
            int i4 = i3 * 2;
            float f10 = f6;
            int i5 = i3;
            this.mStartMotionPath.getCenter(d2, this.mInterpolateVariables, this.mInterpolateData, fArr, i4);
            if (viewOscillator2 != null) {
                fArr[i4] = fArr[i4] + viewOscillator2.get(f10);
            } else if (splineSet != null) {
                fArr[i4] = fArr[i4] + splineSet.get(f10);
            }
            if (viewOscillator3 != null) {
                int i6 = i4 + 1;
                fArr[i6] = fArr[i6] + viewOscillator3.get(f10);
            } else if (splineSet2 != null) {
                int i7 = i4 + 1;
                fArr[i7] = fArr[i7] + splineSet2.get(f10);
            }
            i3 = i5 + 1;
            f = 1.0f;
        }
    }

    /* access modifiers changed from: package-private */
    public double[] getPos(double d) {
        this.mSpline[0].getPos(d, this.mInterpolateData);
        CurveFit curveFit = this.mArcSpline;
        if (curveFit != null) {
            double[] dArr = this.mInterpolateData;
            if (dArr.length > 0) {
                curveFit.getPos(d, dArr);
            }
        }
        return this.mInterpolateData;
    }

    /* access modifiers changed from: package-private */
    public void buildBounds(float[] fArr, int i) {
        float f = 1.0f / ((float) (i - 1));
        HashMap<String, ViewSpline> hashMap = this.mAttributesMap;
        if (hashMap != null) {
            SplineSet splineSet = hashMap.get("translationX");
        }
        HashMap<String, ViewSpline> hashMap2 = this.mAttributesMap;
        if (hashMap2 != null) {
            SplineSet splineSet2 = hashMap2.get("translationY");
        }
        HashMap<String, ViewOscillator> hashMap3 = this.mCycleMap;
        if (hashMap3 != null) {
            ViewOscillator viewOscillator = hashMap3.get("translationX");
        }
        HashMap<String, ViewOscillator> hashMap4 = this.mCycleMap;
        if (hashMap4 != null) {
            ViewOscillator viewOscillator2 = hashMap4.get("translationY");
        }
        for (int i2 = 0; i2 < i; i2++) {
            float f2 = ((float) i2) * f;
            float f3 = this.mStaggerScale;
            float f4 = 0.0f;
            if (f3 != 1.0f) {
                float f5 = this.mStaggerOffset;
                if (f2 < f5) {
                    f2 = 0.0f;
                }
                if (f2 > f5 && ((double) f2) < 1.0d) {
                    f2 = Math.min((f2 - f5) * f3, 1.0f);
                }
            }
            double d = (double) f2;
            Easing easing = this.mStartMotionPath.mKeyFrameEasing;
            float f6 = Float.NaN;
            Iterator<MotionPaths> it = this.mMotionPaths.iterator();
            while (it.hasNext()) {
                MotionPaths next = it.next();
                if (next.mKeyFrameEasing != null) {
                    if (next.time < f2) {
                        easing = next.mKeyFrameEasing;
                        f4 = next.time;
                    } else if (Float.isNaN(f6)) {
                        f6 = next.time;
                    }
                }
            }
            if (easing != null) {
                if (Float.isNaN(f6)) {
                    f6 = 1.0f;
                }
                float f7 = f6 - f4;
                d = (double) ((((float) easing.get((double) ((f2 - f4) / f7))) * f7) + f4);
            }
            this.mSpline[0].getPos(d, this.mInterpolateData);
            CurveFit curveFit = this.mArcSpline;
            if (curveFit != null) {
                double[] dArr = this.mInterpolateData;
                if (dArr.length > 0) {
                    curveFit.getPos(d, dArr);
                }
            }
            this.mStartMotionPath.getBounds(this.mInterpolateVariables, this.mInterpolateData, fArr, i2 * 2);
        }
    }

    private float getPreCycleDistance() {
        float[] fArr = new float[2];
        float f = 1.0f / ((float) 99);
        double d = 0.0d;
        double d2 = 0.0d;
        float f2 = 0.0f;
        int i = 0;
        while (i < 100) {
            float f3 = ((float) i) * f;
            double d3 = (double) f3;
            Easing easing = this.mStartMotionPath.mKeyFrameEasing;
            Iterator<MotionPaths> it = this.mMotionPaths.iterator();
            float f4 = Float.NaN;
            float f5 = 0.0f;
            while (it.hasNext()) {
                MotionPaths next = it.next();
                if (next.mKeyFrameEasing != null) {
                    if (next.time < f3) {
                        easing = next.mKeyFrameEasing;
                        f5 = next.time;
                    } else if (Float.isNaN(f4)) {
                        f4 = next.time;
                    }
                }
            }
            if (easing != null) {
                if (Float.isNaN(f4)) {
                    f4 = 1.0f;
                }
                float f6 = f4 - f5;
                d3 = (double) ((((float) easing.get((double) ((f3 - f5) / f6))) * f6) + f5);
            }
            this.mSpline[0].getPos(d3, this.mInterpolateData);
            float f7 = f2;
            int i2 = i;
            this.mStartMotionPath.getCenter(d3, this.mInterpolateVariables, this.mInterpolateData, fArr, 0);
            f2 = i2 > 0 ? (float) (((double) f7) + Math.hypot(d2 - ((double) fArr[1]), d - ((double) fArr[0]))) : f7;
            d = (double) fArr[0];
            i = i2 + 1;
            d2 = (double) fArr[1];
        }
        return f2;
    }

    /* access modifiers changed from: package-private */
    public KeyPositionBase getPositionKeyframe(int i, int i2, float f, float f2) {
        RectF rectF = new RectF();
        rectF.left = this.mStartMotionPath.x;
        rectF.top = this.mStartMotionPath.y;
        rectF.right = rectF.left + this.mStartMotionPath.width;
        rectF.bottom = rectF.top + this.mStartMotionPath.height;
        RectF rectF2 = new RectF();
        rectF2.left = this.mEndMotionPath.x;
        rectF2.top = this.mEndMotionPath.y;
        rectF2.right = rectF2.left + this.mEndMotionPath.width;
        rectF2.bottom = rectF2.top + this.mEndMotionPath.height;
        Iterator<Key> it = this.mKeyList.iterator();
        while (it.hasNext()) {
            Key next = it.next();
            if (next instanceof KeyPositionBase) {
                KeyPositionBase keyPositionBase = (KeyPositionBase) next;
                if (keyPositionBase.intersects(i, i2, rectF, rectF2, f, f2)) {
                    return keyPositionBase;
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public int buildKeyFrames(float[] fArr, int[] iArr) {
        if (fArr == null) {
            return 0;
        }
        double[] timePoints = this.mSpline[0].getTimePoints();
        if (iArr != null) {
            Iterator<MotionPaths> it = this.mMotionPaths.iterator();
            int i = 0;
            while (it.hasNext()) {
                iArr[i] = it.next().mMode;
                i++;
            }
        }
        int i2 = 0;
        for (int i3 = 0; i3 < timePoints.length; i3++) {
            this.mSpline[0].getPos(timePoints[i3], this.mInterpolateData);
            this.mStartMotionPath.getCenter(timePoints[i3], this.mInterpolateVariables, this.mInterpolateData, fArr, i2);
            i2 += 2;
        }
        return i2 / 2;
    }

    /* access modifiers changed from: package-private */
    public int buildKeyBounds(float[] fArr, int[] iArr) {
        if (fArr == null) {
            return 0;
        }
        double[] timePoints = this.mSpline[0].getTimePoints();
        if (iArr != null) {
            Iterator<MotionPaths> it = this.mMotionPaths.iterator();
            int i = 0;
            while (it.hasNext()) {
                iArr[i] = it.next().mMode;
                i++;
            }
        }
        int i2 = 0;
        for (double pos : timePoints) {
            this.mSpline[0].getPos(pos, this.mInterpolateData);
            this.mStartMotionPath.getBounds(this.mInterpolateVariables, this.mInterpolateData, fArr, i2);
            i2 += 2;
        }
        return i2 / 2;
    }

    /* access modifiers changed from: package-private */
    public int getAttributeValues(String str, float[] fArr, int i) {
        SplineSet splineSet = this.mAttributesMap.get(str);
        if (splineSet == null) {
            return -1;
        }
        for (int i2 = 0; i2 < fArr.length; i2++) {
            fArr[i2] = splineSet.get((float) (i2 / (fArr.length - 1)));
        }
        return fArr.length;
    }

    /* access modifiers changed from: package-private */
    public void buildRect(float f, float[] fArr, int i) {
        this.mSpline[0].getPos((double) getAdjustedPosition(f, (float[]) null), this.mInterpolateData);
        this.mStartMotionPath.getRect(this.mInterpolateVariables, this.mInterpolateData, fArr, i);
    }

    /* access modifiers changed from: package-private */
    public void buildRectangles(float[] fArr, int i) {
        float f = 1.0f / ((float) (i - 1));
        for (int i2 = 0; i2 < i; i2++) {
            this.mSpline[0].getPos((double) getAdjustedPosition(((float) i2) * f, (float[]) null), this.mInterpolateData);
            this.mStartMotionPath.getRect(this.mInterpolateVariables, this.mInterpolateData, fArr, i2 * 8);
        }
    }

    /* access modifiers changed from: package-private */
    public float getKeyFrameParameter(int i, float f, float f2) {
        float f3 = this.mEndMotionPath.x - this.mStartMotionPath.x;
        float f4 = this.mEndMotionPath.y - this.mStartMotionPath.y;
        float f5 = this.mStartMotionPath.x + (this.mStartMotionPath.width / 2.0f);
        float f6 = this.mStartMotionPath.y + (this.mStartMotionPath.height / 2.0f);
        float hypot = (float) Math.hypot((double) f3, (double) f4);
        if (((double) hypot) < 1.0E-7d) {
            return Float.NaN;
        }
        float f7 = f - f5;
        float f8 = f2 - f6;
        if (((float) Math.hypot((double) f7, (double) f8)) == 0.0f) {
            return 0.0f;
        }
        float f9 = (f7 * f3) + (f8 * f4);
        if (i == 0) {
            return f9 / hypot;
        }
        if (i == 1) {
            return (float) Math.sqrt((double) ((hypot * hypot) - (f9 * f9)));
        }
        if (i == 2) {
            return f7 / f3;
        }
        if (i == 3) {
            return f8 / f3;
        }
        if (i == 4) {
            return f7 / f4;
        }
        if (i != 5) {
            return 0.0f;
        }
        return f8 / f4;
    }

    private void insertKey(MotionPaths motionPaths) {
        int binarySearch = Collections.binarySearch(this.mMotionPaths, motionPaths);
        if (binarySearch == 0) {
            Log.e(TAG, " KeyPath position \"" + motionPaths.position + "\" outside of range");
        }
        this.mMotionPaths.add((-binarySearch) - 1, motionPaths);
    }

    /* access modifiers changed from: package-private */
    public void addKeys(ArrayList<Key> arrayList) {
        this.mKeyList.addAll(arrayList);
    }

    public void addKey(Key key) {
        this.mKeyList.add(key);
    }

    public void setPathMotionArc(int i) {
        this.mPathMotionArc = i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:63:0x015d, code lost:
        r11 = (java.lang.Integer) r5.get(r8);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setup(int r18, int r19, float r20, long r21) {
        /*
            r17 = this;
            r0 = r17
            java.lang.Class<double> r1 = double.class
            java.util.HashSet r2 = new java.util.HashSet
            r2.<init>()
            java.util.HashSet r2 = new java.util.HashSet
            r2.<init>()
            java.util.HashSet r3 = new java.util.HashSet
            r3.<init>()
            java.util.HashSet r4 = new java.util.HashSet
            r4.<init>()
            java.util.HashMap r5 = new java.util.HashMap
            r5.<init>()
            int r6 = r0.mPathMotionArc
            int r7 = androidx.constraintlayout.motion.widget.Key.UNSET
            if (r6 == r7) goto L_0x0029
            androidx.constraintlayout.motion.widget.MotionPaths r6 = r0.mStartMotionPath
            int r7 = r0.mPathMotionArc
            r6.mPathMotionArc = r7
        L_0x0029:
            androidx.constraintlayout.motion.widget.MotionConstrainedPoint r6 = r0.mStartPoint
            androidx.constraintlayout.motion.widget.MotionConstrainedPoint r7 = r0.mEndPoint
            r6.different(r7, r3)
            java.util.ArrayList<androidx.constraintlayout.motion.widget.Key> r6 = r0.mKeyList
            if (r6 == 0) goto L_0x0092
            java.util.Iterator r6 = r6.iterator()
            r8 = 0
        L_0x0039:
            boolean r9 = r6.hasNext()
            if (r9 == 0) goto L_0x0093
            java.lang.Object r9 = r6.next()
            androidx.constraintlayout.motion.widget.Key r9 = (androidx.constraintlayout.motion.widget.Key) r9
            boolean r10 = r9 instanceof androidx.constraintlayout.motion.widget.KeyPosition
            if (r10 == 0) goto L_0x006a
            androidx.constraintlayout.motion.widget.KeyPosition r9 = (androidx.constraintlayout.motion.widget.KeyPosition) r9
            androidx.constraintlayout.motion.widget.MotionPaths r10 = new androidx.constraintlayout.motion.widget.MotionPaths
            androidx.constraintlayout.motion.widget.MotionPaths r15 = r0.mStartMotionPath
            androidx.constraintlayout.motion.widget.MotionPaths r14 = r0.mEndMotionPath
            r11 = r10
            r12 = r18
            r13 = r19
            r16 = r14
            r14 = r9
            r11.<init>(r12, r13, r14, r15, r16)
            r0.insertKey(r10)
            int r10 = r9.mCurveFit
            int r11 = androidx.constraintlayout.motion.widget.Key.UNSET
            if (r10 == r11) goto L_0x0039
            int r9 = r9.mCurveFit
            r0.mCurveFitType = r9
            goto L_0x0039
        L_0x006a:
            boolean r10 = r9 instanceof androidx.constraintlayout.motion.widget.KeyCycle
            if (r10 == 0) goto L_0x0072
            r9.getAttributeNames(r4)
            goto L_0x0039
        L_0x0072:
            boolean r10 = r9 instanceof androidx.constraintlayout.motion.widget.KeyTimeCycle
            if (r10 == 0) goto L_0x007a
            r9.getAttributeNames(r2)
            goto L_0x0039
        L_0x007a:
            boolean r10 = r9 instanceof androidx.constraintlayout.motion.widget.KeyTrigger
            if (r10 == 0) goto L_0x008b
            if (r8 != 0) goto L_0x0085
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
        L_0x0085:
            androidx.constraintlayout.motion.widget.KeyTrigger r9 = (androidx.constraintlayout.motion.widget.KeyTrigger) r9
            r8.add(r9)
            goto L_0x0039
        L_0x008b:
            r9.setInterpolation(r5)
            r9.getAttributeNames(r3)
            goto L_0x0039
        L_0x0092:
            r8 = 0
        L_0x0093:
            r6 = 0
            if (r8 == 0) goto L_0x00a0
            androidx.constraintlayout.motion.widget.KeyTrigger[] r9 = new androidx.constraintlayout.motion.widget.KeyTrigger[r6]
            java.lang.Object[] r8 = r8.toArray(r9)
            androidx.constraintlayout.motion.widget.KeyTrigger[] r8 = (androidx.constraintlayout.motion.widget.KeyTrigger[]) r8
            r0.mKeyTriggers = r8
        L_0x00a0:
            boolean r8 = r3.isEmpty()
            java.lang.String r9 = ","
            java.lang.String r10 = "CUSTOM,"
            r11 = 1
            if (r8 != 0) goto L_0x0179
            java.util.HashMap r8 = new java.util.HashMap
            r8.<init>()
            r0.mAttributesMap = r8
            java.util.Iterator r8 = r3.iterator()
        L_0x00b6:
            boolean r12 = r8.hasNext()
            if (r12 == 0) goto L_0x0113
            java.lang.Object r12 = r8.next()
            java.lang.String r12 = (java.lang.String) r12
            boolean r13 = r12.startsWith(r10)
            if (r13 == 0) goto L_0x0102
            android.util.SparseArray r13 = new android.util.SparseArray
            r13.<init>()
            java.lang.String[] r14 = r12.split(r9)
            r14 = r14[r11]
            java.util.ArrayList<androidx.constraintlayout.motion.widget.Key> r15 = r0.mKeyList
            java.util.Iterator r15 = r15.iterator()
        L_0x00d9:
            boolean r16 = r15.hasNext()
            if (r16 == 0) goto L_0x00fd
            java.lang.Object r16 = r15.next()
            r7 = r16
            androidx.constraintlayout.motion.widget.Key r7 = (androidx.constraintlayout.motion.widget.Key) r7
            java.util.HashMap<java.lang.String, androidx.constraintlayout.widget.ConstraintAttribute> r11 = r7.mCustomConstraints
            if (r11 != 0) goto L_0x00ed
        L_0x00eb:
            r11 = 1
            goto L_0x00d9
        L_0x00ed:
            java.util.HashMap<java.lang.String, androidx.constraintlayout.widget.ConstraintAttribute> r11 = r7.mCustomConstraints
            java.lang.Object r11 = r11.get(r14)
            androidx.constraintlayout.widget.ConstraintAttribute r11 = (androidx.constraintlayout.widget.ConstraintAttribute) r11
            if (r11 == 0) goto L_0x00eb
            int r7 = r7.mFramePosition
            r13.append(r7, r11)
            goto L_0x00eb
        L_0x00fd:
            androidx.constraintlayout.motion.utils.ViewSpline r7 = androidx.constraintlayout.motion.utils.ViewSpline.makeCustomSpline(r12, r13)
            goto L_0x0106
        L_0x0102:
            androidx.constraintlayout.motion.utils.ViewSpline r7 = androidx.constraintlayout.motion.utils.ViewSpline.makeSpline(r12)
        L_0x0106:
            if (r7 != 0) goto L_0x0109
            goto L_0x0111
        L_0x0109:
            r7.setType(r12)
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewSpline> r11 = r0.mAttributesMap
            r11.put(r12, r7)
        L_0x0111:
            r11 = 1
            goto L_0x00b6
        L_0x0113:
            java.util.ArrayList<androidx.constraintlayout.motion.widget.Key> r7 = r0.mKeyList
            if (r7 == 0) goto L_0x0131
            java.util.Iterator r7 = r7.iterator()
        L_0x011b:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L_0x0131
            java.lang.Object r8 = r7.next()
            androidx.constraintlayout.motion.widget.Key r8 = (androidx.constraintlayout.motion.widget.Key) r8
            boolean r11 = r8 instanceof androidx.constraintlayout.motion.widget.KeyAttributes
            if (r11 == 0) goto L_0x011b
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewSpline> r11 = r0.mAttributesMap
            r8.addValues(r11)
            goto L_0x011b
        L_0x0131:
            androidx.constraintlayout.motion.widget.MotionConstrainedPoint r7 = r0.mStartPoint
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewSpline> r8 = r0.mAttributesMap
            r7.addValues(r8, r6)
            androidx.constraintlayout.motion.widget.MotionConstrainedPoint r7 = r0.mEndPoint
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewSpline> r8 = r0.mAttributesMap
            r11 = 100
            r7.addValues(r8, r11)
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewSpline> r7 = r0.mAttributesMap
            java.util.Set r7 = r7.keySet()
            java.util.Iterator r7 = r7.iterator()
        L_0x014b:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L_0x0179
            java.lang.Object r8 = r7.next()
            java.lang.String r8 = (java.lang.String) r8
            boolean r11 = r5.containsKey(r8)
            if (r11 == 0) goto L_0x016a
            java.lang.Object r11 = r5.get(r8)
            java.lang.Integer r11 = (java.lang.Integer) r11
            if (r11 == 0) goto L_0x016a
            int r11 = r11.intValue()
            goto L_0x016b
        L_0x016a:
            r11 = 0
        L_0x016b:
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewSpline> r12 = r0.mAttributesMap
            java.lang.Object r8 = r12.get(r8)
            androidx.constraintlayout.core.motion.utils.SplineSet r8 = (androidx.constraintlayout.core.motion.utils.SplineSet) r8
            if (r8 == 0) goto L_0x014b
            r8.setup(r11)
            goto L_0x014b
        L_0x0179:
            boolean r7 = r2.isEmpty()
            if (r7 != 0) goto L_0x0249
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewTimeCycle> r7 = r0.mTimeCycleAttributesMap
            if (r7 != 0) goto L_0x018a
            java.util.HashMap r7 = new java.util.HashMap
            r7.<init>()
            r0.mTimeCycleAttributesMap = r7
        L_0x018a:
            java.util.Iterator r2 = r2.iterator()
        L_0x018e:
            boolean r7 = r2.hasNext()
            if (r7 == 0) goto L_0x01f5
            java.lang.Object r7 = r2.next()
            java.lang.String r7 = (java.lang.String) r7
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewTimeCycle> r8 = r0.mTimeCycleAttributesMap
            boolean r8 = r8.containsKey(r7)
            if (r8 == 0) goto L_0x01a3
            goto L_0x018e
        L_0x01a3:
            boolean r8 = r7.startsWith(r10)
            if (r8 == 0) goto L_0x01e3
            android.util.SparseArray r8 = new android.util.SparseArray
            r8.<init>()
            java.lang.String[] r11 = r7.split(r9)
            r12 = 1
            r11 = r11[r12]
            java.util.ArrayList<androidx.constraintlayout.motion.widget.Key> r12 = r0.mKeyList
            java.util.Iterator r12 = r12.iterator()
        L_0x01bb:
            boolean r13 = r12.hasNext()
            if (r13 == 0) goto L_0x01dc
            java.lang.Object r13 = r12.next()
            androidx.constraintlayout.motion.widget.Key r13 = (androidx.constraintlayout.motion.widget.Key) r13
            java.util.HashMap<java.lang.String, androidx.constraintlayout.widget.ConstraintAttribute> r14 = r13.mCustomConstraints
            if (r14 != 0) goto L_0x01cc
            goto L_0x01bb
        L_0x01cc:
            java.util.HashMap<java.lang.String, androidx.constraintlayout.widget.ConstraintAttribute> r14 = r13.mCustomConstraints
            java.lang.Object r14 = r14.get(r11)
            androidx.constraintlayout.widget.ConstraintAttribute r14 = (androidx.constraintlayout.widget.ConstraintAttribute) r14
            if (r14 == 0) goto L_0x01bb
            int r13 = r13.mFramePosition
            r8.append(r13, r14)
            goto L_0x01bb
        L_0x01dc:
            androidx.constraintlayout.motion.utils.ViewTimeCycle r8 = androidx.constraintlayout.motion.utils.ViewTimeCycle.makeCustomSpline(r7, r8)
            r11 = r21
            goto L_0x01e9
        L_0x01e3:
            r11 = r21
            androidx.constraintlayout.motion.utils.ViewTimeCycle r8 = androidx.constraintlayout.motion.utils.ViewTimeCycle.makeSpline(r7, r11)
        L_0x01e9:
            if (r8 != 0) goto L_0x01ec
            goto L_0x018e
        L_0x01ec:
            r8.setType(r7)
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewTimeCycle> r13 = r0.mTimeCycleAttributesMap
            r13.put(r7, r8)
            goto L_0x018e
        L_0x01f5:
            java.util.ArrayList<androidx.constraintlayout.motion.widget.Key> r2 = r0.mKeyList
            if (r2 == 0) goto L_0x0215
            java.util.Iterator r2 = r2.iterator()
        L_0x01fd:
            boolean r7 = r2.hasNext()
            if (r7 == 0) goto L_0x0215
            java.lang.Object r7 = r2.next()
            androidx.constraintlayout.motion.widget.Key r7 = (androidx.constraintlayout.motion.widget.Key) r7
            boolean r8 = r7 instanceof androidx.constraintlayout.motion.widget.KeyTimeCycle
            if (r8 == 0) goto L_0x01fd
            androidx.constraintlayout.motion.widget.KeyTimeCycle r7 = (androidx.constraintlayout.motion.widget.KeyTimeCycle) r7
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewTimeCycle> r8 = r0.mTimeCycleAttributesMap
            r7.addTimeValues(r8)
            goto L_0x01fd
        L_0x0215:
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewTimeCycle> r2 = r0.mTimeCycleAttributesMap
            java.util.Set r2 = r2.keySet()
            java.util.Iterator r2 = r2.iterator()
        L_0x021f:
            boolean r7 = r2.hasNext()
            if (r7 == 0) goto L_0x0249
            java.lang.Object r7 = r2.next()
            java.lang.String r7 = (java.lang.String) r7
            boolean r8 = r5.containsKey(r7)
            if (r8 == 0) goto L_0x023c
            java.lang.Object r8 = r5.get(r7)
            java.lang.Integer r8 = (java.lang.Integer) r8
            int r8 = r8.intValue()
            goto L_0x023d
        L_0x023c:
            r8 = 0
        L_0x023d:
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewTimeCycle> r9 = r0.mTimeCycleAttributesMap
            java.lang.Object r7 = r9.get(r7)
            androidx.constraintlayout.motion.utils.ViewTimeCycle r7 = (androidx.constraintlayout.motion.utils.ViewTimeCycle) r7
            r7.setup(r8)
            goto L_0x021f
        L_0x0249:
            java.util.ArrayList<androidx.constraintlayout.motion.widget.MotionPaths> r2 = r0.mMotionPaths
            int r2 = r2.size()
            r5 = 2
            int r2 = r2 + r5
            androidx.constraintlayout.motion.widget.MotionPaths[] r7 = new androidx.constraintlayout.motion.widget.MotionPaths[r2]
            androidx.constraintlayout.motion.widget.MotionPaths r8 = r0.mStartMotionPath
            r7[r6] = r8
            int r8 = r2 + -1
            androidx.constraintlayout.motion.widget.MotionPaths r9 = r0.mEndMotionPath
            r7[r8] = r9
            java.util.ArrayList<androidx.constraintlayout.motion.widget.MotionPaths> r8 = r0.mMotionPaths
            int r8 = r8.size()
            if (r8 <= 0) goto L_0x026c
            int r8 = r0.mCurveFitType
            r9 = -1
            if (r8 != r9) goto L_0x026c
            r0.mCurveFitType = r6
        L_0x026c:
            java.util.ArrayList<androidx.constraintlayout.motion.widget.MotionPaths> r8 = r0.mMotionPaths
            java.util.Iterator r8 = r8.iterator()
            r9 = 1
        L_0x0273:
            boolean r11 = r8.hasNext()
            if (r11 == 0) goto L_0x0285
            java.lang.Object r11 = r8.next()
            androidx.constraintlayout.motion.widget.MotionPaths r11 = (androidx.constraintlayout.motion.widget.MotionPaths) r11
            int r12 = r9 + 1
            r7[r9] = r11
            r9 = r12
            goto L_0x0273
        L_0x0285:
            r8 = 18
            java.util.HashSet r9 = new java.util.HashSet
            r9.<init>()
            androidx.constraintlayout.motion.widget.MotionPaths r11 = r0.mEndMotionPath
            java.util.LinkedHashMap<java.lang.String, androidx.constraintlayout.widget.ConstraintAttribute> r11 = r11.attributes
            java.util.Set r11 = r11.keySet()
            java.util.Iterator r11 = r11.iterator()
        L_0x0298:
            boolean r12 = r11.hasNext()
            if (r12 == 0) goto L_0x02c7
            java.lang.Object r12 = r11.next()
            java.lang.String r12 = (java.lang.String) r12
            androidx.constraintlayout.motion.widget.MotionPaths r13 = r0.mStartMotionPath
            java.util.LinkedHashMap<java.lang.String, androidx.constraintlayout.widget.ConstraintAttribute> r13 = r13.attributes
            boolean r13 = r13.containsKey(r12)
            if (r13 == 0) goto L_0x0298
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r13.append(r10)
            r13.append(r12)
            java.lang.String r13 = r13.toString()
            boolean r13 = r3.contains(r13)
            if (r13 != 0) goto L_0x0298
            r9.add(r12)
            goto L_0x0298
        L_0x02c7:
            java.lang.String[] r3 = new java.lang.String[r6]
            java.lang.Object[] r3 = r9.toArray(r3)
            java.lang.String[] r3 = (java.lang.String[]) r3
            r0.mAttributeNames = r3
            int r3 = r3.length
            int[] r3 = new int[r3]
            r0.mAttributeInterpolatorCount = r3
            r3 = 0
        L_0x02d7:
            java.lang.String[] r9 = r0.mAttributeNames
            int r10 = r9.length
            if (r3 >= r10) goto L_0x030d
            r9 = r9[r3]
            int[] r10 = r0.mAttributeInterpolatorCount
            r10[r3] = r6
            r10 = 0
        L_0x02e3:
            if (r10 >= r2) goto L_0x030a
            r11 = r7[r10]
            java.util.LinkedHashMap<java.lang.String, androidx.constraintlayout.widget.ConstraintAttribute> r11 = r11.attributes
            boolean r11 = r11.containsKey(r9)
            if (r11 == 0) goto L_0x0307
            r11 = r7[r10]
            java.util.LinkedHashMap<java.lang.String, androidx.constraintlayout.widget.ConstraintAttribute> r11 = r11.attributes
            java.lang.Object r11 = r11.get(r9)
            androidx.constraintlayout.widget.ConstraintAttribute r11 = (androidx.constraintlayout.widget.ConstraintAttribute) r11
            if (r11 == 0) goto L_0x0307
            int[] r9 = r0.mAttributeInterpolatorCount
            r10 = r9[r3]
            int r11 = r11.numberOfInterpolatedValues()
            int r10 = r10 + r11
            r9[r3] = r10
            goto L_0x030a
        L_0x0307:
            int r10 = r10 + 1
            goto L_0x02e3
        L_0x030a:
            int r3 = r3 + 1
            goto L_0x02d7
        L_0x030d:
            r3 = r7[r6]
            int r3 = r3.mPathMotionArc
            int r9 = androidx.constraintlayout.motion.widget.Key.UNSET
            if (r3 == r9) goto L_0x0317
            r3 = 1
            goto L_0x0318
        L_0x0317:
            r3 = 0
        L_0x0318:
            java.lang.String[] r9 = r0.mAttributeNames
            int r9 = r9.length
            int r8 = r8 + r9
            boolean[] r9 = new boolean[r8]
            r10 = 1
        L_0x031f:
            if (r10 >= r2) goto L_0x032f
            r11 = r7[r10]
            int r12 = r10 + -1
            r12 = r7[r12]
            java.lang.String[] r13 = r0.mAttributeNames
            r11.different(r12, r9, r13, r3)
            int r10 = r10 + 1
            goto L_0x031f
        L_0x032f:
            r3 = 1
            r10 = 0
        L_0x0331:
            if (r3 >= r8) goto L_0x033c
            boolean r11 = r9[r3]
            if (r11 == 0) goto L_0x0339
            int r10 = r10 + 1
        L_0x0339:
            int r3 = r3 + 1
            goto L_0x0331
        L_0x033c:
            int[] r3 = new int[r10]
            r0.mInterpolateVariables = r3
            int r3 = java.lang.Math.max(r5, r10)
            double[] r10 = new double[r3]
            r0.mInterpolateData = r10
            double[] r3 = new double[r3]
            r0.mInterpolateVelocity = r3
            r3 = 1
            r10 = 0
        L_0x034e:
            if (r3 >= r8) goto L_0x035e
            boolean r11 = r9[r3]
            if (r11 == 0) goto L_0x035b
            int[] r11 = r0.mInterpolateVariables
            int r12 = r10 + 1
            r11[r10] = r3
            r10 = r12
        L_0x035b:
            int r3 = r3 + 1
            goto L_0x034e
        L_0x035e:
            int[] r3 = r0.mInterpolateVariables
            int r3 = r3.length
            int[] r8 = new int[r5]
            r9 = 1
            r8[r9] = r3
            r8[r6] = r2
            java.lang.Object r3 = java.lang.reflect.Array.newInstance(r1, r8)
            double[][] r3 = (double[][]) r3
            double[] r8 = new double[r2]
            r9 = 0
        L_0x0371:
            if (r9 >= r2) goto L_0x0386
            r10 = r7[r9]
            r11 = r3[r9]
            int[] r12 = r0.mInterpolateVariables
            r10.fillStandard(r11, r12)
            r10 = r7[r9]
            float r10 = r10.time
            double r10 = (double) r10
            r8[r9] = r10
            int r9 = r9 + 1
            goto L_0x0371
        L_0x0386:
            r9 = 0
        L_0x0387:
            int[] r10 = r0.mInterpolateVariables
            int r11 = r10.length
            if (r9 >= r11) goto L_0x03c8
            r10 = r10[r9]
            java.lang.String[] r11 = androidx.constraintlayout.motion.widget.MotionPaths.names
            int r11 = r11.length
            if (r10 >= r11) goto L_0x03c5
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String[] r11 = androidx.constraintlayout.motion.widget.MotionPaths.names
            int[] r12 = r0.mInterpolateVariables
            r12 = r12[r9]
            r11 = r11[r12]
            r10.append(r11)
            java.lang.String r11 = " ["
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            r11 = 0
        L_0x03ad:
            if (r11 >= r2) goto L_0x03c5
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            r12.append(r10)
            r10 = r3[r11]
            r13 = r10[r9]
            r12.append(r13)
            java.lang.String r10 = r12.toString()
            int r11 = r11 + 1
            goto L_0x03ad
        L_0x03c5:
            int r9 = r9 + 1
            goto L_0x0387
        L_0x03c8:
            java.lang.String[] r9 = r0.mAttributeNames
            int r9 = r9.length
            r10 = 1
            int r9 = r9 + r10
            androidx.constraintlayout.core.motion.utils.CurveFit[] r9 = new androidx.constraintlayout.core.motion.utils.CurveFit[r9]
            r0.mSpline = r9
            r9 = 0
        L_0x03d2:
            java.lang.String[] r10 = r0.mAttributeNames
            int r11 = r10.length
            if (r9 >= r11) goto L_0x042e
            r10 = r10[r9]
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
        L_0x03dd:
            if (r11 >= r2) goto L_0x0415
            r15 = r7[r11]
            boolean r15 = r15.hasCustomData(r10)
            if (r15 == 0) goto L_0x0410
            if (r14 != 0) goto L_0x03ff
            double[] r12 = new double[r2]
            r14 = r7[r11]
            int r14 = r14.getCustomDataCount(r10)
            int[] r15 = new int[r5]
            r16 = 1
            r15[r16] = r14
            r15[r6] = r2
            java.lang.Object r14 = java.lang.reflect.Array.newInstance(r1, r15)
            double[][] r14 = (double[][]) r14
        L_0x03ff:
            r15 = r7[r11]
            float r15 = r15.time
            double r5 = (double) r15
            r12[r13] = r5
            r5 = r7[r11]
            r6 = r14[r13]
            r15 = 0
            r5.getCustomData(r10, r6, r15)
            int r13 = r13 + 1
        L_0x0410:
            int r11 = r11 + 1
            r5 = 2
            r6 = 0
            goto L_0x03dd
        L_0x0415:
            double[] r5 = java.util.Arrays.copyOf(r12, r13)
            java.lang.Object[] r6 = java.util.Arrays.copyOf(r14, r13)
            double[][] r6 = (double[][]) r6
            androidx.constraintlayout.core.motion.utils.CurveFit[] r10 = r0.mSpline
            int r9 = r9 + 1
            int r11 = r0.mCurveFitType
            androidx.constraintlayout.core.motion.utils.CurveFit r5 = androidx.constraintlayout.core.motion.utils.CurveFit.get(r11, r5, r6)
            r10[r9] = r5
            r5 = 2
            r6 = 0
            goto L_0x03d2
        L_0x042e:
            androidx.constraintlayout.core.motion.utils.CurveFit[] r5 = r0.mSpline
            int r6 = r0.mCurveFitType
            androidx.constraintlayout.core.motion.utils.CurveFit r3 = androidx.constraintlayout.core.motion.utils.CurveFit.get(r6, r8, r3)
            r6 = 0
            r5[r6] = r3
            r3 = r7[r6]
            int r3 = r3.mPathMotionArc
            int r5 = androidx.constraintlayout.motion.widget.Key.UNSET
            if (r3 == r5) goto L_0x0480
            int[] r3 = new int[r2]
            double[] r5 = new double[r2]
            r8 = 2
            int[] r9 = new int[r8]
            r10 = 1
            r9[r10] = r8
            r9[r6] = r2
            java.lang.Object r1 = java.lang.reflect.Array.newInstance(r1, r9)
            double[][] r1 = (double[][]) r1
            r15 = 0
        L_0x0454:
            if (r15 >= r2) goto L_0x047a
            r6 = r7[r15]
            int r6 = r6.mPathMotionArc
            r3[r15] = r6
            r6 = r7[r15]
            float r6 = r6.time
            double r8 = (double) r6
            r5[r15] = r8
            r6 = r1[r15]
            r8 = r7[r15]
            float r8 = r8.x
            double r8 = (double) r8
            r10 = 0
            r6[r10] = r8
            r6 = r1[r15]
            r8 = r7[r15]
            float r8 = r8.y
            double r8 = (double) r8
            r11 = 1
            r6[r11] = r8
            int r15 = r15 + 1
            goto L_0x0454
        L_0x047a:
            androidx.constraintlayout.core.motion.utils.CurveFit r1 = androidx.constraintlayout.core.motion.utils.CurveFit.getArc(r3, r5, r1)
            r0.mArcSpline = r1
        L_0x0480:
            r1 = 2143289344(0x7fc00000, float:NaN)
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            r0.mCycleMap = r2
            java.util.ArrayList<androidx.constraintlayout.motion.widget.Key> r2 = r0.mKeyList
            if (r2 == 0) goto L_0x04f5
            java.util.Iterator r2 = r4.iterator()
        L_0x0491:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x04bd
            java.lang.Object r3 = r2.next()
            java.lang.String r3 = (java.lang.String) r3
            androidx.constraintlayout.motion.utils.ViewOscillator r4 = androidx.constraintlayout.motion.utils.ViewOscillator.makeSpline(r3)
            if (r4 != 0) goto L_0x04a4
            goto L_0x0491
        L_0x04a4:
            boolean r5 = r4.variesByPath()
            if (r5 == 0) goto L_0x04b4
            boolean r5 = java.lang.Float.isNaN(r1)
            if (r5 == 0) goto L_0x04b4
            float r1 = r17.getPreCycleDistance()
        L_0x04b4:
            r4.setType(r3)
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewOscillator> r5 = r0.mCycleMap
            r5.put(r3, r4)
            goto L_0x0491
        L_0x04bd:
            java.util.ArrayList<androidx.constraintlayout.motion.widget.Key> r2 = r0.mKeyList
            java.util.Iterator r2 = r2.iterator()
        L_0x04c3:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x04db
            java.lang.Object r3 = r2.next()
            androidx.constraintlayout.motion.widget.Key r3 = (androidx.constraintlayout.motion.widget.Key) r3
            boolean r4 = r3 instanceof androidx.constraintlayout.motion.widget.KeyCycle
            if (r4 == 0) goto L_0x04c3
            androidx.constraintlayout.motion.widget.KeyCycle r3 = (androidx.constraintlayout.motion.widget.KeyCycle) r3
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewOscillator> r4 = r0.mCycleMap
            r3.addCycleValues(r4)
            goto L_0x04c3
        L_0x04db:
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewOscillator> r2 = r0.mCycleMap
            java.util.Collection r2 = r2.values()
            java.util.Iterator r2 = r2.iterator()
        L_0x04e5:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x04f5
            java.lang.Object r3 = r2.next()
            androidx.constraintlayout.motion.utils.ViewOscillator r3 = (androidx.constraintlayout.motion.utils.ViewOscillator) r3
            r3.setup(r1)
            goto L_0x04e5
        L_0x04f5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionController.setup(int, int, float, long):void");
    }

    public String toString() {
        return " start: x: " + this.mStartMotionPath.x + " y: " + this.mStartMotionPath.y + " end: x: " + this.mEndMotionPath.x + " y: " + this.mEndMotionPath.y;
    }

    private void readView(MotionPaths motionPaths) {
        motionPaths.setBounds((float) ((int) this.mView.getX()), (float) ((int) this.mView.getY()), (float) this.mView.getWidth(), (float) this.mView.getHeight());
    }

    public void setView(View view) {
        this.mView = view;
        this.mId = view.getId();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.LayoutParams) {
            this.mConstraintTag = ((ConstraintLayout.LayoutParams) layoutParams).getConstraintTag();
        }
    }

    public View getView() {
        return this.mView;
    }

    /* access modifiers changed from: package-private */
    public void setStartCurrentState(View view) {
        this.mStartMotionPath.time = 0.0f;
        this.mStartMotionPath.position = 0.0f;
        this.mStartMotionPath.setBounds(view.getX(), view.getY(), (float) view.getWidth(), (float) view.getHeight());
        this.mStartPoint.setState(view);
    }

    public void setStartState(ViewState viewState, View view, int i, int i2, int i3) {
        this.mStartMotionPath.time = 0.0f;
        this.mStartMotionPath.position = 0.0f;
        Rect rect = new Rect();
        if (i == 1) {
            int i4 = viewState.left + viewState.right;
            rect.left = ((viewState.top + viewState.bottom) - viewState.width()) / 2;
            rect.top = i2 - ((i4 + viewState.height()) / 2);
            rect.right = rect.left + viewState.width();
            rect.bottom = rect.top + viewState.height();
        } else if (i == 2) {
            int i5 = viewState.left + viewState.right;
            rect.left = i3 - (((viewState.top + viewState.bottom) + viewState.width()) / 2);
            rect.top = (i5 - viewState.height()) / 2;
            rect.right = rect.left + viewState.width();
            rect.bottom = rect.top + viewState.height();
        }
        this.mStartMotionPath.setBounds((float) rect.left, (float) rect.top, (float) rect.width(), (float) rect.height());
        this.mStartPoint.setState(rect, view, i, viewState.rotation);
    }

    /* access modifiers changed from: package-private */
    public void rotate(Rect rect, Rect rect2, int i, int i2, int i3) {
        if (i == 1) {
            int i4 = rect.left + rect.right;
            rect2.left = ((rect.top + rect.bottom) - rect.width()) / 2;
            rect2.top = i3 - ((i4 + rect.height()) / 2);
            rect2.right = rect2.left + rect.width();
            rect2.bottom = rect2.top + rect.height();
        } else if (i == 2) {
            int i5 = rect.left + rect.right;
            rect2.left = i2 - (((rect.top + rect.bottom) + rect.width()) / 2);
            rect2.top = (i5 - rect.height()) / 2;
            rect2.right = rect2.left + rect.width();
            rect2.bottom = rect2.top + rect.height();
        } else if (i == 3) {
            int i6 = rect.left + rect.right;
            int i7 = rect.top;
            int i8 = rect.bottom;
            rect2.left = ((rect.height() / 2) + rect.top) - (i6 / 2);
            rect2.top = i3 - ((i6 + rect.height()) / 2);
            rect2.right = rect2.left + rect.width();
            rect2.bottom = rect2.top + rect.height();
        } else if (i == 4) {
            int i9 = rect.left + rect.right;
            rect2.left = i2 - (((rect.bottom + rect.top) + rect.width()) / 2);
            rect2.top = (i9 - rect.height()) / 2;
            rect2.right = rect2.left + rect.width();
            rect2.bottom = rect2.top + rect.height();
        }
    }

    /* access modifiers changed from: package-private */
    public void setStartState(Rect rect, ConstraintSet constraintSet, int i, int i2) {
        int i3 = constraintSet.mRotate;
        if (i3 != 0) {
            rotate(rect, this.mTempRect, i3, i, i2);
        }
        this.mStartMotionPath.time = 0.0f;
        this.mStartMotionPath.position = 0.0f;
        readView(this.mStartMotionPath);
        this.mStartMotionPath.setBounds((float) rect.left, (float) rect.top, (float) rect.width(), (float) rect.height());
        ConstraintSet.Constraint parameters = constraintSet.getParameters(this.mId);
        this.mStartMotionPath.applyParameters(parameters);
        this.mMotionStagger = parameters.motion.mMotionStagger;
        this.mStartPoint.setState(rect, constraintSet, i3, this.mId);
        this.mTransformPivotTarget = parameters.transform.transformPivotTarget;
        this.mQuantizeMotionSteps = parameters.motion.mQuantizeMotionSteps;
        this.mQuantizeMotionPhase = parameters.motion.mQuantizeMotionPhase;
        this.mQuantizeMotionInterpolator = getInterpolator(this.mView.getContext(), parameters.motion.mQuantizeInterpolatorType, parameters.motion.mQuantizeInterpolatorString, parameters.motion.mQuantizeInterpolatorID);
    }

    private static Interpolator getInterpolator(Context context, int i, String str, int i2) {
        if (i == -2) {
            return AnimationUtils.loadInterpolator(context, i2);
        }
        if (i == -1) {
            final Easing interpolator = Easing.getInterpolator(str);
            return new Interpolator() {
                public float getInterpolation(float f) {
                    return (float) Easing.this.get((double) f);
                }
            };
        } else if (i == 0) {
            return new AccelerateDecelerateInterpolator();
        } else {
            if (i == 1) {
                return new AccelerateInterpolator();
            }
            if (i == 2) {
                return new DecelerateInterpolator();
            }
            if (i == 4) {
                return new BounceInterpolator();
            }
            if (i != 5) {
                return null;
            }
            return new OvershootInterpolator();
        }
    }

    /* access modifiers changed from: package-private */
    public void setEndState(Rect rect, ConstraintSet constraintSet, int i, int i2) {
        int i3 = constraintSet.mRotate;
        if (i3 != 0) {
            rotate(rect, this.mTempRect, i3, i, i2);
            rect = this.mTempRect;
        }
        this.mEndMotionPath.time = 1.0f;
        this.mEndMotionPath.position = 1.0f;
        readView(this.mEndMotionPath);
        this.mEndMotionPath.setBounds((float) rect.left, (float) rect.top, (float) rect.width(), (float) rect.height());
        this.mEndMotionPath.applyParameters(constraintSet.getParameters(this.mId));
        this.mEndPoint.setState(rect, constraintSet, i3, this.mId);
    }

    /* access modifiers changed from: package-private */
    public void setBothStates(View view) {
        this.mStartMotionPath.time = 0.0f;
        this.mStartMotionPath.position = 0.0f;
        this.mNoMovement = true;
        this.mStartMotionPath.setBounds(view.getX(), view.getY(), (float) view.getWidth(), (float) view.getHeight());
        this.mEndMotionPath.setBounds(view.getX(), view.getY(), (float) view.getWidth(), (float) view.getHeight());
        this.mStartPoint.setState(view);
        this.mEndPoint.setState(view);
    }

    private float getAdjustedPosition(float f, float[] fArr) {
        float f2 = 0.0f;
        float f3 = 1.0f;
        if (fArr != null) {
            fArr[0] = 1.0f;
        } else {
            float f4 = this.mStaggerScale;
            if (((double) f4) != 1.0d) {
                float f5 = this.mStaggerOffset;
                if (f < f5) {
                    f = 0.0f;
                }
                if (f > f5 && ((double) f) < 1.0d) {
                    f = Math.min((f - f5) * f4, 1.0f);
                }
            }
        }
        Easing easing = this.mStartMotionPath.mKeyFrameEasing;
        float f6 = Float.NaN;
        Iterator<MotionPaths> it = this.mMotionPaths.iterator();
        while (it.hasNext()) {
            MotionPaths next = it.next();
            if (next.mKeyFrameEasing != null) {
                if (next.time < f) {
                    Easing easing2 = next.mKeyFrameEasing;
                    easing = easing2;
                    f2 = next.time;
                } else if (Float.isNaN(f6)) {
                    f6 = next.time;
                }
            }
        }
        if (easing != null) {
            if (!Float.isNaN(f6)) {
                f3 = f6;
            }
            float f7 = f3 - f2;
            double d = (double) ((f - f2) / f7);
            f = (((float) easing.get(d)) * f7) + f2;
            if (fArr != null) {
                fArr[0] = (float) easing.getDiff(d);
            }
        }
        return f;
    }

    /* access modifiers changed from: package-private */
    public void endTrigger(boolean z) {
        if ("button".equals(Debug.getName(this.mView)) && this.mKeyTriggers != null) {
            int i = 0;
            while (true) {
                KeyTrigger[] keyTriggerArr = this.mKeyTriggers;
                if (i < keyTriggerArr.length) {
                    keyTriggerArr[i].conditionallyFire(z ? -100.0f : 100.0f, this.mView);
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v66, resolved type: androidx.constraintlayout.motion.utils.ViewTimeCycle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v10, resolved type: androidx.constraintlayout.motion.utils.ViewTimeCycle$PathRotate} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean interpolate(android.view.View r21, float r22, long r23, androidx.constraintlayout.core.motion.utils.KeyCache r25) {
        /*
            r20 = this;
            r0 = r20
            r11 = r21
            r1 = 0
            r2 = r22
            float r2 = r0.getAdjustedPosition(r2, r1)
            int r3 = r0.mQuantizeMotionSteps
            int r4 = androidx.constraintlayout.motion.widget.Key.UNSET
            r13 = 1065353216(0x3f800000, float:1.0)
            if (r3 == r4) goto L_0x0047
            int r3 = r0.mQuantizeMotionSteps
            float r3 = (float) r3
            float r3 = r13 / r3
            float r4 = r2 / r3
            double r4 = (double) r4
            double r4 = java.lang.Math.floor(r4)
            float r4 = (float) r4
            float r4 = r4 * r3
            float r2 = r2 % r3
            float r2 = r2 / r3
            float r5 = r0.mQuantizeMotionPhase
            boolean r5 = java.lang.Float.isNaN(r5)
            if (r5 != 0) goto L_0x0030
            float r5 = r0.mQuantizeMotionPhase
            float r2 = r2 + r5
            float r2 = r2 % r13
        L_0x0030:
            android.view.animation.Interpolator r5 = r0.mQuantizeMotionInterpolator
            if (r5 == 0) goto L_0x0039
            float r2 = r5.getInterpolation(r2)
            goto L_0x0044
        L_0x0039:
            double r5 = (double) r2
            r7 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            int r2 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r2 <= 0) goto L_0x0043
            r2 = 1065353216(0x3f800000, float:1.0)
            goto L_0x0044
        L_0x0043:
            r2 = 0
        L_0x0044:
            float r2 = r2 * r3
            float r2 = r2 + r4
        L_0x0047:
            r14 = r2
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewSpline> r2 = r0.mAttributesMap
            if (r2 == 0) goto L_0x0064
            java.util.Collection r2 = r2.values()
            java.util.Iterator r2 = r2.iterator()
        L_0x0054:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0064
            java.lang.Object r3 = r2.next()
            androidx.constraintlayout.motion.utils.ViewSpline r3 = (androidx.constraintlayout.motion.utils.ViewSpline) r3
            r3.setProperty(r11, r14)
            goto L_0x0054
        L_0x0064:
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewTimeCycle> r2 = r0.mTimeCycleAttributesMap
            r15 = 0
            if (r2 == 0) goto L_0x0098
            java.util.Collection r2 = r2.values()
            java.util.Iterator r7 = r2.iterator()
            r8 = r1
            r9 = 0
        L_0x0073:
            boolean r1 = r7.hasNext()
            if (r1 == 0) goto L_0x0094
            java.lang.Object r1 = r7.next()
            androidx.constraintlayout.motion.utils.ViewTimeCycle r1 = (androidx.constraintlayout.motion.utils.ViewTimeCycle) r1
            boolean r2 = r1 instanceof androidx.constraintlayout.motion.utils.ViewTimeCycle.PathRotate
            if (r2 == 0) goto L_0x0087
            r8 = r1
            androidx.constraintlayout.motion.utils.ViewTimeCycle$PathRotate r8 = (androidx.constraintlayout.motion.utils.ViewTimeCycle.PathRotate) r8
            goto L_0x0073
        L_0x0087:
            r2 = r21
            r3 = r14
            r4 = r23
            r6 = r25
            boolean r1 = r1.setProperty(r2, r3, r4, r6)
            r9 = r9 | r1
            goto L_0x0073
        L_0x0094:
            r16 = r9
            r9 = r8
            goto L_0x009b
        L_0x0098:
            r9 = r1
            r16 = 0
        L_0x009b:
            androidx.constraintlayout.core.motion.utils.CurveFit[] r1 = r0.mSpline
            r10 = 1
            if (r1 == 0) goto L_0x01f9
            r1 = r1[r15]
            double r7 = (double) r14
            double[] r2 = r0.mInterpolateData
            r1.getPos((double) r7, (double[]) r2)
            androidx.constraintlayout.core.motion.utils.CurveFit[] r1 = r0.mSpline
            r1 = r1[r15]
            double[] r2 = r0.mInterpolateVelocity
            r1.getSlope((double) r7, (double[]) r2)
            androidx.constraintlayout.core.motion.utils.CurveFit r1 = r0.mArcSpline
            if (r1 == 0) goto L_0x00c4
            double[] r2 = r0.mInterpolateData
            int r3 = r2.length
            if (r3 <= 0) goto L_0x00c4
            r1.getPos((double) r7, (double[]) r2)
            androidx.constraintlayout.core.motion.utils.CurveFit r1 = r0.mArcSpline
            double[] r2 = r0.mInterpolateVelocity
            r1.getSlope((double) r7, (double[]) r2)
        L_0x00c4:
            boolean r1 = r0.mNoMovement
            if (r1 != 0) goto L_0x00e4
            androidx.constraintlayout.motion.widget.MotionPaths r1 = r0.mStartMotionPath
            int[] r4 = r0.mInterpolateVariables
            double[] r5 = r0.mInterpolateData
            double[] r6 = r0.mInterpolateVelocity
            r17 = 0
            boolean r3 = r0.mForceMeasure
            r2 = r14
            r18 = r3
            r3 = r21
            r12 = r7
            r7 = r17
            r8 = r18
            r1.setView(r2, r3, r4, r5, r6, r7, r8)
            r0.mForceMeasure = r15
            goto L_0x00e5
        L_0x00e4:
            r12 = r7
        L_0x00e5:
            int r1 = r0.mTransformPivotTarget
            int r2 = androidx.constraintlayout.motion.widget.Key.UNSET
            if (r1 == r2) goto L_0x0147
            android.view.View r1 = r0.mTransformPivotView
            if (r1 != 0) goto L_0x00fd
            android.view.ViewParent r1 = r21.getParent()
            android.view.View r1 = (android.view.View) r1
            int r2 = r0.mTransformPivotTarget
            android.view.View r1 = r1.findViewById(r2)
            r0.mTransformPivotView = r1
        L_0x00fd:
            android.view.View r1 = r0.mTransformPivotView
            if (r1 == 0) goto L_0x0147
            int r1 = r1.getTop()
            android.view.View r2 = r0.mTransformPivotView
            int r2 = r2.getBottom()
            int r1 = r1 + r2
            float r1 = (float) r1
            r2 = 1073741824(0x40000000, float:2.0)
            float r1 = r1 / r2
            android.view.View r3 = r0.mTransformPivotView
            int r3 = r3.getLeft()
            android.view.View r4 = r0.mTransformPivotView
            int r4 = r4.getRight()
            int r3 = r3 + r4
            float r3 = (float) r3
            float r3 = r3 / r2
            int r2 = r21.getRight()
            int r4 = r21.getLeft()
            int r2 = r2 - r4
            if (r2 <= 0) goto L_0x0147
            int r2 = r21.getBottom()
            int r4 = r21.getTop()
            int r2 = r2 - r4
            if (r2 <= 0) goto L_0x0147
            int r2 = r21.getLeft()
            float r2 = (float) r2
            float r3 = r3 - r2
            int r2 = r21.getTop()
            float r2 = (float) r2
            float r1 = r1 - r2
            r11.setPivotX(r3)
            r11.setPivotY(r1)
        L_0x0147:
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewSpline> r1 = r0.mAttributesMap
            if (r1 == 0) goto L_0x0175
            java.util.Collection r1 = r1.values()
            java.util.Iterator r8 = r1.iterator()
        L_0x0153:
            boolean r1 = r8.hasNext()
            if (r1 == 0) goto L_0x0175
            java.lang.Object r1 = r8.next()
            androidx.constraintlayout.core.motion.utils.SplineSet r1 = (androidx.constraintlayout.core.motion.utils.SplineSet) r1
            boolean r2 = r1 instanceof androidx.constraintlayout.motion.utils.ViewSpline.PathRotate
            if (r2 == 0) goto L_0x0153
            double[] r2 = r0.mInterpolateVelocity
            int r3 = r2.length
            if (r3 <= r10) goto L_0x0153
            androidx.constraintlayout.motion.utils.ViewSpline$PathRotate r1 = (androidx.constraintlayout.motion.utils.ViewSpline.PathRotate) r1
            r4 = r2[r15]
            r6 = r2[r10]
            r2 = r21
            r3 = r14
            r1.setPathRotate(r2, r3, r4, r6)
            goto L_0x0153
        L_0x0175:
            if (r9 == 0) goto L_0x0192
            double[] r1 = r0.mInterpolateVelocity
            r7 = r1[r15]
            r17 = r1[r10]
            r1 = r9
            r2 = r21
            r3 = r25
            r4 = r14
            r5 = r23
            r19 = 1
            r9 = r17
            boolean r1 = r1.setPathRotate(r2, r3, r4, r5, r7, r9)
            r1 = r16 | r1
            r16 = r1
            goto L_0x0194
        L_0x0192:
            r19 = 1
        L_0x0194:
            r10 = 1
        L_0x0195:
            androidx.constraintlayout.core.motion.utils.CurveFit[] r1 = r0.mSpline
            int r2 = r1.length
            if (r10 >= r2) goto L_0x01b9
            r1 = r1[r10]
            float[] r2 = r0.mValuesBuff
            r1.getPos((double) r12, (float[]) r2)
            androidx.constraintlayout.motion.widget.MotionPaths r1 = r0.mStartMotionPath
            java.util.LinkedHashMap<java.lang.String, androidx.constraintlayout.widget.ConstraintAttribute> r1 = r1.attributes
            java.lang.String[] r2 = r0.mAttributeNames
            int r3 = r10 + -1
            r2 = r2[r3]
            java.lang.Object r1 = r1.get(r2)
            androidx.constraintlayout.widget.ConstraintAttribute r1 = (androidx.constraintlayout.widget.ConstraintAttribute) r1
            float[] r2 = r0.mValuesBuff
            androidx.constraintlayout.motion.utils.CustomSupport.setInterpolatedValue(r1, r11, r2)
            int r10 = r10 + 1
            goto L_0x0195
        L_0x01b9:
            androidx.constraintlayout.motion.widget.MotionConstrainedPoint r1 = r0.mStartPoint
            int r1 = r1.mVisibilityMode
            if (r1 != 0) goto L_0x01e7
            r1 = 0
            int r1 = (r14 > r1 ? 1 : (r14 == r1 ? 0 : -1))
            if (r1 > 0) goto L_0x01cc
            androidx.constraintlayout.motion.widget.MotionConstrainedPoint r1 = r0.mStartPoint
            int r1 = r1.visibility
            r11.setVisibility(r1)
            goto L_0x01e7
        L_0x01cc:
            r1 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r14 > r1 ? 1 : (r14 == r1 ? 0 : -1))
            if (r1 < 0) goto L_0x01da
            androidx.constraintlayout.motion.widget.MotionConstrainedPoint r1 = r0.mEndPoint
            int r1 = r1.visibility
            r11.setVisibility(r1)
            goto L_0x01e7
        L_0x01da:
            androidx.constraintlayout.motion.widget.MotionConstrainedPoint r1 = r0.mEndPoint
            int r1 = r1.visibility
            androidx.constraintlayout.motion.widget.MotionConstrainedPoint r2 = r0.mStartPoint
            int r2 = r2.visibility
            if (r1 == r2) goto L_0x01e7
            r11.setVisibility(r15)
        L_0x01e7:
            androidx.constraintlayout.motion.widget.KeyTrigger[] r1 = r0.mKeyTriggers
            if (r1 == 0) goto L_0x0277
            r1 = 0
        L_0x01ec:
            androidx.constraintlayout.motion.widget.KeyTrigger[] r2 = r0.mKeyTriggers
            int r3 = r2.length
            if (r1 >= r3) goto L_0x0277
            r2 = r2[r1]
            r2.conditionallyFire(r14, r11)
            int r1 = r1 + 1
            goto L_0x01ec
        L_0x01f9:
            r19 = 1
            androidx.constraintlayout.motion.widget.MotionPaths r1 = r0.mStartMotionPath
            float r1 = r1.x
            androidx.constraintlayout.motion.widget.MotionPaths r2 = r0.mEndMotionPath
            float r2 = r2.x
            androidx.constraintlayout.motion.widget.MotionPaths r3 = r0.mStartMotionPath
            float r3 = r3.x
            float r2 = r2 - r3
            float r2 = r2 * r14
            float r1 = r1 + r2
            androidx.constraintlayout.motion.widget.MotionPaths r2 = r0.mStartMotionPath
            float r2 = r2.y
            androidx.constraintlayout.motion.widget.MotionPaths r3 = r0.mEndMotionPath
            float r3 = r3.y
            androidx.constraintlayout.motion.widget.MotionPaths r4 = r0.mStartMotionPath
            float r4 = r4.y
            float r3 = r3 - r4
            float r3 = r3 * r14
            float r2 = r2 + r3
            androidx.constraintlayout.motion.widget.MotionPaths r3 = r0.mStartMotionPath
            float r3 = r3.width
            androidx.constraintlayout.motion.widget.MotionPaths r4 = r0.mEndMotionPath
            float r4 = r4.width
            androidx.constraintlayout.motion.widget.MotionPaths r5 = r0.mStartMotionPath
            float r5 = r5.width
            float r4 = r4 - r5
            float r4 = r4 * r14
            float r3 = r3 + r4
            androidx.constraintlayout.motion.widget.MotionPaths r4 = r0.mStartMotionPath
            float r4 = r4.height
            androidx.constraintlayout.motion.widget.MotionPaths r5 = r0.mEndMotionPath
            float r5 = r5.height
            androidx.constraintlayout.motion.widget.MotionPaths r6 = r0.mStartMotionPath
            float r6 = r6.height
            float r5 = r5 - r6
            float r5 = r5 * r14
            float r4 = r4 + r5
            r5 = 1056964608(0x3f000000, float:0.5)
            float r1 = r1 + r5
            int r6 = (int) r1
            float r2 = r2 + r5
            int r5 = (int) r2
            float r1 = r1 + r3
            int r1 = (int) r1
            float r2 = r2 + r4
            int r2 = (int) r2
            int r3 = r1 - r6
            int r4 = r2 - r5
            androidx.constraintlayout.motion.widget.MotionPaths r7 = r0.mEndMotionPath
            float r7 = r7.width
            androidx.constraintlayout.motion.widget.MotionPaths r8 = r0.mStartMotionPath
            float r8 = r8.width
            int r7 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1))
            if (r7 != 0) goto L_0x0265
            androidx.constraintlayout.motion.widget.MotionPaths r7 = r0.mEndMotionPath
            float r7 = r7.height
            androidx.constraintlayout.motion.widget.MotionPaths r8 = r0.mStartMotionPath
            float r8 = r8.height
            int r7 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1))
            if (r7 != 0) goto L_0x0265
            boolean r7 = r0.mForceMeasure
            if (r7 == 0) goto L_0x0274
        L_0x0265:
            r7 = 1073741824(0x40000000, float:2.0)
            int r3 = android.view.View.MeasureSpec.makeMeasureSpec(r3, r7)
            int r4 = android.view.View.MeasureSpec.makeMeasureSpec(r4, r7)
            r11.measure(r3, r4)
            r0.mForceMeasure = r15
        L_0x0274:
            r11.layout(r6, r5, r1, r2)
        L_0x0277:
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewOscillator> r1 = r0.mCycleMap
            if (r1 == 0) goto L_0x02a6
            java.util.Collection r1 = r1.values()
            java.util.Iterator r8 = r1.iterator()
        L_0x0283:
            boolean r1 = r8.hasNext()
            if (r1 == 0) goto L_0x02a6
            java.lang.Object r1 = r8.next()
            androidx.constraintlayout.motion.utils.ViewOscillator r1 = (androidx.constraintlayout.motion.utils.ViewOscillator) r1
            boolean r2 = r1 instanceof androidx.constraintlayout.motion.utils.ViewOscillator.PathRotateSet
            if (r2 == 0) goto L_0x02a2
            androidx.constraintlayout.motion.utils.ViewOscillator$PathRotateSet r1 = (androidx.constraintlayout.motion.utils.ViewOscillator.PathRotateSet) r1
            double[] r2 = r0.mInterpolateVelocity
            r4 = r2[r15]
            r6 = r2[r19]
            r2 = r21
            r3 = r14
            r1.setPathRotate(r2, r3, r4, r6)
            goto L_0x0283
        L_0x02a2:
            r1.setProperty(r11, r14)
            goto L_0x0283
        L_0x02a6:
            return r16
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionController.interpolate(android.view.View, float, long, androidx.constraintlayout.core.motion.utils.KeyCache):boolean");
    }

    /* access modifiers changed from: package-private */
    public void getDpDt(float f, float f2, float f3, float[] fArr) {
        double[] dArr;
        float adjustedPosition = getAdjustedPosition(f, this.mVelocity);
        CurveFit[] curveFitArr = this.mSpline;
        int i = 0;
        if (curveFitArr != null) {
            double d = (double) adjustedPosition;
            curveFitArr[0].getSlope(d, this.mInterpolateVelocity);
            this.mSpline[0].getPos(d, this.mInterpolateData);
            float f4 = this.mVelocity[0];
            while (true) {
                dArr = this.mInterpolateVelocity;
                if (i >= dArr.length) {
                    break;
                }
                dArr[i] = dArr[i] * ((double) f4);
                i++;
            }
            CurveFit curveFit = this.mArcSpline;
            if (curveFit != null) {
                double[] dArr2 = this.mInterpolateData;
                if (dArr2.length > 0) {
                    curveFit.getPos(d, dArr2);
                    this.mArcSpline.getSlope(d, this.mInterpolateVelocity);
                    this.mStartMotionPath.setDpDt(f2, f3, fArr, this.mInterpolateVariables, this.mInterpolateVelocity, this.mInterpolateData);
                    return;
                }
                return;
            }
            this.mStartMotionPath.setDpDt(f2, f3, fArr, this.mInterpolateVariables, dArr, this.mInterpolateData);
            return;
        }
        float f5 = this.mEndMotionPath.x - this.mStartMotionPath.x;
        float f6 = this.mEndMotionPath.y - this.mStartMotionPath.y;
        float f7 = (this.mEndMotionPath.height - this.mStartMotionPath.height) + f6;
        fArr[0] = (f5 * (1.0f - f2)) + (((this.mEndMotionPath.width - this.mStartMotionPath.width) + f5) * f2);
        fArr[1] = (f6 * (1.0f - f3)) + (f7 * f3);
    }

    /* access modifiers changed from: package-private */
    public void getPostLayoutDvDp(float f, int i, int i2, float f2, float f3, float[] fArr) {
        float adjustedPosition = getAdjustedPosition(f, this.mVelocity);
        HashMap<String, ViewSpline> hashMap = this.mAttributesMap;
        ViewOscillator viewOscillator = null;
        SplineSet splineSet = hashMap == null ? null : hashMap.get("translationX");
        HashMap<String, ViewSpline> hashMap2 = this.mAttributesMap;
        SplineSet splineSet2 = hashMap2 == null ? null : hashMap2.get("translationY");
        HashMap<String, ViewSpline> hashMap3 = this.mAttributesMap;
        SplineSet splineSet3 = hashMap3 == null ? null : hashMap3.get(Key.ROTATION);
        HashMap<String, ViewSpline> hashMap4 = this.mAttributesMap;
        SplineSet splineSet4 = hashMap4 == null ? null : hashMap4.get("scaleX");
        HashMap<String, ViewSpline> hashMap5 = this.mAttributesMap;
        SplineSet splineSet5 = hashMap5 == null ? null : hashMap5.get("scaleY");
        HashMap<String, ViewOscillator> hashMap6 = this.mCycleMap;
        ViewOscillator viewOscillator2 = hashMap6 == null ? null : hashMap6.get("translationX");
        HashMap<String, ViewOscillator> hashMap7 = this.mCycleMap;
        ViewOscillator viewOscillator3 = hashMap7 == null ? null : hashMap7.get("translationY");
        HashMap<String, ViewOscillator> hashMap8 = this.mCycleMap;
        ViewOscillator viewOscillator4 = hashMap8 == null ? null : hashMap8.get(Key.ROTATION);
        HashMap<String, ViewOscillator> hashMap9 = this.mCycleMap;
        ViewOscillator viewOscillator5 = hashMap9 == null ? null : hashMap9.get("scaleX");
        HashMap<String, ViewOscillator> hashMap10 = this.mCycleMap;
        if (hashMap10 != null) {
            viewOscillator = hashMap10.get("scaleY");
        }
        VelocityMatrix velocityMatrix = new VelocityMatrix();
        velocityMatrix.clear();
        velocityMatrix.setRotationVelocity(splineSet3, adjustedPosition);
        velocityMatrix.setTranslationVelocity(splineSet, splineSet2, adjustedPosition);
        velocityMatrix.setScaleVelocity(splineSet4, splineSet5, adjustedPosition);
        velocityMatrix.setRotationVelocity((KeyCycleOscillator) viewOscillator4, adjustedPosition);
        velocityMatrix.setTranslationVelocity((KeyCycleOscillator) viewOscillator2, (KeyCycleOscillator) viewOscillator3, adjustedPosition);
        velocityMatrix.setScaleVelocity((KeyCycleOscillator) viewOscillator5, (KeyCycleOscillator) viewOscillator, adjustedPosition);
        CurveFit curveFit = this.mArcSpline;
        if (curveFit != null) {
            double[] dArr = this.mInterpolateData;
            if (dArr.length > 0) {
                double d = (double) adjustedPosition;
                curveFit.getPos(d, dArr);
                this.mArcSpline.getSlope(d, this.mInterpolateVelocity);
                this.mStartMotionPath.setDpDt(f2, f3, fArr, this.mInterpolateVariables, this.mInterpolateVelocity, this.mInterpolateData);
            }
            velocityMatrix.applyTransform(f2, f3, i, i2, fArr);
            return;
        }
        int i3 = 0;
        if (this.mSpline != null) {
            double adjustedPosition2 = (double) getAdjustedPosition(adjustedPosition, this.mVelocity);
            this.mSpline[0].getSlope(adjustedPosition2, this.mInterpolateVelocity);
            this.mSpline[0].getPos(adjustedPosition2, this.mInterpolateData);
            float f4 = this.mVelocity[0];
            while (true) {
                double[] dArr2 = this.mInterpolateVelocity;
                if (i3 < dArr2.length) {
                    dArr2[i3] = dArr2[i3] * ((double) f4);
                    i3++;
                } else {
                    float f5 = f2;
                    float f6 = f3;
                    this.mStartMotionPath.setDpDt(f5, f6, fArr, this.mInterpolateVariables, dArr2, this.mInterpolateData);
                    velocityMatrix.applyTransform(f5, f6, i, i2, fArr);
                    return;
                }
            }
        } else {
            float f7 = this.mEndMotionPath.x - this.mStartMotionPath.x;
            float f8 = this.mEndMotionPath.y - this.mStartMotionPath.y;
            ViewOscillator viewOscillator6 = viewOscillator;
            float f9 = (this.mEndMotionPath.height - this.mStartMotionPath.height) + f8;
            fArr[0] = (f7 * (1.0f - f2)) + (((this.mEndMotionPath.width - this.mStartMotionPath.width) + f7) * f2);
            fArr[1] = (f8 * (1.0f - f3)) + (f9 * f3);
            velocityMatrix.clear();
            velocityMatrix.setRotationVelocity(splineSet3, adjustedPosition);
            velocityMatrix.setTranslationVelocity(splineSet, splineSet2, adjustedPosition);
            velocityMatrix.setScaleVelocity(splineSet4, splineSet5, adjustedPosition);
            velocityMatrix.setRotationVelocity((KeyCycleOscillator) viewOscillator4, adjustedPosition);
            velocityMatrix.setTranslationVelocity((KeyCycleOscillator) viewOscillator2, (KeyCycleOscillator) viewOscillator3, adjustedPosition);
            velocityMatrix.setScaleVelocity((KeyCycleOscillator) viewOscillator5, (KeyCycleOscillator) viewOscillator6, adjustedPosition);
            velocityMatrix.applyTransform(f2, f3, i, i2, fArr);
        }
    }

    public int getDrawPath() {
        int i = this.mStartMotionPath.mDrawPath;
        Iterator<MotionPaths> it = this.mMotionPaths.iterator();
        while (it.hasNext()) {
            i = Math.max(i, it.next().mDrawPath);
        }
        return Math.max(i, this.mEndMotionPath.mDrawPath);
    }

    public void setDrawPath(int i) {
        this.mStartMotionPath.mDrawPath = i;
    }

    /* access modifiers changed from: package-private */
    public String name() {
        return this.mView.getContext().getResources().getResourceEntryName(this.mView.getId());
    }

    /* access modifiers changed from: package-private */
    public void positionKeyframe(View view, KeyPositionBase keyPositionBase, float f, float f2, String[] strArr, float[] fArr) {
        RectF rectF = new RectF();
        rectF.left = this.mStartMotionPath.x;
        rectF.top = this.mStartMotionPath.y;
        rectF.right = rectF.left + this.mStartMotionPath.width;
        rectF.bottom = rectF.top + this.mStartMotionPath.height;
        RectF rectF2 = new RectF();
        rectF2.left = this.mEndMotionPath.x;
        rectF2.top = this.mEndMotionPath.y;
        rectF2.right = rectF2.left + this.mEndMotionPath.width;
        rectF2.bottom = rectF2.top + this.mEndMotionPath.height;
        keyPositionBase.positionAttributes(view, rectF, rectF2, f, f2, strArr, fArr);
    }

    public int getKeyFramePositions(int[] iArr, float[] fArr) {
        Iterator<Key> it = this.mKeyList.iterator();
        int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            Key next = it.next();
            iArr[i] = next.mFramePosition + (next.mType * 1000);
            double d = (double) (((float) next.mFramePosition) / 100.0f);
            this.mSpline[0].getPos(d, this.mInterpolateData);
            this.mStartMotionPath.getCenter(d, this.mInterpolateVariables, this.mInterpolateData, fArr, i2);
            i2 += 2;
            i++;
        }
        return i;
    }

    public int getKeyFrameInfo(int i, int[] iArr) {
        int i2 = i;
        float[] fArr = new float[2];
        Iterator<Key> it = this.mKeyList.iterator();
        int i3 = 0;
        int i4 = 0;
        while (it.hasNext()) {
            Key next = it.next();
            if (next.mType == i2 || i2 != -1) {
                iArr[i4] = 0;
                int i5 = i4 + 1;
                iArr[i5] = next.mType;
                int i6 = i5 + 1;
                iArr[i6] = next.mFramePosition;
                double d = (double) (((float) next.mFramePosition) / 100.0f);
                this.mSpline[0].getPos(d, this.mInterpolateData);
                this.mStartMotionPath.getCenter(d, this.mInterpolateVariables, this.mInterpolateData, fArr, 0);
                int i7 = i6 + 1;
                iArr[i7] = Float.floatToIntBits(fArr[0]);
                int i8 = i7 + 1;
                iArr[i8] = Float.floatToIntBits(fArr[1]);
                if (next instanceof KeyPosition) {
                    KeyPosition keyPosition = (KeyPosition) next;
                    int i9 = i8 + 1;
                    iArr[i9] = keyPosition.mPositionType;
                    int i10 = i9 + 1;
                    iArr[i10] = Float.floatToIntBits(keyPosition.mPercentX);
                    i8 = i10 + 1;
                    iArr[i8] = Float.floatToIntBits(keyPosition.mPercentY);
                }
                int i11 = i8 + 1;
                iArr[i4] = i11 - i4;
                i3++;
                i4 = i11;
            }
        }
        return i3;
    }
}
