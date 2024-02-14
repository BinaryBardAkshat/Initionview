package androidx.constraintlayout.core.motion.key;

import androidx.constraintlayout.core.motion.CustomVariable;
import androidx.constraintlayout.core.motion.utils.KeyCycleOscillator;
import androidx.constraintlayout.core.motion.utils.SplineSet;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.motion.utils.Utils;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;

public class MotionKeyCycle extends MotionKey {
    public static final int KEY_TYPE = 4;
    static final String NAME = "KeyCycle";
    public static final int SHAPE_BOUNCE = 6;
    public static final int SHAPE_COS_WAVE = 5;
    public static final int SHAPE_REVERSE_SAW_WAVE = 4;
    public static final int SHAPE_SAW_WAVE = 3;
    public static final int SHAPE_SIN_WAVE = 0;
    public static final int SHAPE_SQUARE_WAVE = 1;
    public static final int SHAPE_TRIANGLE_WAVE = 2;
    private static final String TAG = "KeyCycle";
    public static final String WAVE_OFFSET = "waveOffset";
    public static final String WAVE_PERIOD = "wavePeriod";
    public static final String WAVE_PHASE = "wavePhase";
    public static final String WAVE_SHAPE = "waveShape";
    private float mAlpha = Float.NaN;
    private int mCurveFit = 0;
    private String mCustomWaveShape = null;
    private float mElevation = Float.NaN;
    private float mProgress = Float.NaN;
    private float mRotation = Float.NaN;
    private float mRotationX = Float.NaN;
    private float mRotationY = Float.NaN;
    private float mScaleX = Float.NaN;
    private float mScaleY = Float.NaN;
    private String mTransitionEasing = null;
    private float mTransitionPathRotate = Float.NaN;
    private float mTranslationX = Float.NaN;
    private float mTranslationY = Float.NaN;
    private float mTranslationZ = Float.NaN;
    private float mWaveOffset = 0.0f;
    private float mWavePeriod = Float.NaN;
    private float mWavePhase = 0.0f;
    private int mWaveShape = -1;

    public void addValues(HashMap<String, SplineSet> hashMap) {
    }

    public MotionKey clone() {
        return null;
    }

    public MotionKeyCycle() {
        this.mType = 4;
        this.mCustom = new HashMap();
    }

