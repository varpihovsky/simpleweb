package webmanager.properties;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class PropertyManagerTest {
    @Mock
    private static final PropertyManager propertyManagerMock = mock(PropertyManager.class);

    @Test
    public void testGetProperty() {
        PropertyManager.setInstance(propertyManagerMock);

        Mockito.when(propertyManagerMock.getProperty(PropertyManager.DATABASE_INITIALIZE)).thenReturn("yes");

        assertEquals("yes", PropertyManager.getInstance().getProperty(PropertyManager.DATABASE_INITIALIZE));
    }
}
