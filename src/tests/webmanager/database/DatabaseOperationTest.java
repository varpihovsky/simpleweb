package webmanager.database;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import webmanager.database.abstractions.Room;
import webmanager.database.operations.RoomAddToUser;
import webmanager.database.pool.ConnectionPool;
import webmanager.properties.PropertyManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mockStatic;


@RunWith(PowerMockRunner.class)
@PrepareForTest({ConnectionPool.class, PropertyManager.class})
public class DatabaseOperationTest {

    @Mock
    Connection connectionMock;

    @Mock
    ResultSet resultSet;

    @Mock
    PreparedStatement statementMock;

    @Mock
    PropertyManager propertyManagerMock;

    @Before
    public void before() throws SQLException {
        mockStatic(PropertyManager.class);
        PowerMockito.when(PropertyManager.getInstance()).thenReturn(propertyManagerMock);

        Mockito.when(propertyManagerMock.getProperty(PropertyManager.DATABASE_CONNECTIONS_AMOUNT)).thenReturn("1");

        mockStatic(ConnectionPool.class);
        PowerMockito.when(ConnectionPool.getConnection()).thenReturn(connectionMock);

        Mockito.when(connectionMock.prepareStatement(Mockito.any())).thenReturn(statementMock);

        Mockito.when(statementMock.executeQuery()).thenReturn(resultSet);
    }

    @Test
    public void testGetRooms() throws SQLException {
        DatabaseOperation databaseOperation = new RoomAddToUser();


        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getInt(1)).thenReturn(1).thenReturn(2).thenReturn(3);
        Mockito.when(resultSet.getString(2)).thenReturn("a").thenReturn("b").thenReturn("c");
        Mockito.when(resultSet.getString(3)).thenReturn("a").thenReturn("b").thenReturn("c");

        ArrayList<Room> expected = new ArrayList<>();

        expected.add(new Room.Builder()
                .withId(1)
                .withName("a")
                .withDescription("a")
                .build());
        expected.add(new Room.Builder()
                .withId(2)
                .withName("b")
                .withDescription("b")
                .build());
        expected.add(new Room.Builder()
                .withId(3)
                .withName("c")
                .withDescription("c")
                .build());

        ArrayList<Room> roomArrayList = databaseOperation.getRooms(resultSet);

        for (int i = 0; i < roomArrayList.size(); i++) {
            assertEquals(roomArrayList.get(i), expected.get(i));
        }
    }
}
