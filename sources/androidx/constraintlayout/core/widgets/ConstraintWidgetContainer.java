package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.Metrics;
import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.constraintlayout.core.widgets.analyzer.DependencyGraph;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class ConstraintWidgetContainer extends WidgetContainer {
    private static final boolean DEBUG = false;
    static final boolean DEBUG_GRAPH = false;
    private static final boolean DEBUG_LAYOUT = false;
    private static final int MAX_ITERATIONS = 8;
    static int myCounter;
    private WeakReference<ConstraintAnchor> horizontalWrapMax = null;
    private WeakReference<ConstraintAnchor> horizontalWrapMin = null;
    BasicMeasure mBasicMeasureSolver = new BasicMeasure(this);
    int mDebugSolverPassCount = 0;
    public DependencyGraph mDependencyGraph = new DependencyGraph(this);
    public boolean mGroupsWrapOptimized = false;
    private boolean mHeightMeasuredTooSmall = false;
    ChainHead[] mHorizontalChainsArray = new ChainHead[4];
    public int mHorizontalChainsSize = 0;
    public boolean mHorizontalWrapOptimized = false;
    private boolean mIsRtl = false;
    public BasicMeasure.Measure mMeasure = new BasicMeasure.Measure();
    protected BasicMeasure.Measurer mMeasurer = null;
    public Metrics mMetrics;
    private int mOptimizationLevel = 257;
    int mPaddingBottom;
    int mPaddingLeft;
    int mPaddingRight;
    int mPaddingTop;
    public boolean mSkipSolver = false;
    protected LinearSystem mSystem = new LinearSystem();
    ChainHead[] mVerticalChainsArray = new ChainHead[4];
    public int mVerticalChainsSize = 0;
    public boolean mVerticalWrapOptimized = false;
    private boolean mWidthMeasuredTooSmall = false;
    public int mWrapFixedHeight = 0;
    public int mWrapFixedWidth = 0;
    private int pass;
    private WeakReference<ConstraintAnchor> verticalWrapMax = null;
    private WeakReference<ConstraintAnchor> verticalWrapMin = null;
    HashSet<ConstraintWidget> widgetsToAdd = new HashSet<>();

    public String getType() {
        return "ConstraintLayout";
    }

    public boolean handlesInternalConstraints() {
        return false;
    }

    public void invalidateGraph() {
        this.mDependencyGraph.invalidateGraph();
    }

    public void invalidateMeasures() {
        this.mDependencyGraph.invalidateMeasures();
    }

    public boolean directMeasure(boolean z) {
        return this.mDependencyGraph.directMeasure(z);
    }

    public boolean directMeasureSetup(boolean z) {
        return this.mDependencyGraph.directMeasureSetup(z);
    }

    public boolean directMeasureWithOrientation(boolean z, int i) {
        return this.mDependencyGraph.directMeasureWithOrientation(z, i);
    }

    public void defineTerminalWidgets() {
        this.mDependencyGraph.defineTerminalWidgets(getHorizontalDimensionBehaviour(), getVerticalDimensionBehaviour());
    }

    public long measure(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        int i10 = i8;
        this.mPaddingLeft = i10;
        int i11 = i9;
        this.mPaddingTop = i11;
        return this.mBasicMeasureSolver.solverMeasure(this, i, i10, i11, i2, i3, i4, i5, i6, i7);
    }

    public void updateHierarchy() {
        this.mBasicMeasureSolver.updateHierarchy(this);
    }

    public void setMeasurer(BasicMeasure.Measurer measurer) {
        this.mMeasurer = measurer;
        this.mDependencyGraph.setMeasurer(measurer);
    }

    public BasicMeasure.Measurer getMeasurer() {
        return this.mMeasurer;
    }

    public void fillMetrics(Metrics metrics) {
        this.mMetrics = metrics;
        this.mSystem.fillMetrics(metrics);
    }

    public ConstraintWidgetContainer() {
    }

    public ConstraintWidgetContainer(int i, int i2, int i3, int i4) {
        super(i, i2, i3, i4);
    }

    public ConstraintWidgetContainer(int i, int i2) {
        super(i, i2);
    }

    public ConstraintWidgetContainer(String str, int i, int i2) {
        super(i, i2);
        setDebugName(str);
    }

    public void setOptimizationLevel(int i) {
        this.mOptimizationLevel = i;
        LinearSystem.USE_DEPENDENCY_ORDERING = optimizeFor(512);
    }

    public int getOptimizationLevel() {
        return this.mOptimizationLevel;
    }

    public boolean optimizeFor(int i) {
        return (this.mOptimizationLevel & i) == i;
    }

    public void reset() {
        this.mSystem.reset();
        this.mPaddingLeft = 0;
        this.mPaddingRight = 0;
        this.mPaddingTop = 0;
        this.mPaddingBottom = 0;
        this.mSkipSolver = false;
        super.reset();
    }

    public boolean isWidthMeasuredTooSmall() {
        return this.mWidthMeasuredTooSmall;
    }

    public boolean isHeightMeasuredTooSmall() {
        return this.mHeightMeasuredTooSmall;
    }

    /* access modifiers changed from: package-private */
    public void addVerticalWrapMinVariable(ConstraintAnchor constraintAnchor) {
        WeakReference<ConstraintAnchor> weakReference = this.verticalWrapMin;
        if (weakReference == null || weakReference.get() == null || constraintAnchor.getFinalValue() > ((ConstraintAnchor) this.verticalWrapMin.get()).getFinalValue()) {
            this.verticalWrapMin = new WeakReference<>(constraintAnchor);
        }
    }

    public void addHorizontalWrapMinVariable(ConstraintAnchor constraintAnchor) {
        WeakReference<ConstraintAnchor> weakReference = this.horizontalWrapMin;
        if (weakReference == null || weakReference.get() == null || constraintAnchor.getFinalValue() > ((ConstraintAnchor) this.horizontalWrapMin.get()).getFinalValue()) {
            this.horizontalWrapMin = new WeakReference<>(constraintAnchor);
        }
    }

    /* access modifiers changed from: package-private */
    public void addVerticalWrapMaxVariable(ConstraintAnchor constraintAnchor) {
        WeakReference<ConstraintAnchor> weakReference = this.verticalWrapMax;
        if (weakReference == null || weakReference.get() == null || constraintAnchor.getFinalValue() > ((ConstraintAnchor) this.verticalWrapMax.get()).getFinalValue()) {
            this.verticalWrapMax = new WeakReference<>(constraintAnchor);
        }
    }

    public void addHorizontalWrapMaxVariable(ConstraintAnchor constraintAnchor) {
        WeakReference<ConstraintAnchor> weakReference = this.horizontalWrapMax;
        if (weakReference == null || weakReference.get() == null || constraintAnchor.getFinalValue() > ((ConstraintAnchor) this.horizontalWrapMax.get()).getFinalValue()) {
            this.horizontalWrapMax = new WeakReference<>(constraintAnchor);
        }
    }

    private void addMinWrap(ConstraintAnchor constraintAnchor, SolverVariable solverVariable) {
        this.mSystem.addGreaterThan(this.mSystem.createObjectVariable(constraintAnchor), solverVariable, 0, 5);
    }

    private void addMaxWrap(ConstraintAnchor constraintAnchor, SolverVariable solverVariable) {
        this.mSystem.addGreaterThan(solverVariable, this.mSystem.createObjectVariable(constraintAnchor), 0, 5);
    }

    public boolean addChildrenToSolver(LinearSystem linearSystem) {
        boolean optimizeFor = optimizeFor(64);
        addToSolver(linearSystem, optimizeFor);
        int size = this.mChildren.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i);
            constraintWidget.setInBarrier(0, false);
            constraintWidget.setInBarrier(1, false);
            if (constraintWidget instanceof Barrier) {
                z = true;
            }
        }
        if (z) {
            for (int i2 = 0; i2 < size; i2++) {
                ConstraintWidget constraintWidget2 = (ConstraintWidget) this.mChildren.get(i2);
                if (constraintWidget2 instanceof Barrier) {
                    ((Barrier) constraintWidget2).markWidgets();
                }
            }
        }
        this.widgetsToAdd.clear();
        for (int i3 = 0; i3 < size; i3++) {
            ConstraintWidget constraintWidget3 = (ConstraintWidget) this.mChildren.get(i3);
            if (constraintWidget3.addFirst()) {
                if (constraintWidget3 instanceof VirtualLayout) {
                    this.widgetsToAdd.add(constraintWidget3);
                } else {
                    constraintWidget3.addToSolver(linearSystem, optimizeFor);
                }
            }
        }
        while (this.widgetsToAdd.size() > 0) {
            int size2 = this.widgetsToAdd.size();
            Iterator<ConstraintWidget> it = this.widgetsToAdd.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                VirtualLayout virtualLayout = (VirtualLayout) it.next();
                if (virtualLayout.contains(this.widgetsToAdd)) {
                    virtualLayout.addToSolver(linearSystem, optimizeFor);
                    this.widgetsToAdd.remove(virtualLayout);
                    break;
                }
            }
            if (size2 == this.widgetsToAdd.size()) {
                Iterator<ConstraintWidget> it2 = this.widgetsToAdd.iterator();
                while (it2.hasNext()) {
                    it2.next().addToSolver(linearSystem, optimizeFor);
                }
                this.widgetsToAdd.clear();
            }
        }
        if (LinearSystem.USE_DEPENDENCY_ORDERING) {
            HashSet hashSet = new HashSet();
            for (int i4 = 0; i4 < size; i4++) {
                ConstraintWidget constraintWidget4 = (ConstraintWidget) this.mChildren.get(i4);
                if (!constraintWidget4.addFirst()) {
                    hashSet.add(constraintWidget4);
                }
            }
            addChildrenToSolverByDependency(this, linearSystem, hashSet, getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT ? 0 : 1, false);
            Iterator it3 = hashSet.iterator();
            while (it3.hasNext()) {
                ConstraintWidget constraintWidget5 = (ConstraintWidget) it3.next();
                Optimizer.checkMatchParent(this, linearSystem, constraintWidget5);
                constraintWidget5.addToSolver(linearSystem, optimizeFor);
            }
        } else {
            for (int i5 = 0; i5 < size; i5++) {
                ConstraintWidget constraintWidget6 = (ConstraintWidget) this.mChildren.get(i5);
                if (constraintWidget6 instanceof ConstraintWidgetContainer) {
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour = constraintWidget6.mListDimensionBehaviors[0];
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = constraintWidget6.mListDimensionBehaviors[1];
                    if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                        constraintWidget6.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                    }
                    if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                        constraintWidget6.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                    }
                    constraintWidget6.addToSolver(linearSystem, optimizeFor);
                    if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                        constraintWidget6.setHorizontalDimensionBehaviour(dimensionBehaviour);
                    }
                    if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                        constraintWidget6.setVerticalDimensionBehaviour(dimensionBehaviour2);
                    }
                } else {
                    Optimizer.checkMatchParent(this, linearSystem, constraintWidget6);
                    if (!constraintWidget6.addFirst()) {
                        constraintWidget6.addToSolver(linearSystem, optimizeFor);
                    }
                }
            }
        }
        if (this.mHorizontalChainsSize > 0) {
            Chain.applyChainConstraints(this, linearSystem, (ArrayList<ConstraintWidget>) null, 0);
        }
        if (this.mVerticalChainsSize > 0) {
            Chain.applyChainConstraints(this, linearSystem, (ArrayList<ConstraintWidget>) null, 1);
        }
        return true;
    }

    public boolean updateChildrenFromSolver(LinearSystem linearSystem, boolean[] zArr) {
        zArr[2] = false;
        boolean optimizeFor = optimizeFor(64);
        updateFromSolver(linearSystem, optimizeFor);
        int size = this.mChildren.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i);
            constraintWidget.updateFromSolver(linearSystem, optimizeFor);
            if (constraintWidget.hasDimensionOverride()) {
                z = true;
            }
        }
        return z;
    }

    public void updateFromRuns(boolean z, boolean z2) {
        super.updateFromRuns(z, z2);
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ((ConstraintWidget) this.mChildren.get(i)).updateFromRuns(z, z2);
        }
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        this.mPaddingLeft = i;
        this.mPaddingTop = i2;
        this.mPaddingRight = i3;
        this.mPaddingBottom = i4;
    }

    public void setRtl(boolean z) {
        this.mIsRtl = z;
    }

    public boolean isRtl() {
        return this.mIsRtl;
    }

    public static boolean measure(int i, ConstraintWidget constraintWidget, BasicMeasure.Measurer measurer, BasicMeasure.Measure measure, int i2) {
        int i3;
        int i4;
        if (measurer == null) {
            return false;
        }
        if (constraintWidget.getVisibility() == 8 || (constraintWidget instanceof Guideline) || (constraintWidget instanceof Barrier)) {
            measure.measuredWidth = 0;
            measure.measuredHeight = 0;
            return false;
        }
        measure.horizontalBehavior = constraintWidget.getHorizontalDimensionBehaviour();
        measure.verticalBehavior = constraintWidget.getVerticalDimensionBehaviour();
        measure.horizontalDimension = constraintWidget.getWidth();
        measure.verticalDimension = constraintWidget.getHeight();
        measure.measuredNeedsSolverPass = false;
        measure.measureStrategy = i2;
        boolean z = measure.horizontalBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean z2 = measure.verticalBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean z3 = z && constraintWidget.mDimensionRatio > 0.0f;
        boolean z4 = z2 && constraintWidget.mDimensionRatio > 0.0f;
        if (z && constraintWidget.hasDanglingDimension(0) && constraintWidget.mMatchConstraintDefaultWidth == 0 && !z3) {
            measure.horizontalBehavior = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            if (z2 && constraintWidget.mMatchConstraintDefaultHeight == 0) {
                measure.horizontalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
            }
            z = false;
        }
        if (z2 && constraintWidget.hasDanglingDimension(1) && constraintWidget.mMatchConstraintDefaultHeight == 0 && !z4) {
            measure.verticalBehavior = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            if (z && constraintWidget.mMatchConstraintDefaultWidth == 0) {
                measure.verticalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
            }
            z2 = false;
        }
        if (constraintWidget.isResolvedHorizontally()) {
            measure.horizontalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
            z = false;
        }
        if (constraintWidget.isResolvedVertically()) {
            measure.verticalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
            z2 = false;
        }
        if (z3) {
            if (constraintWidget.mResolvedMatchConstraintDefault[0] == 4) {
                measure.horizontalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
            } else if (!z2) {
                if (measure.verticalBehavior == ConstraintWidget.DimensionBehaviour.FIXED) {
                    i4 = measure.verticalDimension;
                } else {
                    measure.horizontalBehavior = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    measurer.measure(constraintWidget, measure);
                    i4 = measure.measuredHeight;
                }
                measure.horizontalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
                measure.horizontalDimension = (int) (constraintWidget.getDimensionRatio() * ((float) i4));
            }
        }
        if (z4) {
            if (constraintWidget.mResolvedMatchConstraintDefault[1] == 4) {
                measure.verticalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
            } else if (!z) {
                if (measure.horizontalBehavior == ConstraintWidget.DimensionBehaviour.FIXED) {
                    i3 = measure.horizontalDimension;
                } else {
                    measure.verticalBehavior = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    measurer.measure(constraintWidget, measure);
                    i3 = measure.measuredWidth;
                }
                measure.verticalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
                if (constraintWidget.getDimensionRatioSide() == -1) {
                    measure.verticalDimension = (int) (((float) i3) / constraintWidget.getDimensionRatio());
                } else {
                    measure.verticalDimension = (int) (constraintWidget.getDimensionRatio() * ((float) i3));
                }
            }
        }
        measurer.measure(constraintWidget, measure);
        constraintWidget.setWidth(measure.measuredWidth);
        constraintWidget.setHeight(measure.measuredHeight);
        constraintWidget.setHasBaseline(measure.measuredHasBaseline);
        constraintWidget.setBaselineDistance(measure.measuredBaseline);
        measure.measureStrategy = BasicMeasure.Measure.SELF_DIMENSIONS;
        return measure.measuredNeedsSolverPass;
    }

    /* JADX WARNING: type inference failed for: r6v1, types: [boolean] */
    /* JADX WARNING: type inference failed for: r6v6 */
    /* JADX WARNING: type inference failed for: r6v7 */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x0331  */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x0333  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void layout() {
        /*
            r18 = this;
            r1 = r18
            r2 = 0
            r1.mX = r2
            r1.mY = r2
            r1.mWidthMeasuredTooSmall = r2
            r1.mHeightMeasuredTooSmall = r2
            java.util.ArrayList r0 = r1.mChildren
            int r3 = r0.size()
            int r0 = r18.getWidth()
            int r0 = java.lang.Math.max(r2, r0)
            int r4 = r18.getHeight()
            int r4 = java.lang.Math.max(r2, r4)
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r5 = r1.mListDimensionBehaviors
            r6 = 1
            r5 = r5[r6]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r7 = r1.mListDimensionBehaviors
            r7 = r7[r2]
            androidx.constraintlayout.core.Metrics r8 = r1.mMetrics
            if (r8 == 0) goto L_0x0035
            long r9 = r8.layouts
            r11 = 1
            long r9 = r9 + r11
            r8.layouts = r9
        L_0x0035:
            int r8 = r1.pass
            if (r8 != 0) goto L_0x0097
            int r8 = r1.mOptimizationLevel
            boolean r8 = androidx.constraintlayout.core.widgets.Optimizer.enabled(r8, r6)
            if (r8 == 0) goto L_0x0097
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measurer r8 = r18.getMeasurer()
            androidx.constraintlayout.core.widgets.analyzer.Direct.solvingPass(r1, r8)
            r8 = 0
        L_0x0049:
            if (r8 >= r3) goto L_0x0097
            java.util.ArrayList r9 = r1.mChildren
            java.lang.Object r9 = r9.get(r8)
            androidx.constraintlayout.core.widgets.ConstraintWidget r9 = (androidx.constraintlayout.core.widgets.ConstraintWidget) r9
            boolean r10 = r9.isMeasureRequested()
            if (r10 == 0) goto L_0x0094
            boolean r10 = r9 instanceof androidx.constraintlayout.core.widgets.Guideline
            if (r10 != 0) goto L_0x0094
            boolean r10 = r9 instanceof androidx.constraintlayout.core.widgets.Barrier
            if (r10 != 0) goto L_0x0094
            boolean r10 = r9 instanceof androidx.constraintlayout.core.widgets.VirtualLayout
            if (r10 != 0) goto L_0x0094
            boolean r10 = r9.isInVirtualLayout()
            if (r10 != 0) goto L_0x0094
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r10 = r9.getDimensionBehaviour(r2)
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r11 = r9.getDimensionBehaviour(r6)
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r12 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r10 != r12) goto L_0x0085
            int r10 = r9.mMatchConstraintDefaultWidth
            if (r10 == r6) goto L_0x0085
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r10 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r11 != r10) goto L_0x0085
            int r10 = r9.mMatchConstraintDefaultHeight
            if (r10 == r6) goto L_0x0085
            r10 = 1
            goto L_0x0086
        L_0x0085:
            r10 = 0
        L_0x0086:
            if (r10 != 0) goto L_0x0094
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure r10 = new androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure
            r10.<init>()
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measurer r11 = r1.mMeasurer
            int r12 = androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measure.SELF_DIMENSIONS
            measure(r2, r9, r11, r10, r12)
        L_0x0094:
            int r8 = r8 + 1
            goto L_0x0049
        L_0x0097:
            r8 = 2
            if (r3 <= r8) goto L_0x00e6
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r9 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r7 == r9) goto L_0x00a2
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r9 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r5 != r9) goto L_0x00e6
        L_0x00a2:
            int r9 = r1.mOptimizationLevel
            r10 = 1024(0x400, float:1.435E-42)
            boolean r9 = androidx.constraintlayout.core.widgets.Optimizer.enabled(r9, r10)
            if (r9 == 0) goto L_0x00e6
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measurer r9 = r18.getMeasurer()
            boolean r9 = androidx.constraintlayout.core.widgets.analyzer.Grouping.simpleSolvingPass(r1, r9)
            if (r9 == 0) goto L_0x00e6
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r9 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r7 != r9) goto L_0x00cc
            int r9 = r18.getWidth()
            if (r0 >= r9) goto L_0x00c8
            if (r0 <= 0) goto L_0x00c8
            r1.setWidth(r0)
            r1.mWidthMeasuredTooSmall = r6
            goto L_0x00cc
        L_0x00c8:
            int r0 = r18.getWidth()
        L_0x00cc:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r9 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r5 != r9) goto L_0x00e2
            int r9 = r18.getHeight()
            if (r4 >= r9) goto L_0x00de
            if (r4 <= 0) goto L_0x00de
            r1.setHeight(r4)
            r1.mHeightMeasuredTooSmall = r6
            goto L_0x00e2
        L_0x00de:
            int r4 = r18.getHeight()
        L_0x00e2:
            r9 = r4
            r4 = r0
            r0 = 1
            goto L_0x00e9
        L_0x00e6:
            r9 = r4
            r4 = r0
            r0 = 0
        L_0x00e9:
            r10 = 64
            boolean r11 = r1.optimizeFor(r10)
            if (r11 != 0) goto L_0x00fc
            r11 = 128(0x80, float:1.794E-43)
            boolean r11 = r1.optimizeFor(r11)
            if (r11 == 0) goto L_0x00fa
            goto L_0x00fc
        L_0x00fa:
            r11 = 0
            goto L_0x00fd
        L_0x00fc:
            r11 = 1
        L_0x00fd:
            androidx.constraintlayout.core.LinearSystem r12 = r1.mSystem
            r12.graphOptimizer = r2
            androidx.constraintlayout.core.LinearSystem r12 = r1.mSystem
            r12.newgraphOptimizer = r2
            int r12 = r1.mOptimizationLevel
            if (r12 == 0) goto L_0x010f
            if (r11 == 0) goto L_0x010f
            androidx.constraintlayout.core.LinearSystem r11 = r1.mSystem
            r11.newgraphOptimizer = r6
        L_0x010f:
            java.util.ArrayList r11 = r1.mChildren
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r12 = r18.getHorizontalDimensionBehaviour()
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r13 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r12 == r13) goto L_0x0124
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r12 = r18.getVerticalDimensionBehaviour()
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r13 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r12 != r13) goto L_0x0122
            goto L_0x0124
        L_0x0122:
            r12 = 0
            goto L_0x0125
        L_0x0124:
            r12 = 1
        L_0x0125:
            r18.resetChains()
            r13 = 0
        L_0x0129:
            if (r13 >= r3) goto L_0x013f
            java.util.ArrayList r14 = r1.mChildren
            java.lang.Object r14 = r14.get(r13)
            androidx.constraintlayout.core.widgets.ConstraintWidget r14 = (androidx.constraintlayout.core.widgets.ConstraintWidget) r14
            boolean r15 = r14 instanceof androidx.constraintlayout.core.widgets.WidgetContainer
            if (r15 == 0) goto L_0x013c
            androidx.constraintlayout.core.widgets.WidgetContainer r14 = (androidx.constraintlayout.core.widgets.WidgetContainer) r14
            r14.layout()
        L_0x013c:
            int r13 = r13 + 1
            goto L_0x0129
        L_0x013f:
            boolean r10 = r1.optimizeFor(r10)
            r13 = r0
            r0 = 0
            r14 = 1
        L_0x0146:
            if (r14 == 0) goto L_0x033a
            int r15 = r0 + 1
            androidx.constraintlayout.core.LinearSystem r0 = r1.mSystem     // Catch:{ Exception -> 0x01f7 }
            r0.reset()     // Catch:{ Exception -> 0x01f7 }
            r18.resetChains()     // Catch:{ Exception -> 0x01f7 }
            androidx.constraintlayout.core.LinearSystem r0 = r1.mSystem     // Catch:{ Exception -> 0x01f7 }
            r1.createObjectVariables(r0)     // Catch:{ Exception -> 0x01f7 }
            r0 = 0
        L_0x0158:
            if (r0 >= r3) goto L_0x016c
            java.util.ArrayList r6 = r1.mChildren     // Catch:{ Exception -> 0x01f7 }
            java.lang.Object r6 = r6.get(r0)     // Catch:{ Exception -> 0x01f7 }
            androidx.constraintlayout.core.widgets.ConstraintWidget r6 = (androidx.constraintlayout.core.widgets.ConstraintWidget) r6     // Catch:{ Exception -> 0x01f7 }
            androidx.constraintlayout.core.LinearSystem r2 = r1.mSystem     // Catch:{ Exception -> 0x01f7 }
            r6.createObjectVariables(r2)     // Catch:{ Exception -> 0x01f7 }
            int r0 = r0 + 1
            r2 = 0
            r6 = 1
            goto L_0x0158
        L_0x016c:
            androidx.constraintlayout.core.LinearSystem r0 = r1.mSystem     // Catch:{ Exception -> 0x01f7 }
            boolean r14 = r1.addChildrenToSolver(r0)     // Catch:{ Exception -> 0x01f7 }
            java.lang.ref.WeakReference<androidx.constraintlayout.core.widgets.ConstraintAnchor> r0 = r1.verticalWrapMin     // Catch:{ Exception -> 0x01f7 }
            r2 = 0
            if (r0 == 0) goto L_0x0192
            java.lang.Object r0 = r0.get()     // Catch:{ Exception -> 0x01f7 }
            if (r0 == 0) goto L_0x0192
            java.lang.ref.WeakReference<androidx.constraintlayout.core.widgets.ConstraintAnchor> r0 = r1.verticalWrapMin     // Catch:{ Exception -> 0x01f7 }
            java.lang.Object r0 = r0.get()     // Catch:{ Exception -> 0x01f7 }
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = (androidx.constraintlayout.core.widgets.ConstraintAnchor) r0     // Catch:{ Exception -> 0x01f7 }
            androidx.constraintlayout.core.LinearSystem r6 = r1.mSystem     // Catch:{ Exception -> 0x01f7 }
            androidx.constraintlayout.core.widgets.ConstraintAnchor r8 = r1.mTop     // Catch:{ Exception -> 0x01f7 }
            androidx.constraintlayout.core.SolverVariable r6 = r6.createObjectVariable(r8)     // Catch:{ Exception -> 0x01f7 }
            r1.addMinWrap(r0, r6)     // Catch:{ Exception -> 0x01f7 }
            r1.verticalWrapMin = r2     // Catch:{ Exception -> 0x01f7 }
        L_0x0192:
            java.lang.ref.WeakReference<androidx.constraintlayout.core.widgets.ConstraintAnchor> r0 = r1.verticalWrapMax     // Catch:{ Exception -> 0x01f7 }
            if (r0 == 0) goto L_0x01b1
            java.lang.Object r0 = r0.get()     // Catch:{ Exception -> 0x01f7 }
            if (r0 == 0) goto L_0x01b1
            java.lang.ref.WeakReference<androidx.constraintlayout.core.widgets.ConstraintAnchor> r0 = r1.verticalWrapMax     // Catch:{ Exception -> 0x01f7 }
            java.lang.Object r0 = r0.get()     // Catch:{ Exception -> 0x01f7 }
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = (androidx.constraintlayout.core.widgets.ConstraintAnchor) r0     // Catch:{ Exception -> 0x01f7 }
            androidx.constraintlayout.core.LinearSystem r6 = r1.mSystem     // Catch:{ Exception -> 0x01f7 }
            androidx.constraintlayout.core.widgets.ConstraintAnchor r8 = r1.mBottom     // Catch:{ Exception -> 0x01f7 }
            androidx.constraintlayout.core.SolverVariable r6 = r6.createObjectVariable(r8)     // Catch:{ Exception -> 0x01f7 }
            r1.addMaxWrap(r0, r6)     // Catch:{ Exception -> 0x01f7 }
            r1.verticalWrapMax = r2     // Catch:{ Exception -> 0x01f7 }
        L_0x01b1:
            java.lang.ref.WeakReference<androidx.constraintlayout.core.widgets.ConstraintAnchor> r0 = r1.horizontalWrapMin     // Catch:{ Exception -> 0x01f7 }
            if (r0 == 0) goto L_0x01d0
            java.lang.Object r0 = r0.get()     // Catch:{ Exception -> 0x01f7 }
            if (r0 == 0) goto L_0x01d0
            java.lang.ref.WeakReference<androidx.constraintlayout.core.widgets.ConstraintAnchor> r0 = r1.horizontalWrapMin     // Catch:{ Exception -> 0x01f7 }
            java.lang.Object r0 = r0.get()     // Catch:{ Exception -> 0x01f7 }
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = (androidx.constraintlayout.core.widgets.ConstraintAnchor) r0     // Catch:{ Exception -> 0x01f7 }
            androidx.constraintlayout.core.LinearSystem r6 = r1.mSystem     // Catch:{ Exception -> 0x01f7 }
            androidx.constraintlayout.core.widgets.ConstraintAnchor r8 = r1.mLeft     // Catch:{ Exception -> 0x01f7 }
            androidx.constraintlayout.core.SolverVariable r6 = r6.createObjectVariable(r8)     // Catch:{ Exception -> 0x01f7 }
            r1.addMinWrap(r0, r6)     // Catch:{ Exception -> 0x01f7 }
            r1.horizontalWrapMin = r2     // Catch:{ Exception -> 0x01f7 }
        L_0x01d0:
            java.lang.ref.WeakReference<androidx.constraintlayout.core.widgets.ConstraintAnchor> r0 = r1.horizontalWrapMax     // Catch:{ Exception -> 0x01f7 }
            if (r0 == 0) goto L_0x01ef
            java.lang.Object r0 = r0.get()     // Catch:{ Exception -> 0x01f7 }
            if (r0 == 0) goto L_0x01ef
            java.lang.ref.WeakReference<androidx.constraintlayout.core.widgets.ConstraintAnchor> r0 = r1.horizontalWrapMax     // Catch:{ Exception -> 0x01f7 }
            java.lang.Object r0 = r0.get()     // Catch:{ Exception -> 0x01f7 }
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = (androidx.constraintlayout.core.widgets.ConstraintAnchor) r0     // Catch:{ Exception -> 0x01f7 }
            androidx.constraintlayout.core.LinearSystem r6 = r1.mSystem     // Catch:{ Exception -> 0x01f7 }
            androidx.constraintlayout.core.widgets.ConstraintAnchor r8 = r1.mRight     // Catch:{ Exception -> 0x01f7 }
            androidx.constraintlayout.core.SolverVariable r6 = r6.createObjectVariable(r8)     // Catch:{ Exception -> 0x01f7 }
            r1.addMaxWrap(r0, r6)     // Catch:{ Exception -> 0x01f7 }
            r1.horizontalWrapMax = r2     // Catch:{ Exception -> 0x01f7 }
        L_0x01ef:
            if (r14 == 0) goto L_0x0211
            androidx.constraintlayout.core.LinearSystem r0 = r1.mSystem     // Catch:{ Exception -> 0x01f7 }
            r0.minimize()     // Catch:{ Exception -> 0x01f7 }
            goto L_0x0211
        L_0x01f7:
            r0 = move-exception
            r0.printStackTrace()
            java.io.PrintStream r2 = java.lang.System.out
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r8 = "EXCEPTION : "
            r6.append(r8)
            r6.append(r0)
            java.lang.String r0 = r6.toString()
            r2.println(r0)
        L_0x0211:
            if (r14 == 0) goto L_0x021c
            androidx.constraintlayout.core.LinearSystem r0 = r1.mSystem
            boolean[] r2 = androidx.constraintlayout.core.widgets.Optimizer.flags
            boolean r0 = r1.updateChildrenFromSolver(r0, r2)
            goto L_0x0235
        L_0x021c:
            androidx.constraintlayout.core.LinearSystem r0 = r1.mSystem
            r1.updateFromSolver(r0, r10)
            r0 = 0
        L_0x0222:
            if (r0 >= r3) goto L_0x0234
            java.util.ArrayList r2 = r1.mChildren
            java.lang.Object r2 = r2.get(r0)
            androidx.constraintlayout.core.widgets.ConstraintWidget r2 = (androidx.constraintlayout.core.widgets.ConstraintWidget) r2
            androidx.constraintlayout.core.LinearSystem r6 = r1.mSystem
            r2.updateFromSolver(r6, r10)
            int r0 = r0 + 1
            goto L_0x0222
        L_0x0234:
            r0 = 0
        L_0x0235:
            r2 = 8
            if (r12 == 0) goto L_0x02ac
            if (r15 >= r2) goto L_0x02ac
            boolean[] r6 = androidx.constraintlayout.core.widgets.Optimizer.flags
            r8 = 2
            boolean r6 = r6[r8]
            if (r6 == 0) goto L_0x02ac
            r6 = 0
            r8 = 0
            r14 = 0
        L_0x0245:
            if (r6 >= r3) goto L_0x026f
            java.util.ArrayList r2 = r1.mChildren
            java.lang.Object r2 = r2.get(r6)
            androidx.constraintlayout.core.widgets.ConstraintWidget r2 = (androidx.constraintlayout.core.widgets.ConstraintWidget) r2
            r16 = r0
            int r0 = r2.mX
            int r17 = r2.getWidth()
            int r0 = r0 + r17
            int r14 = java.lang.Math.max(r14, r0)
            int r0 = r2.mY
            int r2 = r2.getHeight()
            int r0 = r0 + r2
            int r8 = java.lang.Math.max(r8, r0)
            int r6 = r6 + 1
            r0 = r16
            r2 = 8
            goto L_0x0245
        L_0x026f:
            r16 = r0
            int r0 = r1.mMinWidth
            int r0 = java.lang.Math.max(r0, r14)
            int r2 = r1.mMinHeight
            int r2 = java.lang.Math.max(r2, r8)
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r7 != r6) goto L_0x0294
            int r6 = r18.getWidth()
            if (r6 >= r0) goto L_0x0294
            r1.setWidth(r0)
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r8 = 0
            r0[r8] = r6
            r13 = 1
            r16 = 1
        L_0x0294:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r0 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r5 != r0) goto L_0x02ae
            int r0 = r18.getHeight()
            if (r0 >= r2) goto L_0x02ae
            r1.setHeight(r2)
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r6 = 1
            r0[r6] = r2
            r13 = 1
            r16 = 1
            goto L_0x02ae
        L_0x02ac:
            r16 = r0
        L_0x02ae:
            int r0 = r1.mMinWidth
            int r2 = r18.getWidth()
            int r0 = java.lang.Math.max(r0, r2)
            int r2 = r18.getWidth()
            if (r0 <= r2) goto L_0x02cb
            r1.setWidth(r0)
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r6 = 0
            r0[r6] = r2
            r13 = 1
            r16 = 1
        L_0x02cb:
            int r0 = r1.mMinHeight
            int r2 = r18.getHeight()
            int r0 = java.lang.Math.max(r0, r2)
            int r2 = r18.getHeight()
            if (r0 <= r2) goto L_0x02e9
            r1.setHeight(r0)
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r6 = 1
            r0[r6] = r2
            r13 = 1
            r16 = 1
            goto L_0x02ea
        L_0x02e9:
            r6 = 1
        L_0x02ea:
            if (r13 != 0) goto L_0x032b
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            r2 = 0
            r0 = r0[r2]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r8 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r0 != r8) goto L_0x030b
            if (r4 <= 0) goto L_0x030b
            int r0 = r18.getWidth()
            if (r0 <= r4) goto L_0x030b
            r1.mWidthMeasuredTooSmall = r6
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r8 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r0[r2] = r8
            r1.setWidth(r4)
            r13 = 1
            r16 = 1
        L_0x030b:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            r0 = r0[r6]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r0 != r2) goto L_0x032b
            if (r9 <= 0) goto L_0x032b
            int r0 = r18.getHeight()
            if (r0 <= r9) goto L_0x032b
            r1.mHeightMeasuredTooSmall = r6
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r0[r6] = r2
            r1.setHeight(r9)
            r0 = 8
            r6 = 1
            r13 = 1
            goto L_0x032f
        L_0x032b:
            r6 = r16
            r0 = 8
        L_0x032f:
            if (r15 <= r0) goto L_0x0333
            r14 = 0
            goto L_0x0334
        L_0x0333:
            r14 = r6
        L_0x0334:
            r0 = r15
            r2 = 0
            r6 = 1
            r8 = 2
            goto L_0x0146
        L_0x033a:
            java.util.ArrayList r11 = (java.util.ArrayList) r11
            r1.mChildren = r11
            if (r13 == 0) goto L_0x034a
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            r2 = 0
            r0[r2] = r7
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r1.mListDimensionBehaviors
            r2 = 1
            r0[r2] = r5
        L_0x034a:
            androidx.constraintlayout.core.LinearSystem r0 = r1.mSystem
            androidx.constraintlayout.core.Cache r0 = r0.getCache()
            r1.resetSolverVariables(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.ConstraintWidgetContainer.layout():void");
    }

    public ArrayList<Guideline> getVerticalGuidelines() {
        ArrayList<Guideline> arrayList = new ArrayList<>();
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i);
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                if (guideline.getOrientation() == 1) {
                    arrayList.add(guideline);
                }
            }
        }
        return arrayList;
    }

    public ArrayList<Guideline> getHorizontalGuidelines() {
        ArrayList<Guideline> arrayList = new ArrayList<>();
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i);
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                if (guideline.getOrientation() == 0) {
                    arrayList.add(guideline);
                }
            }
        }
        return arrayList;
    }

    public LinearSystem getSystem() {
        return this.mSystem;
    }

    private void resetChains() {
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
    }

    /* access modifiers changed from: package-private */
    public void addChain(ConstraintWidget constraintWidget, int i) {
        if (i == 0) {
            addHorizontalChain(constraintWidget);
        } else if (i == 1) {
            addVerticalChain(constraintWidget);
        }
    }

    private void addHorizontalChain(ConstraintWidget constraintWidget) {
        int i = this.mHorizontalChainsSize + 1;
        ChainHead[] chainHeadArr = this.mHorizontalChainsArray;
        if (i >= chainHeadArr.length) {
            this.mHorizontalChainsArray = (ChainHead[]) Arrays.copyOf(chainHeadArr, chainHeadArr.length * 2);
        }
        this.mHorizontalChainsArray[this.mHorizontalChainsSize] = new ChainHead(constraintWidget, 0, isRtl());
        this.mHorizontalChainsSize++;
    }

    private void addVerticalChain(ConstraintWidget constraintWidget) {
        int i = this.mVerticalChainsSize + 1;
        ChainHead[] chainHeadArr = this.mVerticalChainsArray;
        if (i >= chainHeadArr.length) {
            this.mVerticalChainsArray = (ChainHead[]) Arrays.copyOf(chainHeadArr, chainHeadArr.length * 2);
        }
        this.mVerticalChainsArray[this.mVerticalChainsSize] = new ChainHead(constraintWidget, 1, isRtl());
        this.mVerticalChainsSize++;
    }

    public void setPass(int i) {
        this.pass = i;
    }

    public void getSceneString(StringBuilder sb) {
        sb.append(this.stringId + ":{\n");
        sb.append("  actualWidth:" + this.mWidth);
        sb.append("\n");
        sb.append("  actualHeight:" + this.mHeight);
        sb.append("\n");
        Iterator<ConstraintWidget> it = getChildren().iterator();
        while (it.hasNext()) {
            it.next().getSceneString(sb);
            sb.append(",\n");
        }
        sb.append("}");
    }
}
