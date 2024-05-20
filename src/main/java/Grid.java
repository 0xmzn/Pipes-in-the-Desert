import java.awt.Point;

public class Grid {
    final static private int numRows = 10;
    final static private int numColumns = 10;

    private static Element[][] elementsGrid = new Element[numRows][numColumns];

    public static Element[][] getElementsGrid() {
        return elementsGrid;
    }

    public static boolean setElement(final Point targetCoordinate, Element newElement) {
        if (elementsGrid[targetCoordinate.x][targetCoordinate.y] == null) {
            elementsGrid[targetCoordinate.x][targetCoordinate.y] = newElement;
            return true;
        } else {
            System.out.println("The target coordinate is not empty!");
            return false;
        }
    }
}