package WebManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class SessionManagerTest {
    private static SessionManager manager;

    @Mock
    private static HttpSession session;

    @Before
    public void before() {
        manager = new SessionManager();

        session = mock(HttpSession.class);
    }

    @Test
    public void checkUserSessionTestFirst() {
        Mockito.when(session.getAttribute("username")).thenReturn(null);
        Mockito.when(session.getAttribute("password")).thenReturn(null);

        assertTrue(!manager.checkUserSession(session));
    }

    @Test
    public void checkUserSessionTestSecond() {
        Mockito.when(session.getAttribute("username")).thenReturn("username");
        Mockito.when(session.getAttribute("password")).thenReturn("password");

        assertTrue(!manager.checkUserSession(session));
    }

    @Test
    public void checkUserSessionTestThird() {
        Mockito.when(session.getAttribute("username")).thenReturn("admin");
        Mockito.when(session.getAttribute("password")).thenReturn("1133224456");

        assertTrue(manager.checkUserSession(session));
    }
}
