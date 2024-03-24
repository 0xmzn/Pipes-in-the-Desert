import java.util.Scanner;
public abstract class Player {

    private Scanner scanner;

    public Player(){
        scanner = new Scanner(System.in);
    }

    private static void printMethodName(String methodName){
        System.out.println("\n--------------------");
        System.out.println(methodName);
        System.out.println("--------------------\n");
    }

    public void changeInputPipe() {
        if (getLocation() && !isPunctured()) {
            printMethodName("Player is on the pump and it's not punctured. Attempting to change the input pipe...");
            printMethodName("Input pipe changed to the Next Pipe");
        } else if (!getLocation()) {
            printMethodName("Player is not on the pump. Cannot change the input pipe.");
        } else if (isPunctured()) {
            printMethodName("The pump is punctured. Cannot change the input pipe.");
        }
    }

    public void changeOutputPipe() {
        if (getLocation() && !isPunctured()) {
            printMethodName("Player is on the pump and it's not punctured. Attempting to change the output pipe...");
            printMethodName("Output pipe changed to the NextPipe");
        } else if (!getLocation()) {
            printMethodName("Player is not on the pump. Cannot change the output pipe.");
        } else if (isPunctured()) {
            printMethodName("The pump is punctured. Cannot change the output pipe.");
        }
    }

    private int askQuestion(String question){
        System.out.println(question);
        System.out.println("1. Yes");
        System.out.println("2. No");

        return scanner.nextInt();
    }

    public boolean getLocation(){
        printMethodName("getLocation()");
        int isStandingOnPump = askQuestion("Is the Player standing on the Pump?");

        if (isStandingOnPump != 1 && isStandingOnPump != 2){
            printMethodName("Invalid command, changeInput/OutputPipe() failed");
            return false;
        }
        else if (isStandingOnPump == 2){
            printMethodName("This player is not standing on the Pump, changeInput/OutputPipe() failed");
            return false;
        }

        printMethodName("This player is standing on the Pump");
        return true;
    }
    
    public boolean isPunctured(){
        printMethodName("isPunctured()");
        int isObjectPunctured = askQuestion("Is the Pump/Pipe punctured?");

        if (isObjectPunctured != 1 && isObjectPunctured != 2){
            printMethodName("Invalid command, activity failed");
            return false;
        }
        else if (isObjectPunctured == 1){
            printMethodName("This pump/pipe is punctured, activity failed");
            return true;
        }

        printMethodName("This pump/pipe is not punctured");
        return false;
    }

    public void moveW() {
        printMethodName("Moved up to position");
    }

    public void moveA() {
        printMethodName("Moved left to position");
    }

    public void moveS() {
        printMethodName("Moved down to position");
    }

    public void moveD() {
        printMethodName("Moved right to position");
    }
}

