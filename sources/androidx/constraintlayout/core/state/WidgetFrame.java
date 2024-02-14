package androidx.constraintlayout.core.state;

import androidx.constraintlayout.core.motion.CustomAttribute;
import androidx.constraintlayout.core.motion.CustomVariable;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.parser.CLElement;
import androidx.constraintlayout.core.parser.CLKey;
import androidx.constraintlayout.core.parser.CLNumber;
import androidx.constraintlayout.core.parser.CLObject;
import androidx.constraintlayout.core.parser.CLParsingException;
import androidx.constraintlayout.core.state.Transition;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.core.os.EnvironmentCompat;
import java.util.HashMap;
import java.util.Set;

public class WidgetFrame {
    private static final boolean OLD_SYSTEM = true;
    public static float phone_orientation = Float.NaN;
    public float alpha = Float.NaN;
    public int bottom = 0;
    public float interpolatedPos = Float.NaN;
    public int left = 0;
    public final HashMap<String, CustomVariable> mCustom = new HashMap<>();
    public String name = null;
    public float pivotX = Float.NaN;
    public float pivotY = Float.NaN;
    public int right = 0;
    public float rotationX = Float.NaN;
    public float rotationY = Float.NaN;
    public float rotationZ = Float.NaN;
    public float scaleX = Float.NaN;
    public float scaleY = Float.NaN;
    public int top = 0;
    public float translationX = Float.NaN;
    public float translationY = Float.NaN;
    public float translationZ = Float.NaN;
    public int visibility = 0;
    public ConstraintWidget widget = null;

    public void setCustomValue(CustomAttribute customAttribute, float[] fArr) {
    }

    public int width() {
        return Math.max(0, this.right - this.left);
    }

    public int height() {
        return Math.max(0, this.bottom - this.top);
    }

    public WidgetFrame() {
    }

    public WidgetFrame(ConstraintWidget constraintWidget) {
        this.widget = constraintWidget;
    }

    public WidgetFrame(WidgetFrame widgetFrame) {
        this.widget = widgetFrame.widget;
        this.left = widgetFrame.left;
        this.top = widgetFrame.top;
        this.right = widgetFrame.right;
        this.bottom = widgetFrame.bottom;
        updateAttributes(widgetFrame);
    }

    public void updateAttributes(WidgetFrame widgetFrame) {
        this.pivotX = widgetFrame.pivotX;
        this.pivotY = widgetFrame.pivotY;
        this.rotationX = widgetFrame.rotationX;
        this.rotationY = widgetFrame.rotationY;
        this.rotationZ = widgetFrame.rotationZ;
        this.translationX = widgetFrame.translationX;
        this.translationY = widgetFrame.translationY;
        this.translationZ = widgetFrame.translationZ;
        this.scaleX = widgetFrame.scaleX;
        this.scaleY = widgetFrame.scaleY;
        this.alpha = widgetFrame.alpha;
        this.visibility = widgetFrame.visibility;
        this.mCustom.clear();
        if (widgetFrame != null) {
            for (CustomVariable next : widgetFrame.mCustom.values()) {
                this.mCustom.put(next.getName(), next.copy());
            }
        }
    }

    public boolean isDefaultTransform() {
        if (!Float.isNaN(this.rotationX) || !Float.isNaN(this.rotationY) || !Float.isNaN(this.rotationZ) || !Float.isNaN(this.translationX) || !Float.isNaN(this.translationY) || !Float.isNaN(this.translationZ) || !Float.isNaN(this.scaleX) || !Float.isNaN(this.scaleY) || !Float.isNaN(this.alpha)) {
            return false;
        }
        return OLD_SYSTEM;
    }

