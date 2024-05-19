import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Represents a Spring, which is an ActiveElement in the pipe system.
 */
public class Spring extends ActiveElement {
    /**
     * The speed of the water flow.
     */
    int speed;

    /**
     * Indication of whether the spring is connected to the pipe system or not.
     */
    boolean active;

    private JLabel springLabel;

    /**
     * Constructs a new Spring object with default values.
     * The speed is set to 1 and the spring is initially inactive.
     */
    public Spring() {
        super();
        speed = 1;
        active = false;
        try {
            BufferedImage image = ImageIO.read(new File("res/spring.png"));
            ImageIcon cisternIcon = new ImageIcon(image);
            springLabel = new JLabel(cisternIcon);
            springLabel.setBackground(new Color(0,0,0,0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets the activation status of the spring.
     * @param active true if the spring is active, false otherwise
     */
    public void setIsActive(boolean active) {
        this.active = active;
    }

    /**
     * Returns the activation status of the spring.
     * @return true if the spring is active, false otherwise
     */
    public boolean getIsActive() {
        return active;
    }
    public JLabel getSpringLabel(){
        return springLabel;
    }
}