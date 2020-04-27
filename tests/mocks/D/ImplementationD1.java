package mocks.D;

public class ImplementationD1 implements InterfaceD{
    public final int i;

    public ImplementationD1(int i) {
        this.i = i;
    }

    @Override
    public int getInt() {
        return i;
    }
}
