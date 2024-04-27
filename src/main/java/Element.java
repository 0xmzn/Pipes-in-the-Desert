import java.util.List;
import java.awt.Point;

/**
 * Represents an element in the pipeline system. Can be pipe, pump,
 */
public interface Element {
    /**
     * x and y coordinates of the element. Inbuilt java class that defines the
     * coordinates in the game grid.
     */

    Point getCoordinate();


    void setCoordinate(Point coordinate);

}
