package webmanager.sender;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import test.implementations.HttpServletRequestImplemented;
import test.implementations.HttpServletResponseImplemented;
import test.implementations.HttpSessionImplemented;
import webmanager.database.DatabaseController;
import webmanager.sender.sends.LoginSend;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class LoginSendTest {
    private static LoginSend send;

    @Mock
    private static HttpServletRequest request;

    @Mock
    private static HttpSession session;

    @Mock
    private static HttpServletResponseImplemented responseMock;

    @Mock
    private static DatabaseController dbcontrollerMock;

    @Before
    public void before() {
        send = new LoginSend();

        request = mock(HttpServletRequestImplemented.class);
        responseMock = mock(HttpServletResponseImplemented.class);
        dbcontrollerMock = mock(DatabaseController.class);
        Mockito.when(dbcontrollerMock.setOperation(Mockito.any(), Mockito.any())).thenReturn(dbcontrollerMock);

        send.set((HashMap<String, Object>) new HashMap<String, Object>().put("DatabaseController", dbcontrollerMock));
    }

    @Test
    public void executeSendTestFirst() {
        Mockito.when(request.getParameter("page")).thenReturn("login");
        Mockito.when(request.getParameter("username")).thenReturn(null);
        Mockito.when(request.getParameter("password")).thenReturn(null);


        assertEquals("login", send.executeSend(request, responseMock));
    }

    @Test
    public void executeSendTestSecond() {
        Mockito.when(request.getParameter("page")).thenReturn("login");
        Mockito.when(request.getParameter("username")).thenReturn("WrongUsername");
        Mockito.when(request.getParameter("password")).thenReturn("WrongPassword");

        Mockito.when(dbcontrollerMock.execute()).thenReturn(false);

        assertEquals("login", send.executeSend(request, responseMock));
    }

    @Test
    public void executeSendTestThird() {
        session = mock(HttpSessionImplemented.class);

        ((HttpSessionImplemented) session).names = new ArrayList<>();
        ((HttpSessionImplemented) session).objects = new ArrayList<>();

        Mockito.when(request.getParameter("page")).thenReturn("login");
        Mockito.when(request.getParameter("username")).thenReturn("RightUsername");
        Mockito.when(request.getParameter("password")).thenReturn("RightPassword");

        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute(Mockito.any())).thenCallRealMethod();
        Mockito.doCallRealMethod().when(session).setAttribute(Mockito.any(), Mockito.any());
        Mockito.when(dbcontrollerMock.execute()).thenReturn(true);

        assertEquals("profile", send.executeSend(request, responseMock));
        assertEquals(request.getParameter("username"), session.getAttribute("username"));
        assertEquals(request.getParameter("password"), session.getAttribute("password"));
    }
}