    public static void interpolate(int i, int i2, WidgetFrame widgetFrame, WidgetFrame widgetFrame2, WidgetFrame widgetFrame3, Transition transition, float f) {
        float f2;
        int i3;
        int i4;
        float f3;
        float f4;
        int i5;
        float f5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10 = i;
        int i11 = i2;
        WidgetFrame widgetFrame4 = widgetFrame;
        WidgetFrame widgetFrame5 = widgetFrame2;
        WidgetFrame widgetFrame6 = widgetFrame3;
        Transition transition2 = transition;
        float f6 = 100.0f * f;
        int i12 = (int) f6;
        int i13 = widgetFrame5.left;
        int i14 = widgetFrame5.top;
        int i15 = widgetFrame6.left;
        int i16 = widgetFrame6.top;
        int i17 = widgetFrame6.right - i15;
        int i18 = widgetFrame5.right - i13;
        int i19 = widgetFrame6.bottom - i16;
        int i20 = widgetFrame5.bottom - i14;
        float f7 = widgetFrame5.alpha;
        float f8 = widgetFrame6.alpha;
        float f9 = f6;
        if (widgetFrame5.visibility == 8) {
            i13 = (int) (((float) i13) - (((float) i17) / 2.0f));
            i14 = (int) (((float) i14) - (((float) i19) / 2.0f));
            if (Float.isNaN(f7)) {
                i3 = i19;
                i4 = i17;
                f2 = 0.0f;
            } else {
                f2 = f7;
                i4 = i17;
                i3 = i19;
            }
        } else {
            i4 = i18;
            f2 = f7;
            i3 = i20;
        }
        if (widgetFrame6.visibility == 8) {
            i15 = (int) (((float) i15) - (((float) i4) / 2.0f));
            i16 = (int) (((float) i16) - (((float) i3) / 2.0f));
            i17 = i4;
            i19 = i3;
            if (Float.isNaN(f8)) {
                f8 = 0.0f;
            }
        }
        if (Float.isNaN(f2) && !Float.isNaN(f8)) {
            f2 = 1.0f;
        }
        if (!Float.isNaN(f2) && Float.isNaN(f8)) {
            f8 = 1.0f;
        }
        if (widgetFrame5.visibility == 4) {
            f3 = f8;
            f4 = 0.0f;
        } else {
            f4 = f2;
            f3 = f8;
        }
        float f10 = widgetFrame6.visibility == 4 ? 0.0f : f3;
        if (widgetFrame4.widget == null || !transition.hasPositionKeyframes()) {
            i5 = i13;
            f5 = f;
        } else {
            Transition.KeyPosition findPreviousPosition = transition2.findPreviousPosition(widgetFrame4.widget.stringId, i12);
            i5 = i13;
            Transition.KeyPosition findNextPosition = transition2.findNextPosition(widgetFrame4.widget.stringId, i12);
            if (findPreviousPosition == findNextPosition) {
                findNextPosition = null;
            }
            if (findPreviousPosition != null) {
                i5 = (int) (findPreviousPosition.x * ((float) i));
                i7 = i2;
                i14 = (int) (findPreviousPosition.y * ((float) i7));
                i6 = findPreviousPosition.frame;
                i8 = i;
            } else {
                i8 = i;
                i7 = i2;
                i6 = 0;
            }
            if (findNextPosition != null) {
                i15 = (int) (findNextPosition.x * ((float) i8));
                i16 = (int) (findNextPosition.y * ((float) i7));
                i9 = findNextPosition.frame;
            } else {
                i9 = 100;
            }
            f5 = (f9 - ((float) i6)) / ((float) (i9 - i6));
        }
        int i21 = i5;
        widgetFrame4.widget = widgetFrame5.widget;
        int i22 = (int) (((float) i21) + (((float) (i15 - i21)) * f5));
        widgetFrame4.left = i22;
        int i23 = (int) (((float) i14) + (f5 * ((float) (i16 - i14))));
        widgetFrame4.top = i23;
        float f11 = f;
        float f12 = 1.0f - f11;
        widgetFrame4.right = i22 + ((int) ((((float) i4) * f12) + (((float) i17) * f11)));
        widgetFrame4.bottom = i23 + ((int) ((f12 * ((float) i3)) + (((float) i19) * f11)));
        widgetFrame4.pivotX = interpolate(widgetFrame5.pivotX, widgetFrame6.pivotX, 0.5f, f11);
        widgetFrame4.pivotY = interpolate(widgetFrame5.pivotY, widgetFrame6.pivotY, 0.5f, f11);
        widgetFrame4.rotationX = interpolate(widgetFrame5.rotationX, widgetFrame6.rotationX, 0.0f, f11);
        widgetFrame4.rotationY = interpolate(widgetFrame5.rotationY, widgetFrame6.rotationY, 0.0f, f11);
        widgetFrame4.rotationZ = interpolate(widgetFrame5.rotationZ, widgetFrame6.rotationZ, 0.0f, f11);
        widgetFrame4.scaleX = interpolate(widgetFrame5.scaleX, widgetFrame6.scaleX, 1.0f, f11);
        widgetFrame4.scaleY = interpolate(widgetFrame5.scaleY, widgetFrame6.scaleY, 1.0f, f11);
        widgetFrame4.translationX = interpolate(widgetFrame5.translationX, widgetFrame6.translationX, 0.0f, f11);
        widgetFrame4.translationY = interpolate(widgetFrame5.translationY, widgetFrame6.translationY, 0.0f, f11);
        widgetFrame4.translationZ = interpolate(widgetFrame5.translationZ, widgetFrame6.translationZ, 0.0f, f11);
        widgetFrame4.alpha = interpolate(f4, f10, 1.0f, f11);
        Set<String> keySet = widgetFrame6.mCustom.keySet();
        widgetFrame4.mCustom.clear();
        for (String next : keySet) {
            if (widgetFrame5.mCustom.containsKey(next)) {
                CustomVariable customVariable = widgetFrame5.mCustom.get(next);
                CustomVariable customVariable2 = widgetFrame6.mCustom.get(next);
                CustomVariable customVariable3 = new CustomVariable(customVariable);
                widgetFrame4.mCustom.put(next, customVariable3);
                if (customVariable.numberOfInterpolatedValues() == 1) {
                    customVariable3.setValue((Object) Float.valueOf(interpolate(customVariable.getValueToInterpolate(), customVariable2.getValueToInterpolate(), 0.0f, f11)));
                } else {
                    int numberOfInterpolatedValues = customVariable.numberOfInterpolatedValues();
                    float[] fArr = new float[numberOfInterpolatedValues];
                    float[] fArr2 = new float[numberOfInterpolatedValues];
                    customVariable.getValuesToInterpolate(fArr);
                    customVariable2.getValuesToInterpolate(fArr2);
                    for (int i24 = 0; i24 < numberOfInterpolatedValues; i24++) {
                        fArr[i24] = interpolate(fArr[i24], fArr2[i24], 0.0f, f11);
                        customVariable3.setValue(fArr);
                    }
                }
            }
        }
    }

