package androidx.constraintlayout.core.parser;

public class CLParser {
    static boolean DEBUG = false;
    private boolean hasComment = false;
    private int lineNumber;
    private String mContent;

    enum TYPE {
        UNKNOWN,
        OBJECT,
        ARRAY,
        NUMBER,
        STRING,
        KEY,
        TOKEN
    }

    public static CLObject parse(String str) throws CLParsingException {
        return new CLParser(str).parse();
    }

    public CLParser(String str) {
        this.mContent = str;
    }

    public CLObject parse() throws CLParsingException {
        char c;
        char[] charArray = this.mContent.toCharArray();
        int length = charArray.length;
        int i = 1;
        this.lineNumber = 1;
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                i2 = -1;
                break;
            }
            char c2 = charArray[i2];
            if (c2 == '{') {
                break;
            }
            if (c2 == 10) {
                this.lineNumber++;
            }
            i2++;
        }
        if (i2 != -1) {
            CLObject allocate = CLObject.allocate(charArray);
            allocate.setLine(this.lineNumber);
            allocate.setStart((long) i2);
            int i3 = i2 + 1;
            CLElement cLElement = allocate;
            while (i3 < length) {
                char c3 = charArray[i3];
                if (c3 == 10) {
                    this.lineNumber += i;
                }
                if (this.hasComment) {
                    if (c3 == 10) {
                        this.hasComment = z;
                    } else {
                        continue;
                        i3++;
                        i = 1;
                        z = false;
                    }
                }
                if (cLElement == null) {
                    break;
                }
                if (cLElement.isDone()) {
                    cLElement = getNextJsonElement(i3, c3, cLElement, charArray);
                } else if (cLElement instanceof CLObject) {
                    if (c3 == '}') {
                        cLElement.setEnd((long) (i3 - 1));
                    } else {
                        cLElement = getNextJsonElement(i3, c3, cLElement, charArray);
                    }
                } else if (!(cLElement instanceof CLArray)) {
                    boolean z2 = cLElement instanceof CLString;
                    if (!z2) {
                        if (cLElement instanceof CLToken) {
                            CLToken cLToken = (CLToken) cLElement;
                            if (!cLToken.validate(c3, (long) i3)) {
                                throw new CLParsingException("parsing incorrect token " + cLToken.content() + " at line " + this.lineNumber, cLToken);
                            }
                        }
                        if (((cLElement instanceof CLKey) || z2) && (((c = charArray[(int) cLElement.start]) == '\'' || c == '\"') && c == c3)) {
                            cLElement.setStart(cLElement.start + 1);
                            cLElement.setEnd((long) (i3 - 1));
                        }
                        if (!cLElement.isDone() && (c3 == '}' || c3 == ']' || c3 == ',' || c3 == ' ' || c3 == 9 || c3 == 13 || c3 == 10 || c3 == ':')) {
                            long j = (long) (i3 - 1);
                            cLElement.setEnd(j);
                            if (c3 == '}' || c3 == ']') {
                                cLElement = cLElement.getContainer();
                                cLElement.setEnd(j);
                                if (cLElement instanceof CLKey) {
                                    cLElement = cLElement.getContainer();
                                    cLElement.setEnd(j);
                                }
                            }
                        }
                    } else if (charArray[(int) cLElement.start] == c3) {
                        cLElement.setStart(cLElement.start + 1);
                        cLElement.setEnd((long) (i3 - 1));
                    }
                } else if (c3 == ']') {
                    cLElement.setEnd((long) (i3 - 1));
                } else {
                    cLElement = getNextJsonElement(i3, c3, cLElement, charArray);
                }
                if (cLElement.isDone() && (!(cLElement instanceof CLKey) || ((CLKey) cLElement).mElements.size() > 0)) {
                    cLElement = cLElement.getContainer();
                }
                i3++;
                i = 1;
                z = false;
            }
            while (cLElement != null && !cLElement.isDone()) {
                if (cLElement instanceof CLString) {
                    cLElement.setStart((long) (((int) cLElement.start) + 1));
                }
                cLElement.setEnd((long) (length - 1));
                cLElement = cLElement.getContainer();
            }
            if (DEBUG) {
                System.out.println("Root: " + allocate.toJSON());
            }
            return allocate;
        }
        throw new CLParsingException("invalid json content", (CLElement) null);
    }

    private CLElement getNextJsonElement(int i, char c, CLElement cLElement, char[] cArr) throws CLParsingException {
        if (c == 9 || c == 10 || c == 13 || c == ' ') {
            return cLElement;
        }
        if (c == '\"' || c == '\'') {
            if (cLElement instanceof CLObject) {
                return createElement(cLElement, i, TYPE.KEY, true, cArr);
            }
            return createElement(cLElement, i, TYPE.STRING, true, cArr);
        } else if (c != '[') {
            if (c != ']') {
                if (c == '{') {
                    return createElement(cLElement, i, TYPE.OBJECT, true, cArr);
                } else if (c != '}') {
                    switch (c) {
                        case '+':
                        case '-':
                        case '.':
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                            return createElement(cLElement, i, TYPE.NUMBER, true, cArr);
                        case ',':
                        case ':':
                            return cLElement;
                        case '/':
                            int i2 = i + 1;
                            if (i2 >= cArr.length || cArr[i2] != '/') {
                                return cLElement;
                            }
                            this.hasComment = true;
                            return cLElement;
                        default:
                            if (!(cLElement instanceof CLContainer) || (cLElement instanceof CLObject)) {
                                return createElement(cLElement, i, TYPE.KEY, true, cArr);
                            }
                            CLElement createElement = createElement(cLElement, i, TYPE.TOKEN, true, cArr);
                            CLToken cLToken = (CLToken) createElement;
                            if (cLToken.validate(c, (long) i)) {
                                return createElement;
                            }
                            throw new CLParsingException("incorrect token <" + c + "> at line " + this.lineNumber, cLToken);
                    }
                }
            }
            cLElement.setEnd((long) (i - 1));
            CLElement container = cLElement.getContainer();
            container.setEnd((long) i);
            return container;
        } else {
            return createElement(cLElement, i, TYPE.ARRAY, true, cArr);
        }
    }

    private CLElement createElement(CLElement cLElement, int i, TYPE type, boolean z, char[] cArr) {
        CLElement cLElement2;
        if (DEBUG) {
            System.out.println("CREATE " + type + " at " + cArr[i]);
        }
        switch (AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$parser$CLParser$TYPE[type.ordinal()]) {
            case 1:
                cLElement2 = CLObject.allocate(cArr);
                break;
            case 2:
                cLElement2 = CLArray.allocate(cArr);
                break;
            case 3:
                cLElement2 = CLString.allocate(cArr);
                break;
            case 4:
                cLElement2 = CLNumber.allocate(cArr);
                break;
            case 5:
                cLElement2 = CLKey.allocate(cArr);
                break;
            case 6:
                cLElement2 = CLToken.allocate(cArr);
                break;
            default:
                cLElement2 = null;
                break;
        }
        i++;
        if (cLElement2 == null) {
            return null;
        }
        cLElement2.setLine(this.lineNumber);
        if (z) {
            cLElement2.setStart((long) i);
        }
        if (cLElement instanceof CLContainer) {
            cLElement2.setContainer((CLContainer) cLElement);
        }
        return cLElement2;
    }

    /* renamed from: androidx.constraintlayout.core.parser.CLParser$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$constraintlayout$core$parser$CLParser$TYPE;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                androidx.constraintlayout.core.parser.CLParser$TYPE[] r0 = androidx.constraintlayout.core.parser.CLParser.TYPE.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$androidx$constraintlayout$core$parser$CLParser$TYPE = r0
                androidx.constraintlayout.core.parser.CLParser$TYPE r1 = androidx.constraintlayout.core.parser.CLParser.TYPE.OBJECT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$androidx$constraintlayout$core$parser$CLParser$TYPE     // Catch:{ NoSuchFieldError -> 0x001d }
                androidx.constraintlayout.core.parser.CLParser$TYPE r1 = androidx.constraintlayout.core.parser.CLParser.TYPE.ARRAY     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$androidx$constraintlayout$core$parser$CLParser$TYPE     // Catch:{ NoSuchFieldError -> 0x0028 }
                androidx.constraintlayout.core.parser.CLParser$TYPE r1 = androidx.constraintlayout.core.parser.CLParser.TYPE.STRING     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$androidx$constraintlayout$core$parser$CLParser$TYPE     // Catch:{ NoSuchFieldError -> 0x0033 }
                androidx.constraintlayout.core.parser.CLParser$TYPE r1 = androidx.constraintlayout.core.parser.CLParser.TYPE.NUMBER     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$androidx$constraintlayout$core$parser$CLParser$TYPE     // Catch:{ NoSuchFieldError -> 0x003e }
                androidx.constraintlayout.core.parser.CLParser$TYPE r1 = androidx.constraintlayout.core.parser.CLParser.TYPE.KEY     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$androidx$constraintlayout$core$parser$CLParser$TYPE     // Catch:{ NoSuchFieldError -> 0x0049 }
                androidx.constraintlayout.core.parser.CLParser$TYPE r1 = androidx.constraintlayout.core.parser.CLParser.TYPE.TOKEN     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.parser.CLParser.AnonymousClass1.<clinit>():void");
        }
    }
}
