package mocks.F;

import mocks.E.InterfaceE;

public class ImplementationF1 implements InterfaceF{
    private final InterfaceE e;

    public ImplementationF1(InterfaceE e) {
        this.e = e;
    }

    public InterfaceE getE() {
        return e;
    }
}
