package bookshop.db;

import org.junit.Test;
import bookshop.db.models.User;
import static org.junit.Assert.*;
import org.junit.Before;

public class UserTest {

    /**
     * The user insatnce.
     *
     * @var User
     */
    User user = new User();

    /**
     * Fake user attributes.
     *
     * @var String[]
     */
    String[] attributes = { "tjthavarshan@gmail.com", "jMarkleKimberlYWein", "manager" }; // email, password, role

    @Before
    public void setUp() {
        user.setAttributes(this.attributes);
    }

    @Test
    public void userModelCanSetAttributes() {
        assertArrayEquals(attributes, user.getAttributes());
    }

    @Test
    public void userModelCanGetEmail() {
        assertEquals("tjthavarshan@gmail.com", user.email());
    }

    @Test
    public void userModelCanGetPassword() {
        assertEquals("jMarkleKimberlYWein", user.password());
    }

    @Test
    public void userModelCanGetRole() {
        assertEquals("manager", user.role());
    }

    @Test
    public void userModelCanDetermineRole() {
        assertTrue(user.hasRole("manager"));
        assertFalse(user.hasRole("staff"));
    }

    @Test
    public void userModelCanDetermineEmail() {
        assertTrue(user.hasEmail("tjthavarshan@gmail.com"));
        assertFalse(user.hasEmail("james.silverman@gmail.com"));
    }
}