    private static float interpolate(float f, float f2, float f3, float f4) {
        boolean isNaN = Float.isNaN(f);
        boolean isNaN2 = Float.isNaN(f2);
        if (isNaN && isNaN2) {
            return Float.NaN;
        }
        if (isNaN) {
            f = f3;
        }
        if (isNaN2) {
            f2 = f3;
        }
        return f + (f4 * (f2 - f));
    }

    public float centerX() {
        int i = this.left;
        return ((float) i) + (((float) (this.right - i)) / 2.0f);
    }

    public float centerY() {
        int i = this.top;
        return ((float) i) + (((float) (this.bottom - i)) / 2.0f);
    }

    public WidgetFrame update() {
        ConstraintWidget constraintWidget = this.widget;
        if (constraintWidget != null) {
            this.left = constraintWidget.getLeft();
            this.top = this.widget.getTop();
            this.right = this.widget.getRight();
            this.bottom = this.widget.getBottom();
            updateAttributes(this.widget.frame);
        }
        return this;
    }

    public WidgetFrame update(ConstraintWidget constraintWidget) {
        if (constraintWidget == null) {
            return this;
        }
        this.widget = constraintWidget;
        update();
        return this;
    }

    public void addCustomColor(String str, int i) {
        setCustomAttribute(str, (int) TypedValues.Custom.TYPE_COLOR, i);
    }

    public int getCustomColor(String str) {
        if (this.mCustom.containsKey(str)) {
            return this.mCustom.get(str).getColorValue();
        }
        return -21880;
    }

    public void addCustomFloat(String str, float f) {
        setCustomAttribute(str, (int) TypedValues.Custom.TYPE_FLOAT, f);
    }

    public float getCustomFloat(String str) {
        if (this.mCustom.containsKey(str)) {
            return this.mCustom.get(str).getFloatValue();
        }
        return Float.NaN;
    }

    public void setCustomAttribute(String str, int i, float f) {
        if (this.mCustom.containsKey(str)) {
            this.mCustom.get(str).setFloatValue(f);
        } else {
            this.mCustom.put(str, new CustomVariable(str, i, f));
        }
    }

    public void setCustomAttribute(String str, int i, int i2) {
        if (this.mCustom.containsKey(str)) {
            this.mCustom.get(str).setIntValue(i2);
        } else {
            this.mCustom.put(str, new CustomVariable(str, i, i2));
        }
    }

    public void setCustomAttribute(String str, int i, boolean z) {
        if (this.mCustom.containsKey(str)) {
            this.mCustom.get(str).setBooleanValue(z);
        } else {
            this.mCustom.put(str, new CustomVariable(str, i, z));
        }
    }

    public void setCustomAttribute(String str, int i, String str2) {
        if (this.mCustom.containsKey(str)) {
            this.mCustom.get(str).setStringValue(str2);
        } else {
            this.mCustom.put(str, new CustomVariable(str, i, str2));
        }
    }

    public CustomVariable getCustomAttribute(String str) {
        return this.mCustom.get(str);
    }

