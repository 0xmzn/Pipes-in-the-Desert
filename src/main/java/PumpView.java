import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class PumpView {
    private JLabel pumpLabel;
    private int pumpType;
    private Point coordinate;

    public PumpView(Point coordinate) {
        this.coordinate = coordinate;
        pumpLabel = new JLabel();
        Random random = new Random();
        pumpType = random.nextInt(3)+1;
        initializePumpLabel(pumpType);
    }

    private void initializePumpLabel(int pumpType){
        try{
            BufferedImage image;
            switch (pumpType) {
                case 1:
                    image = ImageIO.read(new File("res/Pump2.png"));
                    break;
                case 2:
                    image = ImageIO.read(new File("res/Pump3.png"));
                    break;
                case 3:
                    image = ImageIO.read(new File("res/Pump4.png"));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid pump type");
            }
            ImageIcon pumpIcon = new ImageIcon(image);
            pumpLabel = new JLabel(pumpIcon);
            pumpLabel.setBackground(new Color(0, 0, 0, 0));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void drawPump(Point coordinate){
        pumpLabel.setLocation(coordinate.x, coordinate.y);
        pumpLabel.setVisible(true);
        pumpLabel.repaint();
    }

    public JLabel getLabel(){
        printMethodName("getPumpLabel()");
        return pumpLabel;
    }

    private static void printMethodName(String methodName) {
        System.out.println("\n------------------------------------------------------------");
        System.out.println(methodName + " method of the Controller class is called.");
        System.out.println("------------------------------------------------------------\n");
    }
}
