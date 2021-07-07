package bookshop;

import java.util.ArrayList;
import java.util.List;

import bookshop.auth.Authenticator;
import bookshop.auth.Hasher;
import bookshop.db.Manager;
import bookshop.files.CSVWriter;
import bookshop.files.Writer;
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
        Hasher.generateSalt();

        this.db.loadData();

        this.auth.setUsers(this.db.getTable("users"));

        // gui.build();

        return this;
    }

    public void writeToFile() {
        this.db.insert(new String[] { "loyd@may.com", "Boom", "staff" });
    }

    /**
     * Authenticate the current user.
     *
     * @return void
     */
    public void authenticate() {
        String[] credentials = { "tjthavarshan@gmail.com", "password123" };

        if (this.auth.attempt(credentials)) {
            System.out.println("You are logged in.");
        } else {
            System.out.println("Login failed.");
        }
    }

    /**
     * Execute the main application.
     *
     * @return void
     */
    public static void main(String[] args) {
        Application app = new Application();

        app.start();

        app.authenticate();

        app.writeToFile();
    }
}
