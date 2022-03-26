package brainfuck;

import brainfuck.BrainfuckCompiler;

import java.io.InputStream;
import java.util.Scanner;

/**
 * The class is designed to read a sequence of characters from anywhere.
 */
public class SequenceReader {

    private Scanner scanner;

    private BrainfuckCompiler brainfuckCompiler;

    public SequenceReader() {
        brainfuckCompiler = new BrainfuckCompiler();
    }

    public void readSequenceOfCharactersFromConsole() {
        scanner = new Scanner(System.in);
        String stringOfCommands = scanner.nextLine();
        passStringToCompiler(stringOfCommands);
    }

    /**
     * @param path to the file from which we will read the commands
     */
    public void readSequenceOfCharactersFromFile(String path) {
    }

    /**
     * @param inputStream stream from which we will read information
     */
    public void readSequenceOfCharactersFromAnotherStream(InputStream inputStream) {
    }

    private void passStringToCompiler(String stringOfCommands) {
        brainfuckCompiler.readSequenceOfCommands(stringOfCommands);
    }


}
