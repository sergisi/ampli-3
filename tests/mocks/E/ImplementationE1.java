package mocks.E;

import mocks.F.InterfaceF;

public class ImplementationE1 implements InterfaceE {
    private final InterfaceF f;

    public ImplementationE1(InterfaceF f) {
        this.f = f;
    }

    public Object getF() {
        return f;
    }
}
