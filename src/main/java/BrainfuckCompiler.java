public class BrainfuckCompiler {

    private int[] byteArray;

    private int pointer;

    public BrainfuckCompiler() {
        byteArray = new int[30000];
    }
    public void readSequenceOfCommands(String sequence) {
        try {
            for (pointer = 0; pointer < sequence.length(); pointer++) {
                executeACommand(sequence.charAt(pointer));
            }
        } catch (IncorrectCommandException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void executeACommand(char command) throws IncorrectCommandException {
    }

    public int getPointer() {
        return pointer;
    }
}
