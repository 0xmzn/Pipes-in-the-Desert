import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Represents the view component for the Saboteur in the game.
 * Displays an image of the Saboteur character.
 */
public class SaboteurView {
    private JLabel saboteurLabel;

    /**
     * Constructs a SaboteurView object.
     * Loads the image of the Saboteur character and creates a JLabel to display it.
     */
    public SaboteurView() {
        try {
            BufferedImage image = ImageIO.read(new File("res/saboteur2.png"));
            ImageIcon plumberIcon = new ImageIcon(image);
            saboteurLabel = new JLabel(plumberIcon);
            saboteurLabel.setBackground(new Color(0,0,0,0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets the JLabel component that displays the Saboteur image.
     * 
     * @return the JLabel component displaying the Saboteur image
     */
    public JLabel getLabel() {
        return saboteurLabel;
    }
}
