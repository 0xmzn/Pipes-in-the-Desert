import javax.imageio.ImageIO;
import javax.swing.*;

import static java.lang.System.out;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

/**
 * The Plumber class extends the Player class and implements Plumber-specific
 * functionality.
 */
public class Plumber extends Player {
    /**
     * The inventory of the Plumber - picked-up pipe or a pump.
     */
    Element inventory;
    private Point currentCoordinate;
    private PlumberView plumberView;
    /**
     * Constructs a new Plumber object.
     * It calls the constructor of the superclass (Player) using the super()
     * keyword.
     */
    public Plumber(Point coordinate) {
        super(coordinate);
        currentCoordinate = coordinate;
        this.plumberView = new PlumberView();
        inventory = null;
    }

    @Override
    public void move(int dx, int dy) {
        currentCoordinate.translate(dx,dy);
        movePlumberLabel(currentCoordinate.x,currentCoordinate.y);
    }

    /**
     * Picks up a pipe end from the current location.
     * Checks for all preconditions before picking up the pipe end: player type,
     * inventory state, and player location.
     * If any of the conditions are not met, the method returns false without
     * performing any action.
     * If the pipe end is connected to any active element, it disconnects.
     * If the other end of the pipe is also disconnected, it prints a failure
     * message and returns false.
     * Otherwise, it prints a success message and returns true.
     * 
     * @param endOfPipe the pipe end to be picked up
     * @return true if the pipe end is picked up successfully, false otherwise
     */
    public boolean pickUpPipeEnd(EndOfPipe endOfPipe) {
        if (endOfPipe.canBePickedUp()) {
            inventory = endOfPipe;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Picks up a pump from the current location.
     * Checks for all preconditions before picking up the pump: player type,
     * inventory state, player location, and pump availability.
     * If any of the conditions are not met, the method returns false without
     * performing any action.
     * Otherwise, it prints a success message and returns true.
     * 
     *
     * @return true if the pump is picked up successfully, false otherwise
     */
    public boolean pickUpPump(Cistern cistern) {
        printMethodName("pickUpPump");

        if (cistern.getInventoryPump() == null) {
            out.println("There is no pump in the cistern!");
            return false;
        }else if (inventory != null) {
            out.println("Your inventory is full!");
            return false;
        } else {
            inventory = cistern.getInventoryPump();
            cistern.takePump();
            out.println("Plumber has picked up a pump from the cistern!");
            return true;
        }

    }

    /**
     * Installs a pump into the system.
     * Checks for all preconditions before installing the pump: player type,
     * inventory state.
     * If any of the conditions are not met, the method returns without performing
     * any action.
     * Otherwise, it performs the following steps:
     * - Sets the location.
     * - Cuts the pipe.
     * - Connects the pipeEnds to the Pump.
     * - Changes the input pipe.
     * - Changes the output pipe.
     * - Removes the item from the inventory.
     * Finally, it prints a success message.
     */
    public boolean installPump(Point targetCoordinate) {
        // unnecessary duplication of logic, instead of installPump we could have install Element
        printMethodName("installPump");

        if (inventory instanceof Pump) {
            Grid.setElement(targetCoordinate, inventory);
            Controller.placePipeorPump(Controller.convertToPixels(targetCoordinate), inventory);
            inventory = null;  // empty the inventory, the actual instantiate will happen in the Controller
            out.println("The pump has been installed in "+ targetCoordinate.x + " x " + targetCoordinate.y);
            return true;
        } else if (inventory instanceof EndOfPipe) {
            out.println("You have EndOfPipe in your inventory!");
            return false;
        } else {
            // i.e. inventoru is null
            out.println("There is nothing in your inventory!");
            return false;
        }
    }

    /**
     * Places a pipe end into the system.
     * Checks for all preconditions before placing the pipe end: player type,
     * inventory state.
     * If any of the conditions are not met, the method returns without performing
     * any action.
     * Otherwise, it asks the user if there is an active element to connect the pipe
     * to.
     * If the user chooses to connect, it connects the pipe.
     * If the user chooses to place the pipe end towards the desert, it prints a
     * success message.
     * *
     * 
     *
     * @return true if the pipe end is placed successfully, false otherwise
     */
    public boolean placePipeEnd(ActiveElement targetActiveElement) {
        // we are sure that targetCoordinate is empty
        // if it is not, it should fail on the stage of the Controller

        printMethodName("placePipeEnd");

        if (inventory instanceof EndOfPipe) {
            EndOfPipe currentEndOfPipe = (EndOfPipe)inventory;

            if (targetActiveElement == null) {
                currentEndOfPipe.disconnectPipe();
            } else {
                connect(targetActiveElement);
            }

            inventory = null;  // empty the inventory, the actual instantiate will happen in the Controller
            return true;
        } else if (inventory instanceof Pump) {
            out.println("You have Pump in your inventory!");
            return false;
        } else {
            // i.e. inventoru is null
            out.println("There is nothing in your inventory!");
            return false;
        }
    }

    /**
     * Connects a pipe end to an active element.
     * Checks for all preconditions before picking up the pipe end: player type,
     * inventory;
     * If any of the conditions are not met, the method returns without performing
     * any action.
     * If the pipe end is already connected to any other active element, it fails.
     * Otherwises it removes the item from the inventory and prints a success
     * message.
     * 
     * @param activeElement the active element to connect the pipe end to
     * @return true if the pipe end is connected successfully, false otherwise
     */
    public boolean connect(ActiveElement activeElement) {
        printMethodName("connect");

        return activeElement.connect((EndOfPipe)inventory);
    }

    /**
     * Disconnects a pipe end from an active element.
     * If the pipe end can be disconnected, it adds the item to the inventory and
     * prints a success message.
     * 
     * @param activeElement the active element to disconnect the pipe end from
     * @return true if the pipe end is disconnected successfully, false otherwise
     */
    public boolean disconnect(ActiveElement activeElement) {
        // TODO: update with prototype version
        printMethodName("disconnect");

        return activeElement.disconnect((EndOfPipe)inventory);
    }

    /**
     * Fixes a punctured pipe or pump.
     * Checks the precondition before fixing the element: player type, element
     * punctured state, and location.
     * If any of the conditions are not met, the method returns false without
     * performing any action.
     * Otherwise, it sets the punctured state of the element to false and returns
     * true.
     * 
     * @param element the element (pipe or pump) to be fixed
     * @return true if the element is fixed successfully, false otherwise
     */
    public boolean fixElement(Element element) {
        printMethodName("fixElement()");

        if (element instanceof Pipe) {
            Pipe currentPipe = (Pipe)element;
            
            if (!currentPipe.getIsPunctured()) {
                out.println("Pipe is not punctured!");
                return false;
            } else {
                currentPipe.setIsPunctured(false);
                out.println("Pipe is fixed!");
                return true;
            }
        } else if (element instanceof Pump) {
            Pump currentPump = (Pump)element;
            
            if (!currentPump.getIsPunctured()) {
                out.println("Pump is not punctured!");
                return false;
            } else {
                currentPump.setIsPunctured(false);
                out.println("Pump is fixed!");
                return true;
            }
        } else {
            out.println("Elements outside of the scope of Pump and Pipe cannot be fixed!");
            return false;
        } 
    }

    /**
     * Checks if the inventory is free and returns corresponding response.
     * 
     * @return true if the inventory is free, false otherwise.
     */
    public Element checkInventory() {
        printMethodName("checkInventory()");

        return inventory;
    }

    private Element takeFromInventory() {
        printMethodName("checkInventory()");

        Element pickedUpElement = inventory;
        inventory = null;
        return pickedUpElement;
    }

    public void placeToInventory(Element newElement) {
        inventory = newElement;
    }

    /**
     * Legacy code. DON'T USE IT!
     */
    private void removeInventory() {
        printMethodName("removeInventory()");

        inventory = null;
    }

    public void movePlumberLabel(int x, int y){
        getPlumberLabel().setLocation(x,y);
    }

    public JLabel getPlumberLabel(){
        return plumberView.getLabel();
    }
}
