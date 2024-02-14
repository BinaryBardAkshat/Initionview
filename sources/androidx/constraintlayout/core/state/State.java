package androidx.constraintlayout.core.state;

import androidx.constraintlayout.core.state.helpers.AlignHorizontallyReference;
import androidx.constraintlayout.core.state.helpers.AlignVerticallyReference;
import androidx.constraintlayout.core.state.helpers.BarrierReference;
import androidx.constraintlayout.core.state.helpers.GuidelineReference;
import androidx.constraintlayout.core.state.helpers.HorizontalChainReference;
import androidx.constraintlayout.core.state.helpers.VerticalChainReference;
import java.util.ArrayList;
import java.util.HashMap;

public class State {
    static final int CONSTRAINT_RATIO = 2;
    static final int CONSTRAINT_SPREAD = 0;
    static final int CONSTRAINT_WRAP = 1;
    public static final Integer PARENT = 0;
    static final int UNKNOWN = -1;
    protected HashMap<Object, HelperReference> mHelperReferences = new HashMap<>();
    public final ConstraintReference mParent;
    protected HashMap<Object, Reference> mReferences = new HashMap<>();
    HashMap<String, ArrayList<String>> mTags = new HashMap<>();
    private int numHelpers;

    public enum Chain {
        SPREAD,
        SPREAD_INSIDE,
        PACKED
    }

    public enum Constraint {
        LEFT_TO_LEFT,
        LEFT_TO_RIGHT,
        RIGHT_TO_LEFT,
        RIGHT_TO_RIGHT,
        START_TO_START,
        START_TO_END,
        END_TO_START,
        END_TO_END,
        TOP_TO_TOP,
        TOP_TO_BOTTOM,
        BOTTOM_TO_TOP,
        BOTTOM_TO_BOTTOM,
        BASELINE_TO_BASELINE,
        BASELINE_TO_TOP,
        BASELINE_TO_BOTTOM,
        CENTER_HORIZONTALLY,
        CENTER_VERTICALLY,
        CIRCULAR_CONSTRAINT
    }

    public enum Direction {
        LEFT,
        RIGHT,
        START,
        END,
        TOP,
        BOTTOM
    }

    public enum Helper {
        HORIZONTAL_CHAIN,
        VERTICAL_CHAIN,
        ALIGN_HORIZONTALLY,
        ALIGN_VERTICALLY,
        BARRIER,
        LAYER,
        FLOW
    }

    public State() {
        ConstraintReference constraintReference = new ConstraintReference(this);
        this.mParent = constraintReference;
        this.numHelpers = 0;
        this.mReferences.put(PARENT, constraintReference);
    }

    public void reset() {
        this.mHelperReferences.clear();
        this.mTags.clear();
    }

