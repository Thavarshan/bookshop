package bookshop;

import org.junit.Test;
import static org.junit.Assert.*;

public class ApplicationTest {

    @Test
    public void itCanBuildApplication() {
        Application app = new Application();

        assertTrue(app.build() instanceof Application);
    }
}
