import brainfuck.BrainfuckCompiler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BrainfuckCompilerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeAll
    public void setUpStream() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void shouldIncrementPointer() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        BrainfuckCompiler brainfuckCompiler = new BrainfuckCompiler();
        Method method = BrainfuckCompiler.class.getDeclaredMethod("executeACommand", String.class, int.class);
        method.setAccessible(true);
        method.invoke(brainfuckCompiler, ">>++++----....>>>", 16);
        assertEquals(1, brainfuckCompiler.getPointer());
    }

    @Test
    public void shouldIncreaseCurrentMemoryCellOnFive() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        BrainfuckCompiler brainfuckCompiler = new BrainfuckCompiler();
        Method method = BrainfuckCompiler.class.getDeclaredMethod("executeACommand", String.class, int.class);
        method.setAccessible(true);
        method.invoke(brainfuckCompiler, "+++", 0);
        method.invoke(brainfuckCompiler, "+++", 1);
        method.invoke(brainfuckCompiler, "+++", 2);
        assertEquals(3, brainfuckCompiler.getByteArray()[0]);
    }

    @Test
    public void shouldReturnCurrentIndexForBreakOperatorNotInLoop() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        BrainfuckCompiler brainfuckCompiler = new BrainfuckCompiler();
        Method method = BrainfuckCompiler.class.getDeclaredMethod("executeCustomCommand", String.class, int.class);
        method.setAccessible(true);
        int actual = (int) method.invoke(brainfuckCompiler, ">>>++*-<.,[]", 5);
        assertEquals(5, actual);
    }

    @Test
    public void shouldReturnIndexOfRightBracesForBreakOperatorInLoop() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        BrainfuckCompiler brainfuckCompiler = new BrainfuckCompiler();
        Method method = BrainfuckCompiler.class.getDeclaredMethod("executeCustomCommand", String.class, int.class);
        method.setAccessible(true);
        int actual = (int) method.invoke(brainfuckCompiler, ">>>[++*-<.,]>>>", 6);
        assertEquals(11, actual);
    }

    @Test
    public void shouldReturnIndexOfMatchingClosingBracketForBlankMemoryCell() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        BrainfuckCompiler brainfuckCompiler = new BrainfuckCompiler();
        Method method = BrainfuckCompiler.class.getDeclaredMethod("searchClosingBraces", String.class, int.class);
        method.setAccessible(true);
        int actual = (int) method.invoke(brainfuckCompiler, ">>>[++*-<.,]>>>", 3);
        assertEquals(11, actual);
    }

    @Test
    public void shouldReturnIndexOfCurrentCommandForBlankOpeningBracket() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        BrainfuckCompiler brainfuckCompiler = new BrainfuckCompiler();
        Method method = BrainfuckCompiler.class.getDeclaredMethod("searchOpeningBraces", String.class, int.class);
        method.setAccessible(true);
        int actual = (int) method.invoke(brainfuckCompiler, ">>>[++*>>>-<.,<<]>>>", 16);
        assertEquals(16, actual);
    }

    @Test
    public void shouldReturnPointerWithEqualLastIndexInMemory() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        BrainfuckCompiler brainfuckCompiler = new BrainfuckCompiler();
        Method method = BrainfuckCompiler.class.getDeclaredMethod("executeACommand", String.class, int.class);
        method.setAccessible(true);
        method.invoke(brainfuckCompiler, "<", 0);
        int actual = brainfuckCompiler.getPointer();
        int expected = brainfuckCompiler.getByteArray().length - 1;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldTrowExceptionWithMessageForUnknownCommand() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        BrainfuckCompiler brainfuckCompiler = new BrainfuckCompiler();
        Method method = BrainfuckCompiler.class.getDeclaredMethod("executeCustomCommand", String.class, int.class);
        method.setAccessible(true);
        InvocationTargetException exception = assertThrows(InvocationTargetException.class, () -> {
            method.invoke(brainfuckCompiler, "+++[>>+#+*<<-].", 7);
        });
        assertEquals("Command not found!", exception.getCause().getMessage());
    }

    @Test
    public void shouldPrintHelloWorld() {
        BrainfuckCompiler brainfuckCompiler = new BrainfuckCompiler();
        String sequenceOfCommand = "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.";
        brainfuckCompiler.readSequenceOfCommands(sequenceOfCommand);
        assertEquals("Hello World!", outContent.toString().trim());
    }
}
