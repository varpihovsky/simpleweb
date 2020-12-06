package webmanager.sender;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import test.implementations.HttpServletRequestImplemented;
import test.implementations.HttpServletResponseImplemented;
import webmanager.database.DatabaseController;
import webmanager.sender.sends.RegisterSend;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class RegisterSendTest {
    RegisterSend send = new RegisterSend();

    @Mock
    HttpServletRequestImplemented requestMock;

    @Mock
    HttpServletResponseImplemented responseMock;

    @Mock
    DatabaseController controllerMock;

    @Before
    public void before() {
        requestMock = mock(HttpServletRequestImplemented.class);
        responseMock = mock(HttpServletResponseImplemented.class);
        controllerMock = mock(DatabaseController.class);

        Mockito.when(controllerMock.setOperation(Mockito.any(), Mockito.any())).thenReturn(controllerMock);

        send.setController(controllerMock);
    }

    @Test
    public void rightDataInputTest() {
        Mockito.when(requestMock.getParameter("username")).thenReturn("Username");
        Mockito.when(requestMock.getParameter("password")).thenReturn("Password");
        Mockito.when(requestMock.getParameter("email")).thenReturn("someEmail@mail.com");
        Mockito.when(requestMock.getParameter("page")).thenReturn("register");

        assertEquals("register", send.executeSend(requestMock, responseMock));
        Mockito.verify(controllerMock, Mockito.times(1)).execute();
    }

    @Test
    public void wrongDataInputTest() {
        Mockito.when(requestMock.getParameter(Mockito.any())).thenReturn(null);
        Mockito.when(requestMock.getParameter("page")).thenReturn("register");

        assertEquals("register", send.executeSend(requestMock, responseMock));
        Mockito.verify(controllerMock, Mockito.times(0)).execute();
    }
}
