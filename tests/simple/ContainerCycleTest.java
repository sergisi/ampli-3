package simple;

import common.DependencyException;
import mocks.E.SimpleFactoryE1;
import mocks.F.SimpleFactoryF1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContainerCycleTest {

    simple.Injector injector;

    @BeforeEach
    void setUp() throws DependencyException {
        injector = new Container();
        injector.registerSingleton("F", new SimpleFactoryF1(), "E");
        injector.registerFactory("E", new SimpleFactoryE1(), "F");
    }

    @Test
    void testCycle() {
        Throwable throwable = assertThrows(DependencyException.class,
                () -> injector.getObject("E"));
        assertTrue( throwable.getMessage().contains("Key has a dependency cycle:"));
    }
}
