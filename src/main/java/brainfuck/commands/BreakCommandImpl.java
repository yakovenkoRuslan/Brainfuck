package brainfuck.commands;

/**
 * Operator analogue break for brainfuck compiler
 */
public class BreakCommandImpl implements Command {

    @Override
    public int execute(String sequence, int index, int[] byteArray, int pointer) {
        boolean result = searchLeftParentheses(sequence, index);
        if (result) {
            return searchRightParentheses(sequence, index);
        }
        return index;
    }

    private boolean searchLeftParentheses(String sequence, int index) {
        int leftPointer = index - 1;
        int differenceBetweenParentheses = 0;
        boolean isLoop = false;
        while (leftPointer >= 0) {
            if (sequence.charAt(leftPointer) == ']') {
                differenceBetweenParentheses++;
            } else if (sequence.charAt(index) == '[') {
                differenceBetweenParentheses--;
            }
            if (differenceBetweenParentheses < 0) {
                differenceBetweenParentheses = 0;
                isLoop = true;
                break;
            }
            leftPointer--;
        }
        return isLoop;
    }

    private int searchRightParentheses(String sequence, int index) {
        int rightPointer = index + 1;
        int differenceBetweenParentheses = 0;
        while (rightPointer <= sequence.length() - 1) {
            if (sequence.charAt(rightPointer) == '[') {
                differenceBetweenParentheses++;
            } else if (sequence.charAt(index) == ']') {
                differenceBetweenParentheses--;
            }
            if (differenceBetweenParentheses < 0) {
                return rightPointer;
            }
            rightPointer++;
        }
        return index;
    }
}
