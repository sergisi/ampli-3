package mocks.D;

import common.DependencyException;

public class ComplexFactoryD1 implements complex.Factory<InterfaceD> {
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
