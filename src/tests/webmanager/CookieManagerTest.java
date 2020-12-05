package webmanager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import test.implementations.HttpServletRequestImplemented;
import test.implementations.HttpServletResponseImplemented;

import javax.servlet.http.Cookie;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class CookieManagerTest {
    private static CookieManager manager;

    private Cookie[] cookieArray;

    @Mock
    HttpServletRequestImplemented requestMock;

    @Mock
    HttpServletResponseImplemented responseMock;

    @Before
    public void before() {
        manager = new CookieManager();

        requestMock = mock(HttpServletRequestImplemented.class);
        responseMock = mock(HttpServletResponseImplemented.class);

        Mockito.doCallRealMethod().when(responseMock).addCookie(Mockito.any());
    }

    @Test
    public void getCookiesFromRequestTestFirst() {
        cookieArray = new Cookie[]{new Cookie("username", "user"),
                new Cookie("password", "pass")};

        Mockito.when(requestMock.getCookies()).thenReturn(cookieArray);

        assertTrue(manager.getCookiesFromRequest(requestMock));
    }


    @Test
    public void getCookiesFromRequestTestSecond() {
        cookieArray = new Cookie[]{};

        Mockito.when(requestMock.getCookies()).thenReturn(cookieArray);

        assertTrue(!manager.getCookiesFromRequest(requestMock));
    }

    @Test
    public void setCookiesToResponseTestFirst() {
        responseMock.cookies = new ArrayList<>();

        manager.setCookiesToResponse(responseMock, null, null);

        assertTrue(responseMock.cookies.size() == 0);
    }

    @Test
    public void setCookiesToResponseTestSecond() {
        responseMock.cookies = new ArrayList<>();

        manager.setCookiesToResponse(responseMock, "user", "pass");

        assertTrue(responseMock.cookies.size() > 0);
    }
}
