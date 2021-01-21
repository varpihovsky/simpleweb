package webmanager.database;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import webmanager.database.abstractions.User;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

public class DatabaseControllerTest {

    @Before
    public void before() {
        DatabaseController.init("no");
    }

    @Test
    public void testDatabaseControllerConstructor() {
        DatabaseOperation<Void, User> databaseOperationMock = mock(DatabaseOperation.class);

        Mockito.when(databaseOperationMock.start(Mockito.any())).thenReturn(null);

        User userMock = mock(User.class);

        DatabaseController<DatabaseOperation<Void, User>, User> databaseController =
                new DatabaseController<>(() -> databaseOperationMock, userMock);

        assertNull(databaseController.execute());
    }

}
