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
        return this.email;
    }

    /**
     * Get the role of this user.
     *
     * @return String
     */
    public String role() {
        return this.role;
    }

    /**
     * Get the password of this user.
     *
     * @return String
     */
    public String password() {
        return this.password;
    }

    /**
     * Set the user attributes.
     *
     * @param String[] attributes
     *
     * @return void
     */
    public void setAttributes(String[] attributes) {
        this.attributes = attributes;

        this.email = attributes[0];
        this.password = attributes[1];
        this.role = attributes[2];
    }

    /**
     * Check if this user has the given email.
     *
     * @return boolean
     */
    public boolean hasEmail(String email) {
        return this.email.equals(email);
    }

    /**
     * Check if this user has permission.
     *
     * @param String role
     *
     * @return boolean
     */
    public boolean hasRole(String role) {
        return this.role.equals(role);
    }
}
