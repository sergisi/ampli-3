package simple;

import common.DependencyException;
import mocks.D.ImplementationD1;
import mocks.D.InterfaceD;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class ContainerSimpleTest {
    simple.Injector injector;

    @BeforeEach
    void setUp(){
        injector = new Container();
    }

    @Test
    void testSimpleConstant() throws DependencyException {
        Integer value = Integer.valueOf(3);
        injector.registerConstant("BBAA", value);
        Object obj = injector.getObject("BBAA");
        assertTrue(obj instanceof Integer); //Revisar
        assertEquals(value, obj);
        assertSame(value, obj);
    }
}
