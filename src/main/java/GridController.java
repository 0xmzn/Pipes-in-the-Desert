import javax.swing.*;

public class GridController {
    static private Grid gridModel;
    static private GridView gridView;

    public GridController(Grid gridModel, GridView gridView) {
        this.gridModel = gridModel;
        this.gridView = gridView;
    }

    static public JPanel getGridPanel() {
        return gridView.getGridPanel();
    }
}
