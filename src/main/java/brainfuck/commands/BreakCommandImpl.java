package brainfuck.commands;

/**
 * Operator analogue break for brainfuck compiler
 */
public class BreakCommandImpl implements Command {

    @Override
    public int execute(String sequence, int index, int[] byteArray, int pointer) {
        return 1;
    }
}
