package mocks.D;

import common.DependencyException;

public class SimpleFactoryD1 implements simple.Factory {
    @Override
    public Object create(Object... parameters) throws DependencyException {
        if (parameters.length == 0) {
            throw new DependencyException("Needs at leat one parameter. More will be discarded");
        }
        return new ImplementationD1((int) parameters[0]);
    }
}
