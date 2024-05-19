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
    private Point coordinate;
    private boolean isManufacturing;
    private JLabel cisternLabel;
    private JLabel pumpLabelPlace;
    private JLabel pipeLabelPlace;
    private static int idCounter = 0;

    public Cistern() {
        this.inventoryPipe = null;
        this.inventoryPump = null;
        this.timer = new Timer();
        this.isManufacturing = false;
        try {
            BufferedImage image = ImageIO.read(new File("res/Cistern.png"));
            ImageIcon cisternIcon = new ImageIcon(image);
            cisternLabel = new JLabel(cisternIcon);
            cisternLabel.setBackground(new Color(0,0,0,0));

            //Pump
            pumpLabelPlace = new JLabel();
            pumpLabelPlace.setBackground(new Color(0,0,0,0));

            //Pipe
            pipeLabelPlace = new JLabel();
            pipeLabelPlace.setBackground(new Color(0,0,0,0));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        manufactureElement();
    }

    /**
     * Manufactures new elements in the cistern.
     * The method checks whether the inventoryElement attribute is instantiated or
     * not.
     * Also instantiates new <Pipe> or <Pump> objects separated by random time
     * intervals.
     */
    public void manufactureElement() {
        printMethodName("manufactureElement");
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
            inventoryPipe = new Pipe();
            inventoryPipe.setID(idCounter++);
            schedulePipeManufactureCompletion(5);
        }
        else {
            System.out.println("Manufacturing pump...");
            inventoryPump = new Pump();
            inventoryPump.setID(idCounter++);
            schedulePumpManufactureCompletion(10);
        }
    }

    private void schedulePipeManufactureCompletion(int seconds){
        printMethodName("schedulePipeManufactureCompletion");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Manufacturing of pipe completed.\n");
                isManufacturing = false;
                updateCisternLabelWithPipe();
            }
        }, seconds * 1000);
        isManufacturing = true;
    }

    public void schedulePumpManufactureCompletion(int seconds){
        printMethodName("schedulePumpManufactureCompletion");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Manufacturing of pump completed.\n");
                isManufacturing = false;
                updateCisternLabelWithPump();
            }
        }, seconds * 1000);
        isManufacturing = true;
    }

    private void updateCisternLabelWithPump() {
        if (inventoryPump != null) {
            System.out.println("Inventory is not NULL from updateCisternLabelWithPump");
            JLabel pumpLabel = inventoryPump.getPumpLabel();
            pumpLabelPlace.setIcon(pumpLabel.getIcon());
            pumpLabel.setBounds(100,100,100,100);
            pumpLabelPlace.repaint();
            System.out.println("Finished from updateCisternLabelWithPump");
        }else{
            System.out.println("Inventory NULL from updateCisternLabelWithPump");
        }
    }

    private void updateCisternLabelWithPipe(){
        if(inventoryPipe!=null){
            JLabel pipeLabel = inventoryPipe.getPipeLabel();
            pipeLabelPlace.setIcon(pipeLabel.getIcon());
            pipeLabel.setBounds(100,100,100,100);
            pipeLabelPlace.repaint();
        }
    }

    public Pipe getInventoryPipe() {
        return inventoryPipe;
    }

    public Pump getInventoryPump() {
        return inventoryPump;
    }

    public JLabel getCisternLabel(){
        return cisternLabel;
    }

    public JLabel getPumpPlaceLabel() {
        return pumpLabelPlace;
    }

    public JLabel getPipeLabelPlace(){
        return pipeLabelPlace;
    }
    private static void printMethodName(String methodName) {
        System.out.println("\n------------------------------------------------------------");
        System.out.println(methodName + " method of the Cistern class is called.");
        System.out.println("------------------------------------------------------------\n");
    }
    public Point getCoordinate(){
        return coordinate;
    }
    public void setCoordinate(Point coordinate){
        this.coordinate = coordinate;
    }
    public void takePump(){
        inventoryPump = null;
        manufactureElement();
    }
}
