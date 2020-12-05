package webmanager.renderer;

import test.implementations.HttpServletRequestImplemented;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

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
        assertSame(controller.defineRenderer(null).getClass(), NullRenderer.class);
    }

    @Test
    public void defineRendererTestSecond() {
        assertSame(controller.defineRenderer("src/main").getClass(), MainPageRenderer.class);
    }

    @Test
    public void defineRendererTestThird() {
        assertSame(controller.defineRenderer("profile").getClass(), ProfilePageRenderer.class);
    }

    @Test
    public void defineRendererTestFourth() {
        assertSame(controller.defineRenderer("profileSettings").getClass(), ProfileSettingsPageRenderer.class);
    }
}
