package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import java.util.ArrayList;
import java.util.Iterator;

public class ChainRun extends WidgetRun {
    private int chainStyle;
    ArrayList<WidgetRun> widgets = new ArrayList<>();

    public ChainRun(ConstraintWidget constraintWidget, int i) {
        super(constraintWidget);
        this.orientation = i;
        build();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ChainRun ");
        sb.append(this.orientation == 0 ? "horizontal : " : "vertical : ");
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            sb.append("<");
            sb.append(it.next());
            sb.append("> ");
        }
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public boolean supportsWrapComputation() {
        int size = this.widgets.size();
        for (int i = 0; i < size; i++) {
            if (!this.widgets.get(i).supportsWrapComputation()) {
                return false;
            }
        }
        return true;
    }

    public long getWrapDimension() {
        int size = this.widgets.size();
        long j = 0;
        for (int i = 0; i < size; i++) {
            WidgetRun widgetRun = this.widgets.get(i);
            j = j + ((long) widgetRun.start.margin) + widgetRun.getWrapDimension() + ((long) widgetRun.end.margin);
        }
        return j;
    }

    private void build() {
        ConstraintWidget constraintWidget;
        ConstraintWidget constraintWidget2 = this.widget;
        ConstraintWidget previousChainMember = constraintWidget2.getPreviousChainMember(this.orientation);
        while (true) {
            ConstraintWidget constraintWidget3 = previousChainMember;
            constraintWidget = constraintWidget2;
            constraintWidget2 = constraintWidget3;
            if (constraintWidget2 == null) {
                break;
            }
            previousChainMember = constraintWidget2.getPreviousChainMember(this.orientation);
        }
        this.widget = constraintWidget;
        this.widgets.add(constraintWidget.getRun(this.orientation));
        ConstraintWidget nextChainMember = constraintWidget.getNextChainMember(this.orientation);
        while (nextChainMember != null) {
            this.widgets.add(nextChainMember.getRun(this.orientation));
            nextChainMember = nextChainMember.getNextChainMember(this.orientation);
        }
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            WidgetRun next = it.next();
            if (this.orientation == 0) {
                next.widget.horizontalChainRun = this;
            } else if (this.orientation == 1) {
                next.widget.verticalChainRun = this;
            }
        }
        if ((this.orientation == 0 && ((ConstraintWidgetContainer) this.widget.getParent()).isRtl()) && this.widgets.size() > 1) {
            ArrayList<WidgetRun> arrayList = this.widgets;
            this.widget = arrayList.get(arrayList.size() - 1).widget;
        }
        this.chainStyle = this.orientation == 0 ? this.widget.getHorizontalChainStyle() : this.widget.getVerticalChainStyle();
    }

    /* access modifiers changed from: package-private */
    public void clear() {
        this.runGroup = null;
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            it.next().clear();
        }
    }

    /* access modifiers changed from: package-private */
    public void reset() {
        this.start.resolved = false;
        this.end.resolved = false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00cd, code lost:
        if (r3.dimension.resolved != false) goto L_0x00cf;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void update(androidx.constraintlayout.core.widgets.analyzer.Dependency r23) {
        /*
            r22 = this;
            r0 = r22
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r0.start
            boolean r1 = r1.resolved
            if (r1 == 0) goto L_0x0412
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r0.end
            boolean r1 = r1.resolved
            if (r1 != 0) goto L_0x0010
            goto L_0x0412
        L_0x0010:
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r0.widget
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r1.getParent()
            boolean r2 = r1 instanceof androidx.constraintlayout.core.widgets.ConstraintWidgetContainer
            if (r2 == 0) goto L_0x0021
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r1 = (androidx.constraintlayout.core.widgets.ConstraintWidgetContainer) r1
            boolean r1 = r1.isRtl()
            goto L_0x0022
        L_0x0021:
            r1 = 0
        L_0x0022:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r0.end
            int r2 = r2.value
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r4 = r0.start
            int r4 = r4.value
            int r2 = r2 - r4
            java.util.ArrayList<androidx.constraintlayout.core.widgets.analyzer.WidgetRun> r4 = r0.widgets
            int r4 = r4.size()
            r5 = 0
        L_0x0032:
            r6 = -1
            r7 = 8
            if (r5 >= r4) goto L_0x004a
            java.util.ArrayList<androidx.constraintlayout.core.widgets.analyzer.WidgetRun> r8 = r0.widgets
            java.lang.Object r8 = r8.get(r5)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r8 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r8
            androidx.constraintlayout.core.widgets.ConstraintWidget r8 = r8.widget
            int r8 = r8.getVisibility()
            if (r8 != r7) goto L_0x004b
            int r5 = r5 + 1
            goto L_0x0032
        L_0x004a:
            r5 = -1
        L_0x004b:
            int r8 = r4 + -1
            r9 = r8
        L_0x004e:
            if (r9 < 0) goto L_0x0064
            java.util.ArrayList<androidx.constraintlayout.core.widgets.analyzer.WidgetRun> r10 = r0.widgets
            java.lang.Object r10 = r10.get(r9)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r10 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r10
            androidx.constraintlayout.core.widgets.ConstraintWidget r10 = r10.widget
            int r10 = r10.getVisibility()
            if (r10 != r7) goto L_0x0063
            int r9 = r9 + -1
            goto L_0x004e
        L_0x0063:
            r6 = r9
        L_0x0064:
            r9 = 0
        L_0x0065:
            r11 = 2
            r12 = 1
            if (r9 >= r11) goto L_0x0104
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
        L_0x0070:
            if (r13 >= r4) goto L_0x00f6
            java.util.ArrayList<androidx.constraintlayout.core.widgets.analyzer.WidgetRun> r3 = r0.widgets
            java.lang.Object r3 = r3.get(r13)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r3 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r3
            androidx.constraintlayout.core.widgets.ConstraintWidget r11 = r3.widget
            int r11 = r11.getVisibility()
            if (r11 != r7) goto L_0x0084
            goto L_0x00ef
        L_0x0084:
            int r16 = r16 + 1
            if (r13 <= 0) goto L_0x008f
            if (r13 < r5) goto L_0x008f
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r11 = r3.start
            int r11 = r11.margin
            int r14 = r14 + r11
        L_0x008f:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r11 = r3.dimension
            int r11 = r11.value
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r7 = r3.dimensionBehavior
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r10 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r7 == r10) goto L_0x009b
            r7 = 1
            goto L_0x009c
        L_0x009b:
            r7 = 0
        L_0x009c:
            if (r7 == 0) goto L_0x00bc
            int r10 = r0.orientation
            if (r10 != 0) goto L_0x00ad
            androidx.constraintlayout.core.widgets.ConstraintWidget r10 = r3.widget
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r10 = r10.horizontalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r10 = r10.dimension
            boolean r10 = r10.resolved
            if (r10 != 0) goto L_0x00ad
            return
        L_0x00ad:
            int r10 = r0.orientation
            if (r10 != r12) goto L_0x00d0
            androidx.constraintlayout.core.widgets.ConstraintWidget r10 = r3.widget
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r10 = r10.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r10 = r10.dimension
            boolean r10 = r10.resolved
            if (r10 != 0) goto L_0x00d0
            return
        L_0x00bc:
            int r10 = r3.matchConstraintsType
            if (r10 != r12) goto L_0x00c9
            if (r9 != 0) goto L_0x00c9
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r7 = r3.dimension
            int r11 = r7.wrapValue
            int r15 = r15 + 1
            goto L_0x00cf
        L_0x00c9:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r10 = r3.dimension
            boolean r10 = r10.resolved
            if (r10 == 0) goto L_0x00d0
        L_0x00cf:
            r7 = 1
        L_0x00d0:
            if (r7 != 0) goto L_0x00e4
            int r15 = r15 + 1
            androidx.constraintlayout.core.widgets.ConstraintWidget r7 = r3.widget
            float[] r7 = r7.mWeight
            int r10 = r0.orientation
            r7 = r7[r10]
            r10 = 0
            int r11 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
            if (r11 < 0) goto L_0x00e5
            float r17 = r17 + r7
            goto L_0x00e5
        L_0x00e4:
            int r14 = r14 + r11
        L_0x00e5:
            if (r13 >= r8) goto L_0x00ef
            if (r13 >= r6) goto L_0x00ef
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r3 = r3.end
            int r3 = r3.margin
            int r3 = -r3
            int r14 = r14 + r3
        L_0x00ef:
            int r13 = r13 + 1
            r7 = 8
            r11 = 2
            goto L_0x0070
        L_0x00f6:
            if (r14 < r2) goto L_0x0101
            if (r15 != 0) goto L_0x00fb
            goto L_0x0101
        L_0x00fb:
            int r9 = r9 + 1
            r7 = 8
            goto L_0x0065
        L_0x0101:
            r3 = r16
            goto L_0x0109
        L_0x0104:
            r3 = 0
            r14 = 0
            r15 = 0
            r17 = 0
        L_0x0109:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r7 = r0.start
            int r7 = r7.value
            if (r1 == 0) goto L_0x0113
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r7 = r0.end
            int r7 = r7.value
        L_0x0113:
            r9 = 1056964608(0x3f000000, float:0.5)
            if (r14 <= r2) goto L_0x012a
            r10 = 1073741824(0x40000000, float:2.0)
            if (r1 == 0) goto L_0x0123
            int r11 = r14 - r2
            float r11 = (float) r11
            float r11 = r11 / r10
            float r11 = r11 + r9
            int r10 = (int) r11
            int r7 = r7 + r10
            goto L_0x012a
        L_0x0123:
            int r11 = r14 - r2
            float r11 = (float) r11
            float r11 = r11 / r10
            float r11 = r11 + r9
            int r10 = (int) r11
            int r7 = r7 - r10
        L_0x012a:
            if (r15 <= 0) goto L_0x0214
            int r10 = r2 - r14
            float r10 = (float) r10
            float r11 = (float) r15
            float r11 = r10 / r11
            float r11 = r11 + r9
            int r11 = (int) r11
            r13 = 0
            r16 = 0
        L_0x0137:
            if (r13 >= r4) goto L_0x01c9
            java.util.ArrayList<androidx.constraintlayout.core.widgets.analyzer.WidgetRun> r12 = r0.widgets
            java.lang.Object r12 = r12.get(r13)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r12 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r12
            androidx.constraintlayout.core.widgets.ConstraintWidget r9 = r12.widget
            int r9 = r9.getVisibility()
            r18 = r11
            r11 = 8
            if (r9 != r11) goto L_0x014f
            goto L_0x01b4
        L_0x014f:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r9 = r12.dimensionBehavior
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r11 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r9 != r11) goto L_0x01b4
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r9 = r12.dimension
            boolean r9 = r9.resolved
            if (r9 != 0) goto L_0x01b4
            r9 = 0
            int r11 = (r17 > r9 ? 1 : (r17 == r9 ? 0 : -1))
            if (r11 <= 0) goto L_0x0171
            androidx.constraintlayout.core.widgets.ConstraintWidget r11 = r12.widget
            float[] r11 = r11.mWeight
            int r9 = r0.orientation
            r9 = r11[r9]
            float r9 = r9 * r10
            float r9 = r9 / r17
            r11 = 1056964608(0x3f000000, float:0.5)
            float r9 = r9 + r11
            int r9 = (int) r9
            goto L_0x0173
        L_0x0171:
            r9 = r18
        L_0x0173:
            int r11 = r0.orientation
            if (r11 != 0) goto L_0x0182
            androidx.constraintlayout.core.widgets.ConstraintWidget r11 = r12.widget
            int r11 = r11.mMatchConstraintMaxWidth
            r19 = r10
            androidx.constraintlayout.core.widgets.ConstraintWidget r10 = r12.widget
            int r10 = r10.mMatchConstraintMinWidth
            goto L_0x018c
        L_0x0182:
            r19 = r10
            androidx.constraintlayout.core.widgets.ConstraintWidget r10 = r12.widget
            int r11 = r10.mMatchConstraintMaxHeight
            androidx.constraintlayout.core.widgets.ConstraintWidget r10 = r12.widget
            int r10 = r10.mMatchConstraintMinHeight
        L_0x018c:
            r20 = r14
            int r14 = r12.matchConstraintsType
            r21 = r7
            r7 = 1
            if (r14 != r7) goto L_0x019e
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r7 = r12.dimension
            int r7 = r7.wrapValue
            int r7 = java.lang.Math.min(r9, r7)
            goto L_0x019f
        L_0x019e:
            r7 = r9
        L_0x019f:
            int r7 = java.lang.Math.max(r10, r7)
            if (r11 <= 0) goto L_0x01a9
            int r7 = java.lang.Math.min(r11, r7)
        L_0x01a9:
            if (r7 == r9) goto L_0x01ae
            int r16 = r16 + 1
            r9 = r7
        L_0x01ae:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r7 = r12.dimension
            r7.resolve(r9)
            goto L_0x01ba
        L_0x01b4:
            r21 = r7
            r19 = r10
            r20 = r14
        L_0x01ba:
            int r13 = r13 + 1
            r11 = r18
            r10 = r19
            r14 = r20
            r7 = r21
            r9 = 1056964608(0x3f000000, float:0.5)
            r12 = 1
            goto L_0x0137
        L_0x01c9:
            r21 = r7
            r20 = r14
            if (r16 <= 0) goto L_0x0205
            int r15 = r15 - r16
            r7 = 0
            r9 = 0
        L_0x01d3:
            if (r7 >= r4) goto L_0x0203
            java.util.ArrayList<androidx.constraintlayout.core.widgets.analyzer.WidgetRun> r10 = r0.widgets
            java.lang.Object r10 = r10.get(r7)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r10 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r10
            androidx.constraintlayout.core.widgets.ConstraintWidget r11 = r10.widget
            int r11 = r11.getVisibility()
            r12 = 8
            if (r11 != r12) goto L_0x01e8
            goto L_0x0200
        L_0x01e8:
            if (r7 <= 0) goto L_0x01f1
            if (r7 < r5) goto L_0x01f1
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r11 = r10.start
            int r11 = r11.margin
            int r9 = r9 + r11
        L_0x01f1:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r11 = r10.dimension
            int r11 = r11.value
            int r9 = r9 + r11
            if (r7 >= r8) goto L_0x0200
            if (r7 >= r6) goto L_0x0200
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r10 = r10.end
            int r10 = r10.margin
            int r10 = -r10
            int r9 = r9 + r10
        L_0x0200:
            int r7 = r7 + 1
            goto L_0x01d3
        L_0x0203:
            r14 = r9
            goto L_0x0207
        L_0x0205:
            r14 = r20
        L_0x0207:
            int r7 = r0.chainStyle
            r9 = 2
            if (r7 != r9) goto L_0x0212
            if (r16 != 0) goto L_0x0212
            r7 = 0
            r0.chainStyle = r7
            goto L_0x021a
        L_0x0212:
            r7 = 0
            goto L_0x021a
        L_0x0214:
            r21 = r7
            r20 = r14
            r7 = 0
            r9 = 2
        L_0x021a:
            if (r14 <= r2) goto L_0x021e
            r0.chainStyle = r9
        L_0x021e:
            if (r3 <= 0) goto L_0x0226
            if (r15 != 0) goto L_0x0226
            if (r5 != r6) goto L_0x0226
            r0.chainStyle = r9
        L_0x0226:
            int r9 = r0.chainStyle
            r10 = 1
            if (r9 != r10) goto L_0x02c9
            if (r3 <= r10) goto L_0x0231
            int r2 = r2 - r14
            int r3 = r3 - r10
            int r2 = r2 / r3
            goto L_0x0238
        L_0x0231:
            if (r3 != r10) goto L_0x0237
            int r2 = r2 - r14
            r3 = 2
            int r2 = r2 / r3
            goto L_0x0238
        L_0x0237:
            r2 = 0
        L_0x0238:
            if (r15 <= 0) goto L_0x023b
            r2 = 0
        L_0x023b:
            r7 = r21
            r3 = 0
        L_0x023e:
            if (r3 >= r4) goto L_0x0412
            if (r1 == 0) goto L_0x0247
            int r9 = r3 + 1
            int r9 = r4 - r9
            goto L_0x0248
        L_0x0247:
            r9 = r3
        L_0x0248:
            java.util.ArrayList<androidx.constraintlayout.core.widgets.analyzer.WidgetRun> r10 = r0.widgets
            java.lang.Object r9 = r10.get(r9)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r9 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r9
            androidx.constraintlayout.core.widgets.ConstraintWidget r10 = r9.widget
            int r10 = r10.getVisibility()
            r11 = 8
            if (r10 != r11) goto L_0x0265
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r10 = r9.start
            r10.resolve(r7)
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r9.end
            r9.resolve(r7)
            goto L_0x02c5
        L_0x0265:
            if (r3 <= 0) goto L_0x026c
            if (r1 == 0) goto L_0x026b
            int r7 = r7 - r2
            goto L_0x026c
        L_0x026b:
            int r7 = r7 + r2
        L_0x026c:
            if (r3 <= 0) goto L_0x027d
            if (r3 < r5) goto L_0x027d
            if (r1 == 0) goto L_0x0278
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r10 = r9.start
            int r10 = r10.margin
            int r7 = r7 - r10
            goto L_0x027d
        L_0x0278:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r10 = r9.start
            int r10 = r10.margin
            int r7 = r7 + r10
        L_0x027d:
            if (r1 == 0) goto L_0x0285
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r10 = r9.end
            r10.resolve(r7)
            goto L_0x028a
        L_0x0285:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r10 = r9.start
            r10.resolve(r7)
        L_0x028a:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r10 = r9.dimension
            int r10 = r10.value
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r11 = r9.dimensionBehavior
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r12 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r11 != r12) goto L_0x029d
            int r11 = r9.matchConstraintsType
            r12 = 1
            if (r11 != r12) goto L_0x029d
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r10 = r9.dimension
            int r10 = r10.wrapValue
        L_0x029d:
            if (r1 == 0) goto L_0x02a1
            int r7 = r7 - r10
            goto L_0x02a2
        L_0x02a1:
            int r7 = r7 + r10
        L_0x02a2:
            if (r1 == 0) goto L_0x02aa
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r10 = r9.start
            r10.resolve(r7)
            goto L_0x02af
        L_0x02aa:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r10 = r9.end
            r10.resolve(r7)
        L_0x02af:
            r10 = 1
            r9.resolved = r10
            if (r3 >= r8) goto L_0x02c5
            if (r3 >= r6) goto L_0x02c5
            if (r1 == 0) goto L_0x02bf
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r9.end
            int r9 = r9.margin
            int r9 = -r9
            int r7 = r7 - r9
            goto L_0x02c5
        L_0x02bf:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r9.end
            int r9 = r9.margin
            int r9 = -r9
            int r7 = r7 + r9
        L_0x02c5:
            int r3 = r3 + 1
            goto L_0x023e
        L_0x02c9:
            if (r9 != 0) goto L_0x035f
            int r2 = r2 - r14
            r9 = 1
            int r3 = r3 + r9
            int r2 = r2 / r3
            if (r15 <= 0) goto L_0x02d2
            r2 = 0
        L_0x02d2:
            r7 = r21
            r3 = 0
        L_0x02d5:
            if (r3 >= r4) goto L_0x0412
            if (r1 == 0) goto L_0x02de
            int r9 = r3 + 1
            int r9 = r4 - r9
            goto L_0x02df
        L_0x02de:
            r9 = r3
        L_0x02df:
            java.util.ArrayList<androidx.constraintlayout.core.widgets.analyzer.WidgetRun> r10 = r0.widgets
            java.lang.Object r9 = r10.get(r9)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r9 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r9
            androidx.constraintlayout.core.widgets.ConstraintWidget r10 = r9.widget
            int r10 = r10.getVisibility()
            r11 = 8
            if (r10 != r11) goto L_0x02fc
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r10 = r9.start
            r10.resolve(r7)
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r9.end
            r9.resolve(r7)
            goto L_0x035b
        L_0x02fc:
            if (r1 == 0) goto L_0x0300
            int r7 = r7 - r2
            goto L_0x0301
        L_0x0300:
            int r7 = r7 + r2
        L_0x0301:
            if (r3 <= 0) goto L_0x0312
            if (r3 < r5) goto L_0x0312
            if (r1 == 0) goto L_0x030d
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r10 = r9.start
            int r10 = r10.margin
            int r7 = r7 - r10
            goto L_0x0312
        L_0x030d:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r10 = r9.start
            int r10 = r10.margin
            int r7 = r7 + r10
        L_0x0312:
            if (r1 == 0) goto L_0x031a
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r10 = r9.end
            r10.resolve(r7)
            goto L_0x031f
        L_0x031a:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r10 = r9.start
            r10.resolve(r7)
        L_0x031f:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r10 = r9.dimension
            int r10 = r10.value
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r11 = r9.dimensionBehavior
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r12 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r11 != r12) goto L_0x0336
            int r11 = r9.matchConstraintsType
            r12 = 1
            if (r11 != r12) goto L_0x0336
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r11 = r9.dimension
            int r11 = r11.wrapValue
            int r10 = java.lang.Math.min(r10, r11)
        L_0x0336:
            if (r1 == 0) goto L_0x033a
            int r7 = r7 - r10
            goto L_0x033b
        L_0x033a:
            int r7 = r7 + r10
        L_0x033b:
            if (r1 == 0) goto L_0x0343
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r10 = r9.start
            r10.resolve(r7)
            goto L_0x0348
        L_0x0343:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r10 = r9.end
            r10.resolve(r7)
        L_0x0348:
            if (r3 >= r8) goto L_0x035b
            if (r3 >= r6) goto L_0x035b
            if (r1 == 0) goto L_0x0355
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r9.end
            int r9 = r9.margin
            int r9 = -r9
            int r7 = r7 - r9
            goto L_0x035b
        L_0x0355:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r9.end
            int r9 = r9.margin
            int r9 = -r9
            int r7 = r7 + r9
        L_0x035b:
            int r3 = r3 + 1
            goto L_0x02d5
        L_0x035f:
            r3 = 2
            if (r9 != r3) goto L_0x0412
            int r3 = r0.orientation
            if (r3 != 0) goto L_0x036d
            androidx.constraintlayout.core.widgets.ConstraintWidget r3 = r0.widget
            float r3 = r3.getHorizontalBiasPercent()
            goto L_0x0373
        L_0x036d:
            androidx.constraintlayout.core.widgets.ConstraintWidget r3 = r0.widget
            float r3 = r3.getVerticalBiasPercent()
        L_0x0373:
            if (r1 == 0) goto L_0x0379
            r9 = 1065353216(0x3f800000, float:1.0)
            float r3 = r9 - r3
        L_0x0379:
            int r2 = r2 - r14
            float r2 = (float) r2
            float r2 = r2 * r3
            r3 = 1056964608(0x3f000000, float:0.5)
            float r2 = r2 + r3
            int r2 = (int) r2
            if (r2 < 0) goto L_0x0385
            if (r15 <= 0) goto L_0x0386
        L_0x0385:
            r2 = 0
        L_0x0386:
            if (r1 == 0) goto L_0x038b
            int r2 = r21 - r2
            goto L_0x038d
        L_0x038b:
            int r2 = r21 + r2
        L_0x038d:
            r3 = 0
        L_0x038e:
            if (r3 >= r4) goto L_0x0412
            if (r1 == 0) goto L_0x0397
            int r7 = r3 + 1
            int r7 = r4 - r7
            goto L_0x0398
        L_0x0397:
            r7 = r3
        L_0x0398:
            java.util.ArrayList<androidx.constraintlayout.core.widgets.analyzer.WidgetRun> r9 = r0.widgets
            java.lang.Object r7 = r9.get(r7)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r7 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r7
            androidx.constraintlayout.core.widgets.ConstraintWidget r9 = r7.widget
            int r9 = r9.getVisibility()
            r10 = 8
            if (r9 != r10) goto L_0x03b6
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r7.start
            r9.resolve(r2)
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r7 = r7.end
            r7.resolve(r2)
            r12 = 1
            goto L_0x040e
        L_0x03b6:
            if (r3 <= 0) goto L_0x03c7
            if (r3 < r5) goto L_0x03c7
            if (r1 == 0) goto L_0x03c2
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r7.start
            int r9 = r9.margin
            int r2 = r2 - r9
            goto L_0x03c7
        L_0x03c2:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r7.start
            int r9 = r9.margin
            int r2 = r2 + r9
        L_0x03c7:
            if (r1 == 0) goto L_0x03cf
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r7.end
            r9.resolve(r2)
            goto L_0x03d4
        L_0x03cf:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r7.start
            r9.resolve(r2)
        L_0x03d4:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r9 = r7.dimension
            int r9 = r9.value
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r11 = r7.dimensionBehavior
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r12 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r11 != r12) goto L_0x03e8
            int r11 = r7.matchConstraintsType
            r12 = 1
            if (r11 != r12) goto L_0x03e9
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r9 = r7.dimension
            int r9 = r9.wrapValue
            goto L_0x03e9
        L_0x03e8:
            r12 = 1
        L_0x03e9:
            if (r1 == 0) goto L_0x03ed
            int r2 = r2 - r9
            goto L_0x03ee
        L_0x03ed:
            int r2 = r2 + r9
        L_0x03ee:
            if (r1 == 0) goto L_0x03f6
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r7.start
            r9.resolve(r2)
            goto L_0x03fb
        L_0x03f6:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r7.end
            r9.resolve(r2)
        L_0x03fb:
            if (r3 >= r8) goto L_0x040e
            if (r3 >= r6) goto L_0x040e
            if (r1 == 0) goto L_0x0408
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r7 = r7.end
            int r7 = r7.margin
            int r7 = -r7
            int r2 = r2 - r7
            goto L_0x040e
        L_0x0408:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r7 = r7.end
            int r7 = r7.margin
            int r7 = -r7
            int r2 = r2 + r7
        L_0x040e:
            int r3 = r3 + 1
            goto L_0x038e
        L_0x0412:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.ChainRun.update(androidx.constraintlayout.core.widgets.analyzer.Dependency):void");
    }

    public void applyToWidget() {
        for (int i = 0; i < this.widgets.size(); i++) {
            this.widgets.get(i).applyToWidget();
        }
    }

    private ConstraintWidget getFirstVisibleWidget() {
        for (int i = 0; i < this.widgets.size(); i++) {
            WidgetRun widgetRun = this.widgets.get(i);
            if (widgetRun.widget.getVisibility() != 8) {
                return widgetRun.widget;
            }
        }
        return null;
    }

    private ConstraintWidget getLastVisibleWidget() {
        for (int size = this.widgets.size() - 1; size >= 0; size--) {
            WidgetRun widgetRun = this.widgets.get(size);
            if (widgetRun.widget.getVisibility() != 8) {
                return widgetRun.widget;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void apply() {
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            it.next().apply();
        }
        int size = this.widgets.size();
        if (size >= 1) {
            ConstraintWidget constraintWidget = this.widgets.get(0).widget;
            ConstraintWidget constraintWidget2 = this.widgets.get(size - 1).widget;
            if (this.orientation == 0) {
                ConstraintAnchor constraintAnchor = constraintWidget.mLeft;
                ConstraintAnchor constraintAnchor2 = constraintWidget2.mRight;
                DependencyNode target = getTarget(constraintAnchor, 0);
                int margin = constraintAnchor.getMargin();
                ConstraintWidget firstVisibleWidget = getFirstVisibleWidget();
                if (firstVisibleWidget != null) {
                    margin = firstVisibleWidget.mLeft.getMargin();
                }
                if (target != null) {
                    addTarget(this.start, target, margin);
                }
                DependencyNode target2 = getTarget(constraintAnchor2, 0);
                int margin2 = constraintAnchor2.getMargin();
                ConstraintWidget lastVisibleWidget = getLastVisibleWidget();
                if (lastVisibleWidget != null) {
                    margin2 = lastVisibleWidget.mRight.getMargin();
                }
                if (target2 != null) {
                    addTarget(this.end, target2, -margin2);
                }
            } else {
                ConstraintAnchor constraintAnchor3 = constraintWidget.mTop;
                ConstraintAnchor constraintAnchor4 = constraintWidget2.mBottom;
                DependencyNode target3 = getTarget(constraintAnchor3, 1);
                int margin3 = constraintAnchor3.getMargin();
                ConstraintWidget firstVisibleWidget2 = getFirstVisibleWidget();
                if (firstVisibleWidget2 != null) {
                    margin3 = firstVisibleWidget2.mTop.getMargin();
                }
                if (target3 != null) {
                    addTarget(this.start, target3, margin3);
                }
                DependencyNode target4 = getTarget(constraintAnchor4, 1);
                int margin4 = constraintAnchor4.getMargin();
                ConstraintWidget lastVisibleWidget2 = getLastVisibleWidget();
                if (lastVisibleWidget2 != null) {
                    margin4 = lastVisibleWidget2.mBottom.getMargin();
                }
                if (target4 != null) {
                    addTarget(this.end, target4, -margin4);
                }
            }
            this.start.updateDelegate = this;
            this.end.updateDelegate = this;
        }
    }
}