    public Set<String> getCustomAttributeNames() {
        return this.mCustom.keySet();
    }

    public boolean setValue(String str, CLElement cLElement) throws CLParsingException {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1881940865:
                if (str.equals("phone_orientation")) {
                    c = 0;
                    break;
                }
                break;
            case -1383228885:
                if (str.equals("bottom")) {
                    c = 1;
                    break;
                }
                break;
            case -1349088399:
                if (str.equals("custom")) {
                    c = 2;
                    break;
                }
                break;
            case -1249320806:
                if (str.equals("rotationX")) {
                    c = 3;
                    break;
                }
                break;
            case -1249320805:
                if (str.equals("rotationY")) {
                    c = 4;
                    break;
                }
                break;
            case -1249320804:
                if (str.equals("rotationZ")) {
                    c = 5;
                    break;
                }
                break;
            case -1225497657:
                if (str.equals("translationX")) {
                    c = 6;
                    break;
                }
                break;
            case -1225497656:
                if (str.equals("translationY")) {
                    c = 7;
                    break;
                }
                break;
            case -1225497655:
                if (str.equals("translationZ")) {
                    c = 8;
                    break;
                }
                break;
            case -987906986:
                if (str.equals("pivotX")) {
                    c = 9;
                    break;
                }
                break;
            case -987906985:
                if (str.equals("pivotY")) {
                    c = 10;
                    break;
                }
                break;
            case -908189618:
                if (str.equals("scaleX")) {
                    c = 11;
                    break;
                }
                break;
            case -908189617:
                if (str.equals("scaleY")) {
                    c = 12;
                    break;
                }
                break;
            case 115029:
                if (str.equals("top")) {
                    c = 13;
                    break;
                }
                break;
            case 3317767:
                if (str.equals("left")) {
                    c = 14;
                    break;
                }
                break;
            case 92909918:
                if (str.equals("alpha")) {
                    c = 15;
                    break;
                }
                break;
            case 108511772:
                if (str.equals("right")) {
                    c = 16;
                    break;
                }
                break;
            case 642850769:
                if (str.equals("interpolatedPos")) {
                    c = 17;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                phone_orientation = cLElement.getFloat();
                break;
            case 1:
                this.bottom = cLElement.getInt();
                break;
            case 2:
                parseCustom(cLElement);
                break;
            case 3:
                this.rotationX = cLElement.getFloat();
                break;
            case 4:
                this.rotationY = cLElement.getFloat();
                break;
            case 5:
                this.rotationZ = cLElement.getFloat();
                break;
            case 6:
                this.translationX = cLElement.getFloat();
                break;
            case 7:
                this.translationY = cLElement.getFloat();
                break;
            case 8:
                this.translationZ = cLElement.getFloat();
                break;
            case 9:
                this.pivotX = cLElement.getFloat();
                break;
            case 10:
                this.pivotY = cLElement.getFloat();
                break;
            case 11:
                this.scaleX = cLElement.getFloat();
                break;
            case 12:
                this.scaleY = cLElement.getFloat();
                break;
            case 13:
                this.top = cLElement.getInt();
                break;
            case 14:
                this.left = cLElement.getInt();
                break;
            case 15:
                this.alpha = cLElement.getFloat();
                break;
            case 16:
                this.right = cLElement.getInt();
                break;
            case 17:
                this.interpolatedPos = cLElement.getFloat();
                break;
            default:
                return false;
        }
        return OLD_SYSTEM;
    }

