import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Represents a pump in the system.
 */
public class Pump extends ActiveElement {
    private int waterInReservoir;

    /**
     * Stores the amount of water that can be stored in the reservoir.
     */
    private int reservoirCapacity = 10;

    /**
     * Stores the maximum number of pipes that can be connected (2 to 4).
     */
    private int maxPipes;

    /**
     * Keeps track of the pump's state.
     */
    private boolean isPunctured;

    /**
     * List of size maxPipes that stores a list of endOfPipe elements connectable to
     * the pump.
     */
    private List<EndOfPipe> connectedEndsOfPipes;

    /**
     * Keeps track of where the water is flowing from.
     */
    private EndOfPipe inputEndOfPipe;

    /**
     * Keeps track of where the water is flowing to.
     */
    private EndOfPipe outputEndOfPipe;

    private JLabel pumpLabel;
    private int pumpType;


    public Pump() {
        waterInReservoir = 0;
        connectedEndsOfPipes = new ArrayList<EndOfPipe>();
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

    /**
     * Returns the value of isPunctured.
     *
     * @return the value of isPunctured
     */
    public boolean getIsPunctured() {
        return isPunctured;
    }

    /**
     * Sets the value of isPunctured to the passed boolean value.
     *
     * @param isPunctured the new value of isPunctured
     */
    public void setIsPunctured(boolean isPunctured) {
        this.isPunctured = isPunctured;
    }

    /**
     * Adds the passed EndOfPipe element to the connectedEndsOfPipes list if the
     * maximum number of connected ends is not reached.
     * It returns true if the operation was successful and returns false otherwise.
     *
     * @param endOfPipe the EndOfPipe element to be added
     * @return true if the operation was successful, false otherwise
     */
    public boolean addEndOfPipe(EndOfPipe endOfPipe) {
        if (connectedEndsOfPipes.size() < maxPipes) {
            connectedEndsOfPipes.add(endOfPipe);
            return true;
        }
        return false;
    }

    /**
     * Sets the inputEndOfPipe attribute to the passed EndOfPipe argument.
     *
     * @param endOfPipe the EndOfPipe to set as the inputEndOfPipe
     * @return true if the operation was successful, false otherwise
     */
    public boolean setInputEndOfPipe(EndOfPipe endOfPipe) {
        this.inputEndOfPipe = endOfPipe;
        return true;
    }

    /**
     * Returns the inputEndOfPipe attribute.
     *
     * @return the inputEndOfPipe attribute
     */
    public EndOfPipe getInputEndOfPipe() {
        return inputEndOfPipe;
    }

    /**
     * Sets the outputEndOfPipe attribute to the passed EndOfPipe argument.
     *
     * @param endOfPipe the EndOfPipe to set as the outputEndOfPipe
     * @return true if the operation was successful, false otherwise
     */
    public boolean setOutputEndOfPipe(EndOfPipe endOfPipe) {
        this.outputEndOfPipe = endOfPipe;
        return true;
    }

    /**
     * Returns the outputEndOfPipe attribute.
     *
     * @return the outputEndOfPipe attribute
     */
    public EndOfPipe getOutputEndOfPipe() {
        return outputEndOfPipe;
    }

    /**
     * Fills the reservoir with water.
     */
    public void fillReservoir() {
        waterInReservoir++;
    }

    /**
     * Checks if the reservoir is full.
     *
     * @return true if the reservoir is full, false otherwise
     */
    public boolean isReservoirFull() {
        return waterInReservoir >= reservoirCapacity;
    }
    public JLabel getPumpLabel(){
        printMethodName("getPumpLabel()");
        return pumpLabel;
    }
    private static void printMethodName(String methodName) {
        System.out.println("\n------------------------------------------------------------");
        System.out.println(methodName + " method of the Controller class is called.");
        System.out.println("------------------------------------------------------------\n");
    }

}