import java.awt.*;

import java.awt.Point;

/**
 * Represents one of the two ends of a pipe in the pipe system.
 */
public class EndOfPipe implements Element{
    private EndOfPipe pairEndOfPipe;

    /**
     * The coordinate of the end of the pipe in the game grid.
     */
    private Point coordinate;

    /**
     * The element currently connected to the pipe end. Null if no element is
     * connected.
     */
    private ActiveElement connectedActiveElement;

    public EndOfPipe(Point coordinate, EndOfPipe pairEndOfPipe) {
        this.coordinate = coordinate;
        this.pairEndOfPipe = pairEndOfPipe;
    }

    public EndOfPipe getPairEndOfPipe() {
        return pairEndOfPipe;
    }

    /**
     * Returns the coordinate coordinate of the end of the pipe.
     * 
     * @return The coordinate coordinate.
     */
    public Point getCoordinate() {
        return coordinate;
    }

    /**
     * Sets the coordinate of the end of the pipe.
     * 
     * @param x The x-coordinate of the coordinate.
     * @param y The y-coordinate of the coordinate.
     */
    public void setCoordinate(Point newCoordinate) {
        coordinate = newCoordinate;
    }

    public ActiveElement getActiveElement() {
        return connectedActiveElement;
    }

    /**
     * Connects the pipe to the specified active element.
     * 
     * @param activeElement The active element to connect to the pipe.
     */
    public void connectPipe(ActiveElement activeElement) {
        // TODO: update with prototype version
        this.connectedActiveElement = activeElement;
    }

    /**
     * Disconnects the pipe by setting the connectedActiveElement to null.
     */
    public void disconnectPipe() {
        // TODO: update with prototype version
        this.connectedActiveElement = null;
    }

    public boolean canBePickedUp() {
        return pairEndOfPipe.getActiveElement() != null;
    }
}
