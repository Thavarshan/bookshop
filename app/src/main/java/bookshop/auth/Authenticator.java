package bookshop.auth;

import java.util.ArrayList;
import java.util.HashMap;
import bookshop.db.models.User;

public class Authenticator {

    /**
     * The list of allowable email and associative passwords.
     */
    HashMap<String, User> users = new HashMap<String, User>();

    /**
     * The currently authenticated user's email.
     *
     * @var String
     */
    String authenticatedEmail = null;

    /**
     * Get the email address of the user that is currently authenticated.
     *
     * @return String
     */
    public String authenticatedEmail() {
        return this.authenticatedEmail;
    }

    /**
     * Get the instance of the currently authenticated user.
     *
     * @return User
     */
    public User user() {
        return this.users.get(authenticatedEmail());
    }

    /**
     * Set the list of authenticatable users.
     *
     * @return void
     */
    public void setUsers(ArrayList<String[]> users) {
        for (String[] row : users) {
            if (row.length < 1) {
                continue;
            }

            User user = new User();
            user.setAttributes(row);

            setUser(user.email(), user);
        }
    }

    /**
     * Set list of allowable user credentials.
     *
     * @return void
     */
    public void setUser(String email, User user) {
        this.users.put(email, user);
    }

    /**
     * Get all users within the HashMap.
     *
     * @return HashMap
     */
    public HashMap<String, User> getUsers() {
        return this.users;
    }

    /**
     * Determine if the given email exists in the database.
     *
     * @param String email
     *
     * @return boolean
     */
    public boolean emailExists(String email) {
        return this.users.containsKey(email);
    }

    /**
     * Get the password value associated with the given email
     *
     * @param String email
     *
     * @return String
     *
     * @throws EmailNotFoundException
     */
    public String getPassword(String email) throws EmailNotFoundException {
        if (!emailExists(email)) {
            throw new EmailNotFoundException("Email was not found.");
        }

        return this.users.get(email).password();
    }

    /**
     * Check if the given value matches the hashed value.
     *
     * @param String hasValue
     * @param String value
     *
     * @return boolean
     */
    public boolean verifyPassword(String hashValue, String value) {
        try {
            return Hasher.check(value, hashValue);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Attempt to authenticate the user.
     *
     * @param String[] credentials
     *
     * @return boolean
     */
    public boolean attempt(String[] credentials) {
        String email = credentials[0];

        try {
            String password = getPassword(email);

            if (!verifyPassword(password, credentials[1])) {
                return false;
            }

            this.authenticatedEmail = email;

            return true;
        } catch (EmailNotFoundException e) {
            return false;
        }
    }
}
