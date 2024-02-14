package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.util.SparseArray;
import android.util.Xml;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;

public class StateSet {
    private static final boolean DEBUG = false;
    public static final String TAG = "ConstraintLayoutStates";
    private SparseArray<ConstraintSet> mConstraintSetMap = new SparseArray<>();
    private ConstraintsChangedListener mConstraintsChangedListener = null;
    int mCurrentConstraintNumber = -1;
    int mCurrentStateId = -1;
    ConstraintSet mDefaultConstraintSet;
    int mDefaultState = -1;
    private SparseArray<State> mStateList = new SparseArray<>();

    public StateSet(Context context, XmlPullParser xmlPullParser) {
        load(context, xmlPullParser);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void load(android.content.Context r10, org.xmlpull.v1.XmlPullParser r11) {
        /*
            r9 = this;
            android.util.AttributeSet r0 = android.util.Xml.asAttributeSet(r11)
            int[] r1 = androidx.constraintlayout.widget.R.styleable.StateSet
            android.content.res.TypedArray r0 = r10.obtainStyledAttributes(r0, r1)
            int r1 = r0.getIndexCount()
            r2 = 0
            r3 = 0
        L_0x0010:
            if (r3 >= r1) goto L_0x0025
            int r4 = r0.getIndex(r3)
            int r5 = androidx.constraintlayout.widget.R.styleable.StateSet_defaultState
            if (r4 != r5) goto L_0x0022
            int r5 = r9.mDefaultState
            int r4 = r0.getResourceId(r4, r5)
            r9.mDefaultState = r4
        L_0x0022:
            int r3 = r3 + 1
            goto L_0x0010
        L_0x0025:
            r0.recycle()
            r0 = 0
            int r1 = r11.getEventType()     // Catch:{ XmlPullParserException -> 0x00a3, IOException -> 0x009e }
        L_0x002d:
            r3 = 1
            if (r1 == r3) goto L_0x00a7
            if (r1 == 0) goto L_0x0096
            java.lang.String r4 = "StateSet"
            r5 = 3
            r6 = 2
            if (r1 == r6) goto L_0x0046
            if (r1 == r5) goto L_0x003b
            goto L_0x0099
        L_0x003b:
            java.lang.String r1 = r11.getName()     // Catch:{ XmlPullParserException -> 0x00a3, IOException -> 0x009e }
            boolean r1 = r4.equals(r1)     // Catch:{ XmlPullParserException -> 0x00a3, IOException -> 0x009e }
            if (r1 == 0) goto L_0x0099
            return
        L_0x0046:
            java.lang.String r1 = r11.getName()     // Catch:{ XmlPullParserException -> 0x00a3, IOException -> 0x009e }
            r7 = -1
            int r8 = r1.hashCode()     // Catch:{ XmlPullParserException -> 0x00a3, IOException -> 0x009e }
            switch(r8) {
                case 80204913: goto L_0x006e;
                case 1301459538: goto L_0x0064;
                case 1382829617: goto L_0x005d;
                case 1901439077: goto L_0x0053;
                default: goto L_0x0052;
            }     // Catch:{ XmlPullParserException -> 0x00a3, IOException -> 0x009e }
        L_0x0052:
            goto L_0x0078
        L_0x0053:
            java.lang.String r3 = "Variant"
            boolean r1 = r1.equals(r3)     // Catch:{ XmlPullParserException -> 0x00a3, IOException -> 0x009e }
            if (r1 == 0) goto L_0x0078
            r3 = 3
            goto L_0x0079
        L_0x005d:
            boolean r1 = r1.equals(r4)     // Catch:{ XmlPullParserException -> 0x00a3, IOException -> 0x009e }
            if (r1 == 0) goto L_0x0078
            goto L_0x0079
        L_0x0064:
            java.lang.String r3 = "LayoutDescription"
            boolean r1 = r1.equals(r3)     // Catch:{ XmlPullParserException -> 0x00a3, IOException -> 0x009e }
            if (r1 == 0) goto L_0x0078
            r3 = 0
            goto L_0x0079
        L_0x006e:
            java.lang.String r3 = "State"
            boolean r1 = r1.equals(r3)     // Catch:{ XmlPullParserException -> 0x00a3, IOException -> 0x009e }
            if (r1 == 0) goto L_0x0078
            r3 = 2
            goto L_0x0079
        L_0x0078:
            r3 = -1
        L_0x0079:
            if (r3 == r6) goto L_0x0089
            if (r3 == r5) goto L_0x007e
            goto L_0x0099
        L_0x007e:
            androidx.constraintlayout.widget.StateSet$Variant r1 = new androidx.constraintlayout.widget.StateSet$Variant     // Catch:{ XmlPullParserException -> 0x00a3, IOException -> 0x009e }
            r1.<init>(r10, r11)     // Catch:{ XmlPullParserException -> 0x00a3, IOException -> 0x009e }
            if (r0 == 0) goto L_0x0099
            r0.add(r1)     // Catch:{ XmlPullParserException -> 0x00a3, IOException -> 0x009e }
            goto L_0x0099
        L_0x0089:
            androidx.constraintlayout.widget.StateSet$State r0 = new androidx.constraintlayout.widget.StateSet$State     // Catch:{ XmlPullParserException -> 0x00a3, IOException -> 0x009e }
            r0.<init>(r10, r11)     // Catch:{ XmlPullParserException -> 0x00a3, IOException -> 0x009e }
            android.util.SparseArray<androidx.constraintlayout.widget.StateSet$State> r1 = r9.mStateList     // Catch:{ XmlPullParserException -> 0x00a3, IOException -> 0x009e }
            int r3 = r0.mId     // Catch:{ XmlPullParserException -> 0x00a3, IOException -> 0x009e }
            r1.put(r3, r0)     // Catch:{ XmlPullParserException -> 0x00a3, IOException -> 0x009e }
            goto L_0x0099
        L_0x0096:
            r11.getName()     // Catch:{ XmlPullParserException -> 0x00a3, IOException -> 0x009e }
        L_0x0099:
            int r1 = r11.next()     // Catch:{ XmlPullParserException -> 0x00a3, IOException -> 0x009e }
            goto L_0x002d
        L_0x009e:
            r10 = move-exception
            r10.printStackTrace()
            goto L_0x00a7
        L_0x00a3:
            r10 = move-exception
            r10.printStackTrace()
        L_0x00a7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.StateSet.load(android.content.Context, org.xmlpull.v1.XmlPullParser):void");
    }

    public boolean needsToChange(int i, float f, float f2) {
        int i2 = this.mCurrentStateId;
        if (i2 != i) {
            return true;
        }
        State state = (State) (i == -1 ? this.mStateList.valueAt(0) : this.mStateList.get(i2));
        return (this.mCurrentConstraintNumber == -1 || !state.mVariants.get(this.mCurrentConstraintNumber).match(f, f2)) && this.mCurrentConstraintNumber != state.findMatch(f, f2);
    }

    public void setOnConstraintsChanged(ConstraintsChangedListener constraintsChangedListener) {
        this.mConstraintsChangedListener = constraintsChangedListener;
    }

    public int stateGetConstraintID(int i, int i2, int i3) {
        return updateConstraints(-1, i, (float) i2, (float) i3);
    }

    public int convertToConstraintSet(int i, int i2, float f, float f2) {
        State state = this.mStateList.get(i2);
        if (state == null) {
            return i2;
        }
        if (f != -1.0f && f2 != -1.0f) {
            Variant variant = null;
            Iterator<Variant> it = state.mVariants.iterator();
            while (it.hasNext()) {
                Variant next = it.next();
                if (next.match(f, f2)) {
                    if (i == next.mConstraintID) {
                        return i;
                    }
                    variant = next;
                }
            }
            if (variant != null) {
                return variant.mConstraintID;
            }
            return state.mConstraintID;
        } else if (state.mConstraintID == i) {
            return i;
        } else {
            Iterator<Variant> it2 = state.mVariants.iterator();
            while (it2.hasNext()) {
                if (i == it2.next().mConstraintID) {
                    return i;
                }
            }
            return state.mConstraintID;
        }
    }

    public int updateConstraints(int i, int i2, float f, float f2) {
        State state;
        int findMatch;
        if (i == i2) {
            if (i2 == -1) {
                state = this.mStateList.valueAt(0);
            } else {
                state = this.mStateList.get(this.mCurrentStateId);
            }
            if (state == null) {
                return -1;
            }
            if ((this.mCurrentConstraintNumber == -1 || !state.mVariants.get(i).match(f, f2)) && i != (findMatch = state.findMatch(f, f2))) {
                return findMatch == -1 ? state.mConstraintID : state.mVariants.get(findMatch).mConstraintID;
            }
            return i;
        }
        State state2 = this.mStateList.get(i2);
        if (state2 == null) {
            return -1;
        }
        int findMatch2 = state2.findMatch(f, f2);
        return findMatch2 == -1 ? state2.mConstraintID : state2.mVariants.get(findMatch2).mConstraintID;
    }

    static class State {
        int mConstraintID = -1;
        int mId;
        boolean mIsLayout;
        ArrayList<Variant> mVariants = new ArrayList<>();

        public State(Context context, XmlPullParser xmlPullParser) {
            this.mIsLayout = false;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.State);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == R.styleable.State_android_id) {
                    this.mId = obtainStyledAttributes.getResourceId(index, this.mId);
                } else if (index == R.styleable.State_constraints) {
                    this.mConstraintID = obtainStyledAttributes.getResourceId(index, this.mConstraintID);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.mConstraintID);
                    context.getResources().getResourceName(this.mConstraintID);
                    if ("layout".equals(resourceTypeName)) {
                        this.mIsLayout = true;
                    }
                }
            }
            obtainStyledAttributes.recycle();
        }

        /* access modifiers changed from: package-private */
        public void add(Variant variant) {
            this.mVariants.add(variant);
        }

        public int findMatch(float f, float f2) {
            for (int i = 0; i < this.mVariants.size(); i++) {
                if (this.mVariants.get(i).match(f, f2)) {
                    return i;
                }
            }
            return -1;
        }
    }

    static class Variant {
        int mConstraintID = -1;
        int mId;
        boolean mIsLayout;
        float mMaxHeight = Float.NaN;
        float mMaxWidth = Float.NaN;
        float mMinHeight = Float.NaN;
        float mMinWidth = Float.NaN;

        public Variant(Context context, XmlPullParser xmlPullParser) {
            this.mIsLayout = false;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.Variant);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == R.styleable.Variant_constraints) {
                    this.mConstraintID = obtainStyledAttributes.getResourceId(index, this.mConstraintID);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.mConstraintID);
                    context.getResources().getResourceName(this.mConstraintID);
                    if ("layout".equals(resourceTypeName)) {
                        this.mIsLayout = true;
                    }
                } else if (index == R.styleable.Variant_region_heightLessThan) {
                    this.mMaxHeight = obtainStyledAttributes.getDimension(index, this.mMaxHeight);
                } else if (index == R.styleable.Variant_region_heightMoreThan) {
                    this.mMinHeight = obtainStyledAttributes.getDimension(index, this.mMinHeight);
                } else if (index == R.styleable.Variant_region_widthLessThan) {
                    this.mMaxWidth = obtainStyledAttributes.getDimension(index, this.mMaxWidth);
                } else if (index == R.styleable.Variant_region_widthMoreThan) {
                    this.mMinWidth = obtainStyledAttributes.getDimension(index, this.mMinWidth);
                } else {
                    Log.v("ConstraintLayoutStates", "Unknown tag");
                }
            }
            obtainStyledAttributes.recycle();
        }

        /* access modifiers changed from: package-private */
        public boolean match(float f, float f2) {
            if (!Float.isNaN(this.mMinWidth) && f < this.mMinWidth) {
                return false;
            }
            if (!Float.isNaN(this.mMinHeight) && f2 < this.mMinHeight) {
                return false;
            }
            if (!Float.isNaN(this.mMaxWidth) && f > this.mMaxWidth) {
                return false;
            }
            if (Float.isNaN(this.mMaxHeight) || f2 <= this.mMaxHeight) {
                return true;
            }
            return false;
        }
    }
}
