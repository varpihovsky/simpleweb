package WebManager.renderer;

import test.implementations.HttpServletRequestImplemented;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.assertSame;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;

public class RendererControllerTest {
    private static RendererController controller;

    @Mock
    private static HttpServletRequestImplemented request;

    @Before
    public void before() {
        controller = new RendererController();

        request = mock(HttpServletRequestImplemented.class);
    }

    @Test
    public void defineRendererTestFirst() {

        Mockito.when(request.getParameter(eq("page"))).thenReturn(null);

        assertSame(controller.defineRenderer(request).getClass(), NullRenderer.class);
    }

    @Test
    public void defineRendererTestSecond() {
        Mockito.when(request.getParameter(eq("page"))).thenReturn("main");

        assertSame(controller.defineRenderer(request).getClass(), MainPageRenderer.class);
    }
}
