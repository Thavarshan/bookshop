package bookshop;

import bookshop.auth.Authenticator;

/**
 * Main Application Class.
 */
public class Application {

    /**
     * Output a greeting message.
     *
     * @return String
     */
    public String getGreeting() {
        return "Hello World!";
    }

    public String authenticate() {
        Authenticator auth = new Authenticator();

        auth.setUser("tjthavarshan@gmail.com", "password");

        String[] credentials = { "tjthavarshan@gmail.com", "password" };

        if (auth.attempt(credentials)) {
            return "User was found";
        }

        return "User was not found";
    }

    /**
     * Execute the main application.
     *
     * @return void
     */
    public static void main(String[] args) {
        Application app = new Application();

        System.out.println(app.authenticate());
    }
}
