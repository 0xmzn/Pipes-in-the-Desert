import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PlumberView {
    private JLabel plumberLabel;

    public PlumberView(){
        try {
            BufferedImage image = ImageIO.read(new File("res/plumber3.png"));
            ImageIcon plumberIcon = new ImageIcon(image);
            plumberLabel = new JLabel(plumberIcon);
            plumberLabel.setBackground(new Color(0,0,0,0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public JLabel getLabel() {
        return plumberLabel;
    }
}
