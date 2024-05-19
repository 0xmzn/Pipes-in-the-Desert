import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Represents a cistern that manufactures and stores elements.
 */
public class Cistern extends ActiveElement {
    /**
     * A <Pipe> or <Pump> object, which implements Element interface, that was
     * manufactured by a <Cistern> object
     * and is currently located at the <Cistern>.
     */
    private Pipe inventoryPipe;
    private Pump inventoryPump;
    private Timer timer;
    private boolean isManufacturing;
    private JLabel cisternLabel;

    public Cistern() {
        this.inventoryPipe = null;
        this.inventoryPump = null;
        this.timer = new Timer();
        this.isManufacturing = false;
        try {
            BufferedImage image = ImageIO.read(new File("res/Cistern.png"));
            ImageIcon cisternIcon = new ImageIcon(image);
            cisternLabel = new JLabel(cisternIcon);
            cisternLabel.setOpaque(false);
            cisternLabel.setBackground(new Color(0,0,0,0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Manufactures new elements in the cistern.
     * The method checks whether the inventoryElement attribute is instantiated or
     * not.
     * Also instantiates new <Pipe> or <Pump> objects separated by random time
     * intervals.
     */
    public void manufactureElement() {
        if(isManufacturing){
            System.out.println("Manufacturing is already in progress. Cannot manufacture another element.\n");
            return;
        }

        if (inventoryPipe != null || inventoryPump != null) {
            System.out.println("Inventory already contains an element. Cannot manufacture another element.\n");
            return;
        }

        Random random = new Random();
        int randomNumber = random.nextInt(2);
        if(randomNumber==0) {
            System.out.println("Manufacturing pipe...");

            Point cisternCoordinate = this.getCoordinate();
            inventoryPipe = new Pipe(new EndOfPipe(cisternCoordinate), new EndOfPipe(new Point((int)cisternCoordinate.getX()+1, (int)cisternCoordinate.getY())));

            scheduleManufactureCompletion(15);
        }
        else {
            System.out.println("Manufacturing pipe...");
            inventoryPump = new Pump();
            scheduleManufactureCompletion(15);
        }
    }

    private void scheduleManufactureCompletion(int seconds){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Manufacturing completed.\n");
                isManufacturing = false;
            }
        }, seconds*1000);
        isManufacturing = true;
    }

    public Pipe getInventoryPipe() {
        return inventoryPipe;
    }

    public Pump getInventoryPump() {
        return inventoryPump;
    }

    public JLabel getLabel(){
        return cisternLabel;
    }
}
