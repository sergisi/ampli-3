package simple;

import common.DependecyException;
import complex.Factory;

import java.util.Objects;

public interface Injector {

    void registerConstant(String name, Object value) throws DependecyException;
    void registerFactory(String name, Factory creator,
                             Object... parameters) throws DependecyException;
    void registerSingleton(String name, Factory creator,
                               Object... parameters) throws DependecyException;
    Objects getObject(String name) throws DependecyException
}
