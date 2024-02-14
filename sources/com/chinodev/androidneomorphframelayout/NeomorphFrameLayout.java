package com.chinodev.androidneomorphframelayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import androidx.core.content.ContextCompat;

public class NeomorphFrameLayout extends FrameLayout {
    private int BACKGROUND_COLOR;
    private int CORNER_RADIUS;
    private int ELEVATION;
    private int HIGHLIGHT_COLOR;
    private int LAYER_TYPE;
    private int SHADOW_COLOR;
    private String SHADOW_TYPE;
    private final String SHADOW_TYPE_INNER = "2";
    private final String SHADOW_TYPE_OUTER = "1";
    private boolean SHADOW_VISIBLE;
    private int SHAPE_PADDING = 0;
    private String SHAPE_TYPE;
    private final String SHAPE_TYPE_CIRCLE = "2";
    private final String SHAPE_TYPE_RECTANGLE = "1";
    private Paint basePaint;
    private Path basePath;
    private Paint paintHighLight;
    private Paint paintShadow;
    private Path pathHighlight;
    private Path pathShadow;
    private RectF rectangle;

    public NeomorphFrameLayout(Context context) {
        super(context);
        init(context, (AttributeSet) null, 0);
    }

    public NeomorphFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, 0);
    }

    public NeomorphFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i);
    }

    private void init(Context context, AttributeSet attributeSet, int i) {
        getAttrs(context, attributeSet);
        initPaints();
        int i2 = this.SHAPE_PADDING;
        this.rectangle = new RectF((float) i2, (float) i2, (float) (getWidth() - this.SHAPE_PADDING), (float) (getHeight() - this.SHAPE_PADDING));
    }

    public void getAttrs(Context context, AttributeSet attributeSet) {
        int dimension = (int) context.getResources().getDimension(R.dimen.neomorph_view_elevation);
        int dimension2 = (int) context.getResources().getDimension(R.dimen.neomorph_view_corner_radius);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.NeomorphFrameLayout);
            String string = obtainStyledAttributes.getString(R.styleable.NeomorphFrameLayout_neomorph_view_type);
            this.SHAPE_TYPE = string;
            if (string == null) {
                this.SHAPE_TYPE = "1";
            }
            String string2 = obtainStyledAttributes.getString(R.styleable.NeomorphFrameLayout_neomorph_shadow_type);
            this.SHADOW_TYPE = string2;
            if (string2 == null) {
                this.SHADOW_TYPE = "1";
            }
            this.ELEVATION = obtainStyledAttributes.getDimensionPixelSize(R.styleable.NeomorphFrameLayout_neomorph_elevation, dimension);
            this.CORNER_RADIUS = obtainStyledAttributes.getDimensionPixelSize(R.styleable.NeomorphFrameLayout_neomorph_corner_radius, dimension2);
            this.BACKGROUND_COLOR = obtainStyledAttributes.getColor(R.styleable.NeomorphFrameLayout_neomorph_background_color, ContextCompat.getColor(context, R.color.neomorph_background_color));
            this.SHADOW_COLOR = obtainStyledAttributes.getColor(R.styleable.NeomorphFrameLayout_neomorph_shadow_color, ContextCompat.getColor(context, R.color.neomorph_shadow_color));
            this.HIGHLIGHT_COLOR = obtainStyledAttributes.getColor(R.styleable.NeomorphFrameLayout_neomorph_highlight_color, ContextCompat.getColor(context, R.color.neomorph_highlight_color));
            this.SHADOW_VISIBLE = obtainStyledAttributes.getBoolean(R.styleable.NeomorphFrameLayout_neomorph_shadow_visible, true);
            String string3 = obtainStyledAttributes.getString(R.styleable.NeomorphFrameLayout_neomorph_layer_type);
            if (string3 == null || string3.equals("1")) {
                this.LAYER_TYPE = 1;
            } else {
                this.LAYER_TYPE = 2;
            }
            obtainStyledAttributes.recycle();
            return;
        }
        this.SHAPE_TYPE = "rectangle";
        this.ELEVATION = dimension;
        this.CORNER_RADIUS = dimension2;
        this.BACKGROUND_COLOR = ContextCompat.getColor(context, R.color.neomorph_background_color);
        this.SHADOW_COLOR = ContextCompat.getColor(context, R.color.neomorph_shadow_color);
        this.HIGHLIGHT_COLOR = ContextCompat.getColor(context, R.color.neomorph_highlight_color);
        this.LAYER_TYPE = 1;
        this.SHADOW_VISIBLE = true;
        this.SHADOW_TYPE = "1";
    }

    private void initPaints() {
        this.basePaint = new Paint(1);
        this.paintShadow = new Paint(1);
        this.paintHighLight = new Paint(1);
        this.basePaint.setColor(this.BACKGROUND_COLOR);
        this.paintShadow.setColor(this.BACKGROUND_COLOR);
        this.paintHighLight.setColor(this.BACKGROUND_COLOR);
        if (this.SHADOW_VISIBLE) {
            Paint paint = this.paintShadow;
            int i = this.ELEVATION;
            paint.setShadowLayer((float) i, (float) i, (float) i, this.SHADOW_COLOR);
            Paint paint2 = this.paintHighLight;
            int i2 = this.ELEVATION;
            paint2.setShadowLayer((float) i2, (float) (-i2), (float) (-i2), this.HIGHLIGHT_COLOR);
        }
        this.basePath = new Path();
        this.pathHighlight = new Path();
        this.pathShadow = new Path();
        this.SHAPE_PADDING = this.ELEVATION * 2;
        setWillNotDraw(false);
        setLayerType(this.LAYER_TYPE, (Paint) null);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        int i5 = this.SHAPE_PADDING;
        this.rectangle = new RectF((float) i5, (float) i5, (float) (getWidth() - this.SHAPE_PADDING), (float) (getHeight() - this.SHAPE_PADDING));
        resetPath(i, i2);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        int i = this.SHAPE_PADDING;
        setPadding(i, i, i, i);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x002b  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0041  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onDraw(android.graphics.Canvas r5) {
        /*
            r4 = this;
            super.onDraw(r5)
            java.lang.String r0 = r4.SHADOW_TYPE
            int r1 = r0.hashCode()
            r2 = 49
            r3 = 0
            if (r1 == r2) goto L_0x001d
            r2 = 50
            if (r1 == r2) goto L_0x0013
            goto L_0x0027
        L_0x0013:
            java.lang.String r1 = "2"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0027
            r0 = 0
            goto L_0x0028
        L_0x001d:
            java.lang.String r1 = "1"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0027
            r0 = 2
            goto L_0x0028
        L_0x0027:
            r0 = -1
        L_0x0028:
            if (r0 == 0) goto L_0x002b
            goto L_0x0030
        L_0x002b:
            android.graphics.Path r0 = r4.basePath
            r5.clipPath(r0)
        L_0x0030:
            boolean r0 = r4.SHADOW_VISIBLE
            if (r0 == 0) goto L_0x0041
            android.graphics.Paint r0 = r4.paintShadow
            r1 = 155(0x9b, float:2.17E-43)
            r0.setAlpha(r1)
            android.graphics.Paint r0 = r4.paintHighLight
            r0.setAlpha(r1)
            goto L_0x004b
        L_0x0041:
            android.graphics.Paint r0 = r4.paintShadow
            r0.setAlpha(r3)
            android.graphics.Paint r0 = r4.paintHighLight
            r0.setAlpha(r3)
        L_0x004b:
            android.graphics.Path r0 = r4.basePath
            android.graphics.Paint r1 = r4.basePaint
            r5.drawPath(r0, r1)
            android.graphics.Path r0 = r4.pathShadow
            android.graphics.Paint r1 = r4.paintShadow
            r5.drawPath(r0, r1)
            android.graphics.Path r0 = r4.pathHighlight
            android.graphics.Paint r1 = r4.paintHighLight
            r5.drawPath(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.chinodev.androidneomorphframelayout.NeomorphFrameLayout.onDraw(android.graphics.Canvas):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0097  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void resetPath(int r6, int r7) {
        /*
            r5 = this;
            android.graphics.Path r0 = r5.basePath
            r0.reset()
            android.graphics.Path r0 = r5.pathHighlight
            r0.reset()
            android.graphics.Path r0 = r5.pathShadow
            r0.reset()
            java.lang.String r0 = r5.SHAPE_TYPE
            int r1 = r0.hashCode()
            r2 = 49
            java.lang.String r3 = "2"
            r4 = 2
            if (r1 == r2) goto L_0x0029
            r2 = 50
            if (r1 == r2) goto L_0x0021
            goto L_0x0033
        L_0x0021:
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0033
            r0 = 0
            goto L_0x0034
        L_0x0029:
            java.lang.String r1 = "1"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0033
            r0 = 2
            goto L_0x0034
        L_0x0033:
            r0 = -1
        L_0x0034:
            if (r0 == 0) goto L_0x005e
            android.graphics.Path r6 = r5.basePath
            android.graphics.RectF r7 = r5.rectangle
            int r0 = r5.CORNER_RADIUS
            float r1 = (float) r0
            float r0 = (float) r0
            android.graphics.Path$Direction r2 = android.graphics.Path.Direction.CW
            r6.addRoundRect(r7, r1, r0, r2)
            android.graphics.Path r6 = r5.pathHighlight
            android.graphics.RectF r7 = r5.rectangle
            int r0 = r5.CORNER_RADIUS
            float r1 = (float) r0
            float r0 = (float) r0
            android.graphics.Path$Direction r2 = android.graphics.Path.Direction.CW
            r6.addRoundRect(r7, r1, r0, r2)
            android.graphics.Path r6 = r5.pathShadow
            android.graphics.RectF r7 = r5.rectangle
            int r0 = r5.CORNER_RADIUS
            float r1 = (float) r0
            float r0 = (float) r0
            android.graphics.Path$Direction r2 = android.graphics.Path.Direction.CW
            r6.addRoundRect(r7, r1, r0, r2)
            goto L_0x008f
        L_0x005e:
            int r0 = r5.getWidth()
            int r1 = r5.getHeight()
            if (r0 >= r1) goto L_0x006d
            int r0 = r5.getWidth()
            goto L_0x0071
        L_0x006d:
            int r0 = r5.getHeight()
        L_0x0071:
            int r0 = r0 / r4
            int r1 = r5.SHAPE_PADDING
            int r0 = r0 - r1
            android.graphics.Path r1 = r5.basePath
            int r6 = r6 / r4
            float r6 = (float) r6
            int r7 = r7 / r4
            float r7 = (float) r7
            float r0 = (float) r0
            android.graphics.Path$Direction r2 = android.graphics.Path.Direction.CW
            r1.addCircle(r6, r7, r0, r2)
            android.graphics.Path r1 = r5.pathHighlight
            android.graphics.Path$Direction r2 = android.graphics.Path.Direction.CW
            r1.addCircle(r6, r7, r0, r2)
            android.graphics.Path r1 = r5.pathShadow
            android.graphics.Path$Direction r2 = android.graphics.Path.Direction.CW
            r1.addCircle(r6, r7, r0, r2)
        L_0x008f:
            java.lang.String r6 = r5.SHADOW_TYPE
            boolean r6 = r6.equals(r3)
            if (r6 == 0) goto L_0x00b1
            android.graphics.Path r6 = r5.pathHighlight
            boolean r6 = r6.isInverseFillType()
            if (r6 != 0) goto L_0x00a4
            android.graphics.Path r6 = r5.pathHighlight
            r6.toggleInverseFillType()
        L_0x00a4:
            android.graphics.Path r6 = r5.pathShadow
            boolean r6 = r6.isInverseFillType()
            if (r6 != 0) goto L_0x00b1
            android.graphics.Path r6 = r5.pathShadow
            r6.toggleInverseFillType()
        L_0x00b1:
            android.graphics.Path r6 = r5.basePath
            r6.close()
            android.graphics.Path r6 = r5.pathHighlight
            r6.close()
            android.graphics.Path r6 = r5.pathShadow
            r6.close()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.chinodev.androidneomorphframelayout.NeomorphFrameLayout.resetPath(int, int):void");
    }

    public void setShadowInner() {
        this.SHADOW_VISIBLE = true;
        this.SHADOW_TYPE = "2";
        initPaints();
        resetPath(getWidth(), getHeight());
        invalidate();
    }

    public void setShadowOuter() {
        this.SHADOW_VISIBLE = true;
        this.SHADOW_TYPE = "1";
        initPaints();
        resetPath(getWidth(), getHeight());
        invalidate();
    }

    public void switchShadowType() {
        this.SHADOW_VISIBLE = true;
        if (this.SHADOW_TYPE.equals("2")) {
            this.SHADOW_TYPE = "1";
        } else {
            this.SHADOW_TYPE = "2";
        }
        initPaints();
        resetPath(getWidth(), getHeight());
        invalidate();
    }

    public void setShadowNone() {
        this.SHADOW_VISIBLE = false;
        initPaints();
        resetPath(getWidth(), getHeight());
        invalidate();
    }
}
