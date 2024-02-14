package androidx.constraintlayout.core.motion.utils;

public interface TypedValues {
    public static final int BOOLEAN_MASK = 1;
    public static final int FLOAT_MASK = 4;
    public static final int INT_MASK = 2;
    public static final int STRING_MASK = 8;
    public static final String S_CUSTOM = "CUSTOM";
    public static final int TYPE_FRAME_POSITION = 100;
    public static final int TYPE_TARGET = 101;

    public interface OnSwipe {
        public static final String AUTOCOMPLETE_MODE = "autocompletemode";
        public static final String[] AUTOCOMPLETE_MODE_ENUM = {"continuousVelocity", "spring"};
        public static final String DRAG_DIRECTION = "dragdirection";
        public static final String DRAG_SCALE = "dragscale";
        public static final String DRAG_THRESHOLD = "dragthreshold";
        public static final String LIMIT_BOUNDS_TO = "limitboundsto";
        public static final String MAX_ACCELERATION = "maxacceleration";
        public static final String MAX_VELOCITY = "maxvelocity";
        public static final String MOVE_WHEN_SCROLLAT_TOP = "movewhenscrollattop";
        public static final String NESTED_SCROLL_FLAGS = "nestedscrollflags";
        public static final String[] NESTED_SCROLL_FLAGS_ENUM = {"none", "disablePostScroll", "disableScroll", "supportScrollUp"};
        public static final String ON_TOUCH_UP = "ontouchup";
        public static final String[] ON_TOUCH_UP_ENUM = {"autoComplete", "autoCompleteToStart", "autoCompleteToEnd", "stop", "decelerate", "decelerateAndComplete", "neverCompleteToStart", "neverCompleteToEnd"};
        public static final String ROTATION_CENTER_ID = "rotationcenterid";
        public static final String SPRINGS_TOP_THRESHOLD = "springstopthreshold";
        public static final String SPRING_BOUNDARY = "springboundary";
        public static final String[] SPRING_BOUNDARY_ENUM = {"overshoot", "bounceStart", "bounceEnd", "bounceBoth"};
        public static final String SPRING_DAMPING = "springdamping";
        public static final String SPRING_MASS = "springmass";
        public static final String SPRING_STIFFNESS = "springstiffness";
        public static final String TOUCH_ANCHOR_ID = "touchanchorid";
        public static final String TOUCH_ANCHOR_SIDE = "touchanchorside";
        public static final String TOUCH_REGION_ID = "touchregionid";
    }

    int getId(String str);

    boolean setValue(int i, float f);

    boolean setValue(int i, int i2);

    boolean setValue(int i, String str);

    boolean setValue(int i, boolean z);

    public interface AttributesType {
        public static final String[] KEY_WORDS = {"curveFit", "visibility", "alpha", "translationX", "translationY", "translationZ", "elevation", "rotationX", "rotationY", "rotationZ", "scaleX", "scaleY", "pivotX", "pivotY", "progress", "pathRotate", "easing", "CUSTOM", S_FRAME, S_TARGET, S_PIVOT_TARGET};
        public static final String NAME = "KeyAttributes";
        public static final String S_ALPHA = "alpha";
        public static final String S_CURVE_FIT = "curveFit";
        public static final String S_CUSTOM = "CUSTOM";
        public static final String S_EASING = "easing";
        public static final String S_ELEVATION = "elevation";
        public static final String S_FRAME = "frame";
        public static final String S_PATH_ROTATE = "pathRotate";
        public static final String S_PIVOT_TARGET = "pivotTarget";
        public static final String S_PIVOT_X = "pivotX";
        public static final String S_PIVOT_Y = "pivotY";
        public static final String S_PROGRESS = "progress";
        public static final String S_ROTATION_X = "rotationX";
        public static final String S_ROTATION_Y = "rotationY";
        public static final String S_ROTATION_Z = "rotationZ";
        public static final String S_SCALE_X = "scaleX";
        public static final String S_SCALE_Y = "scaleY";
        public static final String S_TARGET = "target";
        public static final String S_TRANSLATION_X = "translationX";
        public static final String S_TRANSLATION_Y = "translationY";
        public static final String S_TRANSLATION_Z = "translationZ";
        public static final String S_VISIBILITY = "visibility";
        public static final int TYPE_ALPHA = 303;
        public static final int TYPE_CURVE_FIT = 301;
        public static final int TYPE_EASING = 317;
        public static final int TYPE_ELEVATION = 307;
        public static final int TYPE_PATH_ROTATE = 316;
        public static final int TYPE_PIVOT_TARGET = 318;
        public static final int TYPE_PIVOT_X = 313;
        public static final int TYPE_PIVOT_Y = 314;
        public static final int TYPE_PROGRESS = 315;
        public static final int TYPE_ROTATION_X = 308;
        public static final int TYPE_ROTATION_Y = 309;
        public static final int TYPE_ROTATION_Z = 310;
        public static final int TYPE_SCALE_X = 311;
        public static final int TYPE_SCALE_Y = 312;
        public static final int TYPE_TRANSLATION_X = 304;
        public static final int TYPE_TRANSLATION_Y = 305;
        public static final int TYPE_TRANSLATION_Z = 306;
        public static final int TYPE_VISIBILITY = 302;

