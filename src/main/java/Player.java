import static java.lang.System.out;

import java.util.Scanner;

/**
 * The abstract class representing a player in the game. Unites methods used
 * both, by Plumber and by Saboteur.
 */
public abstract class Player {

    protected final Scanner scanner;

    /**
     * Constructs a new Player object. Initializes the scanner to reuse in all
     * methods.
     */
    protected Player() {
        scanner = new Scanner(System.in);
    }

    /**
     * Changes the input pipe for the player.
     * Checks precondition before changing the input pipe: player location and pump
     * puncture state.
     * If any of the conditions are not met, the method returns without performing
     * any action.
     * Otherwise, it prints a success message.
     */
    public void changeInputPipe() {
        printMethodName("changeInputPipe()");
        boolean getLocation = getLocation("Pump");
        boolean isPunctured = isPunctured();
        if (getLocation && !isPunctured) {
            out.println("Player is on the pump and it's not punctured. Attempting to change the input pipe...");
            out.println("Input pipe changed to the new Pipe");
        } else {
            out.println("Player is not on the pump or the pipe is punctured. Cannot change the input pipe.");
        }
    }

    /**
     * Changes the input pipe for the player.
     * Checks precondition before changing the output pipe: player location and pump
     * puncture state.
     * If any of the conditions are not met, the method returns without performing
     * any action.
     * Otherwise, it prints a success message.
     */
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

    /**
     * Moves the player up if possible.
     */
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

    /**
     * Moves the player left if possible.
     */
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

    /**
     * Moves the player down if possible.
     */
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

    /**
     * Moves the player right if possible.
     */
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

    /**
     * Asks a question to the player and returns the choice.
     *
     * @param question the question to ask
     * @return the choice made by the player
     */
    protected int askQuestion(String question) {
        out.println(question);
        out.println("1. Yes");
        out.println("2. No");

        return scanner.nextInt();
    }

    /**
     * Prints the name of the current method.
     *
     * @param methodName the name of the method
     */
    protected static void printMethodName(String methodName) {
        out.println("\n--------------------");
        out.println(methodName);
        out.println("--------------------\n");
    }

    /**
     * Checks if the current player is a Plumber or a Saboteur.
     *
     * @param type the type to check
     * @return true if the player is of the specified type, false otherwise
     */
    protected boolean checkType(String type) {
        printMethodName("checkType()");
        int isTypeofPlayer = askQuestion("Is the current player a " + type + "?");

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


    /**
     * Checks if the player is standing on the specified location.
     *
     * @param location the location to check
     * @return true if the player is standing on the specified location, false
     *         otherwise
     */
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