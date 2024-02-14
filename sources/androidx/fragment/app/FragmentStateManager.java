package androidx.fragment.app;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.core.os.EnvironmentCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.R;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.SpecialEffectsController;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelStoreOwner;

class FragmentStateManager {
    private static final String TAG = "FragmentManager";
    private static final String TARGET_REQUEST_CODE_STATE_TAG = "android:target_req_state";
    private static final String TARGET_STATE_TAG = "android:target_state";
    private static final String USER_VISIBLE_HINT_TAG = "android:user_visible_hint";
    private static final String VIEW_REGISTRY_STATE_TAG = "android:view_registry_state";
    private static final String VIEW_STATE_TAG = "android:view_state";
    private final FragmentLifecycleCallbacksDispatcher mDispatcher;
    private final Fragment mFragment;
    private int mFragmentManagerState = -1;
    private final FragmentStore mFragmentStore;
    private boolean mMovingToState = false;

    FragmentStateManager(FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher, FragmentStore fragmentStore, Fragment fragment) {
        this.mDispatcher = fragmentLifecycleCallbacksDispatcher;
        this.mFragmentStore = fragmentStore;
        this.mFragment = fragment;
    }

    FragmentStateManager(FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher, FragmentStore fragmentStore, ClassLoader classLoader, FragmentFactory fragmentFactory, FragmentState fragmentState) {
        this.mDispatcher = fragmentLifecycleCallbacksDispatcher;
        this.mFragmentStore = fragmentStore;
        Fragment instantiate = fragmentFactory.instantiate(classLoader, fragmentState.mClassName);
        this.mFragment = instantiate;
        if (fragmentState.mArguments != null) {
            fragmentState.mArguments.setClassLoader(classLoader);
        }
        instantiate.setArguments(fragmentState.mArguments);
        instantiate.mWho = fragmentState.mWho;
        instantiate.mFromLayout = fragmentState.mFromLayout;
        instantiate.mRestored = true;
        instantiate.mFragmentId = fragmentState.mFragmentId;
        instantiate.mContainerId = fragmentState.mContainerId;
        instantiate.mTag = fragmentState.mTag;
        instantiate.mRetainInstance = fragmentState.mRetainInstance;
        instantiate.mRemoving = fragmentState.mRemoving;
        instantiate.mDetached = fragmentState.mDetached;
        instantiate.mHidden = fragmentState.mHidden;
        instantiate.mMaxState = Lifecycle.State.values()[fragmentState.mMaxLifecycleState];
        if (fragmentState.mSavedFragmentState != null) {
            instantiate.mSavedFragmentState = fragmentState.mSavedFragmentState;
        } else {
            instantiate.mSavedFragmentState = new Bundle();
        }
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v(TAG, "Instantiated fragment " + instantiate);
        }
    }

    FragmentStateManager(FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher, FragmentStore fragmentStore, Fragment fragment, FragmentState fragmentState) {
        this.mDispatcher = fragmentLifecycleCallbacksDispatcher;
        this.mFragmentStore = fragmentStore;
        this.mFragment = fragment;
        fragment.mSavedViewState = null;
        fragment.mSavedViewRegistryState = null;
        fragment.mBackStackNesting = 0;
        fragment.mInLayout = false;
        fragment.mAdded = false;
        fragment.mTargetWho = fragment.mTarget != null ? fragment.mTarget.mWho : null;
        fragment.mTarget = null;
        if (fragmentState.mSavedFragmentState != null) {
            fragment.mSavedFragmentState = fragmentState.mSavedFragmentState;
        } else {
            fragment.mSavedFragmentState = new Bundle();
        }
    }

    /* access modifiers changed from: package-private */
    public Fragment getFragment() {
        return this.mFragment;
    }

    /* access modifiers changed from: package-private */
    public void setFragmentManagerState(int i) {
        this.mFragmentManagerState = i;
    }

    /* access modifiers changed from: package-private */
    public int computeExpectedState() {
        if (this.mFragment.mFragmentManager == null) {
            return this.mFragment.mState;
        }
        int i = this.mFragmentManagerState;
        int i2 = AnonymousClass2.$SwitchMap$androidx$lifecycle$Lifecycle$State[this.mFragment.mMaxState.ordinal()];
        if (i2 != 1) {
            if (i2 == 2) {
                i = Math.min(i, 5);
            } else if (i2 == 3) {
                i = Math.min(i, 1);
            } else if (i2 != 4) {
                i = Math.min(i, -1);
            } else {
                i = Math.min(i, 0);
            }
        }
        if (this.mFragment.mFromLayout) {
            if (this.mFragment.mInLayout) {
                i = Math.max(this.mFragmentManagerState, 2);
                if (this.mFragment.mView != null && this.mFragment.mView.getParent() == null) {
                    i = Math.min(i, 2);
                }
            } else {
                i = this.mFragmentManagerState < 4 ? Math.min(i, this.mFragment.mState) : Math.min(i, 1);
            }
        }
        if (!this.mFragment.mAdded) {
            i = Math.min(i, 1);
        }
        SpecialEffectsController.Operation.LifecycleImpact lifecycleImpact = null;
        if (FragmentManager.USE_STATE_MANAGER && this.mFragment.mContainer != null) {
            lifecycleImpact = SpecialEffectsController.getOrCreateController(this.mFragment.mContainer, this.mFragment.getParentFragmentManager()).getAwaitingCompletionLifecycleImpact(this);
        }
        if (lifecycleImpact == SpecialEffectsController.Operation.LifecycleImpact.ADDING) {
            i = Math.min(i, 6);
        } else if (lifecycleImpact == SpecialEffectsController.Operation.LifecycleImpact.REMOVING) {
            i = Math.max(i, 3);
        } else if (this.mFragment.mRemoving) {
            if (this.mFragment.isInBackStack()) {
                i = Math.min(i, 1);
            } else {
                i = Math.min(i, -1);
            }
        }
        if (this.mFragment.mDeferStart && this.mFragment.mState < 5) {
            i = Math.min(i, 4);
        }
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v(TAG, "computeExpectedState() of " + i + " for " + this.mFragment);
        }
        return i;
    }

    /* renamed from: androidx.fragment.app.FragmentStateManager$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$androidx$lifecycle$Lifecycle$State;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                androidx.lifecycle.Lifecycle$State[] r0 = androidx.lifecycle.Lifecycle.State.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$androidx$lifecycle$Lifecycle$State = r0
                androidx.lifecycle.Lifecycle$State r1 = androidx.lifecycle.Lifecycle.State.RESUMED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$androidx$lifecycle$Lifecycle$State     // Catch:{ NoSuchFieldError -> 0x001d }
                androidx.lifecycle.Lifecycle$State r1 = androidx.lifecycle.Lifecycle.State.STARTED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$androidx$lifecycle$Lifecycle$State     // Catch:{ NoSuchFieldError -> 0x0028 }
                androidx.lifecycle.Lifecycle$State r1 = androidx.lifecycle.Lifecycle.State.CREATED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$androidx$lifecycle$Lifecycle$State     // Catch:{ NoSuchFieldError -> 0x0033 }
                androidx.lifecycle.Lifecycle$State r1 = androidx.lifecycle.Lifecycle.State.INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.FragmentStateManager.AnonymousClass2.<clinit>():void");
        }
    }

    /* access modifiers changed from: package-private */
    public void moveToExpectedState() {
        if (!this.mMovingToState) {
            boolean z = false;
            z = true;
            try {
                while (true) {
                    int computeExpectedState = computeExpectedState();
                    if (computeExpectedState != this.mFragment.mState) {
                        if (computeExpectedState <= this.mFragment.mState) {
                            switch (this.mFragment.mState - (z ? 1 : 0)) {
                                case -1:
                                    detach();
                                    break;
                                case 0:
                                    destroy();
                                    break;
                                case 1:
                                    destroyFragmentView();
                                    this.mFragment.mState = z;
                                    break;
                                case 2:
                                    this.mFragment.mInLayout = z;
                                    this.mFragment.mState = 2;
                                    break;
                                case 3:
                                    if (FragmentManager.isLoggingEnabled(3)) {
                                        Log.d(TAG, "movefrom ACTIVITY_CREATED: " + this.mFragment);
                                    }
                                    if (this.mFragment.mView != null && this.mFragment.mSavedViewState == null) {
                                        saveViewState();
                                    }
                                    if (!(this.mFragment.mView == null || this.mFragment.mContainer == null)) {
                                        SpecialEffectsController.getOrCreateController(this.mFragment.mContainer, this.mFragment.getParentFragmentManager()).enqueueRemove(this);
                                    }
                                    this.mFragment.mState = 3;
                                    break;
                                case 4:
                                    stop();
                                    break;
                                case 5:
                                    this.mFragment.mState = 5;
                                    break;
                                case 6:
                                    pause();
                                    break;
                            }
                        } else {
                            switch (this.mFragment.mState + z) {
                                case 0:
                                    attach();
                                    break;
                                case 1:
                                    create();
                                    break;
                                case 2:
                                    ensureInflatedView();
                                    createView();
                                    break;
                                case 3:
                                    activityCreated();
                                    break;
                                case 4:
                                    if (!(this.mFragment.mView == null || this.mFragment.mContainer == null)) {
                                        SpecialEffectsController.getOrCreateController(this.mFragment.mContainer, this.mFragment.getParentFragmentManager()).enqueueAdd(SpecialEffectsController.Operation.State.from(this.mFragment.mView.getVisibility()), this);
                                    }
                                    this.mFragment.mState = 4;
                                    break;
                                case 5:
                                    start();
                                    break;
                                case 6:
                                    this.mFragment.mState = 6;
                                    break;
                                case 7:
                                    resume();
                                    break;
                            }
                        }
                    } else {
                        if (FragmentManager.USE_STATE_MANAGER && this.mFragment.mHiddenChanged) {
                            if (!(this.mFragment.mView == null || this.mFragment.mContainer == null)) {
                                SpecialEffectsController orCreateController = SpecialEffectsController.getOrCreateController(this.mFragment.mContainer, this.mFragment.getParentFragmentManager());
                                if (this.mFragment.mHidden) {
                                    orCreateController.enqueueHide(this);
                                } else {
                                    orCreateController.enqueueShow(this);
                                }
                            }
                            if (this.mFragment.mFragmentManager != null) {
                                this.mFragment.mFragmentManager.invalidateMenuForFragment(this.mFragment);
                            }
                            this.mFragment.mHiddenChanged = z;
                            Fragment fragment = this.mFragment;
                            fragment.onHiddenChanged(fragment.mHidden);
                        }
                        this.mMovingToState = z;
                        return;
                    }
                }
            } finally {
                this.mMovingToState = z;
            }
        } else if (FragmentManager.isLoggingEnabled(2)) {
            Log.v(TAG, "Ignoring re-entrant call to moveToExpectedState() for " + getFragment());
        }
    }

    /* access modifiers changed from: package-private */
    public void ensureInflatedView() {
        if (this.mFragment.mFromLayout && this.mFragment.mInLayout && !this.mFragment.mPerformedCreateView) {
            if (FragmentManager.isLoggingEnabled(3)) {
                Log.d(TAG, "moveto CREATE_VIEW: " + this.mFragment);
            }
            Fragment fragment = this.mFragment;
            fragment.performCreateView(fragment.performGetLayoutInflater(fragment.mSavedFragmentState), (ViewGroup) null, this.mFragment.mSavedFragmentState);
            if (this.mFragment.mView != null) {
                this.mFragment.mView.setSaveFromParentEnabled(false);
                this.mFragment.mView.setTag(R.id.fragment_container_view_tag, this.mFragment);
                if (this.mFragment.mHidden) {
                    this.mFragment.mView.setVisibility(8);
                }
                this.mFragment.performViewCreated();
                FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher = this.mDispatcher;
                Fragment fragment2 = this.mFragment;
                fragmentLifecycleCallbacksDispatcher.dispatchOnFragmentViewCreated(fragment2, fragment2.mView, this.mFragment.mSavedFragmentState, false);
                this.mFragment.mState = 2;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void restoreState(ClassLoader classLoader) {
        if (this.mFragment.mSavedFragmentState != null) {
            this.mFragment.mSavedFragmentState.setClassLoader(classLoader);
            Fragment fragment = this.mFragment;
            fragment.mSavedViewState = fragment.mSavedFragmentState.getSparseParcelableArray(VIEW_STATE_TAG);
            Fragment fragment2 = this.mFragment;
            fragment2.mSavedViewRegistryState = fragment2.mSavedFragmentState.getBundle(VIEW_REGISTRY_STATE_TAG);
            Fragment fragment3 = this.mFragment;
            fragment3.mTargetWho = fragment3.mSavedFragmentState.getString(TARGET_STATE_TAG);
            if (this.mFragment.mTargetWho != null) {
                Fragment fragment4 = this.mFragment;
                fragment4.mTargetRequestCode = fragment4.mSavedFragmentState.getInt(TARGET_REQUEST_CODE_STATE_TAG, 0);
            }
            if (this.mFragment.mSavedUserVisibleHint != null) {
                Fragment fragment5 = this.mFragment;
                fragment5.mUserVisibleHint = fragment5.mSavedUserVisibleHint.booleanValue();
                this.mFragment.mSavedUserVisibleHint = null;
            } else {
                Fragment fragment6 = this.mFragment;
                fragment6.mUserVisibleHint = fragment6.mSavedFragmentState.getBoolean(USER_VISIBLE_HINT_TAG, true);
            }
            if (!this.mFragment.mUserVisibleHint) {
                this.mFragment.mDeferStart = true;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void attach() {
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d(TAG, "moveto ATTACHED: " + this.mFragment);
        }
        FragmentStateManager fragmentStateManager = null;
        if (this.mFragment.mTarget != null) {
            FragmentStateManager fragmentStateManager2 = this.mFragmentStore.getFragmentStateManager(this.mFragment.mTarget.mWho);
            if (fragmentStateManager2 != null) {
                Fragment fragment = this.mFragment;
                fragment.mTargetWho = fragment.mTarget.mWho;
                this.mFragment.mTarget = null;
                fragmentStateManager = fragmentStateManager2;
            } else {
                throw new IllegalStateException("Fragment " + this.mFragment + " declared target fragment " + this.mFragment.mTarget + " that does not belong to this FragmentManager!");
            }
        } else if (this.mFragment.mTargetWho != null && (fragmentStateManager = this.mFragmentStore.getFragmentStateManager(this.mFragment.mTargetWho)) == null) {
            throw new IllegalStateException("Fragment " + this.mFragment + " declared target fragment " + this.mFragment.mTargetWho + " that does not belong to this FragmentManager!");
        }
        if (fragmentStateManager != null && (FragmentManager.USE_STATE_MANAGER || fragmentStateManager.getFragment().mState < 1)) {
            fragmentStateManager.moveToExpectedState();
        }
        Fragment fragment2 = this.mFragment;
        fragment2.mHost = fragment2.mFragmentManager.getHost();
        Fragment fragment3 = this.mFragment;
        fragment3.mParentFragment = fragment3.mFragmentManager.getParent();
        this.mDispatcher.dispatchOnFragmentPreAttached(this.mFragment, false);
        this.mFragment.performAttach();
        this.mDispatcher.dispatchOnFragmentAttached(this.mFragment, false);
    }

    /* access modifiers changed from: package-private */
    public void create() {
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d(TAG, "moveto CREATED: " + this.mFragment);
        }
        if (!this.mFragment.mIsCreated) {
            FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher = this.mDispatcher;
            Fragment fragment = this.mFragment;
            fragmentLifecycleCallbacksDispatcher.dispatchOnFragmentPreCreated(fragment, fragment.mSavedFragmentState, false);
            Fragment fragment2 = this.mFragment;
            fragment2.performCreate(fragment2.mSavedFragmentState);
            FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher2 = this.mDispatcher;
            Fragment fragment3 = this.mFragment;
            fragmentLifecycleCallbacksDispatcher2.dispatchOnFragmentCreated(fragment3, fragment3.mSavedFragmentState, false);
            return;
        }
        Fragment fragment4 = this.mFragment;
        fragment4.restoreChildFragmentState(fragment4.mSavedFragmentState);
        this.mFragment.mState = 1;
    }

    /* access modifiers changed from: package-private */
    public void createView() {
        String str;
        if (!this.mFragment.mFromLayout) {
            if (FragmentManager.isLoggingEnabled(3)) {
                Log.d(TAG, "moveto CREATE_VIEW: " + this.mFragment);
            }
            Fragment fragment = this.mFragment;
            LayoutInflater performGetLayoutInflater = fragment.performGetLayoutInflater(fragment.mSavedFragmentState);
            ViewGroup viewGroup = null;
            if (this.mFragment.mContainer != null) {
                viewGroup = this.mFragment.mContainer;
            } else if (this.mFragment.mContainerId != 0) {
                if (this.mFragment.mContainerId != -1) {
                    viewGroup = (ViewGroup) this.mFragment.mFragmentManager.getContainer().onFindViewById(this.mFragment.mContainerId);
                    if (viewGroup == null && !this.mFragment.mRestored) {
                        try {
                            str = this.mFragment.getResources().getResourceName(this.mFragment.mContainerId);
                        } catch (Resources.NotFoundException unused) {
                            str = EnvironmentCompat.MEDIA_UNKNOWN;
                        }
                        throw new IllegalArgumentException("No view found for id 0x" + Integer.toHexString(this.mFragment.mContainerId) + " (" + str + ") for fragment " + this.mFragment);
                    }
                } else {
                    throw new IllegalArgumentException("Cannot create fragment " + this.mFragment + " for a container view with no id");
                }
            }
            this.mFragment.mContainer = viewGroup;
            Fragment fragment2 = this.mFragment;
            fragment2.performCreateView(performGetLayoutInflater, viewGroup, fragment2.mSavedFragmentState);
            if (this.mFragment.mView != null) {
                boolean z = false;
                this.mFragment.mView.setSaveFromParentEnabled(false);
                this.mFragment.mView.setTag(R.id.fragment_container_view_tag, this.mFragment);
                if (viewGroup != null) {
                    addViewToContainer();
                }
                if (this.mFragment.mHidden) {
                    this.mFragment.mView.setVisibility(8);
                }
                if (ViewCompat.isAttachedToWindow(this.mFragment.mView)) {
                    ViewCompat.requestApplyInsets(this.mFragment.mView);
                } else {
                    final View view = this.mFragment.mView;
                    view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                        public void onViewDetachedFromWindow(View view) {
                        }

                        public void onViewAttachedToWindow(View view) {
                            view.removeOnAttachStateChangeListener(this);
                            ViewCompat.requestApplyInsets(view);
                        }
                    });
                }
                this.mFragment.performViewCreated();
                FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher = this.mDispatcher;
                Fragment fragment3 = this.mFragment;
                fragmentLifecycleCallbacksDispatcher.dispatchOnFragmentViewCreated(fragment3, fragment3.mView, this.mFragment.mSavedFragmentState, false);
                int visibility = this.mFragment.mView.getVisibility();
                float alpha = this.mFragment.mView.getAlpha();
                if (FragmentManager.USE_STATE_MANAGER) {
                    this.mFragment.setPostOnViewCreatedAlpha(alpha);
                    if (this.mFragment.mContainer != null && visibility == 0) {
                        View findFocus = this.mFragment.mView.findFocus();
                        if (findFocus != null) {
                            this.mFragment.setFocusedView(findFocus);
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v(TAG, "requestFocus: Saved focused view " + findFocus + " for Fragment " + this.mFragment);
                            }
                        }
                        this.mFragment.mView.setAlpha(0.0f);
                    }
                } else {
                    Fragment fragment4 = this.mFragment;
                    if (visibility == 0 && fragment4.mContainer != null) {
                        z = true;
                    }
                    fragment4.mIsNewlyAdded = z;
                }
            }
            this.mFragment.mState = 2;
        }
    }

    /* access modifiers changed from: package-private */
    public void activityCreated() {
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d(TAG, "moveto ACTIVITY_CREATED: " + this.mFragment);
        }
        Fragment fragment = this.mFragment;
        fragment.performActivityCreated(fragment.mSavedFragmentState);
        FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher = this.mDispatcher;
        Fragment fragment2 = this.mFragment;
        fragmentLifecycleCallbacksDispatcher.dispatchOnFragmentActivityCreated(fragment2, fragment2.mSavedFragmentState, false);
    }

    /* access modifiers changed from: package-private */
    public void start() {
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d(TAG, "moveto STARTED: " + this.mFragment);
        }
        this.mFragment.performStart();
        this.mDispatcher.dispatchOnFragmentStarted(this.mFragment, false);
    }

    /* access modifiers changed from: package-private */
    public void resume() {
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d(TAG, "moveto RESUMED: " + this.mFragment);
        }
        View focusedView = this.mFragment.getFocusedView();
        if (focusedView != null && isFragmentViewChild(focusedView)) {
            boolean requestFocus = focusedView.requestFocus();
            if (FragmentManager.isLoggingEnabled(2)) {
                StringBuilder sb = new StringBuilder();
                sb.append("requestFocus: Restoring focused view ");
                sb.append(focusedView);
                sb.append(" ");
                sb.append(requestFocus ? "succeeded" : "failed");
                sb.append(" on Fragment ");
                sb.append(this.mFragment);
                sb.append(" resulting in focused view ");
                sb.append(this.mFragment.mView.findFocus());
                Log.v(TAG, sb.toString());
            }
        }
        this.mFragment.setFocusedView((View) null);
        this.mFragment.performResume();
        this.mDispatcher.dispatchOnFragmentResumed(this.mFragment, false);
        this.mFragment.mSavedFragmentState = null;
        this.mFragment.mSavedViewState = null;
        this.mFragment.mSavedViewRegistryState = null;
    }

    private boolean isFragmentViewChild(View view) {
        if (view == this.mFragment.mView) {
            return true;
        }
        for (ViewParent parent = view.getParent(); parent != null; parent = parent.getParent()) {
            if (parent == this.mFragment.mView) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void pause() {
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d(TAG, "movefrom RESUMED: " + this.mFragment);
        }
        this.mFragment.performPause();
        this.mDispatcher.dispatchOnFragmentPaused(this.mFragment, false);
    }

    /* access modifiers changed from: package-private */
    public void stop() {
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d(TAG, "movefrom STARTED: " + this.mFragment);
        }
        this.mFragment.performStop();
        this.mDispatcher.dispatchOnFragmentStopped(this.mFragment, false);
    }

    /* access modifiers changed from: package-private */
    public FragmentState saveState() {
        FragmentState fragmentState = new FragmentState(this.mFragment);
        if (this.mFragment.mState <= -1 || fragmentState.mSavedFragmentState != null) {
            fragmentState.mSavedFragmentState = this.mFragment.mSavedFragmentState;
        } else {
            fragmentState.mSavedFragmentState = saveBasicState();
            if (this.mFragment.mTargetWho != null) {
                if (fragmentState.mSavedFragmentState == null) {
                    fragmentState.mSavedFragmentState = new Bundle();
                }
                fragmentState.mSavedFragmentState.putString(TARGET_STATE_TAG, this.mFragment.mTargetWho);
                if (this.mFragment.mTargetRequestCode != 0) {
                    fragmentState.mSavedFragmentState.putInt(TARGET_REQUEST_CODE_STATE_TAG, this.mFragment.mTargetRequestCode);
                }
            }
        }
        return fragmentState;
    }

    /* access modifiers changed from: package-private */
    public Fragment.SavedState saveInstanceState() {
        Bundle saveBasicState;
        if (this.mFragment.mState <= -1 || (saveBasicState = saveBasicState()) == null) {
            return null;
        }
        return new Fragment.SavedState(saveBasicState);
    }

    private Bundle saveBasicState() {
        Bundle bundle = new Bundle();
        this.mFragment.performSaveInstanceState(bundle);
        this.mDispatcher.dispatchOnFragmentSaveInstanceState(this.mFragment, bundle, false);
        if (bundle.isEmpty()) {
            bundle = null;
        }
        if (this.mFragment.mView != null) {
            saveViewState();
        }
        if (this.mFragment.mSavedViewState != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putSparseParcelableArray(VIEW_STATE_TAG, this.mFragment.mSavedViewState);
        }
        if (this.mFragment.mSavedViewRegistryState != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBundle(VIEW_REGISTRY_STATE_TAG, this.mFragment.mSavedViewRegistryState);
        }
        if (!this.mFragment.mUserVisibleHint) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean(USER_VISIBLE_HINT_TAG, this.mFragment.mUserVisibleHint);
        }
        return bundle;
    }

    /* access modifiers changed from: package-private */
    public void saveViewState() {
        if (this.mFragment.mView != null) {
            SparseArray<Parcelable> sparseArray = new SparseArray<>();
            this.mFragment.mView.saveHierarchyState(sparseArray);
            if (sparseArray.size() > 0) {
                this.mFragment.mSavedViewState = sparseArray;
            }
            Bundle bundle = new Bundle();
            this.mFragment.mViewLifecycleOwner.performSave(bundle);
            if (!bundle.isEmpty()) {
                this.mFragment.mSavedViewRegistryState = bundle;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void destroyFragmentView() {
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d(TAG, "movefrom CREATE_VIEW: " + this.mFragment);
        }
        if (!(this.mFragment.mContainer == null || this.mFragment.mView == null)) {
            this.mFragment.mContainer.removeView(this.mFragment.mView);
        }
        this.mFragment.performDestroyView();
        this.mDispatcher.dispatchOnFragmentViewDestroyed(this.mFragment, false);
        this.mFragment.mContainer = null;
        this.mFragment.mView = null;
        this.mFragment.mViewLifecycleOwner = null;
        this.mFragment.mViewLifecycleOwnerLiveData.setValue(null);
        this.mFragment.mInLayout = false;
    }

    /* access modifiers changed from: package-private */
    public void destroy() {
        Fragment findActiveFragment;
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d(TAG, "movefrom CREATED: " + this.mFragment);
        }
        boolean z = true;
        boolean z2 = this.mFragment.mRemoving && !this.mFragment.isInBackStack();
        if (z2 || this.mFragmentStore.getNonConfig().shouldDestroy(this.mFragment)) {
            FragmentHostCallback<?> fragmentHostCallback = this.mFragment.mHost;
            if (fragmentHostCallback instanceof ViewModelStoreOwner) {
                z = this.mFragmentStore.getNonConfig().isCleared();
            } else if (fragmentHostCallback.getContext() instanceof Activity) {
                z = true ^ ((Activity) fragmentHostCallback.getContext()).isChangingConfigurations();
            }
            if (z2 || z) {
                this.mFragmentStore.getNonConfig().clearNonConfigState(this.mFragment);
            }
            this.mFragment.performDestroy();
            this.mDispatcher.dispatchOnFragmentDestroyed(this.mFragment, false);
            for (FragmentStateManager next : this.mFragmentStore.getActiveFragmentStateManagers()) {
                if (next != null) {
                    Fragment fragment = next.getFragment();
                    if (this.mFragment.mWho.equals(fragment.mTargetWho)) {
                        fragment.mTarget = this.mFragment;
                        fragment.mTargetWho = null;
                    }
                }
            }
            if (this.mFragment.mTargetWho != null) {
                Fragment fragment2 = this.mFragment;
                fragment2.mTarget = this.mFragmentStore.findActiveFragment(fragment2.mTargetWho);
            }
            this.mFragmentStore.makeInactive(this);
            return;
        }
        if (!(this.mFragment.mTargetWho == null || (findActiveFragment = this.mFragmentStore.findActiveFragment(this.mFragment.mTargetWho)) == null || !findActiveFragment.mRetainInstance)) {
            this.mFragment.mTarget = findActiveFragment;
        }
        this.mFragment.mState = 0;
    }

    /* access modifiers changed from: package-private */
    public void detach() {
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d(TAG, "movefrom ATTACHED: " + this.mFragment);
        }
        this.mFragment.performDetach();
        boolean z = false;
        this.mDispatcher.dispatchOnFragmentDetached(this.mFragment, false);
        this.mFragment.mState = -1;
        this.mFragment.mHost = null;
        this.mFragment.mParentFragment = null;
        this.mFragment.mFragmentManager = null;
        if (this.mFragment.mRemoving && !this.mFragment.isInBackStack()) {
            z = true;
        }
        if (z || this.mFragmentStore.getNonConfig().shouldDestroy(this.mFragment)) {
            if (FragmentManager.isLoggingEnabled(3)) {
                Log.d(TAG, "initState called for fragment: " + this.mFragment);
            }
            this.mFragment.initState();
        }
    }

    /* access modifiers changed from: package-private */
    public void addViewToContainer() {
        this.mFragment.mContainer.addView(this.mFragment.mView, this.mFragmentStore.findFragmentIndexInContainer(this.mFragment));
    }
}