    public int convertDimension(Object obj) {
        if (obj instanceof Float) {
            return ((Float) obj).intValue();
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public ConstraintReference createConstraintReference(Object obj) {
        return new ConstraintReference(this);
    }

    public boolean sameFixedWidth(int i) {
        return this.mParent.getWidth().equalsFixedValue(i);
    }

    public boolean sameFixedHeight(int i) {
        return this.mParent.getHeight().equalsFixedValue(i);
    }

    public State width(Dimension dimension) {
        return setWidth(dimension);
    }

    public State height(Dimension dimension) {
        return setHeight(dimension);
    }

    public State setWidth(Dimension dimension) {
        this.mParent.setWidth(dimension);
        return this;
    }

    public State setHeight(Dimension dimension) {
        this.mParent.setHeight(dimension);
        return this;
    }

    /* access modifiers changed from: package-private */
    public Reference reference(Object obj) {
        return this.mReferences.get(obj);
    }

    public ConstraintReference constraints(Object obj) {
        Reference reference = this.mReferences.get(obj);
        if (reference == null) {
            reference = createConstraintReference(obj);
            this.mReferences.put(obj, reference);
            reference.setKey(obj);
        }
        if (reference instanceof ConstraintReference) {
            return (ConstraintReference) reference;
        }
        return null;
    }

    private String createHelperKey() {
        StringBuilder sb = new StringBuilder();
        sb.append("__HELPER_KEY_");
        int i = this.numHelpers;
        this.numHelpers = i + 1;
        sb.append(i);
        sb.append("__");
        return sb.toString();
    }

    public HelperReference helper(Object obj, Helper helper) {
        HelperReference helperReference;
        if (obj == null) {
            obj = createHelperKey();
        }
        HelperReference helperReference2 = this.mHelperReferences.get(obj);
        if (helperReference2 == null) {
            int i = AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$state$State$Helper[helper.ordinal()];
            if (i == 1) {
                helperReference = new HorizontalChainReference(this);
            } else if (i == 2) {
                helperReference = new VerticalChainReference(this);
            } else if (i == 3) {
                helperReference = new AlignHorizontallyReference(this);
            } else if (i == 4) {
                helperReference = new AlignVerticallyReference(this);
            } else if (i != 5) {
                helperReference2 = new HelperReference(this, helper);
                helperReference2.setKey(obj);
                this.mHelperReferences.put(obj, helperReference2);
            } else {
                helperReference = new BarrierReference(this);
            }
            helperReference2 = helperReference;
            helperReference2.setKey(obj);
            this.mHelperReferences.put(obj, helperReference2);
        }
        return helperReference2;
    }

    /* renamed from: androidx.constraintlayout.core.state.State$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$constraintlayout$core$state$State$Helper;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                androidx.constraintlayout.core.state.State$Helper[] r0 = androidx.constraintlayout.core.state.State.Helper.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$androidx$constraintlayout$core$state$State$Helper = r0
                androidx.constraintlayout.core.state.State$Helper r1 = androidx.constraintlayout.core.state.State.Helper.HORIZONTAL_CHAIN     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$androidx$constraintlayout$core$state$State$Helper     // Catch:{ NoSuchFieldError -> 0x001d }
                androidx.constraintlayout.core.state.State$Helper r1 = androidx.constraintlayout.core.state.State.Helper.VERTICAL_CHAIN     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$androidx$constraintlayout$core$state$State$Helper     // Catch:{ NoSuchFieldError -> 0x0028 }
                androidx.constraintlayout.core.state.State$Helper r1 = androidx.constraintlayout.core.state.State.Helper.ALIGN_HORIZONTALLY     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$androidx$constraintlayout$core$state$State$Helper     // Catch:{ NoSuchFieldError -> 0x0033 }
                androidx.constraintlayout.core.state.State$Helper r1 = androidx.constraintlayout.core.state.State.Helper.ALIGN_VERTICALLY     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$androidx$constraintlayout$core$state$State$Helper     // Catch:{ NoSuchFieldError -> 0x003e }
                androidx.constraintlayout.core.state.State$Helper r1 = androidx.constraintlayout.core.state.State.Helper.BARRIER     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.state.State.AnonymousClass1.<clinit>():void");
        }
    }

    public GuidelineReference horizontalGuideline(Object obj) {
        return guideline(obj, 0);
    }

    public GuidelineReference verticalGuideline(Object obj) {
        return guideline(obj, 1);
    }

    public GuidelineReference guideline(Object obj, int i) {
        ConstraintReference constraints = constraints(obj);
        if (constraints.getFacade() == null || !(constraints.getFacade() instanceof GuidelineReference)) {
            GuidelineReference guidelineReference = new GuidelineReference(this);
            guidelineReference.setOrientation(i);
            guidelineReference.setKey(obj);
            constraints.setFacade(guidelineReference);
        }
        return (GuidelineReference) constraints.getFacade();
    }

    public BarrierReference barrier(Object obj, Direction direction) {
        ConstraintReference constraints = constraints(obj);
        if (constraints.getFacade() == null || !(constraints.getFacade() instanceof BarrierReference)) {
            BarrierReference barrierReference = new BarrierReference(this);
            barrierReference.setBarrierDirection(direction);
            constraints.setFacade(barrierReference);
        }
        return (BarrierReference) constraints.getFacade();
    }

    public VerticalChainReference verticalChain() {
        return (VerticalChainReference) helper((Object) null, Helper.VERTICAL_CHAIN);
    }

    public VerticalChainReference verticalChain(Object... objArr) {
        VerticalChainReference verticalChainReference = (VerticalChainReference) helper((Object) null, Helper.VERTICAL_CHAIN);
        verticalChainReference.add(objArr);
        return verticalChainReference;
    }

    public HorizontalChainReference horizontalChain() {
        return (HorizontalChainReference) helper((Object) null, Helper.HORIZONTAL_CHAIN);
    }

    public HorizontalChainReference horizontalChain(Object... objArr) {
        HorizontalChainReference horizontalChainReference = (HorizontalChainReference) helper((Object) null, Helper.HORIZONTAL_CHAIN);
        horizontalChainReference.add(objArr);
        return horizontalChainReference;
    }

    public AlignHorizontallyReference centerHorizontally(Object... objArr) {
        AlignHorizontallyReference alignHorizontallyReference = (AlignHorizontallyReference) helper((Object) null, Helper.ALIGN_HORIZONTALLY);
        alignHorizontallyReference.add(objArr);
        return alignHorizontallyReference;
    }

    public AlignVerticallyReference centerVertically(Object... objArr) {
        AlignVerticallyReference alignVerticallyReference = (AlignVerticallyReference) helper((Object) null, Helper.ALIGN_VERTICALLY);
        alignVerticallyReference.add(objArr);
        return alignVerticallyReference;
    }

    public void directMapping() {
        for (Object next : this.mReferences.keySet()) {
            ConstraintReference constraints = constraints(next);
            if (constraints instanceof ConstraintReference) {
                constraints.setView(next);
            }
        }
    }

    public void map(Object obj, Object obj2) {
        ConstraintReference constraints = constraints(obj);
        if (constraints instanceof ConstraintReference) {
            constraints.setView(obj2);
        }
    }

    public void setTag(String str, String str2) {
        ArrayList arrayList;
        ConstraintReference constraints = constraints(str);
        if (constraints instanceof ConstraintReference) {
            constraints.setTag(str2);
            if (!this.mTags.containsKey(str2)) {
                arrayList = new ArrayList();
                this.mTags.put(str2, arrayList);
            } else {
                arrayList = this.mTags.get(str2);
            }
            arrayList.add(str);
        }
    }

    public ArrayList<String> getIdsForTag(String str) {
        if (this.mTags.containsKey(str)) {
            return this.mTags.get(str);
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:50:0x014d, code lost:
        r1 = (androidx.constraintlayout.core.state.HelperReference) r0.getFacade();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void apply(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r8) {
        /*
            r7 = this;
            r8.removeAllChildren()
            androidx.constraintlayout.core.state.ConstraintReference r0 = r7.mParent
            androidx.constraintlayout.core.state.Dimension r0 = r0.getWidth()
            r1 = 0
            r0.apply(r7, r8, r1)
            androidx.constraintlayout.core.state.ConstraintReference r0 = r7.mParent
            androidx.constraintlayout.core.state.Dimension r0 = r0.getHeight()
            r1 = 1
            r0.apply(r7, r8, r1)
            java.util.HashMap<java.lang.Object, androidx.constraintlayout.core.state.HelperReference> r0 = r7.mHelperReferences
            java.util.Set r0 = r0.keySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x0021:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x004b
            java.lang.Object r1 = r0.next()
            java.util.HashMap<java.lang.Object, androidx.constraintlayout.core.state.HelperReference> r2 = r7.mHelperReferences
            java.lang.Object r2 = r2.get(r1)
            androidx.constraintlayout.core.state.HelperReference r2 = (androidx.constraintlayout.core.state.HelperReference) r2
            androidx.constraintlayout.core.widgets.HelperWidget r2 = r2.getHelperWidget()
            if (r2 == 0) goto L_0x0021
            java.util.HashMap<java.lang.Object, androidx.constraintlayout.core.state.Reference> r3 = r7.mReferences
            java.lang.Object r3 = r3.get(r1)
            androidx.constraintlayout.core.state.Reference r3 = (androidx.constraintlayout.core.state.Reference) r3
            if (r3 != 0) goto L_0x0047
            androidx.constraintlayout.core.state.ConstraintReference r3 = r7.constraints(r1)
        L_0x0047:
            r3.setConstraintWidget(r2)
            goto L_0x0021
        L_0x004b:
            java.util.HashMap<java.lang.Object, androidx.constraintlayout.core.state.Reference> r0 = r7.mReferences
            java.util.Set r0 = r0.keySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x0055:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0091
            java.lang.Object r1 = r0.next()
            java.util.HashMap<java.lang.Object, androidx.constraintlayout.core.state.Reference> r2 = r7.mReferences
            java.lang.Object r2 = r2.get(r1)
            androidx.constraintlayout.core.state.Reference r2 = (androidx.constraintlayout.core.state.Reference) r2
            androidx.constraintlayout.core.state.ConstraintReference r3 = r7.mParent
            if (r2 == r3) goto L_0x0055
            androidx.constraintlayout.core.state.helpers.Facade r3 = r2.getFacade()
            boolean r3 = r3 instanceof androidx.constraintlayout.core.state.HelperReference
            if (r3 == 0) goto L_0x0055
            androidx.constraintlayout.core.state.helpers.Facade r2 = r2.getFacade()
            androidx.constraintlayout.core.state.HelperReference r2 = (androidx.constraintlayout.core.state.HelperReference) r2
            androidx.constraintlayout.core.widgets.HelperWidget r2 = r2.getHelperWidget()
            if (r2 == 0) goto L_0x0055
            java.util.HashMap<java.lang.Object, androidx.constraintlayout.core.state.Reference> r3 = r7.mReferences
            java.lang.Object r3 = r3.get(r1)
            androidx.constraintlayout.core.state.Reference r3 = (androidx.constraintlayout.core.state.Reference) r3
            if (r3 != 0) goto L_0x008d
            androidx.constraintlayout.core.state.ConstraintReference r3 = r7.constraints(r1)
        L_0x008d:
            r3.setConstraintWidget(r2)
            goto L_0x0055
        L_0x0091:
            java.util.HashMap<java.lang.Object, androidx.constraintlayout.core.state.Reference> r0 = r7.mReferences
            java.util.Set r0 = r0.keySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x009b:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x00d7
            java.lang.Object r1 = r0.next()
            java.util.HashMap<java.lang.Object, androidx.constraintlayout.core.state.Reference> r2 = r7.mReferences
            java.lang.Object r1 = r2.get(r1)
            androidx.constraintlayout.core.state.Reference r1 = (androidx.constraintlayout.core.state.Reference) r1
            androidx.constraintlayout.core.state.ConstraintReference r2 = r7.mParent
            if (r1 == r2) goto L_0x00d3
            androidx.constraintlayout.core.widgets.ConstraintWidget r2 = r1.getConstraintWidget()
            java.lang.Object r3 = r1.getKey()
            java.lang.String r3 = r3.toString()
            r2.setDebugName(r3)
            r3 = 0
            r2.setParent(r3)
            androidx.constraintlayout.core.state.helpers.Facade r3 = r1.getFacade()
            boolean r3 = r3 instanceof androidx.constraintlayout.core.state.helpers.GuidelineReference
            if (r3 == 0) goto L_0x00cf
            r1.apply()
        L_0x00cf:
            r8.add((androidx.constraintlayout.core.widgets.ConstraintWidget) r2)
            goto L_0x009b
        L_0x00d3:
            r1.setConstraintWidget(r8)
            goto L_0x009b
        L_0x00d7:
            java.util.HashMap<java.lang.Object, androidx.constraintlayout.core.state.HelperReference> r8 = r7.mHelperReferences
            java.util.Set r8 = r8.keySet()
            java.util.Iterator r8 = r8.iterator()
        L_0x00e1:
            boolean r0 = r8.hasNext()
            if (r0 == 0) goto L_0x0125
            java.lang.Object r0 = r8.next()
            java.util.HashMap<java.lang.Object, androidx.constraintlayout.core.state.HelperReference> r1 = r7.mHelperReferences
            java.lang.Object r0 = r1.get(r0)
            androidx.constraintlayout.core.state.HelperReference r0 = (androidx.constraintlayout.core.state.HelperReference) r0
            androidx.constraintlayout.core.widgets.HelperWidget r1 = r0.getHelperWidget()
            if (r1 == 0) goto L_0x0121
            java.util.ArrayList<java.lang.Object> r1 = r0.mReferences
            java.util.Iterator r1 = r1.iterator()
        L_0x00ff:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x011d
            java.lang.Object r2 = r1.next()
            java.util.HashMap<java.lang.Object, androidx.constraintlayout.core.state.Reference> r3 = r7.mReferences
            java.lang.Object r2 = r3.get(r2)
            androidx.constraintlayout.core.state.Reference r2 = (androidx.constraintlayout.core.state.Reference) r2
            androidx.constraintlayout.core.widgets.HelperWidget r3 = r0.getHelperWidget()
            androidx.constraintlayout.core.widgets.ConstraintWidget r2 = r2.getConstraintWidget()
            r3.add(r2)
            goto L_0x00ff
        L_0x011d:
            r0.apply()
            goto L_0x00e1
        L_0x0121:
            r0.apply()
            goto L_0x00e1
        L_0x0125:
            java.util.HashMap<java.lang.Object, androidx.constraintlayout.core.state.Reference> r8 = r7.mReferences
            java.util.Set r8 = r8.keySet()
            java.util.Iterator r8 = r8.iterator()
        L_0x012f:
            boolean r0 = r8.hasNext()
            if (r0 == 0) goto L_0x01a4
            java.lang.Object r0 = r8.next()
            java.util.HashMap<java.lang.Object, androidx.constraintlayout.core.state.Reference> r1 = r7.mReferences
            java.lang.Object r0 = r1.get(r0)
            androidx.constraintlayout.core.state.Reference r0 = (androidx.constraintlayout.core.state.Reference) r0
            androidx.constraintlayout.core.state.ConstraintReference r1 = r7.mParent
            if (r0 == r1) goto L_0x012f
            androidx.constraintlayout.core.state.helpers.Facade r1 = r0.getFacade()
            boolean r1 = r1 instanceof androidx.constraintlayout.core.state.HelperReference
            if (r1 == 0) goto L_0x012f
            androidx.constraintlayout.core.state.helpers.Facade r1 = r0.getFacade()
            androidx.constraintlayout.core.state.HelperReference r1 = (androidx.constraintlayout.core.state.HelperReference) r1
            androidx.constraintlayout.core.widgets.HelperWidget r2 = r1.getHelperWidget()
            if (r2 == 0) goto L_0x012f
            java.util.ArrayList<java.lang.Object> r1 = r1.mReferences
            java.util.Iterator r1 = r1.iterator()
        L_0x015f:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x01a0
            java.lang.Object r3 = r1.next()
            java.util.HashMap<java.lang.Object, androidx.constraintlayout.core.state.Reference> r4 = r7.mReferences
            java.lang.Object r4 = r4.get(r3)
            androidx.constraintlayout.core.state.Reference r4 = (androidx.constraintlayout.core.state.Reference) r4
            if (r4 == 0) goto L_0x017b
            androidx.constraintlayout.core.widgets.ConstraintWidget r3 = r4.getConstraintWidget()
            r2.add(r3)
            goto L_0x015f
        L_0x017b:
            boolean r4 = r3 instanceof androidx.constraintlayout.core.state.Reference
            if (r4 == 0) goto L_0x0189
            androidx.constraintlayout.core.state.Reference r3 = (androidx.constraintlayout.core.state.Reference) r3
            androidx.constraintlayout.core.widgets.ConstraintWidget r3 = r3.getConstraintWidget()
            r2.add(r3)
            goto L_0x015f
        L_0x0189:
            java.io.PrintStream r4 = java.lang.System.out
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "couldn't find reference for "
            r5.append(r6)
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            r4.println(r3)
            goto L_0x015f
        L_0x01a0:
            r0.apply()
            goto L_0x012f
        L_0x01a4:
            java.util.HashMap<java.lang.Object, androidx.constraintlayout.core.state.Reference> r8 = r7.mReferences
            java.util.Set r8 = r8.keySet()
            java.util.Iterator r8 = r8.iterator()
        L_0x01ae:
            boolean r0 = r8.hasNext()
            if (r0 == 0) goto L_0x01d2
            java.lang.Object r0 = r8.next()
            java.util.HashMap<java.lang.Object, androidx.constraintlayout.core.state.Reference> r1 = r7.mReferences
            java.lang.Object r1 = r1.get(r0)
            androidx.constraintlayout.core.state.Reference r1 = (androidx.constraintlayout.core.state.Reference) r1
            r1.apply()
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r1.getConstraintWidget()
            if (r1 == 0) goto L_0x01ae
            if (r0 == 0) goto L_0x01ae
            java.lang.String r0 = r0.toString()
            r1.stringId = r0
            goto L_0x01ae
        L_0x01d2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.state.State.apply(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer):void");
    }
}
