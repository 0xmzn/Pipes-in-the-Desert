import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a pipe.
 */
public class Pipe implements Element {
    /**
     * Keeps track of the coordinates of the pipe on the game grid.
     */
    private Point coordinate;
    private int id;

    /**
     * Keeps track of whether the pipe is punctured or not.
     */
    private boolean isPunctured;

    /**
     * Keeps track of whether water is flowing through the pipe or not.
     */
    private boolean isWaterFlowing;
    private JLabel pipeLabel;
    /**
     * Stores 2 <EndOfPipe> elements referring to both sides of the pipe.
     */
    private EndOfPipe pipeEnd1;
    private EndOfPipe pipeEnd2;

    private PipeView pipeView;

    public Pipe() {
        isPunctured = false;
        isWaterFlowing = false;
        pipeEnd1 = new EndOfPipe(coordinate);
        pipeEnd2 = new EndOfPipe(coordinate);
        initializePipeLabel();

    }

    public void initializePipeLabel(){
        printMethodName("initializePipeLabel");

        BufferedImage image;
        try{
            image = ImageIO.read(new File("res/pipe.png"));
            ImageIcon pumpIcon = new ImageIcon(image);
            pipeLabel = new JLabel(pumpIcon);
            pipeLabel.setBackground(new Color(0, 0, 0, 0));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        setLabel();
    }

    private void setLabel(){
        BufferedImage image;
        if(!isPunctured){
            System.out.println("NOT PUNCTURED PIPE");
            try{
                image = ImageIO.read(new File("res/pipe.png"));
                ImageIcon pumpIcon = new ImageIcon(image);
                pipeLabel.setIcon(pumpIcon);
                pipeLabel.setBackground(new Color(0, 0, 0, 0));
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            System.out.println("PUNCTURED PIPE");
            try{
                image = ImageIO.read(new File("res/leakingPipe.png"));
                ImageIcon pumpIcon = new ImageIcon(image);
                pipeLabel.setIcon(pumpIcon);
                pipeLabel.setBackground(new Color(0, 0, 0, 0));
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        pipeLabel.repaint();
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public Point getCoordinate() {
        return coordinate;
    }

    @Override
    public void setCoordinate(Point coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public boolean isWalkable() {
        return true;
    }


    @Override
    public boolean WaterGoing() {
        return !this.isPunctured;
    }

    /**
     * Returns the punctured state of the pipe.
     *
     * @return true if the pipe is punctured, false otherwise.
     */
    public boolean getIsPunctured() {
        return isPunctured;
    }

    /**
     * Sets the punctured state of the pipe.
     *
     * @param puncturedState the new punctured state of the pipe.
     */
    public void setIsPunctured(boolean puncturedState) {
        printMethodName("setIsPunctured()");
        isPunctured = puncturedState;
        setLabel();
    }

    /**
     * Returns the water flowing state of the pipe.
     *
     * @return true if water is flowing through the pipe, false otherwise.
     */
    public boolean getIsWaterFlowing() {
        return isWaterFlowing;
    }

    /**
     * Sets the water flowing state of the pipe.
     *
     * @param waterFlowingState the new water flowing state of the pipe.
     */
    public void setIsWaterFlowing(boolean waterFlowingState) {
        isWaterFlowing = waterFlowingState;
    }
    public JLabel getPipeLabel(){
        printMethodName("getPipeLabel()");
        return pipeLabel;
    }
//    public JLabel getPuncturedLabel(){
//
//    }
    private static void printMethodName(String methodName) {
        System.out.println("\n------------------------------------------------------------");
        System.out.println(methodName + " method of the Pipe class is called.");
        System.out.println("------------------------------------------------------------\n");
    }
}
