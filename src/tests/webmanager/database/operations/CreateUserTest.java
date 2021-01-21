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
import java.sql.SQLException;

import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ConnectionPool.class, PropertyManager.class})
public class CreateUserTest {
    @Mock
    Connection connectionMock;

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
    }

    @Test(expected = NullPointerException.class)
    public void testOperateWhenOperatorIsNull() {
        DatabaseController<CreateUser, User> databaseController =
                new DatabaseController<>(CreateUser::new, null);
        databaseController.execute();
    }

    @Test
    public void testOperate() {
        DatabaseController<CreateUser, User> databaseController =
                new DatabaseController<>(CreateUser::new,
                        new User.Builder()
                                .withUsername("")
                                .withPassword("")
                                .withEmail("")
                                .build());
        databaseController.execute();
    }
}
