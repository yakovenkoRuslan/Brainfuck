import brainfuck.commands.BreakCommandImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BreakCommandImplTest {

    private final BreakCommandImpl breakCommand = new BreakCommandImpl();

    @Test
    public void shouldReturnFalseIfLeftBracketNotExists() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = BreakCommandImpl.class.getDeclaredMethod("searchLeftParentheses", String.class, int.class);
        method.setAccessible(true);
        boolean actual = (boolean) method.invoke(breakCommand, ">><-+-.,*[]", 8);
        Assertions.assertFalse(actual);
    }

    @Test
    public void shouldReturnTrueIfLeftBracketExists() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = BreakCommandImpl.class.getDeclaredMethod("searchLeftParentheses", String.class, int.class);
        method.setAccessible(true);
        boolean actual = (boolean) method.invoke(breakCommand, ">><[+-.,*]", 8);
        Assertions.assertTrue(actual);
    }

    @Test
    public void shouldReturnIndexOfRightBracket() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = BreakCommandImpl.class.getDeclaredMethod("searchRightParentheses", String.class, int.class);
        method.setAccessible(true);
        int actual = (int) method.invoke(breakCommand, "++[-->.*.,,<<<]+++<<<<<", 7);
        assertEquals(14, actual);
    }
}
