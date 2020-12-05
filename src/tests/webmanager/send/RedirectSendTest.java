package webmanager.send;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import test.implementations.HttpServletRequestImplemented;
import test.implementations.HttpServletResponseImplemented;
import test.implementations.HttpSessionImplemented;
import webmanager.database.DatabaseConnector;
import webmanager.database.DatabaseController;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class RedirectSendTest {
    private static RedirectSend send;

    private ArrayList<String> names;
    private ArrayList<Object> objects;

    @Mock
    private static HttpServletRequest request;

    @Mock
    private static HttpSessionImplemented session;

    @Mock
    private static HttpServletResponseImplemented responseMock;

    @Mock
    private static DatabaseController controller;

    @Before
    public void before() {
        send = new RedirectSend();
        names = new ArrayList<>();
        objects = new ArrayList<>();

        request = mock(HttpServletRequestImplemented.class);
        session = mock(HttpSessionImplemented.class);
        responseMock = mock(HttpServletResponseImplemented.class);
        controller = mock(DatabaseController.class);

        session.names = names;
        session.objects = objects;

        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute(Mockito.any())).thenCallRealMethod();
        Mockito.doCallRealMethod().when(session).setAttribute(Mockito.any(), Mockito.any());


    }

    @Test
    public void executeSendTestFirst() {
        Mockito.when(request.getParameter("page")).thenReturn(null);


        assertEquals("src/main", send.executeSend(request, responseMock, controller));
    }

    @Test
    public void executeSendTestSecond() {
        Mockito.when(request.getParameter("page")).thenReturn("profile");

        assertEquals("profile", send.executeSend(request, responseMock, controller));
    }

    @Test
    public void executeSendTestThird() {
        Mockito.when(request.getParameter("page")).thenReturn("login");

        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("username")).thenReturn("admin");
        Mockito.when(session.getAttribute("password")).thenReturn("1133224456");

        assertEquals("profile", send.executeSend(request, responseMock, controller));
    }
}
