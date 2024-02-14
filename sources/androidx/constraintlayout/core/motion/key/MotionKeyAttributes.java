package androidx.constraintlayout.core.motion.key;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;

public class MotionKeyAttributes extends MotionKey {
    private static final boolean DEBUG = false;
    public static final int KEY_TYPE = 1;
    static final String NAME = "KeyAttribute";
    private static final String TAG = "KeyAttributes";
    private float mAlpha = Float.NaN;
    private int mCurveFit = -1;
    private float mElevation = Float.NaN;
    private float mPivotX = Float.NaN;
    private float mPivotY = Float.NaN;
    private float mProgress = Float.NaN;
    private float mRotation = Float.NaN;
    private float mRotationX = Float.NaN;
    private float mRotationY = Float.NaN;
    private float mScaleX = Float.NaN;
    private float mScaleY = Float.NaN;
    private String mTransitionEasing;
    private float mTransitionPathRotate = Float.NaN;
    private float mTranslationX = Float.NaN;
    private float mTranslationY = Float.NaN;
    private float mTranslationZ = Float.NaN;
    private int mVisibility = 0;

    public MotionKey clone() {
        return null;
    }

    public MotionKeyAttributes() {
        this.mType = 1;
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
        if (!Float.isNaN(this.mPivotX)) {
            hashSet.add("pivotX");
        }
        if (!Float.isNaN(this.mPivotY)) {
            hashSet.add("pivotY");
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
            hashSet.add("pathRotate");
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
        if (this.mCustom.size() > 0) {
            for (String str : this.mCustom.keySet()) {
                hashSet.add("CUSTOM," + str);
            }
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x009d, code lost:
        if (r1.equals("pivotX") == false) goto L_0x0047;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addValues(java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.utils.SplineSet> r7) {
        /*
            r6 = this;
            java.util.Set r0 = r7.keySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x0008:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x01f9
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r2 = r7.get(r1)
            androidx.constraintlayout.core.motion.utils.SplineSet r2 = (androidx.constraintlayout.core.motion.utils.SplineSet) r2
            if (r2 != 0) goto L_0x001d
            goto L_0x0008
        L_0x001d:
            java.lang.String r3 = "CUSTOM"
            boolean r3 = r1.startsWith(r3)
            r4 = 7
            if (r3 == 0) goto L_0x003c
            java.lang.String r1 = r1.substring(r4)
            java.util.HashMap r3 = r6.mCustom
            java.lang.Object r1 = r3.get(r1)
            androidx.constraintlayout.core.motion.CustomVariable r1 = (androidx.constraintlayout.core.motion.CustomVariable) r1
            if (r1 == 0) goto L_0x0008
            androidx.constraintlayout.core.motion.utils.SplineSet$CustomSpline r2 = (androidx.constraintlayout.core.motion.utils.SplineSet.CustomSpline) r2
            int r3 = r6.mFramePosition
            r2.setPoint((int) r3, (androidx.constraintlayout.core.motion.CustomVariable) r1)
            goto L_0x0008
        L_0x003c:
            r1.hashCode()
            r3 = -1
            int r5 = r1.hashCode()
            switch(r5) {
                case -1249320806: goto L_0x00e5;
                case -1249320805: goto L_0x00d9;
                case -1249320804: goto L_0x00cd;
                case -1225497657: goto L_0x00c1;
                case -1225497656: goto L_0x00b6;
                case -1225497655: goto L_0x00ab;
                case -1001078227: goto L_0x00a0;
                case -987906986: goto L_0x0097;
                case -987906985: goto L_0x008b;
                case -908189618: goto L_0x007e;
                case -908189617: goto L_0x0071;
                case -4379043: goto L_0x0064;
                case 92909918: goto L_0x0057;
                case 803192288: goto L_0x004a;
                default: goto L_0x0047;
            }
        L_0x0047:
            r4 = -1
            goto L_0x00f0
        L_0x004a:
            java.lang.String r4 = "pathRotate"
            boolean r4 = r1.equals(r4)
            if (r4 != 0) goto L_0x0053
            goto L_0x0047
        L_0x0053:
            r4 = 13
            goto L_0x00f0
        L_0x0057:
            java.lang.String r4 = "alpha"
            boolean r4 = r1.equals(r4)
            if (r4 != 0) goto L_0x0060
            goto L_0x0047
        L_0x0060:
            r4 = 12
            goto L_0x00f0
        L_0x0064:
            java.lang.String r4 = "elevation"
            boolean r4 = r1.equals(r4)
            if (r4 != 0) goto L_0x006d
            goto L_0x0047
        L_0x006d:
            r4 = 11
            goto L_0x00f0
        L_0x0071:
            java.lang.String r4 = "scaleY"
            boolean r4 = r1.equals(r4)
            if (r4 != 0) goto L_0x007a
            goto L_0x0047
        L_0x007a:
            r4 = 10
            goto L_0x00f0
        L_0x007e:
            java.lang.String r4 = "scaleX"
            boolean r4 = r1.equals(r4)
            if (r4 != 0) goto L_0x0087
            goto L_0x0047
        L_0x0087:
            r4 = 9
            goto L_0x00f0
        L_0x008b:
            java.lang.String r4 = "pivotY"
            boolean r4 = r1.equals(r4)
            if (r4 != 0) goto L_0x0094
            goto L_0x0047
        L_0x0094:
            r4 = 8
            goto L_0x00f0
        L_0x0097:
            java.lang.String r5 = "pivotX"
            boolean r5 = r1.equals(r5)
            if (r5 != 0) goto L_0x00f0
            goto L_0x0047
        L_0x00a0:
            java.lang.String r4 = "progress"
            boolean r4 = r1.equals(r4)
            if (r4 != 0) goto L_0x00a9
            goto L_0x0047
        L_0x00a9:
            r4 = 6
            goto L_0x00f0
        L_0x00ab:
            java.lang.String r4 = "translationZ"
            boolean r4 = r1.equals(r4)
            if (r4 != 0) goto L_0x00b4
            goto L_0x0047
        L_0x00b4:
            r4 = 5
            goto L_0x00f0
        L_0x00b6:
            java.lang.String r4 = "translationY"
            boolean r4 = r1.equals(r4)
            if (r4 != 0) goto L_0x00bf
            goto L_0x0047
        L_0x00bf:
            r4 = 4
            goto L_0x00f0
        L_0x00c1:
            java.lang.String r4 = "translationX"
            boolean r4 = r1.equals(r4)
            if (r4 != 0) goto L_0x00cb
            goto L_0x0047
        L_0x00cb:
            r4 = 3
            goto L_0x00f0
        L_0x00cd:
            java.lang.String r4 = "rotationZ"
            boolean r4 = r1.equals(r4)
            if (r4 != 0) goto L_0x00d7
            goto L_0x0047
        L_0x00d7:
            r4 = 2
            goto L_0x00f0
        L_0x00d9:
            java.lang.String r4 = "rotationY"
            boolean r4 = r1.equals(r4)
            if (r4 != 0) goto L_0x00e3
            goto L_0x0047
        L_0x00e3:
            r4 = 1
            goto L_0x00f0
        L_0x00e5:
            java.lang.String r4 = "rotationX"
            boolean r4 = r1.equals(r4)
            if (r4 != 0) goto L_0x00ef
            goto L_0x0047
        L_0x00ef:
            r4 = 0
        L_0x00f0:
            switch(r4) {
                case 0: goto L_0x01e8;
                case 1: goto L_0x01d7;
                case 2: goto L_0x01c6;
                case 3: goto L_0x01b5;
                case 4: goto L_0x01a4;
                case 5: goto L_0x0193;
                case 6: goto L_0x0182;
                case 7: goto L_0x0171;
                case 8: goto L_0x0160;
                case 9: goto L_0x014f;
                case 10: goto L_0x013e;
                case 11: goto L_0x012d;
                case 12: goto L_0x011c;
                case 13: goto L_0x010b;
                default: goto L_0x00f3;
            }
        L_0x00f3:
            java.io.PrintStream r2 = java.lang.System.err
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "not supported by KeyAttributes "
            r3.append(r4)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.println(r1)
            goto L_0x0008
        L_0x010b:
            float r1 = r6.mTransitionPathRotate
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mTransitionPathRotate
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x011c:
            float r1 = r6.mAlpha
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mAlpha
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x012d:
            float r1 = r6.mElevation
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mElevation
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x013e:
            float r1 = r6.mScaleY
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mScaleY
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x014f:
            float r1 = r6.mScaleX
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mScaleX
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x0160:
            float r1 = r6.mRotationY
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mPivotY
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x0171:
            float r1 = r6.mRotationX
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mPivotX
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x0182:
            float r1 = r6.mProgress
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mProgress
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x0193:
            float r1 = r6.mTranslationZ
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mTranslationZ
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x01a4:
            float r1 = r6.mTranslationY
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mTranslationY
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x01b5:
            float r1 = r6.mTranslationX
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mTranslationX
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x01c6:
            float r1 = r6.mRotation
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mRotation
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x01d7:
            float r1 = r6.mRotationY
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mRotationY
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x01e8:
            float r1 = r6.mRotationX
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L_0x0008
            int r1 = r6.mFramePosition
            float r3 = r6.mRotationX
            r2.setPoint(r1, r3)
            goto L_0x0008
        L_0x01f9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.motion.key.MotionKeyAttributes.addValues(java.util.HashMap):void");
    }

    public boolean setValue(int i, int i2) {
        if (i == 100) {
            this.mFramePosition = i2;
            return true;
        } else if (i == 301) {
            this.mCurveFit = i2;
            return true;
        } else if (i == 302) {
            this.mVisibility = i2;
            return true;
        } else if (!setValue(i, i2)) {
            return super.setValue(i, i2);
        } else {
            return true;
        }
    }

    public boolean setValue(int i, float f) {
        if (i != 100) {
            switch (i) {
                case 303:
                    this.mAlpha = f;
                    return true;
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
                case 313:
                    this.mPivotX = f;
                    return true;
                case 314:
                    this.mPivotY = f;
                    return true;
                case 315:
                    this.mProgress = f;
                    return true;
                case TypedValues.AttributesType.TYPE_PATH_ROTATE /*316*/:
                    this.mTransitionPathRotate = f;
                    return true;
                default:
                    return super.setValue(i, f);
            }
        } else {
            this.mTransitionPathRotate = f;
            return true;
        }
    }

    public void setInterpolation(HashMap<String, Integer> hashMap) {
        if (!Float.isNaN(this.mAlpha)) {
            hashMap.put("alpha", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mElevation)) {
            hashMap.put("elevation", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mRotation)) {
            hashMap.put("rotationZ", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mRotationX)) {
            hashMap.put("rotationX", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mRotationY)) {
            hashMap.put("rotationY", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mPivotX)) {
            hashMap.put("pivotX", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mPivotY)) {
            hashMap.put("pivotY", Integer.valueOf(this.mCurveFit));
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
            hashMap.put("pathRotate", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mScaleX)) {
            hashMap.put("scaleX", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mScaleY)) {
            hashMap.put("scaleY", Integer.valueOf(this.mCurveFit));
        }
        if (!Float.isNaN(this.mProgress)) {
            hashMap.put("progress", Integer.valueOf(this.mCurveFit));
        }
        if (this.mCustom.size() > 0) {
            for (String str : this.mCustom.keySet()) {
                hashMap.put("CUSTOM," + str, Integer.valueOf(this.mCurveFit));
            }
        }
    }

    public boolean setValue(int i, String str) {
        if (i == 101) {
            this.mTargetString = str;
            return true;
        } else if (i != 317) {
            return super.setValue(i, str);
        } else {
            this.mTransitionEasing = str;
            return true;
        }
    }

    public int getId(String str) {
        return TypedValues.AttributesType.CC.getId(str);
    }

    public int getCurveFit() {
        return this.mCurveFit;
    }

    public void printAttributes() {
        HashSet hashSet = new HashSet();
        getAttributeNames(hashSet);
        PrintStream printStream = System.out;
        printStream.println(" ------------- " + this.mFramePosition + " -------------");
        String[] strArr = (String[]) hashSet.toArray(new String[0]);
        for (int i = 0; i < strArr.length; i++) {
            int id = TypedValues.AttributesType.CC.getId(strArr[i]);
            PrintStream printStream2 = System.out;
            printStream2.println(strArr[i] + ":" + getFloatValue(id));
        }
    }

    private float getFloatValue(int i) {
        if (i == 100) {
            return (float) this.mFramePosition;
        }
        switch (i) {
            case 303:
                return this.mAlpha;
            case 304:
                return this.mTranslationX;
            case 305:
                return this.mTranslationY;
            case 306:
                return this.mTranslationZ;
            case 307:
                return this.mElevation;
            case 308:
                return this.mRotationX;
            case 309:
                return this.mRotationY;
            case 310:
                return this.mRotation;
            case 311:
                return this.mScaleX;
            case 312:
                return this.mScaleY;
            case 313:
                return this.mPivotX;
            case 314:
                return this.mPivotY;
            case 315:
                return this.mProgress;
            case TypedValues.AttributesType.TYPE_PATH_ROTATE /*316*/:
                return this.mTransitionPathRotate;
            default:
                return Float.NaN;
        }
    }
}
