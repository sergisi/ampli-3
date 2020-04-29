package mocks.D;

import common.DependencyException;

public class SimpleFactoryD1 implements simple.Factory {
    @Override
    public InterfaceD create(Object... parameters) throws DependencyException {
        int i;
        try {
            i = (int) parameters[0];
        } catch(ClassCastException | ArrayIndexOutOfBoundsException ex) {
            throw new DependencyException(ex);
        }
        return new ImplementationD1(i);
    }
}
