package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.Guideline;
import androidx.constraintlayout.core.widgets.HelperWidget;
import java.util.ArrayList;

public class Grouping {
    private static final boolean DEBUG = false;
    private static final boolean DEBUG_GROUPING = false;

    public static boolean validInGroup(ConstraintWidget.DimensionBehaviour dimensionBehaviour, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, ConstraintWidget.DimensionBehaviour dimensionBehaviour3, ConstraintWidget.DimensionBehaviour dimensionBehaviour4) {
        return (dimensionBehaviour3 == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviour3 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || (dimensionBehaviour3 == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && dimensionBehaviour != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)) || (dimensionBehaviour4 == ConstraintWidget.DimensionBehaviour.FIXED || dimensionBehaviour4 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || (dimensionBehaviour4 == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && dimensionBehaviour2 != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT));
    }

    /* JADX WARNING: Removed duplicated region for block: B:178:0x035f  */
    /* JADX WARNING: Removed duplicated region for block: B:189:0x039b  */
    /* JADX WARNING: Removed duplicated region for block: B:192:0x03a0 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean simpleSolvingPass(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r16, androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measurer r17) {
        /*
            r0 = r16
            java.util.ArrayList r1 = r16.getChildren()
            int r2 = r1.size()
            r3 = 0
            r4 = 0
        L_0x000c:
            if (r4 >= r2) goto L_0x0033
            java.lang.Object r5 = r1.get(r4)
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = (androidx.constraintlayout.core.widgets.ConstraintWidget) r5
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r6 = r16.getHorizontalDimensionBehaviour()
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r7 = r16.getVerticalDimensionBehaviour()
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r8 = r5.getHorizontalDimensionBehaviour()
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r9 = r5.getVerticalDimensionBehaviour()
            boolean r6 = validInGroup(r6, r7, r8, r9)
            if (r6 != 0) goto L_0x002b
            return r3
        L_0x002b:
            boolean r5 = r5 instanceof androidx.constraintlayout.core.widgets.Flow
            if (r5 == 0) goto L_0x0030
            return r3
        L_0x0030:
            int r4 = r4 + 1
            goto L_0x000c
        L_0x0033:
            androidx.constraintlayout.core.Metrics r4 = r0.mMetrics
            if (r4 == 0) goto L_0x0040
            androidx.constraintlayout.core.Metrics r4 = r0.mMetrics
            long r5 = r4.grouping
            r7 = 1
            long r5 = r5 + r7
            r4.grouping = r5
        L_0x0040:
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
        L_0x0047:
            if (r5 >= r2) goto L_0x0121
            java.lang.Object r13 = r1.get(r5)
            androidx.constraintlayout.core.widgets.ConstraintWidget r13 = (androidx.constraintlayout.core.widgets.ConstraintWidget) r13
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r14 = r16.getHorizontalDimensionBehaviour()
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r15 = r16.getVerticalDimensionBehaviour()
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r4 = r13.getHorizontalDimensionBehaviour()
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r12 = r13.getVerticalDimensionBehaviour()
            boolean r4 = validInGroup(r14, r15, r4, r12)
            if (r4 != 0) goto L_0x006f
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure r4 = r0.mMeasure
            int r12 = androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measure.SELF_DIMENSIONS
            r14 = r17
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer.measure(r3, r13, r14, r4, r12)
            goto L_0x0071
        L_0x006f:
            r14 = r17
        L_0x0071:
            boolean r4 = r13 instanceof androidx.constraintlayout.core.widgets.Guideline
            if (r4 == 0) goto L_0x0099
            r12 = r13
            androidx.constraintlayout.core.widgets.Guideline r12 = (androidx.constraintlayout.core.widgets.Guideline) r12
            int r15 = r12.getOrientation()
            if (r15 != 0) goto L_0x0088
            if (r8 != 0) goto L_0x0085
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
        L_0x0085:
            r8.add(r12)
        L_0x0088:
            int r15 = r12.getOrientation()
            r3 = 1
            if (r15 != r3) goto L_0x0099
            if (r6 != 0) goto L_0x0096
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
        L_0x0096:
            r6.add(r12)
        L_0x0099:
            boolean r3 = r13 instanceof androidx.constraintlayout.core.widgets.HelperWidget
            if (r3 == 0) goto L_0x00dd
            boolean r3 = r13 instanceof androidx.constraintlayout.core.widgets.Barrier
            if (r3 == 0) goto L_0x00c6
            r3 = r13
            androidx.constraintlayout.core.widgets.Barrier r3 = (androidx.constraintlayout.core.widgets.Barrier) r3
            int r12 = r3.getOrientation()
            if (r12 != 0) goto L_0x00b4
            if (r7 != 0) goto L_0x00b1
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
        L_0x00b1:
            r7.add(r3)
        L_0x00b4:
            int r12 = r3.getOrientation()
            r15 = 1
            if (r12 != r15) goto L_0x00dd
            if (r9 != 0) goto L_0x00c2
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
        L_0x00c2:
            r9.add(r3)
            goto L_0x00dd
        L_0x00c6:
            r3 = r13
            androidx.constraintlayout.core.widgets.HelperWidget r3 = (androidx.constraintlayout.core.widgets.HelperWidget) r3
            if (r7 != 0) goto L_0x00d0
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
        L_0x00d0:
            r7.add(r3)
            if (r9 != 0) goto L_0x00da
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
        L_0x00da:
            r9.add(r3)
        L_0x00dd:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r13.mLeft
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 != 0) goto L_0x00f9
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r13.mRight
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 != 0) goto L_0x00f9
            if (r4 != 0) goto L_0x00f9
            boolean r3 = r13 instanceof androidx.constraintlayout.core.widgets.Barrier
            if (r3 != 0) goto L_0x00f9
            if (r10 != 0) goto L_0x00f6
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
        L_0x00f6:
            r10.add(r13)
        L_0x00f9:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r13.mTop
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 != 0) goto L_0x011c
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r13.mBottom
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 != 0) goto L_0x011c
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r13.mBaseline
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 != 0) goto L_0x011c
            if (r4 != 0) goto L_0x011c
            boolean r3 = r13 instanceof androidx.constraintlayout.core.widgets.Barrier
            if (r3 != 0) goto L_0x011c
            if (r11 != 0) goto L_0x0119
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            r11 = r3
        L_0x0119:
            r11.add(r13)
        L_0x011c:
            int r5 = r5 + 1
            r3 = 0
            goto L_0x0047
        L_0x0121:
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            if (r6 == 0) goto L_0x013e
            java.util.Iterator r4 = r6.iterator()
        L_0x012c:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x013e
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.core.widgets.Guideline r5 = (androidx.constraintlayout.core.widgets.Guideline) r5
            r6 = 0
            r12 = 0
            findDependents(r5, r6, r3, r12)
            goto L_0x012c
        L_0x013e:
            r6 = 0
            r12 = 0
            if (r7 == 0) goto L_0x015f
            java.util.Iterator r4 = r7.iterator()
        L_0x0146:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x015f
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.core.widgets.HelperWidget r5 = (androidx.constraintlayout.core.widgets.HelperWidget) r5
            androidx.constraintlayout.core.widgets.analyzer.WidgetGroup r7 = findDependents(r5, r6, r3, r12)
            r5.addDependents(r3, r6, r7)
            r7.cleanup(r3)
            r6 = 0
            r12 = 0
            goto L_0x0146
        L_0x015f:
            androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.LEFT
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r0.getAnchor(r4)
            java.util.HashSet r5 = r4.getDependents()
            if (r5 == 0) goto L_0x0187
            java.util.HashSet r4 = r4.getDependents()
            java.util.Iterator r4 = r4.iterator()
        L_0x0173:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0187
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = (androidx.constraintlayout.core.widgets.ConstraintAnchor) r5
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = r5.mOwner
            r6 = 0
            r7 = 0
            findDependents(r5, r6, r3, r7)
            goto L_0x0173
        L_0x0187:
            androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.RIGHT
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r0.getAnchor(r4)
            java.util.HashSet r5 = r4.getDependents()
            if (r5 == 0) goto L_0x01af
            java.util.HashSet r4 = r4.getDependents()
            java.util.Iterator r4 = r4.iterator()
        L_0x019b:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x01af
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = (androidx.constraintlayout.core.widgets.ConstraintAnchor) r5
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = r5.mOwner
            r6 = 0
            r7 = 0
            findDependents(r5, r6, r3, r7)
            goto L_0x019b
        L_0x01af:
            androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.CENTER
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r0.getAnchor(r4)
            java.util.HashSet r5 = r4.getDependents()
            if (r5 == 0) goto L_0x01d7
            java.util.HashSet r4 = r4.getDependents()
            java.util.Iterator r4 = r4.iterator()
        L_0x01c3:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x01d7
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = (androidx.constraintlayout.core.widgets.ConstraintAnchor) r5
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = r5.mOwner
            r6 = 0
            r7 = 0
            findDependents(r5, r6, r3, r7)
            goto L_0x01c3
        L_0x01d7:
            r6 = 0
            r7 = 0
            if (r10 == 0) goto L_0x01ef
            java.util.Iterator r4 = r10.iterator()
        L_0x01df:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x01ef
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = (androidx.constraintlayout.core.widgets.ConstraintWidget) r5
            findDependents(r5, r6, r3, r7)
            goto L_0x01df
        L_0x01ef:
            if (r8 == 0) goto L_0x0206
            java.util.Iterator r4 = r8.iterator()
        L_0x01f5:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0206
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.core.widgets.Guideline r5 = (androidx.constraintlayout.core.widgets.Guideline) r5
            r6 = 1
            findDependents(r5, r6, r3, r7)
            goto L_0x01f5
        L_0x0206:
            r6 = 1
            if (r9 == 0) goto L_0x0226
            java.util.Iterator r4 = r9.iterator()
        L_0x020d:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0226
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.core.widgets.HelperWidget r5 = (androidx.constraintlayout.core.widgets.HelperWidget) r5
            androidx.constraintlayout.core.widgets.analyzer.WidgetGroup r8 = findDependents(r5, r6, r3, r7)
            r5.addDependents(r3, r6, r8)
            r8.cleanup(r3)
            r6 = 1
            r7 = 0
            goto L_0x020d
        L_0x0226:
            androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.TOP
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r0.getAnchor(r4)
            java.util.HashSet r5 = r4.getDependents()
            if (r5 == 0) goto L_0x024e
            java.util.HashSet r4 = r4.getDependents()
            java.util.Iterator r4 = r4.iterator()
        L_0x023a:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x024e
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = (androidx.constraintlayout.core.widgets.ConstraintAnchor) r5
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = r5.mOwner
            r6 = 1
            r7 = 0
            findDependents(r5, r6, r3, r7)
            goto L_0x023a
        L_0x024e:
            androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.BASELINE
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r0.getAnchor(r4)
            java.util.HashSet r5 = r4.getDependents()
            if (r5 == 0) goto L_0x0276
            java.util.HashSet r4 = r4.getDependents()
            java.util.Iterator r4 = r4.iterator()
        L_0x0262:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0276
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = (androidx.constraintlayout.core.widgets.ConstraintAnchor) r5
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = r5.mOwner
            r6 = 1
            r7 = 0
            findDependents(r5, r6, r3, r7)
            goto L_0x0262
        L_0x0276:
            androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.BOTTOM
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r0.getAnchor(r4)
            java.util.HashSet r5 = r4.getDependents()
            if (r5 == 0) goto L_0x029e
            java.util.HashSet r4 = r4.getDependents()
            java.util.Iterator r4 = r4.iterator()
        L_0x028a:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x029e
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = (androidx.constraintlayout.core.widgets.ConstraintAnchor) r5
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = r5.mOwner
            r6 = 1
            r7 = 0
            findDependents(r5, r6, r3, r7)
            goto L_0x028a
        L_0x029e:
            androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.CENTER
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r0.getAnchor(r4)
            java.util.HashSet r5 = r4.getDependents()
            if (r5 == 0) goto L_0x02c6
            java.util.HashSet r4 = r4.getDependents()
            java.util.Iterator r4 = r4.iterator()
        L_0x02b2:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x02c6
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = (androidx.constraintlayout.core.widgets.ConstraintAnchor) r5
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = r5.mOwner
            r6 = 1
            r12 = 0
            findDependents(r5, r6, r3, r12)
            goto L_0x02b2
        L_0x02c6:
            r6 = 1
            r12 = 0
            if (r11 == 0) goto L_0x02de
            java.util.Iterator r4 = r11.iterator()
        L_0x02ce:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x02de
            java.lang.Object r5 = r4.next()
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = (androidx.constraintlayout.core.widgets.ConstraintWidget) r5
            findDependents(r5, r6, r3, r12)
            goto L_0x02ce
        L_0x02de:
            r4 = 0
        L_0x02df:
            if (r4 >= r2) goto L_0x030b
            java.lang.Object r5 = r1.get(r4)
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = (androidx.constraintlayout.core.widgets.ConstraintWidget) r5
            boolean r6 = r5.oppositeDimensionsTied()
            if (r6 == 0) goto L_0x0308
            int r6 = r5.horizontalGroup
            androidx.constraintlayout.core.widgets.analyzer.WidgetGroup r6 = findGroup(r3, r6)
            int r5 = r5.verticalGroup
            androidx.constraintlayout.core.widgets.analyzer.WidgetGroup r5 = findGroup(r3, r5)
            if (r6 == 0) goto L_0x0308
            if (r5 == 0) goto L_0x0308
            r7 = 0
            r6.moveTo(r7, r5)
            r7 = 2
            r5.setOrientation(r7)
            r3.remove(r6)
        L_0x0308:
            int r4 = r4 + 1
            goto L_0x02df
        L_0x030b:
            int r1 = r3.size()
            r2 = 1
            if (r1 > r2) goto L_0x0314
            r1 = 0
            return r1
        L_0x0314:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r1 = r16.getHorizontalDimensionBehaviour()
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r1 != r2) goto L_0x0356
            java.util.Iterator r1 = r3.iterator()
            r2 = r12
            r6 = 0
        L_0x0322:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L_0x0347
            java.lang.Object r4 = r1.next()
            androidx.constraintlayout.core.widgets.analyzer.WidgetGroup r4 = (androidx.constraintlayout.core.widgets.analyzer.WidgetGroup) r4
            int r5 = r4.getOrientation()
            r7 = 1
            if (r5 != r7) goto L_0x0336
            goto L_0x0322
        L_0x0336:
            r5 = 0
            r4.setAuthoritative(r5)
            androidx.constraintlayout.core.LinearSystem r7 = r16.getSystem()
            int r7 = r4.measureWrap((androidx.constraintlayout.core.LinearSystem) r7, (int) r5)
            if (r7 <= r6) goto L_0x0322
            r2 = r4
            r6 = r7
            goto L_0x0322
        L_0x0347:
            if (r2 == 0) goto L_0x0356
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r1 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r0.setHorizontalDimensionBehaviour(r1)
            r0.setWidth(r6)
            r1 = 1
            r2.setAuthoritative(r1)
            goto L_0x0357
        L_0x0356:
            r2 = r12
        L_0x0357:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r1 = r16.getVerticalDimensionBehaviour()
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r4 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r1 != r4) goto L_0x039b
            java.util.Iterator r1 = r3.iterator()
            r3 = r12
            r6 = 0
        L_0x0365:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L_0x038a
            java.lang.Object r4 = r1.next()
            androidx.constraintlayout.core.widgets.analyzer.WidgetGroup r4 = (androidx.constraintlayout.core.widgets.analyzer.WidgetGroup) r4
            int r5 = r4.getOrientation()
            if (r5 != 0) goto L_0x0378
            goto L_0x0365
        L_0x0378:
            r5 = 0
            r4.setAuthoritative(r5)
            androidx.constraintlayout.core.LinearSystem r7 = r16.getSystem()
            r8 = 1
            int r7 = r4.measureWrap((androidx.constraintlayout.core.LinearSystem) r7, (int) r8)
            if (r7 <= r6) goto L_0x0365
            r3 = r4
            r6 = r7
            goto L_0x0365
        L_0x038a:
            r5 = 0
            r8 = 1
            if (r3 == 0) goto L_0x039d
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r1 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r0.setVerticalDimensionBehaviour(r1)
            r0.setHeight(r6)
            r3.setAuthoritative(r8)
            r4 = r3
            goto L_0x039e
        L_0x039b:
            r5 = 0
            r8 = 1
        L_0x039d:
            r4 = r12
        L_0x039e:
            if (r2 != 0) goto L_0x03a5
            if (r4 == 0) goto L_0x03a3
            goto L_0x03a5
        L_0x03a3:
            r3 = 0
            goto L_0x03a6
        L_0x03a5:
            r3 = 1
        L_0x03a6:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.Grouping.simpleSolvingPass(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer, androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measurer):boolean");
    }

    private static WidgetGroup findGroup(ArrayList<WidgetGroup> arrayList, int i) {
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            WidgetGroup widgetGroup = arrayList.get(i2);
            if (i == widgetGroup.id) {
                return widgetGroup;
            }
        }
        return null;
    }

    public static WidgetGroup findDependents(ConstraintWidget constraintWidget, int i, ArrayList<WidgetGroup> arrayList, WidgetGroup widgetGroup) {
        int i2;
        int findGroupInDependents;
        if (i == 0) {
            i2 = constraintWidget.horizontalGroup;
        } else {
            i2 = constraintWidget.verticalGroup;
        }
        int i3 = 0;
        if (i2 != -1 && (widgetGroup == null || i2 != widgetGroup.id)) {
            int i4 = 0;
            while (true) {
                if (i4 >= arrayList.size()) {
                    break;
                }
                WidgetGroup widgetGroup2 = arrayList.get(i4);
                if (widgetGroup2.getId() == i2) {
                    if (widgetGroup != null) {
                        widgetGroup.moveTo(i, widgetGroup2);
                        arrayList.remove(widgetGroup);
                    }
                    widgetGroup = widgetGroup2;
                } else {
                    i4++;
                }
            }
        } else if (i2 != -1) {
            return widgetGroup;
        }
        if (widgetGroup == null) {
            if ((constraintWidget instanceof HelperWidget) && (findGroupInDependents = ((HelperWidget) constraintWidget).findGroupInDependents(i)) != -1) {
                int i5 = 0;
                while (true) {
                    if (i5 >= arrayList.size()) {
                        break;
                    }
                    WidgetGroup widgetGroup3 = arrayList.get(i5);
                    if (widgetGroup3.getId() == findGroupInDependents) {
                        widgetGroup = widgetGroup3;
                        break;
                    }
                    i5++;
                }
            }
            if (widgetGroup == null) {
                widgetGroup = new WidgetGroup(i);
            }
            arrayList.add(widgetGroup);
        }
        if (widgetGroup.add(constraintWidget)) {
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                ConstraintAnchor anchor = guideline.getAnchor();
                if (guideline.getOrientation() == 0) {
                    i3 = 1;
                }
                anchor.findDependents(i3, arrayList, widgetGroup);
            }
            if (i == 0) {
                constraintWidget.horizontalGroup = widgetGroup.getId();
                constraintWidget.mLeft.findDependents(i, arrayList, widgetGroup);
                constraintWidget.mRight.findDependents(i, arrayList, widgetGroup);
            } else {
                constraintWidget.verticalGroup = widgetGroup.getId();
                constraintWidget.mTop.findDependents(i, arrayList, widgetGroup);
                constraintWidget.mBaseline.findDependents(i, arrayList, widgetGroup);
                constraintWidget.mBottom.findDependents(i, arrayList, widgetGroup);
            }
            constraintWidget.mCenter.findDependents(i, arrayList, widgetGroup);
        }
        return widgetGroup;
    }
}
