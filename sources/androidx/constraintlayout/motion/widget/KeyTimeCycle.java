package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.motion.utils.ViewSpline;
import androidx.constraintlayout.widget.R;
import java.util.HashMap;
import java.util.HashSet;

public class KeyTimeCycle extends Key {
    public static final int KEY_TYPE = 3;
    static final String NAME = "KeyTimeCycle";
    public static final int SHAPE_BOUNCE = 6;
    public static final int SHAPE_COS_WAVE = 5;
    public static final int SHAPE_REVERSE_SAW_WAVE = 4;
    public static final int SHAPE_SAW_WAVE = 3;
    public static final int SHAPE_SIN_WAVE = 0;
    public static final int SHAPE_SQUARE_WAVE = 1;
    public static final int SHAPE_TRIANGLE_WAVE = 2;
    private static final String TAG = "KeyTimeCycle";
    public static final String WAVE_OFFSET = "waveOffset";
    public static final String WAVE_PERIOD = "wavePeriod";
    public static final String WAVE_SHAPE = "waveShape";
    /* access modifiers changed from: private */
    public float mAlpha = Float.NaN;
    /* access modifiers changed from: private */
    public int mCurveFit = -1;
    /* access modifiers changed from: private */
    public String mCustomWaveShape = null;
    /* access modifiers changed from: private */
    public float mElevation = Float.NaN;
    /* access modifiers changed from: private */
    public float mProgress = Float.NaN;
    /* access modifiers changed from: private */
    public float mRotation = Float.NaN;
    /* access modifiers changed from: private */
    public float mRotationX = Float.NaN;
    /* access modifiers changed from: private */
    public float mRotationY = Float.NaN;
    /* access modifiers changed from: private */
    public float mScaleX = Float.NaN;
    /* access modifiers changed from: private */
    public float mScaleY = Float.NaN;
    /* access modifiers changed from: private */
    public String mTransitionEasing;
    /* access modifiers changed from: private */
    public float mTransitionPathRotate = Float.NaN;
    /* access modifiers changed from: private */
    public float mTranslationX = Float.NaN;
    /* access modifiers changed from: private */
    public float mTranslationY = Float.NaN;
    /* access modifiers changed from: private */
    public float mTranslationZ = Float.NaN;
    /* access modifiers changed from: private */
    public float mWaveOffset = 0.0f;
    /* access modifiers changed from: private */
    public float mWavePeriod = Float.NaN;
    /* access modifiers changed from: private */
    public int mWaveShape = 0;

    public KeyTimeCycle() {
        this.mType = 3;
        this.mCustomConstraints = new HashMap();
    }

    public void load(Context context, AttributeSet attributeSet) {
        Loader.read(this, context.obtainStyledAttributes(attributeSet, R.styleable.KeyTimeCycle));
    }

