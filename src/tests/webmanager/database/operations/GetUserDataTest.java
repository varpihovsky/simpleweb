package webmanager.database.operations;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import webmanager.database.DatabaseController;
import webmanager.database.abstractions.User;
import webmanager.database.pool.ConnectionPool;
import webmanager.properties.PropertyManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ConnectionPool.class, PropertyManager.class})
public class GetUserDataTest {
    @Mock
    Connection connectionMock;

    @Mock
    PreparedStatement statementMock;

    @Mock
    PropertyManager propertyManagerMock;

    @Mock
    ResultSet resultSetMock;

    @Before
    public void before() throws SQLException {
        mockStatic(PropertyManager.class);
        PowerMockito.when(PropertyManager.getInstance()).thenReturn(propertyManagerMock);

        Mockito.when(propertyManagerMock.getProperty(PropertyManager.DATABASE_CONNECTIONS_AMOUNT)).thenReturn("1");

        mockStatic(ConnectionPool.class);
        PowerMockito.when(ConnectionPool.getConnection()).thenReturn(connectionMock);

        Mockito.when(connectionMock.prepareStatement(Mockito.any())).thenReturn(statementMock);

        Mockito.when(statementMock.executeQuery()).thenReturn(resultSetMock);

        Mockito.when(resultSetMock.next()).thenReturn(true);
        Mockito.when(resultSetMock.getInt(1)).thenReturn(1);
        Mockito.when(resultSetMock.getString(2)).thenReturn("a");
        Mockito.when(resultSetMock.getString(3)).thenReturn("a");
        Mockito.when(resultSetMock.getString(4)).thenReturn("a");
    }

    @Test(expected = NullPointerException.class)
    public void testOperateWhenOperatorIsNull() {
        DatabaseController<GetUserData, User> databaseController =
                new DatabaseController<>(GetUserData::new, null);
        databaseController.execute();
    }

    @Test
    public void testOperate() {
        DatabaseController<GetUserData, User> databaseController =
                new DatabaseController<>(GetUserData::new,
                        new User.Builder()
                                .withUsername("a")
                                .build());
        User expected = new User.Builder()
                .withUsername("a")
                .withPassword("a")
                .withEmail("a")
                .withId(1)
                .build();

        assertEquals(expected, databaseController.execute());
    }
}
