package simple;

import common.DependencyException;
import mocks.D.ComplexFactoryD1;
import mocks.D.ImplementationD1;
import mocks.D.InterfaceD;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContainerSingletonTest {
    simple.Injector injector;
    @BeforeEach
    void setUp() {
        injector = new Container();
    }

    @Test
    void testSingletonSimple() throws DependencyException {
        injector.registerConstant("I", "abbba");
        injector.registerSingleton("A", new ComplexFactoryD1(), "I");
        Object obj = injector.getObject("A");
        assertTrue(obj instanceof InterfaceD);
        assertTrue(obj instanceof ImplementationD1);
        ImplementationD1 d1 = (ImplementationD1) obj;
        assertSame(d1, injector.getObject("A"));
        assertEquals(d1, new ComplexFactoryD1().create("abbba"));
    }
}
