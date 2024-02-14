package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.Barrier;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Guideline;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import java.util.ArrayList;
import java.util.Iterator;

public class Direct {
    private static final boolean APPLY_MATCH_PARENT = false;
    private static final boolean DEBUG = false;
    private static final boolean EARLY_TERMINATION = true;
    private static int hcount = 0;
    private static BasicMeasure.Measure measure = new BasicMeasure.Measure();
    private static int vcount = 0;

    public static void solvingPass(ConstraintWidgetContainer constraintWidgetContainer, BasicMeasure.Measurer measurer) {
        ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = constraintWidgetContainer.getHorizontalDimensionBehaviour();
        ConstraintWidget.DimensionBehaviour verticalDimensionBehaviour = constraintWidgetContainer.getVerticalDimensionBehaviour();
        hcount = 0;
        vcount = 0;
        constraintWidgetContainer.resetFinalResolution();
        ArrayList<ConstraintWidget> children = constraintWidgetContainer.getChildren();
        int size = children.size();
        for (int i = 0; i < size; i++) {
            children.get(i).resetFinalResolution();
        }
        boolean isRtl = constraintWidgetContainer.isRtl();
        if (horizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.FIXED) {
            constraintWidgetContainer.setFinalHorizontal(0, constraintWidgetContainer.getWidth());
        } else {
            constraintWidgetContainer.setFinalLeft(0);
        }
        boolean z = false;
        boolean z2 = false;
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintWidget constraintWidget = children.get(i2);
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                if (guideline.getOrientation() == 1) {
                    if (guideline.getRelativeBegin() != -1) {
                        guideline.setFinalValue(guideline.getRelativeBegin());
                    } else if (guideline.getRelativeEnd() != -1 && constraintWidgetContainer.isResolvedHorizontally()) {
                        guideline.setFinalValue(constraintWidgetContainer.getWidth() - guideline.getRelativeEnd());
                    } else if (constraintWidgetContainer.isResolvedHorizontally()) {
                        guideline.setFinalValue((int) ((guideline.getRelativePercent() * ((float) constraintWidgetContainer.getWidth())) + 0.5f));
                    }
                    z = EARLY_TERMINATION;
                }
            } else if ((constraintWidget instanceof Barrier) && ((Barrier) constraintWidget).getOrientation() == 0) {
                z2 = EARLY_TERMINATION;
            }
        }
        if (z) {
            for (int i3 = 0; i3 < size; i3++) {
                ConstraintWidget constraintWidget2 = children.get(i3);
                if (constraintWidget2 instanceof Guideline) {
                    Guideline guideline2 = (Guideline) constraintWidget2;
                    if (guideline2.getOrientation() == 1) {
                        horizontalSolvingPass(0, guideline2, measurer, isRtl);
                    }
                }
            }
        }
        horizontalSolvingPass(0, constraintWidgetContainer, measurer, isRtl);
        if (z2) {
            for (int i4 = 0; i4 < size; i4++) {
                ConstraintWidget constraintWidget3 = children.get(i4);
                if (constraintWidget3 instanceof Barrier) {
                    Barrier barrier = (Barrier) constraintWidget3;
                    if (barrier.getOrientation() == 0) {
                        solveBarrier(0, barrier, measurer, 0, isRtl);
                    }
                }
            }
        }
        if (verticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.FIXED) {
            constraintWidgetContainer.setFinalVertical(0, constraintWidgetContainer.getHeight());
        } else {
            constraintWidgetContainer.setFinalTop(0);
        }
        boolean z3 = false;
        boolean z4 = false;
        for (int i5 = 0; i5 < size; i5++) {
            ConstraintWidget constraintWidget4 = children.get(i5);
            if (constraintWidget4 instanceof Guideline) {
                Guideline guideline3 = (Guideline) constraintWidget4;
                if (guideline3.getOrientation() == 0) {
                    if (guideline3.getRelativeBegin() != -1) {
                        guideline3.setFinalValue(guideline3.getRelativeBegin());
                    } else if (guideline3.getRelativeEnd() != -1 && constraintWidgetContainer.isResolvedVertically()) {
                        guideline3.setFinalValue(constraintWidgetContainer.getHeight() - guideline3.getRelativeEnd());
                    } else if (constraintWidgetContainer.isResolvedVertically()) {
                        guideline3.setFinalValue((int) ((guideline3.getRelativePercent() * ((float) constraintWidgetContainer.getHeight())) + 0.5f));
                    }
                    z3 = EARLY_TERMINATION;
                }
            } else if ((constraintWidget4 instanceof Barrier) && ((Barrier) constraintWidget4).getOrientation() == 1) {
                z4 = EARLY_TERMINATION;
            }
        }
        if (z3) {
            for (int i6 = 0; i6 < size; i6++) {
                ConstraintWidget constraintWidget5 = children.get(i6);
                if (constraintWidget5 instanceof Guideline) {
                    Guideline guideline4 = (Guideline) constraintWidget5;
                    if (guideline4.getOrientation() == 0) {
                        verticalSolvingPass(1, guideline4, measurer);
                    }
                }
            }
        }
        verticalSolvingPass(0, constraintWidgetContainer, measurer);
        if (z4) {
            for (int i7 = 0; i7 < size; i7++) {
                ConstraintWidget constraintWidget6 = children.get(i7);
                if (constraintWidget6 instanceof Barrier) {
                    Barrier barrier2 = (Barrier) constraintWidget6;
                    if (barrier2.getOrientation() == 1) {
                        solveBarrier(0, barrier2, measurer, 1, isRtl);
                    }
                }
            }
        }
        for (int i8 = 0; i8 < size; i8++) {
            ConstraintWidget constraintWidget7 = children.get(i8);
            if (constraintWidget7.isMeasureRequested() && canMeasure(0, constraintWidget7)) {
                ConstraintWidgetContainer.measure(0, constraintWidget7, measurer, measure, BasicMeasure.Measure.SELF_DIMENSIONS);
                if (!(constraintWidget7 instanceof Guideline)) {
                    horizontalSolvingPass(0, constraintWidget7, measurer, isRtl);
                    verticalSolvingPass(0, constraintWidget7, measurer);
                } else if (((Guideline) constraintWidget7).getOrientation() == 0) {
                    verticalSolvingPass(0, constraintWidget7, measurer);
                } else {
                    horizontalSolvingPass(0, constraintWidget7, measurer, isRtl);
                }
            }
        }
    }

    private static void solveBarrier(int i, Barrier barrier, BasicMeasure.Measurer measurer, int i2, boolean z) {
        if (!barrier.allSolved()) {
            return;
        }
        if (i2 == 0) {
            horizontalSolvingPass(i + 1, barrier, measurer, z);
        } else {
            verticalSolvingPass(i + 1, barrier, measurer);
        }
    }

    public static String ls(int i) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("  ");
        }
        sb.append("+-(" + i + ") ");
        return sb.toString();
    }

    private static void horizontalSolvingPass(int i, ConstraintWidget constraintWidget, BasicMeasure.Measurer measurer, boolean z) {
        ConstraintWidget constraintWidget2 = constraintWidget;
        BasicMeasure.Measurer measurer2 = measurer;
        boolean z2 = z;
        if (!constraintWidget.isHorizontalSolvingPassDone()) {
            hcount++;
            if (!(constraintWidget2 instanceof ConstraintWidgetContainer) && constraintWidget.isMeasureRequested()) {
                int i2 = i + 1;
                if (canMeasure(i2, constraintWidget2)) {
                    ConstraintWidgetContainer.measure(i2, constraintWidget2, measurer2, new BasicMeasure.Measure(), BasicMeasure.Measure.SELF_DIMENSIONS);
                }
            }
            ConstraintAnchor anchor = constraintWidget2.getAnchor(ConstraintAnchor.Type.LEFT);
            ConstraintAnchor anchor2 = constraintWidget2.getAnchor(ConstraintAnchor.Type.RIGHT);
            int finalValue = anchor.getFinalValue();
            int finalValue2 = anchor2.getFinalValue();
            if (anchor.getDependents() != null && anchor.hasFinalValue()) {
                Iterator<ConstraintAnchor> it = anchor.getDependents().iterator();
                while (it.hasNext()) {
                    ConstraintAnchor next = it.next();
                    ConstraintWidget constraintWidget3 = next.mOwner;
                    int i3 = i + 1;
                    boolean canMeasure = canMeasure(i3, constraintWidget3);
                    if (constraintWidget3.isMeasureRequested() && canMeasure) {
                        ConstraintWidgetContainer.measure(i3, constraintWidget3, measurer2, new BasicMeasure.Measure(), BasicMeasure.Measure.SELF_DIMENSIONS);
                    }
                    boolean z3 = ((next != constraintWidget3.mLeft || constraintWidget3.mRight.mTarget == null || !constraintWidget3.mRight.mTarget.hasFinalValue()) && (next != constraintWidget3.mRight || constraintWidget3.mLeft.mTarget == null || !constraintWidget3.mLeft.mTarget.hasFinalValue())) ? false : EARLY_TERMINATION;
                    if (constraintWidget3.getHorizontalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || canMeasure) {
                        if (!constraintWidget3.isMeasureRequested()) {
                            if (next == constraintWidget3.mLeft && constraintWidget3.mRight.mTarget == null) {
                                int margin = constraintWidget3.mLeft.getMargin() + finalValue;
                                constraintWidget3.setFinalHorizontal(margin, constraintWidget3.getWidth() + margin);
                                horizontalSolvingPass(i3, constraintWidget3, measurer2, z2);
                            } else if (next == constraintWidget3.mRight && constraintWidget3.mLeft.mTarget == null) {
                                int margin2 = finalValue - constraintWidget3.mRight.getMargin();
                                constraintWidget3.setFinalHorizontal(margin2 - constraintWidget3.getWidth(), margin2);
                                horizontalSolvingPass(i3, constraintWidget3, measurer2, z2);
                            } else if (z3 && !constraintWidget3.isInHorizontalChain()) {
                                solveHorizontalCenterConstraints(i3, measurer2, constraintWidget3, z2);
                            }
                        }
                    } else if (constraintWidget3.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget3.mMatchConstraintMaxWidth >= 0 && constraintWidget3.mMatchConstraintMinWidth >= 0 && ((constraintWidget3.getVisibility() == 8 || (constraintWidget3.mMatchConstraintDefaultWidth == 0 && constraintWidget3.getDimensionRatio() == 0.0f)) && !constraintWidget3.isInHorizontalChain() && !constraintWidget3.isInVirtualLayout() && z3 && !constraintWidget3.isInHorizontalChain())) {
                        solveHorizontalMatchConstraint(i3, constraintWidget2, measurer2, constraintWidget3, z2);
                    }
                }
            }
            if (!(constraintWidget2 instanceof Guideline)) {
                if (anchor2.getDependents() != null && anchor2.hasFinalValue()) {
                    Iterator<ConstraintAnchor> it2 = anchor2.getDependents().iterator();
                    while (it2.hasNext()) {
                        ConstraintAnchor next2 = it2.next();
                        ConstraintWidget constraintWidget4 = next2.mOwner;
                        int i4 = i + 1;
                        boolean canMeasure2 = canMeasure(i4, constraintWidget4);
                        if (constraintWidget4.isMeasureRequested() && canMeasure2) {
                            ConstraintWidgetContainer.measure(i4, constraintWidget4, measurer2, new BasicMeasure.Measure(), BasicMeasure.Measure.SELF_DIMENSIONS);
                        }
                        boolean z4 = ((next2 != constraintWidget4.mLeft || constraintWidget4.mRight.mTarget == null || !constraintWidget4.mRight.mTarget.hasFinalValue()) && (next2 != constraintWidget4.mRight || constraintWidget4.mLeft.mTarget == null || !constraintWidget4.mLeft.mTarget.hasFinalValue())) ? false : EARLY_TERMINATION;
                        if (constraintWidget4.getHorizontalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || canMeasure2) {
                            if (!constraintWidget4.isMeasureRequested()) {
                                if (next2 == constraintWidget4.mLeft && constraintWidget4.mRight.mTarget == null) {
                                    int margin3 = constraintWidget4.mLeft.getMargin() + finalValue2;
                                    constraintWidget4.setFinalHorizontal(margin3, constraintWidget4.getWidth() + margin3);
                                    horizontalSolvingPass(i4, constraintWidget4, measurer2, z2);
                                } else if (next2 == constraintWidget4.mRight && constraintWidget4.mLeft.mTarget == null) {
                                    int margin4 = finalValue2 - constraintWidget4.mRight.getMargin();
                                    constraintWidget4.setFinalHorizontal(margin4 - constraintWidget4.getWidth(), margin4);
                                    horizontalSolvingPass(i4, constraintWidget4, measurer2, z2);
                                } else if (z4 && !constraintWidget4.isInHorizontalChain()) {
                                    solveHorizontalCenterConstraints(i4, measurer2, constraintWidget4, z2);
                                }
                            }
                        } else if (constraintWidget4.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget4.mMatchConstraintMaxWidth >= 0 && constraintWidget4.mMatchConstraintMinWidth >= 0) {
                            if ((constraintWidget4.getVisibility() == 8 || (constraintWidget4.mMatchConstraintDefaultWidth == 0 && constraintWidget4.getDimensionRatio() == 0.0f)) && !constraintWidget4.isInHorizontalChain() && !constraintWidget4.isInVirtualLayout() && z4 && !constraintWidget4.isInHorizontalChain()) {
                                solveHorizontalMatchConstraint(i4, constraintWidget2, measurer2, constraintWidget4, z2);
                            }
                        }
                    }
                }
                constraintWidget.markHorizontalSolvingPassDone();
            }
        }
    }

    private static void verticalSolvingPass(int i, ConstraintWidget constraintWidget, BasicMeasure.Measurer measurer) {
        ConstraintWidget constraintWidget2 = constraintWidget;
        BasicMeasure.Measurer measurer2 = measurer;
        if (!constraintWidget.isVerticalSolvingPassDone()) {
            vcount++;
            if (!(constraintWidget2 instanceof ConstraintWidgetContainer) && constraintWidget.isMeasureRequested()) {
                int i2 = i + 1;
                if (canMeasure(i2, constraintWidget2)) {
                    ConstraintWidgetContainer.measure(i2, constraintWidget2, measurer2, new BasicMeasure.Measure(), BasicMeasure.Measure.SELF_DIMENSIONS);
                }
            }
            ConstraintAnchor anchor = constraintWidget2.getAnchor(ConstraintAnchor.Type.TOP);
            ConstraintAnchor anchor2 = constraintWidget2.getAnchor(ConstraintAnchor.Type.BOTTOM);
            int finalValue = anchor.getFinalValue();
            int finalValue2 = anchor2.getFinalValue();
            if (anchor.getDependents() != null && anchor.hasFinalValue()) {
                Iterator<ConstraintAnchor> it = anchor.getDependents().iterator();
                while (it.hasNext()) {
                    ConstraintAnchor next = it.next();
                    ConstraintWidget constraintWidget3 = next.mOwner;
                    int i3 = i + 1;
                    boolean canMeasure = canMeasure(i3, constraintWidget3);
                    if (constraintWidget3.isMeasureRequested() && canMeasure) {
                        ConstraintWidgetContainer.measure(i3, constraintWidget3, measurer2, new BasicMeasure.Measure(), BasicMeasure.Measure.SELF_DIMENSIONS);
                    }
                    boolean z = ((next != constraintWidget3.mTop || constraintWidget3.mBottom.mTarget == null || !constraintWidget3.mBottom.mTarget.hasFinalValue()) && (next != constraintWidget3.mBottom || constraintWidget3.mTop.mTarget == null || !constraintWidget3.mTop.mTarget.hasFinalValue())) ? false : EARLY_TERMINATION;
                    if (constraintWidget3.getVerticalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || canMeasure) {
                        if (!constraintWidget3.isMeasureRequested()) {
                            if (next == constraintWidget3.mTop && constraintWidget3.mBottom.mTarget == null) {
                                int margin = constraintWidget3.mTop.getMargin() + finalValue;
                                constraintWidget3.setFinalVertical(margin, constraintWidget3.getHeight() + margin);
                                verticalSolvingPass(i3, constraintWidget3, measurer2);
                            } else if (next == constraintWidget3.mBottom && constraintWidget3.mTop.mTarget == null) {
                                int margin2 = finalValue - constraintWidget3.mBottom.getMargin();
                                constraintWidget3.setFinalVertical(margin2 - constraintWidget3.getHeight(), margin2);
                                verticalSolvingPass(i3, constraintWidget3, measurer2);
                            } else if (z && !constraintWidget3.isInVerticalChain()) {
                                solveVerticalCenterConstraints(i3, measurer2, constraintWidget3);
                            }
                        }
                    } else if (constraintWidget3.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget3.mMatchConstraintMaxHeight >= 0 && constraintWidget3.mMatchConstraintMinHeight >= 0) {
                        if ((constraintWidget3.getVisibility() == 8 || (constraintWidget3.mMatchConstraintDefaultHeight == 0 && constraintWidget3.getDimensionRatio() == 0.0f)) && !constraintWidget3.isInVerticalChain() && !constraintWidget3.isInVirtualLayout() && z && !constraintWidget3.isInVerticalChain()) {
                            solveVerticalMatchConstraint(i3, constraintWidget2, measurer2, constraintWidget3);
                        }
                    }
                }
            }
            if (!(constraintWidget2 instanceof Guideline)) {
                if (anchor2.getDependents() != null && anchor2.hasFinalValue()) {
                    Iterator<ConstraintAnchor> it2 = anchor2.getDependents().iterator();
                    while (it2.hasNext()) {
                        ConstraintAnchor next2 = it2.next();
                        ConstraintWidget constraintWidget4 = next2.mOwner;
                        int i4 = i + 1;
                        boolean canMeasure2 = canMeasure(i4, constraintWidget4);
                        if (constraintWidget4.isMeasureRequested() && canMeasure2) {
                            ConstraintWidgetContainer.measure(i4, constraintWidget4, measurer2, new BasicMeasure.Measure(), BasicMeasure.Measure.SELF_DIMENSIONS);
                        }
                        boolean z2 = ((next2 != constraintWidget4.mTop || constraintWidget4.mBottom.mTarget == null || !constraintWidget4.mBottom.mTarget.hasFinalValue()) && (next2 != constraintWidget4.mBottom || constraintWidget4.mTop.mTarget == null || !constraintWidget4.mTop.mTarget.hasFinalValue())) ? false : EARLY_TERMINATION;
                        if (constraintWidget4.getVerticalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || canMeasure2) {
                            if (!constraintWidget4.isMeasureRequested()) {
                                if (next2 == constraintWidget4.mTop && constraintWidget4.mBottom.mTarget == null) {
                                    int margin3 = constraintWidget4.mTop.getMargin() + finalValue2;
                                    constraintWidget4.setFinalVertical(margin3, constraintWidget4.getHeight() + margin3);
                                    verticalSolvingPass(i4, constraintWidget4, measurer2);
                                } else if (next2 == constraintWidget4.mBottom && constraintWidget4.mTop.mTarget == null) {
                                    int margin4 = finalValue2 - constraintWidget4.mBottom.getMargin();
                                    constraintWidget4.setFinalVertical(margin4 - constraintWidget4.getHeight(), margin4);
                                    verticalSolvingPass(i4, constraintWidget4, measurer2);
                                } else if (z2 && !constraintWidget4.isInVerticalChain()) {
                                    solveVerticalCenterConstraints(i4, measurer2, constraintWidget4);
                                }
                            }
                        } else if (constraintWidget4.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget4.mMatchConstraintMaxHeight >= 0 && constraintWidget4.mMatchConstraintMinHeight >= 0) {
                            if ((constraintWidget4.getVisibility() == 8 || (constraintWidget4.mMatchConstraintDefaultHeight == 0 && constraintWidget4.getDimensionRatio() == 0.0f)) && !constraintWidget4.isInVerticalChain() && !constraintWidget4.isInVirtualLayout() && z2 && !constraintWidget4.isInVerticalChain()) {
                                solveVerticalMatchConstraint(i4, constraintWidget2, measurer2, constraintWidget4);
                            }
                        }
                    }
                }
                ConstraintAnchor anchor3 = constraintWidget2.getAnchor(ConstraintAnchor.Type.BASELINE);
                if (anchor3.getDependents() != null && anchor3.hasFinalValue()) {
                    int finalValue3 = anchor3.getFinalValue();
                    Iterator<ConstraintAnchor> it3 = anchor3.getDependents().iterator();
                    while (it3.hasNext()) {
                        ConstraintAnchor next3 = it3.next();
                        ConstraintWidget constraintWidget5 = next3.mOwner;
                        int i5 = i + 1;
                        boolean canMeasure3 = canMeasure(i5, constraintWidget5);
                        if (constraintWidget5.isMeasureRequested() && canMeasure3) {
                            ConstraintWidgetContainer.measure(i5, constraintWidget5, measurer2, new BasicMeasure.Measure(), BasicMeasure.Measure.SELF_DIMENSIONS);
                        }
                        if ((constraintWidget5.getVerticalDimensionBehaviour() != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || canMeasure3) && !constraintWidget5.isMeasureRequested() && next3 == constraintWidget5.mBaseline) {
                            constraintWidget5.setFinalBaseline(next3.getMargin() + finalValue3);
                            try {
                                verticalSolvingPass(i5, constraintWidget5, measurer2);
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                    }
                }
                constraintWidget.markVerticalSolvingPassDone();
            }
        }
    }

    private static void solveHorizontalCenterConstraints(int i, BasicMeasure.Measurer measurer, ConstraintWidget constraintWidget, boolean z) {
        float horizontalBiasPercent = constraintWidget.getHorizontalBiasPercent();
        int finalValue = constraintWidget.mLeft.mTarget.getFinalValue();
        int finalValue2 = constraintWidget.mRight.mTarget.getFinalValue();
        int margin = constraintWidget.mLeft.getMargin() + finalValue;
        int margin2 = finalValue2 - constraintWidget.mRight.getMargin();
        if (finalValue == finalValue2) {
            horizontalBiasPercent = 0.5f;
        } else {
            finalValue = margin;
            finalValue2 = margin2;
        }
        int width = constraintWidget.getWidth();
        int i2 = (finalValue2 - finalValue) - width;
        if (finalValue > finalValue2) {
            i2 = (finalValue - finalValue2) - width;
        }
        int i3 = ((int) (i2 > 0 ? (horizontalBiasPercent * ((float) i2)) + 0.5f : horizontalBiasPercent * ((float) i2))) + finalValue;
        int i4 = i3 + width;
        if (finalValue > finalValue2) {
            i4 = i3 - width;
        }
        constraintWidget.setFinalHorizontal(i3, i4);
        horizontalSolvingPass(i + 1, constraintWidget, measurer, z);
    }

    private static void solveVerticalCenterConstraints(int i, BasicMeasure.Measurer measurer, ConstraintWidget constraintWidget) {
        float verticalBiasPercent = constraintWidget.getVerticalBiasPercent();
        int finalValue = constraintWidget.mTop.mTarget.getFinalValue();
        int finalValue2 = constraintWidget.mBottom.mTarget.getFinalValue();
        int margin = constraintWidget.mTop.getMargin() + finalValue;
        int margin2 = finalValue2 - constraintWidget.mBottom.getMargin();
        if (finalValue == finalValue2) {
            verticalBiasPercent = 0.5f;
        } else {
            finalValue = margin;
            finalValue2 = margin2;
        }
        int height = constraintWidget.getHeight();
        int i2 = (finalValue2 - finalValue) - height;
        if (finalValue > finalValue2) {
            i2 = (finalValue - finalValue2) - height;
        }
        int i3 = (int) (i2 > 0 ? (verticalBiasPercent * ((float) i2)) + 0.5f : verticalBiasPercent * ((float) i2));
        int i4 = finalValue + i3;
        int i5 = i4 + height;
        if (finalValue > finalValue2) {
            i4 = finalValue - i3;
            i5 = i4 - height;
        }
        constraintWidget.setFinalVertical(i4, i5);
        verticalSolvingPass(i + 1, constraintWidget, measurer);
    }

    private static void solveHorizontalMatchConstraint(int i, ConstraintWidget constraintWidget, BasicMeasure.Measurer measurer, ConstraintWidget constraintWidget2, boolean z) {
        int i2;
        float horizontalBiasPercent = constraintWidget2.getHorizontalBiasPercent();
        int finalValue = constraintWidget2.mLeft.mTarget.getFinalValue() + constraintWidget2.mLeft.getMargin();
        int finalValue2 = constraintWidget2.mRight.mTarget.getFinalValue() - constraintWidget2.mRight.getMargin();
        if (finalValue2 >= finalValue) {
            int width = constraintWidget2.getWidth();
            if (constraintWidget2.getVisibility() != 8) {
                if (constraintWidget2.mMatchConstraintDefaultWidth == 2) {
                    if (constraintWidget instanceof ConstraintWidgetContainer) {
                        i2 = constraintWidget.getWidth();
                    } else {
                        i2 = constraintWidget.getParent().getWidth();
                    }
                    width = (int) (constraintWidget2.getHorizontalBiasPercent() * 0.5f * ((float) i2));
                } else if (constraintWidget2.mMatchConstraintDefaultWidth == 0) {
                    width = finalValue2 - finalValue;
                }
                width = Math.max(constraintWidget2.mMatchConstraintMinWidth, width);
                if (constraintWidget2.mMatchConstraintMaxWidth > 0) {
                    width = Math.min(constraintWidget2.mMatchConstraintMaxWidth, width);
                }
            }
            int i3 = finalValue + ((int) ((horizontalBiasPercent * ((float) ((finalValue2 - finalValue) - width))) + 0.5f));
            constraintWidget2.setFinalHorizontal(i3, width + i3);
            horizontalSolvingPass(i + 1, constraintWidget2, measurer, z);
        }
    }

    private static void solveVerticalMatchConstraint(int i, ConstraintWidget constraintWidget, BasicMeasure.Measurer measurer, ConstraintWidget constraintWidget2) {
        int i2;
        float verticalBiasPercent = constraintWidget2.getVerticalBiasPercent();
        int finalValue = constraintWidget2.mTop.mTarget.getFinalValue() + constraintWidget2.mTop.getMargin();
        int finalValue2 = constraintWidget2.mBottom.mTarget.getFinalValue() - constraintWidget2.mBottom.getMargin();
        if (finalValue2 >= finalValue) {
            int height = constraintWidget2.getHeight();
            if (constraintWidget2.getVisibility() != 8) {
                if (constraintWidget2.mMatchConstraintDefaultHeight == 2) {
                    if (constraintWidget instanceof ConstraintWidgetContainer) {
                        i2 = constraintWidget.getHeight();
                    } else {
                        i2 = constraintWidget.getParent().getHeight();
                    }
                    height = (int) (verticalBiasPercent * 0.5f * ((float) i2));
                } else if (constraintWidget2.mMatchConstraintDefaultHeight == 0) {
                    height = finalValue2 - finalValue;
                }
                height = Math.max(constraintWidget2.mMatchConstraintMinHeight, height);
                if (constraintWidget2.mMatchConstraintMaxHeight > 0) {
                    height = Math.min(constraintWidget2.mMatchConstraintMaxHeight, height);
                }
            }
            int i3 = finalValue + ((int) ((verticalBiasPercent * ((float) ((finalValue2 - finalValue) - height))) + 0.5f));
            constraintWidget2.setFinalVertical(i3, height + i3);
            verticalSolvingPass(i + 1, constraintWidget2, measurer);
        }
    }

    private static boolean canMeasure(int i, ConstraintWidget constraintWidget) {
        ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour = constraintWidget.getHorizontalDimensionBehaviour();
        ConstraintWidget.DimensionBehaviour verticalDimensionBehaviour = constraintWidget.getVerticalDimensionBehaviour();
        ConstraintWidgetContainer constraintWidgetContainer = constraintWidget.getParent() != null ? (ConstraintWidgetContainer) constraintWidget.getParent() : null;
        if (constraintWidgetContainer != null) {
            ConstraintWidget.DimensionBehaviour horizontalDimensionBehaviour2 = constraintWidgetContainer.getHorizontalDimensionBehaviour();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        if (constraintWidgetContainer != null) {
            ConstraintWidget.DimensionBehaviour verticalDimensionBehaviour2 = constraintWidgetContainer.getVerticalDimensionBehaviour();
            ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        boolean z = (horizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.FIXED || constraintWidget.isResolvedHorizontally() || horizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || (horizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mMatchConstraintDefaultWidth == 0 && constraintWidget.mDimensionRatio == 0.0f && constraintWidget.hasDanglingDimension(0)) || (horizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mMatchConstraintDefaultWidth == 1 && constraintWidget.hasResolvedTargets(0, constraintWidget.getWidth()))) ? EARLY_TERMINATION : false;
        boolean z2 = (verticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.FIXED || constraintWidget.isResolvedVertically() || verticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || (verticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mMatchConstraintDefaultHeight == 0 && constraintWidget.mDimensionRatio == 0.0f && constraintWidget.hasDanglingDimension(1)) || (verticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mMatchConstraintDefaultHeight == 1 && constraintWidget.hasResolvedTargets(1, constraintWidget.getHeight()))) ? EARLY_TERMINATION : false;
        if (constraintWidget.mDimensionRatio > 0.0f && (z || z2)) {
            return EARLY_TERMINATION;
        }
        if (!z || !z2) {
            return false;
        }
        return EARLY_TERMINATION;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0058, code lost:
        r7 = r7.mTarget.getFinalValue() + r4.mListAnchors[r23].getMargin();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x01e1, code lost:
        if (r4.mListAnchors[r23].mTarget.mOwner == r0) goto L_0x01e5;
     */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0129  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0159  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean solveChain(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r20, androidx.constraintlayout.core.LinearSystem r21, int r22, int r23, androidx.constraintlayout.core.widgets.ChainHead r24, boolean r25, boolean r26, boolean r27) {
        /*
            r0 = 0
            if (r27 == 0) goto L_0x0004
            return r0
        L_0x0004:
            if (r22 != 0) goto L_0x000d
            boolean r1 = r20.isResolvedHorizontally()
            if (r1 != 0) goto L_0x0014
            return r0
        L_0x000d:
            boolean r1 = r20.isResolvedVertically()
            if (r1 != 0) goto L_0x0014
            return r0
        L_0x0014:
            boolean r1 = r20.isRtl()
            androidx.constraintlayout.core.widgets.ConstraintWidget r2 = r24.getFirst()
            androidx.constraintlayout.core.widgets.ConstraintWidget r3 = r24.getLast()
            androidx.constraintlayout.core.widgets.ConstraintWidget r4 = r24.getFirstVisibleWidget()
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = r24.getLastVisibleWidget()
            androidx.constraintlayout.core.widgets.ConstraintWidget r6 = r24.getHead()
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r7 = r2.mListAnchors
            r7 = r7[r23]
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r3 = r3.mListAnchors
            int r8 = r23 + 1
            r3 = r3[r8]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r9 = r7.mTarget
            if (r9 == 0) goto L_0x0239
            androidx.constraintlayout.core.widgets.ConstraintAnchor r9 = r3.mTarget
            if (r9 != 0) goto L_0x0040
            goto L_0x0239
        L_0x0040:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r9 = r7.mTarget
            boolean r9 = r9.hasFinalValue()
            if (r9 == 0) goto L_0x0239
            androidx.constraintlayout.core.widgets.ConstraintAnchor r9 = r3.mTarget
            boolean r9 = r9.hasFinalValue()
            if (r9 != 0) goto L_0x0052
            goto L_0x0239
        L_0x0052:
            if (r4 == 0) goto L_0x0239
            if (r5 != 0) goto L_0x0058
            goto L_0x0239
        L_0x0058:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r7 = r7.mTarget
            int r7 = r7.getFinalValue()
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r9 = r4.mListAnchors
            r9 = r9[r23]
            int r9 = r9.getMargin()
            int r7 = r7 + r9
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r3.mTarget
            int r3 = r3.getFinalValue()
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r9 = r5.mListAnchors
            r9 = r9[r8]
            int r9 = r9.getMargin()
            int r3 = r3 - r9
            int r9 = r3 - r7
            if (r9 > 0) goto L_0x007b
            return r0
        L_0x007b:
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure r10 = new androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure
            r10.<init>()
            r13 = r2
            r11 = 0
            r12 = 0
            r14 = 0
            r15 = 0
        L_0x0085:
            r17 = 0
            r0 = 1
            if (r11 != 0) goto L_0x010a
            boolean r18 = canMeasure(r0, r13)
            if (r18 != 0) goto L_0x0093
            r16 = 0
            return r16
        L_0x0093:
            r16 = 0
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r13.mListDimensionBehaviors
            r0 = r0[r22]
            r18 = r2
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r0 != r2) goto L_0x00a0
            return r16
        L_0x00a0:
            boolean r0 = r13.isMeasureRequested()
            if (r0 == 0) goto L_0x00b3
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measurer r0 = r20.getMeasurer()
            int r2 = androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measure.SELF_DIMENSIONS
            r19 = r11
            r11 = 1
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer.measure(r11, r13, r0, r10, r2)
            goto L_0x00b5
        L_0x00b3:
            r19 = r11
        L_0x00b5:
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r0 = r13.mListAnchors
            r0 = r0[r23]
            int r0 = r0.getMargin()
            int r15 = r15 + r0
            if (r22 != 0) goto L_0x00c5
            int r0 = r13.getWidth()
            goto L_0x00c9
        L_0x00c5:
            int r0 = r13.getHeight()
        L_0x00c9:
            int r15 = r15 + r0
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r0 = r13.mListAnchors
            r0 = r0[r8]
            int r0 = r0.getMargin()
            int r15 = r15 + r0
            int r14 = r14 + 1
            int r0 = r13.getVisibility()
            r2 = 8
            if (r0 == r2) goto L_0x00df
            int r12 = r12 + 1
        L_0x00df:
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r0 = r13.mListAnchors
            r0 = r0[r8]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r0.mTarget
            if (r0 == 0) goto L_0x00fe
            androidx.constraintlayout.core.widgets.ConstraintWidget r0 = r0.mOwner
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r2 = r0.mListAnchors
            r2 = r2[r23]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x00fe
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r2 = r0.mListAnchors
            r2 = r2[r23]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r2.mTarget
            androidx.constraintlayout.core.widgets.ConstraintWidget r2 = r2.mOwner
            if (r2 == r13) goto L_0x00fc
            goto L_0x00fe
        L_0x00fc:
            r17 = r0
        L_0x00fe:
            if (r17 == 0) goto L_0x0105
            r13 = r17
            r11 = r19
            goto L_0x0106
        L_0x0105:
            r11 = 1
        L_0x0106:
            r2 = r18
            goto L_0x0085
        L_0x010a:
            r18 = r2
            if (r12 != 0) goto L_0x0110
            r0 = 0
            return r0
        L_0x0110:
            r0 = 0
            if (r12 == r14) goto L_0x0114
            return r0
        L_0x0114:
            if (r9 >= r15) goto L_0x0117
            return r0
        L_0x0117:
            int r9 = r9 - r15
            r0 = 2
            if (r25 == 0) goto L_0x0120
            int r2 = r12 + 1
            int r9 = r9 / r2
        L_0x011e:
            r2 = 1
            goto L_0x0127
        L_0x0120:
            if (r26 == 0) goto L_0x011e
            if (r12 <= r0) goto L_0x011e
            int r9 = r9 / r12
            r2 = 1
            int r9 = r9 - r2
        L_0x0127:
            if (r12 != r2) goto L_0x0159
            if (r22 != 0) goto L_0x0130
            float r0 = r6.getHorizontalBiasPercent()
            goto L_0x0134
        L_0x0130:
            float r0 = r6.getVerticalBiasPercent()
        L_0x0134:
            r2 = 1056964608(0x3f000000, float:0.5)
            float r3 = (float) r7
            float r3 = r3 + r2
            float r2 = (float) r9
            float r2 = r2 * r0
            float r3 = r3 + r2
            int r0 = (int) r3
            if (r22 != 0) goto L_0x0148
            int r2 = r4.getWidth()
            int r2 = r2 + r0
            r4.setFinalHorizontal(r0, r2)
            goto L_0x0150
        L_0x0148:
            int r2 = r4.getHeight()
            int r2 = r2 + r0
            r4.setFinalVertical(r0, r2)
        L_0x0150:
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measurer r0 = r20.getMeasurer()
            r2 = 1
            horizontalSolvingPass(r2, r4, r0, r1)
            return r2
        L_0x0159:
            if (r25 == 0) goto L_0x01ed
            int r7 = r7 + r9
            r0 = r18
            r3 = 0
        L_0x015f:
            if (r3 != 0) goto L_0x0238
            int r4 = r0.getVisibility()
            r5 = 8
            if (r4 != r5) goto L_0x0184
            if (r22 != 0) goto L_0x0176
            r0.setFinalHorizontal(r7, r7)
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measurer r4 = r20.getMeasurer()
            horizontalSolvingPass(r2, r0, r4, r1)
            goto L_0x0180
        L_0x0176:
            r0.setFinalVertical(r7, r7)
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measurer r4 = r20.getMeasurer()
            verticalSolvingPass(r2, r0, r4)
        L_0x0180:
            r2 = r21
            r4 = 0
            goto L_0x01c4
        L_0x0184:
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r2 = r0.mListAnchors
            r2 = r2[r23]
            int r2 = r2.getMargin()
            int r7 = r7 + r2
            if (r22 != 0) goto L_0x01a4
            int r2 = r0.getWidth()
            int r2 = r2 + r7
            r0.setFinalHorizontal(r7, r2)
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measurer r2 = r20.getMeasurer()
            r4 = 1
            horizontalSolvingPass(r4, r0, r2, r1)
            int r2 = r0.getWidth()
            goto L_0x01b8
        L_0x01a4:
            r4 = 1
            int r2 = r0.getHeight()
            int r2 = r2 + r7
            r0.setFinalVertical(r7, r2)
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measurer r2 = r20.getMeasurer()
            verticalSolvingPass(r4, r0, r2)
            int r2 = r0.getHeight()
        L_0x01b8:
            int r7 = r7 + r2
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r2 = r0.mListAnchors
            r2 = r2[r8]
            int r2 = r2.getMargin()
            int r7 = r7 + r2
            int r7 = r7 + r9
            goto L_0x0180
        L_0x01c4:
            r0.addToSolver(r2, r4)
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r4 = r0.mListAnchors
            r4 = r4[r8]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 == 0) goto L_0x01e3
            androidx.constraintlayout.core.widgets.ConstraintWidget r4 = r4.mOwner
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r6 = r4.mListAnchors
            r6 = r6[r23]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r6 = r6.mTarget
            if (r6 == 0) goto L_0x01e3
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r6 = r4.mListAnchors
            r6 = r6[r23]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r6 = r6.mTarget
            androidx.constraintlayout.core.widgets.ConstraintWidget r6 = r6.mOwner
            if (r6 == r0) goto L_0x01e5
        L_0x01e3:
            r4 = r17
        L_0x01e5:
            if (r4 == 0) goto L_0x01e9
            r0 = r4
            goto L_0x01ea
        L_0x01e9:
            r3 = 1
        L_0x01ea:
            r2 = 1
            goto L_0x015f
        L_0x01ed:
            if (r26 == 0) goto L_0x0237
            if (r12 != r0) goto L_0x0235
            if (r22 != 0) goto L_0x0214
            int r0 = r4.getWidth()
            int r0 = r0 + r7
            r4.setFinalHorizontal(r7, r0)
            int r0 = r5.getWidth()
            int r0 = r3 - r0
            r5.setFinalHorizontal(r0, r3)
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measurer r0 = r20.getMeasurer()
            r2 = 1
            horizontalSolvingPass(r2, r4, r0, r1)
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measurer r0 = r20.getMeasurer()
            horizontalSolvingPass(r2, r5, r0, r1)
            goto L_0x0234
        L_0x0214:
            r2 = 1
            int r0 = r4.getHeight()
            int r0 = r0 + r7
            r4.setFinalVertical(r7, r0)
            int r0 = r5.getHeight()
            int r0 = r3 - r0
            r5.setFinalVertical(r0, r3)
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measurer r0 = r20.getMeasurer()
            verticalSolvingPass(r2, r4, r0)
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measurer r0 = r20.getMeasurer()
            verticalSolvingPass(r2, r5, r0)
        L_0x0234:
            return r2
        L_0x0235:
            r0 = 0
            return r0
        L_0x0237:
            r2 = 1
        L_0x0238:
            return r2
        L_0x0239:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.Direct.solveChain(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer, androidx.constraintlayout.core.LinearSystem, int, int, androidx.constraintlayout.core.widgets.ChainHead, boolean, boolean, boolean):boolean");
    }
}
