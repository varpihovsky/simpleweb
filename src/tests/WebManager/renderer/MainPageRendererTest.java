package WebManager.renderer;


import test.implementations.HttpServletRequestImplemented;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.servlet.http.*;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;

public class MainPageRendererTest {
    private static MainPageRenderer renderer;

    @Mock
    private static HttpServletRequestImplemented requestMock;

    @Mock
    private static HttpSession sessionMock;

    @Before
    public void before() {
        renderer = new MainPageRenderer();
        requestMock = mock(HttpServletRequestImplemented.class);
        sessionMock = mock(HttpSession.class);

        requestMock.names = new ArrayList<>();
        requestMock.objects = new ArrayList<>();

        Mockito.when(requestMock.getSession()).thenReturn(sessionMock);
        Mockito.when(requestMock.getAttribute(Mockito.any())).thenCallRealMethod();
        Mockito.doCallRealMethod().when(requestMock).setAttribute(Mockito.any(), Mockito.any());
    }

    @Test
    public void renderTestFirst() {
        Mockito.when(sessionMock.getAttribute(eq("username"))).thenReturn("");
        Mockito.when(sessionMock.getAttribute(eq("password"))).thenReturn("");

        renderer.render(requestMock);

        assertEquals("Not registered? <a href=\"/controller?page=register&send=redirect\">Register</a><br/>\n" +
                        "    Registered? <a href=\"/controller?page=login&send=redirect\">Login</a>",
                requestMock.getAttribute("render"));
    }

    @Test
    public void renderTestSecond() {
        Mockito.when(sessionMock.getAttribute(eq("username"))).thenReturn("username");
        Mockito.when(sessionMock.getAttribute(eq("password"))).thenReturn("password");

        renderer.render(requestMock);

        assertEquals("Your username: username\n<br/>" +
                        "    Your password: password\n<br/>" +
                        "    <a href=\"/controller?page=profile&send=redirect\">Go to profile</a><br/>",
                requestMock.getAttribute("render"));
    }
}
