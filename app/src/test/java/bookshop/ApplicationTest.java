package bookshop;

import org.junit.Test;
import static org.junit.Assert.*;

public class ApplicationTest {
    @Test
    public void appHasAGreeting() {
        Application classUnderTest = new Application();

        assertNotNull("User was found", classUnderTest.getGreeting());
    }
}
