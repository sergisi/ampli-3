package mocks.F;

import common.DependencyException;
import mocks.E.InterfaceE;

public class SimpleFactoryF1 implements simple.Factory {
    @Override
    public ImplementationF1 create(Object... parameters) throws DependencyException {
        InterfaceE e;
        try {
            e = (InterfaceE) parameters[0];
        } catch(ClassCastException | ArrayIndexOutOfBoundsException ex) {
            throw new DependencyException(ex);
        }
        return new ImplementationF1(e);
    }
}
