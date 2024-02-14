package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;

public class Placeholder extends VirtualLayout {
    public void measure(int i, int i2, int i3, int i4) {
        boolean z = false;
        int paddingLeft = getPaddingLeft() + getPaddingRight() + 0;
        int paddingTop = getPaddingTop() + getPaddingBottom() + 0;
        if (this.mWidgetsCount > 0) {
            paddingLeft += this.mWidgets[0].getWidth();
            paddingTop += this.mWidgets[0].getHeight();
        }
        int max = Math.max(getMinWidth(), paddingLeft);
        int max2 = Math.max(getMinHeight(), paddingTop);
        if (i != 1073741824) {
            i2 = i == Integer.MIN_VALUE ? Math.min(max, i2) : i == 0 ? max : 0;
        }
        if (i3 != 1073741824) {
            i4 = i3 == Integer.MIN_VALUE ? Math.min(max2, i4) : i3 == 0 ? max2 : 0;
        }
        setMeasure(i2, i4);
        setWidth(i2);
        setHeight(i4);
        if (this.mWidgetsCount > 0) {
            z = true;
        }
        needsCallbackFromSolver(z);
    }

    public void addToSolver(LinearSystem linearSystem, boolean z) {
        super.addToSolver(linearSystem, z);
        if (this.mWidgetsCount > 0) {
            ConstraintWidget constraintWidget = this.mWidgets[0];
            constraintWidget.resetAllConstraints();
            constraintWidget.connect(ConstraintAnchor.Type.LEFT, (ConstraintWidget) this, ConstraintAnchor.Type.LEFT);
            constraintWidget.connect(ConstraintAnchor.Type.RIGHT, (ConstraintWidget) this, ConstraintAnchor.Type.RIGHT);
            constraintWidget.connect(ConstraintAnchor.Type.TOP, (ConstraintWidget) this, ConstraintAnchor.Type.TOP);
            constraintWidget.connect(ConstraintAnchor.Type.BOTTOM, (ConstraintWidget) this, ConstraintAnchor.Type.BOTTOM);
        }
    }
}
