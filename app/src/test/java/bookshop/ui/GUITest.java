package bookshop.ui;

import org.junit.Test;
import bookshop.Application;
import bookshop.db.Manager;

import static org.junit.Assert.*;

public class GUITest {

    @Test
    public void guiCanSetApplicationInstance() {
        Application app = new Application();
        GUI ui = new GUI();
        ui.setApplication(app);

        assertSame(app, ui.getApplication());
        assertTrue(ui.getApplication() instanceof Application);
    }

    @Test
    public void guiCanSetDatabaseInstance() {
        Application app = new Application();
        GUI ui = new GUI();
        ui.setApplication(app);

        assertSame(app.db, ui.db());
        assertTrue(ui.db() instanceof Manager);
    }
}
