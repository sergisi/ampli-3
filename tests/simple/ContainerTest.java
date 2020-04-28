package simple;

import common.DependencyException;
import simple.Container;
import mocks.A.ComplexFactoryA1;
import mocks.A.ImplementationA1;
import mocks.A.InterfaceA;
import mocks.B.ComplexFactoryB1;
import mocks.B.ImplementationB1;
import mocks.B.InterfaceB;
import mocks.C.ComplexFactoryC1;
import mocks.C.ImplementationC1;
import mocks.C.InterfaceC;
import mocks.D.ImplementationD1;
import mocks.D.InterfaceD;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ContainerTest {
    simple.Injector injector;

    @org.junit.jupiter.api.BeforeEach
    void setUp() throws DependencyException {
        injector = new Container();
        injector.registerConstant("D", new ImplementationD1(42));
        injector.registerFactory("C", new ComplexFactoryC1(), "S");
        injector.registerConstant("S", "ABBA");  // TODO: This also checks lazy evaluation. Is it Correct thought?
        injector.registerSingleton("B", new ComplexFactoryB1(), "D");
        injector.registerFactory("A", new ComplexFactoryA1(), "B", "C");
    }


    @Test
    private void testInterfaceA() throws DependencyException {
        assertNotSame(injector.getObject("A"), injector.getObject("A"));
        assertTrue(injector.getObject("A") instanceof InterfaceA);
        Object b, c;
        b = injector.getObject("B");
        c = injector.getObject("C");
        assertTrue(b instanceof InterfaceB);
        assertTrue(c instanceof InterfaceC); // Check types
        InterfaceA expected = new ImplementationA1(
                (InterfaceB) b,
                (InterfaceC) c
        );
        assertEquals(expected, injector.getObject("A"));
    }

    @Test
    @SuppressWarnings(value = "uchecked")
    private void testInterfaceB() throws DependencyException {
        assertSame(injector.getObject("B"), injector.getObject("B"));
        assertTrue(injector.getObject("B") instanceof InterfaceB);
        assertEquals(new ImplementationB1(new ImplementationD1(42)), injector.getObject("B"));
    }

    @Test
    private void testInterfaceC() throws DependencyException {
        assertEquals(injector.getObject("C"), new ImplementationC1("ABBA"));
    }

    @Test
    private void testInterfaceD() throws DependencyException {
        assertTrue(injector.getObject("D") instanceof InterfaceD);
        assertEquals(42, ((InterfaceD) injector.getObject("D")).getInt());
        assertSame(injector.getObject("D"), injector.getObject("D"));
    }

    @Test
    void testAlreadyRegistered() {
        Throwable throwable = assertThrows(DependencyException.class, () -> {
            injector.registerSingleton("C", new ComplexFactoryC1(), "S");
        });
        assertEquals("Key was already registered", throwable.getMessage());
    }

    @Test
    void testGetObjectOfANotRegisteredClass() {
        Throwable throwable = assertThrows(DependencyException.class, () -> {
            injector.getObject("I");
        });
        assertEquals("Key is not registered", throwable.getMessage());
    }
}