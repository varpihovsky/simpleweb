package webmanager.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckerTest {
    private static final Checker checker = new Checker();

    @Test
    public void isContainsWrongTest() {
        assertTrue(Checker.isContainsWrong(null));
        assertTrue(Checker.isContainsWrong(""));
        assertTrue(Checker.isContainsWrong("'"));
        assertTrue(!Checker.isContainsWrong("src/main"));
    }

    @Test
    public void checkLengthTest() {
        String str = "asdasdaasd";

        assertTrue(Checker.checkLength(str, 0, 10));
        assertTrue(Checker.checkLength(str, 10, 0));
        assertTrue(!Checker.checkLength(str, 0, 0));
    }

    @Test
    public void pageReplaceTest() {
        assertEquals("main", Checker.pageReplace("/main.jsp"));
    }
}
