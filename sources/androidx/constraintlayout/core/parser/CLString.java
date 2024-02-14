package androidx.constraintlayout.core.parser;

public class CLString extends CLElement {
    public CLString(char[] cArr) {
        super(cArr);
    }

    public static CLElement allocate(char[] cArr) {
        return new CLString(cArr);
    }

    /* access modifiers changed from: protected */
    public String toJSON() {
        return "'" + content() + "'";
    }

    /* access modifiers changed from: protected */
    public String toFormattedJSON(int i, int i2) {
        StringBuilder sb = new StringBuilder();
        addIndent(sb, i);
        sb.append("'");
        sb.append(content());
        sb.append("'");
        return sb.toString();
    }
}
