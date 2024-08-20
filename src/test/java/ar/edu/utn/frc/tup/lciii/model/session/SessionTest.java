package ar.edu.utn.frc.tup.lciii.model.session;

import ar.edu.utn.frc.tup.lciii.model.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SessionTest {

    @BeforeEach
    public void setUp() {
        Session.setIsUserAuthenticated(false);
        Session.setUser(null);
    }

    @Test
    public void setIsUserAuthenticatedTest() {
        Session.setIsUserAuthenticated(true);
        assertTrue(Session.getIsUserAuthenticated());

        Session.setIsUserAuthenticated(false);
        assertFalse(Session.getIsUserAuthenticated());
    }

    @Test
    public void setUserWhenAuthenticatedTest() {
        User user = new User();
        user.setUserName("pipo");
        user.setPassword("123456");
        Session.setIsUserAuthenticated(true);
        Session.setUser(user);

        assertEquals(user.getUserName(), "pipo");
    }

    @Test
    public void getUserWhenNotAuthenticatedTest() {
        User user = new User();
        user.setUserName("pipo");
        user.setPassword("123456");

        Session.setIsUserAuthenticated(false);
        Session.setUser(user);

        assertNull(Session.getUser());
    }

    @Test
    public void getUserWhenAuthenticatedTest() {
        User user = new User(1, "testUser", "testPass");
        Session.setIsUserAuthenticated(true);
        Session.setUser(user);

        User user1 = Session.getUser();
        assertNotNull(user1);
        assertEquals(user, user1);
    }

    @Test
    public void testGetUserWhenNotAuthenticated() {
        User user = new User(1, "testUser", "testPass");
        Session.setIsUserAuthenticated(false);
        Session.setUser(user);

        User retrievedUser = Session.getUser();
        assertNull(retrievedUser);
    }
}