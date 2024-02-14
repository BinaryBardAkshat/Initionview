package androidx.constraintlayout.core.motion;

import androidx.constraintlayout.core.motion.key.MotionKey;
import androidx.constraintlayout.core.motion.key.MotionKeyPosition;
import androidx.constraintlayout.core.motion.key.MotionKeyTrigger;
import androidx.constraintlayout.core.motion.utils.CurveFit;
import androidx.constraintlayout.core.motion.utils.DifferentialInterpolator;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.motion.utils.FloatRect;
import androidx.constraintlayout.core.motion.utils.KeyCache;
import androidx.constraintlayout.core.motion.utils.KeyCycleOscillator;
import androidx.constraintlayout.core.motion.utils.Rect;
import androidx.constraintlayout.core.motion.utils.SplineSet;
import androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.motion.utils.Utils;
import androidx.constraintlayout.core.motion.utils.VelocityMatrix;
import androidx.constraintlayout.core.motion.utils.ViewState;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class Motion implements TypedValues {
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
    private HashMap<String, SplineSet> mAttributesMap;
    String mConstraintTag;
    float mCurrentCenterX;
    float mCurrentCenterY;
    private int mCurveFitType = -1;
    private HashMap<String, KeyCycleOscillator> mCycleMap;
    private MotionPaths mEndMotionPath = new MotionPaths();
    private MotionConstrainedPoint mEndPoint = new MotionConstrainedPoint();
    int mId;
    private double[] mInterpolateData;
    private int[] mInterpolateVariables;
    private double[] mInterpolateVelocity;
    private ArrayList<MotionKey> mKeyList = new ArrayList<>();
    private MotionKeyTrigger[] mKeyTriggers;
    private ArrayList<MotionPaths> mMotionPaths = new ArrayList<>();
    float mMotionStagger = Float.NaN;
    private boolean mNoMovement = false;
    private int mPathMotionArc = -1;
    private DifferentialInterpolator mQuantizeMotionInterpolator = null;
    private float mQuantizeMotionPhase = Float.NaN;
    private int mQuantizeMotionSteps = -1;
    private CurveFit[] mSpline;
    float mStaggerOffset = 0.0f;
    float mStaggerScale = 1.0f;
    private MotionPaths mStartMotionPath = new MotionPaths();
    private MotionConstrainedPoint mStartPoint = new MotionConstrainedPoint();
    Rect mTempRect = new Rect();
    private HashMap<String, TimeCycleSplineSet> mTimeCycleAttributesMap;
    private int mTransformPivotTarget = -1;
    private MotionWidget mTransformPivotView = null;
    private float[] mValuesBuff = new float[4];
    private float[] mVelocity = new float[1];
    MotionWidget mView;

    /* access modifiers changed from: package-private */
    public void endTrigger(boolean z) {
    }

    public int getId(String str) {
        return 0;
    }

    public boolean setValue(int i, float f) {
        return false;
    }

    public boolean setValue(int i, boolean z) {
        return false;
    }

    public int getTransformPivotTarget() {
        return this.mTransformPivotTarget;
    }

    public void setTransformPivotTarget(int i) {
        this.mTransformPivotTarget = i;
        this.mTransformPivotView = null;
    }

    public MotionPaths getKeyFrame(int i) {
        return this.mMotionPaths.get(i);
    }

    public Motion(MotionWidget motionWidget) {
        setView(motionWidget);
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

    public void setupRelative(Motion motion) {
        this.mStartMotionPath.setupRelative(motion, motion.mStartMotionPath);
        this.mEndMotionPath.setupRelative(motion, motion.mEndMotionPath);
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

    public void buildPath(float[] fArr, int i) {
        int i2 = i;
        float f = 1.0f;
        float f2 = 1.0f / ((float) (i2 - 1));
        HashMap<String, SplineSet> hashMap = this.mAttributesMap;
        KeyCycleOscillator keyCycleOscillator = null;
        SplineSet splineSet = hashMap == null ? null : hashMap.get("translationX");
        HashMap<String, SplineSet> hashMap2 = this.mAttributesMap;
        SplineSet splineSet2 = hashMap2 == null ? null : hashMap2.get("translationY");
        HashMap<String, KeyCycleOscillator> hashMap3 = this.mCycleMap;
        KeyCycleOscillator keyCycleOscillator2 = hashMap3 == null ? null : hashMap3.get("translationX");
        HashMap<String, KeyCycleOscillator> hashMap4 = this.mCycleMap;
        if (hashMap4 != null) {
            keyCycleOscillator = hashMap4.get("translationY");
        }
        KeyCycleOscillator keyCycleOscillator3 = keyCycleOscillator;
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
            if (keyCycleOscillator2 != null) {
                fArr[i4] = fArr[i4] + keyCycleOscillator2.get(f10);
            } else if (splineSet != null) {
                fArr[i4] = fArr[i4] + splineSet.get(f10);
            }
            if (keyCycleOscillator3 != null) {
                int i6 = i4 + 1;
                fArr[i6] = fArr[i6] + keyCycleOscillator3.get(f10);
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
        HashMap<String, SplineSet> hashMap = this.mAttributesMap;
        if (hashMap != null) {
            SplineSet splineSet = hashMap.get("translationX");
        }
        HashMap<String, SplineSet> hashMap2 = this.mAttributesMap;
        if (hashMap2 != null) {
            SplineSet splineSet2 = hashMap2.get("translationY");
        }
        HashMap<String, KeyCycleOscillator> hashMap3 = this.mCycleMap;
        if (hashMap3 != null) {
            KeyCycleOscillator keyCycleOscillator = hashMap3.get("translationX");
        }
        HashMap<String, KeyCycleOscillator> hashMap4 = this.mCycleMap;
        if (hashMap4 != null) {
            KeyCycleOscillator keyCycleOscillator2 = hashMap4.get("translationY");
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
    public MotionKeyPosition getPositionKeyframe(int i, int i2, float f, float f2) {
        FloatRect floatRect = new FloatRect();
        floatRect.left = this.mStartMotionPath.x;
        floatRect.top = this.mStartMotionPath.y;
        floatRect.right = floatRect.left + this.mStartMotionPath.width;
        floatRect.bottom = floatRect.top + this.mStartMotionPath.height;
        FloatRect floatRect2 = new FloatRect();
        floatRect2.left = this.mEndMotionPath.x;
        floatRect2.top = this.mEndMotionPath.y;
        floatRect2.right = floatRect2.left + this.mEndMotionPath.width;
        floatRect2.bottom = floatRect2.top + this.mEndMotionPath.height;
        Iterator<MotionKey> it = this.mKeyList.iterator();
        while (it.hasNext()) {
            MotionKey next = it.next();
            if (next instanceof MotionKeyPosition) {
                MotionKeyPosition motionKeyPosition = (MotionKeyPosition) next;
                if (motionKeyPosition.intersects(i, i2, floatRect, floatRect2, f, f2)) {
                    return motionKeyPosition;
                }
            }
        }
        return null;
    }

    public int buildKeyFrames(float[] fArr, int[] iArr, int[] iArr2) {
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
        if (iArr2 != null) {
            Iterator<MotionPaths> it2 = this.mMotionPaths.iterator();
            int i2 = 0;
            while (it2.hasNext()) {
                iArr2[i2] = (int) (it2.next().position * 100.0f);
                i2++;
            }
        }
        int i3 = 0;
        for (int i4 = 0; i4 < timePoints.length; i4++) {
            this.mSpline[0].getPos(timePoints[i4], this.mInterpolateData);
            this.mStartMotionPath.getCenter(timePoints[i4], this.mInterpolateVariables, this.mInterpolateData, fArr, i3);
            i3 += 2;
        }
        return i3 / 2;
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
        Iterator<MotionPaths> it = this.mMotionPaths.iterator();
        MotionPaths motionPaths2 = null;
        while (it.hasNext()) {
            MotionPaths next = it.next();
            if (motionPaths.position == next.position) {
                motionPaths2 = next;
            }
        }
        if (motionPaths2 != null) {
            this.mMotionPaths.remove(motionPaths2);
        }
        int binarySearch = Collections.binarySearch(this.mMotionPaths, motionPaths);
        if (binarySearch == 0) {
            Utils.loge(TAG, " KeyPath position \"" + motionPaths.position + "\" outside of range");
        }
        this.mMotionPaths.add((-binarySearch) - 1, motionPaths);
    }

    /* access modifiers changed from: package-private */
    public void addKeys(ArrayList<MotionKey> arrayList) {
        this.mKeyList.addAll(arrayList);
    }

    public void addKey(MotionKey motionKey) {
        this.mKeyList.add(motionKey);
    }

    public void setPathMotionArc(int i) {
        this.mPathMotionArc = i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:63:0x016a, code lost:
        r10 = (java.lang.Integer) r7.get(r9);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setup(int r21, int r22, float r23, long r24) {
        /*
            r20 = this;
            r0 = r20
            r1 = r24
            java.lang.Class<double> r3 = double.class
            java.util.HashSet r4 = new java.util.HashSet
            r4.<init>()
            java.util.HashSet r4 = new java.util.HashSet
            r4.<init>()
            java.util.HashSet r5 = new java.util.HashSet
            r5.<init>()
            java.util.HashSet r6 = new java.util.HashSet
            r6.<init>()
            java.util.HashMap r7 = new java.util.HashMap
            r7.<init>()
            int r8 = r0.mPathMotionArc
            r9 = -1
            if (r8 == r9) goto L_0x0028
            androidx.constraintlayout.core.motion.MotionPaths r10 = r0.mStartMotionPath
            r10.mPathMotionArc = r8
        L_0x0028:
            androidx.constraintlayout.core.motion.MotionConstrainedPoint r8 = r0.mStartPoint
            androidx.constraintlayout.core.motion.MotionConstrainedPoint r10 = r0.mEndPoint
            r8.different(r10, r5)
            java.util.ArrayList<androidx.constraintlayout.core.motion.key.MotionKey> r8 = r0.mKeyList
            if (r8 == 0) goto L_0x0092
            java.util.Iterator r8 = r8.iterator()
            r11 = 0
        L_0x0038:
            boolean r12 = r8.hasNext()
            if (r12 == 0) goto L_0x0093
            java.lang.Object r12 = r8.next()
            androidx.constraintlayout.core.motion.key.MotionKey r12 = (androidx.constraintlayout.core.motion.key.MotionKey) r12
            boolean r13 = r12 instanceof androidx.constraintlayout.core.motion.key.MotionKeyPosition
            if (r13 == 0) goto L_0x006a
            androidx.constraintlayout.core.motion.key.MotionKeyPosition r12 = (androidx.constraintlayout.core.motion.key.MotionKeyPosition) r12
            androidx.constraintlayout.core.motion.MotionPaths r13 = new androidx.constraintlayout.core.motion.MotionPaths
            androidx.constraintlayout.core.motion.MotionPaths r15 = r0.mStartMotionPath
            androidx.constraintlayout.core.motion.MotionPaths r14 = r0.mEndMotionPath
            r19 = r14
            r14 = r13
            r18 = r15
            r15 = r21
            r16 = r22
            r17 = r12
            r14.<init>(r15, r16, r17, r18, r19)
            r0.insertKey(r13)
            int r13 = r12.mCurveFit
            if (r13 == r9) goto L_0x0038
            int r12 = r12.mCurveFit
            r0.mCurveFitType = r12
            goto L_0x0038
        L_0x006a:
            boolean r13 = r12 instanceof androidx.constraintlayout.core.motion.key.MotionKeyCycle
            if (r13 == 0) goto L_0x0072
            r12.getAttributeNames(r6)
            goto L_0x0038
        L_0x0072:
            boolean r13 = r12 instanceof androidx.constraintlayout.core.motion.key.MotionKeyTimeCycle
            if (r13 == 0) goto L_0x007a
            r12.getAttributeNames(r4)
            goto L_0x0038
        L_0x007a:
            boolean r13 = r12 instanceof androidx.constraintlayout.core.motion.key.MotionKeyTrigger
            if (r13 == 0) goto L_0x008b
            if (r11 != 0) goto L_0x0085
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
        L_0x0085:
            androidx.constraintlayout.core.motion.key.MotionKeyTrigger r12 = (androidx.constraintlayout.core.motion.key.MotionKeyTrigger) r12
            r11.add(r12)
            goto L_0x0038
        L_0x008b:
            r12.setInterpolation(r7)
            r12.getAttributeNames(r5)
            goto L_0x0038
        L_0x0092:
            r11 = 0
        L_0x0093:
            r8 = 0
            if (r11 == 0) goto L_0x00a0
            androidx.constraintlayout.core.motion.key.MotionKeyTrigger[] r12 = new androidx.constraintlayout.core.motion.key.MotionKeyTrigger[r8]
            java.lang.Object[] r11 = r11.toArray(r12)
            androidx.constraintlayout.core.motion.key.MotionKeyTrigger[] r11 = (androidx.constraintlayout.core.motion.key.MotionKeyTrigger[]) r11
            r0.mKeyTriggers = r11
        L_0x00a0:
            boolean r11 = r5.isEmpty()
            java.lang.String r12 = ","
            java.lang.String r13 = "CUSTOM,"
            r14 = 1
            if (r11 != 0) goto L_0x0186
            java.util.HashMap r11 = new java.util.HashMap
            r11.<init>()
            r0.mAttributesMap = r11
            java.util.Iterator r11 = r5.iterator()
        L_0x00b6:
            boolean r15 = r11.hasNext()
            if (r15 == 0) goto L_0x011f
            java.lang.Object r15 = r11.next()
            java.lang.String r15 = (java.lang.String) r15
            boolean r16 = r15.startsWith(r13)
            if (r16 == 0) goto L_0x0108
            androidx.constraintlayout.core.motion.utils.KeyFrameArray$CustomVar r10 = new androidx.constraintlayout.core.motion.utils.KeyFrameArray$CustomVar
            r10.<init>()
            java.lang.String[] r16 = r15.split(r12)
            r9 = r16[r14]
            java.util.ArrayList<androidx.constraintlayout.core.motion.key.MotionKey> r14 = r0.mKeyList
            java.util.Iterator r14 = r14.iterator()
        L_0x00d9:
            boolean r16 = r14.hasNext()
            if (r16 == 0) goto L_0x0101
            java.lang.Object r16 = r14.next()
            r8 = r16
            androidx.constraintlayout.core.motion.key.MotionKey r8 = (androidx.constraintlayout.core.motion.key.MotionKey) r8
            r16 = r11
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.CustomVariable> r11 = r8.mCustom
            if (r11 != 0) goto L_0x00f1
        L_0x00ed:
            r11 = r16
            r8 = 0
            goto L_0x00d9
        L_0x00f1:
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.CustomVariable> r11 = r8.mCustom
            java.lang.Object r11 = r11.get(r9)
            androidx.constraintlayout.core.motion.CustomVariable r11 = (androidx.constraintlayout.core.motion.CustomVariable) r11
            if (r11 == 0) goto L_0x00ed
            int r8 = r8.mFramePosition
            r10.append(r8, r11)
            goto L_0x00ed
        L_0x0101:
            r16 = r11
            androidx.constraintlayout.core.motion.utils.SplineSet r8 = androidx.constraintlayout.core.motion.utils.SplineSet.makeCustomSplineSet(r15, r10)
            goto L_0x010e
        L_0x0108:
            r16 = r11
            androidx.constraintlayout.core.motion.utils.SplineSet r8 = androidx.constraintlayout.core.motion.utils.SplineSet.makeSpline(r15, r1)
        L_0x010e:
            if (r8 != 0) goto L_0x0111
            goto L_0x0119
        L_0x0111:
            r8.setType(r15)
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.utils.SplineSet> r9 = r0.mAttributesMap
            r9.put(r15, r8)
        L_0x0119:
            r11 = r16
            r8 = 0
            r9 = -1
            r14 = 1
            goto L_0x00b6
        L_0x011f:
            java.util.ArrayList<androidx.constraintlayout.core.motion.key.MotionKey> r8 = r0.mKeyList
            if (r8 == 0) goto L_0x013d
            java.util.Iterator r8 = r8.iterator()
        L_0x0127:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L_0x013d
            java.lang.Object r9 = r8.next()
            androidx.constraintlayout.core.motion.key.MotionKey r9 = (androidx.constraintlayout.core.motion.key.MotionKey) r9
            boolean r10 = r9 instanceof androidx.constraintlayout.core.motion.key.MotionKeyAttributes
            if (r10 == 0) goto L_0x0127
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.utils.SplineSet> r10 = r0.mAttributesMap
            r9.addValues(r10)
            goto L_0x0127
        L_0x013d:
            androidx.constraintlayout.core.motion.MotionConstrainedPoint r8 = r0.mStartPoint
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.utils.SplineSet> r9 = r0.mAttributesMap
            r10 = 0
            r8.addValues(r9, r10)
            androidx.constraintlayout.core.motion.MotionConstrainedPoint r8 = r0.mEndPoint
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.utils.SplineSet> r9 = r0.mAttributesMap
            r10 = 100
            r8.addValues(r9, r10)
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.utils.SplineSet> r8 = r0.mAttributesMap
            java.util.Set r8 = r8.keySet()
            java.util.Iterator r8 = r8.iterator()
        L_0x0158:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L_0x0186
            java.lang.Object r9 = r8.next()
            java.lang.String r9 = (java.lang.String) r9
            boolean r10 = r7.containsKey(r9)
            if (r10 == 0) goto L_0x0177
            java.lang.Object r10 = r7.get(r9)
            java.lang.Integer r10 = (java.lang.Integer) r10
            if (r10 == 0) goto L_0x0177
            int r10 = r10.intValue()
            goto L_0x0178
        L_0x0177:
            r10 = 0
        L_0x0178:
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.utils.SplineSet> r11 = r0.mAttributesMap
            java.lang.Object r9 = r11.get(r9)
            androidx.constraintlayout.core.motion.utils.SplineSet r9 = (androidx.constraintlayout.core.motion.utils.SplineSet) r9
            if (r9 == 0) goto L_0x0158
            r9.setup(r10)
            goto L_0x0158
        L_0x0186:
            boolean r8 = r4.isEmpty()
            if (r8 != 0) goto L_0x024d
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet> r8 = r0.mTimeCycleAttributesMap
            if (r8 != 0) goto L_0x0197
            java.util.HashMap r8 = new java.util.HashMap
            r8.<init>()
            r0.mTimeCycleAttributesMap = r8
        L_0x0197:
            java.util.Iterator r4 = r4.iterator()
        L_0x019b:
            boolean r8 = r4.hasNext()
            if (r8 == 0) goto L_0x01f9
            java.lang.Object r8 = r4.next()
            java.lang.String r8 = (java.lang.String) r8
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet> r9 = r0.mTimeCycleAttributesMap
            boolean r9 = r9.containsKey(r8)
            if (r9 == 0) goto L_0x01b0
            goto L_0x019b
        L_0x01b0:
            boolean r9 = r8.startsWith(r13)
            if (r9 == 0) goto L_0x01ee
            androidx.constraintlayout.core.motion.utils.KeyFrameArray$CustomVar r9 = new androidx.constraintlayout.core.motion.utils.KeyFrameArray$CustomVar
            r9.<init>()
            java.lang.String[] r10 = r8.split(r12)
            r11 = 1
            r10 = r10[r11]
            java.util.ArrayList<androidx.constraintlayout.core.motion.key.MotionKey> r11 = r0.mKeyList
            java.util.Iterator r11 = r11.iterator()
        L_0x01c8:
            boolean r14 = r11.hasNext()
            if (r14 == 0) goto L_0x01e9
            java.lang.Object r14 = r11.next()
            androidx.constraintlayout.core.motion.key.MotionKey r14 = (androidx.constraintlayout.core.motion.key.MotionKey) r14
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.CustomVariable> r15 = r14.mCustom
            if (r15 != 0) goto L_0x01d9
            goto L_0x01c8
        L_0x01d9:
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.CustomVariable> r15 = r14.mCustom
            java.lang.Object r15 = r15.get(r10)
            androidx.constraintlayout.core.motion.CustomVariable r15 = (androidx.constraintlayout.core.motion.CustomVariable) r15
            if (r15 == 0) goto L_0x01c8
            int r14 = r14.mFramePosition
            r9.append(r14, r15)
            goto L_0x01c8
        L_0x01e9:
            androidx.constraintlayout.core.motion.utils.SplineSet r9 = androidx.constraintlayout.core.motion.utils.SplineSet.makeCustomSplineSet(r8, r9)
            goto L_0x01f2
        L_0x01ee:
            androidx.constraintlayout.core.motion.utils.SplineSet r9 = androidx.constraintlayout.core.motion.utils.SplineSet.makeSpline(r8, r1)
        L_0x01f2:
            if (r9 != 0) goto L_0x01f5
            goto L_0x019b
        L_0x01f5:
            r9.setType(r8)
            goto L_0x019b
        L_0x01f9:
            java.util.ArrayList<androidx.constraintlayout.core.motion.key.MotionKey> r1 = r0.mKeyList
            if (r1 == 0) goto L_0x0219
            java.util.Iterator r1 = r1.iterator()
        L_0x0201:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0219
            java.lang.Object r2 = r1.next()
            androidx.constraintlayout.core.motion.key.MotionKey r2 = (androidx.constraintlayout.core.motion.key.MotionKey) r2
            boolean r4 = r2 instanceof androidx.constraintlayout.core.motion.key.MotionKeyTimeCycle
            if (r4 == 0) goto L_0x0201
            androidx.constraintlayout.core.motion.key.MotionKeyTimeCycle r2 = (androidx.constraintlayout.core.motion.key.MotionKeyTimeCycle) r2
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet> r4 = r0.mTimeCycleAttributesMap
            r2.addTimeValues(r4)
            goto L_0x0201
        L_0x0219:
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet> r1 = r0.mTimeCycleAttributesMap
            java.util.Set r1 = r1.keySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x0223:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x024d
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            boolean r4 = r7.containsKey(r2)
            if (r4 == 0) goto L_0x0240
            java.lang.Object r4 = r7.get(r2)
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            goto L_0x0241
        L_0x0240:
            r4 = 0
        L_0x0241:
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet> r8 = r0.mTimeCycleAttributesMap
            java.lang.Object r2 = r8.get(r2)
            androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet r2 = (androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet) r2
            r2.setup(r4)
            goto L_0x0223
        L_0x024d:
            java.util.ArrayList<androidx.constraintlayout.core.motion.MotionPaths> r1 = r0.mMotionPaths
            int r1 = r1.size()
            r2 = 2
            int r1 = r1 + r2
            androidx.constraintlayout.core.motion.MotionPaths[] r4 = new androidx.constraintlayout.core.motion.MotionPaths[r1]
            androidx.constraintlayout.core.motion.MotionPaths r7 = r0.mStartMotionPath
            r8 = 0
            r4[r8] = r7
            int r7 = r1 + -1
            androidx.constraintlayout.core.motion.MotionPaths r9 = r0.mEndMotionPath
            r4[r7] = r9
            java.util.ArrayList<androidx.constraintlayout.core.motion.MotionPaths> r7 = r0.mMotionPaths
            int r7 = r7.size()
            if (r7 <= 0) goto L_0x0272
            int r7 = r0.mCurveFitType
            int r9 = androidx.constraintlayout.core.motion.key.MotionKey.UNSET
            if (r7 != r9) goto L_0x0272
            r0.mCurveFitType = r8
        L_0x0272:
            java.util.ArrayList<androidx.constraintlayout.core.motion.MotionPaths> r7 = r0.mMotionPaths
            java.util.Iterator r7 = r7.iterator()
            r8 = 1
        L_0x0279:
            boolean r9 = r7.hasNext()
            if (r9 == 0) goto L_0x028b
            java.lang.Object r9 = r7.next()
            androidx.constraintlayout.core.motion.MotionPaths r9 = (androidx.constraintlayout.core.motion.MotionPaths) r9
            int r10 = r8 + 1
            r4[r8] = r9
            r8 = r10
            goto L_0x0279
        L_0x028b:
            r7 = 18
            java.util.HashSet r8 = new java.util.HashSet
            r8.<init>()
            androidx.constraintlayout.core.motion.MotionPaths r9 = r0.mEndMotionPath
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.CustomVariable> r9 = r9.customAttributes
            java.util.Set r9 = r9.keySet()
            java.util.Iterator r9 = r9.iterator()
        L_0x029e:
            boolean r10 = r9.hasNext()
            if (r10 == 0) goto L_0x02cd
            java.lang.Object r10 = r9.next()
            java.lang.String r10 = (java.lang.String) r10
            androidx.constraintlayout.core.motion.MotionPaths r11 = r0.mStartMotionPath
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.CustomVariable> r11 = r11.customAttributes
            boolean r11 = r11.containsKey(r10)
            if (r11 == 0) goto L_0x029e
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r13)
            r11.append(r10)
            java.lang.String r11 = r11.toString()
            boolean r11 = r5.contains(r11)
            if (r11 != 0) goto L_0x029e
            r8.add(r10)
            goto L_0x029e
        L_0x02cd:
            r10 = 0
            java.lang.String[] r5 = new java.lang.String[r10]
            java.lang.Object[] r5 = r8.toArray(r5)
            java.lang.String[] r5 = (java.lang.String[]) r5
            r0.mAttributeNames = r5
            int r5 = r5.length
            int[] r5 = new int[r5]
            r0.mAttributeInterpolatorCount = r5
            r5 = 0
        L_0x02de:
            java.lang.String[] r8 = r0.mAttributeNames
            int r9 = r8.length
            if (r5 >= r9) goto L_0x0315
            r8 = r8[r5]
            int[] r9 = r0.mAttributeInterpolatorCount
            r10 = 0
            r9[r5] = r10
            r9 = 0
        L_0x02eb:
            if (r9 >= r1) goto L_0x0312
            r10 = r4[r9]
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.CustomVariable> r10 = r10.customAttributes
            boolean r10 = r10.containsKey(r8)
            if (r10 == 0) goto L_0x030f
            r10 = r4[r9]
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.CustomVariable> r10 = r10.customAttributes
            java.lang.Object r10 = r10.get(r8)
            androidx.constraintlayout.core.motion.CustomVariable r10 = (androidx.constraintlayout.core.motion.CustomVariable) r10
            if (r10 == 0) goto L_0x030f
            int[] r8 = r0.mAttributeInterpolatorCount
            r9 = r8[r5]
            int r10 = r10.numberOfInterpolatedValues()
            int r9 = r9 + r10
            r8[r5] = r9
            goto L_0x0312
        L_0x030f:
            int r9 = r9 + 1
            goto L_0x02eb
        L_0x0312:
            int r5 = r5 + 1
            goto L_0x02de
        L_0x0315:
            r5 = 0
            r8 = r4[r5]
            int r5 = r8.mPathMotionArc
            r8 = -1
            if (r5 == r8) goto L_0x031f
            r5 = 1
            goto L_0x0320
        L_0x031f:
            r5 = 0
        L_0x0320:
            java.lang.String[] r8 = r0.mAttributeNames
            int r8 = r8.length
            int r7 = r7 + r8
            boolean[] r8 = new boolean[r7]
            r9 = 1
        L_0x0327:
            if (r9 >= r1) goto L_0x0337
            r10 = r4[r9]
            int r11 = r9 + -1
            r11 = r4[r11]
            java.lang.String[] r12 = r0.mAttributeNames
            r10.different(r11, r8, r12, r5)
            int r9 = r9 + 1
            goto L_0x0327
        L_0x0337:
            r5 = 1
            r9 = 0
        L_0x0339:
            if (r5 >= r7) goto L_0x0344
            boolean r10 = r8[r5]
            if (r10 == 0) goto L_0x0341
            int r9 = r9 + 1
        L_0x0341:
            int r5 = r5 + 1
            goto L_0x0339
        L_0x0344:
            int[] r5 = new int[r9]
            r0.mInterpolateVariables = r5
            int r5 = java.lang.Math.max(r2, r9)
            double[] r9 = new double[r5]
            r0.mInterpolateData = r9
            double[] r5 = new double[r5]
            r0.mInterpolateVelocity = r5
            r5 = 1
            r9 = 0
        L_0x0356:
            if (r5 >= r7) goto L_0x0366
            boolean r10 = r8[r5]
            if (r10 == 0) goto L_0x0363
            int[] r10 = r0.mInterpolateVariables
            int r11 = r9 + 1
            r10[r9] = r5
            r9 = r11
        L_0x0363:
            int r5 = r5 + 1
            goto L_0x0356
        L_0x0366:
            int[] r5 = r0.mInterpolateVariables
            int r5 = r5.length
            int[] r7 = new int[r2]
            r8 = 1
            r7[r8] = r5
            r5 = 0
            r7[r5] = r1
            java.lang.Object r5 = java.lang.reflect.Array.newInstance(r3, r7)
            double[][] r5 = (double[][]) r5
            double[] r7 = new double[r1]
            r8 = 0
        L_0x037a:
            if (r8 >= r1) goto L_0x038f
            r9 = r4[r8]
            r10 = r5[r8]
            int[] r11 = r0.mInterpolateVariables
            r9.fillStandard(r10, r11)
            r9 = r4[r8]
            float r9 = r9.time
            double r9 = (double) r9
            r7[r8] = r9
            int r8 = r8 + 1
            goto L_0x037a
        L_0x038f:
            r8 = 0
        L_0x0390:
            int[] r9 = r0.mInterpolateVariables
            int r10 = r9.length
            if (r8 >= r10) goto L_0x03d2
            r9 = r9[r8]
            java.lang.String[] r10 = androidx.constraintlayout.core.motion.MotionPaths.names
            int r10 = r10.length
            if (r9 >= r10) goto L_0x03cf
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String[] r10 = androidx.constraintlayout.core.motion.MotionPaths.names
            int[] r11 = r0.mInterpolateVariables
            r11 = r11[r8]
            r10 = r10[r11]
            r9.append(r10)
            java.lang.String r10 = " ["
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r10 = r9
            r9 = 0
        L_0x03b7:
            if (r9 >= r1) goto L_0x03cf
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r11.append(r10)
            r10 = r5[r9]
            r12 = r10[r8]
            r11.append(r12)
            java.lang.String r10 = r11.toString()
            int r9 = r9 + 1
            goto L_0x03b7
        L_0x03cf:
            int r8 = r8 + 1
            goto L_0x0390
        L_0x03d2:
            java.lang.String[] r8 = r0.mAttributeNames
            int r8 = r8.length
            r9 = 1
            int r8 = r8 + r9
            androidx.constraintlayout.core.motion.utils.CurveFit[] r8 = new androidx.constraintlayout.core.motion.utils.CurveFit[r8]
            r0.mSpline = r8
            r8 = 0
        L_0x03dc:
            java.lang.String[] r9 = r0.mAttributeNames
            int r10 = r9.length
            if (r8 >= r10) goto L_0x0449
            r10 = 0
            r11 = r10
            double[][] r11 = (double[][]) r11
            r9 = r9[r8]
            r14 = r10
            r13 = r11
            r11 = 0
            r12 = 0
        L_0x03eb:
            if (r11 >= r1) goto L_0x042d
            r15 = r4[r11]
            boolean r15 = r15.hasCustomData(r9)
            if (r15 == 0) goto L_0x0424
            if (r13 != 0) goto L_0x0410
            double[] r13 = new double[r1]
            r14 = r4[r11]
            int r14 = r14.getCustomDataCount(r9)
            int[] r15 = new int[r2]
            r16 = 1
            r15[r16] = r14
            r14 = 0
            r15[r14] = r1
            java.lang.Object r15 = java.lang.reflect.Array.newInstance(r3, r15)
            double[][] r15 = (double[][]) r15
            r14 = r13
            r13 = r15
        L_0x0410:
            r15 = 0
            r10 = r4[r11]
            float r10 = r10.time
            r16 = r3
            double r2 = (double) r10
            r14[r12] = r2
            r2 = r4[r11]
            r3 = r13[r12]
            r2.getCustomData(r9, r3, r15)
            int r12 = r12 + 1
            goto L_0x0426
        L_0x0424:
            r16 = r3
        L_0x0426:
            int r11 = r11 + 1
            r3 = r16
            r2 = 2
            r10 = 0
            goto L_0x03eb
        L_0x042d:
            r16 = r3
            double[] r2 = java.util.Arrays.copyOf(r14, r12)
            java.lang.Object[] r3 = java.util.Arrays.copyOf(r13, r12)
            double[][] r3 = (double[][]) r3
            androidx.constraintlayout.core.motion.utils.CurveFit[] r9 = r0.mSpline
            int r8 = r8 + 1
            int r10 = r0.mCurveFitType
            androidx.constraintlayout.core.motion.utils.CurveFit r2 = androidx.constraintlayout.core.motion.utils.CurveFit.get(r10, r2, r3)
            r9[r8] = r2
            r3 = r16
            r2 = 2
            goto L_0x03dc
        L_0x0449:
            r16 = r3
            androidx.constraintlayout.core.motion.utils.CurveFit[] r2 = r0.mSpline
            int r3 = r0.mCurveFitType
            androidx.constraintlayout.core.motion.utils.CurveFit r3 = androidx.constraintlayout.core.motion.utils.CurveFit.get(r3, r7, r5)
            r5 = 0
            r2[r5] = r3
            r2 = r4[r5]
            int r2 = r2.mPathMotionArc
            r3 = -1
            if (r2 == r3) goto L_0x049e
            int[] r2 = new int[r1]
            double[] r3 = new double[r1]
            r7 = 2
            int[] r8 = new int[r7]
            r9 = 1
            r8[r9] = r7
            r8[r5] = r1
            r5 = r16
            java.lang.Object r5 = java.lang.reflect.Array.newInstance(r5, r8)
            double[][] r5 = (double[][]) r5
            r10 = 0
        L_0x0472:
            if (r10 >= r1) goto L_0x0498
            r7 = r4[r10]
            int r7 = r7.mPathMotionArc
            r2[r10] = r7
            r7 = r4[r10]
            float r7 = r7.time
            double r7 = (double) r7
            r3[r10] = r7
            r7 = r5[r10]
            r8 = r4[r10]
            float r8 = r8.x
            double r8 = (double) r8
            r11 = 0
            r7[r11] = r8
            r7 = r5[r10]
            r8 = r4[r10]
            float r8 = r8.y
            double r8 = (double) r8
            r12 = 1
            r7[r12] = r8
            int r10 = r10 + 1
            goto L_0x0472
        L_0x0498:
            androidx.constraintlayout.core.motion.utils.CurveFit r1 = androidx.constraintlayout.core.motion.utils.CurveFit.getArc(r2, r3, r5)
            r0.mArcSpline = r1
        L_0x049e:
            r1 = 2143289344(0x7fc00000, float:NaN)
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            r0.mCycleMap = r2
            java.util.ArrayList<androidx.constraintlayout.core.motion.key.MotionKey> r2 = r0.mKeyList
            if (r2 == 0) goto L_0x0513
            java.util.Iterator r2 = r6.iterator()
        L_0x04af:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x04db
            java.lang.Object r3 = r2.next()
            java.lang.String r3 = (java.lang.String) r3
            androidx.constraintlayout.core.motion.utils.KeyCycleOscillator r4 = androidx.constraintlayout.core.motion.utils.KeyCycleOscillator.makeWidgetCycle(r3)
            if (r4 != 0) goto L_0x04c2
            goto L_0x04af
        L_0x04c2:
            boolean r5 = r4.variesByPath()
            if (r5 == 0) goto L_0x04d2
            boolean r5 = java.lang.Float.isNaN(r1)
            if (r5 == 0) goto L_0x04d2
            float r1 = r20.getPreCycleDistance()
        L_0x04d2:
            r4.setType(r3)
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.utils.KeyCycleOscillator> r5 = r0.mCycleMap
            r5.put(r3, r4)
            goto L_0x04af
        L_0x04db:
            java.util.ArrayList<androidx.constraintlayout.core.motion.key.MotionKey> r2 = r0.mKeyList
            java.util.Iterator r2 = r2.iterator()
        L_0x04e1:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x04f9
            java.lang.Object r3 = r2.next()
            androidx.constraintlayout.core.motion.key.MotionKey r3 = (androidx.constraintlayout.core.motion.key.MotionKey) r3
            boolean r4 = r3 instanceof androidx.constraintlayout.core.motion.key.MotionKeyCycle
            if (r4 == 0) goto L_0x04e1
            androidx.constraintlayout.core.motion.key.MotionKeyCycle r3 = (androidx.constraintlayout.core.motion.key.MotionKeyCycle) r3
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.utils.KeyCycleOscillator> r4 = r0.mCycleMap
            r3.addCycleValues(r4)
            goto L_0x04e1
        L_0x04f9:
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.utils.KeyCycleOscillator> r2 = r0.mCycleMap
            java.util.Collection r2 = r2.values()
            java.util.Iterator r2 = r2.iterator()
        L_0x0503:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0513
            java.lang.Object r3 = r2.next()
            androidx.constraintlayout.core.motion.utils.KeyCycleOscillator r3 = (androidx.constraintlayout.core.motion.utils.KeyCycleOscillator) r3
            r3.setup(r1)
            goto L_0x0503
        L_0x0513:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.motion.Motion.setup(int, int, float, long):void");
    }

    public String toString() {
        return " start: x: " + this.mStartMotionPath.x + " y: " + this.mStartMotionPath.y + " end: x: " + this.mEndMotionPath.x + " y: " + this.mEndMotionPath.y;
    }

    private void readView(MotionPaths motionPaths) {
        motionPaths.setBounds((float) this.mView.getX(), (float) this.mView.getY(), (float) this.mView.getWidth(), (float) this.mView.getHeight());
    }

    public void setView(MotionWidget motionWidget) {
        this.mView = motionWidget;
    }

    public MotionWidget getView() {
        return this.mView;
    }

    public void setStart(MotionWidget motionWidget) {
        this.mStartMotionPath.time = 0.0f;
        this.mStartMotionPath.position = 0.0f;
        this.mStartMotionPath.setBounds((float) motionWidget.getX(), (float) motionWidget.getY(), (float) motionWidget.getWidth(), (float) motionWidget.getHeight());
        this.mStartMotionPath.applyParameters(motionWidget);
        this.mStartPoint.setState(motionWidget);
    }

    public void setEnd(MotionWidget motionWidget) {
        this.mEndMotionPath.time = 1.0f;
        this.mEndMotionPath.position = 1.0f;
        readView(this.mEndMotionPath);
        this.mEndMotionPath.setBounds((float) motionWidget.getLeft(), (float) motionWidget.getTop(), (float) motionWidget.getWidth(), (float) motionWidget.getHeight());
        this.mEndMotionPath.applyParameters(motionWidget);
        this.mEndPoint.setState(motionWidget);
    }

    public void setStartState(ViewState viewState, MotionWidget motionWidget, int i, int i2, int i3) {
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
        this.mStartPoint.setState(rect, motionWidget, i, viewState.rotation);
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

    private static DifferentialInterpolator getInterpolator(int i, String str, int i2) {
        if (i != -1) {
            return null;
        }
        final Easing interpolator = Easing.getInterpolator(str);
        return new DifferentialInterpolator() {
            float mX;

            public float getInterpolation(float f) {
                this.mX = f;
                return (float) interpolator.get((double) f);
            }

            public float getVelocity() {
                return (float) interpolator.getDiff((double) this.mX);
            }
        };
    }

    /* access modifiers changed from: package-private */
    public void setBothStates(MotionWidget motionWidget) {
        this.mStartMotionPath.time = 0.0f;
        this.mStartMotionPath.position = 0.0f;
        this.mNoMovement = true;
        this.mStartMotionPath.setBounds((float) motionWidget.getX(), (float) motionWidget.getY(), (float) motionWidget.getWidth(), (float) motionWidget.getHeight());
        this.mEndMotionPath.setBounds((float) motionWidget.getX(), (float) motionWidget.getY(), (float) motionWidget.getWidth(), (float) motionWidget.getHeight());
        this.mStartPoint.setState(motionWidget);
        this.mEndPoint.setState(motionWidget);
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

    public boolean interpolate(MotionWidget motionWidget, float f, long j, KeyCache keyCache) {
        double d;
        MotionWidget motionWidget2 = motionWidget;
        float adjustedPosition = getAdjustedPosition(f, (float[]) null);
        int i = this.mQuantizeMotionSteps;
        if (i != -1) {
            float f2 = 1.0f / ((float) i);
            float floor = ((float) Math.floor((double) (adjustedPosition / f2))) * f2;
            float f3 = (adjustedPosition % f2) / f2;
            if (!Float.isNaN(this.mQuantizeMotionPhase)) {
                f3 = (f3 + this.mQuantizeMotionPhase) % 1.0f;
            }
            DifferentialInterpolator differentialInterpolator = this.mQuantizeMotionInterpolator;
            adjustedPosition = ((differentialInterpolator != null ? differentialInterpolator.getInterpolation(f3) : ((double) f3) > 0.5d ? 1.0f : 0.0f) * f2) + floor;
        }
        float f4 = adjustedPosition;
        HashMap<String, SplineSet> hashMap = this.mAttributesMap;
        if (hashMap != null) {
            for (SplineSet property : hashMap.values()) {
                property.setProperty(motionWidget2, f4);
            }
        }
        CurveFit[] curveFitArr = this.mSpline;
        if (curveFitArr != null) {
            double d2 = (double) f4;
            curveFitArr[0].getPos(d2, this.mInterpolateData);
            this.mSpline[0].getSlope(d2, this.mInterpolateVelocity);
            CurveFit curveFit = this.mArcSpline;
            if (curveFit != null) {
                double[] dArr = this.mInterpolateData;
                if (dArr.length > 0) {
                    curveFit.getPos(d2, dArr);
                    this.mArcSpline.getSlope(d2, this.mInterpolateVelocity);
                }
            }
            if (!this.mNoMovement) {
                d = d2;
                this.mStartMotionPath.setView(f4, motionWidget, this.mInterpolateVariables, this.mInterpolateData, this.mInterpolateVelocity, (double[]) null);
            } else {
                d = d2;
            }
            if (this.mTransformPivotTarget != -1) {
                if (this.mTransformPivotView == null) {
                    this.mTransformPivotView = motionWidget.getParent().findViewById(this.mTransformPivotTarget);
                }
                MotionWidget motionWidget3 = this.mTransformPivotView;
                if (motionWidget3 != null) {
                    float top = ((float) (motionWidget3.getTop() + this.mTransformPivotView.getBottom())) / 2.0f;
                    float left = ((float) (this.mTransformPivotView.getLeft() + this.mTransformPivotView.getRight())) / 2.0f;
                    if (motionWidget.getRight() - motionWidget.getLeft() > 0 && motionWidget.getBottom() - motionWidget.getTop() > 0) {
                        motionWidget2.setPivotX(left - ((float) motionWidget.getLeft()));
                        motionWidget2.setPivotY(top - ((float) motionWidget.getTop()));
                    }
                }
            }
            int i2 = 1;
            while (true) {
                CurveFit[] curveFitArr2 = this.mSpline;
                if (i2 >= curveFitArr2.length) {
                    break;
                }
                curveFitArr2[i2].getPos(d, this.mValuesBuff);
                this.mStartMotionPath.customAttributes.get(this.mAttributeNames[i2 - 1]).setInterpolatedValue(motionWidget2, this.mValuesBuff);
                i2++;
            }
            if (this.mStartPoint.mVisibilityMode == 0) {
                if (f4 <= 0.0f) {
                    motionWidget2.setVisibility(this.mStartPoint.visibility);
                } else if (f4 >= 1.0f) {
                    motionWidget2.setVisibility(this.mEndPoint.visibility);
                } else if (this.mEndPoint.visibility != this.mStartPoint.visibility) {
                    motionWidget2.setVisibility(4);
                }
            }
            if (this.mKeyTriggers != null) {
                int i3 = 0;
                while (true) {
                    MotionKeyTrigger[] motionKeyTriggerArr = this.mKeyTriggers;
                    if (i3 >= motionKeyTriggerArr.length) {
                        break;
                    }
                    motionKeyTriggerArr[i3].conditionallyFire(f4, motionWidget2);
                    i3++;
                }
            }
        } else {
            float f5 = this.mStartMotionPath.x + ((this.mEndMotionPath.x - this.mStartMotionPath.x) * f4) + 0.5f;
            float f6 = this.mStartMotionPath.y + ((this.mEndMotionPath.y - this.mStartMotionPath.y) * f4) + 0.5f;
            motionWidget2.layout((int) f5, (int) f6, (int) (f5 + this.mStartMotionPath.width + ((this.mEndMotionPath.width - this.mStartMotionPath.width) * f4)), (int) (f6 + this.mStartMotionPath.height + ((this.mEndMotionPath.height - this.mStartMotionPath.height) * f4)));
        }
        HashMap<String, KeyCycleOscillator> hashMap2 = this.mCycleMap;
        if (hashMap2 == null) {
            return false;
        }
        for (KeyCycleOscillator next : hashMap2.values()) {
            if (next instanceof KeyCycleOscillator.PathRotateSet) {
                double[] dArr2 = this.mInterpolateVelocity;
                ((KeyCycleOscillator.PathRotateSet) next).setPathRotate(motionWidget, f4, dArr2[0], dArr2[1]);
            } else {
                next.setProperty(motionWidget2, f4);
            }
        }
        return false;
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
        HashMap<String, SplineSet> hashMap = this.mAttributesMap;
        KeyCycleOscillator keyCycleOscillator = null;
        SplineSet splineSet = hashMap == null ? null : hashMap.get("translationX");
        HashMap<String, SplineSet> hashMap2 = this.mAttributesMap;
        SplineSet splineSet2 = hashMap2 == null ? null : hashMap2.get("translationY");
        HashMap<String, SplineSet> hashMap3 = this.mAttributesMap;
        SplineSet splineSet3 = hashMap3 == null ? null : hashMap3.get("rotationZ");
        HashMap<String, SplineSet> hashMap4 = this.mAttributesMap;
        SplineSet splineSet4 = hashMap4 == null ? null : hashMap4.get("scaleX");
        HashMap<String, SplineSet> hashMap5 = this.mAttributesMap;
        SplineSet splineSet5 = hashMap5 == null ? null : hashMap5.get("scaleY");
        HashMap<String, KeyCycleOscillator> hashMap6 = this.mCycleMap;
        KeyCycleOscillator keyCycleOscillator2 = hashMap6 == null ? null : hashMap6.get("translationX");
        HashMap<String, KeyCycleOscillator> hashMap7 = this.mCycleMap;
        KeyCycleOscillator keyCycleOscillator3 = hashMap7 == null ? null : hashMap7.get("translationY");
        HashMap<String, KeyCycleOscillator> hashMap8 = this.mCycleMap;
        KeyCycleOscillator keyCycleOscillator4 = hashMap8 == null ? null : hashMap8.get("rotationZ");
        HashMap<String, KeyCycleOscillator> hashMap9 = this.mCycleMap;
        KeyCycleOscillator keyCycleOscillator5 = hashMap9 == null ? null : hashMap9.get("scaleX");
        HashMap<String, KeyCycleOscillator> hashMap10 = this.mCycleMap;
        if (hashMap10 != null) {
            keyCycleOscillator = hashMap10.get("scaleY");
        }
        VelocityMatrix velocityMatrix = new VelocityMatrix();
        velocityMatrix.clear();
        velocityMatrix.setRotationVelocity(splineSet3, adjustedPosition);
        velocityMatrix.setTranslationVelocity(splineSet, splineSet2, adjustedPosition);
        velocityMatrix.setScaleVelocity(splineSet4, splineSet5, adjustedPosition);
        velocityMatrix.setRotationVelocity(keyCycleOscillator4, adjustedPosition);
        velocityMatrix.setTranslationVelocity(keyCycleOscillator2, keyCycleOscillator3, adjustedPosition);
        velocityMatrix.setScaleVelocity(keyCycleOscillator5, keyCycleOscillator, adjustedPosition);
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
            KeyCycleOscillator keyCycleOscillator6 = keyCycleOscillator;
            float f9 = (this.mEndMotionPath.height - this.mStartMotionPath.height) + f8;
            fArr[0] = (f7 * (1.0f - f2)) + (((this.mEndMotionPath.width - this.mStartMotionPath.width) + f7) * f2);
            fArr[1] = (f8 * (1.0f - f3)) + (f9 * f3);
            velocityMatrix.clear();
            velocityMatrix.setRotationVelocity(splineSet3, adjustedPosition);
            velocityMatrix.setTranslationVelocity(splineSet, splineSet2, adjustedPosition);
            velocityMatrix.setScaleVelocity(splineSet4, splineSet5, adjustedPosition);
            velocityMatrix.setRotationVelocity(keyCycleOscillator4, adjustedPosition);
            velocityMatrix.setTranslationVelocity(keyCycleOscillator2, keyCycleOscillator3, adjustedPosition);
            velocityMatrix.setScaleVelocity(keyCycleOscillator5, keyCycleOscillator6, adjustedPosition);
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
        return this.mView.getName();
    }

    /* access modifiers changed from: package-private */
    public void positionKeyframe(MotionWidget motionWidget, MotionKeyPosition motionKeyPosition, float f, float f2, String[] strArr, float[] fArr) {
        FloatRect floatRect = new FloatRect();
        floatRect.left = this.mStartMotionPath.x;
        floatRect.top = this.mStartMotionPath.y;
        floatRect.right = floatRect.left + this.mStartMotionPath.width;
        floatRect.bottom = floatRect.top + this.mStartMotionPath.height;
        FloatRect floatRect2 = new FloatRect();
        floatRect2.left = this.mEndMotionPath.x;
        floatRect2.top = this.mEndMotionPath.y;
        floatRect2.right = floatRect2.left + this.mEndMotionPath.width;
        floatRect2.bottom = floatRect2.top + this.mEndMotionPath.height;
        motionKeyPosition.positionAttributes(motionWidget, floatRect, floatRect2, f, f2, strArr, fArr);
    }

    public int getKeyFramePositions(int[] iArr, float[] fArr) {
        Iterator<MotionKey> it = this.mKeyList.iterator();
        int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            MotionKey next = it.next();
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
        Iterator<MotionKey> it = this.mKeyList.iterator();
        int i3 = 0;
        int i4 = 0;
        while (it.hasNext()) {
            MotionKey next = it.next();
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
                if (next instanceof MotionKeyPosition) {
                    MotionKeyPosition motionKeyPosition = (MotionKeyPosition) next;
                    int i9 = i8 + 1;
                    iArr[i9] = motionKeyPosition.mPositionType;
                    int i10 = i9 + 1;
                    iArr[i10] = Float.floatToIntBits(motionKeyPosition.mPercentX);
                    i8 = i10 + 1;
                    iArr[i8] = Float.floatToIntBits(motionKeyPosition.mPercentY);
                }
                int i11 = i8 + 1;
                iArr[i4] = i11 - i4;
                i3++;
                i4 = i11;
            }
        }
        return i3;
    }

    public boolean setValue(int i, int i2) {
        if (i != 509) {
            return i == 704;
        }
        setPathMotionArc(i2);
        return true;
    }

    public boolean setValue(int i, String str) {
        if (705 == i) {
            PrintStream printStream = System.out;
            printStream.println("TYPE_INTERPOLATOR  " + str);
            this.mQuantizeMotionInterpolator = getInterpolator(-1, str, 0);
        }
        return false;
    }
}
