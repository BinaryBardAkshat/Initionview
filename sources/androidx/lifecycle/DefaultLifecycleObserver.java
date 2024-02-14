package androidx.lifecycle;

public interface DefaultLifecycleObserver extends FullLifecycleObserver {

    /* renamed from: androidx.lifecycle.DefaultLifecycleObserver$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$onCreate(DefaultLifecycleObserver defaultLifecycleObserver, LifecycleOwner lifecycleOwner) {
        }

        public static void $default$onDestroy(DefaultLifecycleObserver defaultLifecycleObserver, LifecycleOwner lifecycleOwner) {
        }

        public static void $default$onPause(DefaultLifecycleObserver defaultLifecycleObserver, LifecycleOwner lifecycleOwner) {
        }

        public static void $default$onResume(DefaultLifecycleObserver defaultLifecycleObserver, LifecycleOwner lifecycleOwner) {
        }

        public static void $default$onStart(DefaultLifecycleObserver defaultLifecycleObserver, LifecycleOwner lifecycleOwner) {
        }

        public static void $default$onStop(DefaultLifecycleObserver defaultLifecycleObserver, LifecycleOwner lifecycleOwner) {
        }
    }

    void onCreate(LifecycleOwner lifecycleOwner);

    void onDestroy(LifecycleOwner lifecycleOwner);

    void onPause(LifecycleOwner lifecycleOwner);

    void onResume(LifecycleOwner lifecycleOwner);

    void onStart(LifecycleOwner lifecycleOwner);

    void onStop(LifecycleOwner lifecycleOwner);
}
