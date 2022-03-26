package brainfuck.commands;

/**
 * Interface for creating custom commands
 */
public interface Command {

    public int execute(String sequence, int index, int[] byteArray, int pointer);
}
