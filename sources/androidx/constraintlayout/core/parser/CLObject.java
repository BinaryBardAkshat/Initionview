package androidx.constraintlayout.core.parser;

import java.util.Iterator;

public class CLObject extends CLContainer implements Iterable<CLKey> {
    public CLObject(char[] cArr) {
        super(cArr);
    }

    public static CLObject allocate(char[] cArr) {
        return new CLObject(cArr);
    }

    public String toJSON() {
        StringBuilder sb = new StringBuilder(getDebugName() + "{ ");
        Iterator it = this.mElements.iterator();
        boolean z = true;
        while (it.hasNext()) {
            CLElement cLElement = (CLElement) it.next();
            if (!z) {
                sb.append(", ");
            } else {
                z = false;
            }
            sb.append(cLElement.toJSON());
        }
        sb.append(" }");
        return sb.toString();
    }

    public String toFormattedJSON() {
        return toFormattedJSON(0, 0);
    }

    public String toFormattedJSON(int i, int i2) {
        StringBuilder sb = new StringBuilder(getDebugName());
        sb.append("{\n");
        Iterator it = this.mElements.iterator();
        boolean z = true;
        while (it.hasNext()) {
            CLElement cLElement = (CLElement) it.next();
            if (!z) {
                sb.append(",\n");
            } else {
                z = false;
            }
            sb.append(cLElement.toFormattedJSON(BASE_INDENT + i, i2 - 1));
        }
        sb.append("\n");
        addIndent(sb, i);
        sb.append("}");
        return sb.toString();
    }

    public Iterator iterator() {
        return new CLObjectIterator(this);
    }

    private class CLObjectIterator implements Iterator {
        int index = 0;
        CLObject myObject;

        public CLObjectIterator(CLObject cLObject) {
            this.myObject = cLObject;
        }

        public boolean hasNext() {
            return this.index < this.myObject.size();
        }

        public Object next() {
            CLKey cLKey = (CLKey) this.myObject.mElements.get(this.index);
            this.index++;
            return cLKey;
        }
    }
}
