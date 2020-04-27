package mocks.C;

public class ImplementationC1 implements InterfaceC{
    public final String s;

    public ImplementationC1(String s) {
        this.s = s;
    }

    @Override
    public String getString() {
        return s;
    }
}
