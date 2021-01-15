package webmanager.sender;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import test.implementations.HttpServletRequestImplemented;
import test.implementations.HttpServletResponseImplemented;
import test.implementations.HttpSessionImplemented;
import webmanager.database.DatabaseController;
import webmanager.sender.sends.RedirectSend;

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

    @Mock
    private static DatabaseController secondController;

    @Before
    public void before() {
        send = new RedirectSend();
        names = new ArrayList<>();
        objects = new ArrayList<>();

        request = mock(HttpServletRequestImplemented.class);
        session = mock(HttpSessionImplemented.class);
        responseMock = mock(HttpServletResponseImplemented.class);
        controller = mock(DatabaseController.class);
        secondController = mock(DatabaseController.class);

        session.names = names;
        session.objects = objects;

        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute(Mockito.any())).thenCallRealMethod();
        Mockito.doCallRealMethod().when(session).setAttribute(Mockito.any(), Mockito.any());

        Mockito.when(session.getAttribute("username")).thenReturn("Username");
        Mockito.when(session.getAttribute("password")).thenReturn("Password");
        //Mockito.when(controller.setOperation(Mockito.matches(DatabaseController.IS_USER_EXISTS),
        //Mockito.any())).thenReturn(secondController);
        Mockito.when(secondController.execute()).thenReturn(false);
    }

    @Test
    public void executeSendTestFirst() {
        Mockito.when(request.getParameter("page")).thenReturn(null);


        assertEquals("main", send.executeSend(request, responseMock));
    }

    @Test
    public void executeSendTestSecond() {
        Mockito.when(request.getParameter("page")).thenReturn("profile");


        assertEquals("profile", send.executeSend(request, responseMock));
    }

    @Test
    public void rightLoginRedirectTest() {
        Mockito.when(request.getParameter("page")).thenReturn("login");

        Mockito.when(secondController.execute()).thenReturn(true);

        assertEquals("profile", send.executeSend(request, responseMock));
    }

    @Test
    public void wrongLoginRedirectTest() {
        Mockito.when(request.getParameter("page")).thenReturn("login");

        Mockito.when(secondController.execute()).thenReturn(false);

        assertEquals("login", send.executeSend(request, responseMock));
    }

    @Test
    public void rightProfileSettingsRedirectTest() {
        Mockito.when(request.getParameter("page")).thenReturn("profileSettings");

        Mockito.when(secondController.execute()).thenReturn(true);

        assertEquals("profileSettings", send.executeSend(request, responseMock));
    }

    @Test
    public void wrongProfileSettingsRedirectTest() {
        Mockito.when(request.getParameter("page")).thenReturn("profileSettings");

        assertEquals("main", send.executeSend(request, responseMock));
    }
}
