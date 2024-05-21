import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * The PumpView class represents a view component for a pump in a graphical user interface.
 * It displays an image of a pump based on its type and coordinates.
 */
public class PumpView {
    private JLabel pumpLabel;
    private JLabel brokenLabel;
    private int pumpType;
    private Point coordinate;

    /**
     * Constructs a PumpView object with the specified coordinates.
     *
     * @param coordinate the coordinates of the pump
     */
    public PumpView(Point coordinate) {
        this.coordinate = coordinate;
        pumpLabel = new JLabel();
        Random random = new Random();
        pumpType = random.nextInt(3) + 1;
        initializePumpLabel(pumpType);
    }

    /**
     * Initializes the pump label based on the pump type.
     *
     * @param pumpType the type of the pump
     */
    private void initializePumpLabel(int pumpType) {
        try {
            BufferedImage image = ImageIO.read(new File("res/Pump2.png"));
            ImageIcon pumpIcon = new ImageIcon(image);
            pumpLabel = new JLabel(pumpIcon);
            pumpLabel.setBackground(new Color(0, 0, 0, 0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SetLabel(false);
    }

    public void SetLabel(boolean isBroken){
        try {
            BufferedImage image;
            switch (pumpType) {
                case 1:
                    if (isBroken)
                        image = ImageIO.read(new File("res/Pump2Broken.png"));
                    else
                        image = ImageIO.read(new File("res/Pump2.png"));
                    break;
                case 2:
                    if (isBroken)
                        image = ImageIO.read(new File("res/Pump3Broken.png"));
                    else
                        image = ImageIO.read(new File("res/Pump3.png"));
                    break;
                case 3:
                    if (isBroken)
                        image = ImageIO.read(new File("res/Pump4Broken.png"));
                    else
                        image = ImageIO.read(new File("res/Pump4.png"));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid pump type");
            }
            ImageIcon pumpIcon = new ImageIcon(image);
            pumpLabel.setIcon(pumpIcon);
            pumpLabel.setBackground(new Color(0, 0, 0, 0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Draws the pump at the specified coordinates.
     *
     * @param coordinate the coordinates to draw the pump
     * @return the JLabel representing the pump
     */
    public JLabel drawPump(Point coordinate) {
        pumpLabel.setBounds(coordinate.x, coordinate.y, 100, 100);
        pumpLabel.setVisible(true);
        pumpLabel.repaint();
        return pumpLabel;
    }

    /**
     * Returns the label of the pump.
     *
     * @return the JLabel representing the pump
     */
    public JLabel getLabel() {
        printMethodName("getPumpLabel()");
        return pumpLabel;
    }

    private static void printMethodName(String methodName) {
        System.out.println("\n------------------------------------------------------------");
        System.out.println(methodName + " method of the Controller class is called.");
        System.out.println("------------------------------------------------------------\n");
    }
}
