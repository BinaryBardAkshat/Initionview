package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import com.google.android.material.color.MaterialColors;

final class CircularDrawingDelegate extends DrawingDelegate<CircularProgressIndicatorSpec> {
    private float adjustedRadius;
    private int arcDirectionFactor = 1;
    private float displayedCornerRadius;
    private float displayedTrackThickness;

    public CircularDrawingDelegate(CircularProgressIndicatorSpec circularProgressIndicatorSpec) {
        super(circularProgressIndicatorSpec);
    }

    public int getPreferredWidth() {
        return getSize();
    }

    public int getPreferredHeight() {
        return getSize();
    }

    public void adjustCanvas(Canvas canvas, float f) {
        float f2 = (((float) ((CircularProgressIndicatorSpec) this.spec).indicatorSize) / 2.0f) + ((float) ((CircularProgressIndicatorSpec) this.spec).indicatorInset);
        canvas.translate(f2, f2);
        canvas.rotate(-90.0f);
        float f3 = -f2;
        canvas.clipRect(f3, f3, f2, f2);
        this.arcDirectionFactor = ((CircularProgressIndicatorSpec) this.spec).indicatorDirection == 0 ? 1 : -1;
        this.displayedTrackThickness = ((float) ((CircularProgressIndicatorSpec) this.spec).trackThickness) * f;
        this.displayedCornerRadius = ((float) ((CircularProgressIndicatorSpec) this.spec).trackCornerRadius) * f;
        this.adjustedRadius = ((float) (((CircularProgressIndicatorSpec) this.spec).indicatorSize - ((CircularProgressIndicatorSpec) this.spec).trackThickness)) / 2.0f;
        if ((this.drawable.isShowing() && ((CircularProgressIndicatorSpec) this.spec).showAnimationBehavior == 2) || (this.drawable.isHiding() && ((CircularProgressIndicatorSpec) this.spec).hideAnimationBehavior == 1)) {
            this.adjustedRadius += ((1.0f - f) * ((float) ((CircularProgressIndicatorSpec) this.spec).trackThickness)) / 2.0f;
        } else if ((this.drawable.isShowing() && ((CircularProgressIndicatorSpec) this.spec).showAnimationBehavior == 1) || (this.drawable.isHiding() && ((CircularProgressIndicatorSpec) this.spec).hideAnimationBehavior == 2)) {
            this.adjustedRadius -= ((1.0f - f) * ((float) ((CircularProgressIndicatorSpec) this.spec).trackThickness)) / 2.0f;
        }
    }

    /* access modifiers changed from: package-private */
    public void fillIndicator(Canvas canvas, Paint paint, float f, float f2, int i) {
        Paint paint2 = paint;
        if (f != f2) {
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeCap(Paint.Cap.BUTT);
            paint.setAntiAlias(true);
            paint.setColor(i);
            paint.setStrokeWidth(this.displayedTrackThickness);
            int i2 = this.arcDirectionFactor;
            float f3 = f * 360.0f * ((float) i2);
            float f4 = (f2 >= f ? f2 - f : (1.0f + f2) - f) * 360.0f * ((float) i2);
            float f5 = this.adjustedRadius;
            canvas.drawArc(new RectF(-f5, -f5, f5, f5), f3, f4, false, paint);
            if (this.displayedCornerRadius > 0.0f && Math.abs(f4) < 360.0f) {
                paint.setStyle(Paint.Style.FILL);
                Canvas canvas2 = canvas;
                Paint paint3 = paint;
                drawRoundedEnd(canvas2, paint3, this.displayedTrackThickness, this.displayedCornerRadius, f3);
                drawRoundedEnd(canvas2, paint3, this.displayedTrackThickness, this.displayedCornerRadius, f3 + f4);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void fillTrack(Canvas canvas, Paint paint) {
        int compositeARGBWithAlpha = MaterialColors.compositeARGBWithAlpha(((CircularProgressIndicatorSpec) this.spec).trackColor, this.drawable.getAlpha());
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setAntiAlias(true);
        paint.setColor(compositeARGBWithAlpha);
        paint.setStrokeWidth(this.displayedTrackThickness);
        float f = this.adjustedRadius;
        canvas.drawArc(new RectF(-f, -f, f, f), 0.0f, 360.0f, false, paint);
    }

    private int getSize() {
        return ((CircularProgressIndicatorSpec) this.spec).indicatorSize + (((CircularProgressIndicatorSpec) this.spec).indicatorInset * 2);
    }

    private void drawRoundedEnd(Canvas canvas, Paint paint, float f, float f2, float f3) {
        canvas.save();
        canvas.rotate(f3);
        float f4 = this.adjustedRadius;
        float f5 = f / 2.0f;
        canvas.drawRoundRect(new RectF(f4 - f5, f2, f4 + f5, -f2), f2, f2, paint);
        canvas.restore();
    }
}
