package complex;

import common.DependencyException;
import common.FunctionUnitToObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Container implements Injector {

    private Map<Class<?>, FunctionUnitToObject> map;

    public Container() {
        map = new HashMap<>();
    }

    @Override
    public <E> void registerConstant(Class<E> name, E value) throws DependencyException {
        containsKey(map.containsKey(name));
        map.put(name, () -> value);
    }


    @Override
    public <E> void registerFactory(Class<E> name, Factory<? extends E> creator, Class<?>... parameters) throws DependencyException {
        containsKey(map.containsKey(name));
        map.put(name, () -> creator.create(getObjects(parameters)));
    }

    @Override
    public <E> void registerSingleton(Class<E> name, Factory<? extends E> creator, Class<?>... parameters) throws DependencyException {
        containsKey(map.containsKey(name));
        map.put(name, new FunctionUnitToObject() {
            Object object = null;
            @Override
            public Object create() throws DependencyException {
                if (object == null) {
                    object = creator.create(getObjects(parameters));
                }
                return object;
            }
        });
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public <E> E getObject(Class<E> name) throws DependencyException {
        if (!map.containsKey(name)) {
            throw new DependencyException("Key is not registered");
        }
        return (E) map.get(name).create();
    }

    private void containsKey(boolean b) throws DependencyException {
        if (b) {
            throw new DependencyException("Key was already registered");
        }
    }

    private Object[] getObjects(Class<?>[] parameters) throws DependencyException {
        Object[] result = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            result[i] = getObject(parameters[i]);
        }
        return result;
    }
}
