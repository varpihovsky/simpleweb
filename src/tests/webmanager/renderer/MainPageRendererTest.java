package webmanager.renderer;


import test.implementations.HttpServletRequestImplemented;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import webmanager.database.DatabaseController;

import javax.servlet.ServletContext;
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

    @Mock
    private static DatabaseController controllerMock;

    @Mock
    private static ServletContext contextMock;

    @Before
    public void before() {
        renderer = new MainPageRenderer();

        requestMock = mock(HttpServletRequestImplemented.class);
        sessionMock = mock(HttpSession.class);
        controllerMock = mock(DatabaseController.class);
        contextMock = mock(ServletContext.class);

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

        renderer.render(requestMock, controllerMock);

        assertEquals("<a href=\"#\">Users</a>\n" +
                        "            <a href=\"/controller?page=register&send=redirect\">Register</a>\n" +
                        "            <a href=\"#\">News</a>\n" +
                        "            <a href=\"#\">Rooms</a>\n" +
                        "            <a href=\"/controller?page=login&send=redirect\">Login</a>",
                requestMock.getAttribute("render"));
    }

    @Test
    public void renderTestSecond() {
        Mockito.when(sessionMock.getAttribute(eq("username"))).thenReturn("username");
        Mockito.when(sessionMock.getAttribute(eq("password"))).thenReturn("password");

        renderer.render(requestMock, controllerMock);

        assertEquals("<a href=\"#\">Users</a>\n" +
                        "            <a href=\"/controller?page=profile&send=redirect\">Profile</a>\n" +
                        "            <a href=\"#\">News</a>\n" +
                        "            <a href=\"#\">Rooms</a>\n" +
                        "            <a href=\"/controller?page=main&send=logout\">Logout</a>",
                requestMock.getAttribute("render"));
    }
}
