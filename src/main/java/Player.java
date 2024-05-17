import java.awt.Point;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * The abstract class representing a player in the game. Unites common methods
 * used by both Plumber and Saboteur.
 */
public abstract class Player implements KeyListener{

    public Point coordinate;

    private final int moveAmount = 10;

    /**
     * The possible steppable cells for the current player.
     */
    protected boolean[][] steppableCell;

    /**
     * Constructs a new Player object. Initializes the scanner to reuse in all
     * methods.
     */
    protected Player(Point coordinate) {
        this.coordinate = coordinate;
    }

    protected void move(char direction){
        switch (direction){
            case 'W':
                moveForward();
                break;
            case 'D':
                moveRight();
                break;
            case 'S':
                moveBack();
                break;
            case 'A':
                moveLeft();
                break;
            default:
                System.out.println("Invalid direction");
        }
    }

    private void moveForward(){
        coordinate.y+=moveAmount;
    }
    private void moveRight(){
        coordinate.x+=moveAmount;
    }
    private void moveLeft(){
        coordinate.x-=moveAmount;
    }
    private void moveBack(){
        coordinate.y-=moveAmount;
    }

    // KeyListener methods
    @Override
    public void keyPressed(KeyEvent e) {
        char keyChar = e.getKeyChar();
        move(keyChar);
        System.out.println("Player position: (" + coordinate.x + ", " + coordinate.y + ")");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // You can implement this method if needed
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // You can implement this method if needed
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