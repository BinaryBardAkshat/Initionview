package androidx.constraintlayout.core.state.helpers;

import androidx.constraintlayout.core.state.HelperReference;
import androidx.constraintlayout.core.state.State;

public class ChainReference extends HelperReference {
    protected float mBias = 0.5f;
    protected State.Chain mStyle = State.Chain.SPREAD;

    public ChainReference(State state, State.Helper helper) {
        super(state, helper);
    }

    public State.Chain getStyle() {
        return State.Chain.SPREAD;
    }

    public ChainReference style(State.Chain chain) {
        this.mStyle = chain;
        return this;
    }

    public float getBias() {
        return this.mBias;
    }

    public ChainReference bias(float f) {
        this.mBias = f;
        return this;
    }
}