        /* renamed from: androidx.constraintlayout.core.motion.utils.TypedValues$AttributesType$-CC  reason: invalid class name */
        public final /* synthetic */ class CC {
            static {
                String str = AttributesType.NAME;
            }

            public static int getType(int i) {
                if (i == 100) {
                    return 2;
                }
                if (i == 101) {
                    return 8;
                }
                switch (i) {
                    case 301:
                    case 302:
                        return 2;
                    case 303:
                    case 304:
                    case 305:
                    case 306:
                    case 307:
                    case 308:
                    case 309:
                    case 310:
                    case 311:
                    case 312:
                    case 313:
                    case 314:
                    case 315:
                    case AttributesType.TYPE_PATH_ROTATE /*316*/:
                        return 4;
                    case AttributesType.TYPE_EASING /*317*/:
                    case AttributesType.TYPE_PIVOT_TARGET /*318*/:
                        return 8;
                    default:
                        return -1;
                }
            }

            /* JADX WARNING: Can't fix incorrect switch cases order */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public static int getId(java.lang.String r2) {
                /*
                    r2.hashCode()
                    int r0 = r2.hashCode()
                    r1 = -1
                    switch(r0) {
                        case -1310311125: goto L_0x0101;
                        case -1249320806: goto L_0x00f5;
                        case -1249320805: goto L_0x00e9;
                        case -1249320804: goto L_0x00dd;
                        case -1225497657: goto L_0x00d1;
                        case -1225497656: goto L_0x00c5;
                        case -1225497655: goto L_0x00b9;
                        case -1001078227: goto L_0x00ad;
                        case -987906986: goto L_0x009f;
                        case -987906985: goto L_0x0091;
                        case -908189618: goto L_0x0083;
                        case -908189617: goto L_0x0076;
                        case -880905839: goto L_0x0069;
                        case -4379043: goto L_0x005c;
                        case 92909918: goto L_0x004f;
                        case 97692013: goto L_0x0042;
                        case 579057826: goto L_0x0035;
                        case 803192288: goto L_0x0028;
                        case 1167159411: goto L_0x001b;
                        case 1941332754: goto L_0x000e;
                        default: goto L_0x000b;
                    }
                L_0x000b:
                    r2 = -1
                    goto L_0x010c
                L_0x000e:
                    java.lang.String r0 = "visibility"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0017
                    goto L_0x000b
                L_0x0017:
                    r2 = 19
                    goto L_0x010c
                L_0x001b:
                    java.lang.String r0 = "pivotTarget"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0024
                    goto L_0x000b
                L_0x0024:
                    r2 = 18
                    goto L_0x010c
                L_0x0028:
                    java.lang.String r0 = "pathRotate"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0031
                    goto L_0x000b
                L_0x0031:
                    r2 = 17
                    goto L_0x010c
                L_0x0035:
                    java.lang.String r0 = "curveFit"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x003e
                    goto L_0x000b
                L_0x003e:
                    r2 = 16
                    goto L_0x010c
                L_0x0042:
                    java.lang.String r0 = "frame"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x004b
                    goto L_0x000b
                L_0x004b:
                    r2 = 15
                    goto L_0x010c
                L_0x004f:
                    java.lang.String r0 = "alpha"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0058
                    goto L_0x000b
                L_0x0058:
                    r2 = 14
                    goto L_0x010c
                L_0x005c:
                    java.lang.String r0 = "elevation"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0065
                    goto L_0x000b
                L_0x0065:
                    r2 = 13
                    goto L_0x010c
                L_0x0069:
                    java.lang.String r0 = "target"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0072
                    goto L_0x000b
                L_0x0072:
                    r2 = 12
                    goto L_0x010c
                L_0x0076:
                    java.lang.String r0 = "scaleY"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x007f
                    goto L_0x000b
                L_0x007f:
                    r2 = 11
                    goto L_0x010c
                L_0x0083:
                    java.lang.String r0 = "scaleX"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x008d
                    goto L_0x000b
                L_0x008d:
                    r2 = 10
                    goto L_0x010c
                L_0x0091:
                    java.lang.String r0 = "pivotY"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x009b
                    goto L_0x000b
                L_0x009b:
                    r2 = 9
                    goto L_0x010c
                L_0x009f:
                    java.lang.String r0 = "pivotX"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x00a9
                    goto L_0x000b
                L_0x00a9:
                    r2 = 8
                    goto L_0x010c
                L_0x00ad:
                    java.lang.String r0 = "progress"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x00b7
                    goto L_0x000b
                L_0x00b7:
                    r2 = 7
                    goto L_0x010c
                L_0x00b9:
                    java.lang.String r0 = "translationZ"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x00c3
                    goto L_0x000b
                L_0x00c3:
                    r2 = 6
                    goto L_0x010c
                L_0x00c5:
                    java.lang.String r0 = "translationY"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x00cf
                    goto L_0x000b
                L_0x00cf:
                    r2 = 5
                    goto L_0x010c
                L_0x00d1:
                    java.lang.String r0 = "translationX"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x00db
                    goto L_0x000b
                L_0x00db:
                    r2 = 4
                    goto L_0x010c
                L_0x00dd:
                    java.lang.String r0 = "rotationZ"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x00e7
                    goto L_0x000b
                L_0x00e7:
                    r2 = 3
                    goto L_0x010c
                L_0x00e9:
                    java.lang.String r0 = "rotationY"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x00f3
                    goto L_0x000b
                L_0x00f3:
                    r2 = 2
                    goto L_0x010c
                L_0x00f5:
                    java.lang.String r0 = "rotationX"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x00ff
                    goto L_0x000b
                L_0x00ff:
                    r2 = 1
                    goto L_0x010c
                L_0x0101:
                    java.lang.String r0 = "easing"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x010b
                    goto L_0x000b
                L_0x010b:
                    r2 = 0
                L_0x010c:
                    switch(r2) {
                        case 0: goto L_0x0149;
                        case 1: goto L_0x0146;
                        case 2: goto L_0x0143;
                        case 3: goto L_0x0140;
                        case 4: goto L_0x013d;
                        case 5: goto L_0x013a;
                        case 6: goto L_0x0137;
                        case 7: goto L_0x0134;
                        case 8: goto L_0x0131;
                        case 9: goto L_0x012e;
                        case 10: goto L_0x012b;
                        case 11: goto L_0x0128;
                        case 12: goto L_0x0125;
                        case 13: goto L_0x0122;
                        case 14: goto L_0x011f;
                        case 15: goto L_0x011c;
                        case 16: goto L_0x0119;
                        case 17: goto L_0x0116;
                        case 18: goto L_0x0113;
                        case 19: goto L_0x0110;
                        default: goto L_0x010f;
                    }
                L_0x010f:
                    return r1
                L_0x0110:
                    r2 = 302(0x12e, float:4.23E-43)
                    return r2
                L_0x0113:
                    r2 = 318(0x13e, float:4.46E-43)
                    return r2
                L_0x0116:
                    r2 = 316(0x13c, float:4.43E-43)
                    return r2
                L_0x0119:
                    r2 = 301(0x12d, float:4.22E-43)
                    return r2
                L_0x011c:
                    r2 = 100
                    return r2
                L_0x011f:
                    r2 = 303(0x12f, float:4.25E-43)
                    return r2
                L_0x0122:
                    r2 = 307(0x133, float:4.3E-43)
                    return r2
                L_0x0125:
                    r2 = 101(0x65, float:1.42E-43)
                    return r2
                L_0x0128:
                    r2 = 312(0x138, float:4.37E-43)
                    return r2
                L_0x012b:
                    r2 = 311(0x137, float:4.36E-43)
                    return r2
                L_0x012e:
                    r2 = 314(0x13a, float:4.4E-43)
                    return r2
                L_0x0131:
                    r2 = 313(0x139, float:4.39E-43)
                    return r2
                L_0x0134:
                    r2 = 315(0x13b, float:4.41E-43)
                    return r2
                L_0x0137:
                    r2 = 306(0x132, float:4.29E-43)
                    return r2
                L_0x013a:
                    r2 = 305(0x131, float:4.27E-43)
                    return r2
                L_0x013d:
                    r2 = 304(0x130, float:4.26E-43)
                    return r2
                L_0x0140:
                    r2 = 310(0x136, float:4.34E-43)
                    return r2
                L_0x0143:
                    r2 = 309(0x135, float:4.33E-43)
                    return r2
                L_0x0146:
                    r2 = 308(0x134, float:4.32E-43)
                    return r2
                L_0x0149:
                    r2 = 317(0x13d, float:4.44E-43)
                    return r2
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.motion.utils.TypedValues.AttributesType.CC.getId(java.lang.String):int");
            }
        }
    }

    public interface CycleType {
        public static final String[] KEY_WORDS = {"curveFit", "visibility", "alpha", "translationX", "translationY", "translationZ", "elevation", "rotationX", "rotationY", "rotationZ", "scaleX", "scaleY", "pivotX", "pivotY", "progress", "pathRotate", "easing", "waveShape", S_CUSTOM_WAVE_SHAPE, S_WAVE_PERIOD, S_WAVE_OFFSET, S_WAVE_PHASE};
        public static final String NAME = "KeyCycle";
        public static final String S_ALPHA = "alpha";
        public static final String S_CURVE_FIT = "curveFit";
        public static final String S_CUSTOM_WAVE_SHAPE = "customWave";
        public static final String S_EASING = "easing";
        public static final String S_ELEVATION = "elevation";
        public static final String S_PATH_ROTATE = "pathRotate";
        public static final String S_PIVOT_X = "pivotX";
        public static final String S_PIVOT_Y = "pivotY";
        public static final String S_PROGRESS = "progress";
        public static final String S_ROTATION_X = "rotationX";
        public static final String S_ROTATION_Y = "rotationY";
        public static final String S_ROTATION_Z = "rotationZ";
        public static final String S_SCALE_X = "scaleX";
        public static final String S_SCALE_Y = "scaleY";
        public static final String S_TRANSLATION_X = "translationX";
        public static final String S_TRANSLATION_Y = "translationY";
        public static final String S_TRANSLATION_Z = "translationZ";
        public static final String S_VISIBILITY = "visibility";
        public static final String S_WAVE_OFFSET = "offset";
        public static final String S_WAVE_PERIOD = "period";
        public static final String S_WAVE_PHASE = "phase";
        public static final String S_WAVE_SHAPE = "waveShape";
        public static final int TYPE_ALPHA = 403;
        public static final int TYPE_CURVE_FIT = 401;
        public static final int TYPE_CUSTOM_WAVE_SHAPE = 422;
        public static final int TYPE_EASING = 420;
        public static final int TYPE_ELEVATION = 307;
        public static final int TYPE_PATH_ROTATE = 416;
        public static final int TYPE_PIVOT_X = 313;
        public static final int TYPE_PIVOT_Y = 314;
        public static final int TYPE_PROGRESS = 315;
        public static final int TYPE_ROTATION_X = 308;
        public static final int TYPE_ROTATION_Y = 309;
        public static final int TYPE_ROTATION_Z = 310;
        public static final int TYPE_SCALE_X = 311;
        public static final int TYPE_SCALE_Y = 312;
        public static final int TYPE_TRANSLATION_X = 304;
        public static final int TYPE_TRANSLATION_Y = 305;
        public static final int TYPE_TRANSLATION_Z = 306;
        public static final int TYPE_VISIBILITY = 402;
        public static final int TYPE_WAVE_OFFSET = 424;
        public static final int TYPE_WAVE_PERIOD = 423;
        public static final int TYPE_WAVE_PHASE = 425;
        public static final int TYPE_WAVE_SHAPE = 421;

        /* renamed from: androidx.constraintlayout.core.motion.utils.TypedValues$CycleType$-CC  reason: invalid class name */
        public final /* synthetic */ class CC {
            static {
                String str = CycleType.NAME;
            }

            public static int getType(int i) {
                if (i == 100) {
                    return 2;
                }
                if (i == 101) {
                    return 8;
                }
                if (i == 416) {
                    return 4;
                }
                if (i == 420 || i == 421) {
                    return 8;
                }
                switch (i) {
                    case 304:
                    case 305:
                    case 306:
                    case 307:
                    case 308:
                    case 309:
                    case 310:
                    case 311:
                    case 312:
                    case 313:
                    case 314:
                    case 315:
                        return 4;
                    default:
                        switch (i) {
                            case CycleType.TYPE_CURVE_FIT /*401*/:
                            case CycleType.TYPE_VISIBILITY /*402*/:
                                return 2;
                            case CycleType.TYPE_ALPHA /*403*/:
                                return 4;
                            default:
                                switch (i) {
                                    case CycleType.TYPE_WAVE_PERIOD /*423*/:
                                    case CycleType.TYPE_WAVE_OFFSET /*424*/:
                                    case CycleType.TYPE_WAVE_PHASE /*425*/:
                                        return 4;
                                    default:
                                        return -1;
                                }
                        }
                }
            }

            /* JADX WARNING: Can't fix incorrect switch cases order */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public static int getId(java.lang.String r2) {
                /*
                    r2.hashCode()
                    int r0 = r2.hashCode()
                    r1 = -1
                    switch(r0) {
                        case -1310311125: goto L_0x00c8;
                        case -1249320806: goto L_0x00bc;
                        case -1249320805: goto L_0x00b0;
                        case -1249320804: goto L_0x00a4;
                        case -1225497657: goto L_0x0098;
                        case -1225497656: goto L_0x008c;
                        case -1225497655: goto L_0x0081;
                        case -1001078227: goto L_0x0076;
                        case -987906986: goto L_0x0069;
                        case -987906985: goto L_0x005c;
                        case -908189618: goto L_0x004f;
                        case -908189617: goto L_0x0042;
                        case 92909918: goto L_0x0035;
                        case 579057826: goto L_0x0028;
                        case 803192288: goto L_0x001b;
                        case 1941332754: goto L_0x000e;
                        default: goto L_0x000b;
                    }
                L_0x000b:
                    r2 = -1
                    goto L_0x00d3
                L_0x000e:
                    java.lang.String r0 = "visibility"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0017
                    goto L_0x000b
                L_0x0017:
                    r2 = 15
                    goto L_0x00d3
                L_0x001b:
                    java.lang.String r0 = "pathRotate"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0024
                    goto L_0x000b
                L_0x0024:
                    r2 = 14
                    goto L_0x00d3
                L_0x0028:
                    java.lang.String r0 = "curveFit"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0031
                    goto L_0x000b
                L_0x0031:
                    r2 = 13
                    goto L_0x00d3
                L_0x0035:
                    java.lang.String r0 = "alpha"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x003e
                    goto L_0x000b
                L_0x003e:
                    r2 = 12
                    goto L_0x00d3
                L_0x0042:
                    java.lang.String r0 = "scaleY"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x004b
                    goto L_0x000b
                L_0x004b:
                    r2 = 11
                    goto L_0x00d3
                L_0x004f:
                    java.lang.String r0 = "scaleX"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0058
                    goto L_0x000b
                L_0x0058:
                    r2 = 10
                    goto L_0x00d3
                L_0x005c:
                    java.lang.String r0 = "pivotY"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0065
                    goto L_0x000b
                L_0x0065:
                    r2 = 9
                    goto L_0x00d3
                L_0x0069:
                    java.lang.String r0 = "pivotX"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0072
                    goto L_0x000b
                L_0x0072:
                    r2 = 8
                    goto L_0x00d3
                L_0x0076:
                    java.lang.String r0 = "progress"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x007f
                    goto L_0x000b
                L_0x007f:
                    r2 = 7
                    goto L_0x00d3
                L_0x0081:
                    java.lang.String r0 = "translationZ"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x008a
                    goto L_0x000b
                L_0x008a:
                    r2 = 6
                    goto L_0x00d3
                L_0x008c:
                    java.lang.String r0 = "translationY"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0096
                    goto L_0x000b
                L_0x0096:
                    r2 = 5
                    goto L_0x00d3
                L_0x0098:
                    java.lang.String r0 = "translationX"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x00a2
                    goto L_0x000b
                L_0x00a2:
                    r2 = 4
                    goto L_0x00d3
                L_0x00a4:
                    java.lang.String r0 = "rotationZ"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x00ae
                    goto L_0x000b
                L_0x00ae:
                    r2 = 3
                    goto L_0x00d3
                L_0x00b0:
                    java.lang.String r0 = "rotationY"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x00ba
                    goto L_0x000b
                L_0x00ba:
                    r2 = 2
                    goto L_0x00d3
                L_0x00bc:
                    java.lang.String r0 = "rotationX"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x00c6
                    goto L_0x000b
                L_0x00c6:
                    r2 = 1
                    goto L_0x00d3
                L_0x00c8:
                    java.lang.String r0 = "easing"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x00d2
                    goto L_0x000b
                L_0x00d2:
                    r2 = 0
                L_0x00d3:
                    switch(r2) {
                        case 0: goto L_0x0104;
                        case 1: goto L_0x0101;
                        case 2: goto L_0x00fe;
                        case 3: goto L_0x00fb;
                        case 4: goto L_0x00f8;
                        case 5: goto L_0x00f5;
                        case 6: goto L_0x00f2;
                        case 7: goto L_0x00ef;
                        case 8: goto L_0x00ec;
                        case 9: goto L_0x00e9;
                        case 10: goto L_0x00e6;
                        case 11: goto L_0x00e3;
                        case 12: goto L_0x00e0;
                        case 13: goto L_0x00dd;
                        case 14: goto L_0x00da;
                        case 15: goto L_0x00d7;
                        default: goto L_0x00d6;
                    }
                L_0x00d6:
                    return r1
                L_0x00d7:
                    r2 = 402(0x192, float:5.63E-43)
                    return r2
                L_0x00da:
                    r2 = 416(0x1a0, float:5.83E-43)
                    return r2
                L_0x00dd:
                    r2 = 401(0x191, float:5.62E-43)
                    return r2
                L_0x00e0:
                    r2 = 403(0x193, float:5.65E-43)
                    return r2
                L_0x00e3:
                    r2 = 312(0x138, float:4.37E-43)
                    return r2
                L_0x00e6:
                    r2 = 311(0x137, float:4.36E-43)
                    return r2
                L_0x00e9:
                    r2 = 314(0x13a, float:4.4E-43)
                    return r2
                L_0x00ec:
                    r2 = 313(0x139, float:4.39E-43)
                    return r2
                L_0x00ef:
                    r2 = 315(0x13b, float:4.41E-43)
                    return r2
                L_0x00f2:
                    r2 = 306(0x132, float:4.29E-43)
                    return r2
                L_0x00f5:
                    r2 = 305(0x131, float:4.27E-43)
                    return r2
                L_0x00f8:
                    r2 = 304(0x130, float:4.26E-43)
                    return r2
                L_0x00fb:
                    r2 = 310(0x136, float:4.34E-43)
                    return r2
                L_0x00fe:
                    r2 = 309(0x135, float:4.33E-43)
                    return r2
                L_0x0101:
                    r2 = 308(0x134, float:4.32E-43)
                    return r2
                L_0x0104:
                    r2 = 420(0x1a4, float:5.89E-43)
                    return r2
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.motion.utils.TypedValues.CycleType.CC.getId(java.lang.String):int");
            }
        }
    }

    public interface TriggerType {
        public static final String CROSS = "CROSS";
        public static final String[] KEY_WORDS = {"viewTransitionOnCross", "viewTransitionOnPositiveCross", "viewTransitionOnNegativeCross", "postLayout", "triggerSlack", "triggerCollisionView", "triggerCollisionId", "triggerID", "positiveCross", "negativeCross", "triggerReceiver", "CROSS"};
        public static final String NAME = "KeyTrigger";
        public static final String NEGATIVE_CROSS = "negativeCross";
        public static final String POSITIVE_CROSS = "positiveCross";
        public static final String POST_LAYOUT = "postLayout";
        public static final String TRIGGER_COLLISION_ID = "triggerCollisionId";
        public static final String TRIGGER_COLLISION_VIEW = "triggerCollisionView";
        public static final String TRIGGER_ID = "triggerID";
        public static final String TRIGGER_RECEIVER = "triggerReceiver";
        public static final String TRIGGER_SLACK = "triggerSlack";
        public static final int TYPE_CROSS = 312;
        public static final int TYPE_NEGATIVE_CROSS = 310;
        public static final int TYPE_POSITIVE_CROSS = 309;
        public static final int TYPE_POST_LAYOUT = 304;
        public static final int TYPE_TRIGGER_COLLISION_ID = 307;
        public static final int TYPE_TRIGGER_COLLISION_VIEW = 306;
        public static final int TYPE_TRIGGER_ID = 308;
        public static final int TYPE_TRIGGER_RECEIVER = 311;
        public static final int TYPE_TRIGGER_SLACK = 305;
        public static final int TYPE_VIEW_TRANSITION_ON_CROSS = 301;
        public static final int TYPE_VIEW_TRANSITION_ON_NEGATIVE_CROSS = 303;
        public static final int TYPE_VIEW_TRANSITION_ON_POSITIVE_CROSS = 302;
        public static final String VIEW_TRANSITION_ON_CROSS = "viewTransitionOnCross";
        public static final String VIEW_TRANSITION_ON_NEGATIVE_CROSS = "viewTransitionOnNegativeCross";
        public static final String VIEW_TRANSITION_ON_POSITIVE_CROSS = "viewTransitionOnPositiveCross";

        /* renamed from: androidx.constraintlayout.core.motion.utils.TypedValues$TriggerType$-CC  reason: invalid class name */
        public final /* synthetic */ class CC {
            static {
                String str = TriggerType.NAME;
            }

            /* JADX WARNING: Can't fix incorrect switch cases order */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public static int getId(java.lang.String r2) {
                /*
                    r2.hashCode()
                    int r0 = r2.hashCode()
                    r1 = -1
                    switch(r0) {
                        case -1594793529: goto L_0x0090;
                        case -966421266: goto L_0x0084;
                        case -786670827: goto L_0x0079;
                        case -648752941: goto L_0x006e;
                        case -638126837: goto L_0x0063;
                        case -76025313: goto L_0x0058;
                        case -9754574: goto L_0x004d;
                        case 64397344: goto L_0x0042;
                        case 364489912: goto L_0x0035;
                        case 1301930599: goto L_0x0028;
                        case 1401391082: goto L_0x001b;
                        case 1535404999: goto L_0x000e;
                        default: goto L_0x000b;
                    }
                L_0x000b:
                    r2 = -1
                    goto L_0x009b
                L_0x000e:
                    java.lang.String r0 = "triggerReceiver"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0017
                    goto L_0x000b
                L_0x0017:
                    r2 = 11
                    goto L_0x009b
                L_0x001b:
                    java.lang.String r0 = "postLayout"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0024
                    goto L_0x000b
                L_0x0024:
                    r2 = 10
                    goto L_0x009b
                L_0x0028:
                    java.lang.String r0 = "viewTransitionOnCross"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0031
                    goto L_0x000b
                L_0x0031:
                    r2 = 9
                    goto L_0x009b
                L_0x0035:
                    java.lang.String r0 = "triggerSlack"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x003e
                    goto L_0x000b
                L_0x003e:
                    r2 = 8
                    goto L_0x009b
                L_0x0042:
                    java.lang.String r0 = "CROSS"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x004b
                    goto L_0x000b
                L_0x004b:
                    r2 = 7
                    goto L_0x009b
                L_0x004d:
                    java.lang.String r0 = "viewTransitionOnNegativeCross"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0056
                    goto L_0x000b
                L_0x0056:
                    r2 = 6
                    goto L_0x009b
                L_0x0058:
                    java.lang.String r0 = "triggerCollisionView"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0061
                    goto L_0x000b
                L_0x0061:
                    r2 = 5
                    goto L_0x009b
                L_0x0063:
                    java.lang.String r0 = "negativeCross"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x006c
                    goto L_0x000b
                L_0x006c:
                    r2 = 4
                    goto L_0x009b
                L_0x006e:
                    java.lang.String r0 = "triggerID"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0077
                    goto L_0x000b
                L_0x0077:
                    r2 = 3
                    goto L_0x009b
                L_0x0079:
                    java.lang.String r0 = "triggerCollisionId"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0082
                    goto L_0x000b
                L_0x0082:
                    r2 = 2
                    goto L_0x009b
                L_0x0084:
                    java.lang.String r0 = "viewTransitionOnPositiveCross"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x008e
                    goto L_0x000b
                L_0x008e:
                    r2 = 1
                    goto L_0x009b
                L_0x0090:
                    java.lang.String r0 = "positiveCross"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x009a
                    goto L_0x000b
                L_0x009a:
                    r2 = 0
                L_0x009b:
                    switch(r2) {
                        case 0: goto L_0x00c0;
                        case 1: goto L_0x00bd;
                        case 2: goto L_0x00ba;
                        case 3: goto L_0x00b7;
                        case 4: goto L_0x00b4;
                        case 5: goto L_0x00b1;
                        case 6: goto L_0x00ae;
                        case 7: goto L_0x00ab;
                        case 8: goto L_0x00a8;
                        case 9: goto L_0x00a5;
                        case 10: goto L_0x00a2;
                        case 11: goto L_0x009f;
                        default: goto L_0x009e;
                    }
                L_0x009e:
                    return r1
                L_0x009f:
                    r2 = 311(0x137, float:4.36E-43)
                    return r2
                L_0x00a2:
                    r2 = 304(0x130, float:4.26E-43)
                    return r2
                L_0x00a5:
                    r2 = 301(0x12d, float:4.22E-43)
                    return r2
                L_0x00a8:
                    r2 = 305(0x131, float:4.27E-43)
                    return r2
                L_0x00ab:
                    r2 = 312(0x138, float:4.37E-43)
                    return r2
                L_0x00ae:
                    r2 = 303(0x12f, float:4.25E-43)
                    return r2
                L_0x00b1:
                    r2 = 306(0x132, float:4.29E-43)
                    return r2
                L_0x00b4:
                    r2 = 310(0x136, float:4.34E-43)
                    return r2
                L_0x00b7:
                    r2 = 308(0x134, float:4.32E-43)
                    return r2
                L_0x00ba:
                    r2 = 307(0x133, float:4.3E-43)
                    return r2
                L_0x00bd:
                    r2 = 302(0x12e, float:4.23E-43)
                    return r2
                L_0x00c0:
                    r2 = 309(0x135, float:4.33E-43)
                    return r2
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.motion.utils.TypedValues.TriggerType.CC.getId(java.lang.String):int");
            }
        }
    }

    public interface PositionType {
        public static final String[] KEY_WORDS = {"transitionEasing", "drawPath", "percentWidth", "percentHeight", "sizePercent", "percentX", "percentY"};
        public static final String NAME = "KeyPosition";
        public static final String S_DRAWPATH = "drawPath";
        public static final String S_PERCENT_HEIGHT = "percentHeight";
        public static final String S_PERCENT_WIDTH = "percentWidth";
        public static final String S_PERCENT_X = "percentX";
        public static final String S_PERCENT_Y = "percentY";
        public static final String S_SIZE_PERCENT = "sizePercent";
        public static final String S_TRANSITION_EASING = "transitionEasing";
        public static final int TYPE_CURVE_FIT = 508;
        public static final int TYPE_DRAWPATH = 502;
        public static final int TYPE_PATH_MOTION_ARC = 509;
        public static final int TYPE_PERCENT_HEIGHT = 504;
        public static final int TYPE_PERCENT_WIDTH = 503;
        public static final int TYPE_PERCENT_X = 506;
        public static final int TYPE_PERCENT_Y = 507;
        public static final int TYPE_POSITION_TYPE = 510;
        public static final int TYPE_SIZE_PERCENT = 505;
        public static final int TYPE_TRANSITION_EASING = 501;

        /* renamed from: androidx.constraintlayout.core.motion.utils.TypedValues$PositionType$-CC  reason: invalid class name */
        public final /* synthetic */ class CC {
            static {
                String str = PositionType.NAME;
            }

            public static int getType(int i) {
                if (i == 100) {
                    return 2;
                }
                if (i == 101) {
                    return 8;
                }
                switch (i) {
                    case PositionType.TYPE_TRANSITION_EASING /*501*/:
                    case PositionType.TYPE_DRAWPATH /*502*/:
                        return 8;
                    case PositionType.TYPE_PERCENT_WIDTH /*503*/:
                    case PositionType.TYPE_PERCENT_HEIGHT /*504*/:
                    case PositionType.TYPE_SIZE_PERCENT /*505*/:
                    case PositionType.TYPE_PERCENT_X /*506*/:
                    case PositionType.TYPE_PERCENT_Y /*507*/:
                        return 4;
                    case PositionType.TYPE_CURVE_FIT /*508*/:
                        return 2;
                    default:
                        return -1;
                }
            }

            /* JADX WARNING: Can't fix incorrect switch cases order */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public static int getId(java.lang.String r2) {
                /*
                    r2.hashCode()
                    int r0 = r2.hashCode()
                    r1 = -1
                    switch(r0) {
                        case -1812823328: goto L_0x004f;
                        case -1127236479: goto L_0x0044;
                        case -1017587252: goto L_0x0039;
                        case -827014263: goto L_0x002e;
                        case -200259324: goto L_0x0023;
                        case 428090547: goto L_0x0018;
                        case 428090548: goto L_0x000d;
                        default: goto L_0x000b;
                    }
                L_0x000b:
                    r2 = -1
                    goto L_0x0059
                L_0x000d:
                    java.lang.String r0 = "percentY"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0016
                    goto L_0x000b
                L_0x0016:
                    r2 = 6
                    goto L_0x0059
                L_0x0018:
                    java.lang.String r0 = "percentX"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0021
                    goto L_0x000b
                L_0x0021:
                    r2 = 5
                    goto L_0x0059
                L_0x0023:
                    java.lang.String r0 = "sizePercent"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x002c
                    goto L_0x000b
                L_0x002c:
                    r2 = 4
                    goto L_0x0059
                L_0x002e:
                    java.lang.String r0 = "drawPath"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0037
                    goto L_0x000b
                L_0x0037:
                    r2 = 3
                    goto L_0x0059
                L_0x0039:
                    java.lang.String r0 = "percentHeight"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0042
                    goto L_0x000b
                L_0x0042:
                    r2 = 2
                    goto L_0x0059
                L_0x0044:
                    java.lang.String r0 = "percentWidth"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x004d
                    goto L_0x000b
                L_0x004d:
                    r2 = 1
                    goto L_0x0059
                L_0x004f:
                    java.lang.String r0 = "transitionEasing"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0058
                    goto L_0x000b
                L_0x0058:
                    r2 = 0
                L_0x0059:
                    switch(r2) {
                        case 0: goto L_0x006f;
                        case 1: goto L_0x006c;
                        case 2: goto L_0x0069;
                        case 3: goto L_0x0066;
                        case 4: goto L_0x0063;
                        case 5: goto L_0x0060;
                        case 6: goto L_0x005d;
                        default: goto L_0x005c;
                    }
                L_0x005c:
                    return r1
                L_0x005d:
                    r2 = 507(0x1fb, float:7.1E-43)
                    return r2
                L_0x0060:
                    r2 = 506(0x1fa, float:7.09E-43)
                    return r2
                L_0x0063:
                    r2 = 505(0x1f9, float:7.08E-43)
                    return r2
                L_0x0066:
                    r2 = 502(0x1f6, float:7.03E-43)
                    return r2
                L_0x0069:
                    r2 = 504(0x1f8, float:7.06E-43)
                    return r2
                L_0x006c:
                    r2 = 503(0x1f7, float:7.05E-43)
                    return r2
                L_0x006f:
                    r2 = 501(0x1f5, float:7.02E-43)
                    return r2
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.motion.utils.TypedValues.PositionType.CC.getId(java.lang.String):int");
            }
        }
    }

    public interface MotionType {
        public static final String[] KEY_WORDS = {S_STAGGER, S_PATH_ROTATE, S_QUANTIZE_MOTION_PHASE, S_EASING, S_QUANTIZE_INTERPOLATOR, S_ANIMATE_RELATIVE_TO, S_ANIMATE_CIRCLEANGLE_TO, S_PATHMOTION_ARC, S_DRAW_PATH, S_POLAR_RELATIVETO, S_QUANTIZE_MOTIONSTEPS, S_QUANTIZE_INTERPOLATOR_TYPE, S_QUANTIZE_INTERPOLATOR_ID};
        public static final String NAME = "Motion";
        public static final String S_ANIMATE_CIRCLEANGLE_TO = "AnimateCircleAngleTo";
        public static final String S_ANIMATE_RELATIVE_TO = "AnimateRelativeTo";
        public static final String S_DRAW_PATH = "DrawPath";
        public static final String S_EASING = "TransitionEasing";
        public static final String S_PATHMOTION_ARC = "PathMotionArc";
        public static final String S_PATH_ROTATE = "PathRotate";
        public static final String S_POLAR_RELATIVETO = "PolarRelativeTo";
        public static final String S_QUANTIZE_INTERPOLATOR = "QuantizeInterpolator";
        public static final String S_QUANTIZE_INTERPOLATOR_ID = "QuantizeInterpolatorID";
        public static final String S_QUANTIZE_INTERPOLATOR_TYPE = "QuantizeInterpolatorType";
        public static final String S_QUANTIZE_MOTIONSTEPS = "QuantizeMotionSteps";
        public static final String S_QUANTIZE_MOTION_PHASE = "QuantizeMotionPhase";
        public static final String S_STAGGER = "Stagger";
        public static final int TYPE_ANIMATE_CIRCLEANGLE_TO = 606;
        public static final int TYPE_ANIMATE_RELATIVE_TO = 605;
        public static final int TYPE_DRAW_PATH = 608;
        public static final int TYPE_EASING = 603;
        public static final int TYPE_PATHMOTION_ARC = 607;
        public static final int TYPE_PATH_ROTATE = 601;
        public static final int TYPE_POLAR_RELATIVETO = 609;
        public static final int TYPE_QUANTIZE_INTERPOLATOR = 604;
        public static final int TYPE_QUANTIZE_INTERPOLATOR_ID = 612;
        public static final int TYPE_QUANTIZE_INTERPOLATOR_TYPE = 611;
        public static final int TYPE_QUANTIZE_MOTIONSTEPS = 610;
        public static final int TYPE_QUANTIZE_MOTION_PHASE = 602;
        public static final int TYPE_STAGGER = 600;

        /* renamed from: androidx.constraintlayout.core.motion.utils.TypedValues$MotionType$-CC  reason: invalid class name */
        public final /* synthetic */ class CC {
            static {
                String str = MotionType.NAME;
            }

            /* JADX WARNING: Can't fix incorrect switch cases order */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public static int getId(java.lang.String r2) {
                /*
                    r2.hashCode()
                    int r0 = r2.hashCode()
                    r1 = -1
                    switch(r0) {
                        case -2033446275: goto L_0x009e;
                        case -1532277420: goto L_0x0092;
                        case -1529145600: goto L_0x0086;
                        case -1498310144: goto L_0x007b;
                        case -1030753096: goto L_0x0070;
                        case -762370135: goto L_0x0065;
                        case -232872051: goto L_0x005a;
                        case 1138491429: goto L_0x004f;
                        case 1539234834: goto L_0x0042;
                        case 1583722451: goto L_0x0035;
                        case 1639368448: goto L_0x0028;
                        case 1900899336: goto L_0x001b;
                        case 2109694967: goto L_0x000e;
                        default: goto L_0x000b;
                    }
                L_0x000b:
                    r2 = -1
                    goto L_0x00a9
                L_0x000e:
                    java.lang.String r0 = "PathMotionArc"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0017
                    goto L_0x000b
                L_0x0017:
                    r2 = 12
                    goto L_0x00a9
                L_0x001b:
                    java.lang.String r0 = "AnimateRelativeTo"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0024
                    goto L_0x000b
                L_0x0024:
                    r2 = 11
                    goto L_0x00a9
                L_0x0028:
                    java.lang.String r0 = "TransitionEasing"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0031
                    goto L_0x000b
                L_0x0031:
                    r2 = 10
                    goto L_0x00a9
                L_0x0035:
                    java.lang.String r0 = "QuantizeInterpolatorID"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x003e
                    goto L_0x000b
                L_0x003e:
                    r2 = 9
                    goto L_0x00a9
                L_0x0042:
                    java.lang.String r0 = "QuantizeInterpolatorType"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x004b
                    goto L_0x000b
                L_0x004b:
                    r2 = 8
                    goto L_0x00a9
                L_0x004f:
                    java.lang.String r0 = "PolarRelativeTo"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0058
                    goto L_0x000b
                L_0x0058:
                    r2 = 7
                    goto L_0x00a9
                L_0x005a:
                    java.lang.String r0 = "Stagger"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0063
                    goto L_0x000b
                L_0x0063:
                    r2 = 6
                    goto L_0x00a9
                L_0x0065:
                    java.lang.String r0 = "DrawPath"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x006e
                    goto L_0x000b
                L_0x006e:
                    r2 = 5
                    goto L_0x00a9
                L_0x0070:
                    java.lang.String r0 = "QuantizeInterpolator"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0079
                    goto L_0x000b
                L_0x0079:
                    r2 = 4
                    goto L_0x00a9
                L_0x007b:
                    java.lang.String r0 = "PathRotate"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0084
                    goto L_0x000b
                L_0x0084:
                    r2 = 3
                    goto L_0x00a9
                L_0x0086:
                    java.lang.String r0 = "QuantizeMotionSteps"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0090
                    goto L_0x000b
                L_0x0090:
                    r2 = 2
                    goto L_0x00a9
                L_0x0092:
                    java.lang.String r0 = "QuantizeMotionPhase"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x009c
                    goto L_0x000b
                L_0x009c:
                    r2 = 1
                    goto L_0x00a9
                L_0x009e:
                    java.lang.String r0 = "AnimateCircleAngleTo"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x00a8
                    goto L_0x000b
                L_0x00a8:
                    r2 = 0
                L_0x00a9:
                    switch(r2) {
                        case 0: goto L_0x00d1;
                        case 1: goto L_0x00ce;
                        case 2: goto L_0x00cb;
                        case 3: goto L_0x00c8;
                        case 4: goto L_0x00c5;
                        case 5: goto L_0x00c2;
                        case 6: goto L_0x00bf;
                        case 7: goto L_0x00bc;
                        case 8: goto L_0x00b9;
                        case 9: goto L_0x00b6;
                        case 10: goto L_0x00b3;
                        case 11: goto L_0x00b0;
                        case 12: goto L_0x00ad;
                        default: goto L_0x00ac;
                    }
                L_0x00ac:
                    return r1
                L_0x00ad:
                    r2 = 607(0x25f, float:8.5E-43)
                    return r2
                L_0x00b0:
                    r2 = 605(0x25d, float:8.48E-43)
                    return r2
                L_0x00b3:
                    r2 = 603(0x25b, float:8.45E-43)
                    return r2
                L_0x00b6:
                    r2 = 612(0x264, float:8.58E-43)
                    return r2
                L_0x00b9:
                    r2 = 611(0x263, float:8.56E-43)
                    return r2
                L_0x00bc:
                    r2 = 609(0x261, float:8.53E-43)
                    return r2
                L_0x00bf:
                    r2 = 600(0x258, float:8.41E-43)
                    return r2
                L_0x00c2:
                    r2 = 608(0x260, float:8.52E-43)
                    return r2
                L_0x00c5:
                    r2 = 604(0x25c, float:8.46E-43)
                    return r2
                L_0x00c8:
                    r2 = 601(0x259, float:8.42E-43)
                    return r2
                L_0x00cb:
                    r2 = 610(0x262, float:8.55E-43)
                    return r2
                L_0x00ce:
                    r2 = 602(0x25a, float:8.44E-43)
                    return r2
                L_0x00d1:
                    r2 = 606(0x25e, float:8.49E-43)
                    return r2
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.motion.utils.TypedValues.MotionType.CC.getId(java.lang.String):int");
            }
        }
    }

    public interface Custom {
        public static final String[] KEY_WORDS = {S_FLOAT, S_COLOR, S_STRING, S_BOOLEAN, S_DIMENSION, S_REFERENCE};
        public static final String NAME = "Custom";
        public static final String S_BOOLEAN = "boolean";
        public static final String S_COLOR = "color";
        public static final String S_DIMENSION = "dimension";
        public static final String S_FLOAT = "float";
        public static final String S_INT = "integer";
        public static final String S_REFERENCE = "refrence";
        public static final String S_STRING = "string";
        public static final int TYPE_BOOLEAN = 904;
        public static final int TYPE_COLOR = 902;
        public static final int TYPE_DIMENSION = 905;
        public static final int TYPE_FLOAT = 901;
        public static final int TYPE_INT = 900;
        public static final int TYPE_REFERENCE = 906;
        public static final int TYPE_STRING = 903;

        /* renamed from: androidx.constraintlayout.core.motion.utils.TypedValues$Custom$-CC  reason: invalid class name */
        public final /* synthetic */ class CC {
            static {
                String str = Custom.NAME;
            }

            /* JADX WARNING: Can't fix incorrect switch cases order */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public static int getId(java.lang.String r2) {
                /*
                    r2.hashCode()
                    int r0 = r2.hashCode()
                    r1 = -1
                    switch(r0) {
                        case -1095013018: goto L_0x004f;
                        case -891985903: goto L_0x0044;
                        case -710953590: goto L_0x0039;
                        case 64711720: goto L_0x002e;
                        case 94842723: goto L_0x0023;
                        case 97526364: goto L_0x0018;
                        case 1958052158: goto L_0x000d;
                        default: goto L_0x000b;
                    }
                L_0x000b:
                    r2 = -1
                    goto L_0x0059
                L_0x000d:
                    java.lang.String r0 = "integer"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0016
                    goto L_0x000b
                L_0x0016:
                    r2 = 6
                    goto L_0x0059
                L_0x0018:
                    java.lang.String r0 = "float"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0021
                    goto L_0x000b
                L_0x0021:
                    r2 = 5
                    goto L_0x0059
                L_0x0023:
                    java.lang.String r0 = "color"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x002c
                    goto L_0x000b
                L_0x002c:
                    r2 = 4
                    goto L_0x0059
                L_0x002e:
                    java.lang.String r0 = "boolean"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0037
                    goto L_0x000b
                L_0x0037:
                    r2 = 3
                    goto L_0x0059
                L_0x0039:
                    java.lang.String r0 = "refrence"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0042
                    goto L_0x000b
                L_0x0042:
                    r2 = 2
                    goto L_0x0059
                L_0x0044:
                    java.lang.String r0 = "string"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x004d
                    goto L_0x000b
                L_0x004d:
                    r2 = 1
                    goto L_0x0059
                L_0x004f:
                    java.lang.String r0 = "dimension"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0058
                    goto L_0x000b
                L_0x0058:
                    r2 = 0
                L_0x0059:
                    switch(r2) {
                        case 0: goto L_0x006f;
                        case 1: goto L_0x006c;
                        case 2: goto L_0x0069;
                        case 3: goto L_0x0066;
                        case 4: goto L_0x0063;
                        case 5: goto L_0x0060;
                        case 6: goto L_0x005d;
                        default: goto L_0x005c;
                    }
                L_0x005c:
                    return r1
                L_0x005d:
                    r2 = 900(0x384, float:1.261E-42)
                    return r2
                L_0x0060:
                    r2 = 901(0x385, float:1.263E-42)
                    return r2
                L_0x0063:
                    r2 = 902(0x386, float:1.264E-42)
                    return r2
                L_0x0066:
                    r2 = 904(0x388, float:1.267E-42)
                    return r2
                L_0x0069:
                    r2 = 906(0x38a, float:1.27E-42)
                    return r2
                L_0x006c:
                    r2 = 903(0x387, float:1.265E-42)
                    return r2
                L_0x006f:
                    r2 = 905(0x389, float:1.268E-42)
                    return r2
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.motion.utils.TypedValues.Custom.CC.getId(java.lang.String):int");
            }
        }
    }

    public interface MotionScene {
        public static final String[] KEY_WORDS = {S_DEFAULT_DURATION, S_LAYOUT_DURING_TRANSITION};
        public static final String NAME = "MotionScene";
        public static final String S_DEFAULT_DURATION = "defaultDuration";
        public static final String S_LAYOUT_DURING_TRANSITION = "layoutDuringTransition";
        public static final int TYPE_DEFAULT_DURATION = 600;
        public static final int TYPE_LAYOUT_DURING_TRANSITION = 601;

        /* renamed from: androidx.constraintlayout.core.motion.utils.TypedValues$MotionScene$-CC  reason: invalid class name */
        public final /* synthetic */ class CC {
            static {
                String str = MotionScene.NAME;
            }

            public static int getType(int i) {
                if (i != 600) {
                    return i != 601 ? -1 : 1;
                }
                return 2;
            }

            public static int getId(String str) {
                str.hashCode();
                if (!str.equals(MotionScene.S_DEFAULT_DURATION)) {
                    return !str.equals(MotionScene.S_LAYOUT_DURING_TRANSITION) ? -1 : 601;
                }
                return 600;
            }
        }
    }

    public interface TransitionType {
        public static final String[] KEY_WORDS = {S_DURATION, S_FROM, S_TO, S_PATH_MOTION_ARC, S_AUTO_TRANSITION, S_INTERPOLATOR, S_STAGGERED, S_FROM, S_TRANSITION_FLAGS};
        public static final String NAME = "Transitions";
        public static final String S_AUTO_TRANSITION = "autoTransition";
        public static final String S_DURATION = "duration";
        public static final String S_FROM = "from";
        public static final String S_INTERPOLATOR = "motionInterpolator";
        public static final String S_PATH_MOTION_ARC = "pathMotionArc";
        public static final String S_STAGGERED = "staggered";
        public static final String S_TO = "to";
        public static final String S_TRANSITION_FLAGS = "transitionFlags";
        public static final int TYPE_AUTO_TRANSITION = 704;
        public static final int TYPE_DURATION = 700;
        public static final int TYPE_FROM = 701;
        public static final int TYPE_INTERPOLATOR = 705;
        public static final int TYPE_PATH_MOTION_ARC = 509;
        public static final int TYPE_STAGGERED = 706;
        public static final int TYPE_TO = 702;
        public static final int TYPE_TRANSITION_FLAGS = 707;

        /* renamed from: androidx.constraintlayout.core.motion.utils.TypedValues$TransitionType$-CC  reason: invalid class name */
        public final /* synthetic */ class CC {
            static {
                String str = TransitionType.NAME;
            }

            public static int getType(int i) {
                if (i == 509) {
                    return 2;
                }
                switch (i) {
                    case TransitionType.TYPE_DURATION /*700*/:
                        return 2;
                    case TransitionType.TYPE_FROM /*701*/:
                    case TransitionType.TYPE_TO /*702*/:
                        return 8;
                    default:
                        switch (i) {
                            case TransitionType.TYPE_INTERPOLATOR /*705*/:
                            case TransitionType.TYPE_TRANSITION_FLAGS /*707*/:
                                return 8;
                            case TransitionType.TYPE_STAGGERED /*706*/:
                                return 4;
                            default:
                                return -1;
                        }
                }
            }

            /* JADX WARNING: Can't fix incorrect switch cases order */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public static int getId(java.lang.String r2) {
                /*
                    r2.hashCode()
                    int r0 = r2.hashCode()
                    r1 = -1
                    switch(r0) {
                        case -1996906958: goto L_0x005b;
                        case -1992012396: goto L_0x0050;
                        case -1357874275: goto L_0x0045;
                        case -1298065308: goto L_0x003a;
                        case 3707: goto L_0x002f;
                        case 3151786: goto L_0x0024;
                        case 1310733335: goto L_0x0019;
                        case 1839260940: goto L_0x000e;
                        default: goto L_0x000b;
                    }
                L_0x000b:
                    r2 = -1
                    goto L_0x0065
                L_0x000e:
                    java.lang.String r0 = "staggered"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0017
                    goto L_0x000b
                L_0x0017:
                    r2 = 7
                    goto L_0x0065
                L_0x0019:
                    java.lang.String r0 = "pathMotionArc"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0022
                    goto L_0x000b
                L_0x0022:
                    r2 = 6
                    goto L_0x0065
                L_0x0024:
                    java.lang.String r0 = "from"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x002d
                    goto L_0x000b
                L_0x002d:
                    r2 = 5
                    goto L_0x0065
                L_0x002f:
                    java.lang.String r0 = "to"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0038
                    goto L_0x000b
                L_0x0038:
                    r2 = 4
                    goto L_0x0065
                L_0x003a:
                    java.lang.String r0 = "autoTransition"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0043
                    goto L_0x000b
                L_0x0043:
                    r2 = 3
                    goto L_0x0065
                L_0x0045:
                    java.lang.String r0 = "motionInterpolator"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x004e
                    goto L_0x000b
                L_0x004e:
                    r2 = 2
                    goto L_0x0065
                L_0x0050:
                    java.lang.String r0 = "duration"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0059
                    goto L_0x000b
                L_0x0059:
                    r2 = 1
                    goto L_0x0065
                L_0x005b:
                    java.lang.String r0 = "transitionFlags"
                    boolean r2 = r2.equals(r0)
                    if (r2 != 0) goto L_0x0064
                    goto L_0x000b
                L_0x0064:
                    r2 = 0
                L_0x0065:
                    switch(r2) {
                        case 0: goto L_0x007e;
                        case 1: goto L_0x007b;
                        case 2: goto L_0x0078;
                        case 3: goto L_0x0075;
                        case 4: goto L_0x0072;
                        case 5: goto L_0x006f;
                        case 6: goto L_0x006c;
                        case 7: goto L_0x0069;
                        default: goto L_0x0068;
                    }
                L_0x0068:
                    return r1
                L_0x0069:
                    r2 = 706(0x2c2, float:9.9E-43)
                    return r2
                L_0x006c:
                    r2 = 509(0x1fd, float:7.13E-43)
                    return r2
                L_0x006f:
                    r2 = 701(0x2bd, float:9.82E-43)
                    return r2
                L_0x0072:
                    r2 = 702(0x2be, float:9.84E-43)
                    return r2
                L_0x0075:
                    r2 = 704(0x2c0, float:9.87E-43)
                    return r2
                L_0x0078:
                    r2 = 705(0x2c1, float:9.88E-43)
                    return r2
                L_0x007b:
                    r2 = 700(0x2bc, float:9.81E-43)
                    return r2
                L_0x007e:
                    r2 = 707(0x2c3, float:9.91E-43)
                    return r2
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.motion.utils.TypedValues.TransitionType.CC.getId(java.lang.String):int");
            }
        }
    }
}
