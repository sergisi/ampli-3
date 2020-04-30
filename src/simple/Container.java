package simple;

import common.DependencyException;
import common.FunctionUnitToObject;

import java.util.HashMap;
import java.util.Map;

public class Container implements Injector {
    Map<String, FunctionUnitToObject> map;

    public Container() {
        map = new HashMap<>();
    }

    @Override
    public void registerConstant(String name, Object value) throws DependencyException {
        containsKey(name);
        map.put(name, () -> value);
    }

    @Override
    public void registerFactory(String name, Factory creator, String... parameters) throws DependencyException {
        containsKey(name);
        map.put(name, () -> creator.create(getObjects(parameters)));
    }

    @Override
    public void registerSingleton(String name, Factory creator, String... parameters) throws DependencyException {
        containsKey(name);
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
    public Object getObject(String name) throws DependencyException {
        if (!map.containsKey(name)) {
            throw new DependencyException("Key is not registered");
        }
        return map.get(name).create();

    }

    void containsKey(String name) throws DependencyException {
        if (map.containsKey(name)) throw new DependencyException("Key was already registered");
    }


    private Object[] getObjects(String[] parameters) throws DependencyException {
        Object[] result = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            result[i] = getObject(parameters[i]);
        }
        return result;
    }
}
