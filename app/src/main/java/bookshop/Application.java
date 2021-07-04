package bookshop;

import java.util.HashMap;
import java.util.ArrayList;
import bookshop.db.User;
import bookshop.files.CSVReader;

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

    /**
     * Execute the main application.
     *
     * @return void
     */
    public static void main(String[] args) {
        CSVReader reader = new CSVReader();
        ArrayList<String[]> data = new ArrayList<String[]>();
        HashMap<String, User> users = new HashMap<String, User>();

        data = reader.read("data/users.csv", data);

        for (String[] line : data) {
            User user = new User();

            user.email = line[0];
            user.password = line[1];
            user.role = line[2];

            users.put(line[0], user);
        }

        System.out.println(users);
        System.out.println(users.get("tjthavarshan@gmail.com").email);
    }
}
