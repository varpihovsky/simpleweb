package webmanager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import webmanager.database.DatabaseController;

import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.mock;

public class SessionManagerTest {
    private static SessionManager manager;

    @Mock
    private static HttpSession session;

    @Mock
    private static DatabaseController controllerMock;

    @Before
    public void before() {
        manager = new SessionManager();

        session = mock(HttpSession.class);
        controllerMock = mock(DatabaseController.class);
    }

    @Test
    public void checkUserSessionTestFirst() {
        Mockito.when(session.getAttribute("username")).thenReturn(null);
        Mockito.when(session.getAttribute("password")).thenReturn(null);

       // assertTrue(!manager.checkUserSession(session, controllerMock));
    }

    @Test
    public void checkUserSessionTestSecond() {
        Mockito.when(session.getAttribute("username")).thenReturn("username");
        Mockito.when(session.getAttribute("password")).thenReturn("password");

        // assertTrue(!manager.checkUserSession(session, controllerMock));
    }

    @Test
    public void checkUserSessionTestThird() {
        Mockito.when(session.getAttribute("username")).thenReturn("admin");
        Mockito.when(session.getAttribute("password")).thenReturn("1133224456");

        // assertTrue(manager.checkUserSession(session, controllerMock));
    }
}
