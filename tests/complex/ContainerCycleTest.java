package complex;

import common.DependencyException;
import mocks.E.ComplexFactoryE1;
import mocks.E.InterfaceE;
import mocks.F.ComplexFactory;
import mocks.F.InterfaceF;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContainerCycleTest {
    complex.Injector injector;

    @BeforeEach
    void setUp() throws DependencyException {
        injector = new Container();
        injector.registerSingleton(InterfaceF.class, new ComplexFactory(), InterfaceE.class);
        injector.registerFactory(InterfaceE.class, new ComplexFactoryE1(), InterfaceF.class);
    }

    @Test
    void testCycle() {
        Throwable throwable = assertThrows(DependencyException.class,
                () -> injector.getObject(InterfaceE.class));
        assertTrue( throwable.getMessage().contains("Key has a dependency cycle:"));
    }
}
