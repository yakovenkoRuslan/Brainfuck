package brainfuck;

import brainfuck.commands.BreakCommandImpl;
import brainfuck.commands.Command;

import java.util.Scanner;

public class BrainfuckCompiler {

    private int[] byteArray;

    private int pointer;

    private int differenceBetweenOpeningAndClosingBraces;

    public BrainfuckCompiler() {
        byteArray = new int[30000];
    }

    public void readSequenceOfCommands(String sequence) {
        try {
            for (int index = 0; index < sequence.length(); index++) {
                index = executeACommand(sequence, index);
            }
        } catch (IncorrectCommandException exception) {
            System.out.println(exception.getMessage());
        }
        byteArray = new int[30000];
    }

    private int executeACommand(String sequence, int index) throws IncorrectCommandException {
        char command = sequence.charAt(index);
        if (command == '>') {
            pointer = pointer != byteArray.length - 1 ? ++pointer : 0;
        } else if (command == '<') {
            pointer = pointer == 0 ? byteArray.length - 1 : --pointer;
        } else if (command == '+') {
            byteArray[pointer]++;
        } else if (command == '-') {
            byteArray[pointer]--;
        } else if (command == '.') {
            System.out.print((char) byteArray[pointer]);
        } else if (command == ',') {
            Scanner scanner = new Scanner(System.in);
            byteArray[pointer] = (byte) scanner.next().charAt(0);
        } else if (command == '[') {
            return searchClosingBraces(sequence, index);
        } else if (command == ']') {
            return searchOpeningBraces(sequence, index);
        } else {
            return executeCustomCommand(sequence, index);
        }
        return index;
    }

    private int searchClosingBraces(String sequence, int index) {
        if (byteArray[pointer] != 0) return index;
        index++;
            while (differenceBetweenOpeningAndClosingBraces > 0 || sequence.charAt(index) != ']') {
            if (sequence.charAt(index) == '[') {
                differenceBetweenOpeningAndClosingBraces++;
            }
            if (sequence.charAt(index) == ']') {
                differenceBetweenOpeningAndClosingBraces--;
            }
            index++;
        }
        return index;
    }

    private int searchOpeningBraces(String sequence, int index) {
        if (byteArray[pointer] == 0) return index;
        index--;
        while (differenceBetweenOpeningAndClosingBraces > 0 || sequence.charAt(index) != '[') {
            if (sequence.charAt(index) == ']') {
                differenceBetweenOpeningAndClosingBraces++;
            }
            if (sequence.charAt(index) == '[') {
                differenceBetweenOpeningAndClosingBraces--;
            }
            index--;
        }
        return --index;
    }

    /**
     * How to add custom commands
     *
     * @param sequence of commands
     * @param index of current value in sequence
     * @return current index in sequence
     * @throws IncorrectCommandException if command not found
     */
    private int executeCustomCommand(String sequence, int index) throws IncorrectCommandException {
        Command command;
        if (sequence.charAt(index) == '*') {
            command = new BreakCommandImpl();
        } else {
            throw new IncorrectCommandException("Command not found!");
        }
        return command.execute(sequence, index, byteArray, pointer);
    }

    public int getPointer() {
        return pointer;
    }

    public int[] getByteArray() {
        return byteArray;
    }
}
