import static java.lang.System.out;
import java.util.Scanner;
import java.util.Set;

/**
 * The Plumber class extends the Player class and implements Plumber-specific
 * functionality.
 */
public class Plumber extends Player {

    /**
     * Constructs a new Plumber object.
     * It calls the constructor of the superclass (Player) using the super()
     * keyword.
     */
    public Plumber() {
        super();
    }

    /**
     * Picks up a pipe end from the current location.
     * Checks for all preconditions before picking up the pipe end: player type, inventory
     * state, and player location.
     * If any of the conditions are not met, the method returns without performing
     * any action.
     * If the pipe end is connected to any active element disconnects.
     * If the other end of the pipe is also disconnected, it prints a failure
     * message and returns.
     * Otherwise, it prints a success message.
     */
    public void pickUpPipeEnd() {
        printMethodName("pickUpPipeEnd");

        out.println("You are trying to pick up a pipe end...");
        if (!checkType("Plumber") || !checkInventory() || !getLocation("pipe end")) {
            return;
        }
        boolean isConnectedToActiveElement = getConnected();
        if (isConnectedToActiveElement) {
            disconnect();
        } else if (!getPipeEnds()) {
            out.println("Pick up failed as the other end of pipe is also disconnected!");
            return;
        }

        out.println("Pipe picked up successfully!");
    }

    /**
     * Picks up a pump from the current location.
     * Checks for all preconditions before picking up the pump: player type, inventory
     * state, player location, and pump availability.
     * If any of the conditions are not met, the method returns without performing
     * any action.
     * Otherwise, it prints a success message.
     */
    public void pickUpPump() {
        printMethodName("pickUpPump");

        out.println("You are trying to pick up a pump...");
        if (!checkType("Plumber") || !checkInventory() || !getLocation("pump") || !getManufacturedElement()) {
            return;
        }

        out.println("Pump picked up successfully!");
    }

    /**
     * Installs a pump into the system.
     * Checks for all preconditions before installing the pump: player type, inventory state.
     * If any of the conditions are not met, the method returns without performing any action.
     * Otherwise, it performs the following steps:
     * - Sets the location.
     * - Cuts the pipe.
     * - Connects the pipeEnds to the Pump.
     * - Changes the input pipe.
     * - Changes the output pipe.
     * - Removes the item from the inventory.
     * Finally, it prints a success message.
     */
    public void installPump() {
        printMethodName("installPump");

        out.println("You are trying to install a pump into the system...");
        if (!checkType("plumber") || !checkInventory()
                || !getLocation("pump") || !getManufacturedElement()) {
            return;
        }

        setLocation();
        cutPipe();
        connect();
        changeInputPipe();
        changeOutputPipe();
        removeInventory();
        out.println("Pump installed successfully!");
    }

    /**
     * Places a pipe end into the system.
     * Checks for all preconditions before placing the pipe end: player type, inventory state.
     * If any of the conditions are not met, the method returns without performing any action.
     * Otherwise, it asks the user if there is an active element to connect the pipe
     * to.
     * If the user chooses to connect, it connects the pipe.
     * If the user chooses to place the pipe end towards the desert, it prints a
     * success message.
     */
    public void placePipeEnd() {
        printMethodName("placePipeEnd");

        out.println("You are trying to place a pipe end into the system...");
        if (!checkType("Plumber") || !checkInventory()) {
            return;
        }

        int shouldConnect = askQuestion("Is there an active element you would like to connect your pipe to?");
        if (shouldConnect == 1) {
            connect();
        } else if (shouldConnect == 2) {
            out.println("Pipe end placed towards the desert successfully!");
        } else {
            out.println("Invalid command, operation failed");
            return;
        }
    }

    /**
     * Connects a pipe end to an active element.
     * Checks for all preconditions before picking up the pipe end: player type, inventory;
     * If any of the conditions are not met, the method returns without performing any action.
     * If the pipe end is already connected to any other active element, it fails.
     * Otherwises it removes the item from the inventory and prints a success message.
     */
    public void connect() {
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
     * If the pipe end can be disconnectted, it adds the item to the inventory and
     * prints a success message.
     */
    public void disconnect() {
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
     * Fixes a punctured pipe.
     * Checks the precondition before fixing the pipe: player type, pipe punctured state, location.
     * If any of the conditions are not met, the method returns without performing
     * any action.
     * Otherwise, it prints a success message.
     */
    public void fixPipe() {
        printMethodName("fixPipe");

        out.println("You are trying to fix a pipe...");
        if (!checkType("plumber") || !isPunctured() || !getLocation("pipe")) {
            return;
        }

        out.println("Pipe fixed successfully!");
    }

    /**
     * Fixes a punctured pump.
     * Checks precondition before fixing the pump: player type, pump punctured state, location.
     * If any of the conditions are not met, the method returns without performing
     * any action.
     * Otherwise, it prints a success message.
     */
    public void fixPump() {
        printMethodName("fixPump");

        out.println("You are trying to fix a pump...");
        if (!checkType("plumber") || !isPunctured() || !getLocation("pipe")) {
            return;
        }

        out.println("Pump fixed successfully!");
    }

    /**
     * Checks if the inventory is free and returns corresponding response.
     * @return true if the inventory is free, false otherwise.
     */
    private boolean checkInventory() {
        printMethodName("checkInventory()");
        int isInventoryFree = askQuestion("Is the inventory free?");

        if (isInventoryFree != 1 && isInventoryFree != 2) {
            out.println("Invalid command, operation failed");
            return false;
        } else if (isInventoryFree == 2) {
            out.println("You can not pick up multiple items simultaneously, operation failed");
            return false;
        }

        out.println("");
        return true;
    }

    /**
     * Checks if the pipe end is connected to an element and returns corresponding response.
     * @return true if the pipe end is connected, false otherwise.
     */
    private boolean getConnected() {
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
     * Checks if the other end of the pipe is connected to an active element and returns corresponding response.
     * @return true if the other end of the pipe is connected, false otherwise.
     */
    private boolean getPipeEnds() {
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
     * Checks if the cistern has a manufactured pump and returns corresponding response.
     * @return true if the cistern has a manufactured pump, false otherwise.
     */
    private boolean getManufacturedElement() {
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
        printMethodName("cutPipe");
    }

    /**
     * Removes the item from the inventory.
     */
    private void removeInventory() {
        printMethodName("removeInventory");
    }

    /**
     * Adds the item to the inventory.
     */
    private void addInventory() {
        printMethodName("AddInventory");
    }
}
