import static java.lang.System.out;

import java.awt.Point;
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

    /**
     * Constructs a new Plumber object.
     * It calls the constructor of the superclass (Player) using the super()
     * keyword.
     */
    public Plumber() {
        super();
        inventory = null;
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
    public boolean pickUpPipeEnd(Pipe pipe, EndOfPipe endOfPipe) {
        printMethodName("pickUpPipeEnd");
        boolean isInventoryTaken = !this.isInventoryEmpty();
        boolean isPlumberNotStandingOnPipeEnd = this.getLocation() == endOfPipe.getLocation();
        boolean isEndOfPipeStuck = !pipe.canBePickedUp(endOfPipe);

        if (isInventoryTaken || isPlumberNotStandingOnPipeEnd || isEndOfPipeStuck) {
            out.println("The element failed to be picked up.");
            return false;
        }

        endOfPipe.setConnectedActiveElement(null);
        this.inventory = endOfPipe;
        out.println("The element picked up successfully!");
        return true;
    }

    /**
     * Picks up a pump from the current location.
     * Checks for all preconditions before picking up the pump: player type,
     * inventory state, player location, and pump availability.
     * If any of the conditions are not met, the method returns false without
     * performing any action.
     * Otherwise, it prints a success message and returns true.
     * 
     * @param cistern the cistern where the has to be picked up from
     * @return true if the pump is picked up successfully, false otherwise
     */

    public boolean pickUpPump(Cistern cistern){
        printMethodName("pickUpPump");

        boolean isInventoryTaken = !this.isInventoryEmpty();
        boolean isPlumberNotStandingOnCistern = this.getLocation() == cistern.getCoordinate();
        boolean isNoPumpManufactured = cistern.getInventoryElement() instanceof Pump;

        if ( isInventoryTaken || isPlumberNotStandingOnCistern ||  isNoPumpManufactured) {
            out.println("The element failed to be picked up.");
            return false;
        }

        this.inventory = cistern.getInventoryElement();
        cistern.setInventoryElement(null);
        out.println("Pump picked up successfully!");
        return true;
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
    public boolean installPump(Point coordinate) {
        printMethodName("installPump");

        out.println("You are trying to install a pump into the system...");
        if (!checkType("plumber") || !isInventoryEmpty()
                || !getLocation("pump") || !getManufacturedElement()) {
            return;
        }

        setLocation();
        cutPipe();
        connect(new Pump(), new EndOfPipe());
        changeInputPipe(new Pump, new EndOfPipe());
        changeOutputPipe(new Pump, new EndOfPipe());
        removeInventory();
        out.println("Pump installed successfully!");
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
     * @param endOfPipe the pipe end to be placed
     * @return true if the pipe end is placed successfully, false otherwise
     */
    public boolean placePipeEnd(EndOfPipe endOfPipe) {
        // TODO: update with prototype version
        printMethodName("placePipeEnd");

        out.println("You are trying to place a pipe end into the system...");
        if (!checkType("Plumber") || !isInventoryEmpty()) {
            return;
        }

        int shouldConnect = askQuestion("Is there an active element you would like to connect your pipe to?");
        if (shouldConnect == 1) {
            connect(inventory, endOfPipe);
        } else if (shouldConnect == 2) {
            out.println("Pipe end placed towards the desert successfully!");
        } else {
            out.println("Invalid command, operation failed");
            return;
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
     * @param endOfPipe     the pipe end to be connected
     * @return true if the pipe end is connected successfully, false otherwise
     */
    public boolean connect(ActiveElement activeElement, EndOfPipe endOfPipe) {
        // TODO: update with prototype version
        printMethodName("connect");

        out.println("You are trying to connect a pipe end to an active element...");

        int canConnect = askQuestion("Is there space to connect a new pipe end?");
        if (canConnect == 1) {
            removeInventory();
            out.println("Pipe end connected successfully!");
        } else if (canConnect == 2) {
            out.println("The active element is taken by a different pipe end! Could not connect!");
        } else {
            out.println("Invalid command, operation failed");
        }
    }

    /**
     * Disconnects a pipe end from an active element.
     * If the pipe end can be disconnected, it adds the item to the inventory and
     * prints a success message.
     * 
     * @param activeElement the active element to disconnect the pipe end from
     * @param endOfPipe     the pipe end to be disconnected
     * @return true if the pipe end is disconnected successfully, false otherwise
     */
    public boolean disconnect(ActiveElement activeElement, EndOfPipe endOfPipe) {
        // TODO: update with prototype version
        printMethodName("disconnect");

        out.println("You are trying to disconnect a pipe end to an active element...");

        int canDisconnect = askQuestion("Does the active element allow the pipe to be disconnected?");
        if (canDisconnect == 1) {
            addInventory();
            out.println("Pipe end disconnected successfully!");
        } else if (canDisconnect == 2) {
            out.println(
                    "The active element can not function without this connection! Try to use changeInputPipe() or changeOutputPipe() instead!");
        } else {
            out.println("Invalid command, operation failed");
        }
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
        // TODO: update with prototype version
        return false;
    }

    /**
     * Checks if the inventory is free and returns corresponding response.
     * 
     * @return true if the inventory is free, false otherwise.
     */
    private boolean isInventoryEmpty() {
        // TODO: update with prototype version
        printMethodName("isInventoryEmpty()");

        return inventory == null;
    }

    /**
     * Checks if the pipe end is connected to an element and returns corresponding
     * response.
     * 
     * @return true if the pipe end is connected, false otherwise.
     */
    private boolean getConnected() {
        // TODO: update with prototype version
        // removed if not needed
        printMethodName("getConnected()");
        int isConnected = askQuestion("Is the pipe end connected to an element?");

        if (isConnected != 1 && isConnected != 2) {
            out.println("Invalid command, operation failed");
            return false;
        } else if (isConnected == 2) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the other end of the pipe is connected to an active element and
     * returns corresponding response.
     * 
     * @return true if the other end of the pipe is connected, false otherwise.
     */
    private boolean getPipeEnds() {
        // TODO: update with prototype version
        // remove if not needed
        printMethodName("getPipeEnds()");
        int hasOtherEnd = askQuestion("Is the other pipe end connected to an active element?");

        if (hasOtherEnd != 1 && hasOtherEnd != 2) {
            out.println("Invalid command, operation failed");
            return false;
        } else if (hasOtherEnd == 2) {
            out.println("The other end of this pipe is not connected to any active elements! operation failed");
            return false;
        }

        out.println("");
        return true;
    }

    /**
     * Checks if the cistern has a manufactured pump and returns corresponding
     * response.
     * 
     * @return true if the cistern has a manufactured pump, false otherwise.
     */
    private boolean getManufacturedElement() {
        // TODO: update with prototype version
        // remove if not needed
        printMethodName("getManufacturedElement()");
        int isManufactured = askQuestion("Does the cistern have a manufactured pump?");

        if (isManufactured != 1 && isManufactured != 2) {
            out.println("Invalid command, operation failed");
            return false;
        } else if (isManufactured == 2) {
            return false;
        }

        out.println("");
        return true;
    }

    /**
     * Sets the new location to the element.
     */
    private void setLocation() {
        printMethodName("setLocation");
    }

    /**
     * Cuts the pipe to be able to install the pump.
     */
    private void cutPipe() {
        // TODO: update with prototype version
        // remove if not needed
        printMethodName("cutPipe");
    }

    /**
     * Removes the item from the inventory.
     */
    private void removeInventory() {
        // TODO: update with prototype version
        printMethodName("removeInventory");
    }

    /**
     * Adds the item to the inventory.
     */
    private void addInventory() {
        printMethodName("AddInventory");
    }
}
