package mocks.D;

import common.DependencyException;

public class ComplexFactoryD1 implements complex.Factory<InterfaceD> {
    @Override
    public InterfaceD create(Object... parameters) throws DependencyException {
        if (parameters.length == 0) {
            throw new DependencyException("Needs at leat one parameter. More will be discarded");
        }
        return new ImplementationD1((int) parameters[0]);
    }
}
