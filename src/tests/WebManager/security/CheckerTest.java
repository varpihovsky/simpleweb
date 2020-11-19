package WebManager.security;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckerTest {
    private static Checker checker = new Checker();

    @Test
    public void isContainsWrongTest() {
        assertTrue(checker.isContainsWrong(null));
        assertTrue(checker.isContainsWrong(""));
        assertTrue(checker.isContainsWrong("\'"));
        assertTrue(!checker.isContainsWrong("main"));
    }

    @Test
    public void checkLengthTest() {
        String str = "asdasdaasd";

        assertTrue(checker.checkLength(str, 0, 10));
        assertTrue(checker.checkLength(str, 10, 0));
        assertTrue(!checker.checkLength(str, 0, 0));
    }

    @Test
    public void pageReplaceTest() {
        assertEquals("main", checker.pageReplace("/main.jsp"));
    }
}
