import java.util.Scanner;
import java.util.Set;

public class Plumber extends Player 
{
    Scanner scanner;

    public Plumber(){
        super();
        scanner = new Scanner(System.in);
    }

    private static void printMethodName(String methodName){
        System.out.println("\n--------------------");
        System.out.println(methodName);
        System.out.println("--------------------\n");
    }

    private int askQuestion(String question){
        System.out.println(question);
        System.out.println("1. Yes");
        System.out.println("2. No");

        return scanner.nextInt();
    }

    private boolean checkType(){
        printMethodName("checkType()");
        int isPlumber = askQuestion("Are you a plumber?");

        if (isPlumber != 1 && isPlumber != 2){
            System.out.println("Invalid command, pickUp() failed");
            return false;
        }
        else if (isPlumber == 2){
            System.out.println("This player is not a plumber, pickUp() failed");
            return false;
        }

        System.out.println("");
        return true;
    }

    private boolean checkInventory(){
        printMethodName("checkInventory()");
        int isInventoryFree = askQuestion("Is the inventory free?");
        
        if (isInventoryFree != 1 && isInventoryFree != 2){
            System.out.println("Invalid command, pickUp() failed");
            return false;
        }
        else if (isInventoryFree == 2){
            System.out.println("This player is not a plumber, pickUp() failed");
            return false;
        }

        System.out.println("");
        return true;
        
    }

    private boolean getConnected() {
        printMethodName("getConnected()");
        int isConnected = askQuestion("Is the pipe end connected to an element?");
        
        if (isConnected != 1 && isConnected != 2){
            System.out.println("Invalid command, pickUp() failed");
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
            System.out.println("Invalid command, pickUp() failed");
            return false;
        }
        else if (hasOtherEnd == 2){
            System.out.println("The other end of this pipe is not connected to any active elements!");
            return false;
        }

        System.out.println("");
        return true;    
    }

    private boolean getManufacturedElement() {
        printMethodName("getManufacturedElement()");
        int isManufactured = askQuestion("Does the cistern have a manufactured pump?");
        
        if (isManufactured != 1 && isManufactured != 2){
            System.out.println("Invalid command, pickUp() failed");
            return false;
        }
        else if (isManufactured == 2){
            return false;
        }

        System.out.println("");
        return true;    
    }

    public void pickUpPipeEnd(){
        printMethodName("pickUpPipeEnd");
        
        System.out.println("You are trying to pick up a pipe end...");
        if (!checkType() || !checkInventory() || !getLocation() || !getManufacturedElement()){
            return;
        }
        boolean isConnectedToActiveElement = getConnected();
        if (!isConnectedToActiveElement){
            disconnect();
        }
        else if (isConnectedToActiveElement && !getPipeEnds()){
            System.err.println("Pick up failed as the other end of pipe is also disconnected!");
            return;
        }

        System.out.println("Pipe picked up successfully!");
    }

    public void pickUpPump(){
        printMethodName("pickUpPump");
        
        System.out.println("You are trying to pick up a pump...");
        if (!checkType() || !checkInventory() || !getLocation() || !getManufacturedElement()){
            return;
        }
        
        System.out.println("Pump picked up successfully!");

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
    public void installPump(){
        printMethodName("installPump");

        System.out.println("You are trying to install a pump into the system...");
        if (!checkType() || !checkInventory() 
        || !getLocation() || !getManufacturedElement()){
            return;
        }

        setLocation();
        cutPipe();
        connect();
        setInputPipe();
        setOutputPipe();
        removeInventory();
        System.out.println("Pump picked up successfully!");

    }

    public void placePipeEnd(){
        printMethodName("placePipeEnd");


        System.out.println("You are trying to place a pipe end into the system...");
        if (!checkType() || !checkInventory() || !getConnected()){
            return;
        }

        boolean isConnectedToActiveElement = getConnected();
        if (!isConnectedToActiveElement){
            connect();
        }
        else if (isConnectedToActiveElement && !getPipeEnds()){
            System.err.println("Pick up failed as the other end of pipe is also disconnected!");
            return;
        }

        setLocation();
        removeInventory();

        System.out.println("Pump picked up successfully!");
    }

    public void connect(){
        printMethodName("connect");
    }

    public void disconnect(){
        printMethodName("disconnect");
    }

    public void fixPipe(){
        printMethodName("fixPipe");
    }

    public void fixPump(){
        printMethodName("fixPump");
    }
}
