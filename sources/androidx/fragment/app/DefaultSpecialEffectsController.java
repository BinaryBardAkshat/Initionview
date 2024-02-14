package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.collection.ArrayMap;
import androidx.core.os.CancellationSignal;
import androidx.core.util.Preconditions;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupCompat;
import androidx.fragment.app.FragmentAnim;
import androidx.fragment.app.SpecialEffectsController;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class DefaultSpecialEffectsController extends SpecialEffectsController {
    DefaultSpecialEffectsController(ViewGroup viewGroup) {
        super(viewGroup);
    }

    /* access modifiers changed from: package-private */
    public void executeOperations(List<SpecialEffectsController.Operation> list, boolean z) {
        SpecialEffectsController.Operation operation = null;
        SpecialEffectsController.Operation operation2 = null;
        for (SpecialEffectsController.Operation next : list) {
            SpecialEffectsController.Operation.State from = SpecialEffectsController.Operation.State.from(next.getFragment().mView);
            int i = AnonymousClass10.$SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State[next.getFinalState().ordinal()];
            if (i == 1 || i == 2 || i == 3) {
                if (from == SpecialEffectsController.Operation.State.VISIBLE && operation == null) {
                    operation = next;
                }
            } else if (i == 4 && from != SpecialEffectsController.Operation.State.VISIBLE) {
                operation2 = next;
            }
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        final ArrayList<SpecialEffectsController.Operation> arrayList3 = new ArrayList<>(list);
        for (final SpecialEffectsController.Operation next2 : list) {
            CancellationSignal cancellationSignal = new CancellationSignal();
            next2.markStartedSpecialEffect(cancellationSignal);
            arrayList.add(new AnimationInfo(next2, cancellationSignal, z));
            CancellationSignal cancellationSignal2 = new CancellationSignal();
            next2.markStartedSpecialEffect(cancellationSignal2);
            boolean z2 = false;
            if (z) {
                if (next2 != operation) {
                    arrayList2.add(new TransitionInfo(next2, cancellationSignal2, z, z2));
                    next2.addCompletionListener(new Runnable() {
                        public void run() {
                            if (arrayList3.contains(next2)) {
                                arrayList3.remove(next2);
                                DefaultSpecialEffectsController.this.applyContainerChanges(next2);
                            }
                        }
                    });
                }
            } else if (next2 != operation2) {
                arrayList2.add(new TransitionInfo(next2, cancellationSignal2, z, z2));
                next2.addCompletionListener(new Runnable() {
                    public void run() {
                        if (arrayList3.contains(next2)) {
                            arrayList3.remove(next2);
                            DefaultSpecialEffectsController.this.applyContainerChanges(next2);
                        }
                    }
                });
            }
            z2 = true;
            arrayList2.add(new TransitionInfo(next2, cancellationSignal2, z, z2));
            next2.addCompletionListener(new Runnable() {
                public void run() {
                    if (arrayList3.contains(next2)) {
                        arrayList3.remove(next2);
                        DefaultSpecialEffectsController.this.applyContainerChanges(next2);
                    }
                }
            });
        }
        Map<SpecialEffectsController.Operation, Boolean> startTransitions = startTransitions(arrayList2, arrayList3, z, operation, operation2);
        startAnimations(arrayList, arrayList3, startTransitions.containsValue(true), startTransitions);
        for (SpecialEffectsController.Operation applyContainerChanges : arrayList3) {
            applyContainerChanges(applyContainerChanges);
        }
        arrayList3.clear();
    }

    /* renamed from: androidx.fragment.app.DefaultSpecialEffectsController$10  reason: invalid class name */
    static /* synthetic */ class AnonymousClass10 {
        static final /* synthetic */ int[] $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                androidx.fragment.app.SpecialEffectsController$Operation$State[] r0 = androidx.fragment.app.SpecialEffectsController.Operation.State.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State = r0
                androidx.fragment.app.SpecialEffectsController$Operation$State r1 = androidx.fragment.app.SpecialEffectsController.Operation.State.GONE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State     // Catch:{ NoSuchFieldError -> 0x001d }
                androidx.fragment.app.SpecialEffectsController$Operation$State r1 = androidx.fragment.app.SpecialEffectsController.Operation.State.INVISIBLE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State     // Catch:{ NoSuchFieldError -> 0x0028 }
                androidx.fragment.app.SpecialEffectsController$Operation$State r1 = androidx.fragment.app.SpecialEffectsController.Operation.State.REMOVED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State     // Catch:{ NoSuchFieldError -> 0x0033 }
                androidx.fragment.app.SpecialEffectsController$Operation$State r1 = androidx.fragment.app.SpecialEffectsController.Operation.State.VISIBLE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.DefaultSpecialEffectsController.AnonymousClass10.<clinit>():void");
        }
    }

    private void startAnimations(List<AnimationInfo> list, List<SpecialEffectsController.Operation> list2, boolean z, Map<SpecialEffectsController.Operation, Boolean> map) {
        final ViewGroup container = getContainer();
        Context context = container.getContext();
        ArrayList arrayList = new ArrayList();
        boolean z2 = false;
        for (AnimationInfo next : list) {
            if (next.isVisibilityUnchanged()) {
                next.completeSpecialEffect();
            } else {
                FragmentAnim.AnimationOrAnimator animation = next.getAnimation(context);
                if (animation == null) {
                    next.completeSpecialEffect();
                } else {
                    final Animator animator = animation.animator;
                    if (animator == null) {
                        arrayList.add(next);
                    } else {
                        final SpecialEffectsController.Operation operation = next.getOperation();
                        Fragment fragment = operation.getFragment();
                        if (Boolean.TRUE.equals(map.get(operation))) {
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v("FragmentManager", "Ignoring Animator set on " + fragment + " as this Fragment was involved in a Transition.");
                            }
                            next.completeSpecialEffect();
                        } else {
                            final boolean z3 = operation.getFinalState() == SpecialEffectsController.Operation.State.GONE;
                            List<SpecialEffectsController.Operation> list3 = list2;
                            if (z3) {
                                list3.remove(operation);
                            }
                            final View view = fragment.mView;
                            container.startViewTransition(view);
                            AnonymousClass2 r13 = r0;
                            final ViewGroup viewGroup = container;
                            final AnimationInfo animationInfo = next;
                            AnonymousClass2 r0 = new AnimatorListenerAdapter() {
                                public void onAnimationEnd(Animator animator) {
                                    viewGroup.endViewTransition(view);
                                    if (z3) {
                                        operation.getFinalState().applyState(view);
                                    }
                                    animationInfo.completeSpecialEffect();
                                }
                            };
                            animator.addListener(r13);
                            animator.setTarget(view);
                            animator.start();
                            next.getSignal().setOnCancelListener(new CancellationSignal.OnCancelListener() {
                                public void onCancel() {
                                    animator.end();
                                }
                            });
                            z2 = true;
                        }
                    }
                }
            }
            Map<SpecialEffectsController.Operation, Boolean> map2 = map;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            final AnimationInfo animationInfo2 = (AnimationInfo) it.next();
            SpecialEffectsController.Operation operation2 = animationInfo2.getOperation();
            Fragment fragment2 = operation2.getFragment();
            if (z) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Transitions.");
                }
                animationInfo2.completeSpecialEffect();
            } else if (z2) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Animators.");
                }
                animationInfo2.completeSpecialEffect();
            } else {
                final View view2 = fragment2.mView;
                Animation animation2 = (Animation) Preconditions.checkNotNull(((FragmentAnim.AnimationOrAnimator) Preconditions.checkNotNull(animationInfo2.getAnimation(context))).animation);
                if (operation2.getFinalState() != SpecialEffectsController.Operation.State.REMOVED) {
                    view2.startAnimation(animation2);
                    animationInfo2.completeSpecialEffect();
                } else {
                    container.startViewTransition(view2);
                    FragmentAnim.EndViewTransitionAnimation endViewTransitionAnimation = new FragmentAnim.EndViewTransitionAnimation(animation2, container, view2);
                    endViewTransitionAnimation.setAnimationListener(new Animation.AnimationListener() {
                        public void onAnimationRepeat(Animation animation) {
                        }

                        public void onAnimationStart(Animation animation) {
                        }

                        public void onAnimationEnd(Animation animation) {
                            container.post(new Runnable() {
                                public void run() {
                                    container.endViewTransition(view2);
                                    animationInfo2.completeSpecialEffect();
                                }
                            });
                        }
                    });
                    view2.startAnimation(endViewTransitionAnimation);
                }
                animationInfo2.getSignal().setOnCancelListener(new CancellationSignal.OnCancelListener() {
                    public void onCancel() {
                        view2.clearAnimation();
                        container.endViewTransition(view2);
                        animationInfo2.completeSpecialEffect();
                    }
                });
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:78:0x02a2, code lost:
        r0 = (android.view.View) r9.get(r11.get(r1));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Map<androidx.fragment.app.SpecialEffectsController.Operation, java.lang.Boolean> startTransitions(java.util.List<androidx.fragment.app.DefaultSpecialEffectsController.TransitionInfo> r32, java.util.List<androidx.fragment.app.SpecialEffectsController.Operation> r33, boolean r34, androidx.fragment.app.SpecialEffectsController.Operation r35, androidx.fragment.app.SpecialEffectsController.Operation r36) {
        /*
            r31 = this;
            r6 = r31
            r7 = r34
            r8 = r35
            r9 = r36
            java.util.HashMap r10 = new java.util.HashMap
            r10.<init>()
            java.util.Iterator r0 = r32.iterator()
            r15 = 0
        L_0x0012:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0062
            java.lang.Object r1 = r0.next()
            androidx.fragment.app.DefaultSpecialEffectsController$TransitionInfo r1 = (androidx.fragment.app.DefaultSpecialEffectsController.TransitionInfo) r1
            boolean r2 = r1.isVisibilityUnchanged()
            if (r2 == 0) goto L_0x0025
            goto L_0x0012
        L_0x0025:
            androidx.fragment.app.FragmentTransitionImpl r2 = r1.getHandlingImpl()
            if (r15 != 0) goto L_0x002d
            r15 = r2
            goto L_0x0012
        L_0x002d:
            if (r2 == 0) goto L_0x0012
            if (r15 != r2) goto L_0x0032
            goto L_0x0012
        L_0x0032:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Mixing framework transitions and AndroidX transitions is not allowed. Fragment "
            r2.append(r3)
            androidx.fragment.app.SpecialEffectsController$Operation r3 = r1.getOperation()
            androidx.fragment.app.Fragment r3 = r3.getFragment()
            r2.append(r3)
            java.lang.String r3 = " returned Transition "
            r2.append(r3)
            java.lang.Object r1 = r1.getTransition()
            r2.append(r1)
            java.lang.String r1 = " which uses a different Transition  type than other Fragments."
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            r0.<init>(r1)
            throw r0
        L_0x0062:
            r14 = 0
            if (r15 != 0) goto L_0x0085
            java.util.Iterator r0 = r32.iterator()
        L_0x0069:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0084
            java.lang.Object r1 = r0.next()
            androidx.fragment.app.DefaultSpecialEffectsController$TransitionInfo r1 = (androidx.fragment.app.DefaultSpecialEffectsController.TransitionInfo) r1
            androidx.fragment.app.SpecialEffectsController$Operation r2 = r1.getOperation()
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r14)
            r10.put(r2, r3)
            r1.completeSpecialEffect()
            goto L_0x0069
        L_0x0084:
            return r10
        L_0x0085:
            android.view.View r13 = new android.view.View
            android.view.ViewGroup r0 = r31.getContainer()
            android.content.Context r0 = r0.getContext()
            r13.<init>(r0)
            android.graphics.Rect r12 = new android.graphics.Rect
            r12.<init>()
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            androidx.collection.ArrayMap r3 = new androidx.collection.ArrayMap
            r3.<init>()
            java.util.Iterator r20 = r32.iterator()
            r0 = 0
            r2 = 0
            r21 = 0
        L_0x00ae:
            boolean r1 = r20.hasNext()
            if (r1 == 0) goto L_0x031b
            java.lang.Object r1 = r20.next()
            androidx.fragment.app.DefaultSpecialEffectsController$TransitionInfo r1 = (androidx.fragment.app.DefaultSpecialEffectsController.TransitionInfo) r1
            boolean r16 = r1.hasSharedElementTransition()
            if (r16 == 0) goto L_0x02f5
            if (r8 == 0) goto L_0x02f5
            if (r9 == 0) goto L_0x02f5
            java.lang.Object r0 = r1.getSharedElementTransition()
            java.lang.Object r0 = r15.cloneTransition(r0)
            java.lang.Object r1 = r15.wrapTransitionInSet(r0)
            androidx.fragment.app.Fragment r0 = r36.getFragment()
            java.util.ArrayList r0 = r0.getSharedElementSourceNames()
            androidx.fragment.app.Fragment r16 = r35.getFragment()
            java.util.ArrayList r14 = r16.getSharedElementSourceNames()
            androidx.fragment.app.Fragment r16 = r35.getFragment()
            java.util.ArrayList r11 = r16.getSharedElementTargetNames()
            r16 = r1
            r18 = r2
            r1 = 0
        L_0x00ed:
            int r2 = r11.size()
            if (r1 >= r2) goto L_0x010c
            java.lang.Object r2 = r11.get(r1)
            int r2 = r0.indexOf(r2)
            r19 = r11
            r11 = -1
            if (r2 == r11) goto L_0x0107
            java.lang.Object r11 = r14.get(r1)
            r0.set(r2, r11)
        L_0x0107:
            int r1 = r1 + 1
            r11 = r19
            goto L_0x00ed
        L_0x010c:
            androidx.fragment.app.Fragment r1 = r36.getFragment()
            java.util.ArrayList r11 = r1.getSharedElementTargetNames()
            if (r7 != 0) goto L_0x0127
            androidx.fragment.app.Fragment r1 = r35.getFragment()
            androidx.core.app.SharedElementCallback r1 = r1.getExitTransitionCallback()
            androidx.fragment.app.Fragment r2 = r36.getFragment()
            androidx.core.app.SharedElementCallback r2 = r2.getEnterTransitionCallback()
            goto L_0x0137
        L_0x0127:
            androidx.fragment.app.Fragment r1 = r35.getFragment()
            androidx.core.app.SharedElementCallback r1 = r1.getEnterTransitionCallback()
            androidx.fragment.app.Fragment r2 = r36.getFragment()
            androidx.core.app.SharedElementCallback r2 = r2.getExitTransitionCallback()
        L_0x0137:
            int r14 = r0.size()
            r9 = 0
        L_0x013c:
            if (r9 >= r14) goto L_0x015a
            java.lang.Object r19 = r0.get(r9)
            r22 = r14
            r14 = r19
            java.lang.String r14 = (java.lang.String) r14
            java.lang.Object r19 = r11.get(r9)
            r8 = r19
            java.lang.String r8 = (java.lang.String) r8
            r3.put(r14, r8)
            int r9 = r9 + 1
            r8 = r35
            r14 = r22
            goto L_0x013c
        L_0x015a:
            androidx.collection.ArrayMap r8 = new androidx.collection.ArrayMap
            r8.<init>()
            androidx.fragment.app.Fragment r9 = r35.getFragment()
            android.view.View r9 = r9.mView
            r6.findNamedViews(r8, r9)
            r8.retainAll(r0)
            if (r1 == 0) goto L_0x01ad
            r1.onMapSharedElements(r0, r8)
            int r1 = r0.size()
            r9 = 1
            int r1 = r1 - r9
        L_0x0176:
            if (r1 < 0) goto L_0x01aa
            java.lang.Object r9 = r0.get(r1)
            java.lang.String r9 = (java.lang.String) r9
            java.lang.Object r14 = r8.get(r9)
            android.view.View r14 = (android.view.View) r14
            if (r14 != 0) goto L_0x018c
            r3.remove(r9)
            r19 = r0
            goto L_0x01a5
        L_0x018c:
            r19 = r0
            java.lang.String r0 = androidx.core.view.ViewCompat.getTransitionName(r14)
            boolean r0 = r9.equals(r0)
            if (r0 != 0) goto L_0x01a5
            java.lang.Object r0 = r3.remove(r9)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r9 = androidx.core.view.ViewCompat.getTransitionName(r14)
            r3.put(r9, r0)
        L_0x01a5:
            int r1 = r1 + -1
            r0 = r19
            goto L_0x0176
        L_0x01aa:
            r19 = r0
            goto L_0x01b6
        L_0x01ad:
            r19 = r0
            java.util.Set r0 = r8.keySet()
            r3.retainAll(r0)
        L_0x01b6:
            androidx.collection.ArrayMap r9 = new androidx.collection.ArrayMap
            r9.<init>()
            androidx.fragment.app.Fragment r0 = r36.getFragment()
            android.view.View r0 = r0.mView
            r6.findNamedViews(r9, r0)
            r9.retainAll(r11)
            java.util.Collection r0 = r3.values()
            r9.retainAll(r0)
            if (r2 == 0) goto L_0x020d
            r2.onMapSharedElements(r11, r9)
            int r0 = r11.size()
            r1 = 1
            int r0 = r0 - r1
        L_0x01d9:
            if (r0 < 0) goto L_0x0210
            java.lang.Object r1 = r11.get(r0)
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r2 = r9.get(r1)
            android.view.View r2 = (android.view.View) r2
            if (r2 != 0) goto L_0x01f3
            java.lang.String r1 = androidx.fragment.app.FragmentTransition.findKeyForValue(r3, r1)
            if (r1 == 0) goto L_0x020a
            r3.remove(r1)
            goto L_0x020a
        L_0x01f3:
            java.lang.String r14 = androidx.core.view.ViewCompat.getTransitionName(r2)
            boolean r14 = r1.equals(r14)
            if (r14 != 0) goto L_0x020a
            java.lang.String r1 = androidx.fragment.app.FragmentTransition.findKeyForValue(r3, r1)
            if (r1 == 0) goto L_0x020a
            java.lang.String r2 = androidx.core.view.ViewCompat.getTransitionName(r2)
            r3.put(r1, r2)
        L_0x020a:
            int r0 = r0 + -1
            goto L_0x01d9
        L_0x020d:
            androidx.fragment.app.FragmentTransition.retainValues(r3, r9)
        L_0x0210:
            java.util.Set r0 = r3.keySet()
            r6.retainMatchingViews(r8, r0)
            java.util.Collection r0 = r3.values()
            r6.retainMatchingViews(r9, r0)
            boolean r0 = r3.isEmpty()
            if (r0 == 0) goto L_0x0240
            r5.clear()
            r4.clear()
            r24 = r3
            r9 = r4
            r4 = r12
            r8 = r13
            r11 = r15
            r2 = r18
            r0 = 0
            r1 = 0
            r3 = r36
            r15 = r10
            r10 = r35
            r30 = r6
            r6 = r5
            r5 = r30
            goto L_0x0308
        L_0x0240:
            androidx.fragment.app.Fragment r0 = r36.getFragment()
            androidx.fragment.app.Fragment r1 = r35.getFragment()
            r2 = 1
            androidx.fragment.app.FragmentTransition.callSharedElementStartEnd(r0, r1, r7, r8, r2)
            android.view.ViewGroup r14 = r31.getContainer()
            androidx.fragment.app.DefaultSpecialEffectsController$6 r2 = new androidx.fragment.app.DefaultSpecialEffectsController$6
            r1 = r19
            r0 = r2
            r7 = r16
            r1 = r31
            r22 = r10
            r23 = r18
            r10 = r2
            r2 = r36
            r24 = r3
            r3 = r35
            r16 = r13
            r13 = r4
            r4 = r34
            r6 = r5
            r5 = r9
            r0.<init>(r2, r3, r4, r5)
            androidx.core.view.OneShotPreDrawListener.add(r14, r10)
            java.util.Collection r0 = r8.values()
            r6.addAll(r0)
            boolean r0 = r19.isEmpty()
            if (r0 != 0) goto L_0x0292
            r0 = r19
            r1 = 0
            java.lang.Object r0 = r0.get(r1)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r0 = r8.get(r0)
            android.view.View r0 = (android.view.View) r0
            r15.setEpicenter((java.lang.Object) r7, (android.view.View) r0)
            r2 = r0
            goto L_0x0295
        L_0x0292:
            r1 = 0
            r2 = r23
        L_0x0295:
            java.util.Collection r0 = r9.values()
            r13.addAll(r0)
            boolean r0 = r11.isEmpty()
            if (r0 != 0) goto L_0x02c3
            java.lang.Object r0 = r11.get(r1)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r0 = r9.get(r0)
            android.view.View r0 = (android.view.View) r0
            if (r0 == 0) goto L_0x02c3
            android.view.ViewGroup r3 = r31.getContainer()
            androidx.fragment.app.DefaultSpecialEffectsController$7 r4 = new androidx.fragment.app.DefaultSpecialEffectsController$7
            r5 = r31
            r4.<init>(r15, r0, r12)
            androidx.core.view.OneShotPreDrawListener.add(r3, r4)
            r0 = r16
            r21 = 1
            goto L_0x02c7
        L_0x02c3:
            r5 = r31
            r0 = r16
        L_0x02c7:
            r15.setSharedElementTargets(r7, r0, r6)
            r14 = 0
            r3 = 0
            r16 = 0
            r17 = 0
            r4 = r12
            r12 = r15
            r8 = r0
            r9 = r13
            r13 = r7
            r11 = r15
            r15 = r3
            r18 = r7
            r19 = r9
            r12.scheduleRemoveTargets(r13, r14, r15, r16, r17, r18, r19)
            r0 = 1
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r0)
            r10 = r35
            r15 = r22
            r15.put(r10, r3)
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            r3 = r36
            r15.put(r3, r0)
            r0 = r7
            goto L_0x0308
        L_0x02f5:
            r23 = r2
            r24 = r3
            r3 = r9
            r11 = r15
            r1 = 0
            r9 = r4
            r15 = r10
            r4 = r12
            r10 = r8
            r8 = r13
            r30 = r6
            r6 = r5
            r5 = r30
            r2 = r23
        L_0x0308:
            r7 = r34
            r12 = r4
            r13 = r8
            r4 = r9
            r8 = r10
            r10 = r15
            r14 = 0
            r9 = r3
            r15 = r11
            r3 = r24
            r30 = r6
            r6 = r5
            r5 = r30
            goto L_0x00ae
        L_0x031b:
            r23 = r2
            r24 = r3
            r3 = r9
            r11 = r15
            r1 = 0
            r9 = r4
            r15 = r10
            r4 = r12
            r10 = r8
            r8 = r13
            r30 = r6
            r6 = r5
            r5 = r30
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Iterator r7 = r32.iterator()
            r13 = 0
            r14 = 0
        L_0x0337:
            boolean r12 = r7.hasNext()
            if (r12 == 0) goto L_0x0464
            java.lang.Object r12 = r7.next()
            r20 = r12
            androidx.fragment.app.DefaultSpecialEffectsController$TransitionInfo r20 = (androidx.fragment.app.DefaultSpecialEffectsController.TransitionInfo) r20
            boolean r12 = r20.isVisibilityUnchanged()
            if (r12 == 0) goto L_0x035e
            androidx.fragment.app.SpecialEffectsController$Operation r12 = r20.getOperation()
            r34 = r7
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r1)
            r15.put(r12, r7)
            r20.completeSpecialEffect()
        L_0x035b:
            r7 = r34
            goto L_0x0337
        L_0x035e:
            r34 = r7
            java.lang.Object r7 = r20.getTransition()
            java.lang.Object r7 = r11.cloneTransition(r7)
            androidx.fragment.app.SpecialEffectsController$Operation r12 = r20.getOperation()
            if (r0 == 0) goto L_0x0375
            if (r12 == r10) goto L_0x0372
            if (r12 != r3) goto L_0x0375
        L_0x0372:
            r16 = 1
            goto L_0x0377
        L_0x0375:
            r16 = 0
        L_0x0377:
            if (r7 != 0) goto L_0x0396
            if (r16 != 0) goto L_0x0385
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r1)
            r15.put(r12, r7)
            r20.completeSpecialEffect()
        L_0x0385:
            r12 = r33
            r28 = r6
            r26 = r8
            r29 = r9
            r7 = r13
            r6 = r14
            r9 = r15
            r1 = r23
            r13 = 0
            r14 = 1
            goto L_0x0456
        L_0x0396:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r17 = r13
            androidx.fragment.app.Fragment r13 = r12.getFragment()
            android.view.View r13 = r13.mView
            r5.captureTransitioningViews(r1, r13)
            if (r16 == 0) goto L_0x03b1
            if (r12 != r10) goto L_0x03ae
            r1.removeAll(r6)
            goto L_0x03b1
        L_0x03ae:
            r1.removeAll(r9)
        L_0x03b1:
            boolean r13 = r1.isEmpty()
            if (r13 == 0) goto L_0x03c8
            r11.addTarget(r7, r8)
            r28 = r6
            r26 = r8
            r29 = r9
            r13 = r12
            r6 = r14
            r9 = r15
            r8 = r17
            r12 = r33
            goto L_0x0424
        L_0x03c8:
            r11.addTargets(r7, r1)
            r16 = 0
            r18 = 0
            r19 = 0
            r25 = 0
            r13 = r12
            r12 = r11
            r26 = r8
            r27 = r13
            r8 = r17
            r13 = r7
            r28 = r6
            r6 = r14
            r14 = r7
            r29 = r9
            r9 = r15
            r15 = r1
            r17 = r18
            r18 = r19
            r19 = r25
            r12.scheduleRemoveTargets(r13, r14, r15, r16, r17, r18, r19)
            androidx.fragment.app.SpecialEffectsController$Operation$State r12 = r27.getFinalState()
            androidx.fragment.app.SpecialEffectsController$Operation$State r13 = androidx.fragment.app.SpecialEffectsController.Operation.State.GONE
            if (r12 != r13) goto L_0x0420
            r12 = r33
            r13 = r27
            r12.remove(r13)
            java.util.ArrayList r14 = new java.util.ArrayList
            r14.<init>(r1)
            androidx.fragment.app.Fragment r15 = r13.getFragment()
            android.view.View r15 = r15.mView
            r14.remove(r15)
            androidx.fragment.app.Fragment r15 = r13.getFragment()
            android.view.View r15 = r15.mView
            r11.scheduleHideFragmentView(r7, r15, r14)
            android.view.ViewGroup r14 = r31.getContainer()
            androidx.fragment.app.DefaultSpecialEffectsController$8 r15 = new androidx.fragment.app.DefaultSpecialEffectsController$8
            r15.<init>(r1)
            androidx.core.view.OneShotPreDrawListener.add(r14, r15)
            goto L_0x0424
        L_0x0420:
            r12 = r33
            r13 = r27
        L_0x0424:
            androidx.fragment.app.SpecialEffectsController$Operation$State r14 = r13.getFinalState()
            androidx.fragment.app.SpecialEffectsController$Operation$State r15 = androidx.fragment.app.SpecialEffectsController.Operation.State.VISIBLE
            if (r14 != r15) goto L_0x0437
            r2.addAll(r1)
            if (r21 == 0) goto L_0x0434
            r11.setEpicenter((java.lang.Object) r7, (android.graphics.Rect) r4)
        L_0x0434:
            r1 = r23
            goto L_0x043c
        L_0x0437:
            r1 = r23
            r11.setEpicenter((java.lang.Object) r7, (android.view.View) r1)
        L_0x043c:
            r14 = 1
            java.lang.Boolean r15 = java.lang.Boolean.valueOf(r14)
            r9.put(r13, r15)
            boolean r13 = r20.isOverlapAllowed()
            if (r13 == 0) goto L_0x0451
            r13 = 0
            java.lang.Object r6 = r11.mergeTransitionsTogether(r6, r7, r13)
            r7 = r8
            goto L_0x0456
        L_0x0451:
            r13 = 0
            java.lang.Object r7 = r11.mergeTransitionsTogether(r8, r7, r13)
        L_0x0456:
            r23 = r1
            r14 = r6
            r13 = r7
            r15 = r9
            r8 = r26
            r6 = r28
            r9 = r29
            r1 = 0
            goto L_0x035b
        L_0x0464:
            r28 = r6
            r29 = r9
            r8 = r13
            r6 = r14
            r9 = r15
            r14 = 1
            java.lang.Object r1 = r11.mergeTransitionsInSequence(r6, r8, r0)
            java.util.Iterator r4 = r32.iterator()
        L_0x0474:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto L_0x04e8
            java.lang.Object r6 = r4.next()
            androidx.fragment.app.DefaultSpecialEffectsController$TransitionInfo r6 = (androidx.fragment.app.DefaultSpecialEffectsController.TransitionInfo) r6
            boolean r7 = r6.isVisibilityUnchanged()
            if (r7 == 0) goto L_0x0487
            goto L_0x0474
        L_0x0487:
            java.lang.Object r7 = r6.getTransition()
            androidx.fragment.app.SpecialEffectsController$Operation r8 = r6.getOperation()
            if (r0 == 0) goto L_0x0497
            if (r8 == r10) goto L_0x0495
            if (r8 != r3) goto L_0x0497
        L_0x0495:
            r12 = 1
            goto L_0x0498
        L_0x0497:
            r12 = 0
        L_0x0498:
            if (r7 != 0) goto L_0x049c
            if (r12 == 0) goto L_0x0474
        L_0x049c:
            android.view.ViewGroup r7 = r31.getContainer()
            boolean r7 = androidx.core.view.ViewCompat.isLaidOut(r7)
            if (r7 != 0) goto L_0x04d3
            r7 = 2
            boolean r7 = androidx.fragment.app.FragmentManager.isLoggingEnabled(r7)
            if (r7 == 0) goto L_0x04cf
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r12 = "SpecialEffectsController: Container "
            r7.append(r12)
            android.view.ViewGroup r12 = r31.getContainer()
            r7.append(r12)
            java.lang.String r12 = " has not been laid out. Completing operation "
            r7.append(r12)
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            java.lang.String r8 = "FragmentManager"
            android.util.Log.v(r8, r7)
        L_0x04cf:
            r6.completeSpecialEffect()
            goto L_0x0474
        L_0x04d3:
            androidx.fragment.app.SpecialEffectsController$Operation r7 = r6.getOperation()
            androidx.fragment.app.Fragment r7 = r7.getFragment()
            androidx.core.os.CancellationSignal r8 = r6.getSignal()
            androidx.fragment.app.DefaultSpecialEffectsController$9 r12 = new androidx.fragment.app.DefaultSpecialEffectsController$9
            r12.<init>(r6)
            r11.setListenerForTransitionEnd(r7, r1, r8, r12)
            goto L_0x0474
        L_0x04e8:
            android.view.ViewGroup r3 = r31.getContainer()
            boolean r3 = androidx.core.view.ViewCompat.isLaidOut(r3)
            if (r3 != 0) goto L_0x04f3
            return r9
        L_0x04f3:
            r3 = 4
            androidx.fragment.app.FragmentTransition.setViewVisibility(r2, r3)
            r3 = r29
            java.util.ArrayList r16 = r11.prepareSetNameOverridesReordered(r3)
            android.view.ViewGroup r4 = r31.getContainer()
            r11.beginDelayedTransition(r4, r1)
            android.view.ViewGroup r13 = r31.getContainer()
            r12 = r11
            r14 = r28
            r15 = r3
            r17 = r24
            r12.setNameOverridesReordered(r13, r14, r15, r16, r17)
            r1 = 0
            androidx.fragment.app.FragmentTransition.setViewVisibility(r2, r1)
            r1 = r28
            r11.swapSharedElementTargets(r0, r1, r3)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.DefaultSpecialEffectsController.startTransitions(java.util.List, java.util.List, boolean, androidx.fragment.app.SpecialEffectsController$Operation, androidx.fragment.app.SpecialEffectsController$Operation):java.util.Map");
    }

    /* access modifiers changed from: package-private */
    public void retainMatchingViews(ArrayMap<String, View> arrayMap, Collection<String> collection) {
        Iterator<Map.Entry<String, View>> it = arrayMap.entrySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(ViewCompat.getTransitionName((View) it.next().getValue()))) {
                it.remove();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void captureTransitioningViews(ArrayList<View> arrayList, View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (!ViewGroupCompat.isTransitionGroup(viewGroup)) {
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = viewGroup.getChildAt(i);
                    if (childAt.getVisibility() == 0) {
                        captureTransitioningViews(arrayList, childAt);
                    }
                }
            } else if (!arrayList.contains(view)) {
                arrayList.add(viewGroup);
            }
        } else if (!arrayList.contains(view)) {
            arrayList.add(view);
        }
    }

    /* access modifiers changed from: package-private */
    public void findNamedViews(Map<String, View> map, View view) {
        String transitionName = ViewCompat.getTransitionName(view);
        if (transitionName != null) {
            map.put(transitionName, view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt.getVisibility() == 0) {
                    findNamedViews(map, childAt);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void applyContainerChanges(SpecialEffectsController.Operation operation) {
        operation.getFinalState().applyState(operation.getFragment().mView);
    }

    private static class SpecialEffectsInfo {
        private final SpecialEffectsController.Operation mOperation;
        private final CancellationSignal mSignal;

        SpecialEffectsInfo(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal) {
            this.mOperation = operation;
            this.mSignal = cancellationSignal;
        }

        /* access modifiers changed from: package-private */
        public SpecialEffectsController.Operation getOperation() {
            return this.mOperation;
        }

        /* access modifiers changed from: package-private */
        public CancellationSignal getSignal() {
            return this.mSignal;
        }

        /* access modifiers changed from: package-private */
        public boolean isVisibilityUnchanged() {
            SpecialEffectsController.Operation.State from = SpecialEffectsController.Operation.State.from(this.mOperation.getFragment().mView);
            SpecialEffectsController.Operation.State finalState = this.mOperation.getFinalState();
            return from == finalState || !(from == SpecialEffectsController.Operation.State.VISIBLE || finalState == SpecialEffectsController.Operation.State.VISIBLE);
        }

        /* access modifiers changed from: package-private */
        public void completeSpecialEffect() {
            this.mOperation.completeSpecialEffect(this.mSignal);
        }
    }

    private static class AnimationInfo extends SpecialEffectsInfo {
        private FragmentAnim.AnimationOrAnimator mAnimation;
        private boolean mIsPop;
        private boolean mLoadedAnim = false;

        AnimationInfo(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal, boolean z) {
            super(operation, cancellationSignal);
            this.mIsPop = z;
        }

        /* access modifiers changed from: package-private */
        public FragmentAnim.AnimationOrAnimator getAnimation(Context context) {
            if (this.mLoadedAnim) {
                return this.mAnimation;
            }
            FragmentAnim.AnimationOrAnimator loadAnimation = FragmentAnim.loadAnimation(context, getOperation().getFragment(), getOperation().getFinalState() == SpecialEffectsController.Operation.State.VISIBLE, this.mIsPop);
            this.mAnimation = loadAnimation;
            this.mLoadedAnim = true;
            return loadAnimation;
        }
    }

    private static class TransitionInfo extends SpecialEffectsInfo {
        private final boolean mOverlapAllowed;
        private final Object mSharedElementTransition;
        private final Object mTransition;

        TransitionInfo(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal, boolean z, boolean z2) {
            super(operation, cancellationSignal);
            Object obj;
            Object obj2;
            boolean z3;
            if (operation.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                if (z) {
                    obj2 = operation.getFragment().getReenterTransition();
                } else {
                    obj2 = operation.getFragment().getEnterTransition();
                }
                this.mTransition = obj2;
                if (z) {
                    z3 = operation.getFragment().getAllowReturnTransitionOverlap();
                } else {
                    z3 = operation.getFragment().getAllowEnterTransitionOverlap();
                }
                this.mOverlapAllowed = z3;
            } else {
                if (z) {
                    obj = operation.getFragment().getReturnTransition();
                } else {
                    obj = operation.getFragment().getExitTransition();
                }
                this.mTransition = obj;
                this.mOverlapAllowed = true;
            }
            if (!z2) {
                this.mSharedElementTransition = null;
            } else if (z) {
                this.mSharedElementTransition = operation.getFragment().getSharedElementReturnTransition();
            } else {
                this.mSharedElementTransition = operation.getFragment().getSharedElementEnterTransition();
            }
        }

        /* access modifiers changed from: package-private */
        public Object getTransition() {
            return this.mTransition;
        }

        /* access modifiers changed from: package-private */
        public boolean isOverlapAllowed() {
            return this.mOverlapAllowed;
        }

        public boolean hasSharedElementTransition() {
            return this.mSharedElementTransition != null;
        }

        public Object getSharedElementTransition() {
            return this.mSharedElementTransition;
        }

        /* access modifiers changed from: package-private */
        public FragmentTransitionImpl getHandlingImpl() {
            FragmentTransitionImpl handlingImpl = getHandlingImpl(this.mTransition);
            FragmentTransitionImpl handlingImpl2 = getHandlingImpl(this.mSharedElementTransition);
            if (handlingImpl == null || handlingImpl2 == null || handlingImpl == handlingImpl2) {
                return handlingImpl != null ? handlingImpl : handlingImpl2;
            }
            throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + getOperation().getFragment() + " returned Transition " + this.mTransition + " which uses a different Transition  type than its shared element transition " + this.mSharedElementTransition);
        }

        private FragmentTransitionImpl getHandlingImpl(Object obj) {
            if (obj == null) {
                return null;
            }
            if (FragmentTransition.PLATFORM_IMPL != null && FragmentTransition.PLATFORM_IMPL.canHandle(obj)) {
                return FragmentTransition.PLATFORM_IMPL;
            }
            if (FragmentTransition.SUPPORT_IMPL != null && FragmentTransition.SUPPORT_IMPL.canHandle(obj)) {
                return FragmentTransition.SUPPORT_IMPL;
            }
            throw new IllegalArgumentException("Transition " + obj + " for fragment " + getOperation().getFragment() + " is not a valid framework Transition or AndroidX Transition");
        }
    }
}
