package simple;

import common.DependencyException;
import complex.Factory;

import java.util.Objects;

public interface Injector {

    void registerConstant(String name, Object value) throws DependencyException;
    void registerFactory(String name, Factory creator,
                             String... parameters) throws DependencyException;
    void registerSingleton(String name, Factory creator,
                               String... parameters) throws DependencyException;
    Objects getObject(String name) throws DependencyException;
}
