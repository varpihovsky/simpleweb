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
import java.sql.SQLException;

import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ConnectionPool.class, PropertyManager.class})
public class CreateRoomTest {
    @Mock
    Connection connectionMock;

    @Mock
    PreparedStatement firstStatementMock;

    @Mock
    PreparedStatement secondStatementMock;

    @Mock
    PreparedStatement thirdStatementMock;

    @Mock
    PropertyManager propertyManagerMock;

    @Before
    public void before() throws SQLException {
        mockStatic(PropertyManager.class);
        PowerMockito.when(PropertyManager.getInstance()).thenReturn(propertyManagerMock);

        Mockito.when(propertyManagerMock.getProperty(PropertyManager.DATABASE_CONNECTIONS_AMOUNT)).thenReturn("1");

        mockStatic(ConnectionPool.class);
        PowerMockito.when(ConnectionPool.getConnection()).thenReturn(connectionMock);

        Mockito.when(connectionMock.prepareStatement(Mockito.any()))
                .thenReturn(firstStatementMock)
                .thenReturn(secondStatementMock)
                .thenReturn(thirdStatementMock);
    }

    @Test(expected = NullPointerException.class)
    public void testOperateWhenOperatorIsNull() {
        DatabaseController<CreateRoom, Room> databaseController =
                new DatabaseController<>(CreateRoom::new, null);
        databaseController.execute();
    }

    @Test
    public void testOperate() {
        DatabaseController<CreateRoom, Room> databaseController =
                new DatabaseController<>(CreateRoom::new,
                        new Room.Builder()
                                .withName("")
                                .withDescription("")
                                .withPassword("")
                                .addAdditionalData("username", "")
                                .build());
        databaseController.execute();
    }
}
