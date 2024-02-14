package androidx.constraintlayout.core.parser;

import java.util.Iterator;

public class CLArray extends CLContainer {
    public CLArray(char[] cArr) {
        super(cArr);
    }

    public static CLElement allocate(char[] cArr) {
        return new CLArray(cArr);
    }

    /* access modifiers changed from: protected */
    public String toJSON() {
        StringBuilder sb = new StringBuilder(getDebugName() + "[");
        boolean z = true;
        for (int i = 0; i < this.mElements.size(); i++) {
            if (!z) {
                sb.append(", ");
            } else {
                z = false;
            }
            sb.append(((CLElement) this.mElements.get(i)).toJSON());
        }
        return sb + "]";
    }

    /* access modifiers changed from: protected */
    public String toFormattedJSON(int i, int i2) {
        StringBuilder sb = new StringBuilder();
        String json = toJSON();
        if (i2 > 0 || json.length() + i >= MAX_LINE) {
            sb.append("[\n");
            Iterator it = this.mElements.iterator();
            boolean z = true;
            while (it.hasNext()) {
                CLElement cLElement = (CLElement) it.next();
                if (!z) {
                    sb.append(",\n");
                } else {
                    z = false;
                }
                addIndent(sb, BASE_INDENT + i);
                sb.append(cLElement.toFormattedJSON(BASE_INDENT + i, i2 - 1));
            }
            sb.append("\n");
            addIndent(sb, i);
            sb.append("]");
        } else {
            sb.append(json);
        }
        return sb.toString();
    }
}
