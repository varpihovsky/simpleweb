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
import webmanager.database.abstractions.Room;
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
public class GetRoomIdByNameTest {
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

        Mockito.when(resultSetMock.next()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);
        Mockito.when(resultSetMock.getInt(1)).thenReturn(1);
    }

    @Test(expected = NullPointerException.class)
    public void testOperateWhenOperatorIsNull() {
        DatabaseController<GetRoomIdByName, Room> databaseController =
                new DatabaseController<>(GetRoomIdByName::new, null);
        databaseController.execute();
    }

    @Test
    public void testOperate() {
        DatabaseController<GetRoomIdByName, Room> databaseController =
                new DatabaseController<>(GetRoomIdByName::new,
                        new Room.Builder()
                                .withName("a")
                                .build());

        Room expected = new Room.Builder().withId(1).build();

        assertEquals(expected, databaseController.execute());
    }
}
