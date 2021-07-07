package bookshop.db.models;

public class User extends Model {

    /**
     * The users's email address.
     *
     * @var String
     */
    protected String email;

    /**
     * The users's password used for authenticating.
     *
     * @var String
     */
    protected String password;

    /**
     * The users's role to manage permissions.
     *
     * @var String
     */
    protected String role;

    /**
     * Get the email address of this user.
     *
     * @return String
     */
    public String email() {
        return email;
    }

    /**
     * Get the role of this user.
     *
     * @return String
     */
    public String role() {
        return role;
    }

    /**
     * Get the password of this user.
     *
     * @return String
     */
    public String password() {
        return password;
    }

    /**
     * Set the user attributes.
     *
     * @param String[] attributes
     *
     * @return void
     */
    public void setAttributes(String[] attributes) {
        email = attributes[0];
        password = attributes[1];
        role = attributes[2];
    }
}
