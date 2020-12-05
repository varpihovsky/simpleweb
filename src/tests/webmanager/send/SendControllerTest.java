package webmanager.send;

import test.implementations.HttpServletRequestImplemented;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

public class SendControllerTest {
    private static SendController controller;

    @Mock
    private static HttpServletRequestImplemented request;

    @Before
    public void before() {
        controller = new SendController();

        request = mock(HttpServletRequestImplemented.class);
    }

    @Test
    public void defineSendTestSecond() {
        Mockito.when(request.getParameter("send")).thenReturn("redirect");

        assertSame(controller.defineSend(request).getClass(), RedirectSend.class);
    }

    @Test
    public void defineSendTestThird() {
        Mockito.when(request.getParameter("send")).thenReturn("login");

        assertSame(controller.defineSend(request).getClass(), LoginSend.class);
    }

    @Test
    public void defineSendTestFourth() {
        Mockito.when(request.getParameter("send")).thenReturn("logout");

        assertSame(controller.defineSend(request).getClass(), LogoutSend.class);
    }

    @Test
    public void defineSendTestFirst() {
        Mockito.when(request.getParameter("send")).thenReturn("register");

        assertSame(controller.defineSend(request).getClass(), RegisterSend.class);
    }
}
