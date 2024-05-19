import javax.swing.*;
import java.awt.*;

public class GridView {
    private JPanel gridPanel;

    final private int numCellsX = 12;
    final private int numCellsY = 10;
    // coordinates assigned to each grid cell
    private Point[][] cellCoordinatesGrid = new Point[numCellsX][numCellsY];

    final private int gridWidth;
    final private int gridHeight;

    public GridView(int gridWidth, int gridHeight) {
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;

        // set coordinates for each grid cell
        for (int i = 0; i < numCellsX; i++) {
            for (int j = 0; j < numCellsY; j++) {
                cellCoordinatesGrid[i][j] = new Point(i *  gridWidth / numCellsX, j * gridHeight / numCellsY);
            }
        }

        drawInitialGridView();
    }

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
                        g2d.setColor(new Color(232, 140, 35,200));  // light-brown
                        g2d.fillRect(cellCoordinates.x, cellCoordinates.y, cellWidth, cellHeight);
                        g2d.setColor(new Color(153, 88, 14,200));  // dark-brown
                        g2d.drawRect(cellCoordinates.x, cellCoordinates.y, cellWidth, cellHeight);
                    }
                }

                // TODO: add initial characters, i.e. saboteurs & plumbers, and objects, i.e. springs & cisterns
            }
        };
    }

    public void updateGridView() {

    }

    public JPanel getGridPanel() {
        return gridPanel;
    }
}