package mocks.C;

import common.DependencyException;

public class SimpleFactoryC1 implements simple.Factory{
    @Override
    public Object create(Object... parameters) throws DependencyException {
        String s;
        try{
            s = (String) parameters[0];
        }catch(ClassCastException | ArrayIndexOutOfBoundsException ex){
            throw new DependencyException(ex);
        }
        return new ImplementationC1(s);
    }
}
