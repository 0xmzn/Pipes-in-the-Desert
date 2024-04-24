/**
 * Represents one of the two ends of a pipe in the pipe system.
 */
public class EndOfPipe {
    /**
     * The coordinate of the end of the pipe in the game grid.
     */
    private Point coordinate;

    /**
     * The element currently connected to the pipe end. Null if no element is connected.
     */
    private ActiveElement connectedActiveElement;

    /**
     * Connects the pipe to the specified active element.
     * @param activeElement The active element to connect to the pipe.
     */
    public void connectPipe(ActiveElement activeElement) {
        //TODO: update with prototype version
        this.connectedActiveElement = activeElement;
    }

    /**
     * Disconnects the pipe by setting the connectedActiveElement to null.
     */
    public void disconnectPipe() {
        //TODO: update with prototype version
        this.connectedActiveElement = null;
    }

    /**
     * Returns the location coordinate of the end of the pipe.
     * @return The location coordinate.
     */
    public Point getLocation() {
        return coordinate;
    }

    /**
     * Sets the location of the end of the pipe.
     * @param x The x-coordinate of the location.
     * @param y The y-coordinate of the location.
     */
    public void setLocation(int x, int y) {
        coordinate = new Point(x, y);
    }
}
