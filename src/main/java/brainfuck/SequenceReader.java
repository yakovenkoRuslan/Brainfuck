package brainfuck;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * The class is designed to read a sequence of characters from anywhere.
 */
public class SequenceReader {

    private BrainfuckCompiler brainfuckCompiler;

    public SequenceReader() {
        brainfuckCompiler = new BrainfuckCompiler();
    }

    public String readSequenceOfCharactersFromConsole() {
        Scanner scanner = new Scanner(System.in);
        StringBuilder sequenceOfCharacters = new StringBuilder();
        while (scanner.hasNext()) {
            sequenceOfCharacters.append(scanner.next());
        }
        passStringToCompiler(sequenceOfCharacters.toString());
        return sequenceOfCharacters.toString();
    }

    /**
     * @param path to the file from which we will read the commands
     */
    public String readSequenceOfCharactersFromFile(String path) {
        StringBuilder sequenceOfCharacters = new StringBuilder();
        try {
            FileReader fileReader = new FileReader(path);
            int symbolCode;
            while ((symbolCode = fileReader.read()) != -1) {
                sequenceOfCharacters.append((char) symbolCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        passStringToCompiler(sequenceOfCharacters.toString());
        return sequenceOfCharacters.toString();
    }

    private void passStringToCompiler(String stringOfCommands) {
        if (stringOfCommands == null) {
            return;
        }
        brainfuckCompiler.readSequenceOfCommands(stringOfCommands);
    }


}
