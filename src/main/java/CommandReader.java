import java.io.InputStream;
import java.util.Scanner;

/**
 *
 */
public class CommandReader {

    private Scanner scanner;

    private BrainfuckCompiler brainfuckCompiler;

    public CommandReader() {
        brainfuckCompiler = new BrainfuckCompiler();
    }

    public void readSequenceOfCharactersFromConsole() {
        scanner = new Scanner(System.in);
        String stringOfCommands = scanner.nextLine();
        passStringToCompiler(stringOfCommands);
    }

    public void readSequenceOfCharactersFromFile(String file) {
    }

    public void readSequenceOfCharactersFromAnotherStream(InputStream inputStream) {
    }

    private void passStringToCompiler(String stringOfCommands) {
        brainfuckCompiler.readSequenceOfCommands(stringOfCommands);
    }


}
