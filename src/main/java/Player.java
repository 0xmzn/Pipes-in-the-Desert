import static java.lang.System.out;

import java.awt.*;
import java.util.Scanner;

/**
 * The abstract class representing a player in the game. Unites methods used
 * both, by Plumber and by Saboteur.
 */
public abstract class Player {

    protected final Scanner scanner;
    /**
     * The coordinate of the player.
     */
    protected Point coordinate;
    /**
     * the possible steppable cells for the current player.
     */
    protected boolean[][] stepableCell;

    /**
     * Constructs a new Player object. Initializes the scanner to reuse in all
     * methods.
     */
    protected Player() {
        // TODO: update with prototype version
        scanner = new Scanner(System.in);
    }

    /**
     * Changes the input pipe for the player.
     * Checks precondition before changing the input pipe: player location and pump
     * puncture state.
     * If any of the conditions are not met, the method returns without performing
     * any action. Otherwise, it prints a success message.
     * 
     * @param pump      the pump object representing the new input pipe
     * @param endOfPipe the endOfPipe object representing the end of the new input
     *                  pipe
     * @return true if the input pipe was successfully changed, false otherwise
     */
    public boolean changeInputPipe(Pump pump, EndOfPipe endOfPipe) {
        // TODO: update with prototype version
        printMethodName("changeInputPipe()");
        boolean getLocation = getLocation("Pump");


        return getLocation;
    }

    /**
     * Changes the input pipe for the player.
     * Checks precondition before changing the output pipe: player location and pump
     * puncture state.
     * If any of the conditions are not met, the method returns without performing
     * any action.
     * Otherwise, it prints a success message.
     * 
     * @param pump      the pump object representing the new output pipe
     * @param endOfPipe the endOfPipe object representing the end of the new output
     *                  pipe
     * @return true if the input pipe was successfully changed, false otherwise
     */
    public boolean changeOutputPipe(Pump pump, EndOfPipe endOfPipe) {
        // TODO: update with prototype version
        printMethodName("changeOutputPipe()");
        boolean getLocation = getLocation("Pump");

        return getLocation;
    }

    /**
     * Sets the stepability of the cell at the given coordinates (x,y) to the
     * specified value (stepable).
     *
     * @param x        the x-coordinate of the cell
     * @param y        the y-coordinate of the cell
     * @param stepable the stepability value to set
     * @return true if the stepability was successfully set, false otherwise
     */
    public boolean setStepableCells(int x, int y, boolean stepable) {
        printMethodName("setStepableCells()");
        // TODO: update with prototype version
        return false; // Placeholder return statement
    }

    /**
     * Checks if the given coordinate in stepableCells is true.
     *
     * @param x the x-coordinate of the cell
     * @param y the y-coordinate of the cell
     * @return true if the given coordinate in stepableCells is true, false
     *         otherwise
     */
    public boolean checkStepableCell(int x, int y) {
        printMethodName("checkStepableCell()");
        // TODO: update with prototype version
        return false; // Placeholder return statement
    }

    /**
     * Moves the player based on the provided key.
     * 
     * @param key the key representing the direction of movement
     * @return true if the player was successfully moved, false otherwise
     */
    public boolean move(int key) {
        // TODO: update with prototype version
        // four old method implementations are provided below
        return false;
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
     * Player voluntarily gives up their turn.
     */
    public void giveUpTurn() {
        // TODO: update with prototype version
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
        // TODO: update with prototype version
        // remove if not needed
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
        // TODO: update with prototype version
        // remove if not needed
        printMethodName("getLocation()");

        return true;
    }
}