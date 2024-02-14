package soup.neumorphism;

import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.Retention;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0002\b\u0002\b\u0002\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002B\u0000¨\u0006\u0003"}, d2 = {"Lsoup/neumorphism/LightSource;", "", "Companion", "neumorphism_release"}, k = 1, mv = {1, 1, 16})
@Retention(AnnotationRetention.SOURCE)
@java.lang.annotation.Retention(RetentionPolicy.SOURCE)
/* compiled from: LightSource.kt */
public @interface LightSource {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final int DEFAULT = 0;
    public static final int LEFT_BOTTOM = 1;
    public static final int LEFT_TOP = 0;
    public static final int RIGHT_BOTTOM = 3;
    public static final int RIGHT_TOP = 2;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004J\u000e\u0010\f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004J\u000e\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004J\u000e\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lsoup/neumorphism/LightSource$Companion;", "", "()V", "DEFAULT", "", "LEFT_BOTTOM", "LEFT_TOP", "RIGHT_BOTTOM", "RIGHT_TOP", "isBottom", "", "lightSource", "isLeft", "isRight", "isTop", "neumorphism_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: LightSource.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final int DEFAULT = 0;
        public static final int LEFT_BOTTOM = 1;
        public static final int LEFT_TOP = 0;
        public static final int RIGHT_BOTTOM = 3;
        public static final int RIGHT_TOP = 2;

        public final boolean isBottom(int i) {
            return i == 1 || i == 3;
        }

        public final boolean isLeft(int i) {
            return i == 0 || i == 1;
        }

        public final boolean isRight(int i) {
            return i == 2 || i == 3;
        }

        public final boolean isTop(int i) {
            return i == 0 || i == 2;
        }

        private Companion() {
        }
    }
}
