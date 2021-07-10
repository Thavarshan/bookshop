package bookshop;

import bookshop.auth.Authenticator;
import bookshop.auth.Hasher;
import bookshop.db.Manager;
import bookshop.ui.Builder;

/**
 * The main application class.
 */
public class Application {

    /**
     * The application database manager instance.
     *
     * @var Manager
     */
    public Manager db = new Manager();

    /**
     * The authenticator instance.
     *
     * @var Authenticator
     */
    public Authenticator auth = new Authenticator();

    /**
     * The application database manager instance.
     *
     * @var Builder
     */
    public Builder ui = new Builder();

    /**
     * Start the application.
     *
     * @return boolean
     */
    public boolean start() {
        Hasher.generateSalt();

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

        this.auth.setUsers(this.db.getTable("users").getData());

        ui.setApplication(this);
        ui.build();

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
    }
}
