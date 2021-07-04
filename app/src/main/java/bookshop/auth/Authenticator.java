package bookshop.auth;

import java.util.HashMap;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Authenticator {

    /**
     * The list of allowable email and associative passwords.
     */
    HashMap<String, String> users = new HashMap<String, String>();

    /**
     * Set list of allowable user credentials.
     *
     * @return void
     */
    public void setUser(String email, String password) {
        users.put(email, password);
    }

    /**
     * Get all users within the HashMap.
     *
     * @return HashMap
     */
    public HashMap getUsers() {
        return users;
    }

    /**
     * Determine if the given email exists in the database.
     *
     * @param String email
     *
     * @return boolean
     */
    public boolean emailExists(String email) {
        return users.containsKey(email);
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

        return users.get(email);
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

            return verifyPassword(password, credentials[1]);
        } catch (EmailNotFoundException e) {
            return false;
        }
    }
}
