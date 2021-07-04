package bookshop.auth;

import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import com.google.common.collect.Maps;

public class AuthenticatorTest {

    @Test
    public void setAndGetUserDetails() {
        Authenticator auth = new Authenticator();

        auth.setUser("james.silverman@monster.com", "monsterRules123");
        auth.setUser("geoff.goblush@atami.com", "superDinoMenace");

        HashMap<String, String> testUsers = new HashMap<String, String>();
        testUsers.put("james.silverman@monster.com", "monsterRules123");
        testUsers.put("geoff.goblush@atami.com", "superDinoMenace");

        assertTrue(Maps.difference(auth.getUsers(), testUsers).areEqual());
        assertTrue(auth.getUsers().containsKey("james.silverman@monster.com"));
        assertTrue(auth.getUsers().containsKey("geoff.goblush@atami.com"));
    }

    @Test
    public void checkIfEmailExists() {
        Authenticator auth = new Authenticator();

        auth.setUser("james.silverman@monster.com", "monsterRules123");

        assertTrue(auth.emailExists("james.silverman@monster.com"));
        assertFalse(auth.emailExists("james.mongor@silverback.com"));
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

    @Ignore
    @Test
    public void attemptToAuthenticateUser() {
        Hasher.generateSalt();
        Authenticator auth = new Authenticator();
        String originalValue = "javaMonster123";

        auth.setUser("james.silverman@monster.com", originalValue);

        try {
            String hashedValue = Hasher.make(originalValue);

            String[] validCredentials = { "james.silverman@monster.com", hashedValue };
            String[] invalidCredentials = { "james.gormon@gumpert.com", "invalidFurrier" };

            assertTrue(auth.attempt(validCredentials));
            assertFalse(auth.attempt(invalidCredentials));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
