package simple;

import common.DependencyException;
import mocks.D.ComplexFactoryD1;
import mocks.D.ImplementationD1;
import mocks.D.InterfaceD;
import mocks.D.SimpleFactoryD1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContainerFactoryTest {
    simple.Injector injector;
    @BeforeEach
    void setUp() throws DependencyException {
        injector = new Container();
        injector.registerConstant("I", 2);
        injector.registerFactory("A", new SimpleFactoryD1(), "I");
    }

    @Test
    void testSingletonSimple() throws DependencyException {
        Object obj = injector.getObject("A");
        assertTrue(obj instanceof InterfaceD);
        assertTrue(obj instanceof ImplementationD1);
        ImplementationD1 d1 = (ImplementationD1) obj;
        assertNotSame(d1, injector.getObject("A"));
        assertEquals(d1, new ComplexFactoryD1().create(2));
    }


    @Test
    void testNoRepeatedKeys() {
        Throwable exception = assertThrows(DependencyException.class,
                () -> injector.registerFactory("A", new SimpleFactoryD1(), "I"));
        assertEquals("Key was already registered", exception.getMessage());
    }
}
