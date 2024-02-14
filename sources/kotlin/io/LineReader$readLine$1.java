package kotlin.io;

import java.nio.charset.CharsetDecoder;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0Impl;

@Metadata(k = 3, mv = {1, 5, 1})
/* compiled from: Console.kt */
final /* synthetic */ class LineReader$readLine$1 extends MutablePropertyReference0Impl {
    LineReader$readLine$1(LineReader lineReader) {
        super(lineReader, LineReader.class, "decoder", "getDecoder()Ljava/nio/charset/CharsetDecoder;", 0);
    }

    public Object get() {
        return LineReader.access$getDecoder$p((LineReader) this.receiver);
    }

    public void set(Object obj) {
        LineReader.decoder = (CharsetDecoder) obj;
    }
}
