import javax.swing.*;

/**
 * Is responsible for controlling the interaction between the Grid model and the GridView.
 * Provides methods to retrieve the grid panel and the elements grid.
 */
public class GridController {
    static private Grid gridModel;
    static private GridView gridView;

    /**
     * Constructs a GridController object with the specified Grid model and GridView.
     *
     * @param gridModel The Grid model to be associated with the controller.
     * @param gridView  The GridView to be associated with the controller.
     */
    public GridController(Grid gridModel, GridView gridView) {
        this.gridModel = gridModel;
        this.gridView = gridView;
    }

    /**
     * Constructs a GridController object with a new Grid model and the specified GridView.
     *
     * @param gridView The GridView to be associated with the controller.
     */
    public GridController(GridView gridView) {
        this.gridModel = new Grid();
        this.gridView = gridView;
    }

    /**
     * Returns the grid panel associated with the GridView.
     *
     * @return The grid panel.
     */
    static public JPanel getGridPanel() {
        return gridView.getGridPanel();
    }

    /**
     * Returns the elements grid from the Grid model.
     *
     * @return The elements grid.
     */
    public Element[][] getElementsGrid() {
        return gridModel.getElementsGrid();
    }
}