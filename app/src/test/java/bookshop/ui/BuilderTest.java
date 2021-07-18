package bookshop.ui;

import org.junit.Test;
import bookshop.Application;
import static org.junit.Assert.*;

public class BuilderTest {

    @Test
    public void builderCanSetApplicationInstance() {
        Application app = new Application();
        Builder builder = new Builder(app);

        assertSame(app, builder.getApplication());
        assertTrue(builder.getApplication() instanceof Application);
    }

    @Test
    public void buildGivesInstanceOfGUI() {
        Application app = new Application();
        Builder builder = new Builder(app);

        assertTrue(builder.build() instanceof GUI);
    }
}
