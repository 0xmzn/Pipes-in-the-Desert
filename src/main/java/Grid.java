import java.awt.Point;

public class Grid {
    final private int numRows = 10;
    final private int numColumns = 12;

    private Element[][] elementsGrid = new Element[numRows][numColumns];

    public Element[][] getElementsGrid() {
        return elementsGrid;
    }

    public boolean setElement(final Point targetCoordinate, Element newElement) {
        if (elementsGrid[targetCoordinate.x][targetCoordinate.y] == null) {
            elementsGrid[targetCoordinate.x][targetCoordinate.y] = newElement;
            return true;
        } else {
            return false;
        }
    }
}
