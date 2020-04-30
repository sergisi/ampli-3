package common;

import java.util.Set;

/**
 * In haskell, this is () -> a.
 * But as it will be used in a hashmap,
 * and we don't really know how to put generics in
 * pairs of (k, v), we used Object
 *
 * Later we found out that we need to check for dependencies cycle
 * when getting the object, so we used a Set<Key> as a parameter
 * to the function (it checks well)
 */
public interface FunctionToObject<K> {
    Object create(Set<K> dependencies) throws DependencyException;
}
