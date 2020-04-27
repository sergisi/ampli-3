package simple;

import common.DependecyException;
import complex.Factory;

import java.util.Objects;

public class Container implements Injector {
    @Override
    public void registerConstant(String name, Object value) throws DependecyException {

    }

    @Override
    public void registerFactory(String name, Factory creator, Object... parameters) throws DependecyException {

    }

    @Override
    public void registerSingleton(String name, Factory creator, Object... parameters) throws DependecyException {

    }

    @Override
    public Objects getObject(String name) throws DependecyException {
        return null;
    }
}
