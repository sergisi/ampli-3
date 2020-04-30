package complex;

import common.DependencyException;
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

/**
 * Class to test all the expected behavior of a Container.
 */
class ContainerTest {
    complex.Injector injector;

    @org.junit.jupiter.api.BeforeEach
    void setUp() throws DependencyException {
        injector = new Container();
        injector.registerConstant(InterfaceD.class, new ImplementationD1(42));
        injector.registerFactory(InterfaceC.class, new ComplexFactoryC1(), String.class);
        injector.registerConstant(String.class, "ABBA");
        // Note: This also checks lazy evaluation, as we wanted it.
        injector.registerSingleton(InterfaceB.class, new ComplexFactoryB1(), InterfaceD.class);
        injector.registerFactory(InterfaceA.class, new ComplexFactoryA1(), InterfaceB.class, InterfaceC.class);
    }


    @Test
    private void testInterfaceA() throws DependencyException {
        assertNotSame(injector.getObject(InterfaceA.class), injector.getObject(InterfaceA.class));
        InterfaceA expected = new ImplementationA1(
                injector.getObject(InterfaceB.class),
                injector.getObject(InterfaceC.class)
        );
        assertEquals(expected, injector.getObject(InterfaceA.class));
    }

    @Test
    private void testInterfaceB() throws DependencyException {
        assertSame(injector.getObject(InterfaceB.class), injector.getObject(InterfaceB.class));
        assertEquals(new ImplementationB1(new ImplementationD1(42)), injector.getObject(InterfaceB.class));
    }

    @Test
    private void testInterfaceC() throws DependencyException {
        assertEquals(injector.getObject(InterfaceC.class), new ImplementationC1("ABBA"));
    }

    @Test
    private void testInterfaceD() throws DependencyException {
        assertEquals(42, injector.getObject(InterfaceD.class).getInt());
        assertSame(injector.getObject(InterfaceD.class), injector.getObject(InterfaceD.class));
    }

    @Test
    void testAlreadyRegistered() {
        Throwable throwable = assertThrows(DependencyException.class,
                () -> injector.registerSingleton(InterfaceC.class, new ComplexFactoryC1(), String.class));
        assertEquals("Key was already registered", throwable.getMessage());
    }

    @Test
    void testGetObjectOfANotRegisteredClass() {
        Throwable throwable = assertThrows(DependencyException.class,
                () -> injector.getObject(Integer.class));
        assertEquals("Key is not registered", throwable.getMessage());
    }
}