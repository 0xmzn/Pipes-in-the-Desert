import java.awt.Point;
import java.util.List;
import java.util.Scanner;
import static java.lang.System.out;
import java.awt.Point;

/**
 * The abstract class representing a player in the game. Unites common methods
 * used by both Plumber and Saboteur.
 */
public abstract class Player {

    protected final Scanner scanner;
    /**
     * The coordinate of the player on the game grid.
     */
    protected Point coordinate;
    /**
     * The possible steppable cells for the current player.
     */
    protected boolean[][] steppableCell;

    /**
     * Constructs a new Player object. Initializes the scanner to reuse in all
     * methods.
     */
    protected Player() {
        scanner = new Scanner(System.in);
    }

    /**
     * Moves the player to the left.
     *
     * @return true if the move is successful, false otherwise
     */
    public boolean moveA() {
        printMethodName("moveA()");
        if (checkStepableCell(coordinate.x - 1, coordinate.y)) {
            coordinate = new Point(coordinate.x - 1, coordinate.y);
            return true;
        } else {
            out.println("Cannot move left, obstacle detected.");
            return false;
        }
    }

    /**
     * Moves the player up.
     *
     * @return true if the move is successful, false otherwise
     */
    public boolean moveW() {
        printMethodName("moveW()");
        if (checkStepableCell(coordinate.x, coordinate.y - 1)) {
            coordinate = new Point(coordinate.x, coordinate.y - 1);
            return true;
        } else {
            out.println("Cannot move up, obstacle detected.");
            return false;
        }
    }

    /**
     * Moves the player down.
     *
     * @return true if the move is successful, false otherwise
     */
    public boolean moveS() {
        printMethodName("moveS()");
        if (checkStepableCell(coordinate.x, coordinate.y + 1)) {
            coordinate = new Point(coordinate.x, coordinate.y + 1);
            return true;
        } else {
            out.println("Cannot move down, obstacle detected.");
            return false;
        }
    }

    /**
     * Moves the player to the right.
     *
     * @return true if the move is successful, false otherwise
     */
    public boolean moveD() {
        printMethodName("moveD()");
        if (checkStepableCell(coordinate.x + 1, coordinate.y)) {
            coordinate = new Point(coordinate.x + 1, coordinate.y);
            return true;
        } else {
            out.println("Cannot move right, obstacle detected.");
            return false;
        }
    }

    /**
     * Prints the name of the current method for debugging purposes.
     *
     * @param methodName the name of the method to print
     */
    protected void printMethodName(String methodName) {
        out.println("\n--------------------");
        out.println(methodName + " called.");
        out.println("--------------------\n");
    }

    /**
     * Sets the specified cell as steppable if the player can move there
     *
     * @param x the x-coordinate of the cell
     * @param y the y-coordinate of the cell
     */
    protected void setSteppableCell(int x, int y) {
        printMethodName("setSteppableCell");
        steppableCell[x][y] = true;
    }

    /**
     * Checks if the specified cell is steppable.
     *
     * @param x the x-coordinate of the cell
     * @param y the y-coordinate of the cell
     * @return true if the cell is steppable, false otherwise
     */
    private boolean checkStepableCell(int x, int y) {
        printMethodName("checkStepableCell");
        return steppableCell[x][y];
    }

    /**
     * Changes the input pipe of the specified pump.
     *
     * @param pump the pump to change the input pipe for
     * @param endOfPipe the new input end of pipe
     * @return true if the input pipe is successfully changed, false otherwise
     */
    protected boolean changeInputPipe(Pump pump, EndOfPipe endOfPipe) {
        printMethodName("changeInputPipe");
        int pumpCoordinateX = pump.getCoordinate().x;
        int pumpCoordinateY = pump.getCoordinate().y;
        if(this.coordinate.x == pumpCoordinateX && this.coordinate.y == pumpCoordinateY && !pump.getIsPunctured()) {
            pump.setInputEndOfPipe(endOfPipe);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Changes the output pipe of the specified pump.
     *
     * @param pump the pump to change the output pipe for
     * @param endOfPipe the new output end of pipe
     * @return true if the output pipe is successfully changed, false otherwise
     */
    protected boolean changeOutputPipe(Pump pump, EndOfPipe endOfPipe) {
        printMethodName("changeInputPipe");
        int pumpCoordinateX = pump.getCoordinate().x;
        int pumpCoordinateY = pump.getCoordinate().y;
        if(this.coordinate.x == pumpCoordinateX && this.coordinate.y == pumpCoordinateY && !pump.getIsPunctured()) {
            pump.setOutputEndOfPipe(endOfPipe);
            return true;
        } else {
            return false;
        }
    }

}