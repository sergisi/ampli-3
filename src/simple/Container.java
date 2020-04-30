package simple;

import common.DependencyException;
import common.FunctionToObject;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Container implements Injector {
    private final Map<String, FunctionToObject<String>> map;

    public Container() {
        map = new HashMap<>();
    }

    @Override
    public void registerConstant(@NotNull String name, @NotNull Object value) throws DependencyException {
        containsKey(name);
        map.put(name, (dependencies) -> value);
    }

    @Override
    public void registerFactory(@NotNull String name,
                                @NotNull Factory creator, String... parameters) throws DependencyException {
        containsKey(name);
        map.put(name, (dependencies) -> creator.create(getObjects(parameters, dependencies)));
    }

    @Override
    public void registerSingleton(@ NotNull String name,
                                  @NotNull Factory creator, String... parameters) throws DependencyException {
        containsKey(name);
        map.put(name, new FunctionToObject<>() {
            Object object = null;
            @Override
            public Object create(Set<String> dependencies) throws DependencyException {
                if (object == null) {
                    object = creator.create(getObjects(parameters, dependencies));
                }
                return object;
            }
        });
    }

    @Override
    public Object getObject(@NotNull String name) throws DependencyException {
        return getObject(name, new HashSet<>());
    }

    private Object getObject(String name, Set<String> dependencies) throws DependencyException {
        if (!map.containsKey(name)) {
            throw new DependencyException("Key is not registered");
        }
        if (dependencies.contains(name)) {
            throw new DependencyException("Key has a dependency cycle:" + name + dependencies.toString());
        }
        HashSet<String> set = new HashSet<>(dependencies);
        set.add(name);
        return map.get(name).create(set);
    }

    private void containsKey(String name) throws DependencyException {
        if (map.containsKey(name)) throw new DependencyException("Key was already registered");
    }


    private Object[] getObjects(String[] parameters, Set<String> dependencies) throws DependencyException {
        Object[] result = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            result[i] = getObject(parameters[i], dependencies);
        }
        return result;
    }
}
