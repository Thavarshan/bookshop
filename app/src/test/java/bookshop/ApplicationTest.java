package bookshop;

import org.junit.Test;
import static org.junit.Assert.*;

public class ApplicationTest {

    @Test
    public void applicationCanBeInitializedAndStarted() {
        Application app = new Application();

        assertTrue(app.start());
    }

    @Test
    public void buildGivesInstanceOf() {
        Application app = new Application();

        assertTrue(app.build() instanceof Application);
    }
}
