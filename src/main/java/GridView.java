import javax.swing.*;
import java.awt.*;

/**
 * Represents a grid view for a game.
 * Provides methods to draw and update the grid view.
 */
public class GridView {
    private JPanel gridPanel;

    final private int numCellsX = 10;
    final private int numCellsY = 10;
    // coordinates assigned to each grid cell
    private Point[][] cellCoordinatesGrid = new Point[numCellsX][numCellsY];

    final private int gridWidth;
    final private int gridHeight;

    /**
     * Constructs a GridView object with the specified grid width and height.
     *
     * @param gridWidth  the width of the grid
     * @param gridHeight the height of the grid
     */
    public GridView(int gridWidth, int gridHeight) {
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;

        // set coordinates for each grid cell
        for (int i = 0; i < numCellsX; i++) {
            for (int j = 0; j < numCellsY; j++) {
                cellCoordinatesGrid[i][j] = new Point(i * gridWidth / numCellsX, j * gridHeight / numCellsY);
            }
        }

        drawInitialGridView();
    }

    /**
     * Draws the initial grid view.
     * This method is called internally during the construction of the GridView object.
     */
    private void drawInitialGridView() {
        gridPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setStroke(new BasicStroke(2));

                // drawing grid cells
                int cellWidth = gridWidth / numCellsX;
                int cellHeight = gridHeight / numCellsY;
                for (int i = 0; i < numCellsX; i++) {
                    for (int j = 0; j < numCellsY; j++) {
                        Point cellCoordinates = cellCoordinatesGrid[i][j];
                        g2d.setColor(new Color(232, 140, 35, 200));  // light-brown
                        g2d.fillRect(cellCoordinates.x, cellCoordinates.y, cellWidth, cellHeight);
                        g2d.setColor(new Color(153, 88, 14, 200));  // dark-brown
                        g2d.drawRect(cellCoordinates.x, cellCoordinates.y, cellWidth, cellHeight);
                    }
                }
            }
        };
    }

    public void updateGridView() {

    }

    public JPanel getGridPanel() {
        return gridPanel;
    }
}