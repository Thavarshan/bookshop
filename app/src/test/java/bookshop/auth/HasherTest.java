package bookshop.auth;

import org.junit.Test;
import static org.junit.Assert.*;
import java.security.SecureRandom;

public class HasherTest {

    @Test
    public void hashAndValidateValue() {
        String value = "8F*Z&fyB6HP<$7NR";
        Hasher.generateSalt();

        try {
            String hashedValue = Hasher.make(value);

            assertTrue(Hasher.check(value, hashedValue));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void hashAndValidateValueWithCustomSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[24];
        random.nextBytes(salt);
        String value = "8F*Z&fyB6HP<$7NR";
        Hasher.setSaltValule(salt);

        try {
            String hashedValue = Hasher.make(value);

            assertTrue(Hasher.check(value, hashedValue));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
