package WebManager.send;

import WebManager.SessionManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import test.implementations.HttpServletRequestImplemented;
import test.implementations.HttpSessionImplemented;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class RedirectSendTest {
    private static RedirectSend send;

    @Mock
    private static HttpServletRequest request;

    @Mock
    private static HttpSessionImplemented session;

    @Before
    public void before() {
        send = new RedirectSend();

        request = mock(HttpServletRequestImplemented.class);
        session = mock(HttpSessionImplemented.class);
    }

    @Test
    public void executeSendTestFirst() {
        Mockito.when(request.getParameter("page")).thenReturn(null);

        assertEquals("main", send.executeSend(request));
    }

    @Test
    public void executeSendTestSecond() {
        Mockito.when(request.getParameter("page")).thenReturn("profile");

        assertEquals("profile", send.executeSend(request));
    }

    @Test
    public void executeSendTestThird() {
        Mockito.when(request.getParameter("page")).thenReturn("login");

        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("username")).thenReturn("admin");
        Mockito.when(session.getAttribute("password")).thenReturn("1133224456");

        assertEquals("profile", send.executeSend(request));
    }
}
