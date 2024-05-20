import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CisternView {
    private JLabel cisternLabel;

    public CisternView(){
        try {
            BufferedImage image = ImageIO.read(new File("res/Cistern.png"));
            ImageIcon cisternIcon = new ImageIcon(image);
            cisternLabel = new JLabel(cisternIcon);
            cisternLabel.setBackground(new Color(0,0,0,0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public JLabel getLabel() {
        return cisternLabel;
    }
}
