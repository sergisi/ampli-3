package mocks.B;

import common.DependencyException;
import mocks.D.InterfaceD;

public class ComplexFactoryB1 implements complex.Factory<ImplementationB1> {
    @Override
    public ImplementationB1 create(Object... parameters) throws DependencyException {
        InterfaceD d;
        try {
            d = (InterfaceD) parameters[0];
        } catch (ClassCastException | ArrayIndexOutOfBoundsException ex) {
            throw new DependencyException(ex);
        }
        return new ImplementationB1(d);
    }
}
