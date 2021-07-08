package bookshop;

import bookshop.auth.Authenticator;
import bookshop.db.Manager;
import bookshop.ui.GUI;

/**
 * The main application class.
 */
public class Application {

    /**
     * The application database manager instance.
     *
     * @var Manager
     */
    Manager db = new Manager();

    /**
     * The authenticator instance.
     *
     * @var Authenticator
     */
    Authenticator auth = new Authenticator();

    /**
     * The application GUI instance.
     *
     * @var GUI
     */
    GUI gui = new GUI();

    /**
     * Start the application.
     *
     * @return boolean
     */
    public boolean start() {
        try {
            build();
        } catch (Exception e) {
            terminate();

            return false;
        }

        return true;
    }

    /**
     * Construct the application functinoality.
     *
     * @return Application
     */
    public Application build() {
        this.db.loadData();

        this.auth.setUsers(this.db.getTable("users"));

        // gui.build();

        return this;
    }

    /**
     * Terminate the application.
     *
     * @return void
     */
    public void terminate() {
        this.db.persist();
    }

    /**
     * Execute the main application.
     *
     * @return void
     */
    public static void main(String[] args) {
        Application app = new Application();

        app.start();

        app.terminate();
    }
}