    public void getAttributeNames(HashSet<String> hashSet) {
        if (!Float.isNaN(this.mAlpha)) {
            hashSet.add("alpha");
        }
        if (!Float.isNaN(this.mElevation)) {
            hashSet.add("elevation");
        }
        if (!Float.isNaN(this.mRotation)) {
            hashSet.add(Key.ROTATION);
        }
        if (!Float.isNaN(this.mRotationX)) {
            hashSet.add("rotationX");
        }
        if (!Float.isNaN(this.mRotationY)) {
            hashSet.add("rotationY");
        }
        if (!Float.isNaN(this.mTranslationX)) {
            hashSet.add("translationX");
        }
        if (!Float.isNaN(this.mTranslationY)) {
            hashSet.add("translationY");
        }
        if (!Float.isNaN(this.mTranslationZ)) {
            hashSet.add("translationZ");
        }
        if (!Float.isNaN(this.mTransitionPathRotate)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.mScaleX)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.mScaleY)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.mProgress)) {
            hashSet.add("progress");
        }
        if (this.mCustomConstraints.size() > 0) {
            for (String str : this.mCustomConstraints.keySet()) {
                hashSet.add("CUSTOM," + str);
            }
        }
    }

    public void setInterpolation(HashMap<String, Integer> hashMap) {
        if (this.mCurveFit != -1) {
            if (!Float.isNaN(this.mAlpha)) {
                hashMap.put("alpha", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mElevation)) {
                hashMap.put("elevation", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mRotation)) {
                hashMap.put(Key.ROTATION, Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mRotationX)) {
                hashMap.put("rotationX", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mRotationY)) {
                hashMap.put("rotationY", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mTranslationX)) {
                hashMap.put("translationX", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mTranslationY)) {
                hashMap.put("translationY", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mTranslationZ)) {
                hashMap.put("translationZ", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mTransitionPathRotate)) {
                hashMap.put("transitionPathRotate", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mScaleX)) {
                hashMap.put("scaleX", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mScaleX)) {
                hashMap.put("scaleY", Integer.valueOf(this.mCurveFit));
            }
            if (!Float.isNaN(this.mProgress)) {
                hashMap.put("progress", Integer.valueOf(this.mCurveFit));
            }
            if (this.mCustomConstraints.size() > 0) {
                for (String str : this.mCustomConstraints.keySet()) {
                    hashMap.put("CUSTOM," + str, Integer.valueOf(this.mCurveFit));
                }
            }
        }
    }

    public void addValues(HashMap<String, ViewSpline> hashMap) {
        throw new IllegalArgumentException(" KeyTimeCycles do not support SplineSet");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x008c, code lost:
        if (r1.equals("scaleY") == false) goto L_0x0050;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addTimeValues(java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewTimeCycle> r11) {
        /*
            r10 = this;
            java.util.Set r0 = r11.keySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x0008:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0210
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r2 = r11.get(r1)
            r3 = r2
            androidx.constraintlayout.motion.utils.ViewTimeCycle r3 = (androidx.constraintlayout.motion.utils.ViewTimeCycle) r3
            if (r3 != 0) goto L_0x001e
            goto L_0x0008
        L_0x001e:
            java.lang.String r2 = "CUSTOM"
            boolean r2 = r1.startsWith(r2)
            r4 = 7
            if (r2 == 0) goto L_0x0045
            java.lang.String r1 = r1.substring(r4)
            java.util.HashMap r2 = r10.mCustomConstraints
            java.lang.Object r1 = r2.get(r1)
            r6 = r1
            androidx.constraintlayout.widget.ConstraintAttribute r6 = (androidx.constraintlayout.widget.ConstraintAttribute) r6
            if (r6 == 0) goto L_0x0008
            r4 = r3
            androidx.constraintlayout.motion.utils.ViewTimeCycle$CustomSet r4 = (androidx.constraintlayout.motion.utils.ViewTimeCycle.CustomSet) r4
            int r5 = r10.mFramePosition
            float r7 = r10.mWavePeriod
            int r8 = r10.mWaveShape
            float r9 = r10.mWaveOffset
            r4.setPoint((int) r5, (androidx.constraintlayout.widget.ConstraintAttribute) r6, (float) r7, (int) r8, (float) r9)
            goto L_0x0008
        L_0x0045:
            r1.hashCode()
            r2 = -1
            int r5 = r1.hashCode()
            switch(r5) {
                case -1249320806: goto L_0x00d1;
                case -1249320805: goto L_0x00c6;
                case -1225497657: goto L_0x00bb;
                case -1225497656: goto L_0x00b0;
                case -1225497655: goto L_0x00a5;
                case -1001078227: goto L_0x009a;
                case -908189618: goto L_0x008f;
                case -908189617: goto L_0x0086;
                case -40300674: goto L_0x007a;
                case -4379043: goto L_0x006d;
                case 37232917: goto L_0x0060;
                case 92909918: goto L_0x0053;
                default: goto L_0x0050;
            }
        L_0x0050:
            r4 = -1
            goto L_0x00dc
        L_0x0053:
            java.lang.String r4 = "alpha"
            boolean r4 = r1.equals(r4)
            if (r4 != 0) goto L_0x005c
            goto L_0x0050
        L_0x005c:
            r4 = 11
            goto L_0x00dc
        L_0x0060:
            java.lang.String r4 = "transitionPathRotate"
            boolean r4 = r1.equals(r4)
            if (r4 != 0) goto L_0x0069
            goto L_0x0050
        L_0x0069:
            r4 = 10
            goto L_0x00dc
        L_0x006d:
            java.lang.String r4 = "elevation"
            boolean r4 = r1.equals(r4)
            if (r4 != 0) goto L_0x0076
            goto L_0x0050
        L_0x0076:
            r4 = 9
            goto L_0x00dc
        L_0x007a:
            java.lang.String r4 = "rotation"
            boolean r4 = r1.equals(r4)
            if (r4 != 0) goto L_0x0083
            goto L_0x0050
        L_0x0083:
            r4 = 8
            goto L_0x00dc
        L_0x0086:
            java.lang.String r5 = "scaleY"
            boolean r5 = r1.equals(r5)
            if (r5 != 0) goto L_0x00dc
            goto L_0x0050
        L_0x008f:
            java.lang.String r4 = "scaleX"
            boolean r4 = r1.equals(r4)
            if (r4 != 0) goto L_0x0098
            goto L_0x0050
        L_0x0098:
            r4 = 6
            goto L_0x00dc
        L_0x009a:
            java.lang.String r4 = "progress"
            boolean r4 = r1.equals(r4)
            if (r4 != 0) goto L_0x00a3
            goto L_0x0050
        L_0x00a3:
            r4 = 5
            goto L_0x00dc
        L_0x00a5:
            java.lang.String r4 = "translationZ"
            boolean r4 = r1.equals(r4)
            if (r4 != 0) goto L_0x00ae
            goto L_0x0050
        L_0x00ae:
            r4 = 4
            goto L_0x00dc
        L_0x00b0:
            java.lang.String r4 = "translationY"
            boolean r4 = r1.equals(r4)
            if (r4 != 0) goto L_0x00b9
            goto L_0x0050
        L_0x00b9:
            r4 = 3
            goto L_0x00dc
        L_0x00bb:
            java.lang.String r4 = "translationX"
            boolean r4 = r1.equals(r4)
            if (r4 != 0) goto L_0x00c4
            goto L_0x0050
        L_0x00c4:
            r4 = 2
            goto L_0x00dc
        L_0x00c6:
            java.lang.String r4 = "rotationY"
            boolean r4 = r1.equals(r4)
            if (r4 != 0) goto L_0x00cf
            goto L_0x0050
        L_0x00cf:
            r4 = 1
            goto L_0x00dc
        L_0x00d1:
            java.lang.String r4 = "rotationX"
            boolean r4 = r1.equals(r4)
            if (r4 != 0) goto L_0x00db
            goto L_0x0050
        L_0x00db:
            r4 = 0
        L_0x00dc:
            switch(r4) {
                case 0: goto L_0x01f9;
                case 1: goto L_0x01e2;
                case 2: goto L_0x01cb;
                case 3: goto L_0x01b4;
                case 4: goto L_0x019d;
                case 5: goto L_0x0186;
                case 6: goto L_0x016f;
                case 7: goto L_0x0158;
                case 8: goto L_0x0141;
                case 9: goto L_0x012a;
                case 10: goto L_0x0113;
                case 11: goto L_0x00fc;
                default: goto L_0x00df;
            }
        L_0x00df:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "UNKNOWN addValues \""
            r2.append(r3)
            r2.append(r1)
            java.lang.String r1 = "\""
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            java.lang.String r2 = "KeyTimeCycles"
            android.util.Log.e(r2, r1)
            goto L_0x0008
        L_0x00fc:
            float r1 = r10.mAlpha
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r4 = r10.mFramePosition
            float r5 = r10.mAlpha
            float r6 = r10.mWavePeriod
            int r7 = r10.mWaveShape
            float r8 = r10.mWaveOffset
            r3.setPoint(r4, r5, r6, r7, r8)
            goto L_0x0008
        L_0x0113:
            float r1 = r10.mTransitionPathRotate
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r4 = r10.mFramePosition
            float r5 = r10.mTransitionPathRotate
            float r6 = r10.mWavePeriod
            int r7 = r10.mWaveShape
            float r8 = r10.mWaveOffset
            r3.setPoint(r4, r5, r6, r7, r8)
            goto L_0x0008
        L_0x012a:
            float r1 = r10.mElevation
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r4 = r10.mFramePosition
            float r5 = r10.mElevation
            float r6 = r10.mWavePeriod
            int r7 = r10.mWaveShape
            float r8 = r10.mWaveOffset
            r3.setPoint(r4, r5, r6, r7, r8)
            goto L_0x0008
        L_0x0141:
            float r1 = r10.mRotation
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r4 = r10.mFramePosition
            float r5 = r10.mRotation
            float r6 = r10.mWavePeriod
            int r7 = r10.mWaveShape
            float r8 = r10.mWaveOffset
            r3.setPoint(r4, r5, r6, r7, r8)
            goto L_0x0008
        L_0x0158:
            float r1 = r10.mScaleY
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r4 = r10.mFramePosition
            float r5 = r10.mScaleY
            float r6 = r10.mWavePeriod
            int r7 = r10.mWaveShape
            float r8 = r10.mWaveOffset
            r3.setPoint(r4, r5, r6, r7, r8)
            goto L_0x0008
        L_0x016f:
            float r1 = r10.mScaleX
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r4 = r10.mFramePosition
            float r5 = r10.mScaleX
            float r6 = r10.mWavePeriod
            int r7 = r10.mWaveShape
            float r8 = r10.mWaveOffset
            r3.setPoint(r4, r5, r6, r7, r8)
            goto L_0x0008
        L_0x0186:
            float r1 = r10.mProgress
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r4 = r10.mFramePosition
            float r5 = r10.mProgress
            float r6 = r10.mWavePeriod
            int r7 = r10.mWaveShape
            float r8 = r10.mWaveOffset
            r3.setPoint(r4, r5, r6, r7, r8)
            goto L_0x0008
        L_0x019d:
            float r1 = r10.mTranslationZ
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r4 = r10.mFramePosition
            float r5 = r10.mTranslationZ
            float r6 = r10.mWavePeriod
            int r7 = r10.mWaveShape
            float r8 = r10.mWaveOffset
            r3.setPoint(r4, r5, r6, r7, r8)
            goto L_0x0008
        L_0x01b4:
            float r1 = r10.mTranslationY
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r4 = r10.mFramePosition
            float r5 = r10.mTranslationY
            float r6 = r10.mWavePeriod
            int r7 = r10.mWaveShape
            float r8 = r10.mWaveOffset
            r3.setPoint(r4, r5, r6, r7, r8)
            goto L_0x0008
        L_0x01cb:
            float r1 = r10.mTranslationX
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r4 = r10.mFramePosition
            float r5 = r10.mTranslationX
            float r6 = r10.mWavePeriod
            int r7 = r10.mWaveShape
            float r8 = r10.mWaveOffset
            r3.setPoint(r4, r5, r6, r7, r8)
            goto L_0x0008
        L_0x01e2:
            float r1 = r10.mRotationY
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r4 = r10.mFramePosition
            float r5 = r10.mRotationY
            float r6 = r10.mWavePeriod
            int r7 = r10.mWaveShape
            float r8 = r10.mWaveOffset
            r3.setPoint(r4, r5, r6, r7, r8)
            goto L_0x0008
        L_0x01f9:
            float r1 = r10.mRotationX
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r4 = r10.mFramePosition
            float r5 = r10.mRotationX
            float r6 = r10.mWavePeriod
            int r7 = r10.mWaveShape
            float r8 = r10.mWaveOffset
            r3.setPoint(r4, r5, r6, r7, r8)
            goto L_0x0008
        L_0x0210:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.KeyTimeCycle.addTimeValues(java.util.HashMap):void");
    }

    public void setValue(String str, Object obj) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1913008125:
                if (str.equals(Key.MOTIONPROGRESS)) {
                    c = 0;
                    break;
                }
                break;
            case -1812823328:
                if (str.equals("transitionEasing")) {
                    c = 1;
                    break;
                }
                break;
            case -1249320806:
                if (str.equals("rotationX")) {
                    c = 2;
                    break;
                }
                break;
            case -1249320805:
                if (str.equals("rotationY")) {
                    c = 3;
                    break;
                }
                break;
            case -1225497657:
                if (str.equals("translationX")) {
                    c = 4;
                    break;
                }
                break;
            case -1225497656:
                if (str.equals("translationY")) {
                    c = 5;
                    break;
                }
                break;
            case -1225497655:
                if (str.equals("translationZ")) {
                    c = 6;
                    break;
                }
                break;
            case -908189618:
                if (str.equals("scaleX")) {
                    c = 7;
                    break;
                }
                break;
            case -908189617:
                if (str.equals("scaleY")) {
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
            case 184161818:
                if (str.equals("wavePeriod")) {
                    c = 14;
                    break;
                }
                break;
            case 579057826:
                if (str.equals("curveFit")) {
                    c = 15;
                    break;
                }
                break;
            case 1532805160:
                if (str.equals("waveShape")) {
                    c = 16;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                this.mProgress = toFloat(obj);
                return;
            case 1:
                this.mTransitionEasing = obj.toString();
                return;
            case 2:
                this.mRotationX = toFloat(obj);
                return;
            case 3:
                this.mRotationY = toFloat(obj);
                return;
            case 4:
                this.mTranslationX = toFloat(obj);
                return;
            case 5:
                this.mTranslationY = toFloat(obj);
                return;
            case 6:
                this.mTranslationZ = toFloat(obj);
                return;
            case 7:
                this.mScaleX = toFloat(obj);
                return;
            case 8:
                this.mScaleY = toFloat(obj);
                return;
            case 9:
                this.mRotation = toFloat(obj);
                return;
            case 10:
                this.mElevation = toFloat(obj);
                return;
            case 11:
                this.mTransitionPathRotate = toFloat(obj);
                return;
            case 12:
                this.mAlpha = toFloat(obj);
                return;
            case 13:
                this.mWaveOffset = toFloat(obj);
                return;
            case 14:
                this.mWavePeriod = toFloat(obj);
                return;
            case 15:
                this.mCurveFit = toInt(obj);
                return;
            case 16:
                if (obj instanceof Integer) {
                    this.mWaveShape = toInt(obj);
                    return;
                }
                this.mWaveShape = 7;
                this.mCustomWaveShape = obj.toString();
                return;
            default:
                return;
        }
    }

    private static class Loader {
        private static final int ANDROID_ALPHA = 1;
        private static final int ANDROID_ELEVATION = 2;
        private static final int ANDROID_ROTATION = 4;
        private static final int ANDROID_ROTATION_X = 5;
        private static final int ANDROID_ROTATION_Y = 6;
        private static final int ANDROID_SCALE_X = 7;
        private static final int ANDROID_SCALE_Y = 14;
        private static final int ANDROID_TRANSLATION_X = 15;
        private static final int ANDROID_TRANSLATION_Y = 16;
        private static final int ANDROID_TRANSLATION_Z = 17;
        private static final int CURVE_FIT = 13;
        private static final int FRAME_POSITION = 12;
        private static final int PROGRESS = 18;
        private static final int TARGET_ID = 10;
        private static final int TRANSITION_EASING = 9;
        private static final int TRANSITION_PATH_ROTATE = 8;
        private static final int WAVE_OFFSET = 21;
        private static final int WAVE_PERIOD = 20;
        private static final int WAVE_SHAPE = 19;
        private static SparseIntArray mAttrMap;

        private Loader() {
        }

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            mAttrMap = sparseIntArray;
            sparseIntArray.append(R.styleable.KeyTimeCycle_android_alpha, 1);
            mAttrMap.append(R.styleable.KeyTimeCycle_android_elevation, 2);
            mAttrMap.append(R.styleable.KeyTimeCycle_android_rotation, 4);
            mAttrMap.append(R.styleable.KeyTimeCycle_android_rotationX, 5);
            mAttrMap.append(R.styleable.KeyTimeCycle_android_rotationY, 6);
            mAttrMap.append(R.styleable.KeyTimeCycle_android_scaleX, 7);
            mAttrMap.append(R.styleable.KeyTimeCycle_transitionPathRotate, 8);
            mAttrMap.append(R.styleable.KeyTimeCycle_transitionEasing, 9);
            mAttrMap.append(R.styleable.KeyTimeCycle_motionTarget, 10);
            mAttrMap.append(R.styleable.KeyTimeCycle_framePosition, 12);
            mAttrMap.append(R.styleable.KeyTimeCycle_curveFit, 13);
            mAttrMap.append(R.styleable.KeyTimeCycle_android_scaleY, 14);
            mAttrMap.append(R.styleable.KeyTimeCycle_android_translationX, 15);
            mAttrMap.append(R.styleable.KeyTimeCycle_android_translationY, 16);
            mAttrMap.append(R.styleable.KeyTimeCycle_android_translationZ, 17);
            mAttrMap.append(R.styleable.KeyTimeCycle_motionProgress, 18);
            mAttrMap.append(R.styleable.KeyTimeCycle_wavePeriod, 20);
            mAttrMap.append(R.styleable.KeyTimeCycle_waveOffset, 21);
            mAttrMap.append(R.styleable.KeyTimeCycle_waveShape, 19);
        }

        public static void read(KeyTimeCycle keyTimeCycle, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArray.getIndex(i);
                switch (mAttrMap.get(index)) {
                    case 1:
                        float unused = keyTimeCycle.mAlpha = typedArray.getFloat(index, keyTimeCycle.mAlpha);
                        break;
                    case 2:
                        float unused2 = keyTimeCycle.mElevation = typedArray.getDimension(index, keyTimeCycle.mElevation);
                        break;
                    case 4:
                        float unused3 = keyTimeCycle.mRotation = typedArray.getFloat(index, keyTimeCycle.mRotation);
                        break;
                    case 5:
                        float unused4 = keyTimeCycle.mRotationX = typedArray.getFloat(index, keyTimeCycle.mRotationX);
                        break;
                    case 6:
                        float unused5 = keyTimeCycle.mRotationY = typedArray.getFloat(index, keyTimeCycle.mRotationY);
                        break;
                    case 7:
                        float unused6 = keyTimeCycle.mScaleX = typedArray.getFloat(index, keyTimeCycle.mScaleX);
                        break;
                    case 8:
                        float unused7 = keyTimeCycle.mTransitionPathRotate = typedArray.getFloat(index, keyTimeCycle.mTransitionPathRotate);
                        break;
                    case 9:
                        String unused8 = keyTimeCycle.mTransitionEasing = typedArray.getString(index);
                        break;
                    case 10:
                        if (!MotionLayout.IS_IN_EDIT_MODE) {
                            if (typedArray.peekValue(index).type != 3) {
                                keyTimeCycle.mTargetId = typedArray.getResourceId(index, keyTimeCycle.mTargetId);
                                break;
                            } else {
                                keyTimeCycle.mTargetString = typedArray.getString(index);
                                break;
                            }
                        } else {
                            keyTimeCycle.mTargetId = typedArray.getResourceId(index, keyTimeCycle.mTargetId);
                            if (keyTimeCycle.mTargetId != -1) {
                                break;
                            } else {
                                keyTimeCycle.mTargetString = typedArray.getString(index);
                                break;
                            }
                        }
                    case 12:
                        keyTimeCycle.mFramePosition = typedArray.getInt(index, keyTimeCycle.mFramePosition);
                        break;
                    case 13:
                        int unused9 = keyTimeCycle.mCurveFit = typedArray.getInteger(index, keyTimeCycle.mCurveFit);
                        break;
                    case 14:
                        float unused10 = keyTimeCycle.mScaleY = typedArray.getFloat(index, keyTimeCycle.mScaleY);
                        break;
                    case 15:
                        float unused11 = keyTimeCycle.mTranslationX = typedArray.getDimension(index, keyTimeCycle.mTranslationX);
                        break;
                    case 16:
                        float unused12 = keyTimeCycle.mTranslationY = typedArray.getDimension(index, keyTimeCycle.mTranslationY);
                        break;
                    case 17:
                        if (Build.VERSION.SDK_INT < 21) {
                            break;
                        } else {
                            float unused13 = keyTimeCycle.mTranslationZ = typedArray.getDimension(index, keyTimeCycle.mTranslationZ);
                            break;
                        }
                    case 18:
                        float unused14 = keyTimeCycle.mProgress = typedArray.getFloat(index, keyTimeCycle.mProgress);
                        break;
                    case 19:
                        if (typedArray.peekValue(index).type != 3) {
                            int unused15 = keyTimeCycle.mWaveShape = typedArray.getInt(index, keyTimeCycle.mWaveShape);
                            break;
                        } else {
                            String unused16 = keyTimeCycle.mCustomWaveShape = typedArray.getString(index);
                            int unused17 = keyTimeCycle.mWaveShape = 7;
                            break;
                        }
                    case 20:
                        float unused18 = keyTimeCycle.mWavePeriod = typedArray.getFloat(index, keyTimeCycle.mWavePeriod);
                        break;
                    case 21:
                        if (typedArray.peekValue(index).type != 5) {
                            float unused19 = keyTimeCycle.mWaveOffset = typedArray.getFloat(index, keyTimeCycle.mWaveOffset);
                            break;
                        } else {
                            float unused20 = keyTimeCycle.mWaveOffset = typedArray.getDimension(index, keyTimeCycle.mWaveOffset);
                            break;
                        }
                    default:
                        Log.e("KeyTimeCycle", "unused attribute 0x" + Integer.toHexString(index) + "   " + mAttrMap.get(index));
                        break;
                }
            }
        }
    }

    public Key copy(Key key) {
        super.copy(key);
        KeyTimeCycle keyTimeCycle = (KeyTimeCycle) key;
        this.mTransitionEasing = keyTimeCycle.mTransitionEasing;
        this.mCurveFit = keyTimeCycle.mCurveFit;
        this.mWaveShape = keyTimeCycle.mWaveShape;
        this.mWavePeriod = keyTimeCycle.mWavePeriod;
        this.mWaveOffset = keyTimeCycle.mWaveOffset;
        this.mProgress = keyTimeCycle.mProgress;
        this.mAlpha = keyTimeCycle.mAlpha;
        this.mElevation = keyTimeCycle.mElevation;
        this.mRotation = keyTimeCycle.mRotation;
        this.mTransitionPathRotate = keyTimeCycle.mTransitionPathRotate;
        this.mRotationX = keyTimeCycle.mRotationX;
        this.mRotationY = keyTimeCycle.mRotationY;
        this.mScaleX = keyTimeCycle.mScaleX;
        this.mScaleY = keyTimeCycle.mScaleY;
        this.mTranslationX = keyTimeCycle.mTranslationX;
        this.mTranslationY = keyTimeCycle.mTranslationY;
        this.mTranslationZ = keyTimeCycle.mTranslationZ;
        return this;
    }

    public Key clone() {
        return new KeyTimeCycle().copy(this);
    }
}
