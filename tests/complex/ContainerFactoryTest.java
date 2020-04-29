package complex;
import common.DependencyException;
import mocks.B.ImplementationB1;
import mocks.C.ComplexFactoryC1;
import mocks.C.InterfaceC;
import mocks.D.ComplexFactoryD1;
import mocks.D.ImplementationD1;
import mocks.D.InterfaceD;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContainerFactoryTest {
    complex.Injector injector;

    @BeforeEach
    void setUp() {
        injector = new Container();
    }

    @Test
    void testFactory() throws DependencyException {
        injector.registerConstant(Integer.class, 2);
        injector.registerFactory(InterfaceD.class, new ComplexFactoryD1(), Integer.class);
        InterfaceD obj = injector.getObject(InterfaceD.class);
        assertTrue(obj instanceof ImplementationD1);
        ImplementationD1 d1 = (ImplementationD1) obj;
        assertNotSame(d1, injector.getObject(InterfaceD.class));
        assertEquals(d1, new ComplexFactoryD1().create(2));
    }
}
