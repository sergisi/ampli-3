package mocks.B;

import mocks.D.InterfaceD;

public class ImplementationB1 implements InterfaceB {
    final InterfaceD d;
    public ImplementationB1(InterfaceD d) {
        this.d = d;
    }

    @Override
    public InterfaceD getInterfaceD() {
        return d;
    }
}
