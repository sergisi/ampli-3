package mocks.A;

import mocks.B.InterfaceB;
import mocks.C.InterfaceC;

public class ImplementationA1 implements InterfaceA{
    final InterfaceB b;
    final InterfaceC c;

    public ImplementationA1(InterfaceB b, InterfaceC c) {
        this.b = b;
        this.c = c;
    }

    @Override
    public InterfaceB getInterfaceB() {
        return b;
    }

    @Override
    public InterfaceC getInterfaceC() {
        return c;
    }
}
