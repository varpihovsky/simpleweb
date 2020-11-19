package WebManager.send;

import test.implementations.HttpServletRequestImplemented;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import test.implementations.HttpSessionImplemented;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class LoginSendTest {
    private static LoginSend send;

    @Mock
    private static HttpServletRequest request;

    @Mock
    private static HttpSession session;

    @Before
    public void before() {
        send = new LoginSend();

        request = mock(HttpServletRequestImplemented.class);
    }

    @Test
    public void executeSendTestFirst() {
        Mockito.when(request.getParameter("page")).thenReturn("login");
        Mockito.when(request.getParameter("username")).thenReturn(null);
        Mockito.when(request.getParameter("password")).thenReturn(null);

        assertEquals("login", send.executeSend(request));
    }

    @Test
    public void executeSendTestSecond() {
        Mockito.when(request.getParameter("page")).thenReturn("login");
        Mockito.when(request.getParameter("username")).thenReturn("asdasdasd");
        Mockito.when(request.getParameter("password")).thenReturn("999999999");

        assertEquals("login", send.executeSend(request));
    }

    @Test
    public void executeSendTestThird() {
        session = mock(HttpSessionImplemented.class);

        ((HttpSessionImplemented) session).names = new ArrayList<>();
        ((HttpSessionImplemented) session).objects = new ArrayList<>();

        Mockito.when(request.getParameter("page")).thenReturn("login");
        Mockito.when(request.getParameter("username")).thenReturn("admin");
        Mockito.when(request.getParameter("password")).thenReturn("1133224456");

        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute(Mockito.any())).thenCallRealMethod();
        Mockito.doCallRealMethod().when(session).setAttribute(Mockito.any(), Mockito.any());

        assertEquals("profile", send.executeSend(request));
        assertEquals(request.getParameter("username"), session.getAttribute("username"));
        assertEquals(request.getParameter("password"), session.getAttribute("password"));
    }
}
