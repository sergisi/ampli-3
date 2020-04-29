package common;

/**
 * In haskell, this is () -> a.
 * But as it will be used in a hashmap,
 * and we don't really know how to put generics in
 * pairs of (k, v), we used Object
 */
public interface FunctionUnitToObject {
    Object create() throws DependencyException;
}
