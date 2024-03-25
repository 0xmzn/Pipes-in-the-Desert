import static java.lang.System.out;
import java.util.Scanner;
import java.util.Set;

public class Plumber extends Player 
{

    public Plumber(){
        super();
    }

    public void pickUpPipeEnd(){
        printMethodName("pickUpPipeEnd");
        
        out.println("You are trying to pick up a pipe end...");
        if (!checkType("Plumber") || !checkInventory() || !getLocation("pipe end")){
            return;
        }
        boolean isConnectedToActiveElement = getConnected();
        if (isConnectedToActiveElement){
            disconnect();
        }
        else if (!getPipeEnds()){
            out.println("Pick up failed as the other end of pipe is also disconnected!");
            return;
        }

        out.println("Pipe picked up successfully!");
    }

    public void pickUpPump(){
        printMethodName("pickUpPump");
        
        out.println("You are trying to pick up a pump...");
        if (!checkType("Plumber") || !checkInventory() || !getLocation("pump") || !getManufacturedElement()){
            return;
        }
        
        out.println("Pump picked up successfully!");

    }

    public void installPump(){
        printMethodName("installPump");

        out.println("You are trying to install a pump into the system...");
        if (!checkType("plumber") || !checkInventory() 
        || !getLocation("pump") || !getManufacturedElement()){
            return;
        }

        setLocation();
        cutPipe();
        connect();
        setInputPipe();
        setOutputPipe();
        removeInventory();
        out.println("Pump installed successfully!");
    }

    public void placePipeEnd(){
        printMethodName("placePipeEnd");

        out.println("You are trying to place a pipe end into the system...");
        if (!checkType("plumber") || !checkInventory()){
            return;
        }

        int shouldConnect = askQuestion("Is there an active element you would like to connect your pipe to?");
        if(shouldConnect == 1){
            connect();
        }
        else if (shouldConnect == 2){
            out.println("Pipe end placed towards the desert successfully!");
        }
        else{
            out.println("Invalid command, operation failed");
            return;
        }
    }

    public void connect(){
        printMethodName("connect");
        
        out.println("You are trying to connect a pipe end to an active element...");

        int canConnect = askQuestion("Is there space to connect a new pipe end?");
        if(canConnect == 1){
            removeInventory();
            out.println("Pipe end connected successfully!");
        }
        else if (canConnect == 2){
            out.println("The active element is taken by a different pipe end! Could not connect!");
        }
        else{
            out.println("Invalid command, operation failed");
        }
    }

    public void disconnect(){
        printMethodName("disconnect");

        out.println("You are trying to disconnect a pipe end to an active element...");

        int canDisconnect = askQuestion("Does the active element allow the pipe to be disconnected?");
        if(canDisconnect == 1){
            removeInventory();
            out.println("Pipe end disconnected successfully!");
        }
        else if (canDisconnect == 2){
            out.println("The active element can not function without this connection! Try to use changeInputPipe() or changeOutputPipe() instead!");
        }
        else{
            out.println("Invalid command, operation failed");
        }

    }

    public void fixPipe(){
        printMethodName("fixPipe");

        out.println("You are trying to fix a pipe...");
        if (!checkType("plumber") || !isPunctured() || !getLocation("pipe")){
            return;
        }

        out.println("Pipe fixed successfully!");
    }

    public void fixPump(){
        printMethodName("fixPump");

        out.println("You are trying to fix a pump...");
        if (!checkType("plumber") || !isPunctured() || !getLocation("pipe")){
            return;
        }

        out.println("Pump fixed successfully!");
    }

    private boolean checkInventory(){
        printMethodName("checkInventory()");
        int isInventoryFree = askQuestion("Is the inventory free?");
        
        if (isInventoryFree != 1 && isInventoryFree != 2){
            out.println("Invalid command, operation failed");
            return false;
        }
        else if (isInventoryFree == 2){
            out.println("You can not pick up multiple items simultaneously, operation failed");
            return false;
        }

        out.println("");
        return true;
        
    }

    private boolean getConnected() {
        printMethodName("getConnected()");
        int isConnected = askQuestion("Is the pipe end connected to an element?");
        
        if (isConnected != 1 && isConnected != 2){
            out.println("Invalid command, operation failed");
            return false;
        }
        else if (isConnected == 2){
            return false;
        }
        return true;
    }

    private boolean getPipeEnds() {
        printMethodName("getPipeEnds()");
        int hasOtherEnd = askQuestion("Is the other pipe end connected to an active element?");
        
        if (hasOtherEnd != 1 && hasOtherEnd != 2){
            out.println("Invalid command, operation failed");
            return false;
        }
        else if (hasOtherEnd == 2){
            out.println("The other end of this pipe is not connected to any active elements! operation failed");
            return false;
        }

        out.println("");
        return true;    
    }

    private boolean getManufacturedElement() {
        printMethodName("getManufacturedElement()");
        int isManufactured = askQuestion("Does the cistern have a manufactured pump?");
        
        if (isManufactured != 1 && isManufactured != 2){
            out.println("Invalid command, operation failed");
            return false;
        }
        else if (isManufactured == 2){
            return false;
        }

        out.println("");
        return true;    
    }

    private void setLocation() {
        printMethodName("setLocation");
    }

    private void cutPipe() {
        printMethodName("cutPipe");
    }

    private void removeInventory() {
        printMethodName("removeInventory");
    }
}
