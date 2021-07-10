package bookshop.auth;

import org.junit.Test;
import bookshop.db.Table;
import bookshop.db.models.User;

import static org.junit.Assert.*;

public class AuthenticatorTest {

    /**
     * Fake user details for testing.
     *
     * @var String[]
     */
    String[] data = { "tjthavarshan@gmail.com",
            "1000:ce0600b75416fd09979a3485ed08305ebef7b61254d18043:f3862c97b59deef120e7fc8df5fb833557b46c563bf85355",
            "admin" };

    @Test
    public void canSetAuthenticatableUsersFromUsersTable() {
        Table users = new Table();
        users.setName("users");
        users.addData(this.data);

        Authenticator auth = new Authenticator();
        auth.setUsers(users.getData());

        assertTrue(auth.getUsers().containsKey("tjthavarshan@gmail.com"));
    }

    @Test
    public void canSetUsersIndividually() {
        User user = new User();
        user.setAttributes(this.data);

        Authenticator auth = new Authenticator();
        auth.setUser(user.email(), user);

        assertTrue(auth.getUsers().containsKey("tjthavarshan@gmail.com"));
    }

    @Test
    public void canDetermineIfGivenEmailExists() {
        User user = new User();
        user.setAttributes(this.data);

        Authenticator auth = new Authenticator();
        auth.setUser(user.email(), user);

        assertTrue(auth.emailExists("tjthavarshan@gmail.com"));
    }

    @Test
    public void canGetPasswordOfGivenUserEmail() {
        User user = new User();
        user.setAttributes(this.data);

        Authenticator auth = new Authenticator();
        auth.setUser(user.email(), user);

        try {
            assertEquals(user.password(), auth.getPassword(user.email()));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void exceptionThrownWhenEmailNotFound() {
        Authenticator auth = new Authenticator();

        try {
            auth.getPassword("invalid.email@domain.com");

            fail("Exception not thrown");
        } catch (EmailNotFoundException e) {
            assertEquals("Email was not found.", e.getMessage());
        }
    }

    @Test
    public void verifyHashedPasswordWithOriginalValue() {
        Hasher.generateSalt();
        Authenticator auth = new Authenticator();
        String originalValue = "javaMonster123";

        try {
            String hashedValue = Hasher.make(originalValue);

            assertTrue(auth.verifyPassword(hashedValue, originalValue));
            assertFalse(auth.verifyPassword(originalValue, originalValue));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void canAttemptToAuthenticateUser() {
        User user = new User();
        user.setAttributes(this.data);

        Authenticator auth = new Authenticator();
        auth.setUser(user.email(), user);

        String[] credentials = { "tjthavarshan@gmail.com", "password123" };

        assertTrue(auth.attempt(credentials));
    }
}
