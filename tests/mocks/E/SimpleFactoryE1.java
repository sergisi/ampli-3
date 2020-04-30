package mocks.E;

import common.DependencyException;
import mocks.F.InterfaceF;

public class SimpleFactoryE1 implements simple.Factory{
    @Override
    public ImplementationE1 create(Object... parameters) throws DependencyException {
        InterfaceF f;
        try {
            f = (InterfaceF) parameters[0];
        } catch(ClassCastException | ArrayIndexOutOfBoundsException ex) {
            throw new DependencyException(ex);
        }
        return new ImplementationE1(f);
    }
}
