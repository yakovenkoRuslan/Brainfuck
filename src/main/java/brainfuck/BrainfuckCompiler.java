package brainfuck;

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
            throw new IncorrectCommandException("Command not found!");
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

    public int getPointer() {
        return pointer;
    }

    public int[] getByteArray() {
        return byteArray;
    }
}
