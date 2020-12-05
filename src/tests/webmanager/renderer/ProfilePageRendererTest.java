package webmanager.renderer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import test.implementations.HttpServletRequestImplemented;
import test.implementations.HttpSessionImplemented;
import webmanager.database.DatabaseController;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;

public class ProfilePageRendererTest {
    private static ProfilePageRenderer renderer;

    @Mock
    private static HttpServletRequestImplemented requestMock;

    @Mock
    private static HttpSessionImplemented sessionMock;

    @Mock
    private static DatabaseController controllerMock;

    @Before
    public void before() throws SQLException {
        renderer = new ProfilePageRenderer();

        requestMock = mock(HttpServletRequestImplemented.class);
        sessionMock = mock(HttpSessionImplemented.class);
        controllerMock = mock(DatabaseController.class);

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
                requestMock.getAttribute("navbar"));

        assertEquals("<img src=\"useravatars/.jpg\" alt=\"avatar\"/>", requestMock.getAttribute("avatar"));

        assertEquals("", requestMock.getAttribute("username"));
    }

    @Test
    public void renderTestSecond() {
        Mockito.when(sessionMock.getAttribute(eq("username"))).thenReturn("user");
        Mockito.when(sessionMock.getAttribute(eq("password"))).thenReturn("password");

        renderer.render(requestMock, controllerMock);

        assertEquals("<a href=\"#\">Users</a>\n" +
                        "            <a href=\"/controller?page=profile&send=redirect\">Profile</a>\n" +
                        "            <a href=\"#\">News</a>\n" +
                        "            <a href=\"#\">Rooms</a>\n" +
                        "            <a href=\"/controller?page=main&send=logout\">Logout</a>",
                requestMock.getAttribute("navbar"));

        assertEquals("<img src=\"useravatars/user.jpg\" alt=\"avatar\"/>", requestMock.getAttribute("avatar"));

        assertEquals("user", requestMock.getAttribute("username"));
    }
}