    public void getAttributeNames(HashSet<String> hashSet) {
        if (!Float.isNaN(this.mAlpha)) {
            hashSet.add("alpha");
        }
        if (!Float.isNaN(this.mElevation)) {
            hashSet.add("elevation");
        }
        if (!Float.isNaN(this.mRotation)) {
            hashSet.add("rotationZ");
        }
        if (!Float.isNaN(this.mRotationX)) {
            hashSet.add("rotationX");
        }
        if (!Float.isNaN(this.mRotationY)) {
            hashSet.add("rotationY");
        }
        if (!Float.isNaN(this.mScaleX)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.mScaleY)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.mTransitionPathRotate)) {
            hashSet.add("pathRotate");
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
        if (this.mCustom.size() > 0) {
            for (String str : this.mCustom.keySet()) {
                hashSet.add("CUSTOM," + str);
            }
        }
    }

    public boolean setValue(int i, int i2) {
        if (i == 401) {
            this.mCurveFit = i2;
            return true;
        } else if (i == 421) {
            this.mWaveShape = i2;
            return true;
        } else if (setValue(i, (float) i2)) {
            return true;
        } else {
            return super.setValue(i, i2);
        }
    }

    public boolean setValue(int i, String str) {
        if (i == 420) {
            this.mTransitionEasing = str;
            return true;
        } else if (i != 422) {
            return super.setValue(i, str);
        } else {
            this.mCustomWaveShape = str;
            return true;
        }
    }

    public boolean setValue(int i, float f) {
        if (i == 315) {
            this.mProgress = f;
            return true;
        } else if (i == 403) {
            this.mAlpha = f;
            return true;
        } else if (i != 416) {
            switch (i) {
                case 304:
                    this.mTranslationX = f;
                    return true;
                case 305:
                    this.mTranslationY = f;
                    return true;
                case 306:
                    this.mTranslationZ = f;
                    return true;
                case 307:
                    this.mElevation = f;
                    return true;
                case 308:
                    this.mRotationX = f;
                    return true;
                case 309:
                    this.mRotationY = f;
                    return true;
                case 310:
                    this.mRotation = f;
                    return true;
                case 311:
                    this.mScaleX = f;
                    return true;
                case 312:
                    this.mScaleY = f;
                    return true;
                default:
                    switch (i) {
                        case TypedValues.CycleType.TYPE_WAVE_PERIOD /*423*/:
                            this.mWavePeriod = f;
                            return true;
                        case TypedValues.CycleType.TYPE_WAVE_OFFSET /*424*/:
                            this.mWaveOffset = f;
                            return true;
                        case TypedValues.CycleType.TYPE_WAVE_PHASE /*425*/:
                            this.mWavePhase = f;
                            return true;
                        default:
                            return super.setValue(i, f);
                    }
            }
        } else {
            this.mTransitionPathRotate = f;
            return true;
        }
    }

    public float getValue(String str) {
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
            case -1249320804:
                if (str.equals("rotationZ")) {
                    c = 2;
                    break;
                }
                break;
            case -1225497657:
                if (str.equals("translationX")) {
                    c = 3;
                    break;
                }
                break;
            case -1225497656:
                if (str.equals("translationY")) {
                    c = 4;
                    break;
                }
                break;
            case -1225497655:
                if (str.equals("translationZ")) {
                    c = 5;
                    break;
                }
                break;
            case -1019779949:
                if (str.equals(TypedValues.CycleType.S_WAVE_OFFSET)) {
                    c = 6;
                    break;
                }
                break;
            case -1001078227:
                if (str.equals("progress")) {
                    c = 7;
                    break;
                }
                break;
            case -908189618:
                if (str.equals("scaleX")) {
                    c = 8;
                    break;
                }
                break;
            case -908189617:
                if (str.equals("scaleY")) {
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
            case 92909918:
                if (str.equals("alpha")) {
                    c = 11;
                    break;
                }
                break;
            case 106629499:
                if (str.equals(TypedValues.CycleType.S_WAVE_PHASE)) {
                    c = 12;
                    break;
                }
                break;
            case 803192288:
                if (str.equals("pathRotate")) {
                    c = 13;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return this.mRotationX;
            case 1:
                return this.mRotationY;
            case 2:
                return this.mRotation;
            case 3:
                return this.mTranslationX;
            case 4:
                return this.mTranslationY;
            case 5:
                return this.mTranslationZ;
            case 6:
                return this.mWaveOffset;
            case 7:
                return this.mProgress;
            case 8:
                return this.mScaleX;
            case 9:
                return this.mScaleY;
            case 10:
                return this.mElevation;
            case 11:
                return this.mAlpha;
            case 12:
                return this.mWavePhase;
            case 13:
                return this.mTransitionPathRotate;
            default:
                return Float.NaN;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getId(java.lang.String r3) {
        /*
            r2 = this;
            r3.hashCode()
            int r0 = r3.hashCode()
            r1 = -1
            switch(r0) {
                case -1581616630: goto L_0x010f;
                case -1310311125: goto L_0x0103;
                case -1249320806: goto L_0x00f7;
                case -1249320805: goto L_0x00eb;
                case -1249320804: goto L_0x00df;
                case -1225497657: goto L_0x00d3;
                case -1225497656: goto L_0x00c7;
                case -1225497655: goto L_0x00bb;
                case -1019779949: goto L_0x00ad;
                case -1001078227: goto L_0x009f;
                case -991726143: goto L_0x0091;
                case -987906986: goto L_0x0083;
                case -987906985: goto L_0x0076;
                case -908189618: goto L_0x0069;
                case -908189617: goto L_0x005c;
                case 92909918: goto L_0x004f;
                case 106629499: goto L_0x0042;
                case 579057826: goto L_0x0035;
                case 803192288: goto L_0x0028;
                case 1532805160: goto L_0x001b;
                case 1941332754: goto L_0x000e;
                default: goto L_0x000b;
            }
        L_0x000b:
            r3 = -1
            goto L_0x011a
        L_0x000e:
            java.lang.String r0 = "visibility"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x0017
            goto L_0x000b
        L_0x0017:
            r3 = 20
            goto L_0x011a
        L_0x001b:
            java.lang.String r0 = "waveShape"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x0024
            goto L_0x000b
        L_0x0024:
            r3 = 19
            goto L_0x011a
        L_0x0028:
            java.lang.String r0 = "pathRotate"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x0031
            goto L_0x000b
        L_0x0031:
            r3 = 18
            goto L_0x011a
        L_0x0035:
            java.lang.String r0 = "curveFit"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x003e
            goto L_0x000b
        L_0x003e:
            r3 = 17
            goto L_0x011a
        L_0x0042:
            java.lang.String r0 = "phase"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x004b
            goto L_0x000b
        L_0x004b:
            r3 = 16
            goto L_0x011a
        L_0x004f:
            java.lang.String r0 = "alpha"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x0058
            goto L_0x000b
        L_0x0058:
            r3 = 15
            goto L_0x011a
        L_0x005c:
            java.lang.String r0 = "scaleY"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x0065
            goto L_0x000b
        L_0x0065:
            r3 = 14
            goto L_0x011a
        L_0x0069:
            java.lang.String r0 = "scaleX"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x0072
            goto L_0x000b
        L_0x0072:
            r3 = 13
            goto L_0x011a
        L_0x0076:
            java.lang.String r0 = "pivotY"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x007f
            goto L_0x000b
        L_0x007f:
            r3 = 12
            goto L_0x011a
        L_0x0083:
            java.lang.String r0 = "pivotX"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x008d
            goto L_0x000b
        L_0x008d:
            r3 = 11
            goto L_0x011a
        L_0x0091:
            java.lang.String r0 = "period"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x009b
            goto L_0x000b
        L_0x009b:
            r3 = 10
            goto L_0x011a
        L_0x009f:
            java.lang.String r0 = "progress"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x00a9
            goto L_0x000b
        L_0x00a9:
            r3 = 9
            goto L_0x011a
        L_0x00ad:
            java.lang.String r0 = "offset"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x00b7
            goto L_0x000b
        L_0x00b7:
            r3 = 8
            goto L_0x011a
        L_0x00bb:
            java.lang.String r0 = "translationZ"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x00c5
            goto L_0x000b
        L_0x00c5:
            r3 = 7
            goto L_0x011a
        L_0x00c7:
            java.lang.String r0 = "translationY"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x00d1
            goto L_0x000b
        L_0x00d1:
            r3 = 6
            goto L_0x011a
        L_0x00d3:
            java.lang.String r0 = "translationX"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x00dd
            goto L_0x000b
        L_0x00dd:
            r3 = 5
            goto L_0x011a
        L_0x00df:
            java.lang.String r0 = "rotationZ"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x00e9
            goto L_0x000b
        L_0x00e9:
            r3 = 4
            goto L_0x011a
        L_0x00eb:
            java.lang.String r0 = "rotationY"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x00f5
            goto L_0x000b
        L_0x00f5:
            r3 = 3
            goto L_0x011a
        L_0x00f7:
            java.lang.String r0 = "rotationX"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x0101
            goto L_0x000b
        L_0x0101:
            r3 = 2
            goto L_0x011a
        L_0x0103:
            java.lang.String r0 = "easing"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x010d
            goto L_0x000b
        L_0x010d:
            r3 = 1
            goto L_0x011a
        L_0x010f:
            java.lang.String r0 = "customWave"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x0119
            goto L_0x000b
        L_0x0119:
            r3 = 0
        L_0x011a:
            switch(r3) {
                case 0: goto L_0x015a;
                case 1: goto L_0x0157;
                case 2: goto L_0x0154;
                case 3: goto L_0x0151;
                case 4: goto L_0x014e;
                case 5: goto L_0x014b;
                case 6: goto L_0x0148;
                case 7: goto L_0x0145;
                case 8: goto L_0x0142;
                case 9: goto L_0x013f;
                case 10: goto L_0x013c;
                case 11: goto L_0x0139;
                case 12: goto L_0x0136;
                case 13: goto L_0x0133;
                case 14: goto L_0x0130;
                case 15: goto L_0x012d;
                case 16: goto L_0x012a;
                case 17: goto L_0x0127;
                case 18: goto L_0x0124;
                case 19: goto L_0x0121;
                case 20: goto L_0x011e;
                default: goto L_0x011d;
            }
        L_0x011d:
            return r1
        L_0x011e:
            r3 = 402(0x192, float:5.63E-43)
            return r3
        L_0x0121:
            r3 = 421(0x1a5, float:5.9E-43)
            return r3
        L_0x0124:
            r3 = 416(0x1a0, float:5.83E-43)
            return r3
        L_0x0127:
            r3 = 401(0x191, float:5.62E-43)
            return r3
        L_0x012a:
            r3 = 425(0x1a9, float:5.96E-43)
            return r3
        L_0x012d:
            r3 = 403(0x193, float:5.65E-43)
            return r3
        L_0x0130:
            r3 = 312(0x138, float:4.37E-43)
            return r3
        L_0x0133:
            r3 = 311(0x137, float:4.36E-43)
            return r3
        L_0x0136:
            r3 = 314(0x13a, float:4.4E-43)
            return r3
        L_0x0139:
            r3 = 313(0x139, float:4.39E-43)
            return r3
        L_0x013c:
            r3 = 423(0x1a7, float:5.93E-43)
            return r3
        L_0x013f:
            r3 = 315(0x13b, float:4.41E-43)
            return r3
        L_0x0142:
            r3 = 424(0x1a8, float:5.94E-43)
            return r3
        L_0x0145:
            r3 = 306(0x132, float:4.29E-43)
            return r3
        L_0x0148:
            r3 = 305(0x131, float:4.27E-43)
            return r3
        L_0x014b:
            r3 = 304(0x130, float:4.26E-43)
            return r3
        L_0x014e:
            r3 = 310(0x136, float:4.34E-43)
            return r3
        L_0x0151:
            r3 = 309(0x135, float:4.33E-43)
            return r3
        L_0x0154:
            r3 = 308(0x134, float:4.32E-43)
            return r3
        L_0x0157:
            r3 = 420(0x1a4, float:5.89E-43)
            return r3
        L_0x015a:
            r3 = 422(0x1a6, float:5.91E-43)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.motion.key.MotionKeyCycle.getId(java.lang.String):int");
    }

    public void addCycleValues(HashMap<String, KeyCycleOscillator> hashMap) {
        KeyCycleOscillator keyCycleOscillator;
        KeyCycleOscillator keyCycleOscillator2;
        HashMap<String, KeyCycleOscillator> hashMap2 = hashMap;
        for (String next : hashMap.keySet()) {
            if (next.startsWith("CUSTOM")) {
                CustomVariable customVariable = (CustomVariable) this.mCustom.get(next.substring(7));
                if (!(customVariable == null || customVariable.getType() != 901 || (keyCycleOscillator2 = hashMap2.get(next)) == null)) {
                    keyCycleOscillator2.setPoint(this.mFramePosition, this.mWaveShape, this.mCustomWaveShape, -1, this.mWavePeriod, this.mWaveOffset, this.mWavePhase, customVariable.getValueToInterpolate(), customVariable);
                }
            } else {
                float value = getValue(next);
                if (!Float.isNaN(value) && (keyCycleOscillator = hashMap2.get(next)) != null) {
                    keyCycleOscillator.setPoint(this.mFramePosition, this.mWaveShape, this.mCustomWaveShape, -1, this.mWavePeriod, this.mWaveOffset, this.mWavePhase, value);
                }
            }
        }
    }

    public void dump() {
        PrintStream printStream = System.out;
        printStream.println("MotionKeyCycle{mWaveShape=" + this.mWaveShape + ", mWavePeriod=" + this.mWavePeriod + ", mWaveOffset=" + this.mWaveOffset + ", mWavePhase=" + this.mWavePhase + ", mRotation=" + this.mRotation + '}');
    }

    public void printAttributes() {
        HashSet hashSet = new HashSet();
        getAttributeNames(hashSet);
        Utils.log(" ------------- " + this.mFramePosition + " -------------");
        Utils.log("MotionKeyCycle{Shape=" + this.mWaveShape + ", Period=" + this.mWavePeriod + ", Offset=" + this.mWaveOffset + ", Phase=" + this.mWavePhase + '}');
        String[] strArr = (String[]) hashSet.toArray(new String[0]);
        for (int i = 0; i < strArr.length; i++) {
            TypedValues.AttributesType.CC.getId(strArr[i]);
            Utils.log(strArr[i] + ":" + getValue(strArr[i]));
        }
    }
}
