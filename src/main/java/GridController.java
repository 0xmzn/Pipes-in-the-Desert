import javax.swing.*;
import java.awt.Point;

public class GridController {
    static private Grid gridModel;
    static private GridView gridView;

    public GridController(Grid gridModel, GridView gridView) {
        this.gridModel = gridModel;
        this.gridView = gridView;
    }

    public GridController(GridView gridView) {
        this.gridModel = new Grid();
        this.gridView = gridView;
    }

    static public JPanel getGridPanel() {
        return gridView.getGridPanel();
    }

    public static Element[][] getElementsGrid() {
        return gridModel.getElementsGrid();
    }

    public static boolean setElement(Point targetCoordinate, Element newElement) {
        return gridModel.setElement(targetCoordinate, newElement);
    }
}