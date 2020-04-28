package mocks.D;

import java.util.Objects;

public class ImplementationD1 implements InterfaceD{
    public final int i;

    public ImplementationD1(int i) {
        this.i = i;
    }

    @Override
    public int getInt() {
        return i;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImplementationD1 that = (ImplementationD1) o;
        return i == that.i;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i);
    }
}
