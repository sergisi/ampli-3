package mocks.A;

import common.DependencyException;
import mocks.B.InterfaceB;
import mocks.C.InterfaceC;

public class ComplexFactoryA1 implements complex.Factory<ImplementationA1>{
    @Override
    public ImplementationA1 create(Object... parameters) throws DependencyException {
        InterfaceB b;
        InterfaceC c;
        try{
            b = (InterfaceB) parameters[0];
            c = (InterfaceC) parameters[1];
        }catch (ClassCastException | ArrayIndexOutOfBoundsException ex){
            throw new DependencyException(ex);
        }
        return new ImplementationA1(b,c);
    }
}
