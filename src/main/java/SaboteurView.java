import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SaboteurView {
    private JLabel saboteurLabel;

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

    public JLabel getLabel() {
        return saboteurLabel;
    }
}
