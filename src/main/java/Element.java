import java.util.List;

/**
 * Represents an element in the pipeline system. Can be pipe, pump,
 */
public interface Element {
    /**
     * x and y coordinates of the element. Inbuilt java class that defines the
     * coordinates in the game grid.
     */
    protected Point coordinate;

    /**
     * Gets the x and y coordinates of the element.
     * @return the coordinate of the element
     */
    public Point getCoordinate() {
        return coordinate;
    }

    /**
     * Sets the x and y coordinates of the element.
     * @param coordinate the coordinate to set
     */
    public void setCoordinate(Point coordinate) {
        this.coordinate = coordinate;
    }

}
