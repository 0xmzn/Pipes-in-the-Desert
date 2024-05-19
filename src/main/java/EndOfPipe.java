import java.awt.*;

import java.awt.Point;

/**
 * Represents one of the two ends of a pipe in the pipe system.
 */
public class EndOfPipe implements Element{
    private EndOfPipe pairEndOfPipe;
    private int id;

    /**
     * The coordinate of the end of the pipe in the game grid.
     */
    private Point coordinate;

    /**
     * The element currently connected to the pipe end. Null if no element is
     * connected.
     */
    private ActiveElement connectedActiveElement;

    public EndOfPipe(Point coordinate) {
        this.coordinate = coordinate;
    }

    public EndOfPipe getPairEndOfPipe() {
        return pairEndOfPipe;
    }

    public void setPairEndOfPipe(EndOfPipe endOfPipe) {
        pairEndOfPipe = endOfPipe;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    /**
     * Returns the coordinate of the end of the pipe.
     * 
     * @return The coordinate.
     */
    public Point getCoordinate() {
        return coordinate;
    }

    /**
     * Sets the coordinate of the end of the pipe.
     */
    public void setCoordinate(Point newCoordinate) {
        coordinate = newCoordinate;
    }

    @Override
    public boolean isWalkable() {
        return false;
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
