package bookshop.auth;

public class EmailNotFoundException extends Exception {

    public static final long serialVersion = 42L;

    /**
     * Throw email not found exception.
     */
    public EmailNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
