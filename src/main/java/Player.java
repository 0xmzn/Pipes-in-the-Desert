import static java.lang.System.out;

import java.util.Scanner;

public abstract class Player {

    protected final Scanner scanner;

    protected Player() {
        scanner = new Scanner(System.in);
    }

    public void changeInputPipe() {
        printMethodName("changeInputPipe()");
        boolean getLocation = getLocation("Pump");
        boolean isPunctured = isPunctured();
        if (getLocation && !isPunctured) {
            out.println("Player is on the pump and it's not punctured. Attempting to change the input pipe...");
            out.println("Input pipe changed to the Next Pipe");
        } else {
            out.println("Player is not on the pump or the pipe is punctured. Cannot change the input pipe.");
        }
    }

    public void changeOutputPipe() {
        printMethodName("changeOutputPipe()");
        boolean getLocation = getLocation("Pump");
        boolean isPunctured = isPunctured();
        if (getLocation && !isPunctured) {
            out.println("Player is on the pump and it's not punctured. Attempting to change the output pipe...");
            out.println("Output pipe changed to the NextPipe");
        } else {
            out.println("Player is not on the pump he pipe is punctured. Cannot change the output pipe.");
        }
    }

    public void moveW() {
        printMethodName("moveW()");

        int choice = askQuestion("is the player able to move up?");

        if (choice != 1 && choice != 2) {
            out.println("Invalid command, operation failed");
            return;
        } else if (choice == 2) {
            out.println("Moving up is not valid for this location! Operation failed");
            return;
        }

        out.println("Moved up.");
    }

    public void moveA() {
        printMethodName("moveA()");

        int choice = askQuestion("is the player able to move left?");

        if (choice != 1 && choice != 2) {
            out.println("Invalid command, operation failed");
            return;
        } else if (choice == 2) {
            out.println("Moving left is not valid for this location! Operation failed");
            return;
        }

        out.println("Moved left.");
    }

    public void moveS() {
        printMethodName("moveS()");

        int choice = askQuestion("is the player able to move down?");

        if (choice != 1 && choice != 2) {
            out.println("Invalid command, operation failed");
            return;
        } else if (choice == 2) {
            out.println("Moving down is not valid for this location! Operation failed");
            return;
        }

        out.println("Moved down.");
    }

    public void moveD() {
        printMethodName("moveD()");

        int choice = askQuestion("is the player able to move right?");

        if (choice != 1 && choice != 2) {
            out.println("Invalid command, operation failed");
            return;
        } else if (choice == 2) {
            out.println("Moving right is not valid for this location! Operation failed");
            return;
        }

        out.println("Moved right.");
    }

    protected int askQuestion(String question) {
        out.println(question);
        out.println("1. Yes");
        out.println("2. No");

        return scanner.nextInt();
    }

    protected static void printMethodName(String methodName) {
        out.println("\n--------------------");
        out.println(methodName);
        out.println("--------------------\n");
    }

    protected boolean checkType(String type) {
        printMethodName("checkType()");
        int isTypeofPlayer = askQuestion("Is the curret player a " + type + "?");

        if (isTypeofPlayer != 1 && isTypeofPlayer != 2) {
            out.println("Invalid command, operation failed");
            return false;
        } else if (isTypeofPlayer == 2) {
            out.println("This player is not a " + type + ", operation failed");
            return false;
        }

        out.println("");
        return true;
    }

    protected boolean isPunctured() {
        printMethodName("isPunctured()");
        int isObjectPunctured = askQuestion("Is the Pump/Pipe punctured?");

        if (isObjectPunctured != 1 && isObjectPunctured != 2) {
            printMethodName("Invalid command, activity failed");
            return false;
        } else if (isObjectPunctured == 1) {
            printMethodName("This pump/pipe is punctured, activity failed");
            return true;
        }

        printMethodName("This pump/pipe is not punctured");
        return false;
    }

    protected boolean getLocation(String location) {
        printMethodName("getLocation()");
        int isLocation = askQuestion("Is the player standing on a " + location + "?");

        if (isLocation != 1 && isLocation != 2) {
            out.println("Invalid command, operation failed");
            return false;
        } else if (isLocation == 2) {
            out.println("You are not standing on a " + location + "! Operation failed");
            return false;
        }

        out.println("");
        return true;
    }
}
