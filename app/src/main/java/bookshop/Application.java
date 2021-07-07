package bookshop;

import bookshop.auth.Authenticator;
import bookshop.db.Manager;

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
     * Start the application.
     *
     * @return boolean
     */
    public boolean start() {
        try {
            build();
        } catch (Exception e) {
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

        String[] credentials = { "tjthavarshan@gmail.com", "password123" };

        if (this.auth.attempt(credentials)) {
            System.out.println("You are logged in.");
        } else {
            System.out.println("Login failed.");
        }

        return this;
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
