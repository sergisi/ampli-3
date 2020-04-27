package complex;

import common.DependecyException;

public class Container implements Injector {
    @Override
    public <E> void registerConstant(Class<E> name, E value) throws DependecyException {

    }

    @Override
    public <E> void registerFactory(Class<E> name, Factory<? extends E> creator, Class<?>... parameters) throws DependecyException {

    }

    @Override
    public <E> void registerSingleton(Class<E> name, Factory<? extends E> creator, Class<?>... parameters) throws DependecyException {

    }

    @Override
    public <E> E getObject(Class<E> name) throws DependecyException {
        return null;
    }
}
