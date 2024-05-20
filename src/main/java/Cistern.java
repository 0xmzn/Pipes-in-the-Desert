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
/**
 * Represents a cistern, which is an active element in the system.
 * It can manufacture and store pipes and pumps.
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
    private JLabel pumpLabelPlace;
    private JLabel pipeLabelPlace;
    private static int idCounter = 0;

    private CisternView cisternView;

    /**
     * Constructs a new Cistern object.
     * Initializes the inventoryPipe, inventoryPump, timer, isManufacturing, cisternView, pumpLabelPlace, and pipeLabelPlace.
     * Calls the manufactureElement method.
     */
    public Cistern() {
        this.inventoryPipe = null;
        this.inventoryPump = null;
        this.timer = new Timer();
        this.isManufacturing = false;
        this.cisternView = new CisternView();
        //Pump
        pumpLabelPlace = new JLabel();
        pumpLabelPlace.setBackground(new Color(0,0,0,0));

        //Pipe
        pipeLabelPlace = new JLabel();
        pipeLabelPlace.setBackground(new Color(0,0,0,0));
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
            Point pipeCoordinates = new Point(0, (int)Controller.convertCoordinates(this.getCoordinate()).getY()+1);
            inventoryPipe = new Pipe();
            inventoryPipe.setID(idCounter++);
            Grid.setElement(pipeCoordinates, inventoryPipe);
            schedulePipeManufactureCompletion(5);
        }
        else {
            System.out.println("Manufacturing pump...");
            inventoryPump = new Pump();
            inventoryPump.setID(idCounter++);
            Point gridCoordinate = new Point(0, (int)Controller.convertCoordinates(this.getCoordinate()).getY()+1);
            Grid.setElement(gridCoordinate, inventoryPump);
            schedulePumpManufactureCompletion(10);
        }
    }

    /**
     * Schedules the completion of pipe manufacture after a specified number of seconds.
     *
     * @param seconds the number of seconds after which the pipe manufacture should be completed
     */
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

    /**
     * Schedules the completion of pump manufacturing after a specified number of seconds.
     *
     * @param seconds the number of seconds after which the pump manufacturing should be completed
     */
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

    
    /**
     * Updates the cistern label with the pump's label.
     * If the inventory pump is not null, it sets the icon of the pump label
     * to the cistern's pump label place, and repaints the pump label place.
     */
    private void updateCisternLabelWithPump() {
        printMethodName("updateCisternLabel");
        if (inventoryPump != null) {
            JLabel pumpLabel = inventoryPump.getPumpLabel();
            pumpLabelPlace.setIcon(pumpLabel.getIcon());
            pumpLabel.setBounds(100,100,100,100);
            pumpLabelPlace.repaint();
        }
    }

    /**
     * Updates the cistern label with the current pipe.
     * If there is a pipe in the inventory, this method sets the icon of the pipe label
     * and repaints the pipe label place.
     */
    private void updateCisternLabelWithPipe(){
        printMethodName("updateCisternLabelWithPipe");
        if(inventoryPipe!=null){
            JLabel pipeLabel = inventoryPipe.getPipeLabel();
            pipeLabelPlace.setIcon(pipeLabel.getIcon());
            pipeLabel.setBounds(100,100,100,100);
            pipeLabelPlace.repaint();
        }
    }

    /**
     * Gets the inventory pipe.
     *
     * @return the inventory pipe
     */
    public Pipe getInventoryPipe() {
        return inventoryPipe;
    }

    /**
     * Gets the inventory pump.
     *
     * @return the inventory pump
     */
    public Pump getInventoryPump() {
        return inventoryPump;
    }

    /**
     * Gets the cistern label.
     *
     * @return the cistern label
     */
    public JLabel getCisternLabel(){
        return cisternView.getLabel();
    }

    /**
     * Gets the pump place label.
     *
     * @return the pump place label
     */
    public JLabel getPumpPlaceLabel() {
        printMethodName("getPumpLabel");
        return pumpLabelPlace;
    }

    /**
     * Gets the pipe label place.
     *
     * @return the pipe label place
     */
    public JLabel getPipeLabelPlace(){
        printMethodName("getPipeLabelPlace");
        return pipeLabelPlace;
    }

    private static void printMethodName(String methodName) {
        System.out.println("\n------------------------------------------------------------");
        System.out.println(methodName + " method of the Controller class is called.");
        System.out.println("------------------------------------------------------------\n");
    }

    /**
     * Takes the pump from the cistern.
     */
    public void takePump(){
        printMethodName("takePump");
        inventoryPump = null;
        pumpLabelPlace.setIcon(null);
        pumpLabelPlace.repaint();
    }
}
