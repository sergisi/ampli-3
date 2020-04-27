package complex;

import common.DependecyException;

public interface Injector {
    <E> void registerConstant(Class<E> name, E value) throws DependecyException;
    <E> void registerFactory(Class<E> name, Factory<? extends E> creator,
                             Class<?>... parameters) throws DependecyException;
    <E> void registerSingleton(Class<E> name, Factory<? extends E> creator,
                               Class<?>... parameters) throws DependecyException;
    <E> E getObject(Class<E> name) throws DependecyException;
}
