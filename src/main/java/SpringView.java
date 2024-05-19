import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpringView {
    private JLabel springLabel;

    public SpringView(JLabel springLabel) {
        this.springLabel = springLabel;
    }

    public SpringView() {
        try {
            BufferedImage image = ImageIO.read(new File("res/spring.png"));
            ImageIcon cisternIcon = new ImageIcon(image);
            springLabel = new JLabel(cisternIcon);
            springLabel.setOpaque(false);
            springLabel.setBackground(new Color(0,0,0,0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public JLabel getLabel(){
        return springLabel;
    }
}

