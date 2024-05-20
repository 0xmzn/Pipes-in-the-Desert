import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Represents an active element in the pipe system.
 */
public class ActiveElement implements Element {
    /**
     * Keeps track of the coordinates of the pipe on the game grid.
     */
    protected Point coordinate;
    int id;
    /**
     * List of currently connected ends of pipes to this active element.
     */
    protected List<EndOfPipe> connectedEndsOfPipes;

    public ActiveElement() {
        this.coordinate = new Point();
        this.connectedEndsOfPipes = new ArrayList<EndOfPipe>();
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
     * Gets the coordinate of the active element.
     *
     * @return the coordinate of the active element
     */
    @Override
    public Point getCoordinate() {
        return coordinate;
    }

    /**
     * Sets the coordinate of the active element.
     *
     * @param coordinate the new coordinate of the active element
     */
    @Override
    public void setCoordinate(Point coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public boolean isWalkable() {
        return true;
    }

    /**
     * Connects the active element to an end of a pipe.
     *
     * @param endOfPipe the end of the pipe to connect to
     * @return true if the connection was successful, false otherwise
     */
    public boolean connect(EndOfPipe endOfPipe) {
        connectedEndsOfPipes.add(endOfPipe);
        return false;
    }

    /**
     * Disconnects the active element from an end of a pipe.
     *
     * @param endOfPipe the end of the pipe to disconnect from
     * @return true if the disconnection was successful, false otherwise
     */
    public boolean disconnect(EndOfPipe endOfPipe) {
        if (connectedEndsOfPipes.contains(endOfPipe)) {
            connectedEndsOfPipes.remove(endOfPipe);
            return true;
        }
        return false;
    }
}