    public String getId() {
        ConstraintWidget constraintWidget = this.widget;
        if (constraintWidget == null) {
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        return constraintWidget.stringId;
    }

    /* access modifiers changed from: package-private */
    public void parseCustom(CLElement cLElement) throws CLParsingException {
        CLObject cLObject = (CLObject) cLElement;
        int size = cLObject.size();
        for (int i = 0; i < size; i++) {
            CLKey cLKey = (CLKey) cLObject.get(i);
            cLKey.content();
            CLElement value = cLKey.getValue();
            String content = value.content();
            if (content.matches("#[0-9a-fA-F]+")) {
                setCustomAttribute(cLKey.content(), (int) TypedValues.Custom.TYPE_COLOR, Integer.parseInt(content.substring(1), 16));
            } else if (value instanceof CLNumber) {
                setCustomAttribute(cLKey.content(), (int) TypedValues.Custom.TYPE_FLOAT, value.getFloat());
            } else {
                setCustomAttribute(cLKey.content(), (int) TypedValues.Custom.TYPE_STRING, content);
            }
        }
    }

    public StringBuilder serialize(StringBuilder sb) {
        return serialize(sb, false);
    }

    public StringBuilder serialize(StringBuilder sb, boolean z) {
        sb.append("{\n");
        add(sb, "left", this.left);
        add(sb, "top", this.top);
        add(sb, "right", this.right);
        add(sb, "bottom", this.bottom);
        add(sb, "pivotX", this.pivotX);
        add(sb, "pivotY", this.pivotY);
        add(sb, "rotationX", this.rotationX);
        add(sb, "rotationY", this.rotationY);
        add(sb, "rotationZ", this.rotationZ);
        add(sb, "translationX", this.translationX);
        add(sb, "translationY", this.translationY);
        add(sb, "translationZ", this.translationZ);
        add(sb, "scaleX", this.scaleX);
        add(sb, "scaleY", this.scaleY);
        add(sb, "alpha", this.alpha);
        add(sb, "visibility", this.visibility);
        add(sb, "interpolatedPos", this.interpolatedPos);
        if (this.widget != null) {
            for (ConstraintAnchor.Type serializeAnchor : ConstraintAnchor.Type.values()) {
                serializeAnchor(sb, serializeAnchor);
            }
        }
        if (z) {
            add(sb, "phone_orientation", phone_orientation);
        }
        if (z) {
            add(sb, "phone_orientation", phone_orientation);
        }
        if (this.mCustom.size() != 0) {
            sb.append("custom : {\n");
            for (String next : this.mCustom.keySet()) {
                CustomVariable customVariable = this.mCustom.get(next);
                sb.append(next);
                sb.append(": ");
                switch (customVariable.getType()) {
                    case TypedValues.Custom.TYPE_INT:
                        sb.append(customVariable.getIntegerValue());
                        sb.append(",\n");
                        break;
                    case TypedValues.Custom.TYPE_FLOAT:
                    case TypedValues.Custom.TYPE_DIMENSION:
                        sb.append(customVariable.getFloatValue());
                        sb.append(",\n");
                        break;
                    case TypedValues.Custom.TYPE_COLOR:
                        sb.append("'");
                        sb.append(CustomVariable.colorString(customVariable.getIntegerValue()));
                        sb.append("',\n");
                        break;
                    case TypedValues.Custom.TYPE_STRING:
                        sb.append("'");
                        sb.append(customVariable.getStringValue());
                        sb.append("',\n");
                        break;
                    case TypedValues.Custom.TYPE_BOOLEAN:
                        sb.append("'");
                        sb.append(customVariable.getBooleanValue());
                        sb.append("',\n");
                        break;
                }
            }
            sb.append("}\n");
        }
        sb.append("}\n");
        return sb;
    }

    private void serializeAnchor(StringBuilder sb, ConstraintAnchor.Type type) {
        ConstraintAnchor anchor = this.widget.getAnchor(type);
        if (anchor != null && anchor.mTarget != null) {
            sb.append("Anchor");
            sb.append(type.name());
            sb.append(": ['");
            String str = anchor.mTarget.getOwner().stringId;
            if (str == null) {
                str = "#PARENT";
            }
            sb.append(str);
            sb.append("', '");
            sb.append(anchor.mTarget.getType().name());
            sb.append("', '");
            sb.append(anchor.mMargin);
            sb.append("'],\n");
        }
    }

    private static void add(StringBuilder sb, String str, int i) {
        sb.append(str);
        sb.append(": ");
        sb.append(i);
        sb.append(",\n");
    }

    private static void add(StringBuilder sb, String str, float f) {
        if (!Float.isNaN(f)) {
            sb.append(str);
            sb.append(": ");
            sb.append(f);
            sb.append(",\n");
        }
    }

    /* access modifiers changed from: package-private */
    public void printCustomAttributes() {
        String str;
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        String str2 = (".(" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + ") " + stackTraceElement.getMethodName()) + " " + (hashCode() % 1000);
        if (this.widget != null) {
            str = str2 + "/" + (this.widget.hashCode() % 1000) + " ";
        } else {
            str = str2 + "/NULL ";
        }
        HashMap<String, CustomVariable> hashMap = this.mCustom;
        if (hashMap != null) {
            for (String str3 : hashMap.keySet()) {
                System.out.println(str + this.mCustom.get(str3).toString());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void logv(String str) {
        String str2;
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        String str3 = (".(" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + ") " + stackTraceElement.getMethodName()) + " " + (hashCode() % 1000);
        if (this.widget != null) {
            str2 = str3 + "/" + (this.widget.hashCode() % 1000);
        } else {
            str2 = str3 + "/NULL";
        }
        System.out.println(str2 + " " + str);
    }
}
