package complex;

import common.DependencyException;
import common.FunctionToObject;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Container implements Injector {

    private final Map<Class<?>, FunctionToObject<Class<?>>> map;

    public Container() {
        map = new HashMap<>();
    }

    @Override
    public <E> void registerConstant(@NotNull Class<E> name, @NotNull E value) throws DependencyException {
        containsKey(map.containsKey(name));
        map.put(name, (dependencies) -> value);
    }


    @Override
    public <E> void registerFactory(@NotNull Class<E> name, @NotNull Factory<? extends E> creator,
                                    Class<?>... parameters) throws DependencyException {
        containsKey(map.containsKey(name));
        map.put(name, (dependencies) -> creator.create(getObjects(parameters, dependencies)));
    }

    @Override
    public <E> void registerSingleton(@NotNull Class<E> name, @NotNull Factory<? extends E> creator,
                                      Class<?>... parameters) throws DependencyException {
        containsKey(map.containsKey(name));
        map.put(name, new FunctionToObject<>() {
            Object object = null;
            @Override
            public Object create(Set<Class<?>> dependencies) throws DependencyException {
                if (object == null) {
                    object = creator.create(getObjects(parameters, dependencies));
                }
                return object;
            }
        });
    }

    @Override
    public <E> E getObject(@NotNull Class<E> name) throws DependencyException {
        return getObject(name, new HashSet<>());
    }

    @SuppressWarnings("unchecked")
    private <E> E getObject(Class<E> name, Set<Class<?>> parameters) throws DependencyException {
        if (!map.containsKey(name)) {
            throw new DependencyException("Key is not registered");
        }
        if (parameters.contains(name)) {
            throw new DependencyException("Key has a dependency cycle:" + name.toString() + parameters.toString());
        }
        HashSet<Class<?>> set = new HashSet<>(parameters);
        set.add(name);
        return (E) map.get(name).create(set);
    }

    private void containsKey(boolean b) throws DependencyException {
        if (b) {
            throw new DependencyException("Key was already registered");
        }
    }

    private Object[] getObjects(Class<?>[] parameters, Set<Class<?>> dependencies) throws DependencyException {
        Object[] result = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            result[i] = getObject(parameters[i], dependencies);
        }
        return result;
    }
}
