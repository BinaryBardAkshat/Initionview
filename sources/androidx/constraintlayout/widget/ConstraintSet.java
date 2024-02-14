package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.HelperWidget;
import androidx.constraintlayout.motion.widget.Debug;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.motion.widget.MotionScene;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;
import androidx.constraintlayout.widget.R;
import androidx.core.os.EnvironmentCompat;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ConstraintSet {
    private static final int ALPHA = 43;
    private static final int ANIMATE_CIRCLE_ANGLE_TO = 82;
    private static final int ANIMATE_RELATIVE_TO = 64;
    private static final int BARRIER_ALLOWS_GONE_WIDGETS = 75;
    private static final int BARRIER_DIRECTION = 72;
    private static final int BARRIER_MARGIN = 73;
    private static final int BARRIER_TYPE = 1;
    public static final int BASELINE = 5;
    private static final int BASELINE_MARGIN = 93;
    private static final int BASELINE_TO_BASELINE = 1;
    private static final int BASELINE_TO_BOTTOM = 92;
    private static final int BASELINE_TO_TOP = 91;
    public static final int BOTTOM = 4;
    private static final int BOTTOM_MARGIN = 2;
    private static final int BOTTOM_TO_BOTTOM = 3;
    private static final int BOTTOM_TO_TOP = 4;
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    private static final int CHAIN_USE_RTL = 71;
    private static final int CIRCLE = 61;
    private static final int CIRCLE_ANGLE = 63;
    private static final int CIRCLE_RADIUS = 62;
    public static final int CIRCLE_REFERENCE = 8;
    private static final int CONSTRAINED_HEIGHT = 81;
    private static final int CONSTRAINED_WIDTH = 80;
    private static final int CONSTRAINT_REFERENCED_IDS = 74;
    private static final int CONSTRAINT_TAG = 77;
    private static final boolean DEBUG = false;
    private static final int DIMENSION_RATIO = 5;
    private static final int DRAW_PATH = 66;
    private static final int EDITOR_ABSOLUTE_X = 6;
    private static final int EDITOR_ABSOLUTE_Y = 7;
    private static final int ELEVATION = 44;
    public static final int END = 7;
    private static final int END_MARGIN = 8;
    private static final int END_TO_END = 9;
    private static final int END_TO_START = 10;
    private static final String ERROR_MESSAGE = "XML parser error must be within a Constraint ";
    public static final int GONE = 8;
    private static final int GONE_BASELINE_MARGIN = 94;
    private static final int GONE_BOTTOM_MARGIN = 11;
    private static final int GONE_END_MARGIN = 12;
    private static final int GONE_LEFT_MARGIN = 13;
    private static final int GONE_RIGHT_MARGIN = 14;
    private static final int GONE_START_MARGIN = 15;
    private static final int GONE_TOP_MARGIN = 16;
    private static final int GUIDELINE_USE_RTL = 99;
    private static final int GUIDE_BEGIN = 17;
    private static final int GUIDE_END = 18;
    private static final int GUIDE_PERCENT = 19;
    private static final int HEIGHT_DEFAULT = 55;
    private static final int HEIGHT_MAX = 57;
    private static final int HEIGHT_MIN = 59;
    private static final int HEIGHT_PERCENT = 70;
    public static final int HORIZONTAL = 0;
    private static final int HORIZONTAL_BIAS = 20;
    public static final int HORIZONTAL_GUIDELINE = 0;
    private static final int HORIZONTAL_STYLE = 41;
    private static final int HORIZONTAL_WEIGHT = 39;
    private static final int INTERNAL_MATCH_CONSTRAINT = -3;
    private static final int INTERNAL_MATCH_PARENT = -1;
    private static final int INTERNAL_WRAP_CONTENT = -2;
    private static final int INTERNAL_WRAP_CONTENT_CONSTRAINED = -4;
    public static final int INVISIBLE = 4;
    private static final String KEY_PERCENT_PARENT = "parent";
    private static final String KEY_RATIO = "ratio";
    private static final String KEY_WEIGHT = "weight";
    private static final int LAYOUT_CONSTRAINT_HEIGHT = 96;
    private static final int LAYOUT_CONSTRAINT_WIDTH = 95;
    private static final int LAYOUT_HEIGHT = 21;
    private static final int LAYOUT_VISIBILITY = 22;
    private static final int LAYOUT_WIDTH = 23;
    private static final int LAYOUT_WRAP_BEHAVIOR = 97;
    public static final int LEFT = 1;
    private static final int LEFT_MARGIN = 24;
    private static final int LEFT_TO_LEFT = 25;
    private static final int LEFT_TO_RIGHT = 26;
    public static final int MATCH_CONSTRAINT = 0;
    public static final int MATCH_CONSTRAINT_PERCENT = 2;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    private static final int MOTION_STAGGER = 79;
    private static final int MOTION_TARGET = 98;
    private static final int ORIENTATION = 27;
    public static final int PARENT_ID = 0;
    private static final int PATH_MOTION_ARC = 76;
    private static final int PROGRESS = 68;
    private static final int QUANTIZE_MOTION_INTERPOLATOR = 86;
    private static final int QUANTIZE_MOTION_INTERPOLATOR_ID = 89;
    private static final int QUANTIZE_MOTION_INTERPOLATOR_STR = 90;
    private static final int QUANTIZE_MOTION_INTERPOLATOR_TYPE = 88;
    private static final int QUANTIZE_MOTION_PHASE = 85;
    private static final int QUANTIZE_MOTION_STEPS = 84;
    public static final int RIGHT = 2;
    private static final int RIGHT_MARGIN = 28;
    private static final int RIGHT_TO_LEFT = 29;
    private static final int RIGHT_TO_RIGHT = 30;
    public static final int ROTATE_LEFT_OF_PORTRATE = 4;
    public static final int ROTATE_NONE = 0;
    public static final int ROTATE_PORTRATE_OF_LEFT = 2;
    public static final int ROTATE_PORTRATE_OF_RIGHT = 1;
    public static final int ROTATE_RIGHT_OF_PORTRATE = 3;
    private static final int ROTATION = 60;
    private static final int ROTATION_X = 45;
    private static final int ROTATION_Y = 46;
    private static final int SCALE_X = 47;
    private static final int SCALE_Y = 48;
    public static final int START = 6;
    private static final int START_MARGIN = 31;
    private static final int START_TO_END = 32;
    private static final int START_TO_START = 33;
    private static final String TAG = "ConstraintSet";
    public static final int TOP = 3;
    private static final int TOP_MARGIN = 34;
    private static final int TOP_TO_BOTTOM = 35;
    private static final int TOP_TO_TOP = 36;
    private static final int TRANSFORM_PIVOT_TARGET = 83;
    private static final int TRANSFORM_PIVOT_X = 49;
    private static final int TRANSFORM_PIVOT_Y = 50;
    private static final int TRANSITION_EASING = 65;
    private static final int TRANSITION_PATH_ROTATE = 67;
    private static final int TRANSLATION_X = 51;
    private static final int TRANSLATION_Y = 52;
    private static final int TRANSLATION_Z = 53;
    public static final int UNSET = -1;
    private static final int UNUSED = 87;
    public static final int VERTICAL = 1;
    private static final int VERTICAL_BIAS = 37;
    public static final int VERTICAL_GUIDELINE = 1;
    private static final int VERTICAL_STYLE = 42;
    private static final int VERTICAL_WEIGHT = 40;
    private static final int VIEW_ID = 38;
    /* access modifiers changed from: private */
    public static final int[] VISIBILITY_FLAGS = {0, 4, 8};
    private static final int VISIBILITY_MODE = 78;
    public static final int VISIBILITY_MODE_IGNORE = 1;
    public static final int VISIBILITY_MODE_NORMAL = 0;
    public static final int VISIBLE = 0;
    private static final int WIDTH_DEFAULT = 54;
    private static final int WIDTH_MAX = 56;
    private static final int WIDTH_MIN = 58;
    private static final int WIDTH_PERCENT = 69;
    public static final int WRAP_CONTENT = -2;
    private static SparseIntArray mapToConstant = new SparseIntArray();
    private static SparseIntArray overrideMapToConstant = new SparseIntArray();
    public String derivedState = "";
    /* access modifiers changed from: private */
    public HashMap<Integer, Constraint> mConstraints = new HashMap<>();
    private boolean mForceId = true;
    public String mIdString;
    public int mRotate = 0;
    private HashMap<String, ConstraintAttribute> mSavedAttributes = new HashMap<>();
    private boolean mValidate;

    private String sideToString(int i) {
        switch (i) {
            case 1:
                return "left";
            case 2:
                return "right";
            case 3:
                return "top";
            case 4:
                return "bottom";
            case 5:
                return "baseline";
            case 6:
                return "start";
            case 7:
                return "end";
            default:
                return "undefined";
        }
    }

    static {
        mapToConstant.append(R.styleable.Constraint_layout_constraintLeft_toLeftOf, 25);
        mapToConstant.append(R.styleable.Constraint_layout_constraintLeft_toRightOf, 26);
        mapToConstant.append(R.styleable.Constraint_layout_constraintRight_toLeftOf, 29);
        mapToConstant.append(R.styleable.Constraint_layout_constraintRight_toRightOf, 30);
        mapToConstant.append(R.styleable.Constraint_layout_constraintTop_toTopOf, 36);
        mapToConstant.append(R.styleable.Constraint_layout_constraintTop_toBottomOf, 35);
        mapToConstant.append(R.styleable.Constraint_layout_constraintBottom_toTopOf, 4);
        mapToConstant.append(R.styleable.Constraint_layout_constraintBottom_toBottomOf, 3);
        mapToConstant.append(R.styleable.Constraint_layout_constraintBaseline_toBaselineOf, 1);
        mapToConstant.append(R.styleable.Constraint_layout_constraintBaseline_toTopOf, 91);
        mapToConstant.append(R.styleable.Constraint_layout_constraintBaseline_toBottomOf, 92);
        mapToConstant.append(R.styleable.Constraint_layout_editor_absoluteX, 6);
        mapToConstant.append(R.styleable.Constraint_layout_editor_absoluteY, 7);
        mapToConstant.append(R.styleable.Constraint_layout_constraintGuide_begin, 17);
        mapToConstant.append(R.styleable.Constraint_layout_constraintGuide_end, 18);
        mapToConstant.append(R.styleable.Constraint_layout_constraintGuide_percent, 19);
        mapToConstant.append(R.styleable.Constraint_guidelineUseRtl, 99);
        mapToConstant.append(R.styleable.Constraint_android_orientation, 27);
        mapToConstant.append(R.styleable.Constraint_layout_constraintStart_toEndOf, 32);
        mapToConstant.append(R.styleable.Constraint_layout_constraintStart_toStartOf, 33);
        mapToConstant.append(R.styleable.Constraint_layout_constraintEnd_toStartOf, 10);
        mapToConstant.append(R.styleable.Constraint_layout_constraintEnd_toEndOf, 9);
        mapToConstant.append(R.styleable.Constraint_layout_goneMarginLeft, 13);
        mapToConstant.append(R.styleable.Constraint_layout_goneMarginTop, 16);
        mapToConstant.append(R.styleable.Constraint_layout_goneMarginRight, 14);
        mapToConstant.append(R.styleable.Constraint_layout_goneMarginBottom, 11);
        mapToConstant.append(R.styleable.Constraint_layout_goneMarginStart, 15);
        mapToConstant.append(R.styleable.Constraint_layout_goneMarginEnd, 12);
        mapToConstant.append(R.styleable.Constraint_layout_constraintVertical_weight, 40);
        mapToConstant.append(R.styleable.Constraint_layout_constraintHorizontal_weight, 39);
        mapToConstant.append(R.styleable.Constraint_layout_constraintHorizontal_chainStyle, 41);
        mapToConstant.append(R.styleable.Constraint_layout_constraintVertical_chainStyle, 42);
        mapToConstant.append(R.styleable.Constraint_layout_constraintHorizontal_bias, 20);
        mapToConstant.append(R.styleable.Constraint_layout_constraintVertical_bias, 37);
        mapToConstant.append(R.styleable.Constraint_layout_constraintDimensionRatio, 5);
        mapToConstant.append(R.styleable.Constraint_layout_constraintLeft_creator, 87);
        mapToConstant.append(R.styleable.Constraint_layout_constraintTop_creator, 87);
        mapToConstant.append(R.styleable.Constraint_layout_constraintRight_creator, 87);
        mapToConstant.append(R.styleable.Constraint_layout_constraintBottom_creator, 87);
        mapToConstant.append(R.styleable.Constraint_layout_constraintBaseline_creator, 87);
        mapToConstant.append(R.styleable.Constraint_android_layout_marginLeft, 24);
        mapToConstant.append(R.styleable.Constraint_android_layout_marginRight, 28);
        mapToConstant.append(R.styleable.Constraint_android_layout_marginStart, 31);
        mapToConstant.append(R.styleable.Constraint_android_layout_marginEnd, 8);
        mapToConstant.append(R.styleable.Constraint_android_layout_marginTop, 34);
        mapToConstant.append(R.styleable.Constraint_android_layout_marginBottom, 2);
        mapToConstant.append(R.styleable.Constraint_android_layout_width, 23);
        mapToConstant.append(R.styleable.Constraint_android_layout_height, 21);
        mapToConstant.append(R.styleable.Constraint_layout_constraintWidth, 95);
        mapToConstant.append(R.styleable.Constraint_layout_constraintHeight, 96);
        mapToConstant.append(R.styleable.Constraint_android_visibility, 22);
        mapToConstant.append(R.styleable.Constraint_android_alpha, 43);
        mapToConstant.append(R.styleable.Constraint_android_elevation, 44);
        mapToConstant.append(R.styleable.Constraint_android_rotationX, 45);
        mapToConstant.append(R.styleable.Constraint_android_rotationY, 46);
        mapToConstant.append(R.styleable.Constraint_android_rotation, 60);
        mapToConstant.append(R.styleable.Constraint_android_scaleX, 47);
        mapToConstant.append(R.styleable.Constraint_android_scaleY, 48);
        mapToConstant.append(R.styleable.Constraint_android_transformPivotX, 49);
        mapToConstant.append(R.styleable.Constraint_android_transformPivotY, 50);
        mapToConstant.append(R.styleable.Constraint_android_translationX, 51);
        mapToConstant.append(R.styleable.Constraint_android_translationY, 52);
        mapToConstant.append(R.styleable.Constraint_android_translationZ, 53);
        mapToConstant.append(R.styleable.Constraint_layout_constraintWidth_default, 54);
        mapToConstant.append(R.styleable.Constraint_layout_constraintHeight_default, 55);
        mapToConstant.append(R.styleable.Constraint_layout_constraintWidth_max, 56);
        mapToConstant.append(R.styleable.Constraint_layout_constraintHeight_max, 57);
        mapToConstant.append(R.styleable.Constraint_layout_constraintWidth_min, 58);
        mapToConstant.append(R.styleable.Constraint_layout_constraintHeight_min, 59);
        mapToConstant.append(R.styleable.Constraint_layout_constraintCircle, 61);
        mapToConstant.append(R.styleable.Constraint_layout_constraintCircleRadius, 62);
        mapToConstant.append(R.styleable.Constraint_layout_constraintCircleAngle, 63);
        mapToConstant.append(R.styleable.Constraint_animateRelativeTo, 64);
        mapToConstant.append(R.styleable.Constraint_transitionEasing, 65);
        mapToConstant.append(R.styleable.Constraint_drawPath, 66);
        mapToConstant.append(R.styleable.Constraint_transitionPathRotate, 67);
        mapToConstant.append(R.styleable.Constraint_motionStagger, 79);
        mapToConstant.append(R.styleable.Constraint_android_id, 38);
        mapToConstant.append(R.styleable.Constraint_motionProgress, 68);
        mapToConstant.append(R.styleable.Constraint_layout_constraintWidth_percent, 69);
        mapToConstant.append(R.styleable.Constraint_layout_constraintHeight_percent, 70);
        mapToConstant.append(R.styleable.Constraint_layout_wrapBehaviorInParent, 97);
        mapToConstant.append(R.styleable.Constraint_chainUseRtl, 71);
        mapToConstant.append(R.styleable.Constraint_barrierDirection, 72);
        mapToConstant.append(R.styleable.Constraint_barrierMargin, 73);
        mapToConstant.append(R.styleable.Constraint_constraint_referenced_ids, 74);
        mapToConstant.append(R.styleable.Constraint_barrierAllowsGoneWidgets, 75);
        mapToConstant.append(R.styleable.Constraint_pathMotionArc, 76);
        mapToConstant.append(R.styleable.Constraint_layout_constraintTag, 77);
        mapToConstant.append(R.styleable.Constraint_visibilityMode, 78);
        mapToConstant.append(R.styleable.Constraint_layout_constrainedWidth, 80);
        mapToConstant.append(R.styleable.Constraint_layout_constrainedHeight, 81);
        mapToConstant.append(R.styleable.Constraint_polarRelativeTo, 82);
        mapToConstant.append(R.styleable.Constraint_transformPivotTarget, 83);
        mapToConstant.append(R.styleable.Constraint_quantizeMotionSteps, 84);
        mapToConstant.append(R.styleable.Constraint_quantizeMotionPhase, 85);
        mapToConstant.append(R.styleable.Constraint_quantizeMotionInterpolator, 86);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_editor_absoluteY, 6);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_editor_absoluteY, 7);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_orientation, 27);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_goneMarginLeft, 13);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_goneMarginTop, 16);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_goneMarginRight, 14);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_goneMarginBottom, 11);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_goneMarginStart, 15);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_goneMarginEnd, 12);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintVertical_weight, 40);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintHorizontal_weight, 39);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintHorizontal_chainStyle, 41);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintVertical_chainStyle, 42);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintHorizontal_bias, 20);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintVertical_bias, 37);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintDimensionRatio, 5);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintLeft_creator, 87);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintTop_creator, 87);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintRight_creator, 87);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintBottom_creator, 87);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintBaseline_creator, 87);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_layout_marginLeft, 24);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_layout_marginRight, 28);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_layout_marginStart, 31);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_layout_marginEnd, 8);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_layout_marginTop, 34);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_layout_marginBottom, 2);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_layout_width, 23);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_layout_height, 21);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintWidth, 95);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintHeight, 96);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_visibility, 22);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_alpha, 43);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_elevation, 44);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_rotationX, 45);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_rotationY, 46);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_rotation, 60);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_scaleX, 47);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_scaleY, 48);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_transformPivotX, 49);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_transformPivotY, 50);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_translationX, 51);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_translationY, 52);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_translationZ, 53);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintWidth_default, 54);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintHeight_default, 55);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintWidth_max, 56);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintHeight_max, 57);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintWidth_min, 58);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintHeight_min, 59);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintCircleRadius, 62);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintCircleAngle, 63);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_animateRelativeTo, 64);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_transitionEasing, 65);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_drawPath, 66);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_transitionPathRotate, 67);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_motionStagger, 79);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_android_id, 38);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_motionTarget, 98);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_motionProgress, 68);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintWidth_percent, 69);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintHeight_percent, 70);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_chainUseRtl, 71);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_barrierDirection, 72);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_barrierMargin, 73);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_constraint_referenced_ids, 74);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_barrierAllowsGoneWidgets, 75);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_pathMotionArc, 76);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constraintTag, 77);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_visibilityMode, 78);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constrainedWidth, 80);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_constrainedHeight, 81);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_polarRelativeTo, 82);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_transformPivotTarget, 83);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_quantizeMotionSteps, 84);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_quantizeMotionPhase, 85);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_quantizeMotionInterpolator, 86);
        overrideMapToConstant.append(R.styleable.ConstraintOverride_layout_wrapBehaviorInParent, 97);
    }

    public HashMap<String, ConstraintAttribute> getCustomAttributeSet() {
        return this.mSavedAttributes;
    }

    public Constraint getParameters(int i) {
        return get(i);
    }

    public void readFallback(ConstraintSet constraintSet) {
        for (Integer next : constraintSet.mConstraints.keySet()) {
            int intValue = next.intValue();
            Constraint constraint = constraintSet.mConstraints.get(next);
            if (!this.mConstraints.containsKey(Integer.valueOf(intValue))) {
                this.mConstraints.put(Integer.valueOf(intValue), new Constraint());
            }
            Constraint constraint2 = this.mConstraints.get(Integer.valueOf(intValue));
            if (constraint2 != null) {
                if (!constraint2.layout.mApply) {
                    constraint2.layout.copyFrom(constraint.layout);
                }
                if (!constraint2.propertySet.mApply) {
                    constraint2.propertySet.copyFrom(constraint.propertySet);
                }
                if (!constraint2.transform.mApply) {
                    constraint2.transform.copyFrom(constraint.transform);
                }
                if (!constraint2.motion.mApply) {
                    constraint2.motion.copyFrom(constraint.motion);
                }
                for (String next2 : constraint.mCustomConstraints.keySet()) {
                    if (!constraint2.mCustomConstraints.containsKey(next2)) {
                        constraint2.mCustomConstraints.put(next2, constraint.mCustomConstraints.get(next2));
                    }
                }
            }
        }
    }

    public void readFallback(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        int i = 0;
        while (i < childCount) {
            View childAt = constraintLayout.getChildAt(i);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) childAt.getLayoutParams();
            int id = childAt.getId();
            if (!this.mForceId || id != -1) {
                if (!this.mConstraints.containsKey(Integer.valueOf(id))) {
                    this.mConstraints.put(Integer.valueOf(id), new Constraint());
                }
                Constraint constraint = this.mConstraints.get(Integer.valueOf(id));
                if (constraint != null) {
                    if (!constraint.layout.mApply) {
                        constraint.fillFrom(id, layoutParams);
                        if (childAt instanceof ConstraintHelper) {
                            constraint.layout.mReferenceIds = ((ConstraintHelper) childAt).getReferencedIds();
                            if (childAt instanceof Barrier) {
                                Barrier barrier = (Barrier) childAt;
                                constraint.layout.mBarrierAllowsGoneWidgets = barrier.getAllowsGoneWidget();
                                constraint.layout.mBarrierDirection = barrier.getType();
                                constraint.layout.mBarrierMargin = barrier.getMargin();
                            }
                        }
                        constraint.layout.mApply = true;
                    }
                    if (!constraint.propertySet.mApply) {
                        constraint.propertySet.visibility = childAt.getVisibility();
                        constraint.propertySet.alpha = childAt.getAlpha();
                        constraint.propertySet.mApply = true;
                    }
                    if (Build.VERSION.SDK_INT >= 17 && !constraint.transform.mApply) {
                        constraint.transform.mApply = true;
                        constraint.transform.rotation = childAt.getRotation();
                        constraint.transform.rotationX = childAt.getRotationX();
                        constraint.transform.rotationY = childAt.getRotationY();
                        constraint.transform.scaleX = childAt.getScaleX();
                        constraint.transform.scaleY = childAt.getScaleY();
                        float pivotX = childAt.getPivotX();
                        float pivotY = childAt.getPivotY();
                        if (!(((double) pivotX) == 0.0d && ((double) pivotY) == 0.0d)) {
                            constraint.transform.transformPivotX = pivotX;
                            constraint.transform.transformPivotY = pivotY;
                        }
                        constraint.transform.translationX = childAt.getTranslationX();
                        constraint.transform.translationY = childAt.getTranslationY();
                        if (Build.VERSION.SDK_INT >= 21) {
                            constraint.transform.translationZ = childAt.getTranslationZ();
                            if (constraint.transform.applyElevation) {
                                constraint.transform.elevation = childAt.getElevation();
                            }
                        }
                    }
                }
                i++;
            } else {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
        }
    }

    public void applyDeltaFrom(ConstraintSet constraintSet) {
        for (Constraint next : constraintSet.mConstraints.values()) {
            if (next.mDelta != null) {
                if (next.mTargetString != null) {
                    for (Integer intValue : this.mConstraints.keySet()) {
                        Constraint constraint = getConstraint(intValue.intValue());
                        if (constraint.layout.mConstraintTag != null && next.mTargetString.matches(constraint.layout.mConstraintTag)) {
                            next.mDelta.applyDelta(constraint);
                            constraint.mCustomConstraints.putAll((HashMap) next.mCustomConstraints.clone());
                        }
                    }
                } else {
                    next.mDelta.applyDelta(getConstraint(next.mViewId));
                }
            }
        }
    }

    static void parseDimensionConstraints(Object obj, TypedArray typedArray, int i, int i2) {
        if (obj != null) {
            int i3 = typedArray.peekValue(i).type;
            if (i3 != 3) {
                int i4 = -2;
                boolean z = false;
                if (i3 != 5) {
                    int i5 = typedArray.getInt(i, 0);
                    if (i5 != -4) {
                        i4 = (i5 == -3 || !(i5 == -2 || i5 == -1)) ? 0 : i5;
                    } else {
                        z = true;
                    }
                } else {
                    i4 = typedArray.getDimensionPixelSize(i, 0);
                }
                if (obj instanceof ConstraintLayout.LayoutParams) {
                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) obj;
                    if (i2 == 0) {
                        layoutParams.width = i4;
                        layoutParams.constrainedWidth = z;
                        return;
                    }
                    layoutParams.height = i4;
                    layoutParams.constrainedHeight = z;
                } else if (obj instanceof Layout) {
                    Layout layout = (Layout) obj;
                    if (i2 == 0) {
                        layout.mWidth = i4;
                        layout.constrainedWidth = z;
                        return;
                    }
                    layout.mHeight = i4;
                    layout.constrainedHeight = z;
                } else if (obj instanceof Constraint.Delta) {
                    Constraint.Delta delta = (Constraint.Delta) obj;
                    if (i2 == 0) {
                        delta.add(23, i4);
                        delta.add(80, z);
                        return;
                    }
                    delta.add(21, i4);
                    delta.add(81, z);
                }
            } else {
                parseDimensionConstraintsString(obj, typedArray.getString(i), i2);
            }
        }
    }

    static void parseDimensionRatioString(ConstraintLayout.LayoutParams layoutParams, String str) {
        float f = Float.NaN;
        int i = -1;
        if (str != null) {
            int length = str.length();
            int indexOf = str.indexOf(44);
            int i2 = 0;
            if (indexOf > 0 && indexOf < length - 1) {
                String substring = str.substring(0, indexOf);
                if (substring.equalsIgnoreCase("W")) {
                    i = 0;
                } else if (substring.equalsIgnoreCase("H")) {
                    i = 1;
                }
                i2 = indexOf + 1;
            }
            int indexOf2 = str.indexOf(58);
            if (indexOf2 < 0 || indexOf2 >= length - 1) {
                String substring2 = str.substring(i2);
                if (substring2.length() > 0) {
                    f = Float.parseFloat(substring2);
                }
            } else {
                String substring3 = str.substring(i2, indexOf2);
                String substring4 = str.substring(indexOf2 + 1);
                if (substring3.length() > 0 && substring4.length() > 0) {
                    try {
                        float parseFloat = Float.parseFloat(substring3);
                        float parseFloat2 = Float.parseFloat(substring4);
                        if (parseFloat > 0.0f && parseFloat2 > 0.0f) {
                            f = i == 1 ? Math.abs(parseFloat2 / parseFloat) : Math.abs(parseFloat / parseFloat2);
                        }
                    } catch (NumberFormatException unused) {
                    }
                }
            }
        }
        layoutParams.dimensionRatio = str;
        layoutParams.dimensionRatioValue = f;
        layoutParams.dimensionRatioSide = i;
    }

    static void parseDimensionConstraintsString(Object obj, String str, int i) {
        if (str != null) {
            int indexOf = str.indexOf(61);
            int length = str.length();
            if (indexOf > 0 && indexOf < length - 1) {
                String substring = str.substring(0, indexOf);
                String substring2 = str.substring(indexOf + 1);
                if (substring2.length() > 0) {
                    String trim = substring.trim();
                    String trim2 = substring2.trim();
                    if (KEY_RATIO.equalsIgnoreCase(trim)) {
                        if (obj instanceof ConstraintLayout.LayoutParams) {
                            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) obj;
                            if (i == 0) {
                                layoutParams.width = 0;
                            } else {
                                layoutParams.height = 0;
                            }
                            parseDimensionRatioString(layoutParams, trim2);
                        } else if (obj instanceof Layout) {
                            ((Layout) obj).dimensionRatio = trim2;
                        } else if (obj instanceof Constraint.Delta) {
                            ((Constraint.Delta) obj).add(5, trim2);
                        }
                    } else if (KEY_WEIGHT.equalsIgnoreCase(trim)) {
                        try {
                            float parseFloat = Float.parseFloat(trim2);
                            if (obj instanceof ConstraintLayout.LayoutParams) {
                                ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) obj;
                                if (i == 0) {
                                    layoutParams2.width = 0;
                                    layoutParams2.horizontalWeight = parseFloat;
                                    return;
                                }
                                layoutParams2.height = 0;
                                layoutParams2.verticalWeight = parseFloat;
                            } else if (obj instanceof Layout) {
                                Layout layout = (Layout) obj;
                                if (i == 0) {
                                    layout.mWidth = 0;
                                    layout.horizontalWeight = parseFloat;
                                    return;
                                }
                                layout.mHeight = 0;
                                layout.verticalWeight = parseFloat;
                            } else if (obj instanceof Constraint.Delta) {
                                Constraint.Delta delta = (Constraint.Delta) obj;
                                if (i == 0) {
                                    delta.add(23, 0);
                                    delta.add(39, parseFloat);
                                    return;
                                }
                                delta.add(21, 0);
                                delta.add(40, parseFloat);
                            }
                        } catch (NumberFormatException unused) {
                        }
                    } else if (KEY_PERCENT_PARENT.equalsIgnoreCase(trim)) {
                        float max = Math.max(0.0f, Math.min(1.0f, Float.parseFloat(trim2)));
                        if (obj instanceof ConstraintLayout.LayoutParams) {
                            ConstraintLayout.LayoutParams layoutParams3 = (ConstraintLayout.LayoutParams) obj;
                            if (i == 0) {
                                layoutParams3.width = 0;
                                layoutParams3.matchConstraintPercentWidth = max;
                                layoutParams3.matchConstraintDefaultWidth = 2;
                                return;
                            }
                            layoutParams3.height = 0;
                            layoutParams3.matchConstraintPercentHeight = max;
                            layoutParams3.matchConstraintDefaultHeight = 2;
                        } else if (obj instanceof Layout) {
                            Layout layout2 = (Layout) obj;
                            if (i == 0) {
                                layout2.mWidth = 0;
                                layout2.widthPercent = max;
                                layout2.widthDefault = 2;
                                return;
                            }
                            layout2.mHeight = 0;
                            layout2.heightPercent = max;
                            layout2.heightDefault = 2;
                        } else if (obj instanceof Constraint.Delta) {
                            Constraint.Delta delta2 = (Constraint.Delta) obj;
                            if (i == 0) {
                                delta2.add(23, 0);
                                delta2.add(54, 2);
                                return;
                            }
                            delta2.add(21, 0);
                            delta2.add(55, 2);
                        }
                    }
                }
            }
        }
    }

    public static class Layout {
        private static final int BARRIER_ALLOWS_GONE_WIDGETS = 75;
        private static final int BARRIER_DIRECTION = 72;
        private static final int BARRIER_MARGIN = 73;
        private static final int BASELINE_MARGIN = 80;
        private static final int BASELINE_TO_BASELINE = 1;
        private static final int BASELINE_TO_BOTTOM = 78;
        private static final int BASELINE_TO_TOP = 77;
        private static final int BOTTOM_MARGIN = 2;
        private static final int BOTTOM_TO_BOTTOM = 3;
        private static final int BOTTOM_TO_TOP = 4;
        private static final int CHAIN_USE_RTL = 71;
        private static final int CIRCLE = 61;
        private static final int CIRCLE_ANGLE = 63;
        private static final int CIRCLE_RADIUS = 62;
        private static final int CONSTRAINED_HEIGHT = 88;
        private static final int CONSTRAINED_WIDTH = 87;
        private static final int CONSTRAINT_REFERENCED_IDS = 74;
        private static final int CONSTRAINT_TAG = 89;
        private static final int DIMENSION_RATIO = 5;
        private static final int EDITOR_ABSOLUTE_X = 6;
        private static final int EDITOR_ABSOLUTE_Y = 7;
        private static final int END_MARGIN = 8;
        private static final int END_TO_END = 9;
        private static final int END_TO_START = 10;
        private static final int GONE_BASELINE_MARGIN = 79;
        private static final int GONE_BOTTOM_MARGIN = 11;
        private static final int GONE_END_MARGIN = 12;
        private static final int GONE_LEFT_MARGIN = 13;
        private static final int GONE_RIGHT_MARGIN = 14;
        private static final int GONE_START_MARGIN = 15;
        private static final int GONE_TOP_MARGIN = 16;
        private static final int GUIDE_BEGIN = 17;
        private static final int GUIDE_END = 18;
        private static final int GUIDE_PERCENT = 19;
        private static final int GUIDE_USE_RTL = 90;
        private static final int HEIGHT_DEFAULT = 82;
        private static final int HEIGHT_MAX = 83;
        private static final int HEIGHT_MIN = 85;
        private static final int HEIGHT_PERCENT = 70;
        private static final int HORIZONTAL_BIAS = 20;
        private static final int HORIZONTAL_STYLE = 39;
        private static final int HORIZONTAL_WEIGHT = 37;
        private static final int LAYOUT_CONSTRAINT_HEIGHT = 42;
        private static final int LAYOUT_CONSTRAINT_WIDTH = 41;
        private static final int LAYOUT_HEIGHT = 21;
        private static final int LAYOUT_WIDTH = 22;
        private static final int LAYOUT_WRAP_BEHAVIOR = 76;
        private static final int LEFT_MARGIN = 23;
        private static final int LEFT_TO_LEFT = 24;
        private static final int LEFT_TO_RIGHT = 25;
        private static final int ORIENTATION = 26;
        private static final int RIGHT_MARGIN = 27;
        private static final int RIGHT_TO_LEFT = 28;
        private static final int RIGHT_TO_RIGHT = 29;
        private static final int START_MARGIN = 30;
        private static final int START_TO_END = 31;
        private static final int START_TO_START = 32;
        private static final int TOP_MARGIN = 33;
        private static final int TOP_TO_BOTTOM = 34;
        private static final int TOP_TO_TOP = 35;
        public static final int UNSET = -1;
        public static final int UNSET_GONE_MARGIN = Integer.MIN_VALUE;
        private static final int UNUSED = 91;
        private static final int VERTICAL_BIAS = 36;
        private static final int VERTICAL_STYLE = 40;
        private static final int VERTICAL_WEIGHT = 38;
        private static final int WIDTH_DEFAULT = 81;
        private static final int WIDTH_MAX = 84;
        private static final int WIDTH_MIN = 86;
        private static final int WIDTH_PERCENT = 69;
        private static SparseIntArray mapToConstant;
        public int baselineMargin = 0;
        public int baselineToBaseline = -1;
        public int baselineToBottom = -1;
        public int baselineToTop = -1;
        public int bottomMargin = 0;
        public int bottomToBottom = -1;
        public int bottomToTop = -1;
        public float circleAngle = 0.0f;
        public int circleConstraint = -1;
        public int circleRadius = 0;
        public boolean constrainedHeight = false;
        public boolean constrainedWidth = false;
        public String dimensionRatio = null;
        public int editorAbsoluteX = -1;
        public int editorAbsoluteY = -1;
        public int endMargin = 0;
        public int endToEnd = -1;
        public int endToStart = -1;
        public int goneBaselineMargin = Integer.MIN_VALUE;
        public int goneBottomMargin = Integer.MIN_VALUE;
        public int goneEndMargin = Integer.MIN_VALUE;
        public int goneLeftMargin = Integer.MIN_VALUE;
        public int goneRightMargin = Integer.MIN_VALUE;
        public int goneStartMargin = Integer.MIN_VALUE;
        public int goneTopMargin = Integer.MIN_VALUE;
        public int guideBegin = -1;
        public int guideEnd = -1;
        public float guidePercent = -1.0f;
        public boolean guidelineUseRtl = true;
        public int heightDefault = 0;
        public int heightMax = 0;
        public int heightMin = 0;
        public float heightPercent = 1.0f;
        public float horizontalBias = 0.5f;
        public int horizontalChainStyle = 0;
        public float horizontalWeight = -1.0f;
        public int leftMargin = 0;
        public int leftToLeft = -1;
        public int leftToRight = -1;
        public boolean mApply = false;
        public boolean mBarrierAllowsGoneWidgets = true;
        public int mBarrierDirection = -1;
        public int mBarrierMargin = 0;
        public String mConstraintTag;
        public int mHeight;
        public int mHelperType = -1;
        public boolean mIsGuideline = false;
        public boolean mOverride = false;
        public String mReferenceIdString;
        public int[] mReferenceIds;
        public int mWidth;
        public int mWrapBehavior = 0;
        public int orientation = -1;
        public int rightMargin = 0;
        public int rightToLeft = -1;
        public int rightToRight = -1;
        public int startMargin = 0;
        public int startToEnd = -1;
        public int startToStart = -1;
        public int topMargin = 0;
        public int topToBottom = -1;
        public int topToTop = -1;
        public float verticalBias = 0.5f;
        public int verticalChainStyle = 0;
        public float verticalWeight = -1.0f;
        public int widthDefault = 0;
        public int widthMax = 0;
        public int widthMin = 0;
        public float widthPercent = 1.0f;

        public void copyFrom(Layout layout) {
            this.mIsGuideline = layout.mIsGuideline;
            this.mWidth = layout.mWidth;
            this.mApply = layout.mApply;
            this.mHeight = layout.mHeight;
            this.guideBegin = layout.guideBegin;
            this.guideEnd = layout.guideEnd;
            this.guidePercent = layout.guidePercent;
            this.guidelineUseRtl = layout.guidelineUseRtl;
            this.leftToLeft = layout.leftToLeft;
            this.leftToRight = layout.leftToRight;
            this.rightToLeft = layout.rightToLeft;
            this.rightToRight = layout.rightToRight;
            this.topToTop = layout.topToTop;
            this.topToBottom = layout.topToBottom;
            this.bottomToTop = layout.bottomToTop;
            this.bottomToBottom = layout.bottomToBottom;
            this.baselineToBaseline = layout.baselineToBaseline;
            this.baselineToTop = layout.baselineToTop;
            this.baselineToBottom = layout.baselineToBottom;
            this.startToEnd = layout.startToEnd;
            this.startToStart = layout.startToStart;
            this.endToStart = layout.endToStart;
            this.endToEnd = layout.endToEnd;
            this.horizontalBias = layout.horizontalBias;
            this.verticalBias = layout.verticalBias;
            this.dimensionRatio = layout.dimensionRatio;
            this.circleConstraint = layout.circleConstraint;
            this.circleRadius = layout.circleRadius;
            this.circleAngle = layout.circleAngle;
            this.editorAbsoluteX = layout.editorAbsoluteX;
            this.editorAbsoluteY = layout.editorAbsoluteY;
            this.orientation = layout.orientation;
            this.leftMargin = layout.leftMargin;
            this.rightMargin = layout.rightMargin;
            this.topMargin = layout.topMargin;
            this.bottomMargin = layout.bottomMargin;
            this.endMargin = layout.endMargin;
            this.startMargin = layout.startMargin;
            this.baselineMargin = layout.baselineMargin;
            this.goneLeftMargin = layout.goneLeftMargin;
            this.goneTopMargin = layout.goneTopMargin;
            this.goneRightMargin = layout.goneRightMargin;
            this.goneBottomMargin = layout.goneBottomMargin;
            this.goneEndMargin = layout.goneEndMargin;
            this.goneStartMargin = layout.goneStartMargin;
            this.goneBaselineMargin = layout.goneBaselineMargin;
            this.verticalWeight = layout.verticalWeight;
            this.horizontalWeight = layout.horizontalWeight;
            this.horizontalChainStyle = layout.horizontalChainStyle;
            this.verticalChainStyle = layout.verticalChainStyle;
            this.widthDefault = layout.widthDefault;
            this.heightDefault = layout.heightDefault;
            this.widthMax = layout.widthMax;
            this.heightMax = layout.heightMax;
            this.widthMin = layout.widthMin;
            this.heightMin = layout.heightMin;
            this.widthPercent = layout.widthPercent;
            this.heightPercent = layout.heightPercent;
            this.mBarrierDirection = layout.mBarrierDirection;
            this.mBarrierMargin = layout.mBarrierMargin;
            this.mHelperType = layout.mHelperType;
            this.mConstraintTag = layout.mConstraintTag;
            int[] iArr = layout.mReferenceIds;
            if (iArr == null || layout.mReferenceIdString != null) {
                this.mReferenceIds = null;
            } else {
                this.mReferenceIds = Arrays.copyOf(iArr, iArr.length);
            }
            this.mReferenceIdString = layout.mReferenceIdString;
            this.constrainedWidth = layout.constrainedWidth;
            this.constrainedHeight = layout.constrainedHeight;
            this.mBarrierAllowsGoneWidgets = layout.mBarrierAllowsGoneWidgets;
            this.mWrapBehavior = layout.mWrapBehavior;
        }

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            mapToConstant = sparseIntArray;
            sparseIntArray.append(R.styleable.Layout_layout_constraintLeft_toLeftOf, 24);
            mapToConstant.append(R.styleable.Layout_layout_constraintLeft_toRightOf, 25);
            mapToConstant.append(R.styleable.Layout_layout_constraintRight_toLeftOf, 28);
            mapToConstant.append(R.styleable.Layout_layout_constraintRight_toRightOf, 29);
            mapToConstant.append(R.styleable.Layout_layout_constraintTop_toTopOf, 35);
            mapToConstant.append(R.styleable.Layout_layout_constraintTop_toBottomOf, 34);
            mapToConstant.append(R.styleable.Layout_layout_constraintBottom_toTopOf, 4);
            mapToConstant.append(R.styleable.Layout_layout_constraintBottom_toBottomOf, 3);
            mapToConstant.append(R.styleable.Layout_layout_constraintBaseline_toBaselineOf, 1);
            mapToConstant.append(R.styleable.Layout_layout_editor_absoluteX, 6);
            mapToConstant.append(R.styleable.Layout_layout_editor_absoluteY, 7);
            mapToConstant.append(R.styleable.Layout_layout_constraintGuide_begin, 17);
            mapToConstant.append(R.styleable.Layout_layout_constraintGuide_end, 18);
            mapToConstant.append(R.styleable.Layout_layout_constraintGuide_percent, 19);
            mapToConstant.append(R.styleable.Layout_guidelineUseRtl, 90);
            mapToConstant.append(R.styleable.Layout_android_orientation, 26);
            mapToConstant.append(R.styleable.Layout_layout_constraintStart_toEndOf, 31);
            mapToConstant.append(R.styleable.Layout_layout_constraintStart_toStartOf, 32);
            mapToConstant.append(R.styleable.Layout_layout_constraintEnd_toStartOf, 10);
            mapToConstant.append(R.styleable.Layout_layout_constraintEnd_toEndOf, 9);
            mapToConstant.append(R.styleable.Layout_layout_goneMarginLeft, 13);
            mapToConstant.append(R.styleable.Layout_layout_goneMarginTop, 16);
            mapToConstant.append(R.styleable.Layout_layout_goneMarginRight, 14);
            mapToConstant.append(R.styleable.Layout_layout_goneMarginBottom, 11);
            mapToConstant.append(R.styleable.Layout_layout_goneMarginStart, 15);
            mapToConstant.append(R.styleable.Layout_layout_goneMarginEnd, 12);
            mapToConstant.append(R.styleable.Layout_layout_constraintVertical_weight, 38);
            mapToConstant.append(R.styleable.Layout_layout_constraintHorizontal_weight, 37);
            mapToConstant.append(R.styleable.Layout_layout_constraintHorizontal_chainStyle, 39);
            mapToConstant.append(R.styleable.Layout_layout_constraintVertical_chainStyle, 40);
            mapToConstant.append(R.styleable.Layout_layout_constraintHorizontal_bias, 20);
            mapToConstant.append(R.styleable.Layout_layout_constraintVertical_bias, 36);
            mapToConstant.append(R.styleable.Layout_layout_constraintDimensionRatio, 5);
            mapToConstant.append(R.styleable.Layout_layout_constraintLeft_creator, 91);
            mapToConstant.append(R.styleable.Layout_layout_constraintTop_creator, 91);
            mapToConstant.append(R.styleable.Layout_layout_constraintRight_creator, 91);
            mapToConstant.append(R.styleable.Layout_layout_constraintBottom_creator, 91);
            mapToConstant.append(R.styleable.Layout_layout_constraintBaseline_creator, 91);
            mapToConstant.append(R.styleable.Layout_android_layout_marginLeft, 23);
            mapToConstant.append(R.styleable.Layout_android_layout_marginRight, 27);
            mapToConstant.append(R.styleable.Layout_android_layout_marginStart, 30);
            mapToConstant.append(R.styleable.Layout_android_layout_marginEnd, 8);
            mapToConstant.append(R.styleable.Layout_android_layout_marginTop, 33);
            mapToConstant.append(R.styleable.Layout_android_layout_marginBottom, 2);
            mapToConstant.append(R.styleable.Layout_android_layout_width, 22);
            mapToConstant.append(R.styleable.Layout_android_layout_height, 21);
            mapToConstant.append(R.styleable.Layout_layout_constraintWidth, 41);
            mapToConstant.append(R.styleable.Layout_layout_constraintHeight, 42);
            mapToConstant.append(R.styleable.Layout_layout_constrainedWidth, 41);
            mapToConstant.append(R.styleable.Layout_layout_constrainedHeight, 42);
            mapToConstant.append(R.styleable.Layout_layout_wrapBehaviorInParent, 76);
            mapToConstant.append(R.styleable.Layout_layout_constraintCircle, 61);
            mapToConstant.append(R.styleable.Layout_layout_constraintCircleRadius, 62);
            mapToConstant.append(R.styleable.Layout_layout_constraintCircleAngle, 63);
            mapToConstant.append(R.styleable.Layout_layout_constraintWidth_percent, 69);
            mapToConstant.append(R.styleable.Layout_layout_constraintHeight_percent, 70);
            mapToConstant.append(R.styleable.Layout_chainUseRtl, 71);
            mapToConstant.append(R.styleable.Layout_barrierDirection, 72);
            mapToConstant.append(R.styleable.Layout_barrierMargin, 73);
            mapToConstant.append(R.styleable.Layout_constraint_referenced_ids, 74);
            mapToConstant.append(R.styleable.Layout_barrierAllowsGoneWidgets, 75);
        }

        /* access modifiers changed from: package-private */
        public void fillFromAttributeList(Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Layout);
            this.mApply = true;
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                int i2 = mapToConstant.get(index);
                switch (i2) {
                    case 1:
                        this.baselineToBaseline = ConstraintSet.lookupID(obtainStyledAttributes, index, this.baselineToBaseline);
                        break;
                    case 2:
                        this.bottomMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.bottomMargin);
                        break;
                    case 3:
                        this.bottomToBottom = ConstraintSet.lookupID(obtainStyledAttributes, index, this.bottomToBottom);
                        break;
                    case 4:
                        this.bottomToTop = ConstraintSet.lookupID(obtainStyledAttributes, index, this.bottomToTop);
                        break;
                    case 5:
                        this.dimensionRatio = obtainStyledAttributes.getString(index);
                        break;
                    case 6:
                        this.editorAbsoluteX = obtainStyledAttributes.getDimensionPixelOffset(index, this.editorAbsoluteX);
                        break;
                    case 7:
                        this.editorAbsoluteY = obtainStyledAttributes.getDimensionPixelOffset(index, this.editorAbsoluteY);
                        break;
                    case 8:
                        if (Build.VERSION.SDK_INT < 17) {
                            break;
                        } else {
                            this.endMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.endMargin);
                            break;
                        }
                    case 9:
                        this.endToEnd = ConstraintSet.lookupID(obtainStyledAttributes, index, this.endToEnd);
                        break;
                    case 10:
                        this.endToStart = ConstraintSet.lookupID(obtainStyledAttributes, index, this.endToStart);
                        break;
                    case 11:
                        this.goneBottomMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneBottomMargin);
                        break;
                    case 12:
                        this.goneEndMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneEndMargin);
                        break;
                    case 13:
                        this.goneLeftMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneLeftMargin);
                        break;
                    case 14:
                        this.goneRightMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneRightMargin);
                        break;
                    case 15:
                        this.goneStartMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneStartMargin);
                        break;
                    case 16:
                        this.goneTopMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneTopMargin);
                        break;
                    case 17:
                        this.guideBegin = obtainStyledAttributes.getDimensionPixelOffset(index, this.guideBegin);
                        break;
                    case 18:
                        this.guideEnd = obtainStyledAttributes.getDimensionPixelOffset(index, this.guideEnd);
                        break;
                    case 19:
                        this.guidePercent = obtainStyledAttributes.getFloat(index, this.guidePercent);
                        break;
                    case 20:
                        this.horizontalBias = obtainStyledAttributes.getFloat(index, this.horizontalBias);
                        break;
                    case 21:
                        this.mHeight = obtainStyledAttributes.getLayoutDimension(index, this.mHeight);
                        break;
                    case 22:
                        this.mWidth = obtainStyledAttributes.getLayoutDimension(index, this.mWidth);
                        break;
                    case 23:
                        this.leftMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.leftMargin);
                        break;
                    case 24:
                        this.leftToLeft = ConstraintSet.lookupID(obtainStyledAttributes, index, this.leftToLeft);
                        break;
                    case 25:
                        this.leftToRight = ConstraintSet.lookupID(obtainStyledAttributes, index, this.leftToRight);
                        break;
                    case 26:
                        this.orientation = obtainStyledAttributes.getInt(index, this.orientation);
                        break;
                    case 27:
                        this.rightMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.rightMargin);
                        break;
                    case 28:
                        this.rightToLeft = ConstraintSet.lookupID(obtainStyledAttributes, index, this.rightToLeft);
                        break;
                    case 29:
                        this.rightToRight = ConstraintSet.lookupID(obtainStyledAttributes, index, this.rightToRight);
                        break;
                    case 30:
                        if (Build.VERSION.SDK_INT < 17) {
                            break;
                        } else {
                            this.startMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.startMargin);
                            break;
                        }
                    case 31:
                        this.startToEnd = ConstraintSet.lookupID(obtainStyledAttributes, index, this.startToEnd);
                        break;
                    case 32:
                        this.startToStart = ConstraintSet.lookupID(obtainStyledAttributes, index, this.startToStart);
                        break;
                    case 33:
                        this.topMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.topMargin);
                        break;
                    case 34:
                        this.topToBottom = ConstraintSet.lookupID(obtainStyledAttributes, index, this.topToBottom);
                        break;
                    case 35:
                        this.topToTop = ConstraintSet.lookupID(obtainStyledAttributes, index, this.topToTop);
                        break;
                    case 36:
                        this.verticalBias = obtainStyledAttributes.getFloat(index, this.verticalBias);
                        break;
                    case 37:
                        this.horizontalWeight = obtainStyledAttributes.getFloat(index, this.horizontalWeight);
                        break;
                    case 38:
                        this.verticalWeight = obtainStyledAttributes.getFloat(index, this.verticalWeight);
                        break;
                    case 39:
                        this.horizontalChainStyle = obtainStyledAttributes.getInt(index, this.horizontalChainStyle);
                        break;
                    case 40:
                        this.verticalChainStyle = obtainStyledAttributes.getInt(index, this.verticalChainStyle);
                        break;
                    case 41:
                        ConstraintSet.parseDimensionConstraints(this, obtainStyledAttributes, index, 0);
                        break;
                    case 42:
                        ConstraintSet.parseDimensionConstraints(this, obtainStyledAttributes, index, 1);
                        break;
                    default:
                        switch (i2) {
                            case 61:
                                this.circleConstraint = ConstraintSet.lookupID(obtainStyledAttributes, index, this.circleConstraint);
                                break;
                            case 62:
                                this.circleRadius = obtainStyledAttributes.getDimensionPixelSize(index, this.circleRadius);
                                break;
                            case 63:
                                this.circleAngle = obtainStyledAttributes.getFloat(index, this.circleAngle);
                                break;
                            default:
                                switch (i2) {
                                    case 69:
                                        this.widthPercent = obtainStyledAttributes.getFloat(index, 1.0f);
                                        break;
                                    case 70:
                                        this.heightPercent = obtainStyledAttributes.getFloat(index, 1.0f);
                                        break;
                                    case 71:
                                        Log.e(ConstraintSet.TAG, "CURRENTLY UNSUPPORTED");
                                        break;
                                    case 72:
                                        this.mBarrierDirection = obtainStyledAttributes.getInt(index, this.mBarrierDirection);
                                        break;
                                    case 73:
                                        this.mBarrierMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.mBarrierMargin);
                                        break;
                                    case 74:
                                        this.mReferenceIdString = obtainStyledAttributes.getString(index);
                                        break;
                                    case 75:
                                        this.mBarrierAllowsGoneWidgets = obtainStyledAttributes.getBoolean(index, this.mBarrierAllowsGoneWidgets);
                                        break;
                                    case 76:
                                        this.mWrapBehavior = obtainStyledAttributes.getInt(index, this.mWrapBehavior);
                                        break;
                                    case 77:
                                        this.baselineToTop = ConstraintSet.lookupID(obtainStyledAttributes, index, this.baselineToTop);
                                        break;
                                    case 78:
                                        this.baselineToBottom = ConstraintSet.lookupID(obtainStyledAttributes, index, this.baselineToBottom);
                                        break;
                                    case 79:
                                        this.goneBaselineMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneBaselineMargin);
                                        break;
                                    case 80:
                                        this.baselineMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.baselineMargin);
                                        break;
                                    case 81:
                                        this.widthDefault = obtainStyledAttributes.getInt(index, this.widthDefault);
                                        break;
                                    case 82:
                                        this.heightDefault = obtainStyledAttributes.getInt(index, this.heightDefault);
                                        break;
                                    case 83:
                                        this.heightMax = obtainStyledAttributes.getDimensionPixelSize(index, this.heightMax);
                                        break;
                                    case 84:
                                        this.widthMax = obtainStyledAttributes.getDimensionPixelSize(index, this.widthMax);
                                        break;
                                    case 85:
                                        this.heightMin = obtainStyledAttributes.getDimensionPixelSize(index, this.heightMin);
                                        break;
                                    case 86:
                                        this.widthMin = obtainStyledAttributes.getDimensionPixelSize(index, this.widthMin);
                                        break;
                                    case 87:
                                        this.constrainedWidth = obtainStyledAttributes.getBoolean(index, this.constrainedWidth);
                                        break;
                                    case 88:
                                        this.constrainedHeight = obtainStyledAttributes.getBoolean(index, this.constrainedHeight);
                                        break;
                                    case 89:
                                        this.mConstraintTag = obtainStyledAttributes.getString(index);
                                        break;
                                    case 90:
                                        this.guidelineUseRtl = obtainStyledAttributes.getBoolean(index, this.guidelineUseRtl);
                                        break;
                                    case 91:
                                        Log.w(ConstraintSet.TAG, "unused attribute 0x" + Integer.toHexString(index) + "   " + mapToConstant.get(index));
                                        break;
                                    default:
                                        Log.w(ConstraintSet.TAG, "Unknown attribute 0x" + Integer.toHexString(index) + "   " + mapToConstant.get(index));
                                        break;
                                }
                        }
                }
            }
            obtainStyledAttributes.recycle();
        }

        /* JADX WARNING: type inference failed for: r2v8, types: [java.lang.String] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void dump(androidx.constraintlayout.motion.widget.MotionScene r10, java.lang.StringBuilder r11) {
            /*
                r9 = this;
                java.lang.Class r0 = r9.getClass()
                java.lang.reflect.Field[] r0 = r0.getDeclaredFields()
                java.lang.String r1 = "\n"
                r11.append(r1)
                r1 = 0
            L_0x000e:
                int r2 = r0.length
                if (r1 >= r2) goto L_0x0080
                r2 = r0[r1]
                java.lang.String r3 = r2.getName()
                int r4 = r2.getModifiers()
                boolean r4 = java.lang.reflect.Modifier.isStatic(r4)
                if (r4 == 0) goto L_0x0022
                goto L_0x007d
            L_0x0022:
                java.lang.Object r4 = r2.get(r9)     // Catch:{ IllegalAccessException -> 0x0079 }
                java.lang.Class r2 = r2.getType()     // Catch:{ IllegalAccessException -> 0x0079 }
                java.lang.Class r5 = java.lang.Integer.TYPE     // Catch:{ IllegalAccessException -> 0x0079 }
                java.lang.String r6 = "\"\n"
                java.lang.String r7 = " = \""
                java.lang.String r8 = "    "
                if (r2 != r5) goto L_0x0059
                java.lang.Integer r4 = (java.lang.Integer) r4     // Catch:{ IllegalAccessException -> 0x0079 }
                int r2 = r4.intValue()     // Catch:{ IllegalAccessException -> 0x0079 }
                r5 = -1
                if (r2 == r5) goto L_0x007d
                int r2 = r4.intValue()     // Catch:{ IllegalAccessException -> 0x0079 }
                java.lang.String r2 = r10.lookUpConstraintName(r2)     // Catch:{ IllegalAccessException -> 0x0079 }
                r11.append(r8)     // Catch:{ IllegalAccessException -> 0x0079 }
                r11.append(r3)     // Catch:{ IllegalAccessException -> 0x0079 }
                r11.append(r7)     // Catch:{ IllegalAccessException -> 0x0079 }
                if (r2 != 0) goto L_0x0051
                goto L_0x0052
            L_0x0051:
                r4 = r2
            L_0x0052:
                r11.append(r4)     // Catch:{ IllegalAccessException -> 0x0079 }
                r11.append(r6)     // Catch:{ IllegalAccessException -> 0x0079 }
                goto L_0x007d
            L_0x0059:
                java.lang.Class r5 = java.lang.Float.TYPE     // Catch:{ IllegalAccessException -> 0x0079 }
                if (r2 != r5) goto L_0x007d
                java.lang.Float r4 = (java.lang.Float) r4     // Catch:{ IllegalAccessException -> 0x0079 }
                float r2 = r4.floatValue()     // Catch:{ IllegalAccessException -> 0x0079 }
                r5 = -1082130432(0xffffffffbf800000, float:-1.0)
                int r2 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
                if (r2 == 0) goto L_0x007d
                r11.append(r8)     // Catch:{ IllegalAccessException -> 0x0079 }
                r11.append(r3)     // Catch:{ IllegalAccessException -> 0x0079 }
                r11.append(r7)     // Catch:{ IllegalAccessException -> 0x0079 }
                r11.append(r4)     // Catch:{ IllegalAccessException -> 0x0079 }
                r11.append(r6)     // Catch:{ IllegalAccessException -> 0x0079 }
                goto L_0x007d
            L_0x0079:
                r2 = move-exception
                r2.printStackTrace()
            L_0x007d:
                int r1 = r1 + 1
                goto L_0x000e
            L_0x0080:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintSet.Layout.dump(androidx.constraintlayout.motion.widget.MotionScene, java.lang.StringBuilder):void");
        }
    }

    public static class Transform {
        private static final int ELEVATION = 11;
        private static final int ROTATION = 1;
        private static final int ROTATION_X = 2;
        private static final int ROTATION_Y = 3;
        private static final int SCALE_X = 4;
        private static final int SCALE_Y = 5;
        private static final int TRANSFORM_PIVOT_TARGET = 12;
        private static final int TRANSFORM_PIVOT_X = 6;
        private static final int TRANSFORM_PIVOT_Y = 7;
        private static final int TRANSLATION_X = 8;
        private static final int TRANSLATION_Y = 9;
        private static final int TRANSLATION_Z = 10;
        private static SparseIntArray mapToConstant;
        public boolean applyElevation = false;
        public float elevation = 0.0f;
        public boolean mApply = false;
        public float rotation = 0.0f;
        public float rotationX = 0.0f;
        public float rotationY = 0.0f;
        public float scaleX = 1.0f;
        public float scaleY = 1.0f;
        public int transformPivotTarget = -1;
        public float transformPivotX = Float.NaN;
        public float transformPivotY = Float.NaN;
        public float translationX = 0.0f;
        public float translationY = 0.0f;
        public float translationZ = 0.0f;

        public void copyFrom(Transform transform) {
            this.mApply = transform.mApply;
            this.rotation = transform.rotation;
            this.rotationX = transform.rotationX;
            this.rotationY = transform.rotationY;
            this.scaleX = transform.scaleX;
            this.scaleY = transform.scaleY;
            this.transformPivotX = transform.transformPivotX;
            this.transformPivotY = transform.transformPivotY;
            this.transformPivotTarget = transform.transformPivotTarget;
            this.translationX = transform.translationX;
            this.translationY = transform.translationY;
            this.translationZ = transform.translationZ;
            this.applyElevation = transform.applyElevation;
            this.elevation = transform.elevation;
        }

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            mapToConstant = sparseIntArray;
            sparseIntArray.append(R.styleable.Transform_android_rotation, 1);
            mapToConstant.append(R.styleable.Transform_android_rotationX, 2);
            mapToConstant.append(R.styleable.Transform_android_rotationY, 3);
            mapToConstant.append(R.styleable.Transform_android_scaleX, 4);
            mapToConstant.append(R.styleable.Transform_android_scaleY, 5);
            mapToConstant.append(R.styleable.Transform_android_transformPivotX, 6);
            mapToConstant.append(R.styleable.Transform_android_transformPivotY, 7);
            mapToConstant.append(R.styleable.Transform_android_translationX, 8);
            mapToConstant.append(R.styleable.Transform_android_translationY, 9);
            mapToConstant.append(R.styleable.Transform_android_translationZ, 10);
            mapToConstant.append(R.styleable.Transform_android_elevation, 11);
            mapToConstant.append(R.styleable.Transform_transformPivotTarget, 12);
        }

        /* access modifiers changed from: package-private */
        public void fillFromAttributeList(Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Transform);
            this.mApply = true;
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                switch (mapToConstant.get(index)) {
                    case 1:
                        this.rotation = obtainStyledAttributes.getFloat(index, this.rotation);
                        break;
                    case 2:
                        this.rotationX = obtainStyledAttributes.getFloat(index, this.rotationX);
                        break;
                    case 3:
                        this.rotationY = obtainStyledAttributes.getFloat(index, this.rotationY);
                        break;
                    case 4:
                        this.scaleX = obtainStyledAttributes.getFloat(index, this.scaleX);
                        break;
                    case 5:
                        this.scaleY = obtainStyledAttributes.getFloat(index, this.scaleY);
                        break;
                    case 6:
                        this.transformPivotX = obtainStyledAttributes.getDimension(index, this.transformPivotX);
                        break;
                    case 7:
                        this.transformPivotY = obtainStyledAttributes.getDimension(index, this.transformPivotY);
                        break;
                    case 8:
                        this.translationX = obtainStyledAttributes.getDimension(index, this.translationX);
                        break;
                    case 9:
                        this.translationY = obtainStyledAttributes.getDimension(index, this.translationY);
                        break;
                    case 10:
                        if (Build.VERSION.SDK_INT < 21) {
                            break;
                        } else {
                            this.translationZ = obtainStyledAttributes.getDimension(index, this.translationZ);
                            break;
                        }
                    case 11:
                        if (Build.VERSION.SDK_INT < 21) {
                            break;
                        } else {
                            this.applyElevation = true;
                            this.elevation = obtainStyledAttributes.getDimension(index, this.elevation);
                            break;
                        }
                    case 12:
                        this.transformPivotTarget = ConstraintSet.lookupID(obtainStyledAttributes, index, this.transformPivotTarget);
                        break;
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    public static class PropertySet {
        public float alpha = 1.0f;
        public boolean mApply = false;
        public float mProgress = Float.NaN;
        public int mVisibilityMode = 0;
        public int visibility = 0;

        public void copyFrom(PropertySet propertySet) {
            this.mApply = propertySet.mApply;
            this.visibility = propertySet.visibility;
            this.alpha = propertySet.alpha;
            this.mProgress = propertySet.mProgress;
            this.mVisibilityMode = propertySet.mVisibilityMode;
        }

        /* access modifiers changed from: package-private */
        public void fillFromAttributeList(Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PropertySet);
            this.mApply = true;
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == R.styleable.PropertySet_android_alpha) {
                    this.alpha = obtainStyledAttributes.getFloat(index, this.alpha);
                } else if (index == R.styleable.PropertySet_android_visibility) {
                    this.visibility = obtainStyledAttributes.getInt(index, this.visibility);
                    this.visibility = ConstraintSet.VISIBILITY_FLAGS[this.visibility];
                } else if (index == R.styleable.PropertySet_visibilityMode) {
                    this.mVisibilityMode = obtainStyledAttributes.getInt(index, this.mVisibilityMode);
                } else if (index == R.styleable.PropertySet_motionProgress) {
                    this.mProgress = obtainStyledAttributes.getFloat(index, this.mProgress);
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    public static class Motion {
        private static final int ANIMATE_CIRCLE_ANGLE_TO = 6;
        private static final int ANIMATE_RELATIVE_TO = 5;
        private static final int INTERPOLATOR_REFERENCE_ID = -2;
        private static final int INTERPOLATOR_UNDEFINED = -3;
        private static final int MOTION_DRAW_PATH = 4;
        private static final int MOTION_STAGGER = 7;
        private static final int PATH_MOTION_ARC = 2;
        private static final int QUANTIZE_MOTION_INTERPOLATOR = 10;
        private static final int QUANTIZE_MOTION_PHASE = 9;
        private static final int QUANTIZE_MOTION_STEPS = 8;
        private static final int SPLINE_STRING = -1;
        private static final int TRANSITION_EASING = 3;
        private static final int TRANSITION_PATH_ROTATE = 1;
        private static SparseIntArray mapToConstant;
        public int mAnimateCircleAngleTo = 0;
        public int mAnimateRelativeTo = -1;
        public boolean mApply = false;
        public int mDrawPath = 0;
        public float mMotionStagger = Float.NaN;
        public int mPathMotionArc = -1;
        public float mPathRotate = Float.NaN;
        public int mPolarRelativeTo = -1;
        public int mQuantizeInterpolatorID = -1;
        public String mQuantizeInterpolatorString = null;
        public int mQuantizeInterpolatorType = -3;
        public float mQuantizeMotionPhase = Float.NaN;
        public int mQuantizeMotionSteps = -1;
        public String mTransitionEasing = null;

        public void copyFrom(Motion motion) {
            this.mApply = motion.mApply;
            this.mAnimateRelativeTo = motion.mAnimateRelativeTo;
            this.mTransitionEasing = motion.mTransitionEasing;
            this.mPathMotionArc = motion.mPathMotionArc;
            this.mDrawPath = motion.mDrawPath;
            this.mPathRotate = motion.mPathRotate;
            this.mMotionStagger = motion.mMotionStagger;
            this.mPolarRelativeTo = motion.mPolarRelativeTo;
        }

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            mapToConstant = sparseIntArray;
            sparseIntArray.append(R.styleable.Motion_motionPathRotate, 1);
            mapToConstant.append(R.styleable.Motion_pathMotionArc, 2);
            mapToConstant.append(R.styleable.Motion_transitionEasing, 3);
            mapToConstant.append(R.styleable.Motion_drawPath, 4);
            mapToConstant.append(R.styleable.Motion_animateRelativeTo, 5);
            mapToConstant.append(R.styleable.Motion_animateCircleAngleTo, 6);
            mapToConstant.append(R.styleable.Motion_motionStagger, 7);
            mapToConstant.append(R.styleable.Motion_quantizeMotionSteps, 8);
            mapToConstant.append(R.styleable.Motion_quantizeMotionPhase, 9);
            mapToConstant.append(R.styleable.Motion_quantizeMotionInterpolator, 10);
        }

        /* access modifiers changed from: package-private */
        public void fillFromAttributeList(Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Motion);
            this.mApply = true;
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                switch (mapToConstant.get(index)) {
                    case 1:
                        this.mPathRotate = obtainStyledAttributes.getFloat(index, this.mPathRotate);
                        break;
                    case 2:
                        this.mPathMotionArc = obtainStyledAttributes.getInt(index, this.mPathMotionArc);
                        break;
                    case 3:
                        if (obtainStyledAttributes.peekValue(index).type != 3) {
                            this.mTransitionEasing = Easing.NAMED_EASING[obtainStyledAttributes.getInteger(index, 0)];
                            break;
                        } else {
                            this.mTransitionEasing = obtainStyledAttributes.getString(index);
                            break;
                        }
                    case 4:
                        this.mDrawPath = obtainStyledAttributes.getInt(index, 0);
                        break;
                    case 5:
                        this.mAnimateRelativeTo = ConstraintSet.lookupID(obtainStyledAttributes, index, this.mAnimateRelativeTo);
                        break;
                    case 6:
                        this.mAnimateCircleAngleTo = obtainStyledAttributes.getInteger(index, this.mAnimateCircleAngleTo);
                        break;
                    case 7:
                        this.mMotionStagger = obtainStyledAttributes.getFloat(index, this.mMotionStagger);
                        break;
                    case 8:
                        this.mQuantizeMotionSteps = obtainStyledAttributes.getInteger(index, this.mQuantizeMotionSteps);
                        break;
                    case 9:
                        this.mQuantizeMotionPhase = obtainStyledAttributes.getFloat(index, this.mQuantizeMotionPhase);
                        break;
                    case 10:
                        TypedValue peekValue = obtainStyledAttributes.peekValue(index);
                        if (peekValue.type != 1) {
                            if (peekValue.type != 3) {
                                this.mQuantizeInterpolatorType = obtainStyledAttributes.getInteger(index, this.mQuantizeInterpolatorID);
                                break;
                            } else {
                                String string = obtainStyledAttributes.getString(index);
                                this.mQuantizeInterpolatorString = string;
                                if (string.indexOf("/") <= 0) {
                                    this.mQuantizeInterpolatorType = -1;
                                    break;
                                } else {
                                    this.mQuantizeInterpolatorID = obtainStyledAttributes.getResourceId(index, -1);
                                    this.mQuantizeInterpolatorType = -2;
                                    break;
                                }
                            }
                        } else {
                            int resourceId = obtainStyledAttributes.getResourceId(index, -1);
                            this.mQuantizeInterpolatorID = resourceId;
                            if (resourceId == -1) {
                                break;
                            } else {
                                this.mQuantizeInterpolatorType = -2;
                                break;
                            }
                        }
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    public static class Constraint {
        public final Layout layout = new Layout();
        public HashMap<String, ConstraintAttribute> mCustomConstraints = new HashMap<>();
        Delta mDelta;
        String mTargetString;
        int mViewId;
        public final Motion motion = new Motion();
        public final PropertySet propertySet = new PropertySet();
        public final Transform transform = new Transform();

        static class Delta {
            private static final int INITIAL_BOOLEAN = 4;
            private static final int INITIAL_FLOAT = 10;
            private static final int INITIAL_INT = 10;
            private static final int INITIAL_STRING = 5;
            int mCountBoolean = 0;
            int mCountFloat = 0;
            int mCountInt = 0;
            int mCountString = 0;
            int[] mTypeBoolean = new int[4];
            int[] mTypeFloat = new int[10];
            int[] mTypeInt = new int[10];
            int[] mTypeString = new int[5];
            boolean[] mValueBoolean = new boolean[4];
            float[] mValueFloat = new float[10];
            int[] mValueInt = new int[10];
            String[] mValueString = new String[5];

            Delta() {
            }

            /* access modifiers changed from: package-private */
            public void add(int i, int i2) {
                int i3 = this.mCountInt;
                int[] iArr = this.mTypeInt;
                if (i3 >= iArr.length) {
                    this.mTypeInt = Arrays.copyOf(iArr, iArr.length * 2);
                    int[] iArr2 = this.mValueInt;
                    this.mValueInt = Arrays.copyOf(iArr2, iArr2.length * 2);
                }
                int[] iArr3 = this.mTypeInt;
                int i4 = this.mCountInt;
                iArr3[i4] = i;
                int[] iArr4 = this.mValueInt;
                this.mCountInt = i4 + 1;
                iArr4[i4] = i2;
            }

            /* access modifiers changed from: package-private */
            public void add(int i, float f) {
                int i2 = this.mCountFloat;
                int[] iArr = this.mTypeFloat;
                if (i2 >= iArr.length) {
                    this.mTypeFloat = Arrays.copyOf(iArr, iArr.length * 2);
                    float[] fArr = this.mValueFloat;
                    this.mValueFloat = Arrays.copyOf(fArr, fArr.length * 2);
                }
                int[] iArr2 = this.mTypeFloat;
                int i3 = this.mCountFloat;
                iArr2[i3] = i;
                float[] fArr2 = this.mValueFloat;
                this.mCountFloat = i3 + 1;
                fArr2[i3] = f;
            }

            /* access modifiers changed from: package-private */
            public void add(int i, String str) {
                int i2 = this.mCountString;
                int[] iArr = this.mTypeString;
                if (i2 >= iArr.length) {
                    this.mTypeString = Arrays.copyOf(iArr, iArr.length * 2);
                    String[] strArr = this.mValueString;
                    this.mValueString = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
                }
                int[] iArr2 = this.mTypeString;
                int i3 = this.mCountString;
                iArr2[i3] = i;
                String[] strArr2 = this.mValueString;
                this.mCountString = i3 + 1;
                strArr2[i3] = str;
            }

            /* access modifiers changed from: package-private */
            public void add(int i, boolean z) {
                int i2 = this.mCountBoolean;
                int[] iArr = this.mTypeBoolean;
                if (i2 >= iArr.length) {
                    this.mTypeBoolean = Arrays.copyOf(iArr, iArr.length * 2);
                    boolean[] zArr = this.mValueBoolean;
                    this.mValueBoolean = Arrays.copyOf(zArr, zArr.length * 2);
                }
                int[] iArr2 = this.mTypeBoolean;
                int i3 = this.mCountBoolean;
                iArr2[i3] = i;
                boolean[] zArr2 = this.mValueBoolean;
                this.mCountBoolean = i3 + 1;
                zArr2[i3] = z;
            }

            /* access modifiers changed from: package-private */
            public void applyDelta(Constraint constraint) {
                for (int i = 0; i < this.mCountInt; i++) {
                    ConstraintSet.setDeltaValue(constraint, this.mTypeInt[i], this.mValueInt[i]);
                }
                for (int i2 = 0; i2 < this.mCountFloat; i2++) {
                    ConstraintSet.setDeltaValue(constraint, this.mTypeFloat[i2], this.mValueFloat[i2]);
                }
                for (int i3 = 0; i3 < this.mCountString; i3++) {
                    ConstraintSet.setDeltaValue(constraint, this.mTypeString[i3], this.mValueString[i3]);
                }
                for (int i4 = 0; i4 < this.mCountBoolean; i4++) {
                    ConstraintSet.setDeltaValue(constraint, this.mTypeBoolean[i4], this.mValueBoolean[i4]);
                }
            }

            /* access modifiers changed from: package-private */
            public void printDelta(String str) {
                Log.v(str, "int");
                for (int i = 0; i < this.mCountInt; i++) {
                    Log.v(str, this.mTypeInt[i] + " = " + this.mValueInt[i]);
                }
                Log.v(str, TypedValues.Custom.S_FLOAT);
                for (int i2 = 0; i2 < this.mCountFloat; i2++) {
                    Log.v(str, this.mTypeFloat[i2] + " = " + this.mValueFloat[i2]);
                }
                Log.v(str, "strings");
                for (int i3 = 0; i3 < this.mCountString; i3++) {
                    Log.v(str, this.mTypeString[i3] + " = " + this.mValueString[i3]);
                }
                Log.v(str, TypedValues.Custom.S_BOOLEAN);
                for (int i4 = 0; i4 < this.mCountBoolean; i4++) {
                    Log.v(str, this.mTypeBoolean[i4] + " = " + this.mValueBoolean[i4]);
                }
            }
        }

        public void applyDelta(Constraint constraint) {
            Delta delta = this.mDelta;
            if (delta != null) {
                delta.applyDelta(constraint);
            }
        }

        public void printDelta(String str) {
            Delta delta = this.mDelta;
            if (delta != null) {
                delta.printDelta(str);
            } else {
                Log.v(str, "DELTA IS NULL");
            }
        }

        private ConstraintAttribute get(String str, ConstraintAttribute.AttributeType attributeType) {
            if (this.mCustomConstraints.containsKey(str)) {
                ConstraintAttribute constraintAttribute = this.mCustomConstraints.get(str);
                if (constraintAttribute.getType() == attributeType) {
                    return constraintAttribute;
                }
                throw new IllegalArgumentException("ConstraintAttribute is already a " + constraintAttribute.getType().name());
            }
            ConstraintAttribute constraintAttribute2 = new ConstraintAttribute(str, attributeType);
            this.mCustomConstraints.put(str, constraintAttribute2);
            return constraintAttribute2;
        }

        /* access modifiers changed from: private */
        public void setStringValue(String str, String str2) {
            get(str, ConstraintAttribute.AttributeType.STRING_TYPE).setStringValue(str2);
        }

        /* access modifiers changed from: private */
        public void setFloatValue(String str, float f) {
            get(str, ConstraintAttribute.AttributeType.FLOAT_TYPE).setFloatValue(f);
        }

        /* access modifiers changed from: private */
        public void setIntValue(String str, int i) {
            get(str, ConstraintAttribute.AttributeType.INT_TYPE).setIntValue(i);
        }

        /* access modifiers changed from: private */
        public void setColorValue(String str, int i) {
            get(str, ConstraintAttribute.AttributeType.COLOR_TYPE).setColorValue(i);
        }

        public Constraint clone() {
            Constraint constraint = new Constraint();
            constraint.layout.copyFrom(this.layout);
            constraint.motion.copyFrom(this.motion);
            constraint.propertySet.copyFrom(this.propertySet);
            constraint.transform.copyFrom(this.transform);
            constraint.mViewId = this.mViewId;
            constraint.mDelta = this.mDelta;
            return constraint;
        }

        /* access modifiers changed from: private */
        public void fillFromConstraints(ConstraintHelper constraintHelper, int i, Constraints.LayoutParams layoutParams) {
            fillFromConstraints(i, layoutParams);
            if (constraintHelper instanceof Barrier) {
                this.layout.mHelperType = 1;
                Barrier barrier = (Barrier) constraintHelper;
                this.layout.mBarrierDirection = barrier.getType();
                this.layout.mReferenceIds = barrier.getReferencedIds();
                this.layout.mBarrierMargin = barrier.getMargin();
            }
        }

        /* access modifiers changed from: private */
        public void fillFromConstraints(int i, Constraints.LayoutParams layoutParams) {
            fillFrom(i, layoutParams);
            this.propertySet.alpha = layoutParams.alpha;
            this.transform.rotation = layoutParams.rotation;
            this.transform.rotationX = layoutParams.rotationX;
            this.transform.rotationY = layoutParams.rotationY;
            this.transform.scaleX = layoutParams.scaleX;
            this.transform.scaleY = layoutParams.scaleY;
            this.transform.transformPivotX = layoutParams.transformPivotX;
            this.transform.transformPivotY = layoutParams.transformPivotY;
            this.transform.translationX = layoutParams.translationX;
            this.transform.translationY = layoutParams.translationY;
            this.transform.translationZ = layoutParams.translationZ;
            this.transform.elevation = layoutParams.elevation;
            this.transform.applyElevation = layoutParams.applyElevation;
        }

        /* access modifiers changed from: private */
        public void fillFrom(int i, ConstraintLayout.LayoutParams layoutParams) {
            this.mViewId = i;
            this.layout.leftToLeft = layoutParams.leftToLeft;
            this.layout.leftToRight = layoutParams.leftToRight;
            this.layout.rightToLeft = layoutParams.rightToLeft;
            this.layout.rightToRight = layoutParams.rightToRight;
            this.layout.topToTop = layoutParams.topToTop;
            this.layout.topToBottom = layoutParams.topToBottom;
            this.layout.bottomToTop = layoutParams.bottomToTop;
            this.layout.bottomToBottom = layoutParams.bottomToBottom;
            this.layout.baselineToBaseline = layoutParams.baselineToBaseline;
            this.layout.baselineToTop = layoutParams.baselineToTop;
            this.layout.baselineToBottom = layoutParams.baselineToBottom;
            this.layout.startToEnd = layoutParams.startToEnd;
            this.layout.startToStart = layoutParams.startToStart;
            this.layout.endToStart = layoutParams.endToStart;
            this.layout.endToEnd = layoutParams.endToEnd;
            this.layout.horizontalBias = layoutParams.horizontalBias;
            this.layout.verticalBias = layoutParams.verticalBias;
            this.layout.dimensionRatio = layoutParams.dimensionRatio;
            this.layout.circleConstraint = layoutParams.circleConstraint;
            this.layout.circleRadius = layoutParams.circleRadius;
            this.layout.circleAngle = layoutParams.circleAngle;
            this.layout.editorAbsoluteX = layoutParams.editorAbsoluteX;
            this.layout.editorAbsoluteY = layoutParams.editorAbsoluteY;
            this.layout.orientation = layoutParams.orientation;
            this.layout.guidePercent = layoutParams.guidePercent;
            this.layout.guideBegin = layoutParams.guideBegin;
            this.layout.guideEnd = layoutParams.guideEnd;
            this.layout.mWidth = layoutParams.width;
            this.layout.mHeight = layoutParams.height;
            this.layout.leftMargin = layoutParams.leftMargin;
            this.layout.rightMargin = layoutParams.rightMargin;
            this.layout.topMargin = layoutParams.topMargin;
            this.layout.bottomMargin = layoutParams.bottomMargin;
            this.layout.baselineMargin = layoutParams.baselineMargin;
            this.layout.verticalWeight = layoutParams.verticalWeight;
            this.layout.horizontalWeight = layoutParams.horizontalWeight;
            this.layout.verticalChainStyle = layoutParams.verticalChainStyle;
            this.layout.horizontalChainStyle = layoutParams.horizontalChainStyle;
            this.layout.constrainedWidth = layoutParams.constrainedWidth;
            this.layout.constrainedHeight = layoutParams.constrainedHeight;
            this.layout.widthDefault = layoutParams.matchConstraintDefaultWidth;
            this.layout.heightDefault = layoutParams.matchConstraintDefaultHeight;
            this.layout.widthMax = layoutParams.matchConstraintMaxWidth;
            this.layout.heightMax = layoutParams.matchConstraintMaxHeight;
            this.layout.widthMin = layoutParams.matchConstraintMinWidth;
            this.layout.heightMin = layoutParams.matchConstraintMinHeight;
            this.layout.widthPercent = layoutParams.matchConstraintPercentWidth;
            this.layout.heightPercent = layoutParams.matchConstraintPercentHeight;
            this.layout.mConstraintTag = layoutParams.constraintTag;
            this.layout.goneTopMargin = layoutParams.goneTopMargin;
            this.layout.goneBottomMargin = layoutParams.goneBottomMargin;
            this.layout.goneLeftMargin = layoutParams.goneLeftMargin;
            this.layout.goneRightMargin = layoutParams.goneRightMargin;
            this.layout.goneStartMargin = layoutParams.goneStartMargin;
            this.layout.goneEndMargin = layoutParams.goneEndMargin;
            this.layout.goneBaselineMargin = layoutParams.goneBaselineMargin;
            this.layout.mWrapBehavior = layoutParams.wrapBehaviorInParent;
            if (Build.VERSION.SDK_INT >= 17) {
                this.layout.endMargin = layoutParams.getMarginEnd();
                this.layout.startMargin = layoutParams.getMarginStart();
            }
        }

        public void applyTo(ConstraintLayout.LayoutParams layoutParams) {
            layoutParams.leftToLeft = this.layout.leftToLeft;
            layoutParams.leftToRight = this.layout.leftToRight;
            layoutParams.rightToLeft = this.layout.rightToLeft;
            layoutParams.rightToRight = this.layout.rightToRight;
            layoutParams.topToTop = this.layout.topToTop;
            layoutParams.topToBottom = this.layout.topToBottom;
            layoutParams.bottomToTop = this.layout.bottomToTop;
            layoutParams.bottomToBottom = this.layout.bottomToBottom;
            layoutParams.baselineToBaseline = this.layout.baselineToBaseline;
            layoutParams.baselineToTop = this.layout.baselineToTop;
            layoutParams.baselineToBottom = this.layout.baselineToBottom;
            layoutParams.startToEnd = this.layout.startToEnd;
            layoutParams.startToStart = this.layout.startToStart;
            layoutParams.endToStart = this.layout.endToStart;
            layoutParams.endToEnd = this.layout.endToEnd;
            layoutParams.leftMargin = this.layout.leftMargin;
            layoutParams.rightMargin = this.layout.rightMargin;
            layoutParams.topMargin = this.layout.topMargin;
            layoutParams.bottomMargin = this.layout.bottomMargin;
            layoutParams.goneStartMargin = this.layout.goneStartMargin;
            layoutParams.goneEndMargin = this.layout.goneEndMargin;
            layoutParams.goneTopMargin = this.layout.goneTopMargin;
            layoutParams.goneBottomMargin = this.layout.goneBottomMargin;
            layoutParams.horizontalBias = this.layout.horizontalBias;
            layoutParams.verticalBias = this.layout.verticalBias;
            layoutParams.circleConstraint = this.layout.circleConstraint;
            layoutParams.circleRadius = this.layout.circleRadius;
            layoutParams.circleAngle = this.layout.circleAngle;
            layoutParams.dimensionRatio = this.layout.dimensionRatio;
            layoutParams.editorAbsoluteX = this.layout.editorAbsoluteX;
            layoutParams.editorAbsoluteY = this.layout.editorAbsoluteY;
            layoutParams.verticalWeight = this.layout.verticalWeight;
            layoutParams.horizontalWeight = this.layout.horizontalWeight;
            layoutParams.verticalChainStyle = this.layout.verticalChainStyle;
            layoutParams.horizontalChainStyle = this.layout.horizontalChainStyle;
            layoutParams.constrainedWidth = this.layout.constrainedWidth;
            layoutParams.constrainedHeight = this.layout.constrainedHeight;
            layoutParams.matchConstraintDefaultWidth = this.layout.widthDefault;
            layoutParams.matchConstraintDefaultHeight = this.layout.heightDefault;
            layoutParams.matchConstraintMaxWidth = this.layout.widthMax;
            layoutParams.matchConstraintMaxHeight = this.layout.heightMax;
            layoutParams.matchConstraintMinWidth = this.layout.widthMin;
            layoutParams.matchConstraintMinHeight = this.layout.heightMin;
            layoutParams.matchConstraintPercentWidth = this.layout.widthPercent;
            layoutParams.matchConstraintPercentHeight = this.layout.heightPercent;
            layoutParams.orientation = this.layout.orientation;
            layoutParams.guidePercent = this.layout.guidePercent;
            layoutParams.guideBegin = this.layout.guideBegin;
            layoutParams.guideEnd = this.layout.guideEnd;
            layoutParams.width = this.layout.mWidth;
            layoutParams.height = this.layout.mHeight;
            if (this.layout.mConstraintTag != null) {
                layoutParams.constraintTag = this.layout.mConstraintTag;
            }
            layoutParams.wrapBehaviorInParent = this.layout.mWrapBehavior;
            if (Build.VERSION.SDK_INT >= 17) {
                layoutParams.setMarginStart(this.layout.startMargin);
                layoutParams.setMarginEnd(this.layout.endMargin);
            }
            layoutParams.validate();
        }
    }

    public void clone(Context context, int i) {
        clone((ConstraintLayout) LayoutInflater.from(context).inflate(i, (ViewGroup) null));
    }

    public void clone(ConstraintSet constraintSet) {
        this.mConstraints.clear();
        for (Integer next : constraintSet.mConstraints.keySet()) {
            Constraint constraint = constraintSet.mConstraints.get(next);
            if (constraint != null) {
                this.mConstraints.put(next, constraint.clone());
            }
        }
    }

    public void clone(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        this.mConstraints.clear();
        int i = 0;
        while (i < childCount) {
            View childAt = constraintLayout.getChildAt(i);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) childAt.getLayoutParams();
            int id = childAt.getId();
            if (!this.mForceId || id != -1) {
                if (!this.mConstraints.containsKey(Integer.valueOf(id))) {
                    this.mConstraints.put(Integer.valueOf(id), new Constraint());
                }
                Constraint constraint = this.mConstraints.get(Integer.valueOf(id));
                if (constraint != null) {
                    constraint.mCustomConstraints = ConstraintAttribute.extractAttributes(this.mSavedAttributes, childAt);
                    constraint.fillFrom(id, layoutParams);
                    constraint.propertySet.visibility = childAt.getVisibility();
                    if (Build.VERSION.SDK_INT >= 17) {
                        constraint.propertySet.alpha = childAt.getAlpha();
                        constraint.transform.rotation = childAt.getRotation();
                        constraint.transform.rotationX = childAt.getRotationX();
                        constraint.transform.rotationY = childAt.getRotationY();
                        constraint.transform.scaleX = childAt.getScaleX();
                        constraint.transform.scaleY = childAt.getScaleY();
                        float pivotX = childAt.getPivotX();
                        float pivotY = childAt.getPivotY();
                        if (!(((double) pivotX) == 0.0d && ((double) pivotY) == 0.0d)) {
                            constraint.transform.transformPivotX = pivotX;
                            constraint.transform.transformPivotY = pivotY;
                        }
                        constraint.transform.translationX = childAt.getTranslationX();
                        constraint.transform.translationY = childAt.getTranslationY();
                        if (Build.VERSION.SDK_INT >= 21) {
                            constraint.transform.translationZ = childAt.getTranslationZ();
                            if (constraint.transform.applyElevation) {
                                constraint.transform.elevation = childAt.getElevation();
                            }
                        }
                    }
                    if (childAt instanceof Barrier) {
                        Barrier barrier = (Barrier) childAt;
                        constraint.layout.mBarrierAllowsGoneWidgets = barrier.getAllowsGoneWidget();
                        constraint.layout.mReferenceIds = barrier.getReferencedIds();
                        constraint.layout.mBarrierDirection = barrier.getType();
                        constraint.layout.mBarrierMargin = barrier.getMargin();
                    }
                }
                i++;
            } else {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
        }
    }

    public void clone(Constraints constraints) {
        int childCount = constraints.getChildCount();
        this.mConstraints.clear();
        int i = 0;
        while (i < childCount) {
            View childAt = constraints.getChildAt(i);
            Constraints.LayoutParams layoutParams = (Constraints.LayoutParams) childAt.getLayoutParams();
            int id = childAt.getId();
            if (!this.mForceId || id != -1) {
                if (!this.mConstraints.containsKey(Integer.valueOf(id))) {
                    this.mConstraints.put(Integer.valueOf(id), new Constraint());
                }
                Constraint constraint = this.mConstraints.get(Integer.valueOf(id));
                if (constraint != null) {
                    if (childAt instanceof ConstraintHelper) {
                        constraint.fillFromConstraints((ConstraintHelper) childAt, id, layoutParams);
                    }
                    constraint.fillFromConstraints(id, layoutParams);
                }
                i++;
            } else {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
        }
    }

    public void applyTo(ConstraintLayout constraintLayout) {
        applyToInternal(constraintLayout, true);
        constraintLayout.setConstraintSet((ConstraintSet) null);
        constraintLayout.requestLayout();
    }

    public void applyToWithoutCustom(ConstraintLayout constraintLayout) {
        applyToInternal(constraintLayout, false);
        constraintLayout.setConstraintSet((ConstraintSet) null);
    }

    public void applyCustomAttributes(ConstraintLayout constraintLayout) {
        Constraint constraint;
        int childCount = constraintLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            int id = childAt.getId();
            if (!this.mConstraints.containsKey(Integer.valueOf(id))) {
                Log.w(TAG, "id unknown " + Debug.getName(childAt));
            } else if (this.mForceId && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            } else if (this.mConstraints.containsKey(Integer.valueOf(id)) && (constraint = this.mConstraints.get(Integer.valueOf(id))) != null) {
                ConstraintAttribute.setAttributes(childAt, constraint.mCustomConstraints);
            }
        }
    }

    public void applyToHelper(ConstraintHelper constraintHelper, ConstraintWidget constraintWidget, ConstraintLayout.LayoutParams layoutParams, SparseArray<ConstraintWidget> sparseArray) {
        Constraint constraint;
        int id = constraintHelper.getId();
        if (this.mConstraints.containsKey(Integer.valueOf(id)) && (constraint = this.mConstraints.get(Integer.valueOf(id))) != null && (constraintWidget instanceof HelperWidget)) {
            constraintHelper.loadParameters(constraint, (HelperWidget) constraintWidget, layoutParams, sparseArray);
        }
    }

    public void applyToLayoutParams(int i, ConstraintLayout.LayoutParams layoutParams) {
        Constraint constraint;
        if (this.mConstraints.containsKey(Integer.valueOf(i)) && (constraint = this.mConstraints.get(Integer.valueOf(i))) != null) {
            constraint.applyTo(layoutParams);
        }
    }

    /* access modifiers changed from: package-private */
    public void applyToInternal(ConstraintLayout constraintLayout, boolean z) {
        int childCount = constraintLayout.getChildCount();
        HashSet hashSet = new HashSet(this.mConstraints.keySet());
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            int id = childAt.getId();
            if (!this.mConstraints.containsKey(Integer.valueOf(id))) {
                Log.w(TAG, "id unknown " + Debug.getName(childAt));
            } else if (this.mForceId && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            } else if (id != -1) {
                if (this.mConstraints.containsKey(Integer.valueOf(id))) {
                    hashSet.remove(Integer.valueOf(id));
                    Constraint constraint = this.mConstraints.get(Integer.valueOf(id));
                    if (constraint != null) {
                        if (childAt instanceof Barrier) {
                            constraint.layout.mHelperType = 1;
                            Barrier barrier = (Barrier) childAt;
                            barrier.setId(id);
                            barrier.setType(constraint.layout.mBarrierDirection);
                            barrier.setMargin(constraint.layout.mBarrierMargin);
                            barrier.setAllowsGoneWidget(constraint.layout.mBarrierAllowsGoneWidgets);
                            if (constraint.layout.mReferenceIds != null) {
                                barrier.setReferencedIds(constraint.layout.mReferenceIds);
                            } else if (constraint.layout.mReferenceIdString != null) {
                                constraint.layout.mReferenceIds = convertReferenceString(barrier, constraint.layout.mReferenceIdString);
                                barrier.setReferencedIds(constraint.layout.mReferenceIds);
                            }
                        }
                        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) childAt.getLayoutParams();
                        layoutParams.validate();
                        constraint.applyTo(layoutParams);
                        if (z) {
                            ConstraintAttribute.setAttributes(childAt, constraint.mCustomConstraints);
                        }
                        childAt.setLayoutParams(layoutParams);
                        if (constraint.propertySet.mVisibilityMode == 0) {
                            childAt.setVisibility(constraint.propertySet.visibility);
                        }
                        if (Build.VERSION.SDK_INT >= 17) {
                            childAt.setAlpha(constraint.propertySet.alpha);
                            childAt.setRotation(constraint.transform.rotation);
                            childAt.setRotationX(constraint.transform.rotationX);
                            childAt.setRotationY(constraint.transform.rotationY);
                            childAt.setScaleX(constraint.transform.scaleX);
                            childAt.setScaleY(constraint.transform.scaleY);
                            if (constraint.transform.transformPivotTarget != -1) {
                                View findViewById = ((View) childAt.getParent()).findViewById(constraint.transform.transformPivotTarget);
                                if (findViewById != null) {
                                    float top = ((float) (findViewById.getTop() + findViewById.getBottom())) / 2.0f;
                                    float left = ((float) (findViewById.getLeft() + findViewById.getRight())) / 2.0f;
                                    if (childAt.getRight() - childAt.getLeft() > 0 && childAt.getBottom() - childAt.getTop() > 0) {
                                        childAt.setPivotX(left - ((float) childAt.getLeft()));
                                        childAt.setPivotY(top - ((float) childAt.getTop()));
                                    }
                                }
                            } else {
                                if (!Float.isNaN(constraint.transform.transformPivotX)) {
                                    childAt.setPivotX(constraint.transform.transformPivotX);
                                }
                                if (!Float.isNaN(constraint.transform.transformPivotY)) {
                                    childAt.setPivotY(constraint.transform.transformPivotY);
                                }
                            }
                            childAt.setTranslationX(constraint.transform.translationX);
                            childAt.setTranslationY(constraint.transform.translationY);
                            if (Build.VERSION.SDK_INT >= 21) {
                                childAt.setTranslationZ(constraint.transform.translationZ);
                                if (constraint.transform.applyElevation) {
                                    childAt.setElevation(constraint.transform.elevation);
                                }
                            }
                        }
                    }
                } else {
                    Log.v(TAG, "WARNING NO CONSTRAINTS for view " + id);
                }
            }
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            Integer num = (Integer) it.next();
            Constraint constraint2 = this.mConstraints.get(num);
            if (constraint2 != null) {
                if (constraint2.layout.mHelperType == 1) {
                    Barrier barrier2 = new Barrier(constraintLayout.getContext());
                    barrier2.setId(num.intValue());
                    if (constraint2.layout.mReferenceIds != null) {
                        barrier2.setReferencedIds(constraint2.layout.mReferenceIds);
                    } else if (constraint2.layout.mReferenceIdString != null) {
                        constraint2.layout.mReferenceIds = convertReferenceString(barrier2, constraint2.layout.mReferenceIdString);
                        barrier2.setReferencedIds(constraint2.layout.mReferenceIds);
                    }
                    barrier2.setType(constraint2.layout.mBarrierDirection);
                    barrier2.setMargin(constraint2.layout.mBarrierMargin);
                    ConstraintLayout.LayoutParams generateDefaultLayoutParams = constraintLayout.generateDefaultLayoutParams();
                    barrier2.validateParams();
                    constraint2.applyTo(generateDefaultLayoutParams);
                    constraintLayout.addView(barrier2, generateDefaultLayoutParams);
                }
                if (constraint2.layout.mIsGuideline) {
                    Guideline guideline = new Guideline(constraintLayout.getContext());
                    guideline.setId(num.intValue());
                    ConstraintLayout.LayoutParams generateDefaultLayoutParams2 = constraintLayout.generateDefaultLayoutParams();
                    constraint2.applyTo(generateDefaultLayoutParams2);
                    constraintLayout.addView(guideline, generateDefaultLayoutParams2);
                }
            }
        }
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt2 = constraintLayout.getChildAt(i2);
            if (childAt2 instanceof ConstraintHelper) {
                ((ConstraintHelper) childAt2).applyLayoutFeaturesInConstraintSet(constraintLayout);
            }
        }
    }

    public void center(int i, int i2, int i3, int i4, int i5, int i6, int i7, float f) {
        int i8 = i3;
        float f2 = f;
        if (i4 < 0) {
            throw new IllegalArgumentException("margin must be > 0");
        } else if (i7 < 0) {
            throw new IllegalArgumentException("margin must be > 0");
        } else if (f2 <= 0.0f || f2 > 1.0f) {
            throw new IllegalArgumentException("bias must be between 0 and 1 inclusive");
        } else if (i8 == 1 || i8 == 2) {
            int i9 = i;
            connect(i9, 1, i2, i3, i4);
            connect(i9, 2, i5, i6, i7);
            Constraint constraint = this.mConstraints.get(Integer.valueOf(i));
            if (constraint != null) {
                constraint.layout.horizontalBias = f2;
            }
        } else if (i8 == 6 || i8 == 7) {
            int i10 = i;
            connect(i10, 6, i2, i3, i4);
            connect(i10, 7, i5, i6, i7);
            Constraint constraint2 = this.mConstraints.get(Integer.valueOf(i));
            if (constraint2 != null) {
                constraint2.layout.horizontalBias = f2;
            }
        } else {
            int i11 = i;
            connect(i11, 3, i2, i3, i4);
            connect(i11, 4, i5, i6, i7);
            Constraint constraint3 = this.mConstraints.get(Integer.valueOf(i));
            if (constraint3 != null) {
                constraint3.layout.verticalBias = f2;
            }
        }
    }

    public void centerHorizontally(int i, int i2, int i3, int i4, int i5, int i6, int i7, float f) {
        connect(i, 1, i2, i3, i4);
        connect(i, 2, i5, i6, i7);
        Constraint constraint = this.mConstraints.get(Integer.valueOf(i));
        if (constraint != null) {
            constraint.layout.horizontalBias = f;
        }
    }

    public void centerHorizontallyRtl(int i, int i2, int i3, int i4, int i5, int i6, int i7, float f) {
        connect(i, 6, i2, i3, i4);
        connect(i, 7, i5, i6, i7);
        Constraint constraint = this.mConstraints.get(Integer.valueOf(i));
        if (constraint != null) {
            constraint.layout.horizontalBias = f;
        }
    }

    public void centerVertically(int i, int i2, int i3, int i4, int i5, int i6, int i7, float f) {
        connect(i, 3, i2, i3, i4);
        connect(i, 4, i5, i6, i7);
        Constraint constraint = this.mConstraints.get(Integer.valueOf(i));
        if (constraint != null) {
            constraint.layout.verticalBias = f;
        }
    }

    public void createVerticalChain(int i, int i2, int i3, int i4, int[] iArr, float[] fArr, int i5) {
        int[] iArr2 = iArr;
        float[] fArr2 = fArr;
        if (iArr2.length < 2) {
            throw new IllegalArgumentException("must have 2 or more widgets in a chain");
        } else if (fArr2 == null || fArr2.length == iArr2.length) {
            if (fArr2 != null) {
                get(iArr2[0]).layout.verticalWeight = fArr2[0];
            }
            get(iArr2[0]).layout.verticalChainStyle = i5;
            connect(iArr2[0], 3, i, i2, 0);
            for (int i6 = 1; i6 < iArr2.length; i6++) {
                int i7 = iArr2[i6];
                int i8 = i6 - 1;
                connect(iArr2[i6], 3, iArr2[i8], 4, 0);
                connect(iArr2[i8], 4, iArr2[i6], 3, 0);
                if (fArr2 != null) {
                    get(iArr2[i6]).layout.verticalWeight = fArr2[i6];
                }
            }
            connect(iArr2[iArr2.length - 1], 4, i3, i4, 0);
        } else {
            throw new IllegalArgumentException("must have 2 or more widgets in a chain");
        }
    }

    public void createHorizontalChain(int i, int i2, int i3, int i4, int[] iArr, float[] fArr, int i5) {
        createHorizontalChain(i, i2, i3, i4, iArr, fArr, i5, 1, 2);
    }

    public void createHorizontalChainRtl(int i, int i2, int i3, int i4, int[] iArr, float[] fArr, int i5) {
        createHorizontalChain(i, i2, i3, i4, iArr, fArr, i5, 6, 7);
    }

    private void createHorizontalChain(int i, int i2, int i3, int i4, int[] iArr, float[] fArr, int i5, int i6, int i7) {
        int[] iArr2 = iArr;
        float[] fArr2 = fArr;
        if (iArr2.length < 2) {
            throw new IllegalArgumentException("must have 2 or more widgets in a chain");
        } else if (fArr2 == null || fArr2.length == iArr2.length) {
            if (fArr2 != null) {
                get(iArr2[0]).layout.horizontalWeight = fArr2[0];
            }
            get(iArr2[0]).layout.horizontalChainStyle = i5;
            connect(iArr2[0], i6, i, i2, -1);
            for (int i8 = 1; i8 < iArr2.length; i8++) {
                int i9 = iArr2[i8];
                int i10 = i8 - 1;
                connect(iArr2[i8], i6, iArr2[i10], i7, -1);
                connect(iArr2[i10], i7, iArr2[i8], i6, -1);
                if (fArr2 != null) {
                    get(iArr2[i8]).layout.horizontalWeight = fArr2[i8];
                }
            }
            connect(iArr2[iArr2.length - 1], i7, i3, i4, -1);
        } else {
            throw new IllegalArgumentException("must have 2 or more widgets in a chain");
        }
    }

    public void connect(int i, int i2, int i3, int i4, int i5) {
        if (!this.mConstraints.containsKey(Integer.valueOf(i))) {
            this.mConstraints.put(Integer.valueOf(i), new Constraint());
        }
        Constraint constraint = this.mConstraints.get(Integer.valueOf(i));
        if (constraint != null) {
            switch (i2) {
                case 1:
                    if (i4 == 1) {
                        constraint.layout.leftToLeft = i3;
                        constraint.layout.leftToRight = -1;
                    } else if (i4 == 2) {
                        constraint.layout.leftToRight = i3;
                        constraint.layout.leftToLeft = -1;
                    } else {
                        throw new IllegalArgumentException("Left to " + sideToString(i4) + " undefined");
                    }
                    constraint.layout.leftMargin = i5;
                    return;
                case 2:
                    if (i4 == 1) {
                        constraint.layout.rightToLeft = i3;
                        constraint.layout.rightToRight = -1;
                    } else if (i4 == 2) {
                        constraint.layout.rightToRight = i3;
                        constraint.layout.rightToLeft = -1;
                    } else {
                        throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                    }
                    constraint.layout.rightMargin = i5;
                    return;
                case 3:
                    if (i4 == 3) {
                        constraint.layout.topToTop = i3;
                        constraint.layout.topToBottom = -1;
                        constraint.layout.baselineToBaseline = -1;
                        constraint.layout.baselineToTop = -1;
                        constraint.layout.baselineToBottom = -1;
                    } else if (i4 == 4) {
                        constraint.layout.topToBottom = i3;
                        constraint.layout.topToTop = -1;
                        constraint.layout.baselineToBaseline = -1;
                        constraint.layout.baselineToTop = -1;
                        constraint.layout.baselineToBottom = -1;
                    } else {
                        throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                    }
                    constraint.layout.topMargin = i5;
                    return;
                case 4:
                    if (i4 == 4) {
                        constraint.layout.bottomToBottom = i3;
                        constraint.layout.bottomToTop = -1;
                        constraint.layout.baselineToBaseline = -1;
                        constraint.layout.baselineToTop = -1;
                        constraint.layout.baselineToBottom = -1;
                    } else if (i4 == 3) {
                        constraint.layout.bottomToTop = i3;
                        constraint.layout.bottomToBottom = -1;
                        constraint.layout.baselineToBaseline = -1;
                        constraint.layout.baselineToTop = -1;
                        constraint.layout.baselineToBottom = -1;
                    } else {
                        throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                    }
                    constraint.layout.bottomMargin = i5;
                    return;
                case 5:
                    if (i4 == 5) {
                        constraint.layout.baselineToBaseline = i3;
                        constraint.layout.bottomToBottom = -1;
                        constraint.layout.bottomToTop = -1;
                        constraint.layout.topToTop = -1;
                        constraint.layout.topToBottom = -1;
                        return;
                    } else if (i4 == 3) {
                        constraint.layout.baselineToTop = i3;
                        constraint.layout.bottomToBottom = -1;
                        constraint.layout.bottomToTop = -1;
                        constraint.layout.topToTop = -1;
                        constraint.layout.topToBottom = -1;
                        return;
                    } else if (i4 == 4) {
                        constraint.layout.baselineToBottom = i3;
                        constraint.layout.bottomToBottom = -1;
                        constraint.layout.bottomToTop = -1;
                        constraint.layout.topToTop = -1;
                        constraint.layout.topToBottom = -1;
                        return;
                    } else {
                        throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                    }
                case 6:
                    if (i4 == 6) {
                        constraint.layout.startToStart = i3;
                        constraint.layout.startToEnd = -1;
                    } else if (i4 == 7) {
                        constraint.layout.startToEnd = i3;
                        constraint.layout.startToStart = -1;
                    } else {
                        throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                    }
                    constraint.layout.startMargin = i5;
                    return;
                case 7:
                    if (i4 == 7) {
                        constraint.layout.endToEnd = i3;
                        constraint.layout.endToStart = -1;
                    } else if (i4 == 6) {
                        constraint.layout.endToStart = i3;
                        constraint.layout.endToEnd = -1;
                    } else {
                        throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                    }
                    constraint.layout.endMargin = i5;
                    return;
                default:
                    throw new IllegalArgumentException(sideToString(i2) + " to " + sideToString(i4) + " unknown");
            }
        }
    }

    public void connect(int i, int i2, int i3, int i4) {
        if (!this.mConstraints.containsKey(Integer.valueOf(i))) {
            this.mConstraints.put(Integer.valueOf(i), new Constraint());
        }
        Constraint constraint = this.mConstraints.get(Integer.valueOf(i));
        if (constraint != null) {
            switch (i2) {
                case 1:
                    if (i4 == 1) {
                        constraint.layout.leftToLeft = i3;
                        constraint.layout.leftToRight = -1;
                        return;
                    } else if (i4 == 2) {
                        constraint.layout.leftToRight = i3;
                        constraint.layout.leftToLeft = -1;
                        return;
                    } else {
                        throw new IllegalArgumentException("left to " + sideToString(i4) + " undefined");
                    }
                case 2:
                    if (i4 == 1) {
                        constraint.layout.rightToLeft = i3;
                        constraint.layout.rightToRight = -1;
                        return;
                    } else if (i4 == 2) {
                        constraint.layout.rightToRight = i3;
                        constraint.layout.rightToLeft = -1;
                        return;
                    } else {
                        throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                    }
                case 3:
                    if (i4 == 3) {
                        constraint.layout.topToTop = i3;
                        constraint.layout.topToBottom = -1;
                        constraint.layout.baselineToBaseline = -1;
                        constraint.layout.baselineToTop = -1;
                        constraint.layout.baselineToBottom = -1;
                        return;
                    } else if (i4 == 4) {
                        constraint.layout.topToBottom = i3;
                        constraint.layout.topToTop = -1;
                        constraint.layout.baselineToBaseline = -1;
                        constraint.layout.baselineToTop = -1;
                        constraint.layout.baselineToBottom = -1;
                        return;
                    } else {
                        throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                    }
                case 4:
                    if (i4 == 4) {
                        constraint.layout.bottomToBottom = i3;
                        constraint.layout.bottomToTop = -1;
                        constraint.layout.baselineToBaseline = -1;
                        constraint.layout.baselineToTop = -1;
                        constraint.layout.baselineToBottom = -1;
                        return;
                    } else if (i4 == 3) {
                        constraint.layout.bottomToTop = i3;
                        constraint.layout.bottomToBottom = -1;
                        constraint.layout.baselineToBaseline = -1;
                        constraint.layout.baselineToTop = -1;
                        constraint.layout.baselineToBottom = -1;
                        return;
                    } else {
                        throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                    }
                case 5:
                    if (i4 == 5) {
                        constraint.layout.baselineToBaseline = i3;
                        constraint.layout.bottomToBottom = -1;
                        constraint.layout.bottomToTop = -1;
                        constraint.layout.topToTop = -1;
                        constraint.layout.topToBottom = -1;
                        return;
                    } else if (i4 == 3) {
                        constraint.layout.baselineToTop = i3;
                        constraint.layout.bottomToBottom = -1;
                        constraint.layout.bottomToTop = -1;
                        constraint.layout.topToTop = -1;
                        constraint.layout.topToBottom = -1;
                        return;
                    } else if (i4 == 4) {
                        constraint.layout.baselineToBottom = i3;
                        constraint.layout.bottomToBottom = -1;
                        constraint.layout.bottomToTop = -1;
                        constraint.layout.topToTop = -1;
                        constraint.layout.topToBottom = -1;
                        return;
                    } else {
                        throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                    }
                case 6:
                    if (i4 == 6) {
                        constraint.layout.startToStart = i3;
                        constraint.layout.startToEnd = -1;
                        return;
                    } else if (i4 == 7) {
                        constraint.layout.startToEnd = i3;
                        constraint.layout.startToStart = -1;
                        return;
                    } else {
                        throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                    }
                case 7:
                    if (i4 == 7) {
                        constraint.layout.endToEnd = i3;
                        constraint.layout.endToStart = -1;
                        return;
                    } else if (i4 == 6) {
                        constraint.layout.endToStart = i3;
                        constraint.layout.endToEnd = -1;
                        return;
                    } else {
                        throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                    }
                default:
                    throw new IllegalArgumentException(sideToString(i2) + " to " + sideToString(i4) + " unknown");
            }
        }
    }

    public void centerHorizontally(int i, int i2) {
        if (i2 == 0) {
            center(i, 0, 1, 0, 0, 2, 0, 0.5f);
        } else {
            center(i, i2, 2, 0, i2, 1, 0, 0.5f);
        }
    }

    public void centerHorizontallyRtl(int i, int i2) {
        if (i2 == 0) {
            center(i, 0, 6, 0, 0, 7, 0, 0.5f);
        } else {
            center(i, i2, 7, 0, i2, 6, 0, 0.5f);
        }
    }

    public void centerVertically(int i, int i2) {
        if (i2 == 0) {
            center(i, 0, 3, 0, 0, 4, 0, 0.5f);
        } else {
            center(i, i2, 4, 0, i2, 3, 0, 0.5f);
        }
    }

    public void clear(int i) {
        this.mConstraints.remove(Integer.valueOf(i));
    }

    public void clear(int i, int i2) {
        Constraint constraint;
        if (this.mConstraints.containsKey(Integer.valueOf(i)) && (constraint = this.mConstraints.get(Integer.valueOf(i))) != null) {
            switch (i2) {
                case 1:
                    constraint.layout.leftToRight = -1;
                    constraint.layout.leftToLeft = -1;
                    constraint.layout.leftMargin = -1;
                    constraint.layout.goneLeftMargin = Integer.MIN_VALUE;
                    return;
                case 2:
                    constraint.layout.rightToRight = -1;
                    constraint.layout.rightToLeft = -1;
                    constraint.layout.rightMargin = -1;
                    constraint.layout.goneRightMargin = Integer.MIN_VALUE;
                    return;
                case 3:
                    constraint.layout.topToBottom = -1;
                    constraint.layout.topToTop = -1;
                    constraint.layout.topMargin = 0;
                    constraint.layout.goneTopMargin = Integer.MIN_VALUE;
                    return;
                case 4:
                    constraint.layout.bottomToTop = -1;
                    constraint.layout.bottomToBottom = -1;
                    constraint.layout.bottomMargin = 0;
                    constraint.layout.goneBottomMargin = Integer.MIN_VALUE;
                    return;
                case 5:
                    constraint.layout.baselineToBaseline = -1;
                    constraint.layout.baselineToTop = -1;
                    constraint.layout.baselineToBottom = -1;
                    constraint.layout.baselineMargin = 0;
                    constraint.layout.goneBaselineMargin = Integer.MIN_VALUE;
                    return;
                case 6:
                    constraint.layout.startToEnd = -1;
                    constraint.layout.startToStart = -1;
                    constraint.layout.startMargin = 0;
                    constraint.layout.goneStartMargin = Integer.MIN_VALUE;
                    return;
                case 7:
                    constraint.layout.endToStart = -1;
                    constraint.layout.endToEnd = -1;
                    constraint.layout.endMargin = 0;
                    constraint.layout.goneEndMargin = Integer.MIN_VALUE;
                    return;
                case 8:
                    constraint.layout.circleAngle = -1.0f;
                    constraint.layout.circleRadius = -1;
                    constraint.layout.circleConstraint = -1;
                    return;
                default:
                    throw new IllegalArgumentException("unknown constraint");
            }
        }
    }

    public void setMargin(int i, int i2, int i3) {
        Constraint constraint = get(i);
        switch (i2) {
            case 1:
                constraint.layout.leftMargin = i3;
                return;
            case 2:
                constraint.layout.rightMargin = i3;
                return;
            case 3:
                constraint.layout.topMargin = i3;
                return;
            case 4:
                constraint.layout.bottomMargin = i3;
                return;
            case 5:
                constraint.layout.baselineMargin = i3;
                return;
            case 6:
                constraint.layout.startMargin = i3;
                return;
            case 7:
                constraint.layout.endMargin = i3;
                return;
            default:
                throw new IllegalArgumentException("unknown constraint");
        }
    }

    public void setGoneMargin(int i, int i2, int i3) {
        Constraint constraint = get(i);
        switch (i2) {
            case 1:
                constraint.layout.goneLeftMargin = i3;
                return;
            case 2:
                constraint.layout.goneRightMargin = i3;
                return;
            case 3:
                constraint.layout.goneTopMargin = i3;
                return;
            case 4:
                constraint.layout.goneBottomMargin = i3;
                return;
            case 5:
                constraint.layout.goneBaselineMargin = i3;
                return;
            case 6:
                constraint.layout.goneStartMargin = i3;
                return;
            case 7:
                constraint.layout.goneEndMargin = i3;
                return;
            default:
                throw new IllegalArgumentException("unknown constraint");
        }
    }

    public void setHorizontalBias(int i, float f) {
        get(i).layout.horizontalBias = f;
    }

    public void setVerticalBias(int i, float f) {
        get(i).layout.verticalBias = f;
    }

    public void setDimensionRatio(int i, String str) {
        get(i).layout.dimensionRatio = str;
    }

    public void setVisibility(int i, int i2) {
        get(i).propertySet.visibility = i2;
    }

    public void setVisibilityMode(int i, int i2) {
        get(i).propertySet.mVisibilityMode = i2;
    }

    public int getVisibilityMode(int i) {
        return get(i).propertySet.mVisibilityMode;
    }

    public int getVisibility(int i) {
        return get(i).propertySet.visibility;
    }

    public int getHeight(int i) {
        return get(i).layout.mHeight;
    }

    public int getWidth(int i) {
        return get(i).layout.mWidth;
    }

    public void setAlpha(int i, float f) {
        get(i).propertySet.alpha = f;
    }

    public boolean getApplyElevation(int i) {
        return get(i).transform.applyElevation;
    }

    public void setApplyElevation(int i, boolean z) {
        if (Build.VERSION.SDK_INT >= 21) {
            get(i).transform.applyElevation = z;
        }
    }

    public void setElevation(int i, float f) {
        if (Build.VERSION.SDK_INT >= 21) {
            get(i).transform.elevation = f;
            get(i).transform.applyElevation = true;
        }
    }

    public void setRotation(int i, float f) {
        get(i).transform.rotation = f;
    }

    public void setRotationX(int i, float f) {
        get(i).transform.rotationX = f;
    }

    public void setRotationY(int i, float f) {
        get(i).transform.rotationY = f;
    }

    public void setScaleX(int i, float f) {
        get(i).transform.scaleX = f;
    }

    public void setScaleY(int i, float f) {
        get(i).transform.scaleY = f;
    }

    public void setTransformPivotX(int i, float f) {
        get(i).transform.transformPivotX = f;
    }

    public void setTransformPivotY(int i, float f) {
        get(i).transform.transformPivotY = f;
    }

    public void setTransformPivot(int i, float f, float f2) {
        Constraint constraint = get(i);
        constraint.transform.transformPivotY = f2;
        constraint.transform.transformPivotX = f;
    }

    public void setTranslationX(int i, float f) {
        get(i).transform.translationX = f;
    }

    public void setTranslationY(int i, float f) {
        get(i).transform.translationY = f;
    }

    public void setTranslation(int i, float f, float f2) {
        Constraint constraint = get(i);
        constraint.transform.translationX = f;
        constraint.transform.translationY = f2;
    }

    public void setTranslationZ(int i, float f) {
        if (Build.VERSION.SDK_INT >= 21) {
            get(i).transform.translationZ = f;
        }
    }

    public void setEditorAbsoluteX(int i, int i2) {
        get(i).layout.editorAbsoluteX = i2;
    }

    public void setEditorAbsoluteY(int i, int i2) {
        get(i).layout.editorAbsoluteY = i2;
    }

    public void setLayoutWrapBehavior(int i, int i2) {
        if (i2 >= 0 && i2 <= 3) {
            get(i).layout.mWrapBehavior = i2;
        }
    }

    public void constrainHeight(int i, int i2) {
        get(i).layout.mHeight = i2;
    }

    public void constrainWidth(int i, int i2) {
        get(i).layout.mWidth = i2;
    }

    public void constrainCircle(int i, int i2, int i3, float f) {
        Constraint constraint = get(i);
        constraint.layout.circleConstraint = i2;
        constraint.layout.circleRadius = i3;
        constraint.layout.circleAngle = f;
    }

    public void constrainMaxHeight(int i, int i2) {
        get(i).layout.heightMax = i2;
    }

    public void constrainMaxWidth(int i, int i2) {
        get(i).layout.widthMax = i2;
    }

    public void constrainMinHeight(int i, int i2) {
        get(i).layout.heightMin = i2;
    }

    public void constrainMinWidth(int i, int i2) {
        get(i).layout.widthMin = i2;
    }

    public void constrainPercentWidth(int i, float f) {
        get(i).layout.widthPercent = f;
    }

    public void constrainPercentHeight(int i, float f) {
        get(i).layout.heightPercent = f;
    }

    public void constrainDefaultHeight(int i, int i2) {
        get(i).layout.heightDefault = i2;
    }

    public void constrainedWidth(int i, boolean z) {
        get(i).layout.constrainedWidth = z;
    }

    public void constrainedHeight(int i, boolean z) {
        get(i).layout.constrainedHeight = z;
    }

    public void constrainDefaultWidth(int i, int i2) {
        get(i).layout.widthDefault = i2;
    }

    public void setHorizontalWeight(int i, float f) {
        get(i).layout.horizontalWeight = f;
    }

    public void setVerticalWeight(int i, float f) {
        get(i).layout.verticalWeight = f;
    }

    public void setHorizontalChainStyle(int i, int i2) {
        get(i).layout.horizontalChainStyle = i2;
    }

    public void setVerticalChainStyle(int i, int i2) {
        get(i).layout.verticalChainStyle = i2;
    }

    public void addToHorizontalChain(int i, int i2, int i3) {
        connect(i, 1, i2, i2 == 0 ? 1 : 2, 0);
        connect(i, 2, i3, i3 == 0 ? 2 : 1, 0);
        if (i2 != 0) {
            connect(i2, 2, i, 1, 0);
        }
        if (i3 != 0) {
            connect(i3, 1, i, 2, 0);
        }
    }

    public void addToHorizontalChainRTL(int i, int i2, int i3) {
        connect(i, 6, i2, i2 == 0 ? 6 : 7, 0);
        connect(i, 7, i3, i3 == 0 ? 7 : 6, 0);
        if (i2 != 0) {
            connect(i2, 7, i, 6, 0);
        }
        if (i3 != 0) {
            connect(i3, 6, i, 7, 0);
        }
    }

    public void addToVerticalChain(int i, int i2, int i3) {
        connect(i, 3, i2, i2 == 0 ? 3 : 4, 0);
        connect(i, 4, i3, i3 == 0 ? 4 : 3, 0);
        if (i2 != 0) {
            connect(i2, 4, i, 3, 0);
        }
        if (i3 != 0) {
            connect(i3, 3, i, 4, 0);
        }
    }

    public void removeFromVerticalChain(int i) {
        if (this.mConstraints.containsKey(Integer.valueOf(i))) {
            Constraint constraint = this.mConstraints.get(Integer.valueOf(i));
            if (constraint != null) {
                int i2 = constraint.layout.topToBottom;
                int i3 = constraint.layout.bottomToTop;
                if (!(i2 == -1 && i3 == -1)) {
                    if (i2 != -1 && i3 != -1) {
                        connect(i2, 4, i3, 3, 0);
                        connect(i3, 3, i2, 4, 0);
                    } else if (constraint.layout.bottomToBottom != -1) {
                        connect(i2, 4, constraint.layout.bottomToBottom, 4, 0);
                    } else if (constraint.layout.topToTop != -1) {
                        connect(i3, 3, constraint.layout.topToTop, 3, 0);
                    }
                }
            } else {
                return;
            }
        }
        clear(i, 3);
        clear(i, 4);
    }

    public void removeFromHorizontalChain(int i) {
        Constraint constraint;
        if (this.mConstraints.containsKey(Integer.valueOf(i)) && (constraint = this.mConstraints.get(Integer.valueOf(i))) != null) {
            int i2 = constraint.layout.leftToRight;
            int i3 = constraint.layout.rightToLeft;
            if (i2 == -1 && i3 == -1) {
                int i4 = constraint.layout.startToEnd;
                int i5 = constraint.layout.endToStart;
                if (!(i4 == -1 && i5 == -1)) {
                    if (i4 != -1 && i5 != -1) {
                        connect(i4, 7, i5, 6, 0);
                        connect(i5, 6, i2, 7, 0);
                    } else if (i5 != -1) {
                        if (constraint.layout.rightToRight != -1) {
                            connect(i2, 7, constraint.layout.rightToRight, 7, 0);
                        } else if (constraint.layout.leftToLeft != -1) {
                            connect(i5, 6, constraint.layout.leftToLeft, 6, 0);
                        }
                    }
                }
                clear(i, 6);
                clear(i, 7);
                return;
            }
            if (i2 != -1 && i3 != -1) {
                connect(i2, 2, i3, 1, 0);
                connect(i3, 1, i2, 2, 0);
            } else if (constraint.layout.rightToRight != -1) {
                connect(i2, 2, constraint.layout.rightToRight, 2, 0);
            } else if (constraint.layout.leftToLeft != -1) {
                connect(i3, 1, constraint.layout.leftToLeft, 1, 0);
            }
            clear(i, 1);
            clear(i, 2);
        }
    }

    public void create(int i, int i2) {
        Constraint constraint = get(i);
        constraint.layout.mIsGuideline = true;
        constraint.layout.orientation = i2;
    }

    public void createBarrier(int i, int i2, int i3, int... iArr) {
        Constraint constraint = get(i);
        constraint.layout.mHelperType = 1;
        constraint.layout.mBarrierDirection = i2;
        constraint.layout.mBarrierMargin = i3;
        constraint.layout.mIsGuideline = false;
        constraint.layout.mReferenceIds = iArr;
    }

    public void setGuidelineBegin(int i, int i2) {
        get(i).layout.guideBegin = i2;
        get(i).layout.guideEnd = -1;
        get(i).layout.guidePercent = -1.0f;
    }

    public void setGuidelineEnd(int i, int i2) {
        get(i).layout.guideEnd = i2;
        get(i).layout.guideBegin = -1;
        get(i).layout.guidePercent = -1.0f;
    }

    public void setGuidelinePercent(int i, float f) {
        get(i).layout.guidePercent = f;
        get(i).layout.guideEnd = -1;
        get(i).layout.guideBegin = -1;
    }

    public int[] getReferencedIds(int i) {
        Constraint constraint = get(i);
        if (constraint.layout.mReferenceIds == null) {
            return new int[0];
        }
        return Arrays.copyOf(constraint.layout.mReferenceIds, constraint.layout.mReferenceIds.length);
    }

    public void setReferencedIds(int i, int... iArr) {
        get(i).layout.mReferenceIds = iArr;
    }

    public void setBarrierType(int i, int i2) {
        get(i).layout.mHelperType = i2;
    }

    public void removeAttribute(String str) {
        this.mSavedAttributes.remove(str);
    }

    public void setIntValue(int i, String str, int i2) {
        get(i).setIntValue(str, i2);
    }

    public void setColorValue(int i, String str, int i2) {
        get(i).setColorValue(str, i2);
    }

    public void setFloatValue(int i, String str, float f) {
        get(i).setFloatValue(str, f);
    }

    public void setStringValue(int i, String str, String str2) {
        get(i).setStringValue(str, str2);
    }

    private void addAttributes(ConstraintAttribute.AttributeType attributeType, String... strArr) {
        for (int i = 0; i < strArr.length; i++) {
            if (this.mSavedAttributes.containsKey(strArr[i])) {
                ConstraintAttribute constraintAttribute = this.mSavedAttributes.get(strArr[i]);
                if (!(constraintAttribute == null || constraintAttribute.getType() == attributeType)) {
                    throw new IllegalArgumentException("ConstraintAttribute is already a " + constraintAttribute.getType().name());
                }
            } else {
                this.mSavedAttributes.put(strArr[i], new ConstraintAttribute(strArr[i], attributeType));
            }
        }
    }

    public void parseIntAttributes(Constraint constraint, String str) {
        String[] split = str.split(",");
        for (int i = 0; i < split.length; i++) {
            String[] split2 = split[i].split("=");
            if (split2.length != 2) {
                Log.w(TAG, " Unable to parse " + split[i]);
            } else {
                constraint.setFloatValue(split2[0], (float) Integer.decode(split2[1]).intValue());
            }
        }
    }

    public void parseColorAttributes(Constraint constraint, String str) {
        String[] split = str.split(",");
        for (int i = 0; i < split.length; i++) {
            String[] split2 = split[i].split("=");
            if (split2.length != 2) {
                Log.w(TAG, " Unable to parse " + split[i]);
            } else {
                constraint.setColorValue(split2[0], Color.parseColor(split2[1]));
            }
        }
    }

    public void parseFloatAttributes(Constraint constraint, String str) {
        String[] split = str.split(",");
        for (int i = 0; i < split.length; i++) {
            String[] split2 = split[i].split("=");
            if (split2.length != 2) {
                Log.w(TAG, " Unable to parse " + split[i]);
            } else {
                constraint.setFloatValue(split2[0], Float.parseFloat(split2[1]));
            }
        }
    }

    public void parseStringAttributes(Constraint constraint, String str) {
        String[] splitString = splitString(str);
        for (int i = 0; i < splitString.length; i++) {
            String[] split = splitString[i].split("=");
            Log.w(TAG, " Unable to parse " + splitString[i]);
            constraint.setStringValue(split[0], split[1]);
        }
    }

    private static String[] splitString(String str) {
        char[] charArray = str.toCharArray();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        boolean z = false;
        for (int i2 = 0; i2 < charArray.length; i2++) {
            if (charArray[i2] == ',' && !z) {
                arrayList.add(new String(charArray, i, i2 - i));
                i = i2 + 1;
            } else if (charArray[i2] == '\"') {
                z = !z;
            }
        }
        arrayList.add(new String(charArray, i, charArray.length - i));
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public void addIntAttributes(String... strArr) {
        addAttributes(ConstraintAttribute.AttributeType.INT_TYPE, strArr);
    }

    public void addColorAttributes(String... strArr) {
        addAttributes(ConstraintAttribute.AttributeType.COLOR_TYPE, strArr);
    }

    public void addFloatAttributes(String... strArr) {
        addAttributes(ConstraintAttribute.AttributeType.FLOAT_TYPE, strArr);
    }

    public void addStringAttributes(String... strArr) {
        addAttributes(ConstraintAttribute.AttributeType.STRING_TYPE, strArr);
    }

    private Constraint get(int i) {
        if (!this.mConstraints.containsKey(Integer.valueOf(i))) {
            this.mConstraints.put(Integer.valueOf(i), new Constraint());
        }
        return this.mConstraints.get(Integer.valueOf(i));
    }

    public void load(Context context, int i) {
        XmlResourceParser xml = context.getResources().getXml(i);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 0) {
                    xml.getName();
                } else if (eventType == 2) {
                    String name = xml.getName();
                    Constraint fillFromAttributeList = fillFromAttributeList(context, Xml.asAttributeSet(xml), false);
                    if (name.equalsIgnoreCase("Guideline")) {
                        fillFromAttributeList.layout.mIsGuideline = true;
                    }
                    this.mConstraints.put(Integer.valueOf(fillFromAttributeList.mViewId), fillFromAttributeList);
                }
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:115:0x01cd, code lost:
        continue;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void load(android.content.Context r10, org.xmlpull.v1.XmlPullParser r11) {
        /*
            r9 = this;
            int r0 = r11.getEventType()     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            r1 = 0
            r2 = r1
        L_0x0006:
            r3 = 1
            if (r0 == r3) goto L_0x01dc
            if (r0 == 0) goto L_0x01ca
            r4 = -1
            r5 = 3
            r6 = 2
            r7 = 0
            if (r0 == r6) goto L_0x0067
            if (r0 == r5) goto L_0x0015
            goto L_0x01cd
        L_0x0015:
            java.lang.String r0 = r11.getName()     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            java.util.Locale r8 = java.util.Locale.ROOT     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            java.lang.String r0 = r0.toLowerCase(r8)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            int r8 = r0.hashCode()     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            switch(r8) {
                case -2075718416: goto L_0x0045;
                case -190376483: goto L_0x003b;
                case 426575017: goto L_0x0031;
                case 2146106725: goto L_0x0027;
                default: goto L_0x0026;
            }     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
        L_0x0026:
            goto L_0x004e
        L_0x0027:
            java.lang.String r8 = "constraintset"
            boolean r0 = r0.equals(r8)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            if (r0 == 0) goto L_0x004e
            r4 = 0
            goto L_0x004e
        L_0x0031:
            java.lang.String r7 = "constraintoverride"
            boolean r0 = r0.equals(r7)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            if (r0 == 0) goto L_0x004e
            r4 = 2
            goto L_0x004e
        L_0x003b:
            java.lang.String r7 = "constraint"
            boolean r0 = r0.equals(r7)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            if (r0 == 0) goto L_0x004e
            r4 = 1
            goto L_0x004e
        L_0x0045:
            java.lang.String r7 = "guideline"
            boolean r0 = r0.equals(r7)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            if (r0 == 0) goto L_0x004e
            r4 = 3
        L_0x004e:
            if (r4 == 0) goto L_0x0066
            if (r4 == r3) goto L_0x0058
            if (r4 == r6) goto L_0x0058
            if (r4 == r5) goto L_0x0058
            goto L_0x01cd
        L_0x0058:
            java.util.HashMap<java.lang.Integer, androidx.constraintlayout.widget.ConstraintSet$Constraint> r0 = r9.mConstraints     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            int r3 = r2.mViewId     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            r0.put(r3, r2)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            r2 = r1
            goto L_0x01cd
        L_0x0066:
            return
        L_0x0067:
            java.lang.String r0 = r11.getName()     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            int r8 = r0.hashCode()     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            switch(r8) {
                case -2025855158: goto L_0x00d0;
                case -1984451626: goto L_0x00c6;
                case -1962203927: goto L_0x00bc;
                case -1269513683: goto L_0x00b2;
                case -1238332596: goto L_0x00a8;
                case -71750448: goto L_0x009e;
                case 366511058: goto L_0x0093;
                case 1331510167: goto L_0x0089;
                case 1791837707: goto L_0x007e;
                case 1803088381: goto L_0x0074;
                default: goto L_0x0072;
            }     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
        L_0x0072:
            goto L_0x00d9
        L_0x0074:
            java.lang.String r5 = "Constraint"
            boolean r0 = r0.equals(r5)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            if (r0 == 0) goto L_0x00d9
            r4 = 0
            goto L_0x00d9
        L_0x007e:
            java.lang.String r5 = "CustomAttribute"
            boolean r0 = r0.equals(r5)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            if (r0 == 0) goto L_0x00d9
            r4 = 8
            goto L_0x00d9
        L_0x0089:
            java.lang.String r6 = "Barrier"
            boolean r0 = r0.equals(r6)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            if (r0 == 0) goto L_0x00d9
            r4 = 3
            goto L_0x00d9
        L_0x0093:
            java.lang.String r5 = "CustomMethod"
            boolean r0 = r0.equals(r5)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            if (r0 == 0) goto L_0x00d9
            r4 = 9
            goto L_0x00d9
        L_0x009e:
            java.lang.String r5 = "Guideline"
            boolean r0 = r0.equals(r5)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            if (r0 == 0) goto L_0x00d9
            r4 = 2
            goto L_0x00d9
        L_0x00a8:
            java.lang.String r5 = "Transform"
            boolean r0 = r0.equals(r5)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            if (r0 == 0) goto L_0x00d9
            r4 = 5
            goto L_0x00d9
        L_0x00b2:
            java.lang.String r5 = "PropertySet"
            boolean r0 = r0.equals(r5)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            if (r0 == 0) goto L_0x00d9
            r4 = 4
            goto L_0x00d9
        L_0x00bc:
            java.lang.String r5 = "ConstraintOverride"
            boolean r0 = r0.equals(r5)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            if (r0 == 0) goto L_0x00d9
            r4 = 1
            goto L_0x00d9
        L_0x00c6:
            java.lang.String r5 = "Motion"
            boolean r0 = r0.equals(r5)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            if (r0 == 0) goto L_0x00d9
            r4 = 7
            goto L_0x00d9
        L_0x00d0:
            java.lang.String r5 = "Layout"
            boolean r0 = r0.equals(r5)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            if (r0 == 0) goto L_0x00d9
            r4 = 6
        L_0x00d9:
            java.lang.String r0 = "XML parser error must be within a Constraint "
            switch(r4) {
                case 0: goto L_0x01c0;
                case 1: goto L_0x01b7;
                case 2: goto L_0x01a6;
                case 3: goto L_0x0199;
                case 4: goto L_0x0174;
                case 5: goto L_0x014e;
                case 6: goto L_0x0128;
                case 7: goto L_0x0102;
                case 8: goto L_0x00e0;
                case 9: goto L_0x00e0;
                default: goto L_0x00de;
            }
        L_0x00de:
            goto L_0x01cd
        L_0x00e0:
            if (r2 == 0) goto L_0x00e9
            java.util.HashMap<java.lang.String, androidx.constraintlayout.widget.ConstraintAttribute> r0 = r2.mCustomConstraints     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            androidx.constraintlayout.widget.ConstraintAttribute.parse(r10, r11, r0)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            goto L_0x01cd
        L_0x00e9:
            java.lang.RuntimeException r10 = new java.lang.RuntimeException     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            r1.<init>()     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            r1.append(r0)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            int r11 = r11.getLineNumber()     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            r1.append(r11)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            java.lang.String r11 = r1.toString()     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            r10.<init>(r11)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            throw r10     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
        L_0x0102:
            if (r2 == 0) goto L_0x010f
            androidx.constraintlayout.widget.ConstraintSet$Motion r0 = r2.motion     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            android.util.AttributeSet r3 = android.util.Xml.asAttributeSet(r11)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            r0.fillFromAttributeList(r10, r3)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            goto L_0x01cd
        L_0x010f:
            java.lang.RuntimeException r10 = new java.lang.RuntimeException     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            r1.<init>()     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            r1.append(r0)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            int r11 = r11.getLineNumber()     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            r1.append(r11)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            java.lang.String r11 = r1.toString()     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            r10.<init>(r11)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            throw r10     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
        L_0x0128:
            if (r2 == 0) goto L_0x0135
            androidx.constraintlayout.widget.ConstraintSet$Layout r0 = r2.layout     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            android.util.AttributeSet r3 = android.util.Xml.asAttributeSet(r11)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            r0.fillFromAttributeList(r10, r3)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            goto L_0x01cd
        L_0x0135:
            java.lang.RuntimeException r10 = new java.lang.RuntimeException     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            r1.<init>()     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            r1.append(r0)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            int r11 = r11.getLineNumber()     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            r1.append(r11)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            java.lang.String r11 = r1.toString()     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            r10.<init>(r11)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            throw r10     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
        L_0x014e:
            if (r2 == 0) goto L_0x015b
            androidx.constraintlayout.widget.ConstraintSet$Transform r0 = r2.transform     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            android.util.AttributeSet r3 = android.util.Xml.asAttributeSet(r11)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            r0.fillFromAttributeList(r10, r3)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            goto L_0x01cd
        L_0x015b:
            java.lang.RuntimeException r10 = new java.lang.RuntimeException     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            r1.<init>()     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            r1.append(r0)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            int r11 = r11.getLineNumber()     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            r1.append(r11)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            java.lang.String r11 = r1.toString()     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            r10.<init>(r11)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            throw r10     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
        L_0x0174:
            if (r2 == 0) goto L_0x0180
            androidx.constraintlayout.widget.ConstraintSet$PropertySet r0 = r2.propertySet     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            android.util.AttributeSet r3 = android.util.Xml.asAttributeSet(r11)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            r0.fillFromAttributeList(r10, r3)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            goto L_0x01cd
        L_0x0180:
            java.lang.RuntimeException r10 = new java.lang.RuntimeException     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            r1.<init>()     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            r1.append(r0)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            int r11 = r11.getLineNumber()     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            r1.append(r11)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            java.lang.String r11 = r1.toString()     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            r10.<init>(r11)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            throw r10     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
        L_0x0199:
            android.util.AttributeSet r0 = android.util.Xml.asAttributeSet(r11)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            androidx.constraintlayout.widget.ConstraintSet$Constraint r0 = r9.fillFromAttributeList(r10, r0, r7)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            androidx.constraintlayout.widget.ConstraintSet$Layout r2 = r0.layout     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            r2.mHelperType = r3     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            goto L_0x01c8
        L_0x01a6:
            android.util.AttributeSet r0 = android.util.Xml.asAttributeSet(r11)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            androidx.constraintlayout.widget.ConstraintSet$Constraint r0 = r9.fillFromAttributeList(r10, r0, r7)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            androidx.constraintlayout.widget.ConstraintSet$Layout r2 = r0.layout     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            r2.mIsGuideline = r3     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            androidx.constraintlayout.widget.ConstraintSet$Layout r2 = r0.layout     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            r2.mApply = r3     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            goto L_0x01c8
        L_0x01b7:
            android.util.AttributeSet r0 = android.util.Xml.asAttributeSet(r11)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            androidx.constraintlayout.widget.ConstraintSet$Constraint r0 = r9.fillFromAttributeList(r10, r0, r3)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            goto L_0x01c8
        L_0x01c0:
            android.util.AttributeSet r0 = android.util.Xml.asAttributeSet(r11)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            androidx.constraintlayout.widget.ConstraintSet$Constraint r0 = r9.fillFromAttributeList(r10, r0, r7)     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
        L_0x01c8:
            r2 = r0
            goto L_0x01cd
        L_0x01ca:
            r11.getName()     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
        L_0x01cd:
            int r0 = r11.next()     // Catch:{ XmlPullParserException -> 0x01d8, IOException -> 0x01d3 }
            goto L_0x0006
        L_0x01d3:
            r10 = move-exception
            r10.printStackTrace()
            goto L_0x01dc
        L_0x01d8:
            r10 = move-exception
            r10.printStackTrace()
        L_0x01dc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintSet.load(android.content.Context, org.xmlpull.v1.XmlPullParser):void");
    }

    /* access modifiers changed from: private */
    public static int lookupID(TypedArray typedArray, int i, int i2) {
        int resourceId = typedArray.getResourceId(i, i2);
        return resourceId == -1 ? typedArray.getInt(i, -1) : resourceId;
    }

    private Constraint fillFromAttributeList(Context context, AttributeSet attributeSet, boolean z) {
        Constraint constraint = new Constraint();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, z ? R.styleable.ConstraintOverride : R.styleable.Constraint);
        populateConstraint(context, constraint, obtainStyledAttributes, z);
        obtainStyledAttributes.recycle();
        return constraint;
    }

    public static Constraint buildDelta(Context context, XmlPullParser xmlPullParser) {
        AttributeSet asAttributeSet = Xml.asAttributeSet(xmlPullParser);
        Constraint constraint = new Constraint();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(asAttributeSet, R.styleable.ConstraintOverride);
        populateOverride(context, constraint, obtainStyledAttributes);
        obtainStyledAttributes.recycle();
        return constraint;
    }

    private static void populateOverride(Context context, Constraint constraint, TypedArray typedArray) {
        int indexCount = typedArray.getIndexCount();
        Constraint.Delta delta = new Constraint.Delta();
        constraint.mDelta = delta;
        constraint.motion.mApply = false;
        constraint.layout.mApply = false;
        constraint.propertySet.mApply = false;
        constraint.transform.mApply = false;
        for (int i = 0; i < indexCount; i++) {
            int index = typedArray.getIndex(i);
            switch (overrideMapToConstant.get(index)) {
                case 2:
                    delta.add(2, typedArray.getDimensionPixelSize(index, constraint.layout.bottomMargin));
                    break;
                case 5:
                    delta.add(5, typedArray.getString(index));
                    break;
                case 6:
                    delta.add(6, typedArray.getDimensionPixelOffset(index, constraint.layout.editorAbsoluteX));
                    break;
                case 7:
                    delta.add(7, typedArray.getDimensionPixelOffset(index, constraint.layout.editorAbsoluteY));
                    break;
                case 8:
                    if (Build.VERSION.SDK_INT < 17) {
                        break;
                    } else {
                        delta.add(8, typedArray.getDimensionPixelSize(index, constraint.layout.endMargin));
                        break;
                    }
                case 11:
                    delta.add(11, typedArray.getDimensionPixelSize(index, constraint.layout.goneBottomMargin));
                    break;
                case 12:
                    delta.add(12, typedArray.getDimensionPixelSize(index, constraint.layout.goneEndMargin));
                    break;
                case 13:
                    delta.add(13, typedArray.getDimensionPixelSize(index, constraint.layout.goneLeftMargin));
                    break;
                case 14:
                    delta.add(14, typedArray.getDimensionPixelSize(index, constraint.layout.goneRightMargin));
                    break;
                case 15:
                    delta.add(15, typedArray.getDimensionPixelSize(index, constraint.layout.goneStartMargin));
                    break;
                case 16:
                    delta.add(16, typedArray.getDimensionPixelSize(index, constraint.layout.goneTopMargin));
                    break;
                case 17:
                    delta.add(17, typedArray.getDimensionPixelOffset(index, constraint.layout.guideBegin));
                    break;
                case 18:
                    delta.add(18, typedArray.getDimensionPixelOffset(index, constraint.layout.guideEnd));
                    break;
                case 19:
                    delta.add(19, typedArray.getFloat(index, constraint.layout.guidePercent));
                    break;
                case 20:
                    delta.add(20, typedArray.getFloat(index, constraint.layout.horizontalBias));
                    break;
                case 21:
                    delta.add(21, typedArray.getLayoutDimension(index, constraint.layout.mHeight));
                    break;
                case 22:
                    delta.add(22, VISIBILITY_FLAGS[typedArray.getInt(index, constraint.propertySet.visibility)]);
                    break;
                case 23:
                    delta.add(23, typedArray.getLayoutDimension(index, constraint.layout.mWidth));
                    break;
                case 24:
                    delta.add(24, typedArray.getDimensionPixelSize(index, constraint.layout.leftMargin));
                    break;
                case 27:
                    delta.add(27, typedArray.getInt(index, constraint.layout.orientation));
                    break;
                case 28:
                    delta.add(28, typedArray.getDimensionPixelSize(index, constraint.layout.rightMargin));
                    break;
                case 31:
                    if (Build.VERSION.SDK_INT < 17) {
                        break;
                    } else {
                        delta.add(31, typedArray.getDimensionPixelSize(index, constraint.layout.startMargin));
                        break;
                    }
                case 34:
                    delta.add(34, typedArray.getDimensionPixelSize(index, constraint.layout.topMargin));
                    break;
                case 37:
                    delta.add(37, typedArray.getFloat(index, constraint.layout.verticalBias));
                    break;
                case 38:
                    constraint.mViewId = typedArray.getResourceId(index, constraint.mViewId);
                    delta.add(38, constraint.mViewId);
                    break;
                case 39:
                    delta.add(39, typedArray.getFloat(index, constraint.layout.horizontalWeight));
                    break;
                case 40:
                    delta.add(40, typedArray.getFloat(index, constraint.layout.verticalWeight));
                    break;
                case 41:
                    delta.add(41, typedArray.getInt(index, constraint.layout.horizontalChainStyle));
                    break;
                case 42:
                    delta.add(42, typedArray.getInt(index, constraint.layout.verticalChainStyle));
                    break;
                case 43:
                    delta.add(43, typedArray.getFloat(index, constraint.propertySet.alpha));
                    break;
                case 44:
                    if (Build.VERSION.SDK_INT < 21) {
                        break;
                    } else {
                        delta.add(44, true);
                        delta.add(44, typedArray.getDimension(index, constraint.transform.elevation));
                        break;
                    }
                case 45:
                    delta.add(45, typedArray.getFloat(index, constraint.transform.rotationX));
                    break;
                case 46:
                    delta.add(46, typedArray.getFloat(index, constraint.transform.rotationY));
                    break;
                case 47:
                    delta.add(47, typedArray.getFloat(index, constraint.transform.scaleX));
                    break;
                case 48:
                    delta.add(48, typedArray.getFloat(index, constraint.transform.scaleY));
                    break;
                case 49:
                    delta.add(49, typedArray.getDimension(index, constraint.transform.transformPivotX));
                    break;
                case 50:
                    delta.add(50, typedArray.getDimension(index, constraint.transform.transformPivotY));
                    break;
                case 51:
                    delta.add(51, typedArray.getDimension(index, constraint.transform.translationX));
                    break;
                case 52:
                    delta.add(52, typedArray.getDimension(index, constraint.transform.translationY));
                    break;
                case 53:
                    if (Build.VERSION.SDK_INT < 21) {
                        break;
                    } else {
                        delta.add(53, typedArray.getDimension(index, constraint.transform.translationZ));
                        break;
                    }
                case 54:
                    delta.add(54, typedArray.getInt(index, constraint.layout.widthDefault));
                    break;
                case 55:
                    delta.add(55, typedArray.getInt(index, constraint.layout.heightDefault));
                    break;
                case 56:
                    delta.add(56, typedArray.getDimensionPixelSize(index, constraint.layout.widthMax));
                    break;
                case 57:
                    delta.add(57, typedArray.getDimensionPixelSize(index, constraint.layout.heightMax));
                    break;
                case 58:
                    delta.add(58, typedArray.getDimensionPixelSize(index, constraint.layout.widthMin));
                    break;
                case 59:
                    delta.add(59, typedArray.getDimensionPixelSize(index, constraint.layout.heightMin));
                    break;
                case 60:
                    delta.add(60, typedArray.getFloat(index, constraint.transform.rotation));
                    break;
                case 62:
                    delta.add(62, typedArray.getDimensionPixelSize(index, constraint.layout.circleRadius));
                    break;
                case 63:
                    delta.add(63, typedArray.getFloat(index, constraint.layout.circleAngle));
                    break;
                case 64:
                    delta.add(64, lookupID(typedArray, index, constraint.motion.mAnimateRelativeTo));
                    break;
                case 65:
                    if (typedArray.peekValue(index).type != 3) {
                        delta.add(65, Easing.NAMED_EASING[typedArray.getInteger(index, 0)]);
                        break;
                    } else {
                        delta.add(65, typedArray.getString(index));
                        break;
                    }
                case 66:
                    delta.add(66, typedArray.getInt(index, 0));
                    break;
                case 67:
                    delta.add(67, typedArray.getFloat(index, constraint.motion.mPathRotate));
                    break;
                case 68:
                    delta.add(68, typedArray.getFloat(index, constraint.propertySet.mProgress));
                    break;
                case 69:
                    delta.add(69, typedArray.getFloat(index, 1.0f));
                    break;
                case 70:
                    delta.add(70, typedArray.getFloat(index, 1.0f));
                    break;
                case 71:
                    Log.e(TAG, "CURRENTLY UNSUPPORTED");
                    break;
                case 72:
                    delta.add(72, typedArray.getInt(index, constraint.layout.mBarrierDirection));
                    break;
                case 73:
                    delta.add(73, typedArray.getDimensionPixelSize(index, constraint.layout.mBarrierMargin));
                    break;
                case 74:
                    delta.add(74, typedArray.getString(index));
                    break;
                case 75:
                    delta.add(75, typedArray.getBoolean(index, constraint.layout.mBarrierAllowsGoneWidgets));
                    break;
                case 76:
                    delta.add(76, typedArray.getInt(index, constraint.motion.mPathMotionArc));
                    break;
                case 77:
                    delta.add(77, typedArray.getString(index));
                    break;
                case 78:
                    delta.add(78, typedArray.getInt(index, constraint.propertySet.mVisibilityMode));
                    break;
                case 79:
                    delta.add(79, typedArray.getFloat(index, constraint.motion.mMotionStagger));
                    break;
                case 80:
                    delta.add(80, typedArray.getBoolean(index, constraint.layout.constrainedWidth));
                    break;
                case 81:
                    delta.add(81, typedArray.getBoolean(index, constraint.layout.constrainedHeight));
                    break;
                case 82:
                    delta.add(82, typedArray.getInteger(index, constraint.motion.mAnimateCircleAngleTo));
                    break;
                case 83:
                    delta.add(83, lookupID(typedArray, index, constraint.transform.transformPivotTarget));
                    break;
                case 84:
                    delta.add(84, typedArray.getInteger(index, constraint.motion.mQuantizeMotionSteps));
                    break;
                case 85:
                    delta.add(85, typedArray.getFloat(index, constraint.motion.mQuantizeMotionPhase));
                    break;
                case 86:
                    TypedValue peekValue = typedArray.peekValue(index);
                    if (peekValue.type != 1) {
                        if (peekValue.type != 3) {
                            constraint.motion.mQuantizeInterpolatorType = typedArray.getInteger(index, constraint.motion.mQuantizeInterpolatorID);
                            delta.add(88, constraint.motion.mQuantizeInterpolatorType);
                            break;
                        } else {
                            constraint.motion.mQuantizeInterpolatorString = typedArray.getString(index);
                            delta.add(90, constraint.motion.mQuantizeInterpolatorString);
                            if (constraint.motion.mQuantizeInterpolatorString.indexOf("/") <= 0) {
                                constraint.motion.mQuantizeInterpolatorType = -1;
                                delta.add(88, constraint.motion.mQuantizeInterpolatorType);
                                break;
                            } else {
                                constraint.motion.mQuantizeInterpolatorID = typedArray.getResourceId(index, -1);
                                delta.add(89, constraint.motion.mQuantizeInterpolatorID);
                                constraint.motion.mQuantizeInterpolatorType = -2;
                                delta.add(88, constraint.motion.mQuantizeInterpolatorType);
                                break;
                            }
                        }
                    } else {
                        constraint.motion.mQuantizeInterpolatorID = typedArray.getResourceId(index, -1);
                        delta.add(89, constraint.motion.mQuantizeInterpolatorID);
                        if (constraint.motion.mQuantizeInterpolatorID == -1) {
                            break;
                        } else {
                            constraint.motion.mQuantizeInterpolatorType = -2;
                            delta.add(88, constraint.motion.mQuantizeInterpolatorType);
                            break;
                        }
                    }
                case 87:
                    Log.w(TAG, "unused attribute 0x" + Integer.toHexString(index) + "   " + mapToConstant.get(index));
                    break;
                case 93:
                    delta.add(93, typedArray.getDimensionPixelSize(index, constraint.layout.baselineMargin));
                    break;
                case 94:
                    delta.add(94, typedArray.getDimensionPixelSize(index, constraint.layout.goneBaselineMargin));
                    break;
                case 95:
                    parseDimensionConstraints(delta, typedArray, index, 0);
                    break;
                case 96:
                    parseDimensionConstraints(delta, typedArray, index, 1);
                    break;
                case 97:
                    delta.add(97, typedArray.getInt(index, constraint.layout.mWrapBehavior));
                    break;
                case 98:
                    if (!MotionLayout.IS_IN_EDIT_MODE) {
                        if (typedArray.peekValue(index).type != 3) {
                            constraint.mViewId = typedArray.getResourceId(index, constraint.mViewId);
                            break;
                        } else {
                            constraint.mTargetString = typedArray.getString(index);
                            break;
                        }
                    } else {
                        constraint.mViewId = typedArray.getResourceId(index, constraint.mViewId);
                        if (constraint.mViewId != -1) {
                            break;
                        } else {
                            constraint.mTargetString = typedArray.getString(index);
                            break;
                        }
                    }
                case 99:
                    delta.add(99, typedArray.getBoolean(index, constraint.layout.guidelineUseRtl));
                    break;
                default:
                    Log.w(TAG, "Unknown attribute 0x" + Integer.toHexString(index) + "   " + mapToConstant.get(index));
                    break;
            }
        }
    }

    /* access modifiers changed from: private */
    public static void setDeltaValue(Constraint constraint, int i, float f) {
        if (i == 19) {
            constraint.layout.guidePercent = f;
        } else if (i == 20) {
            constraint.layout.horizontalBias = f;
        } else if (i == 37) {
            constraint.layout.verticalBias = f;
        } else if (i == 60) {
            constraint.transform.rotation = f;
        } else if (i == 63) {
            constraint.layout.circleAngle = f;
        } else if (i == 79) {
            constraint.motion.mMotionStagger = f;
        } else if (i == 85) {
            constraint.motion.mQuantizeMotionPhase = f;
        } else if (i == 87) {
        } else {
            if (i == 39) {
                constraint.layout.horizontalWeight = f;
            } else if (i != 40) {
                switch (i) {
                    case 43:
                        constraint.propertySet.alpha = f;
                        return;
                    case 44:
                        constraint.transform.elevation = f;
                        constraint.transform.applyElevation = true;
                        return;
                    case 45:
                        constraint.transform.rotationX = f;
                        return;
                    case 46:
                        constraint.transform.rotationY = f;
                        return;
                    case 47:
                        constraint.transform.scaleX = f;
                        return;
                    case 48:
                        constraint.transform.scaleY = f;
                        return;
                    case 49:
                        constraint.transform.transformPivotX = f;
                        return;
                    case 50:
                        constraint.transform.transformPivotY = f;
                        return;
                    case 51:
                        constraint.transform.translationX = f;
                        return;
                    case 52:
                        constraint.transform.translationY = f;
                        return;
                    case 53:
                        constraint.transform.translationZ = f;
                        return;
                    default:
                        switch (i) {
                            case 67:
                                constraint.motion.mPathRotate = f;
                                return;
                            case 68:
                                constraint.propertySet.mProgress = f;
                                return;
                            case 69:
                                constraint.layout.widthPercent = f;
                                return;
                            case 70:
                                constraint.layout.heightPercent = f;
                                return;
                            default:
                                Log.w(TAG, "Unknown attribute 0x");
                                return;
                        }
                }
            } else {
                constraint.layout.verticalWeight = f;
            }
        }
    }

    /* access modifiers changed from: private */
    public static void setDeltaValue(Constraint constraint, int i, int i2) {
        if (i == 6) {
            constraint.layout.editorAbsoluteX = i2;
        } else if (i == 7) {
            constraint.layout.editorAbsoluteY = i2;
        } else if (i == 8) {
            constraint.layout.endMargin = i2;
        } else if (i == 27) {
            constraint.layout.orientation = i2;
        } else if (i == 28) {
            constraint.layout.rightMargin = i2;
        } else if (i == 41) {
            constraint.layout.horizontalChainStyle = i2;
        } else if (i == 42) {
            constraint.layout.verticalChainStyle = i2;
        } else if (i == 61) {
            constraint.layout.circleConstraint = i2;
        } else if (i == 62) {
            constraint.layout.circleRadius = i2;
        } else if (i == 72) {
            constraint.layout.mBarrierDirection = i2;
        } else if (i != 73) {
            switch (i) {
                case 2:
                    constraint.layout.bottomMargin = i2;
                    return;
                case 11:
                    constraint.layout.goneBottomMargin = i2;
                    return;
                case 12:
                    constraint.layout.goneEndMargin = i2;
                    return;
                case 13:
                    constraint.layout.goneLeftMargin = i2;
                    return;
                case 14:
                    constraint.layout.goneRightMargin = i2;
                    return;
                case 15:
                    constraint.layout.goneStartMargin = i2;
                    return;
                case 16:
                    constraint.layout.goneTopMargin = i2;
                    return;
                case 17:
                    constraint.layout.guideBegin = i2;
                    return;
                case 18:
                    constraint.layout.guideEnd = i2;
                    return;
                case 31:
                    constraint.layout.startMargin = i2;
                    return;
                case 34:
                    constraint.layout.topMargin = i2;
                    return;
                case 38:
                    constraint.mViewId = i2;
                    return;
                case 64:
                    constraint.motion.mAnimateRelativeTo = i2;
                    return;
                case 66:
                    constraint.motion.mDrawPath = i2;
                    return;
                case 76:
                    constraint.motion.mPathMotionArc = i2;
                    return;
                case 78:
                    constraint.propertySet.mVisibilityMode = i2;
                    return;
                case 93:
                    constraint.layout.baselineMargin = i2;
                    return;
                case 94:
                    constraint.layout.goneBaselineMargin = i2;
                    return;
                case 97:
                    constraint.layout.mWrapBehavior = i2;
                    return;
                default:
                    switch (i) {
                        case 21:
                            constraint.layout.mHeight = i2;
                            return;
                        case 22:
                            constraint.propertySet.visibility = i2;
                            return;
                        case 23:
                            constraint.layout.mWidth = i2;
                            return;
                        case 24:
                            constraint.layout.leftMargin = i2;
                            return;
                        default:
                            switch (i) {
                                case 54:
                                    constraint.layout.widthDefault = i2;
                                    return;
                                case 55:
                                    constraint.layout.heightDefault = i2;
                                    return;
                                case 56:
                                    constraint.layout.widthMax = i2;
                                    return;
                                case 57:
                                    constraint.layout.heightMax = i2;
                                    return;
                                case 58:
                                    constraint.layout.widthMin = i2;
                                    return;
                                case 59:
                                    constraint.layout.heightMin = i2;
                                    return;
                                default:
                                    switch (i) {
                                        case 82:
                                            constraint.motion.mAnimateCircleAngleTo = i2;
                                            return;
                                        case 83:
                                            constraint.transform.transformPivotTarget = i2;
                                            return;
                                        case 84:
                                            constraint.motion.mQuantizeMotionSteps = i2;
                                            return;
                                        default:
                                            switch (i) {
                                                case 87:
                                                    return;
                                                case 88:
                                                    constraint.motion.mQuantizeInterpolatorType = i2;
                                                    return;
                                                case 89:
                                                    constraint.motion.mQuantizeInterpolatorID = i2;
                                                    return;
                                                default:
                                                    Log.w(TAG, "Unknown attribute 0x");
                                                    return;
                                            }
                                    }
                            }
                    }
            }
        } else {
            constraint.layout.mBarrierMargin = i2;
        }
    }

    /* access modifiers changed from: private */
    public static void setDeltaValue(Constraint constraint, int i, String str) {
        if (i == 5) {
            constraint.layout.dimensionRatio = str;
        } else if (i == 65) {
            constraint.motion.mTransitionEasing = str;
        } else if (i == 74) {
            constraint.layout.mReferenceIdString = str;
            constraint.layout.mReferenceIds = null;
        } else if (i == 77) {
            constraint.layout.mConstraintTag = str;
        } else if (i == 87) {
        } else {
            if (i != 90) {
                Log.w(TAG, "Unknown attribute 0x");
            } else {
                constraint.motion.mQuantizeInterpolatorString = str;
            }
        }
    }

    /* access modifiers changed from: private */
    public static void setDeltaValue(Constraint constraint, int i, boolean z) {
        if (i == 44) {
            constraint.transform.applyElevation = z;
        } else if (i == 75) {
            constraint.layout.mBarrierAllowsGoneWidgets = z;
        } else if (i == 87) {
        } else {
            if (i == 80) {
                constraint.layout.constrainedWidth = z;
            } else if (i != 81) {
                Log.w(TAG, "Unknown attribute 0x");
            } else {
                constraint.layout.constrainedHeight = z;
            }
        }
    }

    private void populateConstraint(Context context, Constraint constraint, TypedArray typedArray, boolean z) {
        if (z) {
            populateOverride(context, constraint, typedArray);
            return;
        }
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArray.getIndex(i);
            if (!(index == R.styleable.Constraint_android_id || R.styleable.Constraint_android_layout_marginStart == index || R.styleable.Constraint_android_layout_marginEnd == index)) {
                constraint.motion.mApply = true;
                constraint.layout.mApply = true;
                constraint.propertySet.mApply = true;
                constraint.transform.mApply = true;
            }
            switch (mapToConstant.get(index)) {
                case 1:
                    constraint.layout.baselineToBaseline = lookupID(typedArray, index, constraint.layout.baselineToBaseline);
                    break;
                case 2:
                    constraint.layout.bottomMargin = typedArray.getDimensionPixelSize(index, constraint.layout.bottomMargin);
                    break;
                case 3:
                    constraint.layout.bottomToBottom = lookupID(typedArray, index, constraint.layout.bottomToBottom);
                    break;
                case 4:
                    constraint.layout.bottomToTop = lookupID(typedArray, index, constraint.layout.bottomToTop);
                    break;
                case 5:
                    constraint.layout.dimensionRatio = typedArray.getString(index);
                    break;
                case 6:
                    constraint.layout.editorAbsoluteX = typedArray.getDimensionPixelOffset(index, constraint.layout.editorAbsoluteX);
                    break;
                case 7:
                    constraint.layout.editorAbsoluteY = typedArray.getDimensionPixelOffset(index, constraint.layout.editorAbsoluteY);
                    break;
                case 8:
                    if (Build.VERSION.SDK_INT < 17) {
                        break;
                    } else {
                        constraint.layout.endMargin = typedArray.getDimensionPixelSize(index, constraint.layout.endMargin);
                        break;
                    }
                case 9:
                    constraint.layout.endToEnd = lookupID(typedArray, index, constraint.layout.endToEnd);
                    break;
                case 10:
                    constraint.layout.endToStart = lookupID(typedArray, index, constraint.layout.endToStart);
                    break;
                case 11:
                    constraint.layout.goneBottomMargin = typedArray.getDimensionPixelSize(index, constraint.layout.goneBottomMargin);
                    break;
                case 12:
                    constraint.layout.goneEndMargin = typedArray.getDimensionPixelSize(index, constraint.layout.goneEndMargin);
                    break;
                case 13:
                    constraint.layout.goneLeftMargin = typedArray.getDimensionPixelSize(index, constraint.layout.goneLeftMargin);
                    break;
                case 14:
                    constraint.layout.goneRightMargin = typedArray.getDimensionPixelSize(index, constraint.layout.goneRightMargin);
                    break;
                case 15:
                    constraint.layout.goneStartMargin = typedArray.getDimensionPixelSize(index, constraint.layout.goneStartMargin);
                    break;
                case 16:
                    constraint.layout.goneTopMargin = typedArray.getDimensionPixelSize(index, constraint.layout.goneTopMargin);
                    break;
                case 17:
                    constraint.layout.guideBegin = typedArray.getDimensionPixelOffset(index, constraint.layout.guideBegin);
                    break;
                case 18:
                    constraint.layout.guideEnd = typedArray.getDimensionPixelOffset(index, constraint.layout.guideEnd);
                    break;
                case 19:
                    constraint.layout.guidePercent = typedArray.getFloat(index, constraint.layout.guidePercent);
                    break;
                case 20:
                    constraint.layout.horizontalBias = typedArray.getFloat(index, constraint.layout.horizontalBias);
                    break;
                case 21:
                    constraint.layout.mHeight = typedArray.getLayoutDimension(index, constraint.layout.mHeight);
                    break;
                case 22:
                    constraint.propertySet.visibility = typedArray.getInt(index, constraint.propertySet.visibility);
                    constraint.propertySet.visibility = VISIBILITY_FLAGS[constraint.propertySet.visibility];
                    break;
                case 23:
                    constraint.layout.mWidth = typedArray.getLayoutDimension(index, constraint.layout.mWidth);
                    break;
                case 24:
                    constraint.layout.leftMargin = typedArray.getDimensionPixelSize(index, constraint.layout.leftMargin);
                    break;
                case 25:
                    constraint.layout.leftToLeft = lookupID(typedArray, index, constraint.layout.leftToLeft);
                    break;
                case 26:
                    constraint.layout.leftToRight = lookupID(typedArray, index, constraint.layout.leftToRight);
                    break;
                case 27:
                    constraint.layout.orientation = typedArray.getInt(index, constraint.layout.orientation);
                    break;
                case 28:
                    constraint.layout.rightMargin = typedArray.getDimensionPixelSize(index, constraint.layout.rightMargin);
                    break;
                case 29:
                    constraint.layout.rightToLeft = lookupID(typedArray, index, constraint.layout.rightToLeft);
                    break;
                case 30:
                    constraint.layout.rightToRight = lookupID(typedArray, index, constraint.layout.rightToRight);
                    break;
                case 31:
                    if (Build.VERSION.SDK_INT < 17) {
                        break;
                    } else {
                        constraint.layout.startMargin = typedArray.getDimensionPixelSize(index, constraint.layout.startMargin);
                        break;
                    }
                case 32:
                    constraint.layout.startToEnd = lookupID(typedArray, index, constraint.layout.startToEnd);
                    break;
                case 33:
                    constraint.layout.startToStart = lookupID(typedArray, index, constraint.layout.startToStart);
                    break;
                case 34:
                    constraint.layout.topMargin = typedArray.getDimensionPixelSize(index, constraint.layout.topMargin);
                    break;
                case 35:
                    constraint.layout.topToBottom = lookupID(typedArray, index, constraint.layout.topToBottom);
                    break;
                case 36:
                    constraint.layout.topToTop = lookupID(typedArray, index, constraint.layout.topToTop);
                    break;
                case 37:
                    constraint.layout.verticalBias = typedArray.getFloat(index, constraint.layout.verticalBias);
                    break;
                case 38:
                    constraint.mViewId = typedArray.getResourceId(index, constraint.mViewId);
                    break;
                case 39:
                    constraint.layout.horizontalWeight = typedArray.getFloat(index, constraint.layout.horizontalWeight);
                    break;
                case 40:
                    constraint.layout.verticalWeight = typedArray.getFloat(index, constraint.layout.verticalWeight);
                    break;
                case 41:
                    constraint.layout.horizontalChainStyle = typedArray.getInt(index, constraint.layout.horizontalChainStyle);
                    break;
                case 42:
                    constraint.layout.verticalChainStyle = typedArray.getInt(index, constraint.layout.verticalChainStyle);
                    break;
                case 43:
                    constraint.propertySet.alpha = typedArray.getFloat(index, constraint.propertySet.alpha);
                    break;
                case 44:
                    if (Build.VERSION.SDK_INT < 21) {
                        break;
                    } else {
                        constraint.transform.applyElevation = true;
                        constraint.transform.elevation = typedArray.getDimension(index, constraint.transform.elevation);
                        break;
                    }
                case 45:
                    constraint.transform.rotationX = typedArray.getFloat(index, constraint.transform.rotationX);
                    break;
                case 46:
                    constraint.transform.rotationY = typedArray.getFloat(index, constraint.transform.rotationY);
                    break;
                case 47:
                    constraint.transform.scaleX = typedArray.getFloat(index, constraint.transform.scaleX);
                    break;
                case 48:
                    constraint.transform.scaleY = typedArray.getFloat(index, constraint.transform.scaleY);
                    break;
                case 49:
                    constraint.transform.transformPivotX = typedArray.getDimension(index, constraint.transform.transformPivotX);
                    break;
                case 50:
                    constraint.transform.transformPivotY = typedArray.getDimension(index, constraint.transform.transformPivotY);
                    break;
                case 51:
                    constraint.transform.translationX = typedArray.getDimension(index, constraint.transform.translationX);
                    break;
                case 52:
                    constraint.transform.translationY = typedArray.getDimension(index, constraint.transform.translationY);
                    break;
                case 53:
                    if (Build.VERSION.SDK_INT < 21) {
                        break;
                    } else {
                        constraint.transform.translationZ = typedArray.getDimension(index, constraint.transform.translationZ);
                        break;
                    }
                case 54:
                    constraint.layout.widthDefault = typedArray.getInt(index, constraint.layout.widthDefault);
                    break;
                case 55:
                    constraint.layout.heightDefault = typedArray.getInt(index, constraint.layout.heightDefault);
                    break;
                case 56:
                    constraint.layout.widthMax = typedArray.getDimensionPixelSize(index, constraint.layout.widthMax);
                    break;
                case 57:
                    constraint.layout.heightMax = typedArray.getDimensionPixelSize(index, constraint.layout.heightMax);
                    break;
                case 58:
                    constraint.layout.widthMin = typedArray.getDimensionPixelSize(index, constraint.layout.widthMin);
                    break;
                case 59:
                    constraint.layout.heightMin = typedArray.getDimensionPixelSize(index, constraint.layout.heightMin);
                    break;
                case 60:
                    constraint.transform.rotation = typedArray.getFloat(index, constraint.transform.rotation);
                    break;
                case 61:
                    constraint.layout.circleConstraint = lookupID(typedArray, index, constraint.layout.circleConstraint);
                    break;
                case 62:
                    constraint.layout.circleRadius = typedArray.getDimensionPixelSize(index, constraint.layout.circleRadius);
                    break;
                case 63:
                    constraint.layout.circleAngle = typedArray.getFloat(index, constraint.layout.circleAngle);
                    break;
                case 64:
                    constraint.motion.mAnimateRelativeTo = lookupID(typedArray, index, constraint.motion.mAnimateRelativeTo);
                    break;
                case 65:
                    if (typedArray.peekValue(index).type != 3) {
                        constraint.motion.mTransitionEasing = Easing.NAMED_EASING[typedArray.getInteger(index, 0)];
                        break;
                    } else {
                        constraint.motion.mTransitionEasing = typedArray.getString(index);
                        break;
                    }
                case 66:
                    constraint.motion.mDrawPath = typedArray.getInt(index, 0);
                    break;
                case 67:
                    constraint.motion.mPathRotate = typedArray.getFloat(index, constraint.motion.mPathRotate);
                    break;
                case 68:
                    constraint.propertySet.mProgress = typedArray.getFloat(index, constraint.propertySet.mProgress);
                    break;
                case 69:
                    constraint.layout.widthPercent = typedArray.getFloat(index, 1.0f);
                    break;
                case 70:
                    constraint.layout.heightPercent = typedArray.getFloat(index, 1.0f);
                    break;
                case 71:
                    Log.e(TAG, "CURRENTLY UNSUPPORTED");
                    break;
                case 72:
                    constraint.layout.mBarrierDirection = typedArray.getInt(index, constraint.layout.mBarrierDirection);
                    break;
                case 73:
                    constraint.layout.mBarrierMargin = typedArray.getDimensionPixelSize(index, constraint.layout.mBarrierMargin);
                    break;
                case 74:
                    constraint.layout.mReferenceIdString = typedArray.getString(index);
                    break;
                case 75:
                    constraint.layout.mBarrierAllowsGoneWidgets = typedArray.getBoolean(index, constraint.layout.mBarrierAllowsGoneWidgets);
                    break;
                case 76:
                    constraint.motion.mPathMotionArc = typedArray.getInt(index, constraint.motion.mPathMotionArc);
                    break;
                case 77:
                    constraint.layout.mConstraintTag = typedArray.getString(index);
                    break;
                case 78:
                    constraint.propertySet.mVisibilityMode = typedArray.getInt(index, constraint.propertySet.mVisibilityMode);
                    break;
                case 79:
                    constraint.motion.mMotionStagger = typedArray.getFloat(index, constraint.motion.mMotionStagger);
                    break;
                case 80:
                    constraint.layout.constrainedWidth = typedArray.getBoolean(index, constraint.layout.constrainedWidth);
                    break;
                case 81:
                    constraint.layout.constrainedHeight = typedArray.getBoolean(index, constraint.layout.constrainedHeight);
                    break;
                case 82:
                    constraint.motion.mAnimateCircleAngleTo = typedArray.getInteger(index, constraint.motion.mAnimateCircleAngleTo);
                    break;
                case 83:
                    constraint.transform.transformPivotTarget = lookupID(typedArray, index, constraint.transform.transformPivotTarget);
                    break;
                case 84:
                    constraint.motion.mQuantizeMotionSteps = typedArray.getInteger(index, constraint.motion.mQuantizeMotionSteps);
                    break;
                case 85:
                    constraint.motion.mQuantizeMotionPhase = typedArray.getFloat(index, constraint.motion.mQuantizeMotionPhase);
                    break;
                case 86:
                    TypedValue peekValue = typedArray.peekValue(index);
                    if (peekValue.type != 1) {
                        if (peekValue.type != 3) {
                            constraint.motion.mQuantizeInterpolatorType = typedArray.getInteger(index, constraint.motion.mQuantizeInterpolatorID);
                            break;
                        } else {
                            constraint.motion.mQuantizeInterpolatorString = typedArray.getString(index);
                            if (constraint.motion.mQuantizeInterpolatorString.indexOf("/") <= 0) {
                                constraint.motion.mQuantizeInterpolatorType = -1;
                                break;
                            } else {
                                constraint.motion.mQuantizeInterpolatorID = typedArray.getResourceId(index, -1);
                                constraint.motion.mQuantizeInterpolatorType = -2;
                                break;
                            }
                        }
                    } else {
                        constraint.motion.mQuantizeInterpolatorID = typedArray.getResourceId(index, -1);
                        if (constraint.motion.mQuantizeInterpolatorID == -1) {
                            break;
                        } else {
                            constraint.motion.mQuantizeInterpolatorType = -2;
                            break;
                        }
                    }
                case 87:
                    Log.w(TAG, "unused attribute 0x" + Integer.toHexString(index) + "   " + mapToConstant.get(index));
                    break;
                case 91:
                    constraint.layout.baselineToTop = lookupID(typedArray, index, constraint.layout.baselineToTop);
                    break;
                case 92:
                    constraint.layout.baselineToBottom = lookupID(typedArray, index, constraint.layout.baselineToBottom);
                    break;
                case 93:
                    constraint.layout.baselineMargin = typedArray.getDimensionPixelSize(index, constraint.layout.baselineMargin);
                    break;
                case 94:
                    constraint.layout.goneBaselineMargin = typedArray.getDimensionPixelSize(index, constraint.layout.goneBaselineMargin);
                    break;
                case 95:
                    parseDimensionConstraints(constraint.layout, typedArray, index, 0);
                    break;
                case 96:
                    parseDimensionConstraints(constraint.layout, typedArray, index, 1);
                    break;
                case 97:
                    constraint.layout.mWrapBehavior = typedArray.getInt(index, constraint.layout.mWrapBehavior);
                    break;
                default:
                    Log.w(TAG, "Unknown attribute 0x" + Integer.toHexString(index) + "   " + mapToConstant.get(index));
                    break;
            }
        }
        if (constraint.layout.mReferenceIdString != null) {
            constraint.layout.mReferenceIds = null;
        }
    }

    private int[] convertReferenceString(View view, String str) {
        int i;
        Object designInformation;
        String[] split = str.split(",");
        Context context = view.getContext();
        int[] iArr = new int[split.length];
        int i2 = 0;
        int i3 = 0;
        while (i2 < split.length) {
            String trim = split[i2].trim();
            try {
                i = R.id.class.getField(trim).getInt((Object) null);
            } catch (Exception unused) {
                i = 0;
            }
            if (i == 0) {
                i = context.getResources().getIdentifier(trim, "id", context.getPackageName());
            }
            if (i == 0 && view.isInEditMode() && (view.getParent() instanceof ConstraintLayout) && (designInformation = ((ConstraintLayout) view.getParent()).getDesignInformation(0, trim)) != null && (designInformation instanceof Integer)) {
                i = ((Integer) designInformation).intValue();
            }
            iArr[i3] = i;
            i2++;
            i3++;
        }
        return i3 != split.length ? Arrays.copyOf(iArr, i3) : iArr;
    }

    public Constraint getConstraint(int i) {
        if (this.mConstraints.containsKey(Integer.valueOf(i))) {
            return this.mConstraints.get(Integer.valueOf(i));
        }
        return null;
    }

    public int[] getKnownIds() {
        Integer[] numArr = (Integer[]) this.mConstraints.keySet().toArray(new Integer[0]);
        int length = numArr.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = numArr[i].intValue();
        }
        return iArr;
    }

    public boolean isForceId() {
        return this.mForceId;
    }

    public void setForceId(boolean z) {
        this.mForceId = z;
    }

    public void setValidateOnParse(boolean z) {
        this.mValidate = z;
    }

    public void dump(MotionScene motionScene, int... iArr) {
        HashSet hashSet;
        Set<Integer> keySet = this.mConstraints.keySet();
        if (iArr.length != 0) {
            hashSet = new HashSet();
            for (int valueOf : iArr) {
                hashSet.add(Integer.valueOf(valueOf));
            }
        } else {
            hashSet = new HashSet(keySet);
        }
        System.out.println(hashSet.size() + " constraints");
        StringBuilder sb = new StringBuilder();
        for (Integer num : (Integer[]) hashSet.toArray(new Integer[0])) {
            Constraint constraint = this.mConstraints.get(num);
            if (constraint != null) {
                sb.append("<Constraint id=");
                sb.append(num);
                sb.append(" \n");
                constraint.layout.dump(motionScene, sb);
                sb.append("/>\n");
            }
        }
        System.out.println(sb.toString());
    }

    static String getLine(Context context, int i, XmlPullParser xmlPullParser) {
        return ".(" + Debug.getName(context, i) + ".xml:" + xmlPullParser.getLineNumber() + ") \"" + xmlPullParser.getName() + "\"";
    }

    static String getDebugName(int i) {
        for (Field field : ConstraintSet.class.getDeclaredFields()) {
            if (field.getName().contains("_") && field.getType() == Integer.TYPE && Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers())) {
                try {
                    if (field.getInt((Object) null) == i) {
                        return field.getName();
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return "UNKNOWN";
    }

    public void writeState(Writer writer, ConstraintLayout constraintLayout, int i) throws IOException {
        writer.write("\n---------------------------------------------\n");
        if ((i & 1) == 1) {
            new WriteXmlEngine(writer, constraintLayout, i).writeLayout();
        } else {
            new WriteJsonEngine(writer, constraintLayout, i).writeLayout();
        }
        writer.write("\n---------------------------------------------\n");
    }

    class WriteXmlEngine {
        private static final String SPACE = "\n       ";
        final String BASELINE = "'baseline'";
        final String BOTTOM = "'bottom'";
        final String END = "'end'";
        final String LEFT = "'left'";
        final String RIGHT = "'right'";
        final String START = "'start'";
        final String TOP = "'top'";
        Context context;
        int flags;
        HashMap<Integer, String> idMap = new HashMap<>();
        ConstraintLayout layout;
        int unknownCount = 0;
        Writer writer;

        WriteXmlEngine(Writer writer2, ConstraintLayout constraintLayout, int i) throws IOException {
            this.writer = writer2;
            this.layout = constraintLayout;
            this.context = constraintLayout.getContext();
            this.flags = i;
        }

        /* access modifiers changed from: package-private */
        public void writeLayout() throws IOException {
            this.writer.write("\n<ConstraintSet>\n");
            for (Integer num : ConstraintSet.this.mConstraints.keySet()) {
                String name = getName(num.intValue());
                this.writer.write("  <Constraint");
                Writer writer2 = this.writer;
                writer2.write("\n       android:id=\"" + name + "\"");
                Layout layout2 = ((Constraint) ConstraintSet.this.mConstraints.get(num)).layout;
                writeBaseDimension("android:layout_width", layout2.mWidth, -5);
                writeBaseDimension("android:layout_height", layout2.mHeight, -5);
                writeVariable("app:layout_constraintGuide_begin", (float) layout2.guideBegin, -1.0f);
                writeVariable("app:layout_constraintGuide_end", (float) layout2.guideEnd, -1.0f);
                writeVariable("app:layout_constraintGuide_percent", layout2.guidePercent, -1.0f);
                writeVariable("app:layout_constraintHorizontal_bias", layout2.horizontalBias, 0.5f);
                writeVariable("app:layout_constraintVertical_bias", layout2.verticalBias, 0.5f);
                writeVariable("app:layout_constraintDimensionRatio", layout2.dimensionRatio, (String) null);
                writeXmlConstraint("app:layout_constraintCircle", layout2.circleConstraint);
                writeVariable("app:layout_constraintCircleRadius", (float) layout2.circleRadius, 0.0f);
                writeVariable("app:layout_constraintCircleAngle", layout2.circleAngle, 0.0f);
                writeVariable("android:orientation", (float) layout2.orientation, -1.0f);
                writeVariable("app:layout_constraintVertical_weight", layout2.verticalWeight, -1.0f);
                writeVariable("app:layout_constraintHorizontal_weight", layout2.horizontalWeight, -1.0f);
                writeVariable("app:layout_constraintHorizontal_chainStyle", (float) layout2.horizontalChainStyle, 0.0f);
                writeVariable("app:layout_constraintVertical_chainStyle", (float) layout2.verticalChainStyle, 0.0f);
                writeVariable("app:barrierDirection", (float) layout2.mBarrierDirection, -1.0f);
                writeVariable("app:barrierMargin", (float) layout2.mBarrierMargin, 0.0f);
                writeDimension("app:layout_marginLeft", layout2.leftMargin, 0);
                writeDimension("app:layout_goneMarginLeft", layout2.goneLeftMargin, Integer.MIN_VALUE);
                writeDimension("app:layout_marginRight", layout2.rightMargin, 0);
                writeDimension("app:layout_goneMarginRight", layout2.goneRightMargin, Integer.MIN_VALUE);
                writeDimension("app:layout_marginStart", layout2.startMargin, 0);
                writeDimension("app:layout_goneMarginStart", layout2.goneStartMargin, Integer.MIN_VALUE);
                writeDimension("app:layout_marginEnd", layout2.endMargin, 0);
                writeDimension("app:layout_goneMarginEnd", layout2.goneEndMargin, Integer.MIN_VALUE);
                writeDimension("app:layout_marginTop", layout2.topMargin, 0);
                writeDimension("app:layout_goneMarginTop", layout2.goneTopMargin, Integer.MIN_VALUE);
                writeDimension("app:layout_marginBottom", layout2.bottomMargin, 0);
                writeDimension("app:layout_goneMarginBottom", layout2.goneBottomMargin, Integer.MIN_VALUE);
                writeDimension("app:goneBaselineMargin", layout2.goneBaselineMargin, Integer.MIN_VALUE);
                writeDimension("app:baselineMargin", layout2.baselineMargin, 0);
                writeBoolen("app:layout_constrainedWidth", layout2.constrainedWidth, false);
                writeBoolen("app:layout_constrainedHeight", layout2.constrainedHeight, false);
                writeBoolen("app:barrierAllowsGoneWidgets", layout2.mBarrierAllowsGoneWidgets, true);
                writeVariable("app:layout_wrapBehaviorInParent", (float) layout2.mWrapBehavior, 0.0f);
                writeXmlConstraint("app:baselineToBaseline", layout2.baselineToBaseline);
                writeXmlConstraint("app:baselineToBottom", layout2.baselineToBottom);
                writeXmlConstraint("app:baselineToTop", layout2.baselineToTop);
                writeXmlConstraint("app:layout_constraintBottom_toBottomOf", layout2.bottomToBottom);
                writeXmlConstraint("app:layout_constraintBottom_toTopOf", layout2.bottomToTop);
                writeXmlConstraint("app:layout_constraintEnd_toEndOf", layout2.endToEnd);
                writeXmlConstraint("app:layout_constraintEnd_toStartOf", layout2.endToStart);
                writeXmlConstraint("app:layout_constraintLeft_toLeftOf", layout2.leftToLeft);
                writeXmlConstraint("app:layout_constraintLeft_toRightOf", layout2.leftToRight);
                writeXmlConstraint("app:layout_constraintRight_toLeftOf", layout2.rightToLeft);
                writeXmlConstraint("app:layout_constraintRight_toRightOf", layout2.rightToRight);
                writeXmlConstraint("app:layout_constraintStart_toEndOf", layout2.startToEnd);
                writeXmlConstraint("app:layout_constraintStart_toStartOf", layout2.startToStart);
                writeXmlConstraint("app:layout_constraintTop_toBottomOf", layout2.topToBottom);
                writeXmlConstraint("app:layout_constraintTop_toTopOf", layout2.topToTop);
                String[] strArr = {"spread", "wrap", "percent"};
                writeEnum("app:layout_constraintHeight_default", layout2.heightDefault, strArr, 0);
                writeVariable("app:layout_constraintHeight_percent", layout2.heightPercent, 1.0f);
                writeDimension("app:layout_constraintHeight_min", layout2.heightMin, 0);
                writeDimension("app:layout_constraintHeight_max", layout2.heightMax, 0);
                writeBoolen("android:layout_constrainedHeight", layout2.constrainedHeight, false);
                writeEnum("app:layout_constraintWidth_default", layout2.widthDefault, strArr, 0);
                writeVariable("app:layout_constraintWidth_percent", layout2.widthPercent, 1.0f);
                writeDimension("app:layout_constraintWidth_min", layout2.widthMin, 0);
                writeDimension("app:layout_constraintWidth_max", layout2.widthMax, 0);
                writeBoolen("android:layout_constrainedWidth", layout2.constrainedWidth, false);
                writeVariable("app:layout_constraintVertical_weight", layout2.verticalWeight, -1.0f);
                writeVariable("app:layout_constraintHorizontal_weight", layout2.horizontalWeight, -1.0f);
                writeVariable("app:layout_constraintHorizontal_chainStyle", layout2.horizontalChainStyle);
                writeVariable("app:layout_constraintVertical_chainStyle", layout2.verticalChainStyle);
                writeEnum("app:barrierDirection", layout2.mBarrierDirection, new String[]{"left", "right", "top", "bottom", "start", "end"}, -1);
                writeVariable("app:layout_constraintTag", layout2.mConstraintTag, (String) null);
                if (layout2.mReferenceIds != null) {
                    writeVariable("'ReferenceIds'", layout2.mReferenceIds);
                }
                this.writer.write(" />\n");
            }
            this.writer.write("</ConstraintSet>\n");
        }

        private void writeBoolen(String str, boolean z, boolean z2) throws IOException {
            if (z != z2) {
                Writer writer2 = this.writer;
                writer2.write(SPACE + str + "=\"" + z + "dp\"");
            }
        }

        private void writeEnum(String str, int i, String[] strArr, int i2) throws IOException {
            if (i != i2) {
                Writer writer2 = this.writer;
                writer2.write(SPACE + str + "=\"" + strArr[i] + "\"");
            }
        }

        private void writeDimension(String str, int i, int i2) throws IOException {
            if (i != i2) {
                Writer writer2 = this.writer;
                writer2.write(SPACE + str + "=\"" + i + "dp\"");
            }
        }

        private void writeBaseDimension(String str, int i, int i2) throws IOException {
            if (i == i2) {
                return;
            }
            if (i == -2) {
                Writer writer2 = this.writer;
                writer2.write(SPACE + str + "=\"wrap_content\"");
            } else if (i == -1) {
                Writer writer3 = this.writer;
                writer3.write(SPACE + str + "=\"match_parent\"");
            } else {
                Writer writer4 = this.writer;
                writer4.write(SPACE + str + "=\"" + i + "dp\"");
            }
        }

        /* access modifiers changed from: package-private */
        public String getName(int i) {
            if (this.idMap.containsKey(Integer.valueOf(i))) {
                return "@+id/" + this.idMap.get(Integer.valueOf(i)) + "";
            } else if (i == 0) {
                return ConstraintSet.KEY_PERCENT_PARENT;
            } else {
                String lookup = lookup(i);
                this.idMap.put(Integer.valueOf(i), lookup);
                return "@+id/" + lookup + "";
            }
        }

        /* access modifiers changed from: package-private */
        public String lookup(int i) {
            if (i != -1) {
                try {
                    return this.context.getResources().getResourceEntryName(i);
                } catch (Exception unused) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(EnvironmentCompat.MEDIA_UNKNOWN);
                    int i2 = this.unknownCount + 1;
                    this.unknownCount = i2;
                    sb.append(i2);
                    return sb.toString();
                }
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(EnvironmentCompat.MEDIA_UNKNOWN);
                int i3 = this.unknownCount + 1;
                this.unknownCount = i3;
                sb2.append(i3);
                return sb2.toString();
            }
        }

        /* access modifiers changed from: package-private */
        public void writeXmlConstraint(String str, int i) throws IOException {
            if (i != -1) {
                Writer writer2 = this.writer;
                writer2.write(SPACE + str);
                Writer writer3 = this.writer;
                writer3.write("=\"" + getName(i) + "\"");
            }
        }

        /* access modifiers changed from: package-private */
        public void writeConstraint(String str, int i, String str2, int i2, int i3) throws IOException {
            if (i != -1) {
                Writer writer2 = this.writer;
                writer2.write(SPACE + str);
                this.writer.write(":[");
                this.writer.write(getName(i));
                this.writer.write(" , ");
                this.writer.write(str2);
                if (i2 != 0) {
                    Writer writer3 = this.writer;
                    writer3.write(" , " + i2);
                }
                this.writer.write("],\n");
            }
        }

        /* access modifiers changed from: package-private */
        public void writeCircle(int i, float f, int i2) throws IOException {
            if (i != -1) {
                this.writer.write("circle");
                this.writer.write(":[");
                this.writer.write(getName(i));
                Writer writer2 = this.writer;
                writer2.write(", " + f);
                Writer writer3 = this.writer;
                writer3.write(i2 + "]");
            }
        }

        /* access modifiers changed from: package-private */
        public void writeVariable(String str, int i) throws IOException {
            if (i != 0 && i != -1) {
                Writer writer2 = this.writer;
                writer2.write(SPACE + str + "=\"" + i + "\"\n");
            }
        }

        /* access modifiers changed from: package-private */
        public void writeVariable(String str, float f, float f2) throws IOException {
            if (f != f2) {
                Writer writer2 = this.writer;
                writer2.write(SPACE + str);
                Writer writer3 = this.writer;
                writer3.write("=\"" + f + "\"");
            }
        }

        /* access modifiers changed from: package-private */
        public void writeVariable(String str, String str2, String str3) throws IOException {
            if (str2 != null && !str2.equals(str3)) {
                Writer writer2 = this.writer;
                writer2.write(SPACE + str);
                Writer writer3 = this.writer;
                writer3.write("=\"" + str2 + "\"");
            }
        }

        /* access modifiers changed from: package-private */
        public void writeVariable(String str, int[] iArr) throws IOException {
            if (iArr != null) {
                Writer writer2 = this.writer;
                writer2.write(SPACE + str);
                this.writer.write(":");
                int i = 0;
                while (i < iArr.length) {
                    Writer writer3 = this.writer;
                    StringBuilder sb = new StringBuilder();
                    sb.append(i == 0 ? "[" : ", ");
                    sb.append(getName(iArr[i]));
                    writer3.write(sb.toString());
                    i++;
                }
                this.writer.write("],\n");
            }
        }

        /* access modifiers changed from: package-private */
        public void writeVariable(String str, String str2) throws IOException {
            if (str2 != null) {
                this.writer.write(str);
                this.writer.write(":");
                Writer writer2 = this.writer;
                writer2.write(", " + str2);
                this.writer.write("\n");
            }
        }
    }

    class WriteJsonEngine {
        private static final String SPACE = "       ";
        final String BASELINE = "'baseline'";
        final String BOTTOM = "'bottom'";
        final String END = "'end'";
        final String LEFT = "'left'";
        final String RIGHT = "'right'";
        final String START = "'start'";
        final String TOP = "'top'";
        Context context;
        int flags;
        HashMap<Integer, String> idMap = new HashMap<>();
        ConstraintLayout layout;
        int unknownCount = 0;
        Writer writer;

        private void writeGuideline(int i, int i2, int i3, float f) {
        }

        WriteJsonEngine(Writer writer2, ConstraintLayout constraintLayout, int i) throws IOException {
            this.writer = writer2;
            this.layout = constraintLayout;
            this.context = constraintLayout.getContext();
            this.flags = i;
        }

        /* access modifiers changed from: package-private */
        public void writeLayout() throws IOException {
            this.writer.write("\n'ConstraintSet':{\n");
            for (Integer num : ConstraintSet.this.mConstraints.keySet()) {
                String name = getName(num.intValue());
                Writer writer2 = this.writer;
                writer2.write(name + ":{\n");
                Layout layout2 = ((Constraint) ConstraintSet.this.mConstraints.get(num)).layout;
                writeDimension("height", layout2.mHeight, layout2.heightDefault, layout2.heightPercent, layout2.heightMin, layout2.heightMax, layout2.constrainedHeight);
                writeDimension("width", layout2.mWidth, layout2.widthDefault, layout2.widthPercent, layout2.widthMin, layout2.widthMax, layout2.constrainedWidth);
                writeConstraint("'left'", layout2.leftToLeft, "'left'", layout2.leftMargin, layout2.goneLeftMargin);
                writeConstraint("'left'", layout2.leftToRight, "'right'", layout2.leftMargin, layout2.goneLeftMargin);
                writeConstraint("'right'", layout2.rightToLeft, "'left'", layout2.rightMargin, layout2.goneRightMargin);
                writeConstraint("'right'", layout2.rightToRight, "'right'", layout2.rightMargin, layout2.goneRightMargin);
                writeConstraint("'baseline'", layout2.baselineToBaseline, "'baseline'", -1, layout2.goneBaselineMargin);
                writeConstraint("'baseline'", layout2.baselineToTop, "'top'", -1, layout2.goneBaselineMargin);
                writeConstraint("'baseline'", layout2.baselineToBottom, "'bottom'", -1, layout2.goneBaselineMargin);
                writeConstraint("'top'", layout2.topToBottom, "'bottom'", layout2.topMargin, layout2.goneTopMargin);
                writeConstraint("'top'", layout2.topToTop, "'top'", layout2.topMargin, layout2.goneTopMargin);
                writeConstraint("'bottom'", layout2.bottomToBottom, "'bottom'", layout2.bottomMargin, layout2.goneBottomMargin);
                writeConstraint("'bottom'", layout2.bottomToTop, "'top'", layout2.bottomMargin, layout2.goneBottomMargin);
                writeConstraint("'start'", layout2.startToStart, "'start'", layout2.startMargin, layout2.goneStartMargin);
                writeConstraint("'start'", layout2.startToEnd, "'end'", layout2.startMargin, layout2.goneStartMargin);
                writeConstraint("'end'", layout2.endToStart, "'start'", layout2.endMargin, layout2.goneEndMargin);
                writeConstraint("'end'", layout2.endToEnd, "'end'", layout2.endMargin, layout2.goneEndMargin);
                writeVariable("'horizontalBias'", layout2.horizontalBias, 0.5f);
                writeVariable("'verticalBias'", layout2.verticalBias, 0.5f);
                writeCircle(layout2.circleConstraint, layout2.circleAngle, layout2.circleRadius);
                writeGuideline(layout2.orientation, layout2.guideBegin, layout2.guideEnd, layout2.guidePercent);
                writeVariable("'dimensionRatio'", layout2.dimensionRatio);
                writeVariable("'barrierMargin'", layout2.mBarrierMargin);
                writeVariable("'type'", layout2.mHelperType);
                writeVariable("'ReferenceId'", layout2.mReferenceIdString);
                writeVariable("'mBarrierAllowsGoneWidgets'", layout2.mBarrierAllowsGoneWidgets, true);
                writeVariable("'WrapBehavior'", layout2.mWrapBehavior);
                writeVariable("'verticalWeight'", layout2.verticalWeight);
                writeVariable("'horizontalWeight'", layout2.horizontalWeight);
                writeVariable("'horizontalChainStyle'", layout2.horizontalChainStyle);
                writeVariable("'verticalChainStyle'", layout2.verticalChainStyle);
                writeVariable("'barrierDirection'", layout2.mBarrierDirection);
                if (layout2.mReferenceIds != null) {
                    writeVariable("'ReferenceIds'", layout2.mReferenceIds);
                }
                this.writer.write("}\n");
            }
            this.writer.write("}\n");
        }

        private void writeDimension(String str, int i, int i2, float f, int i3, int i4, boolean z) throws IOException {
            if (i == 0) {
                if (i4 == -1 && i3 == -1) {
                    if (i2 == 1) {
                        Writer writer2 = this.writer;
                        writer2.write(SPACE + str + ": '???????????',\n");
                    } else if (i2 == 2) {
                        Writer writer3 = this.writer;
                        writer3.write(SPACE + str + ": '" + f + "%',\n");
                    }
                } else if (i2 == 0) {
                    Writer writer4 = this.writer;
                    writer4.write(SPACE + str + ": {'spread' ," + i3 + ", " + i4 + "}\n");
                } else if (i2 == 1) {
                    Writer writer5 = this.writer;
                    writer5.write(SPACE + str + ": {'wrap' ," + i3 + ", " + i4 + "}\n");
                } else if (i2 == 2) {
                    Writer writer6 = this.writer;
                    writer6.write(SPACE + str + ": {'" + f + "'% ," + i3 + ", " + i4 + "}\n");
                }
            } else if (i == -2) {
                Writer writer7 = this.writer;
                writer7.write(SPACE + str + ": 'wrap'\n");
            } else if (i == -1) {
                Writer writer8 = this.writer;
                writer8.write(SPACE + str + ": 'parent'\n");
            } else {
                Writer writer9 = this.writer;
                writer9.write(SPACE + str + ": " + i + ",\n");
            }
        }

        /* access modifiers changed from: package-private */
        public String getName(int i) {
            if (this.idMap.containsKey(Integer.valueOf(i))) {
                return "'" + this.idMap.get(Integer.valueOf(i)) + "'";
            } else if (i == 0) {
                return "'parent'";
            } else {
                String lookup = lookup(i);
                this.idMap.put(Integer.valueOf(i), lookup);
                return "'" + lookup + "'";
            }
        }

        /* access modifiers changed from: package-private */
        public String lookup(int i) {
            if (i != -1) {
                try {
                    return this.context.getResources().getResourceEntryName(i);
                } catch (Exception unused) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(EnvironmentCompat.MEDIA_UNKNOWN);
                    int i2 = this.unknownCount + 1;
                    this.unknownCount = i2;
                    sb.append(i2);
                    return sb.toString();
                }
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(EnvironmentCompat.MEDIA_UNKNOWN);
                int i3 = this.unknownCount + 1;
                this.unknownCount = i3;
                sb2.append(i3);
                return sb2.toString();
            }
        }

        /* access modifiers changed from: package-private */
        public void writeConstraint(String str, int i, String str2, int i2, int i3) throws IOException {
            if (i != -1) {
                Writer writer2 = this.writer;
                writer2.write(SPACE + str);
                this.writer.write(":[");
                this.writer.write(getName(i));
                this.writer.write(" , ");
                this.writer.write(str2);
                if (i2 != 0) {
                    Writer writer3 = this.writer;
                    writer3.write(" , " + i2);
                }
                this.writer.write("],\n");
            }
        }

        /* access modifiers changed from: package-private */
        public void writeCircle(int i, float f, int i2) throws IOException {
            if (i != -1) {
                this.writer.write("       circle");
                this.writer.write(":[");
                this.writer.write(getName(i));
                Writer writer2 = this.writer;
                writer2.write(", " + f);
                Writer writer3 = this.writer;
                writer3.write(i2 + "]");
            }
        }

        /* access modifiers changed from: package-private */
        public void writeVariable(String str, int i) throws IOException {
            if (i != 0 && i != -1) {
                Writer writer2 = this.writer;
                writer2.write(SPACE + str);
                this.writer.write(":");
                Writer writer3 = this.writer;
                writer3.write(", " + i);
                this.writer.write("\n");
            }
        }

        /* access modifiers changed from: package-private */
        public void writeVariable(String str, float f) throws IOException {
            if (f != -1.0f) {
                Writer writer2 = this.writer;
                writer2.write(SPACE + str);
                Writer writer3 = this.writer;
                writer3.write(": " + f);
                this.writer.write(",\n");
            }
        }

        /* access modifiers changed from: package-private */
        public void writeVariable(String str, float f, float f2) throws IOException {
            if (f != f2) {
                Writer writer2 = this.writer;
                writer2.write(SPACE + str);
                Writer writer3 = this.writer;
                writer3.write(": " + f);
                this.writer.write(",\n");
            }
        }

        /* access modifiers changed from: package-private */
        public void writeVariable(String str, boolean z) throws IOException {
            if (z) {
                Writer writer2 = this.writer;
                writer2.write(SPACE + str);
                Writer writer3 = this.writer;
                writer3.write(": " + z);
                this.writer.write(",\n");
            }
        }

        /* access modifiers changed from: package-private */
        public void writeVariable(String str, boolean z, boolean z2) throws IOException {
            if (z != z2) {
                Writer writer2 = this.writer;
                writer2.write(SPACE + str);
                Writer writer3 = this.writer;
                writer3.write(": " + z);
                this.writer.write(",\n");
            }
        }

        /* access modifiers changed from: package-private */
        public void writeVariable(String str, int[] iArr) throws IOException {
            if (iArr != null) {
                Writer writer2 = this.writer;
                writer2.write(SPACE + str);
                this.writer.write(": ");
                int i = 0;
                while (i < iArr.length) {
                    Writer writer3 = this.writer;
                    StringBuilder sb = new StringBuilder();
                    sb.append(i == 0 ? "[" : ", ");
                    sb.append(getName(iArr[i]));
                    writer3.write(sb.toString());
                    i++;
                }
                this.writer.write("],\n");
            }
        }

        /* access modifiers changed from: package-private */
        public void writeVariable(String str, String str2) throws IOException {
            if (str2 != null) {
                Writer writer2 = this.writer;
                writer2.write(SPACE + str);
                this.writer.write(":");
                Writer writer3 = this.writer;
                writer3.write(", " + str2);
                this.writer.write("\n");
            }
        }
    }
}
