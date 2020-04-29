package mocks.D;

import common.DependencyException;

public class ComplexFactoryD1 implements complex.Factory<ImplementationD1> {
    @Override
    public ImplementationD1 create(Object... parameters) throws DependencyException {
        int i;
        try {
            i = (int) parameters[0];
        } catch(ClassCastException | ArrayIndexOutOfBoundsException ex) {
            throw new DependencyException(ex);
        }
        return new ImplementationD1(i);
    }
}
