package complex;

import common.DependencyException;
import mocks.D.ImplementationD1;
import mocks.D.InterfaceD;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContainerConstantTest {
    complex.Injector injector;

    @BeforeEach
    void setUp(){
        injector = new Container();
    }

    @Test
    void testConstant() throws DependencyException {
        Integer value = 3;
        injector.registerConstant(Integer.class, value);
        InterfaceD obj = injector.getObject(InterfaceD.class);
        assertTrue(obj instanceof ImplementationD1);
        ImplementationD1 d1 = (ImplementationD1) obj;

        assertSame(obj , injector.getObject(InterfaceD.class));
        assertEquals(value ,d1.getInt());
    }

}
