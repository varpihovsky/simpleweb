package webmanager.database.pool;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import webmanager.properties.PropertyManager;

import java.sql.Connection;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

//Warning!!! This test should be run independent from other tests!!!
//TODO: do this test working with others tests
@Ignore
@RunWith(PowerMockRunner.class)
@PrepareForTest({ConnectionPoolSupplier.class, PropertyManager.class})
@PowerMockIgnore("org.mockito.*")
public class ConnectionPoolTest {
    @Mock
    PropertyManager propertyManagerMock;

    @Mock
    Connection connectionMock;

    @Before
    public void before() throws Exception {
        mockStatic(PropertyManager.class);
        PowerMockito.when(PropertyManager.getInstance()).thenReturn(propertyManagerMock);

        Mockito.when(propertyManagerMock.getProperty(PropertyManager.DATABASE_CONNECTIONS_AMOUNT)).thenReturn("1");
        Mockito.when(propertyManagerMock.getProperty(PropertyManager.DATABASE_CURRENT)).thenReturn("MySQL");
        Mockito.when(propertyManagerMock.getProperty(PropertyManager.DATABASE_URL)).thenReturn("");

        mockStatic(ConnectionPoolSupplier.class);
        PowerMockito.when(ConnectionPoolSupplier.getConnectionFromDriver()).thenReturn(connectionMock);

        Mockito.when(connectionMock.isClosed()).thenReturn(false).thenReturn(true).thenReturn(false);
    }

    @Test
    public void testGiveBack() {
        Connection connection = ConnectionPool.getConnection();
        ConnectionPool.giveBack(connection);
        assertEquals(connectionMock, ConnectionPool.getConnection());
    }

    @Test
    public void testGetConnection() {
        assertEquals(connectionMock, ConnectionPool.getConnection());
    }
}
